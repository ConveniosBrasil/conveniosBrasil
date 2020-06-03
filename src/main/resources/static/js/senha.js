$(document).ready(function() {
	$('#redigitesenha').on('keyup', function() {
		if ($('#senha').val() == $('#redigitesenha').val()) {
			$('.fasenha').removeClass('fa-times-circle')
					.addClass('fa-check-circle').css('color', 'green');
		} else {
			$('.fasenha').removeClass('fa-check-circle')
					.addClass('fa-times-circle').css('color', 'red');
		}
	});
	
	$('#senha').on('keyup', function() {
		var password = $(this).val();
        var passwordPontos = 0;

        // Contém numeros
        if (/[0-9]/.test(password))
            passwordPontos += 20;

        // Contém letras minúsculas
        if (/[a-z]/.test(password))
            passwordPontos += 20;

        // Contém letras maiúsculas
        if (/[A-Z]/.test(password))
            passwordPontos += 20;
        
        // Contém letras maiúsculas
        if (/\W|_/.test(password))
            passwordPontos += 20;

        if (password.length >= 8)
            passwordPontos += 20;
        
        if (passwordPontos == 20){
        	$('.nivel1, .nivel2, .nivel3, .nivel4, nivel5').removeClass('nivelamarelo').removeClass('nivelverde');
        	$('.nivel1').addClass('nivelvermelho');
        } else if (passwordPontos == 40 || passwordPontos == 60){
        	$('.nivel1, .nivel2, .nivel3, .nivel4, nivel5').removeClass('nivelvermelho').removeClass('nivelverde');
        	$('.nivel1, .nivel2, .nivel3').addClass('nivelamarelo');
		} else if (passwordPontos > 60)
        	$('.nivel1, .nivel2, .nivel3, .nivel4, .nivel5').removeClass('nivelvermelho').removeClass('nivelamarelo').addClass('nivelverde');			     
	});

});
