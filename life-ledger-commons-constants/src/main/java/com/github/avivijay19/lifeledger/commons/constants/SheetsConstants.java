package com.github.avivijay19.lifeledger.commons.constants;

/**
 * @author : avinashvijayvargiya
 * @created : 30/05/25, Friday
 **/

public class SheetsConstants {
    public static final String SHEETS_BASE_URL = "/sheets";

    public static final String SYNC_SHEETS_BASE_URL = "/syncSheets";

    public static final String SYNC_SHEET_BY_SHEET_TITLE =
        SHEETS_BASE_URL + SYNC_SHEETS_BASE_URL + "/{sheetTitle}";
}
