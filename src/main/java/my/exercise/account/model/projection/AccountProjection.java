package my.exercise.account.model.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import my.exercise.account.model.Account;
import my.exercise.account.model.Transactions;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Projection(name = "default", types = Account.class)
public interface AccountProjection {

    @JsonProperty("account_id")
    @JsonIgnore
    Long getAccountId();

    Long getAccountNumber();

    String getAccountName();

    String getAccountType();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date getBalanceDate();

    String getCurrency();

    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "###,###.##")
    BigDecimal getOpeningAvailableBalance();

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @OrderBy("valueDate ASC")
    @JsonIgnore
    List<Transactions> getTransactions();

}

