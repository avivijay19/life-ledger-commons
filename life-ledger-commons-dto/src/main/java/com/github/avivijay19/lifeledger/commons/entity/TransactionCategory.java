package com.github.avivijay19.lifeledger.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "transactionCategory")
@Entity
public class TransactionCategory {
    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category")
    private String category;

    @Column(name = "sub_category")
    private String subCategory;
}
