package br.com.phamtecnologia.services;

import br.com.phamtecnologia.dtos.requests.PerfilRequest;
import br.com.phamtecnologia.dtos.responses.PerfilResponse;
import br.com.phamtecnologia.entities.Perfil;
import br.com.phamtecnologia.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public PerfilResponse criar(PerfilRequest request) {

        var perfil = new Perfil();
        perfil.setNome(request.nome());

        perfilRepository.save(perfil);

        return new PerfilResponse(
          perfil.getId(),
          perfil.getNome()
        );
    }

    public List<PerfilResponse> consultar() {

        var lista =  perfilRepository.findAll();

        return lista.stream()
                .map(p -> new PerfilResponse(
                        p.getId(),
                        p.getNome()
                ))
                .toList();
    }

}
