

function drawCoordinatePlane(canvas) {
    ctx = canvas.getContext('2d');
    const centerX = canvas.width / 2
    const centerY = canvas.height / 2

    ctx.clearRect(0, 0, canvas.width, canvas.height)

    ctx.beginPath()
    ctx.moveTo(0, centerY)
    ctx.lineTo(canvas.width, centerY)
    ctx.strokeStyle = 'black'
    ctx.lineWidth = 2
    ctx.stroke()

    ctx.beginPath()
    ctx.moveTo(centerX, 0)
    ctx.lineTo(centerX, canvas.height)
    ctx.strokeStyle = 'black'
    ctx.lineWidth = 2
    ctx.stroke()

    ctx.font = '10px Arial'
    for (let i = -5; i <= 5; i++) {
        if (i !== 0) {
            ctx.fillText(i, centerX + i * scale - 5, centerY + 15)
            ctx.beginPath()
            ctx.moveTo(centerX + i * scale, centerY - 5)
            ctx.lineTo(centerX + i * scale, centerY + 5)
            ctx.stroke()

            ctx.fillText(-i, centerX - 15, centerY + i * scale + 5)
            ctx.beginPath()
            ctx.moveTo(centerX - 5, centerY + i * scale)
            ctx.lineTo(centerX + 5, centerY + i * scale)
            ctx.stroke()
        }
    }
}

function drawPlot(canvas, r) {
    const scale = 40
    const centerX = canvas.width / 2
    const centerY = canvas.height / 2

    r *= scale

    ctx.beginPath()
    ctx.arc(centerX, centerY, r, -Math.PI / 2, Math.PI, true)
    ctx.lineTo(centerX, centerY)
    ctx.closePath()
    ctx.fillStyle = 'rgba(98,227,251,0.3)'
    ctx.fill()
    ctx.strokeStyle = 'rgb(97,220,244)'
    ctx.stroke()

    ctx.beginPath()
    ctx.moveTo(centerX, centerY)
    ctx.lineTo(centerX + r, centerY)
    ctx.lineTo(centerX, centerY - r / 2)
    ctx.closePath()
    ctx.fill()
    ctx.stroke()

    ctx.beginPath()
    ctx.rect(centerX - r, centerY, r, r)
    ctx.closePath()
    ctx.fill()
    ctx.stroke()
    ctx.strokeStyle = 'rgb(0,0,0)'
}


function addPoint(x, y, r, isIn) {
    const xCanvas = centerX + x * scale
    const yCanvas = centerY - y * scale

    ctx.beginPath()
    ctx.arc(xCanvas, yCanvas, 4, 0, Math.PI * 2)
    ctx.fillStyle = isIn ? 'blue' : 'red'
    ctx.fill()
    ctx.stroke()

    points.push({x,y})
}


function addPoints() {
//     <%
//             if (application.getAttribute("responseList") != null) {
//                 List<Response> responses = (List<Response>) application.getAttribute("responseList");
//                 for (int i = responses.size() - 1; i >= 0; i--) {
//                     Response resp = responses.get(i);
//                 %>
//                     addPoint(<%= resp.getX() %>, <%= resp.getY() %>, <%= resp.getR() %>, <%= resp.isIn() %>);
//                     <%
//                 }
//             }
// %>
}

addPoints();

function canvasClick (event) {
    const rect = canvas.getBoundingClientRect();
    const mouseX = event.clientX - rect.left;
    const mouseY = event.clientY - rect.top;

    const x = (mouseX - centerX) / scale;
    const y = (centerY - mouseY) / scale;

    let rValue = 3;
    const selectedRCheckbox = Array.from(rCheckboxInputs).find(input => input.checked);
    if (selectedRCheckbox) {
        rValue = parseFloat(selectedRCheckbox.value);
    }

    if (x >= -3 && x <= 5 && y >= -5 && y <= 3) {

        xTextInput.value = x.toFixed(2);
        yTextInput.value = y.toFixed(2)
        rCheckboxInputs.forEach(input => {
            input.checked = parseFloat(input.value) === rValue;
        })

        // console.log(x,y,rValue)
        submitForm.submit();

    } else {
        showErrorPopup('Недопустимые координаты');
    }
}

window.onload = function() {
    const canvas = document.getElementById("plot-canvas")
    if (canvas) {

        let points = []

        const ctx = canvas.getContext('2d')

        drawCoordinatePlane(canvas)
        drawPlot(canvas, 3)

        addPoints()

        // canvas.addEventListener('click', canvasClick);

    } else {
        console.error("канваса нету");
    }
};