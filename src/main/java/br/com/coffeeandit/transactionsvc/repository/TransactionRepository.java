package br.com.coffeeandit.transactionsvc.repository;

import br.com.coffeeandit.transactionsvc.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionDto, UUID> {
}
