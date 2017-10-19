package com.meteocontrol.client.test.endpoint.tickets;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.Factory;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.endpoints.sub.tickets.TicketHistories;
import com.meteocontrol.client.filters.TicketsCriteria;
import com.meteocontrol.client.models.Ticket;
import com.meteocontrol.client.models.TicketHistory;
import com.meteocontrol.client.params.*;
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

public class TicketsTest {
    @Mock
    private HttpClient http;
    private ApiClient client;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        this.client = new ApiClient(http);
    }

    @Test
    public void testGetTickets() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetTickets.json");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        TicketsCriteria ticketsCriteria = new TicketsCriteria();
        ticketsCriteria.withDateFrom(simpleDate.parse("2016-01-01T00:00:00+02:00"))
                .withDateTo(simpleDate.parse("2016-03-01T01:00:00+02:00"))
                .withLastChangeFrom(simpleDate.parse("2016-01-01T12:00:00+02:00"))
                .withLastChangeTo(simpleDate.parse("2016-02-21T12:00:00+02:00"))
                .withRectifiedOnFrom(simpleDate.parse("2016-01-01T14:00:00+02:00"))
                .withRectifiedOnTo(simpleDate.parse("2016-02-20T14:00:00+02:00"));


        when(http.execute(ApiMethods.GET, "/tickets", ticketsCriteria.getAsList(), null))
                .thenReturn(expectedJson);

        Ticket[] tickets = client.tickets.find(ticketsCriteria);

        verify(http, times(1)).execute(ApiMethods.GET, "/tickets", ticketsCriteria.getAsList(), null);

        Ticket[] expectedTickets = new Ticket[]{
                new Ticket(
                        123,
                        "ABCDE",
                        "Ticket #123",
                        "This is a description.",
                        "This is a summary.",
                        simpleDate.parse("2016-01-01T12:00:00+02:00"),
                        simpleDate.parse("2016-01-01T13:00:00+02:00"),
                        null,
                        null,
                        TicketStatus.CLOSED,
                        null,
                        TicketPriority.NORMAL,
                        null,
                        null,
                        TicketSeverity.NORMAL
                ),
                new Ticket(
                        456,
                        "FGHIJ",
                        "Ticket #456",
                        "This is a description.",
                        "This is a summary.",
                        simpleDate.parse("2016-02-01T12:00:00+02:00"),
                        simpleDate.parse("2016-02-02T13:00:00+02:00"),
                        null,
                        null,
                        TicketStatus.INPROGRESS,
                        null,
                        TicketPriority.HIGH,
                        null,
                        null,
                        null
                )
        };

        assertArrayEquals(expectedTickets, tickets);
    }

    @Test
    public void testGetTicketsWithMultipleParametersInFilter() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetTickets2.json");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        TicketsCriteria ticketsCriteria = new TicketsCriteria();
        ticketsCriteria.withDateFrom(simpleDate.parse("2016-01-01T00:00:00+00:00"))
                .withDateTo(simpleDate.parse("2016-03-01T01:00:00+00:00"))
                .withLastChangeFrom(simpleDate.parse("2016-01-01T12:00:00+00:00"))
                .withLastChangeTo(simpleDate.parse("2016-02-21T12:00:00+00:00"))
                .withRectifiedOnFrom(simpleDate.parse("2016-01-01T14:00:00+00:00"))
                .withRectifiedOnTo(simpleDate.parse("2016-02-20T14:00:00+00:00"))
                .withStatus(new TicketStatus[]{TicketStatus.CLOSED, TicketStatus.INPROGRESS})
                .withPriority(new TicketPriority[] {TicketPriority.NORMAL, TicketPriority.HIGH})
                .withSeverity(new TicketSeverity[] {TicketSeverity.NORMAL, TicketSeverity.HIGH})
                .withSystemKey(new String[] {"ABCDE", "FGHIJ"})
                .withAssignee(new String[] {"Tom", "John"})
                .withIncludeInReports(
                        new TicketIncludedInReportType[] {
                                TicketIncludedInReportType.SUMMARY,
                                TicketIncludedInReportType.DETAIL
                        }
                );

        when(http.execute(ApiMethods.GET, "/tickets", ticketsCriteria.getAsList(), null))
                .thenReturn(expectedJson);

        Ticket[] tickets = client.tickets.find(ticketsCriteria);

        verify(http, times(1)).execute(ApiMethods.GET, "/tickets", ticketsCriteria.getAsList(), null);

        Ticket[] expectedTickets = new Ticket[]{
                new Ticket(
                        123,
                        "ABCDE",
                        "Ticket #123",
                        "This is a description.",
                        "This is a summary.",
                        simpleDate.parse("2016-01-01T12:00:00+02:00"),
                        simpleDate.parse("2016-01-01T13:00:00+02:00"),
                        null,
                        null,
                        TicketStatus.CLOSED,
                        null,
                        TicketPriority.NORMAL,
                        null,
                        null,
                        TicketSeverity.NORMAL
                ),
                new Ticket(
                        456,
                        "FGHIJ",
                        "Ticket #456",
                        "This is a description.",
                        "This is a summary.",
                        simpleDate.parse("2016-02-01T12:00:00+02:00"),
                        simpleDate.parse("2016-02-02T13:00:00+02:00"),
                        null,
                        null,
                        TicketStatus.INPROGRESS,
                        null,
                        TicketPriority.HIGH,
                        null,
                        null,
                        TicketSeverity.HIGH
                )
        };

        assertArrayEquals(expectedTickets, tickets);
    }

    @Test
    public void testGetSingleTicket() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetTicket.json");

        when(http.execute(ApiMethods.GET, "/tickets/123", null, null))
                .thenReturn(expectedJson);

        Ticket ticket = this.client.ticket("123").get();
        verify(http, times(1)).execute(ApiMethods.GET, "/tickets/123", null, null);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        Ticket expectedTicket = new Ticket(
                123,
                "ABCDE",
                "Ticket #123",
                "This is a description.",
                "This is a summary.",
                simpleDate.parse("2016-01-01T12:00:00+02:00"),
                simpleDate.parse("2016-01-01T13:00:00+02:00"),
                simpleDate.parse("2016-01-01T14:00:00+02:00"),
                null,
                TicketStatus.INPROGRESS,
                10,
                TicketPriority.NORMAL,
                TicketIncludedInReportType.NO,
                true,
                TicketSeverity.NORMAL
        );

        assertEquals(expectedTicket, ticket);
    }

    @Test
    public void testUpdateTicket() throws ParseException, IOException {
        String bodyJson = TestUtils.readJsonFile("/responses/tickets/UpdateTicketBody.json");
        when(http.execute(ApiMethods.PATCH, "/tickets/123", null, bodyJson)).thenReturn("");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        Ticket ticket = new Ticket(
                123,
                "ABCDE",
                "Ticket #123",
                "This is a description.",
                "This is a summary.",
                simpleDate.parse("2016-01-01T12:00:00+00:00"),
                simpleDate.parse("2016-01-01T13:00:00+00:00"),
                simpleDate.parse("2016-01-01T14:00:00+00:00"),
                null,
                TicketStatus.INPROGRESS,
                10,
                TicketPriority.NORMAL,
                TicketIncludedInReportType.NO,
                true,
                TicketSeverity.NORMAL
        );

        this.client.ticket("123").update(ticket);
        verify(http, times(1)).execute(ApiMethods.PATCH, "/tickets/123", null, bodyJson);
    }

    @Test
    public void testUpdateTicketWhichQueryParamAreNotUTC() throws ParseException, IOException {
        String bodyJson = TestUtils.readJsonFile("/responses/tickets/UpdateTicketBody.json");
        when(http.execute(ApiMethods.PATCH, "/tickets/123", null, bodyJson)).thenReturn("");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        Ticket ticket = new Ticket(
                123,
                "ABCDE",
                "Ticket #123",
                "This is a description.",
                "This is a summary.",
                simpleDate.parse("2016-01-01T14:00:00+02:00"),
                simpleDate.parse("2016-01-01T15:00:00+02:00"),
                simpleDate.parse("2016-01-01T16:00:00+02:00"),
                null,
                TicketStatus.INPROGRESS,
                10,
                TicketPriority.NORMAL,
                TicketIncludedInReportType.NO,
                true,
                TicketSeverity.NORMAL
        );

        this.client.ticket("123").update(ticket);
        verify(http, times(1)).execute(ApiMethods.PATCH, "/tickets/123", null, bodyJson);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTicketWithoutRequiredValue() throws ParseException, IOException {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        Ticket ticket = new Ticket(
                123,
                "ABCDE",
                "",
                "This is a description.",
                "This is a summary.",
                simpleDate.parse("2016-01-01T12:00:00+00:00"),
                simpleDate.parse("2016-01-01T13:00:00+00:00"),
                simpleDate.parse("2016-01-01T14:00:00+00:00"),
                null,
                TicketStatus.INPROGRESS,
                10,
                TicketPriority.NORMAL,
                TicketIncludedInReportType.NO,
                true,
                TicketSeverity.NORMAL
        );

        this.client.ticket("123").update(ticket);
    }

    @Test
    public void testCreateTicket() throws ParseException, IOException {
        String bodyJson = TestUtils.readJsonFile("/responses/tickets/CreateTicketBody.json");
        String responseJson = TestUtils.readJsonFile("/responses/tickets/CreateTicket.json");
        when(http.execute(ApiMethods.POST, "/tickets", null, bodyJson)).thenReturn(responseJson);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        Ticket ticket = new Ticket(
                123,
                "ABCDE",
                "Ticket #123",
                "This is a description.",
                "This is a summary.",
                simpleDate.parse("2016-01-01T12:00:00+00:00"),
                simpleDate.parse("2016-01-01T13:00:00+00:00"),
                simpleDate.parse("2016-01-01T14:00:00+00:00"),
                null,
                TicketStatus.INPROGRESS,
                10,
                TicketPriority.NORMAL,
                TicketIncludedInReportType.NO,
                true,
                TicketSeverity.NORMAL
        );

        int ticketId = this.client.tickets.create(ticket);
        verify(http, times(1)).execute(ApiMethods.POST, "/tickets", null, bodyJson);

        assertEquals(123, ticketId);
    }

    @Test
    public void testDeleteTicket() throws ParseException, IOException {
        when(http.execute(ApiMethods.DELETE, "/tickets/123", null, null)).thenReturn("");

        this.client.ticket("123").delete();
        verify(http, times(1)).execute(ApiMethods.DELETE, "/tickets/123", null, null);
    }

    @Test
    public void testGetTicketHistories() throws ParseException, IOException {
        String responseJson = TestUtils.readJsonFile("/responses/tickets/GetTicketHistories.json");
        when(http.execute(ApiMethods.GET, "/tickets/123/histories", null, null)).thenReturn(responseJson);

        TicketHistory[] ticketHistories = this.client.ticket("123").histories().get();
        verify(http, times(1)).execute(ApiMethods.GET, "/tickets/123/histories", null, null);

        assertEquals(3, ticketHistories.length);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        TicketHistory[] expectedHistories = new TicketHistory[] {
                new TicketHistory(
                        simpleDate.parse("2017-08-31T01:42:03+00:00"),
                        "statusChanged",
                        "userB",
                        "open",
                        "inProgress"
                ),
                new TicketHistory(
                        simpleDate.parse("2017-08-31T02:18:51+00:00"),
                        "assigneeChanged",
                        "userB",
                        null,
                        "userA"
                ),
                new TicketHistory(
                        simpleDate.parse("2017-08-31T02:19:41+00:00"),
                        "assigneeChanged",
                        "userB",
                        "userA",
                        "userB"
                )
        };
        assertArrayEquals(expectedHistories, ticketHistories);
    }
}
