$(document).ready(
		function() {
			function limpa_formulário_cep() {
				// Limpa valores do formulário de cep.
				$("#usuario\\.rua, #rua").val("");
				$("#usuario\\.bairro, #bairro").val("");
				$("#usuario\\.cidade, #cidade").val("");
				$("#usuario\\.estado, #estado").val("");
			}

			// Quando o campo cep perde o foco.
			$("#usuario\\.cep, #cep").blur(
					function() {
						// Nova variável "cep" somente com dígitos.
						var cep = $(this).val().replace(/\D/g, '');

						// Verifica se campo cep possui valor informado.
						if (cep != "") {

							// Expressão regular para validar o CEP.
							var validacep = /^[0-9]{8}$/;

							// Valida o formato do CEP.
							if (validacep.test(cep)) {
								// Consulta o webservice viacep.com.br/
								$.getJSON("https://viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										// Atualiza os campos com os valores da
										// consulta.
										$("#usuario\\.rua, #rua").val(dados.logradouro);
										$("#usuario\\.bairro, #bairro").val(dados.bairro).focus();
										$("#usuario\\.cidade, #cidade").val(dados.localidade).focus();
										$("#usuario\\.estado, #estado").val(dados.uf).focus();
										$("select").formSelect();
										$("#usuario\\.numero, #numero").focus();
									} // end if.
									else {
										// CEP pesquisado não foi encontrado.
										limpa_formulário_cep();
										alert("CEP não encontrado.");
									}
								});
							} // end if.
							else {
								// cep é inválido.
								limpa_formulário_cep();
								alert("Formato de CEP inválido.");
							}
						} // end if.
						else {
							// cep sem valor, limpa formulário.
							limpa_formulário_cep();
						}
					});
		});