package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Responsibilities extends BaseModel {

    private UserDetail owner;
    private UserDetail operator;
    private UserDetail electrician;
    private UserDetail invoiceRecipient;
    private UserDetail alarmContact;

    @ModelProperty
    public UserDetail getOwner() {
        return owner;
    }

    public void setOwner(UserDetail owner) {
        this.owner = owner;
    }

    @ModelProperty
    public UserDetail getOperator() {
        return operator;
    }

    public void setOperator(UserDetail operator) {
        this.operator = operator;
    }

    @ModelProperty
    public UserDetail getElectrician() {
        return electrician;
    }

    public void setElectrician(UserDetail electrician) {
        this.electrician = electrician;
    }

    @ModelProperty
    public UserDetail getInvoiceRecipient() {
        return invoiceRecipient;
    }

    public void setInvoiceRecipient(UserDetail invoiceRecipient) {
        this.invoiceRecipient = invoiceRecipient;
    }

    @ModelProperty
    public UserDetail getAlarmContact() {
        return alarmContact;
    }

    public void setAlarmContact(UserDetail alarmContact) {
        this.alarmContact = alarmContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Responsibilities that = (Responsibilities) o;

        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (electrician != null ? !electrician.equals(that.electrician) : that.electrician != null) return false;
        if (invoiceRecipient != null ? !invoiceRecipient.equals(that.invoiceRecipient) : that.invoiceRecipient != null)
            return false;
        return alarmContact != null ? alarmContact.equals(that.alarmContact) : that.alarmContact == null;
    }
}
