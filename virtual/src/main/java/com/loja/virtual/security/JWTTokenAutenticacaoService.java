package com.loja.virtual.security;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.loja.virtual.ApplicationContextLoad;
import com.loja.virtual.model.Usuario;
import com.loja.virtual.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@Service
public class JWTTokenAutenticacaoService {

	private static final long EXPIRATION_TIME = 959990000;

	private static final String SECRET = "ksfbdafisdivbsipvrnvid6599dvfe@#T$T";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void AddAuthentication(HttpServletResponse reponse, String username) throws Exception {
                  
		String JWT = Jwts.builder().
					setSubject(username) /* Adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

	
		String token = TOKEN_PREFIX + " " + JWT;

		
		reponse.addHeader(HEADER_STRING, token);

		liberacaoCors(reponse);

		reponse.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	public Authentication getAthentication(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody().getSubject();

			if (user != null) {

				Usuario usuario = ApplicationContextLoad.getApplicationContext()

						.getBean(UsuarioRepository.class).findUserByLogin(user);

				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(),
							usuario.getAuthorities());
				}

			}

		}

		liberacaoCors(response);
		return null;
	}

	private void liberacaoCors(HttpServletResponse response) {

		if (response.getHeader("Acess-Control-Allow-Origin") == null) {
			response.addHeader("Acess-Control-Allow-Origin", "*");
		}

		if (response.getHeader("Acess-Control-Allow-Header") == null) {
			response.addHeader("Acess-Control-Allow-Header", "*");
		}

		if (response.getHeader("Acess-Control-Allow-Request") == null) {
			response.addHeader("Acess-Control-Allow-Request", "*");
		}

		if (response.getHeader("Acess-Control-Allow-Methods") == null) {
			response.addHeader("Acess-Control-Allow-Methods", "*");
		}

	}
}
