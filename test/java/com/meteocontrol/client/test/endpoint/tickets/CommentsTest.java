package com.meteocontrol.client.test.endpoint.tickets;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.models.Comment;
import com.meteocontrol.client.models.CommentDetail;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.test.TestUtils;
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

public class CommentsTest {
    @Mock
    private HttpClient http;
    private ApiClient client;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        this.client = new ApiClient(http);
    }

    @Test
    public void testGetComments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetComments.json");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        when(http.execute(ApiMethods.GET, "/tickets/123/comments", null, null))
                .thenReturn(expectedJson);

        Comment[] comments = client.ticket("123").comments().get();

        verify(http, times(1)).execute(ApiMethods.GET, "/tickets/123/comments", null, null);

        Comment[] expectedComments = new Comment[]{
                new Comment(661288, simpleDate.parse("2016-02-19T12:49:20+01:00"), "Comment text", "Username"),
                new Comment(661286, simpleDate.parse("2016-02-19T12:49:07+01:00"), "Comment text", "Username")
        };

        assertArrayEquals(expectedComments, comments);
    }

    @Test
    public void testGetSingleComment() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetComment.json");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        when(http.execute(ApiMethods.GET, "/tickets/123/comments/661288", null, null))
                .thenReturn(expectedJson);

        CommentDetail comment = client.ticket("123").comment(661288).get();

        verify(http, times(1)).execute(ApiMethods.GET, "/tickets/123/comments/661288", null, null);

        CommentDetail expectedComment = new CommentDetail(
                661288,
                simpleDate.parse("2016-02-19T12:49:20+01:00"),
                "Comment text",
                "Username"
        );

        assertEquals(expectedComment, comment);
    }

    @Test
    public void testUpdateComment() throws ParseException, IOException {
        String bodyJson = TestUtils.readJsonFile("/responses/tickets/UpdateCommentBody.json");
        when(http.execute(ApiMethods.PATCH, "/tickets/123/comments/661288", null, bodyJson)).thenReturn("");

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        CommentDetail comment = new CommentDetail(
                661288,
                simpleDate.parse("2016-02-19T12:49:20+01:00"),
                "New Comment",
                "Username"
        );

        this.client.ticket("123").comment(661288).update(comment);
        verify(http, times(1)).execute(ApiMethods.PATCH, "/tickets/123/comments/661288", null, bodyJson);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCommentWithoutRequiredValue() throws ParseException, IOException {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        CommentDetail comment = new CommentDetail(
                661288,
                simpleDate.parse("2016-02-19T12:49:20+01:00"),
                "",
                "Username"
        );

        this.client.ticket("123").comment(661288).update(comment);
    }

    @Test
    public void testCreateComment() throws ParseException, IOException {
        String bodyJson = TestUtils.readJsonFile("/responses/tickets/CreateCommentBody.json");
        String responseJson = TestUtils.readJsonFile("/responses/tickets/CreateComment.json");
        when(http.execute(ApiMethods.POST, "/tickets/123/comments", null, bodyJson)).thenReturn(responseJson);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

        CommentDetail comment = new CommentDetail(
                661288,
                simpleDate.parse("2016-02-19T12:49:20+01:00"),
                "New Comment",
                "Username"
        );

        int commentId = this.client.ticket("123").comments().create(comment);
        verify(http, times(1)).execute(ApiMethods.POST, "/tickets/123/comments", null, bodyJson);

        assertEquals(454548, commentId);
    }

    @Test
    public void testDeleteComment() throws ParseException, IOException {
        when(http.execute(ApiMethods.DELETE, "/tickets/123/comments/661288", null, null)).thenReturn("");

        this.client.ticket("123").comment(661288).delete();
        verify(http, times(1)).execute(ApiMethods.DELETE, "/tickets/123/comments/661288", null, null);
    }
}
