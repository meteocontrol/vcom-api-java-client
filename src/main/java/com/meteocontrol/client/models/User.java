package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class User extends BaseModel {

    private String id;
    private String username;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ModelProperty
    public String getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ModelProperty
    public String getUsername() {
        return this.username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ModelProperty
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ModelProperty
    public String getLastName() {
        return this.lastName;
    }

}
