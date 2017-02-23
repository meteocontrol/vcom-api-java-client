package com.meteocontrol.client.test.endpoint.systems;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.models.System;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.test.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SystemsTest {
    @Mock
    private HttpClient http;
    private ApiClient client;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        this.client = new ApiClient(http);
    }

    @Test
    public void testGetSystems() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/GetSystems.json");

        when(http.execute(ApiMethods.GET, "/systems", null, null))
                .thenReturn(expectedJson);

        System[] systems = client.systems.get();

        verify(http, times(1)).execute(ApiMethods.GET, "/systems", null, null);

        System[] expectedSystems = new System[] {
                new System("ABCDE", "Meteocontrol PV system"),
                new System("VWXYZ", "Meteocontrol PV system #2")
        };

        assertArrayEquals(expectedSystems, systems);
    }

    @Test
    public void testGetSystem() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/GetSystem.json");

        when(http.execute(ApiMethods.GET, "/systems/ABCDE", null, null))
                .thenReturn(expectedJson);

        SystemDetail system = this.client.system("ABCDE").get();
        verify(http, times(1)).execute(ApiMethods.GET, "/systems/ABCDE", null, null);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        SystemDetail expectedSystem = new SystemDetail(
                new Address("Augsburg", "DE", "86157", "Spicherer Straße 48"),
                480,
                simpleDate.parse("2016-01-28"),
                new Coordinates(48.3670191, 10.8681),
                "test",
                new Timezone("Europe/Berlin", "+01:00")
        );

        assertEquals(expectedSystem, system);
    }
}
