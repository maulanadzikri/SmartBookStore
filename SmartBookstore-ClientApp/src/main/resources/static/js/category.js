$(document).ready(() =>{
    $("#table-category").DataTable({
        ajax: {
            url: "/api/category",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            {data: "id"},
            {data: "name"},
            {data: null,
                render: (data) => {
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
                                title="Delete ${data.name}" onclick="deleteCategory(${data.id})">
                                Delete
                        </button>
                    </div>
                    `;
                },
            }
        ]
    })
});

$("#create-new-category").click((event) => {
    event.preventDefault();
    let valueName = $("#create-category-name").val();

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
            url: "/api/category",
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                // console.log(res);
                $("#create-modal").modal("hide");
                $("#table-category").DataTable().ajax.reload();
                $("#create-category-name").val("");
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "New Category has been created!",
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
        url: "/api/category/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-category-id").val(res.id);
            $("#detail-category-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
};

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/category/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#update-category-id").val(res.id);
            $("#update-category-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

$("#btn-update-category").click((event) => {
    event.preventDefault();
    let valueId = $("#update-category-id").val();
    let valueName = $("#update-category-name").val();

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
            url: "/api/category/" + valueId,
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                $("#update-modal").modal("hide");
                $("#table-category").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Category has been updated!",
                    showConfirmButton: false,
                    timer: 1500,
                });
                $("#update-category-id").val("");
                $("#update-category-name").val("");
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
});

function deleteCategory(id) {
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
                url: "/api/category/" + id,
                dataType: "JSON",
                beforeSend: addCsrf(),
                contentType: "application/json",
                success: (res) => {
                    $("#table-category").DataTable().ajax.reload();
                },
                error: (err) => {
                    console.log(err);
                },
                });
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your Category has been deleted.",
                    icon: "success",
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your imaginary Category is safe :)",
                    icon: "error",
                });
            }
    });
}