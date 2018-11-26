package ca.mydemo.tcoffice.domain;

import java.io.Serializable;
import java.util.Date;

public class Opportunity implements Serializable {

    private int id;
    private String applicationNbr;
    private int bizId;
    private Date createdDate;
    private Contact contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getApplicationNbr() {
        return applicationNbr;
    }

    public void setApplicationNbr(String applicationNbr) {
        this.applicationNbr = applicationNbr;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }
}
