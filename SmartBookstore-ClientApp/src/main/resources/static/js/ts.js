$(document).ready(() => {
    console.log("History Script");
    $("#table-history").DataTable({
        ajax: {
            url: "/api/order/history/all",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            { data: "customer.id" },
            { data: "customer.fullname" },
            { data: "date" },
            { 
                data: null,
                render: function(data, type, row) {
                    return data.books[0].id;
                }
            },
            { data: "books[0].title" },
            { 
                data: null,
                render: function(data, type, row) {
                    return Math.round(data.total / data.books[0].price);
                }
            },
            { 
                data: null,
                render: function(data, type, row) {
                    return data.books[0].price;
                }
            },
            { data: "total" }
        ]
    });

    getEarnings();
});

function getEarnings(){
    $.ajax({
        method: "GET",
        url: "/api/order",
        dataType: "json",
        success: function(data) {
            let totalEarnings = 0;
            data.forEach(function(order) {
                totalEarnings += order.total;
            });
            const formatter = new Intl.NumberFormat('id-ID', {
                style: 'currency',
                currency: 'IDR',
                minimumFractionDigits: 0
            });
            $("#val-earning").text(formatter.format(totalEarnings));
        },
        error: function(err) {
            console.log("Error:", err);
        }
    });
}

// const salesCtx = document.getElementById('salesChart').getContext('2d');
// const salesChart = new Chart(salesCtx, {
//     type: 'line',
//     data: {
//         labels: ['January', 'February', 'March', 'April', 'May', 'June'],
//         datasets: [{
//             label: 'Sales',
//             data: [65, 59, 80, 81, 56, 55, 40],
//             borderColor: 'rgba(75, 192, 192, 1)',
//             borderWidth: 1,
//             fill: false
//         }]
//     },
//     options: {
//         responsive: true,
//         maintainAspectRatio: false,
//         aspectRatio: 2,
//         scales: {
//             y: {
//                 beginAtZero: true
//             }
//         }
//     }
// });

// const ordersCtx = document.getElementById('ordersChart').getContext('2d');
// const ordersChart = new Chart(ordersCtx, {
//     type: 'bar',
//     data: {
//         labels: ['January', 'February', 'March', 'April', 'May', 'June'],
//         datasets: [{
//             label: 'Orders',
//             data: [65, 59, 80, 81, 56, 55, 40],
//             backgroundColor: 'rgba(153, 102, 255, 0.2)',
//             borderColor: 'rgba(153, 102, 255, 1)',
//             borderWidth: 1
//         }]
//     },
//     options: {
//         responsive: true,
//         maintainAspectRatio: false,
//         aspectRatio: 2,
//         scales: {
//             y: {
//                 beginAtZero: true
//             }
//         }
//     }
// });