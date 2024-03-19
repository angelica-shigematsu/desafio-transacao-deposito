package br.com.tgid.javajunior.services;

import br.com.tgid.javajunior.models.Company;
import br.com.tgid.javajunior.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.ComponentAdapter;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repositoryCompany;

    public Company create(Company company) {

        Company existCnpj = this.repositoryCompany.findByCnpj(company.getCnpj());

        if (existCnpj != null) throw new RuntimeException("Já existe empresa com esse CNPJ");
        if (company.getCnpj().isEmpty()) throw new RuntimeException("CNPJ vazio");

        if (company.getCnpj().length() != 13) throw  new RuntimeException("CPF inválido. Digite 13 dígitos");

        if (company.getCnpj().matches("/[A-Za-z]/")) throw  new RuntimeException("Digite apenas digitos");

        return this.repositoryCompany.save(company);
    }
}
