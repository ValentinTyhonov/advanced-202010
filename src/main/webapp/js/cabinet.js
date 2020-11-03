let products = null;

$.get("products", function (data) {
    if (data != '') {
        products = data;
    }
},"json").done(function () {
    let cardsContent = "";

    jQuery.each(JSON.parse(products),function (i, product) {
        cardsContent += "<div class='card'>" +
            "<div class='card-body'>" +
            "<h5 class='card-title'>Product name : " + product.name + "</h5>" +
            "<h6 class='card-subtitle mb-2 text-muted'> Price : " + product.price + "</h6>" +
            "<p class='card-text'>Description : " + product.description + "</p>" +
            "<p class='card-text'>Id product : " + product.id + "</p>" +
            "<a href='product?about=true&id=" + product.id + "'class='card-link'>About product</a>" +
            "<a href='product?update=true&id=" + product.id + "'class='card-link'>Update Product</a>" +
            "<a href='product?delete=true&id=" + product.id + "'class='card-link'>Delete Product</a>" +
            "</div>" +
            "</div>"
    });

    $("div#all-products").html(cardsContent);
});