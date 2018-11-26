package ca.mydemo.tcoffice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Tab implements Serializable {
    private static final long serialVersionUID = 1L;

    // internal map of all our tabs
    private static final Map TABS = new HashMap();

    public static final Tab DASHBOARD = new Tab("tabDashboard", "DASHBOARD", "/general/dashboard.htm");
    public static final Tab MESSAGES = new Tab("tabMessages", "", "");
    public static final Tab VT = new Tab("tabVT", "VT", "/vt/showVT.htm");
    public static final Tab BATCH = new Tab("tabBatch", "BATCH", "/batch/showBatchUpload.htm");
    public static final Tab Source = new Tab("tabReports", "Source", "/source/clientActive.htm");
    public static final Tab CONSUMERS = new Tab("tabConsumers", "RB", "/profile/showProfile.htm");
    public static final Tab SETTINGS = new Tab("tabSettings", "SETTINGS", "/settings/showSettings.htm");

    // sub menu items
    public static final String RPT_ACTIVITY = "rptActivity";
    public static final String RPT_BATCH = "rptBatch";
    public static final String RPT_CB = "rptSearch";

    public static final String SETT_SECURITY = "settSecurity";
    public static final String SETT_CHG_PWD = "settChangePwd";
    public static final String SETT_CUTOMER_FIELD = "settCustomerField";
    public static final String SETT_EMAILS = "settEmails";
    public static final String SETT_USAGE = "settUsage";
    public static final String SETT_GROUPING = "settGrouping";
    public static final String SETT_EMAIL_TPL = "settEmailTemplates";
    public static final String SETT_USER_ADMIN = "settUserAdmin";

    // class fields
    private String code;
    private String landingPageCode;
    private String landingPageLink;
    private int displayOrder;

    private Tab(String code, String landingPageCode, String landingPageLink) {
        this.code = code;
        this.landingPageCode = landingPageCode;
        this.landingPageLink = landingPageLink;
        TABS.put(code, this);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public static Tab getTab(String code) {
        return (Tab) TABS.get(code);
    }

    public String getCode() {
        return code;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public String getLandingPageCode() {
        return landingPageCode;
    }

    public void setLandingPageCode(String landingPageCode) {
        this.landingPageCode = landingPageCode;
    }

    public String getLandingPageLink() {
        return landingPageLink;
    }

    public void setLandingPageLink(String landingPageLink) {
        this.landingPageLink = landingPageLink;
    }
}
