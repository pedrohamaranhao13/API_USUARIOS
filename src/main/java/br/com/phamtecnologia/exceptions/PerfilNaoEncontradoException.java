package br.com.phamtecnologia.exceptions;

public class PerfilNaoEncontradoException extends RuntimeException {

    @Override
    public  String getMessage() {
        return "Perfil não encontrado!";

    }
}
