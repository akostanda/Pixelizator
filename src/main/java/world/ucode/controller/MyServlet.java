package world.ucode.controller;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import world.ucode.model.PictureString;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/upload")
@MultipartConfig
public class MyServlet extends HttpServlet {
    private Gson gson = new Gson();
    private PrintWriter writer;
    private Part filePart;
    private int pixSize;
    private String format;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writer = response.getWriter();
        filePart = request.getPart("file");
        pixSize = Integer.parseInt(request.getHeader("sizePixel"));
        format = request.getHeader("format");
        String jsonImage = getBytJason(filePart);

        if (filePart != null) {
            writer = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer.print(jsonImage);
            writer.close();
        }
    }

    private String getBytJason(Part filePart) throws IOException {
        InputStream inputStream = filePart.getInputStream();
        BufferedImage bufferedImage = pixelateImage(inputStream);
        PictureString pictureString = new PictureString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String imageInString;
        String json;
        byte[] bytes;

        ImageIO.write(bufferedImage, format, baos);
        bytes = baos.toByteArray();
        imageInString = Base64.encodeBase64String(bytes);
        pictureString.setImage(imageInString);
        json = gson.toJson(pictureString);
        return json;
    }

    private BufferedImage pixelateImage(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        Raster src = bufferedImage.getData();
        WritableRaster dest = src.createCompatibleWritableRaster();

        for (int y = 0; y < src.getHeight(); y += pixSize) {
            for (int x = 0; x < src.getWidth(); x += pixSize) {
                double[] pixel = new double[4];
                pixel = src.getPixel(x, y, pixel);
                for (int yd = y; (yd < y + pixSize) && (yd < dest.getHeight()); yd++) {
                    for (int xd = x; (xd < x + pixSize) && (xd < dest.getWidth()); xd++) {
                        dest.setPixel(xd, yd, pixel);
                    }
                }
            }
        }
        bufferedImage.setData(dest);
        return bufferedImage;
    }
}
