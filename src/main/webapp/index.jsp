<html>
<link rel="stylesheet" href="cssFile.css">
<link rel="icon" href="data:;base64,=">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pixelizator</title>
</head>

<body>
<h1>WOULD YOU LIKE TO PIXILIZE</h1>
<div>
    <h3> Choose PNG/JPG file for uploading </h3>
    <input type="file" value="upload" id="imageUploader" name="file" accept=".jpg, .jpeg, .png" onchange="loadRawImage">
    <button id="loadButton">LoadButton</button>
</div>
<br>
<div>
    <p class="text">Pixel size: <span class="text" id="sizeNumber">10</span></p>
    <input type="range" class="choiceNumber" id="rangeSetNumber" min="1" max="100"  value="10">
<%--    <p class="text" id="sizeNumber">10</p>--%>
</div>

<div class="mainDiv">
    <div class="loadedImg">
        <img class="imageIn" id="forInImage" src="" alt="RAWIMAGE">
        <%--        <span style="padding: 0px 280px;">&nbsp;</span>--%>
        <div class="DownloadBut">
            <a href="#" id="linkForJpgDownload" download="pixImage.jpg"></a>
            <button disabled="disabled" id="dwnJpg" class="button">Download<br>JPG</button>
            <a href="#" id="linkForPngDownload" download="pixImage.png"></a>
            <button disabled="disabled" id="dwnPng" class="button">Download<br>PNG</button>
            <a href="#" id="linkForBmpDownload" download="pixImage.bmp"></a>
            <button disabled="disabled" id="dwnBmp" class="button">Download<br>BMP</button>
        </div>
        <img class="imageOut" id="forOutImage" src="" alt="PROCESSEDIMAGE">
    </div>
</div>

<script src="javaScript/fetch.js"></script>
</body>
</html>
