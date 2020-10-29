$(document).ready(function () {
    $("button#exists").click(function () {
        var id = $("form.exist-product-page input.id").val();

        console.log("My JS " + id);


            $.get("productExists?id=" + id, function () {
                // if (data.id != null){
                    window.location.href = "cabinet.jsp";
                // }
                // else {
                //     alert(data.message)
                // }
                $("form")[0].reset();

            }, "json");
        // }
    });
});





// $(document).ready(function () {
//     $("button#exists").click(function () {
//         var id = $("form.exist-product-page input.id").val();
//
//         console.log("My JS Exist Id Product ");
//
//         if (id == ''){
//             alert("Please fill all fields...!!!!!!!!!!!");
//         }
//
//         else {
//             var product = {
//                 id: id,
//             };
//             $.post("productExists", product, function (data) {
//                 if (data.id != null) {
//                     window.location.href = "cabinet.jsp";
//                 } else {
//                     alert(data.message)
//                 }
//                 $("form")[0].reset();
//
//             }, "json").done(function () {
//                 let cardsContent = "";
//
//                 jQuery.each(JSON.parse(product), function (i, product) {
//                     cardsContent += "<div class='card'>" +
//                         "<div class='card-body'>" +
//                         "<h5 class='card-title'>Product name : " + product.name + "</h5>" +
//                         "<h6 class='card-subtitle mb-2 text-muted'> Price : " + product.price + "</h6>" +
//                         "<p class='card-text'>Description : " + product.description + "</p>" +
//                         "<p class='card-text'>Id product : " + product.id + "</p>" +
//                         "<a href='product?id=" + product.id + "'class='card-link'>About product</a>" +
//                         "</div>" +
//                         "</div>"
//                 });
//
//                 $("div#all-products").html(cardsContent);
//             });
//         }
//     });
// });







// let products = null;
//
// $.get("products", function (data) {
//     if (data != '') {
//         products = data;
//     }
// },"json").done(function () {
//     let cardsContent = "";
//
//     jQuery.each(JSON.parse(products),function (i, product) {
//         cardsContent += "<div class='card'>" +
//             "<div class='card-body'>" +
//             "<h5 class='card-title'>Product name : " + product.name + "</h5>" +
//             "<h6 class='card-subtitle mb-2 text-muted'> Price : " + product.price + "</h6>" +
//             "<p class='card-text'>Description : " + product.description + "</p>" +
//             "<p class='card-text'>Id product : " + product.id + "</p>" +
//             "<a href='product?id=" + product.id + "'class='card-link'>About product</a>" +
//             "</div>" +
//             "</div>"
//     });
//
//     $("div#all-products").html(cardsContent);
// });