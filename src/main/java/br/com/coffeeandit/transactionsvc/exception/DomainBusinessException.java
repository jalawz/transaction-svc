package br.com.coffeeandit.transactionsvc.exception;

public class DomainBusinessException extends RuntimeException {
    public DomainBusinessException(String message) {
        super(message);
    }
}
