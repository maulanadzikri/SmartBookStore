$(document).ready(() => {
    getBookCount();
    // getEarnings();
    getUserCount();
    getCategoryCount();
    getAuthorCount();
    getPublisherCount();
    getTransactionCount();
});

function getBookCount() {
    $.ajax({
        method: "GET",
        url: "/api/book",
        dataType: "json",
        success: function(data) {
            $("#val-books").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

function getUserCount() {
    $.ajax({
        method: "GET",
        url: "/api/userdetail",
        dataType: "json",
        success: function(data) {
            $("#val-userdetails").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

function getCategoryCount() {
    $.ajax({
        method: "GET",
        url: "/api/category",
        dataType: "json",
        success: function(data) {
            $("#val-categorys").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

function getAuthorCount() {
    $.ajax({
        method: "GET",
        url: "/api/author",
        dataType: "json",
        success: function(data) {
            $("#val-authors").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

function getPublisherCount() {
    $.ajax({
        method: "GET",
        url: "/api/publisher",
        dataType: "json",
        success: function(data) {
            $("#val-publishers").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

function getTransactionCount() {
    $.ajax({
        method: "GET",
        url: "/api/order/history/all",
        dataType: "json",
        success: function(data) {
            $("#val-transactions").text(data.length);
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

// function getEarnings(){
//     $.ajax({
//         method: "GET",
//         url: "/api/order",
//         dataType: "json",
//         success: function(data) {
//             let totalEarnings = 0;
//             data.forEach(function(order) {
//                 totalEarnings += order.total;
//             });
//             const formatter = new Intl.NumberFormat('id-ID', {
//                 style: 'currency',
//                 currency: 'IDR',
//                 minimumFractionDigits: 0
//             });
//             $("#val-earning").text(formatter.format(totalEarnings));
//         },
//         error: function(err) {
//             console.log("Error:", err);
//         }
//     });
// }