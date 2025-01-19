$(document).ready(function () {
    const bookId = parseInt($("#update-book-id").val(), 10);
    fetchBookDetails(bookId);

    let imgUrl = $("#update-book-img-url").val();
    if (imgUrl) {
        $("#api-book-img").show();
    } else {
        $("#api-book-img").hide();
    }
});

function fetchBookDetails(id) {
    $.ajax({
        method: "GET",
        url: "/api/book",
        // beforeSend: addCsrf(),
        dataType: "JSON",
        contentType: "application/json",
        success: function(res) {
            let book = res.find(b => b.id === id);
            if (book) {
                $("#update-book-id").val(book.id);
                $("#update-book-title").val(book.title);
                $("#update-book-year").val(book.year);
                $("#update-book-price").val(book.price);
                $("#update-book-stock").val(book.stock);
                $("#update-book-img-url").val(book.img);
                $("#api-book-img").attr("src", book.img);

                renderChildData(book.authorId, book.categoryId, book.publisherId);
            } else {
                console.log("Book not found");
            }
        },
        error: function(err) {
            console.log(err);
        }
    });
}

function renderChildData(selectedAuthorId, selectedCategoryId, selectedPublisherId) {
    $.ajax({
        method: "GET",
        url: "/api/author",
        success: function(data) {
            let updateSelectAuthor = $("#update-book-author");

            updateSelectAuthor.empty();
            updateSelectAuthor.append('<option value="" selected>Select Author</option>');

            $.each(data, function(key, author) {
                let selected = author.id === selectedAuthorId ? 'selected' : '';
                updateSelectAuthor.append('<option value="' + author.id + '" ' + selected + '>' + author.name + '</option>');
            });
        },
        error: function(err) {
            console.log(err);
        }
    });

    $.ajax({
        method: "GET",
        url: "/api/category",
        success: function(data) {
            let updateSelectCategory = $("#update-book-category");

            updateSelectCategory.empty();
            updateSelectCategory.append('<option value="" selected>Select Category</option>');

            $.each(data, function(key, category) {
                let selected = category.id === parseInt(selectedCategoryId, 10) ? 'selected' : '';
                updateSelectCategory.append('<option value="' + category.id + '" ' + selected + '>' + category.name + '</option>');
            });
        },
        error: function(err) {
            console.log(err);
        }
    });

    $.ajax({
        method: "GET",
        url: "/api/publisher",
        success: function(data) {
            let updateSelectPublisher = $("#update-book-publisher");

            updateSelectPublisher.empty();
            updateSelectPublisher.append('<option value="" selected>Select Publisher</option>');

            $.each(data, function(key, publisher) {
                let selected = publisher.id === selectedPublisherId ? 'selected' : '';
                updateSelectPublisher.append('<option value="' + publisher.id + '" ' + selected + '>' + publisher.name + '</option>');
            });
        },
        error: function(err) {
            console.log(err);
        }
    });
}

$('input[name="img-option"]').on('change', function() {
    if ($(this).val() === 'file') {
        $('#file-input-container').hide();
        $('#url-input-container').show();
    } else {
        $('#file-input-container').show();
        $('#url-input-container').hide();
    }
});

// Function to populate form fields with book data
function populateFormFields(book) {
    $("#update-book-title").val(book.title);
    $("#update-book-year").val(book.year);
    $("#update-book-category").val(book.categoryId);
    $("#update-book-author").val(book.authorId);
    $("#update-book-publisher").val(book.publisherId);
    $("#update-book-price").val(book.price);
    $("#update-book-stock").val(book.stock);
    if (book.img.startsWith("http")) {
        $("#update-book-img-url").val(book.img);
        $("#upload-file-option").prop('checked', false);
        $("#url-option").prop('checked', true);
        $("#file-input-container").hide();
        $("#url-input-container").show();
    } else {
        $("#update-book-img").val(book.img);
        $("#upload-file-option").prop('checked', true);
        $("#url-option").prop('checked', false);
        $("#file-input-container").show();
        $("#url-input-container").hide();
    }
}

// Function to handle the update button click
$("#update-book-button").click((event) => {
    event.preventDefault();

    let id = $("#update-book-id").val();
    let title = $("#update-book-title").val();
    let year = parseInt($("#update-book-year").val());
    let category = parseInt($("#update-book-category").val()); 
    let author = parseInt($("#update-book-author").val());
    let publisher = parseInt($("#update-book-publisher").val());
    let price = parseInt($("#update-book-price").val());
    let stock = parseInt($("#update-book-stock").val());
    let img = $("#update-book-img-url").val();
    let admin = 11;

    // console.log(id);
    // console.log(title);
    // console.log(year);
    // console.log(category);
    // console.log(author);
    // console.log(publisher);
    // console.log(price);
    // console.log(stock);
    // console.log(img);
    // console.log(admin);

    if (!id || !title || !year || !category || !author || !publisher || !price || !stock) {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Please fill all fields!",
            showConfirmButton: false,
            timer: 1500,
        });
        return;
    }

    $.ajax({
        method: "PUT",
        url: `/api/book/${id}`,
        beforeSend: addCsrf(),
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            title: title,
            year: year,
            category: category,
            author: author,
            publisher: publisher,
            price: price,
            stock: stock,
            img: img,
            admin: admin
        }),
        success: (res) => {
            $("#update-modal").modal("hide");
            $("#table-book").DataTable().ajax.reload();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Book has been updated!",
                showConfirmButton: false,
                timer: 1500,
            }).then(() => {
                window.location.href = "http://localhost:9099/book";
            });

            $("#update-book-id").val("");
            $("#update-book-title").val("");
            $("#update-book-year").val("");
            $("#update-book-category").val("");
            $("#update-book-author").val("");
            $("#update-book-publisher").val("");
            $("#update-book-price").val("");
            $("#update-book-stock").val("");
            $("#update-book-img-url").val("");
        },
        error: (err) => {
            console.log(err);
            Swal.fire({
                position: "center",
                icon: "error",
                title: "Failed to update book!",
                showConfirmButton: false,
                timer: 1500,
            });
        }
    });
});

