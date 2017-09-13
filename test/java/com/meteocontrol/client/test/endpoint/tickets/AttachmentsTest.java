package com.meteocontrol.client.test.endpoint.tickets;

import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.HttpClient;
import com.meteocontrol.client.models.Attachment;
import com.meteocontrol.client.models.AttachmentFile;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.test.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AttachmentsTest {
    @Mock
    private HttpClient http;
    private ApiClient client;

    @Before
    public void setUp() throws Exception {
        http = Mockito.mock(HttpClient.class);
        this.client = new ApiClient(http);
    }

    @Test
    public void testGetAttachments() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetAttachments.json");

        when(http.execute(ApiMethods.GET, "/tickets/123/attachments", null, null))
                .thenReturn(expectedJson);

        Attachment[] attachments = client.ticket("123").attachments().get();

        verify(http, times(1))
                .execute(ApiMethods.GET, "/tickets/123/attachments", null, null);

        Attachment[] expectedAttachments = new Attachment[]{
                new Attachment(1234, "test.jpg"),
                new Attachment(5678, "test2.jpg")
        };

        assertArrayEquals(expectedAttachments, attachments);
    }

    @Test
    public void testGetAttachment() throws ParseException, IOException {
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/GetAttachment.json");
        when(http.execute(ApiMethods.GET, "/tickets/123/attachments/1234", null, null))
                .thenReturn(expectedJson);
        AttachmentFile attachment = client.ticket("123").attachment(1234).get();

        verify(http, times(1))
                .execute(ApiMethods.GET, "/tickets/123/attachments/1234", null, null);
        byte[] testContent = Files.readAllBytes(Paths.get("test/resources/responses/tickets/test.jpg"));
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        AttachmentFile expectedFile = new AttachmentFile("test.jpg", testContent, "test attachment");
        expectedFile.setId(1234);
        expectedFile.setCreatorId(12345);
        expectedFile.setCreated(simpleDate.parse("2017-08-29T03:22:23+00:00"));
        assertEquals(expectedFile, attachment);
    }

    @Test
    public void testGetCreateAttachment() throws ParseException, IOException {
        String expectedBody = TestUtils.readJsonFile("/responses/tickets/PostAttachment.json");
        String expectedJson = TestUtils.readJsonFile("/responses/tickets/PostAttachmentResult.json");
        byte[] testImageContent = Files.readAllBytes(Paths.get("test/resources/responses/tickets/test.jpg"));
        AttachmentFile expectedFile = new AttachmentFile("test.jpg", testImageContent, "test attachment");
        when(http.execute(ApiMethods.POST, "/tickets/123/attachments", null, expectedBody))
                .thenReturn(expectedJson);
        Attachment attachment = client.ticket("123").attachments().create(expectedFile);
        verify(http, times(1))
                .execute(ApiMethods.POST, "/tickets/123/attachments", null, expectedBody);
        assertEquals("test.jpg", attachment.getFilename());
        assertEquals(1234, attachment.getAttachmentId());
    }
}
