package br.com.fapen.conveniosBrasil.services;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoService {
	@Autowired
	private ResourceLoader resourceLoader;

	public static final String DIRETORIO_BASE = "/home/tiago/eclipse-workspace/fotosConveniosBrasil/";
	public static final String DIRETORIO_TEMPLATE_EMAIL = "/WEB-INF/templateEmail/";
	
	public String salvarEmDisco(MultipartFile arquivo) {
		String realPath = DIRETORIO_BASE;
		
		File diretorio = new File(realPath);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		
		diretorio = null;
		String destino = DIRETORIO_BASE + arquivo.getOriginalFilename();
		File arquivoDestino = new File(destino);
		try {
			arquivo.transferTo(arquivoDestino);
			return "/fotosConveniosBrasil/" + arquivo.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String carregarTemplateEmail(String nomeTemplate) throws IOException {
		File arquivo = resourceLoader.getResource(DIRETORIO_TEMPLATE_EMAIL + nomeTemplate).getFile();
		Scanner leitor = new Scanner(arquivo);
		StringBuilder sb = new StringBuilder();
		
		while(leitor.hasNextLine()) {
			String linha = leitor.nextLine();
			sb.append(linha);
		}
		
		leitor.close();
		return sb.toString();
	}
}
