package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("cadastrar")
    public ResponseEntity<?> criarUsuario(@RequestBody CriarUsuarioRequest request) {

        return ResponseEntity.ok().body(usuarioService.criar(request));
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request) {

        return ResponseEntity.ok().body(usuarioService.autenticar(null));
    }

    @PostMapping("recuperar")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaRequest request) {

        return ResponseEntity.ok().body(usuarioService.recuperar(null));
    }
}
