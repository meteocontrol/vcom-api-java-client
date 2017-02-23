package com.meteocontrol.client.test;

import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.MeasurementValue;
import com.meteocontrol.client.params.Resolutions;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class TestUtils {


    public static String readJsonFile(String path) {
        InputStream stream = TestUtils.class.getClass().getResourceAsStream(path);
        return new Scanner(stream).useDelimiter("\\Z").next();
    }

    public static MeasurementsCriteria getCriteria(
            Resolutions resolution,
            String from,
            String to
    ) throws ParseException {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        MeasurementsCriteria criteria = new MeasurementsCriteria();
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        criteria.withDateFrom(simpleDate.parse(from))
                .withDateTo(simpleDate.parse(to))
                .withResolution(resolution);
        return criteria;
    }

    public static MeasurementValue createMeasurementValue(String date, String vlaue) throws ParseException {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new MeasurementValue(simpleDate.parse(date), vlaue);
    }
}
