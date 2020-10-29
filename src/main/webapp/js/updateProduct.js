
$(document).ready(function () {
    $("button#update").click(function () {
        var id = $("form.update-product-page input.id").val();
        var name = $("form.update-product-page input.name").val();
        var price = $("form.update-product-page input.price").val();
        var description = $("form.update-product-page input.description").val();

        console.log("My JS IN UpdateSERVLET");

        if (name == '' || price ==''|| description == ''){
            alert("Please fill all fields...!!!!!!!!!!!");
        }
        else if (price <=0) {
            alert("Price should be greater than 0...!!!!!!!!!!")
        }
        else {
            var product = {
                id: id,
                name: name,
                price: price,
                description: description
            };
            $.post("productUpdate", product, function (data) {
                if (data.id != null){
                    window.location.href = "cabinet.jsp";
                }
                else {
                    alert(data.message)
                }
                $("form")[0].reset();

            }, "json");
        }
    });
});
