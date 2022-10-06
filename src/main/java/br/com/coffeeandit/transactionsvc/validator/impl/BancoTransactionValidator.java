package br.com.coffeeandit.transactionsvc.validator.impl;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConditionalOnProperty(value = "${transactional.validation.banco}", havingValue = "true", matchIfMissing = false)
public class BancoTransactionValidator implements TransactionValidator {

    public static final int CODIGO_BANCO = 785;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (Objects.isNull(requestTransactionDto.getBeneficiario())) {
            throw new DomainBusinessException("Inválido banco do beneficiário.");
        } else if (Objects.isNull(requestTransactionDto.getBeneficiario().getCodigoBanco()) ||
                requestTransactionDto.getBeneficiario().getCodigoBanco().compareTo((long) CODIGO_BANCO) != 0) {
            throw new DomainBusinessException("Inválido banco do beneficiário.");
        }
    }
}
