package br.com.tgid.javajunior.services;

import br.com.tgid.javajunior.dtos.ReceiveDataTransition;
import br.com.tgid.javajunior.models.Client;
import br.com.tgid.javajunior.models.Company;
import br.com.tgid.javajunior.models.Transition;
import br.com.tgid.javajunior.repositories.ClientRepository;
import br.com.tgid.javajunior.repositories.CompanyRepository;
import br.com.tgid.javajunior.repositories.TransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransitionService {

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private CompanyRepository repositoryCompany;

    @Autowired
    private ClientRepository respositoryClient;


    public Transition create(ReceiveDataTransition data) {

        Company company = this.validateEntityCompany(data.getCnpj());

        Client client = this.validationEntityClient(data.getCpf());

        Transition tranferData = new Transition();
        tranferData.setClient(client);
        tranferData.setCompany(company);

        Transition createdTransition = transitionRepository.save(tranferData);

        return createdTransition;
    }

    public double getWithdraw(String cpf, String cnpj, double value) {

        Client client = this.respositoryClient.findByCpf(cpf);

        Transition transition = this.transitionRepository.findByClientId(client.getId());

        if (!transition.getCompany().getCnpj().contentEquals(cnpj)) throw new RuntimeException("Inseriu cpnj errado");
        Company company = this.validateEntityCompany(cnpj);

        if (company.getSaldo() > 0) throw new RuntimeException("Saldo vazio");


        if (value < 0) throw new RuntimeException("Não é possível sacar valor negativo");

        if (company.getSaldo() < value) throw new RuntimeException("Não é possível sacar essa quantidade de dinheiro");

        this.repositoryCompany.findById(company.getId()).map(result -> {
            result.setSaldo(result.getSaldo() - value);
            return result;
        });

        return value;
    }

    private Company validateEntityCompany(String cnpj) {
        Company company = this.repositoryCompany.findByCnpj(cnpj);

        if (company == null) throw new RuntimeException("Não existe esse cnpj");

        return company;
    }

    private Client validationEntityClient(String cpf) {
        Client client = this.respositoryClient.findByCpf(cpf);

        if (client == null) throw new RuntimeException("Não existe esse cnpj cadastrado");

        return client;
    }
}
