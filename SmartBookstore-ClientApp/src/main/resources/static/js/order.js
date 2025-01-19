$(document).ready(function () {
    const bookId = document.getElementById("book-id").innerText;
    fetchBookDetails(parseInt(bookId));
    calculateTotal();

    // quantity
    let input = document.querySelector('#qty');
    let btnminus = document.querySelector('.qtyminus');
    let btnplus = document.querySelector('.qtyplus');

    if (input !== undefined && btnminus !== undefined && btnplus !== undefined && input !== null && btnminus !== null && btnplus !== null) {
        let min = Number(input.getAttribute('min'));
        let max = Number(input.getAttribute('max'));
        let step = Number(input.getAttribute('step'));

        function qtyminus(e) {
            let current = Number(input.value);
            let newval = (current - step);
            if (newval < min) {
                newval = min;
            }
            input.value = Number(newval);
            calculateTotal();
            e.preventDefault();
        }

        function qtyplus(e) {
            let current = Number(input.value);
            let newval = (current + step);
            if (newval > max) newval = max;
            input.value = Number(newval);
            calculateTotal();
            e.preventDefault();
        }

        btnminus.addEventListener('click', qtyminus);
        btnplus.addEventListener('click', qtyplus);
        input.addEventListener('input', calculateTotal);
    }
});

function fetchBookDetails(id) {
    
    $.ajax({
        method: "GET",
        url: "/api/book",
        dataType: "JSON",
        contentType: "application/json",
        success: function(res) {
            let book = res.find(b => b.id === id);
            if (book) {
                $("#book-id").val(book.id);
                // $("#book-title").html(book.title);
                $("#book-author").html(book.authorName);
                $("#book-category").html(book.categoryName);
                $("#book-publisher").html(book.publisherName);
                $("#book-year").html(book.year);
                $("#api-book-img").attr("src", book.img);

                // renderChildData(book.authorId, book.categoryId, book.publisherId);
            } else {
                console.log("Book not found");
            }
        },
        error: function(err) {
            console.log(err);
        }
    });
}

function calculateTotal() {
    let bookPriceElement = document.getElementById('book-price');
    let qtyInput = document.querySelector('#qty');
    let orderTotalElement = document.getElementById('order-total');

    let bookPrice = parseFloat(bookPriceElement.textContent.trim().replace('Rp', '').replace('.', ''));
    let qty = parseInt(qtyInput.value);
    let total = bookPrice * qty;

    orderTotalElement.textContent = 'Rp' + total.toLocaleString('id-ID');
}

$("#order-buy").click((event) => {
    event.preventDefault();
    let customerId = document.getElementById("userId").value;
    const bookId = document.getElementById("book-id").innerText;
    let bookQty = document.getElementById("qty").value;
    let books = [{bookId, bookQty}];
    let cardType = document.getElementById("card-type").value;
    let cardHolderName = document.getElementById("card-holder-name").value;
    let cardNumber = document.getElementById("card-number").value;
    let mm = document.getElementById("exp-mm").value;
    let yy = document.getElementById("exp-yy").value;
    let cvv = document.getElementById("cvv").value;

    if (customerId == "" || null) {
        customerId = 13;
    }
    
    if (!bookId || !bookQty || !cardType || !cardHolderName || !cardNumber || !mm || !yy || !cvv) {
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
        url: `/api/order`,
        beforeSend: addCsrf(),
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            customerId: customerId,
            books: books,
            creditCard: cardType,
            cardNumber: cardNumber,
            mm: mm,
            yy: yy,
            cvv: cvv,
            cardHolderName: cardHolderName
        }),
        success: (res) => {
            $("#update-modal").modal("hide");
            $("#table-book").DataTable().ajax.reload();
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                  confirmButton: "btn btn-info",
                  denyButton: "btn btn-primary",
                  cancelButton: "btn btn-success"
                },
                buttonsStyling: false
            });
            swalWithBootstrapButtons.fire({
                position: "center",
                icon: "success",
                title: "Your work has been saved",
                showConfirmButton: true,
                confirmButtonText: 'History',
                showDenyButton: true,
                denyButtonText: 'Back to Homepage',
                showCancelButton: true,
                cancelButtonText: 'Close'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "http://localhost:9099/history";
                } else if (result.isDenied) {
                    window.location.href = "http://localhost:9099/home";
                }
            });
        },
        error: (err) => {
            console.log(err);
            Swal.fire({
                position: "center",
                icon: "error",
                title: "Payment Fail!",
                showConfirmButton: false,
                timer: 1500,
            });
        }
    });
});