$(document).ready(() =>{
    $("#table-author").DataTable({
        ajax: {
            url: "/api/author",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            {data: "id"},
            {data: "name"},
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
                                title="Delete ${data.name}" onclick="deleteAuthor(${data.id})">
                                Delete
                        </button>
                    </div>
                    `;
                },
            }
        ]
    })
});

$("#create-new-author").click((event) => {
    event.preventDefault();
    let valueName = $("#create-author-name").val();

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
            method: "POST",
            url: "/api/author",
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                // console.log(res);
                $("#create-modal").modal("hide");
                $("#table-author").DataTable().ajax.reload();
                $("#create-author-name").val("");
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "New Author has been created!",
                    showConfirmButton: false,
                    timer: 1500,
                });
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
});

let findById = (id) => {
    $.ajax({
        method: "GET",
        url: "/api/author/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-author-id").val(res.id);
            $("#detail-author-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
};

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/author/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#update-author-id").val(res.id);
            $("#update-author-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

$("#btn-update-author").click((event) => {
    event.preventDefault();
    let valueId = $("#update-author-id").val();
    let valueName = $("#update-author-name").val();

    if (valueName === "" || valueName === null) {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Please fill all field!!!",
            showConfirmButton: false,
            timer: 1500,
        });
    } else {
        console.log(valueId);
        console.log(valueName);
        $.ajax({
            method: "PUT",
            url: "/api/author/" + valueId,
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                $("#update-modal").modal("hide");
                $("#table-author").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Author has been updated!",
                    showConfirmButton: false,
                    timer: 1500,
                });
                $("#update-author-id").val("");
                $("#update-author-name").val("");
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
});

function deleteAuthor(id) {
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
                url: "/api/author/" + id,
                dataType: "JSON",
                beforeSend: addCsrf(),
                contentType: "application/json",
                success: (res) => {
                    $("#table-author").DataTable().ajax.reload();
                },
                error: (err) => {
                    console.log(err);
                },
                });
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your Author has been deleted.",
                    icon: "success",
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your imaginary Author is safe :)",
                    icon: "error",
                });
            }
    });
}