$(document).ready(() => {
    $("#table-book").DataTable({
        ajax: {
            url: "/api/book",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            { data: "id" },
            { data: "title" },
            {
                data: null,
                render: (data) => {
                    return /*html*/ `
                    <div class="d-flex gap-4 justify-content-center align-items-center">
                        <button type="button" class="btn btn-primary btn-sm d-flex align-items-center"
                                data-bs-toggle="modal" data-bs-target="#detail-modal"
                                title="Detail ${data.title}" onclick="findById(${data.id})">
                                Detail
                        </button>
                        <a href="/book/update/${data.id}" class="btn btn-warning btn-sm d-flex align-items-center text-white">Update</a>
                        <button type="button" class="btn btn-danger btn-sm d-flex align-items-center"
                                title="Delete ${data.title}" onclick="confirmDelete(${data.id})">
                                Delete
                        </button>
                    </div>
                    `;
                },
            }
        ]
    });
    renderChildData();
});

$('input[name="img-option"]').on('change', function() {
    if ($(this).val() === 'file') {
        $('#file-input-container').show();
        $('#url-input-container').hide();
    } else {
        $('#file-input-container').hide();
        $('#url-input-container').show();
    }
});

$("#create-new-book").click((event) => {
    event.preventDefault();

    let title = $("#create-book-title").val();
    let year = $("#create-book-year").val();
    let category = $("#create-book-category").val();
    let author = $("#create-book-author").val();
    let publisher = $("#create-book-publisher").val();
    let price = $("#create-book-price").val();
    let stock = $("#create-book-stock").val();
    let img;
    let admin = "11";

    if ($("input[name='img-option']:checked").val() === 'file') {
        let fileInput = $("#create-book-img")[0];
        img = fileInput.files.length > 0 ? fileInput.files[0].name : null;
    } else {
        img = $("#create-book-img-url").val();
    }

    if (!title || !year || !category || !author || !publisher || !price || !stock || !img) {
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
        method: "POST",
        url: "/api/book",
        beforeSend: addCsrf(),
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify({
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
            $("#create-modal").modal("hide");
            $("#table-book").DataTable().ajax.reload();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "New Book has been created!",
                showConfirmButton: false,
                timer: 1500,
            }).then(() => {
                window.location.href = "http://localhost:9099/book";
            });

            $("#create-book-title").val("");
            $("#create-book-year").val("");
            $("#create-book-category").val("");
            $("#create-book-author").val("");
            $("#create-book-publisher").val("");
            $("#create-book-price").val("");
            $("#create-book-stock").val("");
            $("input[name='create-book-img']").val("");
            $("#create-book-img-url").val("");
        },
        error: (err) => {
            console.log(err);
        }
    });
});


let findById = (id) => {
    $.ajax({
        method: "GET",
        url: "/api/book",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            let book = res.find(b => b.id === id);
            if (book) {
                $("#detail-book-id").val(book.id);
                $("#detail-book-title").val(book.title);
                $("#detail-book-year").val(book.year);
                $("#detail-book-category").val(book.categoryName);
                $("#detail-book-author").val(book.authorName);
                $("#detail-book-publisher").val(book.publisherName);
                $("#detail-book-price").val(book.price);
                $("#detail-book-stock").val(book.stock);
                $("#detail-book-img").attr("src", book.img);
            } else {
                console.log("Book not found");
            }
        },
        error: (err) => {
            console.log(err);
        },
    });
};

function confirmDelete(id) {
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
            deleteBook(id);
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire({
                title: "Cancelled",
                text: "Your book is safe :)",
                icon: "error",
            });
        }
    });
}

function deleteBook(id) {
    $.ajax({
        method: "DELETE",
        url: "/api/book/" + id,
        beforeSend: addCsrf(),
        dataType: "JSON",
        success: (res) => {
            $("#table-book").DataTable().ajax.reload();
            Swal.fire({
                title: "Deleted!",
                text: "Your book has been deleted.",
                icon: "success",
            });
        },
        error: (err) => {
            console.log(err);
            Swal.fire({
                title: "Error!",
                text: "An error occurred while deleting the book.",
                icon: "error",
            });
        },
    });
}

