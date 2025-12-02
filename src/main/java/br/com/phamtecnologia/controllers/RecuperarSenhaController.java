package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.exceptions.EmailNaoEncontrado;
import br.com.phamtecnologia.services.RecuperarSenhaService;
import br.com.phamtecnologia.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recuperar-senha")
public class RecuperarSenhaController {

    @Autowired
    private RecuperarSenhaService recuperarSenhaService;

    @PostMapping()
    public ResponseEntity<?> recuperarSenha(@Valid @RequestBody RecuperarSenhaRequest request) {

        try {
            return ResponseEntity.ok().body(recuperarSenhaService.recuperar(request));
        }
        catch (EmailNaoEncontrado e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
