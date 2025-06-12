package com.github.avivijay19.lifeledger.commons.constants;

/**
 * @author : avinashvijayvargiya
 * @created : 30/05/25, Friday
 **/

public class DatabaseConstants {
    public static final String DATABASE_BASE_URL = "/database";

    public static final String SYNC_DATABASE_BASE_URL = "/syncDatabase";

    public static final String SYNC_DATABASE_BY_SHEET_TITLE =
        DATABASE_BASE_URL + DATABASE_BASE_URL + "/{sheetTitle}";
}
