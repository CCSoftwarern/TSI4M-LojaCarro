package br.org.edu.ifrn.LojaCarro.controllers;

import br.org.edu.ifrn.LojaCarro.dto.LoginDTO;
import br.org.edu.ifrn.LojaCarro.model.Usuario;
import br.org.edu.ifrn.LojaCarro.repository.UsuarioRepository;
import br.org.edu.ifrn.LojaCarro.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/teste")
    public String teste() {
        return "OK";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginDTO dto) {

        Usuario usuario =
                repository.findByLogin(
                                dto.getLogin())
                        .orElseThrow();

        if(!usuario.getSenha()
                .equals(dto.getSenha())) {

            throw new RuntimeException(
                    "Senha inválida");
        }

        return jwtService
                .gerarToken(usuario);
    }
}