package br.com.coffeeandit.transactionsvc.validator.impl;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import org.springframework.stereotype.Component;

@Component
public class EmptyTransactionValidationBean implements TransactionValidation {

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

    }
}
