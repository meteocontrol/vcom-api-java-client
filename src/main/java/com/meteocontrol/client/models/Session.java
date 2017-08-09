package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Session extends BaseModel {

    private UserDetail user;

    public Session(UserDetail user) {
        this.user = user;
    }

    public Session() {
    }

    @ModelProperty
    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }
}
