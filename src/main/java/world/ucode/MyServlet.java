package world.ucode;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writer = response.getWriter();
        Part filePart = request.getPart("file");
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

        ImageIO.write(bufferedImage, "png", baos);
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

        for (int y = 0; y < src.getHeight(); y += 15) {
            for (int x = 0; x < src.getWidth(); x += 15) {
                double[] pixel = new double[4];
                pixel = src.getPixel(x, y, pixel);
                for (int yd = y; (yd < y + 15) && (yd < dest.getHeight()); yd++) {
                    for (int xd = x; (xd < x + 15) && (xd < dest.getWidth()); xd++) {
                        dest.setPixel(xd, yd, pixel);
                    }
                }
            }
        }
        bufferedImage.setData(dest);
        return bufferedImage;
    }



//    public class Pixelizator {
//
//        private InputStream fileContent = null;
//        private String imageByte = null;
//                private Pictures pic = null;
//        private BufferedImage img = null;
//        private byte[] bytes = null;
//        private int pixsize = 1;
//        private String format;
//

//        public Pixelizator(Part filePart, String pixsize, String format) throws IOException {
//            fileContent = filePart.getInputStream();
//            this.format = format;
//            this.pixsize = Integer.parseInt(pixsize);
//
//
//            if (fileContent != null) {
//                Pixlate();
//                imageByte = Base64.encodeBase64String(bytes);
//            }
//            pic = new Pictures();
//            pic.setBase64Image(imageByte);
//        }
//
//        public Pictures getImageByte() {
//            return pic;
//        }
//
//        private void Pixlate() throws IOException {
//            img = ImageIO.read(fileContent);
//            Raster src = img.getData();
//            WritableRaster dest = src.createCompatibleWritableRaster();
//
//            for (int y = 0; y < src.getHeight(); y += pixsize) {
//                for (int x = 0; x < src.getWidth(); x += pixsize) {
//                    // Copy the pixel
//                    double[] pixel = new double[4];
//                    pixel = src.getPixel(x, y, pixel);
//                    for (int yd = y; (yd < y + pixsize) && (yd < dest.getHeight()); yd++) {
//                        for (int xd = x; (xd < x + pixsize) && (xd < dest.getWidth()); xd++) {
//                            dest.setPixel(xd, yd, pixel);
//                        }
//                    }
//                }
//            }
//            img.setData(dest);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(img, format, baos);
//            bytes = baos.toByteArray();
//        }
//    }
}
