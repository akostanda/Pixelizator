<html>
<link rel="stylesheet" href="cssFile.css">

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
    <input type="range" class="choiceNumber" id="rangeSetNumber" min="1" max="100"  value="10">
</div>

<%--<p class="text">Block size: </p>--%>
<%--<p class="text" id="outNumber">10</p>--%>


<p class="text">Block size: </p>
<p class="text" id="sizeNumber">10</p>
<div class="mainDiv">
    <div class="loadedImg">
        <img class="imageIn" id="forInImage" src="" alt="RAWIMAGE">
        <%--        <span style="padding: 0px 280px;">&nbsp;</span>--%>
        <div class="DownloadBut">
            <a href="#" id="downloadLinkjpg" download="pixImage.jpg"></a>
            <button disabled="disabled" id="dwnJpg" class="button">Download<br>JPG</button>
            <a href="#" id="downloadLinkpng" download="pixImage.png"></a>
            <button disabled="disabled" id="dwnPng" class="button">Download<br>PNG</button>
            <%--    <br>--%>
            <a href="#" id="downloadLinkbmp" download="pixImage.bmp"></a>
            <button disabled="disabled" id="dwnBmp" class="button">Download<br>BMP</button>
        </div>
        <img class="imageOut" id="forOutImage" src="" alt="PROCESSEDIMAGE">
    </div>
</div>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--</div>--%>
<%--<span style="padding: 100px 580px;">&nbsp;</span>--%>

<script src="javaScript/fetch.js"></script>
</body>
</html>
