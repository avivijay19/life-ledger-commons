package com.github.avivijay19.lifeledger.commons.entity.transactions;

import com.github.avivijay19.lifeledger.commons.enumeration.transaction.TransactionStatus;
import com.github.avivijay19.lifeledger.commons.enumeration.transaction.VerificationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "transaction", schema = "transactions")
@Entity
public class Transaction {
    @Id
    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transaction_type")
    private TransactionStatus transactionStatus;

    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "date_of_transaction")
    private LocalDate dateOfTransaction;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "verified")
    private VerificationStatus verified;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "audit_created_timestamp")
    private Long auditCreatedTimestamp;

    @Column(name = "verified_timestamp")
    private Long verifiedTimestamp;

    @Column(name = "deleted_timestamp")
    private Long deletedTimestamp;
}
