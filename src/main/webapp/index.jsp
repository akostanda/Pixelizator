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
        <h3> Choose the file for uploading </h3>
        <input type="file">
        <button id="loadButton">LoadButton</button>
    </div>
    <br>
    <p class="text">Block size: </p>
    <p class="text" id="outNumber">10</p>
    <div class="mainDiv">
        <div class="loadedImg">
            <img class="im" id="forInput" src="" alt="rawImage">
            <span style="padding: 0px 80px;">&nbsp;</span>
            <img class="im" id="forOutput" src="" alt="processedImage">
        </div>
    </div>
    <script src="javaScript/fetch.js"></script>
</body>
</html>
