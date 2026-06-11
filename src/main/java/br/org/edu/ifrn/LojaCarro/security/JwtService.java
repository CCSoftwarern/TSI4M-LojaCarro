package br.org.edu.ifrn.LojaCarro.security;

import br.org.edu.ifrn.LojaCarro.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // Chave com pelo menos 32 caracteres
    private static final String SECRET =
            "minha-chave-jwt-super-secreta-2026-ifrn-lojacarro";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // Gera o token
    public String gerarToken(Usuario usuario) {

        return Jwts.builder()
                .subject(usuario.getLogin())
                .claim("role", usuario.getRole().name())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 // 1 hora
                        )
                )
                .signWith(key)
                .compact();
    }

    // Extrai o login do token
    public String extrairLogin(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Extrai a role do token
    public String extrairRole(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }

    // Verifica se o token é válido
    public boolean tokenValido(String token) {

        try {

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (JwtException e) {

            return false;
        }
    }
}