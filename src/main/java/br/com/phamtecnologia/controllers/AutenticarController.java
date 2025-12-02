package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.services.Autenticarservice;
import br.com.phamtecnologia.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autenticar")
public class AutenticarController {

    @Autowired
    private Autenticarservice autenticarservice;

    @PostMapping()
    public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody AutenticarUsuarioRequest request) {

        try {
            return ResponseEntity.ok().body(autenticarservice.autenticar(request));
        }
        catch (AcessoNegadoException e) {
            return  ResponseEntity.status(401).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
