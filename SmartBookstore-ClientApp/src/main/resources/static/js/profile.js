$(document).ready(function() {
    console.log("Update Profile Ready");
});

function updateProfile() {
    let valueId = $('#user-id').val();
    let valueName = $('#fullname').val();
    let valueEmail = $('#user-email').val();
    let valuePhone = $('#user-phone').val();

    if (valueName === "" || valueName === null) {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Please fill all fields!!!",
            showConfirmButton: false,
            timer: 1500,
        });
    } else {
        $.ajax({
            type: 'PUT',
            url: '/api/userdetail/' + valueId,
            beforeSend: addCsrf(),
            contentType: 'application/json',
            data: JSON.stringify({
                fullname: valueName,
                email: valueEmail,
                phone: valuePhone
            }),
            success: (res) => {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Profile has been updated!",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(() => {
                    window.location.href = "http://localhost:9099/profile";
                });
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
}

