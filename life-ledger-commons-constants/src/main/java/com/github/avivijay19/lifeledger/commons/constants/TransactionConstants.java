package com.github.avivijay19.lifeledger.commons.constants;

/**
 * @author : avinashvijayvargiya
 * @created : 26/05/25, Monday
 **/

public class TransactionConstants {
    public static final String TRANSACTION_BASE_URL = "/transaction";

    public static final String TRANSACTION_CATEGORY_BASE_URL = "/transaction-category";

    public static final String TRANSACTION_CATEGORY_ID =
        "/transaction-category" + "/{transactionCategoryId}";

    public static final String VERIFY_TRANSACTION = TRANSACTION_BASE_URL + "/verifyTransaction";

    public static final String MARK_FOR_DELETION_OF_TRANSACTION =
        TRANSACTION_BASE_URL + "/markForDeletionOfTransaction";

    public static final String SET_CATEGORY_ID = TRANSACTION_BASE_URL + "/setCategoryId";

    public static final String DELETE_TRANSACTION_GMAIL =
        TRANSACTION_BASE_URL + "/delete-transaction-gmail";
}
