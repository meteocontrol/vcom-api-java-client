package com.meteocontrol.client.test.endpoint.session;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.test.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SessionTest {
    @Mock
    private HttpClient http;
    private ApiClient client;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        this.client = new ApiClient(http);
    }

    @Test
    public void testGetSystem() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/GetSession.json");

        when(http.execute(ApiMethods.GET, "/session", null, null))
                .thenReturn(expectedJson);

        Session session = this.client.session().get();
        verify(http, times(1)).execute(ApiMethods.GET, "/session", null, null);

        assertEquals(getExpectedSession(), session);
    }

    private Session getExpectedSession() {
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

        return new Session(user);
    }
}
