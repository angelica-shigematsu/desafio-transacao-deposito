package br.com.tgid.javajunior.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private long id;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="saldo")
    private double saldo;
}
