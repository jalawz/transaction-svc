package br.com.coffeeandit.transactionsvc.validator;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;

public interface TransactionValidation {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;
}
