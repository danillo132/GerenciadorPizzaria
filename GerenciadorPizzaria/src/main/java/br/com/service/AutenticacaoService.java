package br.com.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.model.Funcionario;
import br.com.repository.FuncionarioRepo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class AutenticacaoService {

	
	private static final Long TEMPO_EXPIRACAO = (long) 43200000;
	
	private static final String SENHA_COMPOSTA = "#FuNc10N4R!0#";
	
	private static final String PREFIXO_TOKEN = "Bearer";
	
	private static final String CABECALHO_TOKEN = "Authorization";
	
	
	public void adicionaAutorizacao(HttpServletResponse response, String loginFuncionario) throws IOException {
		
		String JWT = Jwts.builder().setSubject(loginFuncionario)
				.setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
				.signWith(SignatureAlgorithm.HS512, SENHA_COMPOSTA).compact();
		
		String token = PREFIXO_TOKEN + " " + JWT;
		
		response.addHeader(CABECALHO_TOKEN, token);
		
		ApplicationContextService.getApplicationContext().getBean(FuncionarioRepo.class)
		.atualizaTokenUser(JWT, loginFuncionario);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response ) {
		
		
		String token = request.getHeader(CABECALHO_TOKEN);
		
		try {
			if(token != null) {
				
				String tokenLimpo = token.replace(PREFIXO_TOKEN, "").trim();
				
				String funcUser = Jwts.parser().setSigningKey(SENHA_COMPOSTA).parseClaimsJws(tokenLimpo)
						.getBody().getSubject();
				
				if(funcUser != null) {
					
					Funcionario funcionario = ApplicationContextService.getApplicationContext().getBean(FuncionarioRepo.class)
							.findUserByLogin(funcUser);
					
					if(tokenLimpo.equalsIgnoreCase(funcionario.getToken())) {
						return new UsernamePasswordAuthenticationToken(funcionario.getLogin(), funcionario.getSenha(),
								funcionario.getAuthorities());
					}
				}
			}
		} catch (ExpiredJwtException e) {
			try {
				response.getOutputStream().println("Sua sessão expirou, faça o login novamente!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
}
