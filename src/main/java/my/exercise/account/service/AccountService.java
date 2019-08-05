package my.exercise.account.service;

import my.exercise.account.model.projection.AccountProjection;

import java.util.List;

public interface AccountService {

    List<AccountProjection> getAccounts();
    List<AccountProjection> getAccountsByNumber(Long number);

}
