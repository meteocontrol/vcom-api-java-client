package com.meteocontrol.client.test.model;

import com.meteocontrol.client.models.Abbreviation;
import com.meteocontrol.client.models.AttachmentFile;
import org.junit.Assert;
import org.junit.Test;

public class BaseModelTest {

    @Test
    public void hashCodeTest() {
        Abbreviation testClass1 = new Abbreviation("1", "2", "3", "4", "5");
        Abbreviation testClass2 = new Abbreviation("1", "2", "3", "4", "5");
        Abbreviation testClass3 = new Abbreviation("1", "2", "3", "4", "6");

        Assert.assertEquals(testClass1.hashCode(), testClass2.hashCode());
        Assert.assertNotEquals(testClass2.hashCode(), testClass3.hashCode());
        Assert.assertNotEquals(testClass1.hashCode(), testClass3.hashCode());

        Assert.assertNotSame(testClass1, testClass2);
        Assert.assertNotSame(testClass2, testClass3);
    }

    @Test
    public void hashCodeWithNonPrimitiveTypeTest() {
        AttachmentFile testClass1 = new AttachmentFile("1", "2");
        AttachmentFile testClass2 = new AttachmentFile("1", "2");
        AttachmentFile testClass3 = new AttachmentFile("1", "3");

        Assert.assertEquals(testClass1.hashCode(), testClass2.hashCode());
        Assert.assertNotEquals(testClass2.hashCode(), testClass3.hashCode());
        Assert.assertNotEquals(testClass1.hashCode(), testClass3.hashCode());

        Assert.assertNotSame(testClass1, testClass2);
        Assert.assertNotSame(testClass2, testClass3);
    }
}
