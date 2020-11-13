const url = 'http://localhost:8080/upload';
var rawImage = document.getElementById("forInImage")
var processedImage = document.getElementById("forOutImage")
imageUploader.addEventListener('change', loadRawImage);

function loadRawImage(event) {
    rawImage.src = window.URL.createObjectURL(event.target.files[0]);
}

document.getElementById("loadButton").addEventListener('click', async function () {
    const formData = new FormData();
    const fileField = document.querySelector('input[type="file"]');

    formData.append("file", fileField.files[0]);
    try {
        let response = await fetch(url, {
            method: 'POST', // или 'PUT'
            body: formData, // данные могут быть 'строкой' или {объектом}!
            enctype: "multipart/form-data"
        });
        if (response.ok) {
            // alert("response.ok1")
            const json = await response.json();
            // alert("response.ok2")
            console.log('Ne Zhopa', json);
            // alert("response.ok3")
            processedImage.setAttribute("src", "data:image/png;base64," + json.pixelatedImage);
        }
    } catch (error) {
        console.error('Zhopa', error);
    }
});

document.getElementById("rangeSetNumber").addEventListener('mousemove', function () {
    document.getElementById("sizeNumber").textContent = document.getElementById("rangeSetNumber").value
})