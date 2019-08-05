package my.exercise.account.service;

import lombok.extern.slf4j.Slf4j;
import my.exercise.account.exception.AccountException;
import my.exercise.account.model.projection.AccountProjection;
import my.exercise.account.model.projection.TransactionsProjection;
import my.exercise.account.repository.AccountRepository;
import my.exercise.account.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<TransactionsProjection> getTransactionsByAccount(Long accountNumber) {

        List<AccountProjection> accounts = accountRepository.findByAccountNumber(accountNumber);
        Optional<AccountProjection> account = accounts.stream().findFirst();
        List<TransactionsProjection> transactions;
        if(account.isPresent())
            transactions = transactionsRepository.findByAccountAccountId(account.get().getAccountId());
        else
            throw new AccountException("No Transaction exists");
        return transactions;
    }
}
