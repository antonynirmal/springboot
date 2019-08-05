package my.exercise.account.repository;

import my.exercise.account.model.Account;
import my.exercise.account.model.projection.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<AccountProjection> findAllProjectedBy();
    List<AccountProjection> findByAccountNumber(Long accountNumber);
}