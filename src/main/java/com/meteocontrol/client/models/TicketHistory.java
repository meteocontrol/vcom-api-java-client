package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.Date;

public class TicketHistory extends BaseModel {
    private Date timestamp;
    private String action;
    private String personInCharge;
    private String from;
    private String to;

    public TicketHistory() {}

    public TicketHistory(Date timestamp, String action, String personInCharge, String from, String to) {
        this.timestamp = timestamp;
        this.action = action;
        this.personInCharge = personInCharge;
        this.from = from;
        this.to = to;
    }

    @ModelProperty
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @ModelProperty
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @ModelProperty
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @ModelProperty
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @ModelProperty
    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }
}
