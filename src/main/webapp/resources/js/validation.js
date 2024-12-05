
function showError(message) {
    const errorContainer = document.querySelector(".error-message");
    errorContainer.style.display = "block";
    errorContainer.innerHTML = message;

    setTimeout(hideError, 5000);
}

function hideError() {
    const errorContainer = document.querySelector(".error-message");
    errorContainer.style.display = "none";
}

function validateForm() {
    let isValid = true;
    let errorMessage = "";

    let xSelected = false;
    document.querySelectorAll(".checkbox-x").forEach(function(checkbox) {
        checkbox.addEventListener("change", function() {
            if (checkbox.checked) {
                document.querySelectorAll(".checkbox-x").forEach(function(otherCheckbox) {
                    if (otherCheckbox !== checkbox) {
                        otherCheckbox.checked = false;
                    }
                });
                xSelected = true;
            }
        });
    });
    if (!xSelected) {
        errorMessage += "Выберите одно значение для X!<br/>";
        isValid = false;
    }

    let yInput = document.getElementById("y");
    let yValue = parseFloat(yInput.value);
    if (isNaN(yValue) || yValue < -3 || yValue > 5 || yInput.value.length > 15) {
        showError("Значение Y должно быть числом от -3 до 5");
        isValid = false;
    }

    if (isValid) {
        hideError();
    } else {
        showError(errorMessage);
    }

    return isValid;
}