function renderChildData(){
    $.ajax({
        method: "GET",
        url: "/api/author",
        success: function(data) {
            let createSelectAuthor = $("#create-book-author");
            let createApiSelectAuthor = $("#create-api-book-author");

            createSelectAuthor.empty();
            createApiSelectAuthor.empty();

            createSelectAuthor.append('<option value="" selected>Select Author</option>');
            createApiSelectAuthor.append('<option value="" selected>Select Author</option>');

            $.each(data, function(key, author){
                createSelectAuthor.append('<option value="' + author.id + '">' + author.name + '</option>');
                createApiSelectAuthor.append('<option value="' + author.id + '">' + author.name + '</option>');
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
            let createSelectCategory = $("#create-book-category");
            let createApiSelectCategory = $("#create-api-book-category");

            createSelectCategory.empty();
            createApiSelectCategory.empty();

            createSelectCategory.append('<option value="" selected>Select Category</option>');
            createApiSelectCategory.append('<option value="" selected>Select Category</option>');

            $.each(data, function(key, category){
                createSelectCategory.append('<option value="' + category.id + '">' + category.name + '</option>');
                createApiSelectCategory.append('<option value="' + category.id + '">' + category.name + '</option>');
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
            let createSelectPublisher = $("#create-book-publisher");
            let createApiSelectPublisher = $("#create-api-book-publisher");
    
            createSelectPublisher.empty();
            createApiSelectPublisher.empty();
    
            createSelectPublisher.append('<option value="" selected>Select Publisher</option>');
            createApiSelectPublisher.append('<option value="" selected>Select Publisher</option>');
    
            $.each(data, function(key, publisher){
                createSelectPublisher.append('<option value="' + publisher.id + '">' + publisher.name + '</option>');
                createApiSelectPublisher.append('<option value="' + publisher.id + '">' + publisher.name + '</option>');
            });
        },
        error: function(err) {
            console.log(err);
        }
    });
}

document.getElementById("defaultOpen").click();

function switchTab(evt, createMethod) {
    var i, tabcontent, tablinks;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
  
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
  
    document.getElementById(createMethod).style.display = "block";
    evt.currentTarget.className += " active";
}

async function searchBooks() {
    const query = document.getElementById('query').value;
    const apiKey = 'AIzaSyARtx_LM60Y38OXGdyZV_hZgWTQAmRf60c';
    const url = `https://www.googleapis.com/books/v1/volumes?q=${query}&key=${apiKey}`;

    try {
        const response = await fetch(url);
        const data = await response.json();
        displayResults(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

function displayResults(data) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '';

    if (data.items) {
        const ul = document.createElement('ul');
        
        data.items.forEach(item => {
            const li = document.createElement('li');
            const title = item.volumeInfo.title || 'N/A';
            const imageLink = item.volumeInfo.imageLinks?.thumbnail || 'https://via.placeholder.com/150';
            li.innerHTML = `
                <img src="${imageLink}" alt="${title} cover" style="max-width: 100px; height: auto;">
                <span class="title-result">${title}</span>
            `;
            li.onclick = () => displayBookDetails(item);
            ul.appendChild(li);
        });

        resultsDiv.appendChild(ul);
    } else {
        resultsDiv.innerHTML = '<p>No books found.</p>';
    }
}

function displayBookDetails(book) {
    const titleInput = document.getElementById('create-api-book-title');
    const imgCover = document.getElementById('api-book-img');
    const imgUrlInput = document.getElementById('create-api-book-img-url');
    const yearInput = document.getElementById('create-api-book-year');
    const authorInput = document.getElementById('create-api-book-author');
    const categoryInput = document.getElementById('create-api-book-category');
    const publisherInput = document.getElementById('create-api-book-publisher');
    const priceInput = document.getElementById('create-api-book-price');
    const stockInput = document.getElementById('create-api-book-stock');
    
    const authors = book.volumeInfo.authors || [];
    const categories = book.volumeInfo.categories || [];
    const publisher = book.volumeInfo.publisher || 'Unknown Publisher';
    imgUrlInput.value = book.volumeInfo.imageLinks?.thumbnail || '';

    titleInput.value = book.volumeInfo.title || '';
    yearInput.value = book.volumeInfo.publishedDate?.substring(0, 4) || '';
    authorInput.value = authors.join(', ');
    categoryInput.value = categories.join(', ');
    publisherInput.value = publisher;
    priceInput.value = '';
    stockInput.value = '';

    imgCover.src = book.volumeInfo.imageLinks.thumbnail;
    imgCover.style.display = "block";
    $("#url-input-container").show();

    if (book.volumeInfo.imageLinks && book.volumeInfo.imageLinks.thumbnail) {
        $("#url-input-container").show();
    } else {
        $("#url-input-container").hide();
    }

    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '';
}

$("#create-api-new-book").click((event) => {
    event.preventDefault();

    let title = $("#create-api-book-title").val();
    let year = $("#create-api-book-year").val();
    let category = $("#create-api-book-category").val();
    let author = $("#create-api-book-author").val();
    let publisher = $("#create-api-book-publisher").val();
    let price = $("#create-api-book-price").val();
    let stock = $("#create-api-book-stock").val();
    let img = $("#create-api-book-img-url").val();
    let admin = "11";

    if (!title || !year || !category || !author || !publisher || !price || !stock || !img) {
        Swal.fire({
          position: "center",
          icon: "error",
          title: "Please fill all fields!",
          showConfirmButton: false,
          timer: 1500,
        });
        return;
    }

    Promise.all([
        checkAndCreateCategory(category),
        checkAndCreateAuthor(author),
        checkAndCreatePublisher(publisher)
    ]).then(([categoryId, authorId, publisherId]) => {
        $.ajax({
            method: "POST",
            url: "/api/book",
            beforeSend: addCsrf(),
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                title: title,
                year: year,
                category: categoryId,
                author: authorId,
                publisher: publisherId,
                price: price,
                stock: stock,
                img: img,
                admin: admin
            }),
            success: (res) => {
                $("#create-modal").modal("hide");
                $("#table-book").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "New Book has been created!",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(() => {
                    window.location.href = "http://localhost:9099/book";
                });

                $("#create-api-book-title").val("");
                $("#create-api-book-year").val("");
                $("#create-api-book-category").val("");
                $("#create-api-book-author").val("");
                $("#create-api-book-publisher").val("");
                $("#create-api-book-price").val("");
                $("#create-api-book-stock").val("");
                $("input[name='create-api-book-img']").val("");
                $("#create-api-book-img-url").val("");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }).catch((err) => {
        console.error(err);
    });
});

function checkAndCreateAuthor(authorName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "GET",
            url: "/api/author",
            success: function(data) {
                let authorId;

                let existingAuthor = data.find(author => author.name === authorName);
                if (existingAuthor) {
                    authorId = existingAuthor.id;
                    resolve(authorId);
                } else {
                    createAuthor(authorName).then((newAuthorId) => {
                        resolve(newAuthorId);
                    }).catch((err) => {
                        reject(err);
                    });
                }
            },
            error: function(err) {
                reject(err);
            }
        });
    });
}

function createAuthor(authorName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "POST",
            url: "/api/author",
            beforeSend: addCsrf(),
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                name: authorName,
            }),
            success: (res) => {
                console.log('New author created:', res);
                resolve(res.id);
            },
            error: (err) => {
                reject(err);
            }
        });
    });
}

