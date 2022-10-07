package br.com.coffeeandit.transactionsvc.domain;

import br.com.coffeeandit.transactionsvc.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import br.com.coffeeandit.transactionsvc.repository.TransactionRepository;
import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public void updateTransaction(TransactionDto transactionDto) {
        log.info("Atualizando transaction: {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void aprovarTransacao(TransactionDto transactionEvent) {
        var transaction = buscarTransacao(transactionEvent);
        if (transaction.isPresent()) {
            var transactionDB = transaction.get();
            if (!transactionDB.isAnalisada() && transactionEvent.isAnalisada()) {
                transactionDB.aprovar();
                updateTransaction(transactionDB);
                log.info("Transaction aprovada: {}", transactionDB);
            }
        }
    }

    public Optional<TransactionDto> buscarTransacao(TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getUuid());
    }
}
