package my.exercise.account.repository;

import my.exercise.account.model.Transactions;
import my.exercise.account.model.projection.TransactionsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<TransactionsProjection> findByAccountAccountId(Long accountId);
}