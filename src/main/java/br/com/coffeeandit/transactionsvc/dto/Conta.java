package br.com.coffeeandit.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Conta {

    @NotNull(message = "Informar o código da Agência.")
    private Long codigoAgencia;
    @NotNull(message = "Informar o código da Conta.")
    private Long codigoConta;
}
