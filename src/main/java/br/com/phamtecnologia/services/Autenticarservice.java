package br.com.phamtecnologia.services;

import br.com.phamtecnologia.components.CryptoComponent;
import br.com.phamtecnologia.components.JwtBearerComponent;
import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.responses.AutenticarUsuarioResponse;
import br.com.phamtecnologia.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Autenticarservice {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private JwtBearerComponent jwtBearerComponent;

    public AutenticarUsuarioResponse autenticar(AutenticarUsuarioRequest request) {

        var usuario = usuarioRepository.findByEmailAndSenha(request.email(), cryptoComponent.encrypt(request.senha()));

        if (usuario == null) {
            throw new AcessoNegadoException();
        }

        return new AutenticarUsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().getNome(),
                LocalDateTime.now(),
                jwtBearerComponent.createToken(usuario.getEmail(), usuario.getPerfil().getNome())
        );
    }
}
