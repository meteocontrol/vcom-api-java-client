package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Attachment extends BaseModel {
    private int attachmentId;
    private String filename;

    public Attachment() {
    }

    public Attachment(int attachmentId, String filename) {
        this.attachmentId = attachmentId;
        this.filename = filename;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    @ModelProperty
    public int getAttachmentId() {
        return this.attachmentId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ModelProperty
    public String getFilename() {
        return this.filename;
    }

}
