package com.meteocontrol.client.test.endpoint.systems.technicalData;

import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.models.TechnicalData;
import com.meteocontrol.client.models.TechnicalDevice;
import com.meteocontrol.client.params.ApiMethods;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TechnicalDataTest {
    @Mock
    private HttpClient http;
    private System systemEndpoint;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        ApiClient api = new ApiClient(http);
        this.systemEndpoint = api.system("ABCDE");
    }

    @Test
    public void testGetTechnicalData() throws ParseException, IOException {
        String expectedJson = com.meteocontrol.client.test.TestUtils.readJsonFile(
                "/responses/systems/technical-data/GetTechnicalData.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/technical-data", null, null))
                .thenReturn(expectedJson);

        TechnicalData technicalData = systemEndpoint.technicalData().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE/technical-data", null, null);

        TechnicalDevice[] expectedPanels = new TechnicalDevice[] {
                new TechnicalDevice("01 - Unknown", "Standard poly", "720"),
                new TechnicalDevice("01 - Unknown", "Standard poly2", "720")
        };

        TechnicalDevice[] expectedInverters = new TechnicalDevice[] {
                new TechnicalDevice("TMEIC", "PVL-L0250", "3"),
                new TechnicalDevice("TMEIC2", "PVL-L0251", "3")
        };

        assertArrayEquals(expectedPanels, technicalData.getAllPanels());
        assertArrayEquals(expectedInverters, technicalData.getAllInverters());
    }
}
