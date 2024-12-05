function updateHiddenX(checkbox) {
    const xInputHidden = document.getElementById("form:x");

    if (checkbox.checked) {
        xInputHidden.value = checkbox.value;
    }
}
