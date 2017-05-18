package com.meteocontrol.client.test.endpoint.systems.meters;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.*;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MetersTest {
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
    public void testGetMeters() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetMeters.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters", null, null))
                .thenReturn(expectedJson);

        Meter[] meters = systemEndpoint.meters().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/meters", null, null);

        Meter[] expectedMeters = new Meter[]{
                new Meter("129676", "Janitza Station 4"),
                new Meter("13155", "Janitza 110kV")
        };

        assertArrayEquals(expectedMeters, meters);
    }

    @Test
    public void testGetSingleMeter() throws ParseException, IOException {

        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetMeter.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/129676", null, null))
                .thenReturn(expectedJson);

        MeterDetail meterDetail = systemEndpoint.meter("129676").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/meters/129676", null, null);

        MeterDetail expectedDetail = new MeterDetail("129676", "Janitza Station 4", "192.168.178.241:1");

        assertEquals(expectedDetail.getId(), meterDetail.getId());
        assertEquals(expectedDetail.getName(), meterDetail.getName());
        assertEquals(expectedDetail.getAddress(), meterDetail.getAddress());
    }

    @Test
    public void testGetMeterAbbreviations() throws ParseException, IOException {

        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetAbbreviations.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/129676/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.meter("129676").abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/meters/129676/abbreviations", null, null);

        String[] expectedAbbreviations = new String[]{
                "E_INT",
                "M_AC_E_EXP",
                "M_AC_E_IMP",
                "M_AC_F",
                "M_AC_I1",
                "M_AC_I2",
                "M_AC_I3",
                "M_AC_P",
                "M_AC_P1",
                "M_AC_P2",
                "M_AC_P3",
                "M_AC_PF_COSPHI",
                "M_AC_Q",
                "M_AC_S",
                "M_AC_U_L1L2",
                "M_AC_U_L2L3",
                "M_AC_U_L3L1",
                "M_AC_U1",
                "M_AC_U2",
                "M_AC_U3"
        };

        assertEquals(expectedAbbreviations.length, abbreviations.length);
        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetMeterSingleAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetAbbreviation.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/129676/abbreviations/E_INT", null, null))
                .thenReturn(expectedJson);

        Abbreviation abbreviation = systemEndpoint.meter("129676").abbreviation("E_INT").get();
        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/meters/129676/abbreviations/E_INT", null, null);
        Abbreviation expectedAbbreviation = new Abbreviation("SUM", "3", "Energy generated per interval", "kWh");
        assertEquals(expectedAbbreviation.getAggregation(), abbreviation.getAggregation());
        assertEquals(expectedAbbreviation.getDescription(), abbreviation.getDescription());
        assertEquals(expectedAbbreviation.getPrecision(), abbreviation.getPrecision());
        assertEquals(expectedAbbreviation.getUnit(), abbreviation.getUnit());
    }

    @Test
    public void testGetMeterMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetMeasurements.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-01-01T11:00:00+02:00",
                "2016-01-01T11:15:00+02:00"
        );
        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/meters/129676/abbreviations/E_INT/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);

        DeviceMeasurement measurements = systemEndpoint.meter("129676")
                .abbreviation("E_INT")
                .measurements()
                .get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/meters/129676/abbreviations/E_INT/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T11:00:00+02:00", "0"),
                TestUtils.createMeasurementValue("2016-01-01T11:15:00+02:00", "0")
        };
        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("129676").getValuesByAbbreviation("E_INT")
        );
    }

    @Test
    public void testGetMeterMeasurementsWithMultipleArguments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetMeasurements_multiple.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-01-01T00:00:00+02:00",
                "2016-01-02T23:59:59+02:00"
        );
        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/meters/129676,67890/abbreviations/E_INT,M_AC_F/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);
        String[] deviceIds = new String[]{"129676", "67890"};
        String[] abbreviationsIds = new String[]{"E_INT", "M_AC_F"};
        DeviceMeasurement measurements = systemEndpoint.meter(deviceIds)
                .abbreviation(abbreviationsIds)
                .measurements()
                .get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/meters/129676,67890/abbreviations/E_INT,M_AC_F/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T11:00:00+02:00", "0.089"),
                TestUtils.createMeasurementValue("2016-01-01T11:15:00+02:00", "0.082")
        };

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("129676").getValuesByAbbreviation("E_INT")
        );
        expectedMeasurementValues[0] = TestUtils.createMeasurementValue("2016-01-01T11:00:00+02:00", "60");
        expectedMeasurementValues[1] = TestUtils.createMeasurementValue("2016-01-01T11:15:00+02:00", "65");

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("67890").getValuesByAbbreviation("M_AC_F")
        );

    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/meters/GetBulk.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-09-01T10:00:00+02:00",
                "2016-09-01T10:15:00+02:00"
        );
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.meters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/meters/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedJson, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/meters/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-09-01T10:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-09-01T10:15:00+02:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.meters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/meters/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedData, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormatButWrongParameter()
            throws ParseException, IOException, IllegalArgumentException {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Delimiter and decimal point symbols can't be the same");
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/meters/GetBulk.csv"
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

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/meters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.meters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/meters/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
