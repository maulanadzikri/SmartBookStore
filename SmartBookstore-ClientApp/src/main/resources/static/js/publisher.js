$(document).ready(() =>{
    $("#table-publisher").DataTable({
        ajax: {
            url: "/api/publisher",
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
                                title="Delete ${data.name}" onclick="deletePublisher(${data.id})">
                                Delete
                        </button>
                    </div>
                    `;
                },
            }
        ]
    })
});

$("#create-new-publisher").click((event) => {
    event.preventDefault();
    let valueName = $("#create-publisher-name").val();

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
            url: "/api/publisher",
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                // console.log(res);
                $("#create-modal").modal("hide");
                $("#table-publisher").DataTable().ajax.reload();
                $("#create-publisher-name").val("");
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "New Publisher has been created!",
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
        url: "/api/publisher/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-publisher-id").val(res.id);
            $("#detail-publisher-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
};

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "/api/publisher/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#update-publisher-id").val(res.id);
            $("#update-publisher-name").val(res.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

$("#btn-update-publisher").click((event) => {
    event.preventDefault();
    let valueId = $("#update-publisher-id").val();
    let valueName = $("#update-publisher-name").val();

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
            url: "/api/publisher/" + valueId,
            dataType: "JSON",
            beforeSend: addCsrf(),
            contentType: "application/json",
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                $("#update-modal").modal("hide");
                $("#table-publisher").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Publisher has been updated!",
                    showConfirmButton: false,
                    timer: 1500,
                });
                $("#update-publisher-id").val("");
                $("#update-publisher-name").val("");
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
});

function deletePublisher(id) {
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
                url: "/api/publisher/" + id,
                dataType: "JSON",
                beforeSend: addCsrf(),
                contentType: "application/json",
                success: (res) => {
                    $("#table-publisher").DataTable().ajax.reload();
                },
                error: (err) => {
                    console.log(err);
                },
                });
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your Publisher has been deleted.",
                    icon: "success",
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your imaginary Publisher is safe :)",
                    icon: "error",
                });
            }
    });
}