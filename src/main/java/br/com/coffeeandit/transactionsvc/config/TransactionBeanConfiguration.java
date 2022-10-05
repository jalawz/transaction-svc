package br.com.coffeeandit.transactionsvc.config;

import br.com.coffeeandit.transactionsvc.validator.TransactionValidation;
import br.com.coffeeandit.transactionsvc.validator.impl.EmptyTransactionValidationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionNewerThanJavaSeventeenValidation() {
        return new EmptyTransactionValidationBean();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.SEVENTEEN)
    public TransactionValidation emptyTransactionJavaOlderThanSeventeenValidation() {
        return new EmptyTransactionValidationBean();
    }
}
