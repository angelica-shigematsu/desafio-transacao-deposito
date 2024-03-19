package br.com.tgid.javajunior.repositories;

import br.com.tgid.javajunior.models.Transition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Long> {
    Transition findByClientId(long id);

}
