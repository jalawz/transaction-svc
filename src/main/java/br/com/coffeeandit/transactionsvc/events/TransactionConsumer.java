package br.com.coffeeandit.transactionsvc.events;

import br.com.coffeeandit.transactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.transactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionConsumer {

    private final ObjectMapper objectMapper;
    private final TransactionBusiness transactionBusiness;

    @KafkaListener(topics = "${events.consumeTopic}")
    public void consumeTransaction(String message) {
        try {
            var transaction = getTransaction(message);
            transactionBusiness.updateTransaction(transaction);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "${events.returnTopic}")
    public void consultaTransactionExtorno(String message) {
        try {
            var transaction = getTransaction(message);
            log.info("Transaction recebida da análise: {}", transaction);

            if (!transaction.isAnalisada()) {
                return;
            } else {
                log.info("Transaction analisada: {}", transaction);
                transactionBusiness.aprovarTransacao(transaction);
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws JsonProcessingException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        if (Objects.isNull(transactionDto.getSituacao())) {
            transactionDto.setSituacao(SituacaoEnum.NAO_ANALISADA);
        }
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }
}
