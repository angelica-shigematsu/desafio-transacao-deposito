package br.com.tgid.javajunior.services;

import br.com.tgid.javajunior.dtos.ReceiveDataTransition;
import br.com.tgid.javajunior.models.Client;
import br.com.tgid.javajunior.models.Company;
import br.com.tgid.javajunior.models.Transaction;
import br.com.tgid.javajunior.repositories.ClientRepository;
import br.com.tgid.javajunior.repositories.CompanyRepository;
import br.com.tgid.javajunior.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transitionRepository;

    @Autowired
    private CompanyRepository repositoryCompany;

    @Autowired
    private ClientRepository respositoryClient;


    public Transaction create(ReceiveDataTransition data) {

        Company company = this.validateEntityCompany(data.getCnpj());

        Client client = this.validationEntityClient(data.getCpf());

        Transaction transferData = new Transaction();
        transferData.setClient(client);
        transferData.setCompany(company);

        Transaction createdTransition = transitionRepository.save(transferData);

        return createdTransition;
    }

    public double getWithdraw(String cpf, String cnpj, double value) {

        Client client = this.respositoryClient.findByCpf(cpf);

        Transaction transition = this.transitionRepository.findByClientId(client.getId());

        if (transition == null) throw new RuntimeException("Inseriu cpnj errado");

        Company company = this.validateEntityCompany(cnpj);

        if (company.getSaldo() < 0) throw new RuntimeException("Saldo vazio");


        if (value < 0) throw new RuntimeException("Não é possível sacar valor negativo");

        if (company.getSaldo() < value) throw new RuntimeException("Não é possível sacar essa quantidade de dinheiro");

        Optional<Company> data = this.repositoryCompany.findById(company.getId()).map(result -> {
            result.setSaldo(result.getSaldo() - value);
            return this.repositoryCompany.save(result);
        });

        if (data != null) return value;

        return 0.0;
    }

    private Company validateEntityCompany(String cnpj) {
        Company company = this.repositoryCompany.findByCnpj(cnpj);

        if (company == null) throw new RuntimeException("Não existe esse cnpj");

        return company;
    }

    private Client validationEntityClient(String cpf) {
        Client client = this.respositoryClient.findByCpf(cpf);

        if (client == null) throw new RuntimeException("Não existe esse cpf cadastrado");

        return client;
    }

    public double setDeposit(String cpf, String cnpj, double value) {

        if (value < 0 ) throw new RuntimeException("Não é possível depositar valor negativo");

        Client client = this.validationEntityClient(cpf);

        Company company = this.validateEntityCompany(cnpj);

        Transaction existClient = this.transitionRepository.findByClientId(client.getId());

        if (existClient == null) throw new RuntimeException("Inseriu cpf errado");

        Transaction existCompany = this.transitionRepository.findByCompanyId(company.getId());

        if (existCompany == null) throw new RuntimeException("Inseriu cnpj errado");

        Optional<Object> data = this.repositoryCompany.findById(company.getId()).map(result -> {
            result.setSaldo(value);
            return this.repositoryCompany.save(result);
        });

        if (data != null) return value;

        return 0;
    }
}
