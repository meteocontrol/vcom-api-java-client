package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meteocontrol.client.models.annotation.ModelProperty;
import com.meteocontrol.client.params.TicketPriority;
import com.meteocontrol.client.params.TicketStatus;
import com.meteocontrol.client.params.TicketIncludedInReportType;
import com.meteocontrol.client.params.TicketSeverity;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Ticket extends BaseModel {
    private int id;
    private String systemKey;
    private String designation;
    private String summary;
    private String description;
    @Deprecated
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date date;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;
    @Deprecated
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date lastChange;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date lastChangedAt;
    @Deprecated
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date rectifiedOn;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date rectifiedAt;
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
                  Date createdAt,
                  Date lastChangedAt,
                  Date rectifiedAt,
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
        this.createdAt = this.date = createdAt;
        this.lastChangedAt = this.lastChange = lastChangedAt;
        this.rectifiedAt = this.rectifiedOn = rectifiedAt;
        this.assignee = assignee;
        this.status = status;
        this.causeId = causeId;
        this.priority = priority;
        this.includeInReports = includeInReports;
        this.fieldService = fieldService;
        this.severity = severity;
        this.description = description;

    }

    @ModelProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ModelProperty
    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    @ModelProperty
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @ModelProperty
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Deprecated
    @ModelProperty
    public Date getDate() {
        return date;
    }

    @Deprecated
    public void setDate(Date date) {
        this.createdAt = this.date = date;
    }

    @ModelProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = this.date = createdAt;
    }

    @Deprecated
    @ModelProperty
    public Date getLastChange() {
        return lastChange;
    }

    @Deprecated
    public void setLastChange(Date lastChange) {
        this.lastChangedAt = this.lastChange = lastChange;
    }

    @ModelProperty
    public Date getLastChangedAt() {
        return lastChangedAt;
    }

    public void setLastChangedAt(Date lastChangedAt) {
        this.lastChangedAt = this.lastChange = lastChangedAt;
    }

    @Deprecated
    @ModelProperty
    public Date getRectifiedOn() {
        return rectifiedAt;
    }

    @Deprecated
    public void setRectifiedOn(Date rectifiedOn) {
        this.rectifiedAt = this.rectifiedOn = rectifiedOn;
    }

    @ModelProperty
    public Date getRectifiedAt() {
        return rectifiedAt;
    }

    public void setRectifiedAt(Date rectifiedAt) {
        this.rectifiedAt = this.rectifiedOn = rectifiedAt;
    }

    @ModelProperty
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @ModelProperty
    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @ModelProperty
    public Integer getCauseId() {
        return causeId;
    }

    public void setCauseId(Integer causeId) {
        this.causeId = causeId;
    }

    @ModelProperty
    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    @ModelProperty
    public TicketIncludedInReportType getIncludeInReports() {
        return includeInReports;
    }

    public void setIncludeInReports(TicketIncludedInReportType includeInReports) {
        this.includeInReports = includeInReports;
    }

    @ModelProperty
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ModelProperty
    public Boolean getFieldService() {
        return fieldService;
    }

    public void setFieldService(Boolean fieldService) {
        this.fieldService = fieldService;
    }

    @ModelProperty
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
                Objects.equals(p.getCreatedAt(), this.createdAt) &&
                Objects.equals(p.getLastChangedAt(), this.lastChangedAt) &&
                Objects.equals(p.getRectifiedAt(), this.rectifiedAt) &&
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
        return !"".equals(this.systemKey) && !"".equals(this.designation) && this.createdAt != null;
    }
}
