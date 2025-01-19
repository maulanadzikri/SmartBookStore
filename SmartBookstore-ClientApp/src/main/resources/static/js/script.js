$(document).ready(() => {

    $('#search-form').submit(function(event) {
        event.preventDefault();
        let query = $('#search-input').val();
        searchBooks(query);
    });

    function searchBooks(query) {
        $.ajax({
            method: "GET",
            url: "/api/book",
            dataType: "JSON",
            success: function(data) {
                let filteredBooks = data.filter(book => 
                    book.title.toLowerCase().includes(query.toLowerCase()) ||
                    book.categoryName.toLowerCase().includes(query.toLowerCase()) ||
                    book.authorName.toLowerCase().includes(query.toLowerCase())
                );

                $(".container-card").empty();

                filteredBooks.forEach(function(book) {
                    let cardTemplate = `
                        <div class="card shadow">
                            <div class="book-cover">
                                <img src="${book.img}" class="card-img-top card-img-cover" alt="Book Cover">
                            </div>
                            <div class="card-body">
                                <div class="book-title">
                                    <h5 class="card-title">${book.title}</h5>
                                </div>
                                <div class="card-info">
                                    <span class="card-price">Rp. ${book.price}</span><br>
                                </div>
                                <div class="card-button">
                                    <a href="/book/order/${book.id}" class="btn btn-primary">Order</a>
                                </div>
                            </div>
                        </div>
                    `;
                    $(".container-card").append(cardTemplate);
                });
            },
            error: function(err) {
                console.log("Error:", err);
            }
        });
    }

    searchBooks('');
});

let findById = (id) => {
    console.log(id);
    $.ajax({
        method: "GET",
        url: "/api/book",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            let book = res.find(b => b.id === id);
            if (book) {
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

function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle('open');
    document.body.classList.toggle('sidebar-open');
}