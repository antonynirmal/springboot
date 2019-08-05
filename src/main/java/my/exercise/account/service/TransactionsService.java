package my.exercise.account.service;

import my.exercise.account.model.projection.TransactionsProjection;

import java.util.List;

public interface TransactionsService {
    List<TransactionsProjection> getTransactionsByAccount(Long accountNumber);
}
