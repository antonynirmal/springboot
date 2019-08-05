package my.exercise.account.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import my.exercise.account.model.Transactions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import java.math.BigDecimal;
import java.util.Date;

@Projection(types = Transactions.class)
public interface TransactionsProjection {

    @Value("#{target.getAccount().getAccountNumber()}")
    Long getAccountNumber();

    @Value("#{target.getAccount().getAccountName()}")
    String getAccountName();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM. dd, yyyy")
    Date getValueDate();

    @Value("#{target.getAccount().getCurrency()}")
    String getCurrencyType();

    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "99,999.99")
    BigDecimal getDebitAmount();

    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "99,999.99")
    BigDecimal getcreditAmount();

    String getTransactionType();

    String getTransactionNarrative();

}
