package com.meteocontrol.client.models;


import com.meteocontrol.client.models.annotation.ModelProperty;

public class UserDetail extends BaseModel {

    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String language;
    private String company;
    private String fax;
    private String telephone;
    private String cellphone;
    private ExtendedAddress address;
    private Timezone timezone;

    public UserDetail() {
    }

    public UserDetail(
            String id,
            String title,
            String firstName,
            String lastName,
            String username,
            String email,
            String language,
            String company,
            String fax,
            String telephone,
            String cellphone,
            ExtendedAddress address,
            Timezone timezone
    ) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.language = language;
        this.company = company;
        this.fax = fax;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.address = address;
        this.timezone = timezone;
    }

    @ModelProperty
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ModelProperty
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ModelProperty
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ModelProperty
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ModelProperty
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ModelProperty
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ModelProperty
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @ModelProperty
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @ModelProperty
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @ModelProperty
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @ModelProperty
    public String getCellphone() {
        return this.cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @ModelProperty
    public ExtendedAddress getAddress() {
        return this.address;
    }

    public void setAddress(ExtendedAddress address) {
        this.address = address;
    }

    @ModelProperty
    public Timezone getTimezone() {
        return this.timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
}
