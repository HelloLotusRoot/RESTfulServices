function dataSend() {
    var data=$("#input").val();
    var User={
        result:data
    };
    $.ajax({
        url: "/dataSend",
        data: User,
        type:"POST",
    }).done(function (fragment) {
        $("#resultDiv").replaceWith(fragment);
    });
}