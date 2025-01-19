$(document).ready(() =>{
    $("#table-userm").DataTable({
        ajax: {
            url: "/api/userdetail",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            {data: "id"},
            {data: "fullname"},
            {data: null,
                render: (data) => {
                    console.log(data.name);
                    return /*html*/ `
                    <div class="d-flex gap-4 justify-content-center align-items-center">
                        <button type="button" class="btn btn-primary btn-sm d-flex align-items-center"
                                data-bs-toggle="modal" data-bs-target="#detail-modal"
                                title="Detail ${data.name}" onclick="findById(${data.id})">
                                Detail
                        </button>
                        <button type="button" class="btn btn-warning btn-sm d-flex align-items-center text-white"
                                data-bs-toggle="modal" data-bs-target="#update-modal"
                                title="Update ${data.name}" onclick="beforeUpdate(${data.id})">
                                Update
                        </button>
                        <button type="button" class="btn btn-danger btn-sm d-flex align-items-center"
                                title="Delete ${data.name}" onclick="deleteUser(${data.id})">
                                Delete
                        </button>
                    </div>
                    `;
                },
            }
        ]
    })
});

// $("#create-new-userm").click((event) => {
//     event.preventDefault();
//     let valueName = $("#create-userm-name").val();

//     if (valueName === "" || valueName === null) {
//         Swal.fire({
//           position: "center",
//           icon: "error",
//           title: "Please fill all field!!!",
//           showConfirmButton: false,
//           timer: 1500,
//         });
//       } else {
//         $.ajax({
//             method: "POST",
//             url: "/api/userdetail",
//             dataType: "JSON",
//             beforeSend: addCsrf(),
//             contentType: "application/json",
//             data: JSON.stringify({
//                 name: valueName,
//             }),
//             success: (res) => {
//                 // console.log(res);
//                 $("#create-modal").modal("hide");
//                 $("#table-userm").DataTable().ajax.reload();
//                 $("#create-userm-name").val("");
//                 Swal.fire({
//                     position: "center",
//                     icon: "success",
//                     title: "New User has been created!",
//                     showConfirmButton: false,
//                     timer: 1500,
//                 });
//             },
//             error: (err) => {
//                 console.log(err);
//             }
//         });
//     }
// });

let findById = (id) => {
    $.ajax({
        method: "GET",
        url: "/api/userdetail/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-userm-id").val(res.id);
            $("#detail-userm-name").val(res.fullname);
            $("#detail-userm-email").val(res.email);
            $("#detail-userm-phone").val(res.phone);
        },
        error: (err) => {
            console.log(err);
        },
    });
};

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/userdetail/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#update-userm-id").val(res.id);
            $("#update-userm-name").val(res.fullname);
            $("#update-userm-email").val(res.email);
            $("#update-userm-phone").val(res.phone);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

$("#btn-update-userm").click((event) => {
    event.preventDefault();
    let valueId = $("#update-userm-id").val();
    let valueName = $("#update-userm-name").val();
    let valueEmail = $("#update-userm-email").val();
    let valuePhone = $("#update-userm-phone").val();

    if (valueName === "" || valueName === null) {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Please fill all field!!!",
            showConfirmButton: false,
            timer: 1500,
        });
    } else {
        $.ajax({
            method: "PUT",
            url: "/api/userdetail/" + valueId,
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                fullname: valueName,
                email: valueEmail,
                phone: valuePhone,
            }),
            success: (res) => {
                $("#update-modal").modal("hide");
                $("#table-userm").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "User has been updated!",
                    showConfirmButton: false,
                    timer: 1500,
                });
                $("#update-userm-id").val("");
                $("#update-userm-name").val("");
                $("#update-userm-email").val("");
                $("#update-userm-phone").val("");
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
});

function deleteUser(id) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success swal-button",
            cancelButton: "btn btn-danger",
        },
        buttonsStyling: false,
    });
    swalWithBootstrapButtons.fire({
        title: "Are you sure?",
        text: "You won't be able to delete this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel!",
        reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                method: "DELETE",
                url: "/api/userdetail/" + id,
                dataType: "JSON",
                beforeSend: addCsrf(),
                contentType: "application/json",
                success: (res) => {
                    $("#table-userm").DataTable().ajax.reload();
                },
                error: (err) => {
                    console.log(err);
                },
                });
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your User has been deleted.",
                    icon: "success",
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your imaginary User is safe :)",
                    icon: "error",
                });
            }
    });
}