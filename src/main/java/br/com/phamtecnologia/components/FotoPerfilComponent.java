package br.com.phamtecnologia.components;

import br.com.phamtecnologia.entities.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FotoPerfilComponent {

    public String salvarFotoPerfil(MultipartFile foto) {

        if (foto == null || foto.isEmpty()) {
            return null;
        }

        String destino = "C:/uploads/usuarios/";
        File dir = new File(destino);
        if (!dir.exists()) dir.mkdirs();

        String nomeArquivo = UUID.randomUUID() + "_" + foto.getOriginalFilename();
        Path caminho = Path.of(destino + nomeArquivo);

        try {
            Files.write(caminho, foto.getBytes());
            return nomeArquivo;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a foto do usuário.", e);
        }
    }
}
