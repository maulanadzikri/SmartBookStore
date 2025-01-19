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
                // Filter and sort books by stock (ascending order)
                let filteredBooks = data.filter(book => 
                    book.title.toLowerCase().includes(query.toLowerCase()) ||
                    book.categoryName.toLowerCase().includes(query.toLowerCase()) ||
                    book.authorName.toLowerCase().includes(query.toLowerCase())
                ).sort((a, b) => a.stock - b.stock); // Sorting by stock

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