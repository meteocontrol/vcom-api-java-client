package com.meteocontrol.client.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.lang.*;
import java.util.Base64;
import java.util.Date;

public class AttachmentFile extends BaseModel {

    private int id;
    private String filename;
    private String content;
    private String description;
    private int creatorId;
    @Deprecated
    private Date created;
    private Date createdAt;
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    public AttachmentFile() {
    }

    public AttachmentFile(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    public AttachmentFile(String filename, byte[] content) {
        this.filename = filename;
        this.content = this.encodeContent(content);
    }

    public AttachmentFile(String filename, byte[] content, String description) {
        this.filename = filename;
        this.content = this.encodeContent(content);
        this.description = description;
    }

    @ModelProperty
    @JsonProperty("attachmentId")
    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @ModelProperty
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ModelProperty
    public String getContent() {
        return this.content;
    }

    public byte[] getDecodedContent() {
        return this.decodeContent(this.content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(byte[] content) {
        this.content = this.encodeContent(content);
    }

    @ModelProperty
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ModelProperty
    @Deprecated
    public Date getCreated() {
        return this.created;
    }

    @Deprecated
    public void setCreated(Date created) {
        this.createdAt = this.created = created;
    }

    @ModelProperty
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.created = this.createdAt = createdAt;
    }


    @ModelProperty
    public int getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    private String encodeContent(byte[] content) {
        return "data:image/jpeg;base64," + encoder.encodeToString(content);
    }

    private byte[] decodeContent(String encodedText) {
        String[] tokens = encodedText.split(",");
        return decoder.decode(tokens[1]);
    }

    public boolean isValid() {
        return !"".equals(this.filename) && !"".equals(this.content);
    }
}
