package com.github.avivijay19.lifeledger.commons.backup.sheets;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class GoogleSheetClient {

    private static final Logger log = LoggerFactory.getLogger(GoogleSheetClient.class);
    private static final String SCOPE = "https://www.googleapis.com/auth/spreadsheets";

    private final String base64Credentials;
    private final String spreadsheetId;

    public GoogleSheetClient(String base64Credentials, String spreadsheetId) {
        this.base64Credentials = base64Credentials;
        this.spreadsheetId = spreadsheetId;
    }

    public List<List<Object>> readSheet(String sheetName) throws IOException {
        var service = getSheetsService();
        var response = service.spreadsheets().values()
            .get(spreadsheetId, sheetName)
            .execute();
        return response.getValues();
    }

    public void writeSheet(List<List<Object>> data, String sheetName) throws IOException {
        var service = getSheetsService();
        var body = new ValueRange().setValues(data);
        service.spreadsheets().values()
            .update(spreadsheetId, sheetName, body)
            .setValueInputOption("RAW")
            .execute();
    }

    public void clearSheet(String sheetName) throws IOException {
        var service = getSheetsService();
        try {
            service.spreadsheets().values()
                .clear(spreadsheetId, sheetName, new ClearValuesRequest())
                .execute();
            log.info("Sheet '{}' cleared.", sheetName);
        } catch (GoogleJsonResponseException e) {
            if (e.getStatusCode() == 404
                || (e.getStatusCode() == 400
                    && e.getDetails() != null
                    && e.getDetails().getMessage() != null
                    && e.getDetails().getMessage().contains("Unable to parse range"))) {
                log.info("Worksheet '{}' not found, creating it.", sheetName);
                createWorksheet(service, sheetName);
            } else {
                throw e;
            }
        }
    }

    private void createWorksheet(Sheets service, String sheetName) throws IOException {
        var addSheetRequest = new AddSheetRequest()
            .setProperties(new SheetProperties().setTitle(sheetName));
        var batchRequest = new BatchUpdateSpreadsheetRequest()
            .setRequests(List.of(new Request().setAddSheet(addSheetRequest)));
        service.spreadsheets()
            .batchUpdate(spreadsheetId, batchRequest)
            .execute();
        log.info("Worksheet '{}' created.", sheetName);
    }

    @SuppressWarnings("deprecation")
    private Sheets getSheetsService() throws IOException {
        var credential =
            GoogleCredential.fromStream(
                    new ByteArrayInputStream(Base64.getDecoder().decode(base64Credentials)))
                .createScoped(Collections.singleton(SCOPE));

        return new Sheets.Builder(
            credential.getTransport(),
            credential.getJsonFactory(),
            credential)
            .setApplicationName("Life Ledger Backup")
            .build();
    }
}
