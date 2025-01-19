$(document).ready(() => {
    console.log("History Script");
    $("#table-history").DataTable({
        ajax: {
            url: "/api/order/history/13",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            // { data: "id" },
            { data: "date" },
            { 
                data: null,
                render: function(data, type, row) {
                    return data.books[0].title;
                }
            },
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
});
