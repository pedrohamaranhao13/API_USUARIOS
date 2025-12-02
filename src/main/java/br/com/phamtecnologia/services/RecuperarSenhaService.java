package br.com.phamtecnologia.services;

import br.com.phamtecnologia.components.CryptoComponent;
import br.com.phamtecnologia.components.EmailComponent;
import br.com.phamtecnologia.dtos.requests.RecuperarSenhaRequest;
import br.com.phamtecnologia.dtos.responses.RecuperarSenhaResponse;
import br.com.phamtecnologia.exceptions.EmailNaoEncontrado;
import br.com.phamtecnologia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecuperarSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private EmailComponent emailComponent;

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
