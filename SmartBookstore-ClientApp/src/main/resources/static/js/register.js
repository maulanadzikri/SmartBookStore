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