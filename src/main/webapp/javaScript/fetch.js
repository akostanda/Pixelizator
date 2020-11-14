const url = 'http://localhost:8080/upload';
var rawImage = document.getElementById("forInImage")
var processedImage = document.getElementById("forOutImage")
let jpgButton = document.getElementById("dwnJpg");
let pngButton = document.getElementById("dwnPng");
let bmpButton = document.getElementById("dwnBmp");

jpgButton.style.opacity = 0;
pngButton.style.opacity = 0;
bmpButton.style.opacity = 0;

imageUploader.addEventListener('change', loadRawImage);

jpgButton.addEventListener('click', () => {
    document.getElementById("linkForJpgDownload").href = processedImage.src;
    document.getElementById("linkForJpgDownload").click();
})

pngButton.addEventListener('click', () => {
    document.getElementById("linkForPngDownload").href = processedImage.src;
    document.getElementById("linkForPngDownload").click();
})

bmpButton.addEventListener('click', () => {
    document.getElementById("linkForBmpDownload").href = processedImage.src;
    document.getElementById("linkForBmpDownload").click();
})

function loadRawImage(event) {
    rawImage.src = window.URL.createObjectURL(event.target.files[0]);
    jpgButton.disabled = true;
    pngButton.disabled = true;
    bmpButton.disabled = true;
    jpgButton.style.opacity = 0;
    pngButton.style.opacity = 0;
    bmpButton.style.opacity = 0;
    processedImage.setAttribute("src", "#");
}

document.getElementById("loadButton").addEventListener('click', async function () {
    const formData = new FormData();
    const fileField = document.querySelector('input[type="file"]');

    formData.append("file", fileField.files[0]);
    try {
        let str = document.getElementById('imageUploader').value.split('.');
        let response = await fetch(url, {
            method: 'POST', // или 'PUT'
            body: formData, // данные могут быть 'строкой' или {объектом}!
            enctype: "multipart/form-data",
            headers: {
                'sizePixel': document.getElementById("rangeSetNumber").value,
                'format': str[str.length - 1]
            }
        });
        if (response.ok) {
            const json = await response.json();
            processedImage.setAttribute("src", "data:image/png;base64," + json.pixelatedImage);
            jpgButton.disabled = false;
            pngButton.disabled = false;
            bmpButton.disabled = false;
            jpgButton.style.opacity = 1;
            pngButton.style.opacity = 1;
            bmpButton.style.opacity = 1;
        }
    } catch (error) {
        console.error('error:', error);
    }
});

document.getElementById("rangeSetNumber").addEventListener('mousemove', function () {
    document.getElementById("sizeNumber").textContent = document.getElementById("rangeSetNumber").value
})

