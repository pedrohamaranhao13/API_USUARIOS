package br.com.phamtecnologia.exceptions;

public class EmailNaoEncontrado extends RuntimeException {

    @Override
    public  String getMessage() {
        return "E-mail não encontrado!";
    }
}
