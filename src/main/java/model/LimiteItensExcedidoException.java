package model;

public class LimiteItensExcedidoException extends RuntimeException{
    public LimiteItensExcedidoException(String mensagem){
        super(mensagem);
    }
}
