package ca.mydemo.tcoffice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Contact implements Serializable {

    private String firstName;
    private String lastName;
    private int conId;
    private String address;
    private String email;
    private String city;
    private String bizTitle;
    private String companyName;
    private int languageId;
    private int emailOptIn;
    private String contactTypeKey;
    private int contactTypeId;
    private String mobile;
    private int nbrOfBiz;
    private BigDecimal ownPercentage;
    private String postCode;
    private String preferPhone;
    private String provinceKey;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBizTitle() {
        return bizTitle;
    }

    public void setBizTitle(String bizTitle) {
        this.bizTitle = bizTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getEmailOptIn() {
        return emailOptIn;
    }

    public void setEmailOptIn(int emailOptIn) {
        this.emailOptIn = emailOptIn;
    }

    public String getContactTypeKey() {
        return contactTypeKey;
    }

    public void setContactTypeKey(String contactTypeKey) {
        this.contactTypeKey = contactTypeKey;
    }

    public int getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(int contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getNbrOfBiz() {
        return nbrOfBiz;
    }

    public void setNbrOfBiz(int nbrOfBiz) {
        this.nbrOfBiz = nbrOfBiz;
    }

    public BigDecimal getOwnPercentage() {
        return ownPercentage;
    }

    public void setOwnPercentage(BigDecimal ownPercentage) {
        this.ownPercentage = ownPercentage;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPreferPhone() {
        return preferPhone;
    }

    public void setPreferPhone(String preferPhone) {
        this.preferPhone = preferPhone;
    }

    public String getProvinceKey() {
        return provinceKey;
    }

    public void setProvinceKey(String provinceKey) {
        this.provinceKey = provinceKey;
    }
}
