package br.com.phamtecnologia.services;

import br.com.phamtecnologia.components.CryptoComponent;
import br.com.phamtecnologia.components.FotoPerfilComponent;
import br.com.phamtecnologia.dtos.requests.AtualizarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.responses.*;
import br.com.phamtecnologia.entities.Usuario;
import br.com.phamtecnologia.exceptions.*;
import br.com.phamtecnologia.repositories.PerfilRepository;
import br.com.phamtecnologia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CryptoComponent cryptoComponent;


    @Autowired
    private FotoPerfilComponent fotoPerfilComponent;

    public CriarUsuarioResponse criar(CriarUsuarioRequest request, MultipartFile foto) {

        if(usuarioRepository.findByEmail(request.email()) != null) {
            throw new EmailJaCadastradoException();
        }

        var perfil = perfilRepository.findByNome(request.perfil());
        if (perfil == null) {
            throw new PerfilNaoEncontradoException();
        }

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setTelefone(request.telefone());
        usuario.setSenha(cryptoComponent.encrypt(request.senha()));
        usuario.setDataHoraCriacao(LocalDateTime.now());
        usuario.setAtivo(true);
        usuario.setPerfil(perfil);

        String nomeArquivo = fotoPerfilComponent.salvarFotoPerfil(foto);
        if (nomeArquivo != null) {
            usuario.setFoto(nomeArquivo);
        }

        usuarioRepository.save(usuario);

        return new CriarUsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getPerfil().getNome(),
                usuario.getDataHoraCriacao(),
                usuario.getFoto()

        );
    }

    public AtualizarUsuarioResponse atualizar(AtualizarUsuarioRequest request,
                                              MultipartFile foto,
                                              UUID id) {

        var usuario = usuarioRepository.findByIdWithPerfil(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }

        if (request.nome() != null)
            usuario.setNome(request.nome());

        if (request.email() != null)
            usuario.setEmail(request.email());

        if (request.telefone() != null)
            usuario.setTelefone(request.telefone());

        if (request.senha() != null && !request.senha().isBlank())
            usuario.setSenha(cryptoComponent.encrypt(request.senha()));

        if (request.perfil() != null && !request.perfil().isBlank()) {
            var perfil = perfilRepository.findByNome(request.perfil());
            if (perfil == null) {
                throw new PerfilNaoEncontradoException();
            }
            usuario.setPerfil(perfil);
        }

        if (foto != null && !foto.isEmpty()) {
            String nomeArquivo = fotoPerfilComponent.salvarFotoPerfil(foto);
            usuario.setFoto(nomeArquivo);
        }

        usuarioRepository.save(usuario);

        return new AtualizarUsuarioResponse(
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail()
        );
    }
    public void deletar(UUID id) {

        var usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        usuario.setAtivo(false);

        usuarioRepository.save(usuario);
    }

    public List<UsuarioResponse> consultar() {

        var lista = usuarioRepository.findByUsuarioAtivo();

        return lista.stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getTelefone(),
                        usuario.getEmail(),
                        usuario.getPerfil().getNome()
                )).toList();
    }

}
