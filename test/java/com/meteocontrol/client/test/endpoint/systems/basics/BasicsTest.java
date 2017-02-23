package com.meteocontrol.client.test.endpoint.systems.basics;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.Abbreviation;
import com.meteocontrol.client.models.MeasurementValue;
import com.meteocontrol.client.models.bulk.BulkMeasurements;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.params.BulkResponseFormat;
import com.meteocontrol.client.params.Resolutions;
import com.meteocontrol.client.params.bulkCsv.CsvDecimalPoint;
import com.meteocontrol.client.params.bulkCsv.CsvDelimiter;
import com.meteocontrol.client.test.TestUtils;
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
import static org.mockito.Mockito.*;

public class BasicsTest {
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
    public void testGetSystemsBasicsAbbreviations() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/systems/basics/getBasicsAbbreviations.json");

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/basics/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.basics().abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/basics/abbreviations", null, null);

        String[] expectedAbbreviations = new String[]{
                "E_Z_PV1",
                "E_Z_EVU",
                "G_M0"
        };

        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetBasicsSingleAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/systems/basics/getBasicsSingleAbbreviation.json");

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/basics/abbreviations/E_Z_EVU", null, null))
                .thenReturn(expectedJson);

        Abbreviation abbreviation = systemEndpoint.basics().abbreviation("E_Z_EVU").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/basics/abbreviations/E_Z_EVU", null, null);

        assertEquals("SUM", abbreviation.getAggregation());
        assertEquals("Energie aus I-checker", abbreviation.getDescription());
        assertEquals(null, abbreviation.getPrecision());
        assertEquals("kWh", abbreviation.getUnit());
        assertEquals("SUM", abbreviation.getComprehension());
    }

    @Test
    public void testGetSystemsMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/systems/basics/GetMeasurements.json");

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );

        MeasurementValue[] expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-30T06:00:00+09:00", "60.52"),
                TestUtils.createMeasurementValue("2016-10-30T06:05:00+09:00", "56.712")
        };

        when(
            http.execute(
                ApiMethods.GET,
                "/systems/ABCDE/basics/abbreviations/E_Z_EVU/measurements",
                criteria.getAsList(),
                null
            )
        ).thenReturn(expectedJson);

        MeasurementValue[] measurements = systemEndpoint.basics().abbreviation("E_Z_EVU").measurements().get(criteria);
        verify(
                http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/basics/abbreviations/E_Z_EVU/measurements",
                criteria.getAsList(),
                null
        );
        assertArrayEquals(expectedMeasurementValues, measurements);
    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/systems/basics/GetBulk.json");

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/basics/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurements = systemEndpoint.basics().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(
                ApiMethods.GET,
                "/systems/ABCDE/basics/bulk/measurements",
                criteria.getAsList(),
                null
        );
        assertEquals(expectedJson, measurements.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/basics/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-30T06:00:00+09:00"))
                .withDateTo(simpleDate.parse("2016-10-30T06:05:00+09:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/basics/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.basics().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/basics/bulk/measurements",
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
                "/responses/systems/basics/GetBulk.csv"
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
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/basics/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.basics().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/basics/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
