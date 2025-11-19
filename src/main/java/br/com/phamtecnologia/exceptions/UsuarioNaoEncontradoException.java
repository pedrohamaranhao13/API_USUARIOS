package br.com.phamtecnologia.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Usuário não encontrado";
    }
}
