package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.exceptions.EmailJaCadastradoException;
import br.com.phamtecnologia.exceptions.EmailNaoEncontrado;
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

        try {
            return ResponseEntity.ok().body(usuarioService.criar(request));
        }
        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request) {

        try {
            return ResponseEntity.ok().body(usuarioService.autenticar(request));
        }
        catch (AcessoNegadoException e) {
            return  ResponseEntity.status(401).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("recuperar")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaRequest request) {

        try {
            return ResponseEntity.ok().body(usuarioService.recuperar(request));
        }
        catch (EmailNaoEncontrado e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
