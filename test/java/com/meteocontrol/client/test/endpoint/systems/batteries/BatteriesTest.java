package com.meteocontrol.client.test.endpoint.systems.batteries;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.endpoints.sub.systems.System;
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
import static org.mockito.Mockito.*;

public class BatteriesTest {
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
    public void testGetBatteries() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetBatteries.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries", null, null))
                .thenReturn(expectedJson);

        Battery[] meters = systemEndpoint.batteries().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/batteries", null, null);

        Battery[] expectedBatteries = new Battery[]{
                new Battery("145103", ""),
                new Battery("145104", "")
        };

        assertArrayEquals(expectedBatteries, meters);
    }

    @Test
    public void testGetSingleBattery() throws ParseException, IOException {

        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetBattery.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103", null, null))
                .thenReturn(expectedJson);

        BatteryDetail batteryDetail = systemEndpoint.battery("145103").get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103", null, null);

        BatteryDetail expectedDetail = new BatteryDetail("145103", "", "bat1");

        assertEquals(expectedDetail.getId(), batteryDetail.getId());
        assertEquals(expectedDetail.getName(), batteryDetail.getName());
        assertEquals(expectedDetail.getAddress(), batteryDetail.getAddress());
    }

    @Test
    public void testGetBatteryAbbreviations() throws ParseException, IOException {

        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetAbbreviations.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemEndpoint.battery("145103").abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103/abbreviations", null, null);

        String[] expectedAbbreviations = new String[]{
                "B_CHARGE_LEVEL",
                "B_E_EXP",
                "B_E_IMP",
                "T1"
        };

        assertEquals(expectedAbbreviations.length, abbreviations.length);
        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetBatterySingleAbbreviation() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetAbbreviation.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103/abbreviations/B_CHARGE_LEVEL", null, null))
                .thenReturn(expectedJson);

        Abbreviation abbreviation = systemEndpoint.battery("145103").abbreviation("B_CHARGE_LEVEL").get();
        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/batteries/145103/abbreviations/B_CHARGE_LEVEL", null, null);
        Abbreviation expectedAbbreviation = new Abbreviation("AVG", "2", "Charging status", "%");
        assertEquals(expectedAbbreviation.getAggregation(), abbreviation.getAggregation());
        assertEquals(expectedAbbreviation.getDescription(), abbreviation.getDescription());
        assertEquals(expectedAbbreviation.getPrecision(), abbreviation.getPrecision());
        assertEquals(expectedAbbreviation.getUnit(), abbreviation.getUnit());
    }

    @Test
    public void testGetBatteryMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetMeasurements.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-10T11:00:00+02:00",
                "2016-10-10T11:15:00+02:00"
        );
        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/145103/abbreviations/B_CHARGE_LEVEL/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);

        DeviceMeasurement measurements = systemEndpoint.battery("145103")
                .abbreviation("B_CHARGE_LEVEL")
                .measurements()
                .get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/145103/abbreviations/B_CHARGE_LEVEL/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-10T11:00:00+02:00", "80.762"),
                TestUtils.createMeasurementValue("2016-10-10T11:05:00+02:00", "80.782"),
                TestUtils.createMeasurementValue("2016-10-10T11:10:00+02:00", "80.802"),
                TestUtils.createMeasurementValue("2016-10-10T11:15:00+02:00", "80.822")
        };
        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("145103").getValuesByAbbreviation("B_CHARGE_LEVEL")
        );
    }

    @Test
    public void testGetBatteryMeasurementsWithMultipleArguments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetMeasurements_multiple.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-10T11:00:00+02:00",
                "2016-10-10T11:15:00+02:00"
        );
        when(http.execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/145103,145104/abbreviations/B_CHARGE_LEVEL,B_E_EXP/measurements",
                criteria.getAsList(),
                null))
                .thenReturn(expectedJson);
        String[] deviceIds = new String[]{"145103", "145104"};
        String[] abbreviationsIds = new String[]{"B_CHARGE_LEVEL", "B_E_EXP"};
        DeviceMeasurement measurements = systemEndpoint.battery(deviceIds)
                .abbreviation(abbreviationsIds)
                .measurements()
                .get(criteria);

        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/145103,145104/abbreviations/B_CHARGE_LEVEL,B_E_EXP/measurements",
                criteria.getAsList(),
                null);

        MeasurementValue[] expectedMeasurementValues;
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-10-10T11:00:00+02:00", "80.762"),
                TestUtils.createMeasurementValue("2016-10-10T11:05:00+02:00", "80.782"),
                TestUtils.createMeasurementValue("2016-10-10T11:10:00+02:00", "80.802"),
                TestUtils.createMeasurementValue("2016-10-10T11:15:00+02:00", "80.822")
        };

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("145103").getValuesByAbbreviation("B_CHARGE_LEVEL")
        );
        expectedMeasurementValues[0] = TestUtils.createMeasurementValue("2016-10-10T11:00:00+02:00", "1347.772");
        expectedMeasurementValues[1] = TestUtils.createMeasurementValue("2016-10-10T11:05:00+02:00", "1347.792");
        expectedMeasurementValues[2] = TestUtils.createMeasurementValue("2016-10-10T11:10:00+02:00", "1347.812");
        expectedMeasurementValues[3] = TestUtils.createMeasurementValue("2016-10-10T11:15:00+02:00", "1347.832");

        assertArrayEquals(
                expectedMeasurementValues,
                measurements.getDeviceMeasurementByDeviceId("145104").getValuesByAbbreviation("B_E_EXP")
        );

    }

    @Test
    public void testGetBulkData() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetBulk.json"
        );
        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.INTERVAL,
                "2016-10-10T11:00:00+02:00",
                "2016-10-10T11:15:00+02:00"
        );
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);
        BulkMeasurements measurement = systemEndpoint.batteries().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/bulk/measurements",
                criteria.getAsList(),
                null);
        assertEquals(expectedJson, measurement.getAsString());
    }

    @Test
    public void testGetBulkDataWithCsvFormat() throws ParseException, IOException, IllegalArgumentException {
        String expectedData = TestUtils.readJsonFile(
                "/responses/systems/batteries/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-10T11:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-10-10T11:15:00+02:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV);
        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.batteries().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/bulk/measurements",
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
                "/responses/systems/batteries/GetBulk.csv"
        );
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        criteria.withDateFrom(simpleDate.parse("2016-10-10T11:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-10-10T11:15:00+02:00"))
                .withResolution(Resolutions.INTERVAL)
                .withFormat(BulkResponseFormat.FORMAT_CSV)
                .withDecimalPoint(CsvDecimalPoint.DECIMAL_POINT_COLON)
                .withDelimiter(CsvDelimiter.DELIMITER_COLON);

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/batteries/bulk/measurements", criteria.getAsList(), null))
                .thenReturn(expectedData);
        BulkMeasurements measurement = systemEndpoint.batteries().bulk().measurements().get(criteria);
        verify(http, times(1)).execute(ApiMethods.GET,
                "/systems/ABCDE/batteries/bulk/measurements",
                criteria.getAsList(),
                null);
    }
}
