package com.meteocontrol.client.test.endpoint.systems.stringboxes;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;

import com.meteocontrol.client.models.*;
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

import static org.junit.Assert.assertArrayEquals;
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
    public void testGetStringboxes() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetStringboxes.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes", null, null))
                .thenReturn(expectedJson);

        Stringbox[] stringboxes = systemEndpoint.stringboxes().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/stringboxes", null, null);

        Stringbox[] expectedStringbox = new Stringbox[]{
                new Stringbox("12345", "stringbox1"),
                new Stringbox("12346", "stringbox2"),
        };

        assertArrayEquals(expectedStringbox, stringboxes);
    }

    @Test
    public void testGetSingleStringbox() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetSingleStringbox.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/12345", null, null))
                .thenReturn(expectedJson);
        StringboxDetail stringboxDetail = systemEndpoint.stringbox("12345").get();
        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/12345", null, null);
        StringboxDetail expectedStringboxDetail = new StringboxDetail(
                "12345", "stringbox1", "abcd", 2.0f
        );

        assertEquals(expectedStringboxDetail.getName(), stringboxDetail.getName());
        assertEquals(expectedStringboxDetail.getId(), stringboxDetail.getId());
        assertEquals(expectedStringboxDetail.getSerial(), stringboxDetail.getSerial());
        assertEquals(expectedStringboxDetail.getScaleFactor(), stringboxDetail.getScaleFactor(), 0.01f);
    }

    @Test
    public void testGetStringboxAbbreviations() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetAbbreviations.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/12345/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.stringbox("12345").abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/12345/abbreviations", null, null);
        String[] expectedAbbreviations = new String[]{
                "D_IN2",
                "I1",
                "I1_N",
                "I2",
                "I2_N",
                "STATE",
                "T1",
                "U_DC"
        };
        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetSingleStringboxAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetAbbreviation.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/stringboxes/12345/abbreviations/I1", null, null))
                .thenReturn(expectedJson);

        Abbreviation stringboxAbbreviation = systemEndpoint.stringbox("12345").abbreviation("I1").get();

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/stringboxes/12345/abbreviations/I1",
                null,
                null);

        Abbreviation expectedAbbreviation = new Abbreviation(
                "AVG",
                null,
                "Current DC",
                "A"
        );
        assertEquals(expectedAbbreviation.getAggregation(), stringboxAbbreviation.getAggregation());
        assertEquals(expectedAbbreviation.getUnit(), stringboxAbbreviation.getUnit());
        assertEquals(expectedAbbreviation.getPrecision(), stringboxAbbreviation.getPrecision());
        assertEquals(expectedAbbreviation.getDescription(), stringboxAbbreviation.getDescription());
    }


    @Test
    public void testGetMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetMeasurements.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.DAY,
                "2016-01-01T11:00:00+02:00",
                "2016-01-02T11:15:00+02:00"
        );
        when(
                http.execute(
                        ApiMethods.GET,
                        "/systems/ABCDE/stringboxes/12345/abbreviations/I1/measurements",
                        criteria.getAsList(),
                        null
                )
        ).thenReturn(expectedJson);

        DeviceMeasurement measurements = systemEndpoint.stringbox("12345")
                .abbreviation("I1")
                .measurements()
                .get(criteria);

        verify(http, times(1))
                .execute(
                        ApiMethods.GET,
                        "/systems/ABCDE/stringboxes/12345/abbreviations/I1/measurements",
                        criteria.getAsList(),
                        null
                );

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T11:00:00+02:00", "177.2"),
                TestUtils.createMeasurementValue("2016-01-01T11:15:00+02:00", "157.2")
        };

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("12345").getValuesByAbbreviation("I1")
        );
    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/stringboxes/GetBulk.json"
        );

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-09-01T10:00:00+02:00",
                "2016-09-01T10:15:00+02:00"
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
        criteria.withDateFrom(simpleDate.parse("2016-09-01T10:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-09-01T10:15:00+02:00"))
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
        criteria.withDateFrom(simpleDate.parse("2016-09-01T10:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-09-01T10:15:00+02:00"))
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
