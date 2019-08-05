package my.exercise.account.service;

import lombok.extern.slf4j.Slf4j;
import my.exercise.account.repository.AccountRepository;
import my.exercise.account.model.projection.AccountProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

        @Override
    public List<AccountProjection> getAccounts() {

        List<AccountProjection> account = accountRepository.findAllProjectedBy();
        return account;
    }

    @Override
    public List<AccountProjection> getAccountsByNumber(Long number) {
        List<AccountProjection> account = accountRepository.findByAccountNumber(number);
        return account;
    }

}
