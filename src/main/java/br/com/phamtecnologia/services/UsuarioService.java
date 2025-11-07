package br.com.phamtecnologia.services;

import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.dtos.responses.AutenticarUsuarioResponse;
import br.com.phamtecnologia.dtos.responses.CriarUsuarioResponse;
import br.com.phamtecnologia.dtos.responses.RecuperarSenhaResponse;
import br.com.phamtecnologia.entities.Usuario;
import br.com.phamtecnologia.repositories.PerfilRepository;
import br.com.phamtecnologia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public CriarUsuarioResponse criar(CriarUsuarioRequest request) {

        var perfil = perfilRepository.findByNome(request.perfil());
        if (perfil == null) {
            throw new RuntimeException("Perfil não encontrado: " + request.perfil());
        }

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setTelefone(request.telefone());
        usuario.setSenha(request.senha());
        usuario.setDataHoraCriacao(LocalDateTime.now());
        usuario.setPerfil(perfil);


        usuarioRepository.save(usuario);

        return new CriarUsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getPerfil().getNome(),
                usuario.getDataHoraCriacao()
        );
    }

    public AutenticarUsuarioResponse autenticar(AutenticarUsuarioRequest request) {
        //TODO
        return null;
    }

    public RecuperarSenhaResponse recuperar(RecuperarSenhaRequest request){
        //TODO
        return null;
    }
}
