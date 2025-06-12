package com.github.avivijay19.lifeledger.commons.constants;

/**
 * @author : avinashvijayvargiya
 * @created : 29/05/25, Thursday
 **/

public class MutualFundConstants {
    public static final String MUTUAL_FUND_BASE_URL = "/mf";

    public static final String BULK_MUTUAL_FUND_BASE_URL = "/bulk";

    public static final String SIP_MUTUAL_FUND_BASE_URL = "/sip";

    public static final String PENDING_MUTUAL_FUND_BASE_URL = "/pending";

    public static final String MUTUAL_FUND_SCHEME_CODE = "/{schemeCode}";

    public static final String DATE_PATH = "/{date}";

    public static final String DATE_PARAM = "/date";

    public static final String LATEST = "/latest";

    public static final String LOAD = "/load";

    public static final String INVESTED_BASE_URL = "/invested";

    public static final String CURRENT_BASE_URL = "/current";

    public static final String INVESTED_NAV_BASE_URL = "/invested-nav";

    public static final String INVESTED_NAV = MUTUAL_FUND_BASE_URL + INVESTED_NAV_BASE_URL;

    public static final String MF_API = "/mf-api";

    public static final String INVESTED_HISTORY = "/invested-history";

    public static final String MUTUAL_FUND_DAILY = MUTUAL_FUND_BASE_URL + "/daily";

    public static final String SIP_MUTUAL_FUND_DATE =
        MUTUAL_FUND_BASE_URL + SIP_MUTUAL_FUND_BASE_URL + DATE_PATH;

    public static final String SIP_MUTUAL_FUND = MUTUAL_FUND_BASE_URL + SIP_MUTUAL_FUND_BASE_URL;

    public static final String INVESTED_HISTORY_DATE_CHECK =
        MUTUAL_FUND_BASE_URL + "/invested-history-date-check";

    public static final String INVESTED_HISTORY_TO_BE_CALCULATED =
        MUTUAL_FUND_BASE_URL + INVESTED_HISTORY + "/markForToBeCalculated";

    public static final String INVESTED_HISTORY_COMPLETE_TRANSACTION =
        MUTUAL_FUND_BASE_URL + INVESTED_HISTORY + "/complete-transactions/{toBeIncluded}";

    public static final String INVESTED_HISTORY_COMPLETE_TRANSACTION_FALSE =
        MUTUAL_FUND_BASE_URL + INVESTED_HISTORY + "/complete-transactions/false";

    public static final String MF_API_DETAILS_SCHEME_CODE =
        MUTUAL_FUND_BASE_URL + MF_API + MUTUAL_FUND_SCHEME_CODE;

    public static final String MF_API_DETAILS = MUTUAL_FUND_BASE_URL + MF_API;

    public static final String PENDING_MUTUAL_FUND_BASE =
        MUTUAL_FUND_BASE_URL + PENDING_MUTUAL_FUND_BASE_URL;

    public static final String MUTUAL_FUND_BACK_FILL_SIP =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL + SIP_MUTUAL_FUND_BASE_URL;

    public static final String BULK_MUTUAL_FUND_BACK_FILL =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL;

    public static final String INVESTED_NAV_VALUE =
        MUTUAL_FUND_BASE_URL + INVESTED_NAV_BASE_URL + MUTUAL_FUND_SCHEME_CODE + DATE_PATH;

    public static final String BULK_MF_CURRENT =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL + CURRENT_BASE_URL;

    public static final String BULK_SIP =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL + SIP_MUTUAL_FUND_BASE_URL;

    public static final String BULK_MUTUAL_FUND_LOAD =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL + LOAD;

    public static final String BULK_MF_INVESTED =
        MUTUAL_FUND_BASE_URL + BULK_MUTUAL_FUND_BASE_URL + INVESTED_BASE_URL;

    public static final String INVESTED_MF = MUTUAL_FUND_BASE_URL + INVESTED_BASE_URL;

    public static final String CURRENT_MF = MUTUAL_FUND_BASE_URL + CURRENT_BASE_URL;

    public static final String INVESTED_HISTORY_MF = MUTUAL_FUND_BASE_URL + INVESTED_HISTORY;

    public static final String PENDING_MF_SCHEME_CODE =
        MUTUAL_FUND_BASE_URL + PENDING_MUTUAL_FUND_BASE_URL + MUTUAL_FUND_SCHEME_CODE;

    public static final String PENDING_MF = MUTUAL_FUND_BASE_URL + PENDING_MUTUAL_FUND_BASE_URL;

}
