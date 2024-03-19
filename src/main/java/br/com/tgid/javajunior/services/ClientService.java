package br.com.tgid.javajunior.services;

import br.com.tgid.javajunior.models.Client;
import br.com.tgid.javajunior.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client create(Client client) {


        Client existCPF = clientRepository.findByCpf(client.getCpf());

        if (existCPF != null) throw new RuntimeException("Já existe cliente com esse CPF");

        if (client.getCpf().isEmpty()) throw new RuntimeException("CPF vazio");

        if (client.getCpf().length() != 11) throw  new RuntimeException("CPF inválido. Digite 11 dígitos");

        if (client.getCpf().matches("/[A-Za-z]/")) throw  new RuntimeException("Digite apenas digitos");

        return this.clientRepository.save(client);
    }
}
