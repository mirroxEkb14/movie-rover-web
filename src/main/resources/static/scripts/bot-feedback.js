function showSnackbar(event) {
    event.preventDefault();

    var snackbar = document.getElementById("snackbar");
    snackbar.className = "show";

    setTimeout(function() { snackbar.className = snackbar.className.replace("show", ""); }, 3000);

    var form = event.target;
    var formData = new FormData(form);

    fetch(form.action, {
        method: form.method,
        body: formData
    }).then(function(response) {
        if (response.ok) {
            form.reset();
        } else {
            alert("There was an error submitting your feedback. Please try again.");
        }
    }).catch(function(error) {
        alert("There was an error submitting your feedback. Please try again.");
    });
}