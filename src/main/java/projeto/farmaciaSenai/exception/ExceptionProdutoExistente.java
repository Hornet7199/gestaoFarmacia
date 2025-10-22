package projeto.farmaciaSenai.exception;

public class ExceptionProdutoExistente extends RuntimeException {
    public ExceptionProdutoExistente(String message) {
        super(message);
    }
}
