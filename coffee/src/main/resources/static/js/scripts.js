
$(function () {

 var id;

    $('.btn-outline-danger').click(function () {
        $(".modal").addClass("active");
        $('#modal_cafe_name').text($(this).attr("data-cafe_name"));
        id = $(this).attr("data-cafe_id");
    });


    $('#close_modal').click(function () {
        $('.modal').removeClass("active");
    });


    $('#yes-modal').click(function () {
        $.get("/deleteCafe/" + id)
            .done(function(data){
                location.reload();
            });
        $('.modal').removeClass("active");
    });

    $('#no-modal').click(function () {
        $('.modal').removeClass("active");
    });

});


