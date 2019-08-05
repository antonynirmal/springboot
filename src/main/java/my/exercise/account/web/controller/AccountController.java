package my.exercise.account.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import my.exercise.account.exception.AccountException;
import my.exercise.account.model.projection.AccountProjection;
import my.exercise.account.model.projection.TransactionsProjection;
import my.exercise.account.service.AccountService;
import my.exercise.account.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionsService transactionsService;

    @ApiOperation(
            value = "get all accounts",
            notes = "No input required",
            httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "INVALID REQUEST"),
            @ApiResponse(code = 500, message = "SERVER ERROR")})
    @GetMapping("/accounts")
    Resources<Resource<AccountProjection>> accounts() {
        log.debug("--> Accounts()");
        List<AccountProjection> account = accountService.getAccounts();
        if(account.isEmpty()){
            log.debug("X-- Accounts() Data Unavailable --X");
            new AccountException("Data Unavailable or Invalid request!");
        }
        log.debug("<-- Accounts() ");

        List<Resource<AccountProjection>> accountResources =
                account.stream()
                        .map(AccountController::createAccountResource)
                        .collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(AccountController.class)
                .accounts()).withSelfRel();
        return new Resources<>(accountResources, selfRel);
    }

    @ApiOperation(
            value = "get an account",
            notes = "Takes account number as input and returns account details",
            httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "INVALID REQUEST"),
            @ApiResponse(code = 500, message = "SERVER ERROR")})
    @GetMapping("/accounts/{number}")
    Resources<Resource<AccountProjection>> getAccountsByNumber(@PathVariable Long number) {
        log.debug("--> getAccountsByNumber()");
        List<AccountProjection> account = accountService.getAccountsByNumber(number);
        if(account.isEmpty()){
            log.debug("X-- getAccountsByNumber() Data Unavailable --X");
            new AccountException("Data Unavailable or Invalid request!");
        }
        List<Resource<AccountProjection>> accountResources =
                account.stream()
                        .map(AccountController::createAccountResource)
                        .collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(AccountController.class)
                .getAccountsByNumber(number)).withSelfRel();

        log.debug("<-- getAccountsByNumber()");
        return new Resources<>(accountResources, selfRel);
    }
    
    
    @ApiOperation(
            value = "get all transactions of an account",
            notes = "accepts account number as input and returns all the transaction of the account",
            httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "INVALID REQUEST"),
            @ApiResponse(code = 500, message = "SERVER ERROR")})

    @GetMapping("/accounts/{number}/transactions")
    Resources<Resource<TransactionsProjection>> getTransactionByAccount(@PathVariable Long number) {
        log.debug("--> getTransactionByAccount()");
        List<TransactionsProjection> transactions = transactionsService.getTransactionsByAccount(number);
        if(transactions.isEmpty()){
            log.debug("X-- getTransactionByAccount() Data Unavailable --X");
            new AccountException("Data Unavailable or Invalid request!");
        }
        List<Resource<TransactionsProjection>> transactionResources =
                transactions.stream()
                        .map(AccountController::createTransactionResource)
                        .collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(AccountController.class)
                .getTransactionByAccount(number)).withSelfRel();
        log.debug("<-- getTransactionByAccount()");

        return new Resources<>(transactionResources, selfRel);
    }


    private static Resource<AccountProjection> createAccountResource(AccountProjection account) {
        Link selfRel = linkTo(methodOn(AccountController.class).accounts()).withSelfRel();
        return new Resource<>(account, selfRel,
                createAccountLink(account.getAccountNumber()));
    }

    private static Resource<TransactionsProjection> createTransactionResource(TransactionsProjection transactions) {
        Link selfRel = linkTo(methodOn(AccountController.class).getTransactionByAccount(transactions.getAccountNumber())).withSelfRel();
        return new Resource<>(transactions, selfRel,
                createAccountLink(transactions.getAccountNumber()));
    }

    static Link createAccountLink(Long accountId) {
        return linkTo(methodOn(AccountController.class).getAccountsByNumber(accountId))
                .withRel("account");
    }
}