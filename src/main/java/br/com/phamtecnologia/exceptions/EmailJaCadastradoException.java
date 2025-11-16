package br.com.phamtecnologia.exceptions;

public class EmailJaCadastradoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "E-mail já está cadastrado. Tente outro.";
    }

}
