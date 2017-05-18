package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meteocontrol.client.params.TicketPriority;
import com.meteocontrol.client.params.TicketStatus;
import com.meteocontrol.client.params.TicketIncludedInReportType;
import com.meteocontrol.client.params.TicketSeverity;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Ticket {
    private int id;
    private String systemKey;
    private String designation;
    private String summary;
    private String description;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date date;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date lastChange;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date rectifiedOn;
    private String assignee;
    private TicketStatus status;
    private Integer causeId;
    private TicketPriority priority;
    private TicketIncludedInReportType includeInReports;
    private Boolean fieldService;
    private TicketSeverity severity;

    public Ticket() {
    }

    public Ticket(int id,
                  String systemKey,
                  String designation,
                  String description,
                  String summary,
                  Date date,
                  Date lastChange,
                  Date rectifiedOn,
                  String assignee,
                  TicketStatus status,
                  Integer causeId,
                  TicketPriority priority,
                  TicketIncludedInReportType includeInReports,
                  Boolean fieldService,
                  TicketSeverity severity) {
        this.id = id;
        this.systemKey = systemKey;
        this.designation = designation;
        this.summary = summary;
        this.date = date;
        this.lastChange = lastChange;
        this.rectifiedOn = rectifiedOn;
        this.assignee = assignee;
        this.status = status;
        this.causeId = causeId;
        this.priority = priority;
        this.includeInReports = includeInReports;
        this.fieldService = fieldService;
        this.severity = severity;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public Date getRectifiedOn() {
        return rectifiedOn;
    }

    public void setRectifiedOn(Date rectifiedOn) {
        this.rectifiedOn = rectifiedOn;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Integer getCauseId() {
        return causeId;
    }

    public void setCauseId(Integer causeId) {
        this.causeId = causeId;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public TicketIncludedInReportType getIncludeInReports() {
        return includeInReports;
    }

    public void setIncludeInReports(TicketIncludedInReportType includeInReports) {
        this.includeInReports = includeInReports;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFieldService() {
        return fieldService;
    }

    public void setFieldService(Boolean fieldService) {
        this.fieldService = fieldService;
    }

    public TicketSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(TicketSeverity severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ticket))
            return false;
        Ticket p = (Ticket) obj;
        return p.getId() == this.id &&
                p.getSystemKey().equals(this.systemKey) &&
                p.getDesignation().equals(this.designation) &&
                p.getSummary().equals(this.summary) &&
                Objects.equals(p.getDate(), this.date) &&
                Objects.equals(p.getLastChange(), this.lastChange) &&
                Objects.equals(p.getRectifiedOn(), this.rectifiedOn) &&
                Objects.equals(p.getAssignee(), this.assignee) &&
                Objects.equals(p.getStatus(), this.status) &&
                Objects.equals(p.getCauseId(), this.causeId) &&
                Objects.equals(p.getPriority(), this.priority) &&
                Objects.equals(p.getIncludeInReports(), this.includeInReports) &&
                Objects.equals(p.getFieldService(), this.fieldService) &&
                Objects.equals(p.getSeverity(), this.severity) &&
                Objects.equals(p.getDescription(), this.description);
    }

    public boolean isValid() {
        return !"".equals(this.systemKey) && !"".equals(this.designation) && this.date != null;
    }
}
