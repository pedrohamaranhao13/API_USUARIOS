package br.com.phamtecnologia.repositories;

import br.com.phamtecnologia.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("""
            SELECT
                u
            FROM
                Usuario u
            WHERE 
                u.email = :email
            """)
    Usuario findByEmail(@Param("email") String email);

    @Query("""
            SELECT
                u
            FROM
                Usuario u
            JOIN 
                u.perfil p
            WHERE
                u.email = :email
            AND
                u.senha = :senha
            """)
    Usuario findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

    @Query(
            """
             SELECT
                u
             FROM
                Usuario u
             WHERE 
                u.telefone = :telefone
             """
    )
    Usuario findByTelefone(@Param("telefone") String telefone);

    @Query(
            """
            SELECT 
                u
            FROM 
                Usuario u
            JOIN 
                u.perfil p
            WHERE 
                u.id = :id
            """
    )
    Usuario findByIdWithPerfil(@Param("id") UUID id);
}
