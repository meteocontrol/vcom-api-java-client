package com.meteocontrol.client.test.endpoint.systems.inverters;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.bulk.BulkMeasurements;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.params.BulkResponseFormat;
import com.meteocontrol.client.params.Resolutions;
import com.meteocontrol.client.params.bulkCsv.CsvDecimalPoint;
import com.meteocontrol.client.params.bulkCsv.CsvDelimiter;
import com.meteocontrol.client.test.TestParameters;
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

public class InvertersTest {
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
    public void testGetInverters() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetInverters.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters", null, null))
                .thenReturn(expectedJson);

        Inverter[] inverters = systemEndpoint.inverters().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/inverters", null, null);

        Inverter[] expectedInverters = new Inverter[]{
                new Inverter("Id86460.1", "PCS 2.C09.E18", "12837"),
                new Inverter("Id86460.2", "PCS 2.C09.E19", "12863"),
                new Inverter("Id86460.3", "TMEIC-Solar-Ware-1-192.168.30.92-1", "1"),
        };

        assertArrayEquals(expectedInverters, inverters);
    }

    @Test
    public void testGetSingleInverter() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetInverter.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/Id86460.1", null, null))
                .thenReturn(expectedJson);

        InverterDetail inverterDetail = systemEndpoint.inverter("Id86460.1").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/inverters/Id86460.1", null, null);

        InverterDetail expectedInverterDetail = new InverterDetail(
                "Id86460.1",
                "PVL-L0250",
                "TMEIC",
                "12837",
                "PCS 2.C09.E18",
                24.01f
        );

        assertEquals(expectedInverterDetail.getName(), inverterDetail.getName());
        assertEquals(expectedInverterDetail.getId(), inverterDetail.getId());
        assertEquals(expectedInverterDetail.getModel(), inverterDetail.getModel());
        assertEquals(expectedInverterDetail.getSerial(), inverterDetail.getSerial());
        assertEquals(expectedInverterDetail.getVendor(), inverterDetail.getVendor());
        assertEquals(
                expectedInverterDetail.getScaleFactor(),
                inverterDetail.getScaleFactor(),
                TestParameters.FLOAT_DELTA
        );
    }

    @Test
    public void testGetInverterAbbreviations() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetAbbreviations.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/Id86460.1/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.inverter("Id86460.1").abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/inverters/Id86460.1/abbreviations", null, null);
        String[] expectedAbbreviations = new String[]{
                "COS_PHI",
                "E_DAY",
                "E_INT",
                "E_INT_N",
                "E_TOTAL",
                "F_AC",
                "I_AC",
                "I_AC1",
                "I_AC2",
                "I_AC3",
                "I_DC",
                "P_AC",
                "P_AC_N",
                "P_DC",
                "U_AC_L1L2",
                "U_AC_L2L3",
                "U_AC_L3L1",
                "U_DC"
        };
        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetSingleInverterAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetAbbreviation.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/Id86460.1/abbreviations/E_DAY", null, null))
                .thenReturn(expectedJson);

        Abbreviation inverterAbbreviation = systemEndpoint.inverter("Id86460.1").abbreviation("E_DAY").get();

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/inverters/Id86460.1/abbreviations/E_DAY",
                null,
                null);

        Abbreviation expectedAbbreviation = new Abbreviation(
                "MAX",
                "2",
                "Energy generated per day",
                "kWh"
        );
        assertEquals(expectedAbbreviation.getAggregation(), inverterAbbreviation.getAggregation());
        assertEquals(expectedAbbreviation.getUnit(), inverterAbbreviation.getUnit());
        assertEquals(expectedAbbreviation.getPrecision(), inverterAbbreviation.getPrecision());
        assertEquals(expectedAbbreviation.getDescription(), inverterAbbreviation.getDescription());
    }

    @Test
    public void testGetMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetMeasurements.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.DAY,
                "2016-01-01T11:00:00+02:00",
                "2016-01-02T23:59:59+02:00"
        );
        when(
                http.execute(
                        ApiMethods.GET,
                        "/systems/ABCDE/inverters/Id12345.1/abbreviations/E_INT/measurements",
                        criteria.getAsList(),
                        null
                )
        ).thenReturn(expectedJson);

        DeviceMeasurement measurements = systemEndpoint.inverter("Id12345.1")
                .abbreviation("E_INT")
                .measurements()
                .get(criteria);

        verify(http, times(1))
                .execute(
                        ApiMethods.GET,
                        "/systems/ABCDE/inverters/Id12345.1/abbreviations/E_INT/measurements",
                        criteria.getAsList(),
                        null
                );

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T11:00:00+02:00", "0.089"),
                TestUtils.createMeasurementValue("2016-01-01T11:15:00+02:00", "0.082"),
                TestUtils.createMeasurementValue("2016-01-01T11:30:00+02:00", "0.078"),
                TestUtils.createMeasurementValue("2016-01-01T11:45:00+02:00", "0.089"),
                TestUtils.createMeasurementValue("2016-01-01T12:00:00+02:00", "0.095"),
        };

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("Id12345.1").getValuesByAbbreviation("E_INT")
        );
    }

    @Test
    public void testGetMeasurementsWithMultipleArguments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetMeasurements_multiple.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+02:00",
                "2016-10-30T06:15:00+02:00"
        );
        when(
            http.execute(
                ApiMethods.GET,
                "/systems/ABCDE/inverters/Id86460.1,Id86460.2/abbreviations/E_DAY,E_INT/measurements",
                criteria.getAsList(),
                null
            )
        )
                .thenReturn(expectedJson);
        String[] deviceIds = new String[]{"Id86460.1", "Id86460.2"};
        String[] abbreviationsIds = new String[]{"E_DAY", "E_INT"};

        DeviceMeasurement measurements = systemEndpoint.inverter(deviceIds)
                .abbreviation(abbreviationsIds)
                .measurements()
                .get(criteria);

        verify(
                http,
                times(1)).execute(
                ApiMethods.GET,
                "/systems/ABCDE/inverters/Id86460.1,Id86460.2/abbreviations/E_DAY,E_INT/measurements",
                criteria.getAsList(),
                null
        );
        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-30T06:00:00+02:00", "31.566"),
                TestUtils.createMeasurementValue("2016-10-30T06:05:00+02:00", "29.992")
        };
        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("Id86460.2").getValuesByAbbreviation("E_INT")
        );
    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetBulk.json"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-09-01T10:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-09-01T10:15:00+02:00"))
                .withResolution(Resolutions.INTERVAL);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.inverters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/inverters/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedJson, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/inverters/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-09-01T10:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-09-01T10:15:00+02:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.inverters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/inverters/bulk/measurements",
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
                "/responses/systems/inverters/GetBulk.csv"
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
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/inverters/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.inverters().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/inverters/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