function checkAndCreatePublisher(publisherName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "GET",
            url: "/api/publisher",
            success: function(data) {
                let publisherId;

                let existingPublisher = data.find(publisher => publisher.name === publisherName);
                if (existingPublisher) {
                    publisherId = existingPublisher.id;
                    resolve(publisherId);
                } else {
                    createPublisher(publisherName).then((newPublisherId) => {
                        resolve(newPublisherId);
                    }).catch((err) => {
                        reject(err);
                    });
                }
            },
            error: function(err) {
                reject(err);
            }
        });
    });
}

function createPublisher(publisherName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "POST",
            url: "/api/publisher",
            beforeSend: addCsrf(),
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                name: publisherName,
            }),
            success: (res) => {
                console.log('New publisher created:', res);
                resolve(res.id);
            },
            error: (err) => {
                reject(err);
            }
        });
    });
}

function checkAndCreateCategory(categoryName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "GET",
            url: "/api/category",
            success: function(data) {
                let categoryId;

                let existingCategory = data.find(category => category.name === categoryName);
                if (existingCategory) {
                    categoryId = existingCategory.id;
                    resolve(categoryId);
                } else {
                    createCategory(categoryName).then((newCategoryId) => {
                        resolve(newCategoryId);
                    }).catch((err) => {
                        reject(err);
                    });
                }
            },
            error: function(err) {
                reject(err);
            }
        });
    });
}

function createCategory(categoryName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            method: "POST",
            url: "/api/category",
            beforeSend: addCsrf(),
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                name: categoryName,
            }),
            success: (res) => {
                console.log('New category created:', res);
                resolve(res.id);
            },
            error: (err) => {
                reject(err);
            }
        });
    });
}

let inputBook = document.getElementById("query");
inputBook.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
      event.preventDefault();
      searchBooks();
    }
  });