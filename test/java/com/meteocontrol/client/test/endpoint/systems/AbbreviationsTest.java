package com.meteocontrol.client.test.endpoint.systems;

import com.meteocontrol.client.endpoints.main.Systems;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.Measurement;
import com.meteocontrol.client.models.MeasurementValue;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.params.Resolutions;
import com.meteocontrol.client.test.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AbbreviationsTest {
    @Mock
    private HttpClient http;
    private Systems systemsEndpoint;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        ApiClient api = new ApiClient(http);
        this.systemsEndpoint = api.systems;
    }

    @Test
    public void testGetSystemsAbbreviations() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/GetAbbreviations.json");

        when(http.execute(ApiMethods.GET, "/systems/abbreviations", null, null))
                .thenReturn(expectedJson);

        String[] abbreviations = systemsEndpoint.abbreviations().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/abbreviations", null, null);

        String[] expectedAbbreviations = new String[]{
                "E_Z_EVU",
                "E_DAY",
                "G_M0"
        };

        assertArrayEquals(expectedAbbreviations, abbreviations);
    }

    @Test
    public void testGetSystemsMeasurements() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/GetMeasurements.json");

        MeasurementsCriteria criteria = TestUtils.getCriteria(
                Resolutions.DAY,
                "2016-01-01T00:00:00+00:00",
                "2016-01-02T23:59:59+00:00"
        );
        when(http.execute(ApiMethods.GET, "/systems/abbreviations/E_Z_EVU/measurements", criteria.getAsList(), null))
                .thenReturn(expectedJson);

        Measurement[] measurements = this.systemsEndpoint.abbreviation("E_Z_EVU").measurements().get(criteria);

        MeasurementValue[] expectedMeasurementValues;

        assertEquals(2, measurements.length);
        assertEquals("ABCDE", measurements[0].getSystemKey());
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T00:00:00Z", "52.182")
        };
        assertArrayEquals(expectedMeasurementValues, measurements[0].getValuesByAbbreviation("E_Z_EVU"));

        assertEquals("VWXYZ", measurements[1].getSystemKey());
        expectedMeasurementValues = new MeasurementValue[]{
                TestUtils.createMeasurementValue("2016-01-01T00:00:00Z", "199.175")
        };
        assertArrayEquals(expectedMeasurementValues, measurements[1].getValuesByAbbreviation("E_Z_EVU"));
    }
}
