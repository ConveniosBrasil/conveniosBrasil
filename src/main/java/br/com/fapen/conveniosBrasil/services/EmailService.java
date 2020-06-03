package br.com.fapen.conveniosBrasil.services;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.fapen.conveniosBrasil.models.Usuario;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender senderEmail;
	
	@Autowired
	private ArquivoService servicoArquivo;
	
	public void EnviarEmailHTML(String email, String assunto, String conteudo) {
		try {
			MimeMessage objMensagemHtml = senderEmail.createMimeMessage();
			MimeMessageHelper ajudante = new MimeMessageHelper(objMensagemHtml, true);
			File logo = new File("src/main/webapp/WEB-INF/templateEmail/conveniosbrasil.png");
			
			ajudante.setTo(email);
			ajudante.setSubject(assunto);
			ajudante.setFrom("cb.conveniosbrasil@gmail.com");
			ajudante.setText(conteudo, true);
			ajudante.addInline("logoconveniosbrasil", logo);
			
			senderEmail.send(objMensagemHtml);
			System.out.println("E-mail enviado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao enviar e-mail: " + e.getMessage());
		}
	}
	
	public void EnviarEmailRecSenhaHTML(HttpServletRequest request, Usuario usuario) {
		try {
			String assunto = "Recuperação de Senha - Convênios Brasil";
			String email = usuario.getUsername();
			String linkRecuperacao = request.getRequestURL().toString() + "cadastro?token=" + usuario.getToken();
			String conteudo = servicoArquivo.carregarTemplateEmail("recuperarSenhaEmail.html");
			
			conteudo = conteudo.replace("${nome}", usuario.getNome());
			conteudo = conteudo.replace("${link}", linkRecuperacao);
			
			this.EnviarEmailHTML(email, assunto, conteudo);
		} catch (Exception e) {
			System.out.println("Erro ao enviar e-mail: " + e.getMessage());
		}
	}
	
	public void EnviarEmailContatoHTML(String nome, String email, String telefone, String mensagem) {
		try {
			MimeMessage objMensagemHtml = senderEmail.createMimeMessage();
			MimeMessageHelper ajudante = new MimeMessageHelper(objMensagemHtml, true);
			
			ajudante.setTo("cb.conveniosbrasil@gmail.com");
			ajudante.setSubject("Nova mensagem para Convênios Brasil");
			ajudante.setText("<h1>Informação do cliente " + nome + "</h1>" +
							 "<h3>E-mail: " + email + "<br>" +
							 "Telefone: " + telefone + "</h3><hr>" +
							 "<h1>Mensagem:</h1><h3>" + mensagem + "</h3>", true);
			
			senderEmail.send(objMensagemHtml);
			System.out.println("E-mail enviado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao enviar e-mail: " + e.getMessage());
		}
	}
}
