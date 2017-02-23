package com.meteocontrol.client.filters;

import com.meteocontrol.client.exceptions.ApiClientException;
import com.meteocontrol.client.params.BulkResponseFormat;
import com.meteocontrol.client.params.Resolutions;
import com.meteocontrol.client.params.bulkCsv.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MeasurementsCriteria {
    private Map<String, String> filters;

    private SimpleDateFormat dateFormat;

    public MeasurementsCriteria() {
        this.filters = new HashMap<String, String>();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    }

    public Date getDateFrom() {
        try {
            return dateFormat.parse(this.filters.get("from"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public MeasurementsCriteria withDateFrom(Date from) {
        this.filters.put("from", dateFormat.format(from));
        return this;
    }

    public Date getDateTo() {
        try {
            return dateFormat.parse(this.filters.get("to"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public MeasurementsCriteria withDateTo(Date to) {
        this.filters.put("to", dateFormat.format(to));
        return this;
    }

    public Resolutions getResolution() {
        return Enum.valueOf(Resolutions.class, this.filters.get("resolution"));
    }

    public MeasurementsCriteria withResolution(Resolutions resolution) {
        this.filters.put("resolution", resolution.toString());
        return this;
    }

    public MeasurementsCriteria withFormat(BulkResponseFormat format) {
        this.filters.put("format", format.toString());
        return this;
    }

    public String getFormat() {
        return this.filters.get("format") != null ?
                this.filters.get("format") :
                BulkResponseFormat.FORMAT_JSON.toString();

    }

    public MeasurementsCriteria withLineBreak(CsvLineBreak lineBreak) {
        this.filters.put("lineBreak", lineBreak.toString());
        return this;
    }

    public String getLineBreak() {
        return this.filters.get("lineBreak") != null ?
                this.filters.get("lineBreak") :
                CsvLineBreak.LINE_BREAK_LF.toString();
    }

    public MeasurementsCriteria withDelimiter(CsvDelimiter delimiter) {
        this.filters.put("delimiter", delimiter.toString());
        return this;
    }

    public String getDelimiter() {
        return this.filters.get("delimiter") != null ?
                this.filters.get("delimiter") :
                CsvDelimiter.DELIMITER_COMMA.toString();
    }

    public MeasurementsCriteria withDecimalPoint(CsvDecimalPoint decimalPoint) {
        this.filters.put("decimalPoint", decimalPoint.toString());
        return this;
    }

    public String getDecimalPoint() {
        return this.filters.get("decimalPoint") != null ?
                this.filters.get("decimalPoint") :
                CsvDecimalPoint.DECIMAL_POINT_DOT.toString();
    }

    public MeasurementsCriteria withPrecision(CsvPrecision precision) {
        this.filters.put("precision", precision.toString());
        return this;
    }

    public String getPrecision() {
        return this.filters.get("precision") != null ?
                this.filters.get("precision") :
                CsvPrecision.PRECISION_2.toString();
    }

    public MeasurementsCriteria withEmptyPlaceHolder(String emptyPlaceHolder) {
        this.filters.put(
                "emptyPlaceholder",
                emptyPlaceHolder == null
                        ? CsvEmptyPlaceHolder.EMPTY_PLACE_HOLDER_EMPTY.toString()
                        : emptyPlaceHolder
        );
        return this;
    }

    public String getEmptyPlaceholder() {
        return this.filters.get("emptyPlaceholder") != null ?
                this.filters.get("emptyPlaceholder") :
                CsvEmptyPlaceHolder.EMPTY_PLACE_HOLDER_EMPTY.toString();
    }

    public List<NameValuePair> getAsList() {
        this.validateCriteriaSettings();
        List<NameValuePair> list = new ArrayList<NameValuePair>(filters.size());
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private void validateCriteriaSettings() {
        if (this.getFormat().equals(BulkResponseFormat.FORMAT_CSV.toString())) {
            if (this.getDecimalPoint().equals(this.getDelimiter())) {
                throw new IllegalArgumentException("Delimiter and decimal point symbols can't be the same");
            }
        }
    }
}