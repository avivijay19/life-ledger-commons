package com.github.avivijay19.lifeledger.commons.dto.transaction;

import com.github.avivijay19.lifeledger.commons.enumeration.transaction.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transactions {

    private double amount;

    private TransactionStatus transactionType;

    private String sender;

    private String recipient;

    private LocalDate dateOfTransaction;

    private Long timeStamp;

    private String transactionId;

    private String mailId;
}
