package my.exercise.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transactions extends ResourceSupport {

    @Id
    @GeneratedValue
    private Long transactionsId;

    @ManyToOne
    @JoinColumn(name="account_id")
    @JsonIgnore
    private Account account;

    @NonNull
    private Date valueDate;

    @NonNull
    private BigDecimal debitAmount;

    @NonNull
    private BigDecimal creditAmount;

    private String transactionType;

    private String transactionNarrative;

}