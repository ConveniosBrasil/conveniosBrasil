package br.com.fapen.conveniosBrasil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class ConveniosBrasilSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService usuarioService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers("/plano").permitAll()
			.antMatchers("/sobre").permitAll()
			.antMatchers("/politicaprivacidade").permitAll()
			.antMatchers("/esquecisenha/**").permitAll()
			.antMatchers("/novasenha/**").permitAll()
			.antMatchers("/email").permitAll()
			.antMatchers("/contato/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/usuario/perfil").authenticated()
			.antMatchers("/perfil/**").hasRole("ADMIN")
			.antMatchers("/usuario/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/usuario/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/pedido").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/home");
	}
}
