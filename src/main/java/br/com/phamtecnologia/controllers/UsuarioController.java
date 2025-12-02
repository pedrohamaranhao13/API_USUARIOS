package br.com.phamtecnologia.controllers;

import br.com.phamtecnologia.dtos.requests.AtualizarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.exceptions.EmailJaCadastradoException;
import br.com.phamtecnologia.exceptions.EmailNaoEncontrado;
import br.com.phamtecnologia.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criarUsuario(
            @RequestPart("request") String requestJson,
            @RequestPart(name = "foto", required = false)MultipartFile foto) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            CriarUsuarioRequest request = mapper.readValue(requestJson, CriarUsuarioRequest.class);

            return ResponseEntity.ok().body(usuarioService.criar(request, foto));
        }
        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(value = "atualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @RequestPart("request") String requestJson,
            @RequestPart(name = "foto", required = false) MultipartFile foto) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            AtualizarUsuarioRequest request = mapper.readValue(requestJson, AtualizarUsuarioRequest.class);

            return ResponseEntity.ok().body(usuarioService.atualizar(request, foto, id));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> consultar() {
        return ResponseEntity.ok(usuarioService.consultar());
    }





}
