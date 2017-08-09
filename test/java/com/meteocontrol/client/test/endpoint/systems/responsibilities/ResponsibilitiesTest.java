package com.meteocontrol.client.test.endpoint.systems.responsibilities;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.models.ExtendedAddress;
import com.meteocontrol.client.models.Responsibilities;
import com.meteocontrol.client.models.Timezone;
import com.meteocontrol.client.models.UserDetail;
import com.meteocontrol.client.params.ApiMethods;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ResponsibilitiesTest {
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
                "/responses/systems/responsibilities/getResponsibilities.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/responsibilities", null, null))
                .thenReturn(expectedJson);

        Responsibilities responsibilities = systemEndpoint.responsibilities().get();

        verify(http, times(1)).execute(
                ApiMethods.GET, "/systems/ABCDE/responsibilities", null, null
        );

        assertEquals(getExpectedResponsibilities(), responsibilities);
    }

    private Responsibilities getExpectedResponsibilities() {
        ExtendedAddress address = new ExtendedAddress();
        address.setCity("City");
        address.setCountry("Country");
        address.setPostalCode("12345");
        address.setStreet("Street 123");
        address.setStreetAddition("2F-1");

        Timezone timezone = new Timezone();
        timezone.setName("Europe/Berlin");
        timezone.setUtcOffset("+02:00");

        UserDetail user = new UserDetail();
        user.setId("123");
        user.setTitle("Mr.");
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setUsername("user.name");
        user.setEmail("fake@mail.intra");
        user.setLanguage("en");
        user.setCompany("Company");
        user.setFax("0234567890");
        user.setTelephone("0234567891");
        user.setCellphone("0987654321");
        user.setAddress(address);
        user.setTimezone(timezone);


        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setOwner(user);
        responsibilities.setOperator(user);
        responsibilities.setElectrician(user);
        responsibilities.setInvoiceRecipient(user);
        responsibilities.setAlarmContact(user);
        return responsibilities;
    }
}
