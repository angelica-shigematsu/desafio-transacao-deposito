package br.com.tgid.javajunior.repositories;

import br.com.tgid.javajunior.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByClientId(long id);

    Transaction findByCompanyId(long id);

}
