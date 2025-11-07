package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.PerfilRequest;
import br.com.phamtecnologia.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping()
    public ResponseEntity<?> criarPerfil(@RequestBody PerfilRequest request) {

        return ResponseEntity.ok().body(perfilService.criar(request));
    }

}
