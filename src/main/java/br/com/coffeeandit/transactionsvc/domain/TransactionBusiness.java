package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidationBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionBusiness {

    private final TransactionRepository transactionRepository;
    private final TransactionValidation transactionValidation;

    public void save(final RequestTransactionDto requestTransactionDto) {
        if (Objects.isNull(requestTransactionDto.getData())) {
            requestTransactionDto.setData(LocalDateTime.now());
        }
        transactionValidation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }
}
