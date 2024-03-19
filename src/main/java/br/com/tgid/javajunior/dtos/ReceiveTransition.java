package br.com.tgid.javajunior.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveTransition {

    private String cpf;

    private String cnpj;

    private double value;
}
