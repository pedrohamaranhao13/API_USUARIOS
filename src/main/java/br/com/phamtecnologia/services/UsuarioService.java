package br.com.phamtecnologia.services;

import br.com.phamtecnologia.components.CryptoComponent;
import br.com.phamtecnologia.components.EmailComponent;
import br.com.phamtecnologia.components.JwtBearerComponent;
import br.com.phamtecnologia.dtos.requests.AutenticarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.CriarUsuarioRequest;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.dtos.responses.AutenticarUsuarioResponse;
import br.com.phamtecnologia.dtos.responses.CriarUsuarioResponse;
import br.com.phamtecnologia.dtos.responses.RecuperarSenhaResponse;
import br.com.phamtecnologia.entities.Usuario;
import br.com.phamtecnologia.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.exceptions.EmailJaCadastradoException;
import br.com.phamtecnologia.exceptions.EmailNaoEncontrado;
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

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private JwtBearerComponent jwtBearerComponent;

    @Autowired
    private EmailComponent emailComponent;

    public CriarUsuarioResponse criar(CriarUsuarioRequest request) {

        if(usuarioRepository.findByEmail(request.email()) != null) {
            throw new EmailJaCadastradoException();
        }

        var perfil = perfilRepository.findByNome(request.perfil());
        if (perfil == null) {
            throw new RuntimeException("Perfil não encontrado: " + request.perfil());
        }

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setTelefone(request.telefone());
        usuario.setSenha(cryptoComponent.encrypt(request.senha()));
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

    public RecuperarSenhaResponse recuperar(RecuperarSenhaRequest request){

        var usuario = (usuarioRepository.findByEmail(request.email()));

        if (usuario == null){
            throw new EmailNaoEncontrado();
        }

        var novaSenha = cryptoComponent.generateRandomPassword(8);

        usuario.setSenha(cryptoComponent.encrypt(novaSenha));
        usuarioRepository.save(usuario);

        emailComponent.send(
                usuario.getEmail(),
                "Recuperação de Senha - PhamTecnologia",
                """
                Olá, sua nova senha é:
    
                """ + novaSenha + """

            Recomendamos alterá-la após o login.
            """
        );


        return new RecuperarSenhaResponse(
                usuario.getEmail()
        );
    }
}
