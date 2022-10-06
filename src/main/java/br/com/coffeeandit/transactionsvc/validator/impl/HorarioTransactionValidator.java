package br.com.coffeeandit.transactionsvc.validator.impl;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.exception.DomainBusinessException;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(value = "${transactional.validation.horario}", havingValue = "true", matchIfMissing = false)
public class HorarioTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (LocalDateTime.now().getHour() > HOUR_END || requestTransactionDto.getData().getHour() > HOUR_END) {
            throw new DomainBusinessException("Horário após as 18.");
        }
    }
}
