$(document).ready(function(){
    $('#voltarTopo').hide();

    $(window).scroll(function(){
        if($(this).scrollTop() > 0){
            $('#voltarTopo').fadeIn();
        } else {
            $('#voltarTopo').fadeOut();
        }
    });

    $('#voltarTopo').click(function(){
        $('html, body').animate({
            scrollTop: 0
        }, 500);
    });
	
	$('#voltarTopo').hover(function(){
		$(this).attr("src", "/img/imgTopohover.png");
	}, function(){
		$(this).attr("src", "/img/imgTopo.png");
	});
});