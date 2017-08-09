package com.meteocontrol.client.models;


import com.meteocontrol.client.models.annotation.ModelProperty;

import java.lang.*;
import java.util.Base64;

public class AttachmentFile extends BaseModel {

    private String filename;
    private String content;
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
