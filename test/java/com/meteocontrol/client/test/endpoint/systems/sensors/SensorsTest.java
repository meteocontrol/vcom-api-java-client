package com.meteocontrol.client.test.endpoint.systems.sensors;

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
import java.util.HashMap;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SensorsTest {
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
    public void testGetSensors() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetSensors.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors", null, null))
                .thenReturn(expectedJson);

        Sensor[] sensors = systemEndpoint.sensors().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/sensors", null, null);

        Sensor[] expectedSensors = new Sensor[]{
                new Sensor("136863", "C09　Module Temp　モジュール温度計"),
                new Sensor("136864", "C09　Pyranometer SMP11　日射量計"),
                new Sensor("136865", "C09　Irradiation sensor SI-420TC　日射量計"),
                new Sensor("136866", "C09　Ambient Temp　温度計"),
                new Sensor("136908", " Irradiation sensor M&T / mc Si-420TC-T (4 - 20mA)"),
                new Sensor("136909", "Temperature sensor PT1000 sensor with integrated converter (0 - 10V)")
        };

        assertArrayEquals(expectedSensors, sensors);
    }

    @Test
    public void testGetSingleSensor() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetSensor.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863", null, null))
                .thenReturn(expectedJson);

        SensorDetail sensorDetail = systemEndpoint.sensor("136863").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863", null, null);

        SensorDetail expectedSensorDetail = new SensorDetail("136863", "C09　Module Temp　モジュール温度計", "11814");

        assertEquals(expectedSensorDetail.getId(), sensorDetail.getId());
        assertEquals(expectedSensorDetail.getName(), sensorDetail.getName());
        assertEquals(expectedSensorDetail.getAddress(), sensorDetail.getAddress());
    }

    @Test
    public void testGetAbbreviations() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetAbbreviations.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.sensor("136863").abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863/abbreviations", null, null);

        String[] expectedAbbreviations = new String[]{
                "SRAD",
                "STATE",
                "T"
        };

        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetSingleAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetAbbreviation.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863/abbreviations/SRAD", null, null))
                .thenReturn(expectedJson);

        Abbreviation abbreviation = systemEndpoint.sensor("136863").abbreviation("SRAD").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/sensors/136863/abbreviations/SRAD", null, null);

        Abbreviation expectedAbbreviation = new Abbreviation(
                "AVG",
                "3",
                "Irradiance",
                "W/m²"
        );

        assertEquals(expectedAbbreviation.getAggregation(), abbreviation.getAggregation());
        assertEquals(expectedAbbreviation.getDescription(), abbreviation.getDescription());
        assertEquals(expectedAbbreviation.getPrecision(), abbreviation.getPrecision());
        assertEquals(expectedAbbreviation.getUnit(), abbreviation.getUnit());
    }

    @Test
    public void testGetMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetMeasurements.json"
        );

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );

        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/136863/abbreviations/T/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);

        DeviceMeasurement measurements = systemEndpoint.sensor("136863").abbreviation("T").measurements().get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/136863/abbreviations/T/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-30T06:00:00+09:00", "21.43"),
                TestUtils.createMeasurementValue("2016-10-30T06:05:00+09:00", "20.922")
        };
        HashMap<String, MeasurementValue[]> expectedValue = new HashMap<String, MeasurementValue[]>();
        expectedValue.put("T", expectedMeasurementValues);

        assertArrayEquals(expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("136863").getValuesByAbbreviation("T"));

    }

    @Test
    public void testGetMeasurementsWithMultipleArguments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetMeasurements_multiple.json"
        );

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );
        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/136863,136864/abbreviations/T,SRAD/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);
        String[] deviceIds = new String[]{"136863", "136864"};
        String[] abbreviationsIds = new String[]{"T", "SRAD"};
        DeviceMeasurement measurements = systemEndpoint.sensor(deviceIds)
                .abbreviation(abbreviationsIds)
                .measurements()
                .get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/136863,136864/abbreviations/T,SRAD/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-30T06:00:00+09:00", "21.43"),
                TestUtils.createMeasurementValue("2016-10-30T06:05:00+09:00", "20.922")
        };
        HashMap<String, MeasurementValue[]> expectedValue = new HashMap<String, MeasurementValue[]>();
        expectedValue.put("T", expectedMeasurementValues);

        assertArrayEquals(expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("136863").getValuesByAbbreviation("T"));

    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetBulk.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-30T06:00:00+09:00",
                "2016-10-30T06:05:00+09:00"
        );
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.sensors().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedJson, measurement.getAsString());
    }


    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/sensors/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-30T06:00:00+09:00"))
                .withDateTo(simpleDate.parse("2016-10-30T06:05:00+09:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.sensors().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/bulk/measurements",
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
                "/responses/systems/sensors/GetBulk.csv"
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
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/sensors/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.sensors().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/sensors/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
