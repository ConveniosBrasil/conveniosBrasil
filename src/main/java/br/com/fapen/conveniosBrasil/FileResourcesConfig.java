package br.com.fapen.conveniosBrasil;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.fapen.conveniosBrasil.services.ArquivoService;

@Configuration
public class FileResourcesConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/fotosConveniosBrasil/**").addResourceLocations("file://" + ArquivoService.DIRETORIO_BASE);
	}
}
