package com.meteocontrol.client.filters;

import com.meteocontrol.client.exceptions.ApiClientException;
import com.meteocontrol.client.params.TicketIncludedInReportType;
import com.meteocontrol.client.params.TicketPriority;
import com.meteocontrol.client.params.TicketSeverity;
import com.meteocontrol.client.params.TicketStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketsCriteria {
    private Map<String, String> filters;

    private SimpleDateFormat dateFormat;

    public TicketsCriteria() {
        this.filters = new HashMap<String, String>();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    }

    public Date getLastChangeFrom() {
        try {
            return dateFormat.parse(this.filters.get("lastChange[from]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withLastChangeFrom(Date from) {
        this.filters.put("lastChange[from]", dateFormat.format(from));
        return this;
    }

    public Date getLastChangeTo() {
        try {
            return dateFormat.parse(this.filters.get("lastChange[to]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withLastChangeTo(Date to) {
        this.filters.put("lastChange[to]", dateFormat.format(to));
        return this;
    }

    public Date getDateFrom() {
        try {
            return dateFormat.parse(this.filters.get("date[from]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withDateFrom(Date from) {
        this.filters.put("date[from]", dateFormat.format(from));
        return this;
    }

    public Date getDateTo() {
        try {
            return dateFormat.parse(this.filters.get("date[to]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withDateTo(Date to) {
        this.filters.put("date[to]", dateFormat.format(to));
        return this;
    }

    public Date getRectifiedOnFrom() {
        try {
            return dateFormat.parse(this.filters.get("rectifiedOn[from]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withRectifiedOnFrom(Date from) {
        this.filters.put("rectifiedOn[from]", dateFormat.format(from));
        return this;
    }

    public Date getRectifiedOnTo() {
        try {
            return dateFormat.parse(this.filters.get("rectifiedOn[to]"));
        } catch (ParseException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public TicketsCriteria withRectifiedOnTo(Date to) {
        this.filters.put("rectifiedOn[to]", dateFormat.format(to));
        return this;
    }

    public TicketIncludedInReportType getIncludeInReports() {
        return Enum.valueOf(TicketIncludedInReportType.class, this.filters.get("includeInReports"));
    }

    public TicketsCriteria withIncludeInReports(TicketIncludedInReportType includeInReports) {
        this.filters.put("includeInReports", includeInReports.toString());
        return this;
    }

    public TicketStatus getStatus() {
        return Enum.valueOf(TicketStatus.class, this.filters.get("status"));
    }

    public TicketsCriteria withStatus(TicketStatus status) {
        this.filters.put("status", status.toString());
        return this;
    }

    public TicketSeverity getSeverity() {
        return Enum.valueOf(TicketSeverity.class, this.filters.get("severity"));
    }

    public TicketsCriteria withSeverity(TicketSeverity severity) {
        this.filters.put("severity", severity.toString());
        return this;
    }

    public TicketPriority getPriority() {
        return Enum.valueOf(TicketPriority.class, this.filters.get("priority"));
    }

    public TicketsCriteria withPriority(TicketPriority priority) {
        this.filters.put("priority", priority.toString());
        return this;
    }

    public String getAssignee() {
        return this.filters.get("assignee");
    }

    public TicketsCriteria withAssignee(String assignee) {
        this.filters.put("assignee", assignee);
        return this;
    }

    public String getSystemKey() {
        return this.filters.get("systemKey");
    }

    public TicketsCriteria withSystemKey(String systemKey) {
        this.filters.put("systemKey", systemKey);
        return this;
    }

    public List<NameValuePair> getAsList() {
        List<NameValuePair> list = new ArrayList<NameValuePair>(filters.size());
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}