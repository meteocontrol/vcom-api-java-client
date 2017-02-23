package com.meteocontrol.client.test.endpoint.systems.stringboxes;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;

import com.meteocontrol.client.params.BulkResponseFormat;
import com.meteocontrol.client.params.bulkCsv.CsvDecimalPoint;
import com.meteocontrol.client.params.bulkCsv.CsvDelimiter;
import com.meteocontrol.client.test.TestUtils;
import com.meteocontrol.client.models.bulk.BulkMeasurements;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.params.Resolutions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StringboxesTest {
    @Mock
    private HttpClient http;
    private System systemEndpoint;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        ApiClient api = new ApiClient(http);
        this.systemEndpoint = api.system("ABCDE");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetBulk.json"
        );

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);

        BulkMeasurements measurement = systemEndpoint.stringboxes().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(
                ApiMethods.GET,
                "/systems/ABCDE/stringboxes/bulk/measurements",
                criteria.getAsList(),
                null
        );

        assertEquals(expectedJson, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-30T06:00:00+09:00"))
                .withDateTo(simpleDate.parse("2016-10-30T06:05:00+09:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.stringboxes().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/stringboxes/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedData, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormatButWrongParameter()
            throws ParseException, IOException, IllegalArgumentException {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Delimiter and decimal point symbols can't be the same");

        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-30T06:00:00+09:00"))
                .withDateTo(simpleDate.parse("2016-10-30T06:05:00+09:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV)
                .withDecimalPoint(CsvDecimalPoint.DECIMAL_POINT_COLON)
                .withDelimiter(CsvDelimiter.DELIMITER_COLON);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.stringboxes().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/stringboxes/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
