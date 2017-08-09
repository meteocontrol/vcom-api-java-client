package com.meteocontrol.client.test.endpoint.systems;


import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.filters.UserCriteria;
import com.meteocontrol.client.models.User;
import com.meteocontrol.client.models.UserDetail;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.test.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UsersTest {
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
    public void testGetUsers() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/users/GetUsers.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/users", null, null))
                .thenReturn(expectedJson);

        User[] actualUsers = this.systemEndpoint.users().get();
        User[] expectedUsers = new User[]{
                new User("123", "test", "vcom-api", "e2e test user"),
                new User("1234", "mc-admin", "meteocontrol", "Administrator")
        };
        assertArrayEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetSingleUserById() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/users/GetSingleUser.json"
        );

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/users/91366", null, null))
                .thenReturn(expectedJson);

        UserDetail actualUser = this.systemEndpoint.user("91366").get();

        assertEquals("123", actualUser.getId());
        assertEquals("Mr.", actualUser.getTitle());
        assertEquals("vcom-api", actualUser.getFirstName());
        assertEquals("e2e test user", actualUser.getLastName());
        assertEquals("vcom-api-e2e-test-user", actualUser.getUsername());
        assertEquals("invalid@intranet.ba", actualUser.getEmail());
        assertEquals("de", actualUser.getLanguage());
        assertEquals("meteocontrol", actualUser.getCompany());
        assertEquals("123456", actualUser.getFax());
        assertEquals("123456", actualUser.getTelephone());
        assertEquals("654321", actualUser.getCellphone());
        assertEquals("Berlin", actualUser.getAddress().getCity());
        assertEquals("Germany", actualUser.getAddress().getCountry());
        assertEquals("AAA", actualUser.getAddress().getStreet());
        assertEquals("BBB", actualUser.getAddress().getStreetAddition());
        assertEquals("123", actualUser.getAddress().getPostalCode());
        assertEquals("Europe/Berlin", actualUser.getTimezone().getName());
        assertEquals("+02:00", actualUser.getTimezone().getUtcOffset());
    }

    @Test
    public void testGetSingleUserByName() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile(
                "/responses/systems/users/GetSingleUser.json"
        );

        UserCriteria userCriteria = new UserCriteria();
        userCriteria = userCriteria.withUserName("vcom-api-e2e-test-user");

        when(http.execute(ApiMethods.GET, "/systems/ABCDE/users", userCriteria.getAsList(), null))
                .thenReturn(expectedJson);

        UserDetail actualUser = this.systemEndpoint.users().get(userCriteria);

        assertEquals("123", actualUser.getId());
        assertEquals("Mr.", actualUser.getTitle());
        assertEquals("vcom-api", actualUser.getFirstName());
        assertEquals("e2e test user", actualUser.getLastName());
        assertEquals("vcom-api-e2e-test-user", actualUser.getUsername());
        assertEquals("invalid@intranet.ba", actualUser.getEmail());
        assertEquals("de", actualUser.getLanguage());
        assertEquals("meteocontrol", actualUser.getCompany());
        assertEquals("123456", actualUser.getFax());
        assertEquals("123456", actualUser.getTelephone());
        assertEquals("654321", actualUser.getCellphone());
        assertEquals("Berlin", actualUser.getAddress().getCity());
        assertEquals("Germany", actualUser.getAddress().getCountry());
        assertEquals("AAA", actualUser.getAddress().getStreet());
        assertEquals("BBB", actualUser.getAddress().getStreetAddition());
        assertEquals("123", actualUser.getAddress().getPostalCode());
        assertEquals("Europe/Berlin", actualUser.getTimezone().getName());
        assertEquals("+02:00", actualUser.getTimezone().getUtcOffset());
    }
}
