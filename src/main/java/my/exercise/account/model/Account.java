package my.exercise.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "account")
public class Account extends ResourceSupport {

    @Id
    @GeneratedValue
    private Long accountId;

    private Long accountNumber;

    @NonNull
    private String accountName;

    @NonNull
    private String accountType;

    private Date balanceDate;

    @NonNull
    private String currency;

    @NonNull
    private BigDecimal openingAvailableBalance;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @OrderBy("valueDate ASC")
    @JsonIgnore
    private Set<Transactions> transactions;

}