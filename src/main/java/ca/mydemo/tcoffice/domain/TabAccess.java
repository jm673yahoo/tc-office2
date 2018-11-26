package ca.mydemo.tcoffice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class TabAccess implements Serializable {

    private boolean showMessages;
    private boolean showStatement;
    private boolean showTerminal;
    private boolean showBatchUpload;
    private boolean showSource;
    private boolean showConsumers;
    private boolean showSettings;
    private boolean showAccounts;
    private boolean showUserAdmin;
    // acct statement sub menu items
    private boolean showMonthlyStmt;

    // docs
    private boolean desjardinsOnlyDocs;

    // setting sub menu items
    private boolean showSFTP;
    private boolean showPGP; //requires showSFTP to be displayed, not it's own tab but a section on the SFTP page.
    private boolean showCascading;
    private boolean showEmailSetup;

    // reports sub menu items
    private boolean showClientActivity;
    private boolean showClientSummary;
    private boolean showRptSearch;
    private boolean showRptScheduled;

    // permissions
    private boolean allowVTSubmit;
    private boolean allowReprocessTxn;
    private boolean allowDDCredit;
    private boolean allowBatchUpload;
    private boolean allowDDBatchApproval;
    private boolean allowAccessD;
    private boolean allowOBRefund;

    // hold the account the user has access to
    private boolean hasOneCCAccount;
    private boolean hasOneDDAccount;
    private boolean hasOneRBAccount;

    // the merchant legal entity code
    private String meletCode;

    private Tab selectedTab;
    private String selectedSubTab;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public void setSelectedTab(Tab selectedTab) {
        this.selectedTab = selectedTab;
    }

    public Tab getSelectedTab() {
        return selectedTab;
    }

    public boolean isShowMessages() {
        return showMessages;
    }

    public void setShowMessages(boolean showMessages) {
        this.showMessages = showMessages;
    }

    public boolean isShowStatement() {
        return showStatement;
    }

    public void setShowStatement(boolean showStatement) {
        this.showStatement = showStatement;
    }

    public boolean isShowTerminal() {
        return showTerminal;
    }

    public void setShowTerminal(boolean showTerminal) {
        this.showTerminal = showTerminal;
    }

    public boolean isShowBatchUpload() {
        return showBatchUpload;
    }

    public void setShowBatchUpload(boolean showBatchUpload) {
        this.showBatchUpload = showBatchUpload;
    }


    public boolean isShowConsumers() {
        return showConsumers;
    }

    public void setShowConsumers(boolean showConsumers) {
        this.showConsumers = showConsumers;
    }

    public boolean isShowRptSearch() {
        return showRptSearch;
    }

    public void setShowRptSearch(boolean showRptSearch) {
        this.showRptSearch = showRptSearch;
    }

    public boolean isShowRptScheduled() {
        return showRptScheduled;
    }

    public void setShowRptScheduled(boolean showRptScheduled) {
        this.showRptScheduled = showRptScheduled;
    }

    public boolean isAllowVTSubmit() {
        return allowVTSubmit;
    }

    public void setAllowVTSubmit(boolean allowVTSubmit) {
        this.allowVTSubmit = allowVTSubmit;
    }

    public boolean isAllowReprocessTxn() {
        return allowReprocessTxn;
    }

    public void setAllowReprocessTxn(boolean allowReprocessTxn) {
        this.allowReprocessTxn = allowReprocessTxn;
    }

    public boolean isAllowDDCredit() {
        return allowDDCredit;
    }

    public void setAllowDDCredit(boolean allowDDCredit) {
        this.allowDDCredit = allowDDCredit;
    }

    public boolean isAllowAccessD() {
        return allowAccessD;
    }

    public void setAllowAccessD(boolean allowAccessD) {
        this.allowAccessD = allowAccessD;
    }

    public boolean isAllowOBRefund() {
        return allowOBRefund;
    }

    public void setAllowOBRefund(boolean allowOBRefund) {
        this.allowOBRefund = allowOBRefund;
    }

    public boolean isShowSettings() {
        return showSettings;
    }

    public void setShowSettings(boolean showSettings) {
        this.showSettings = showSettings;
    }

    public boolean isShowSFTP() {
        return showSFTP;
    }

    public void setShowSFTP(boolean showSFTP) {
        this.showSFTP = showSFTP;
    }

    public boolean isShowPGP() {
        return showPGP;
    }

    public void setShowPGP(boolean showPGP) {
        this.showPGP = showPGP;
    }

    public boolean isHasOneCCAccount() {
        return hasOneCCAccount;
    }

    public void setHasOneCCAccount(boolean hasOneCCAccount) {
        this.hasOneCCAccount = hasOneCCAccount;
    }

    public boolean isHasOneDDAccount() {
        return hasOneDDAccount;
    }

    public void setHasOneDDAccount(boolean hasOneDDAccount) {
        this.hasOneDDAccount = hasOneDDAccount;
    }

    public boolean isHasOneRBAccount() {
        return hasOneRBAccount;
    }

    public void setHasOneRBAccount(boolean hasOneRBAccount) {
        this.hasOneRBAccount = hasOneRBAccount;
    }

    public String getMeletCode() {
        return meletCode;
    }

    public void setMeletCode(String meletCode) {
        this.meletCode = meletCode;
    }

    public boolean isAllowBatchUpload() {
        return allowBatchUpload;
    }

    public void setAllowBatchUpload(boolean allowBatchUpload) {
        this.allowBatchUpload = allowBatchUpload;
    }

    public boolean isAllowDDBatchApproval() {
        return allowDDBatchApproval;
    }

    public void setAllowDDBatchApproval(boolean allowDDBatchApproval) {
        this.allowDDBatchApproval = allowDDBatchApproval;
    }

    public String getSelectedSubTab() {
        return selectedSubTab;
    }

    public void setSelectedSubTab(String selectedSubTab) {
        this.selectedSubTab = selectedSubTab;
    }

    public boolean isShowCascading() {
        return showCascading;
    }

    public void setShowCascading(boolean showCascading) {
        this.showCascading = showCascading;
    }

    public boolean isShowEmailSetup() {
        return showEmailSetup;
    }

    public void setShowEmailSetup(boolean showEmailSetup) {
        this.showEmailSetup = showEmailSetup;
    }

    public boolean isDesjardinsOnlyDocs() {
        return desjardinsOnlyDocs;
    }

    public void setDesjardinsOnlyDocs(boolean desjardinsOnlyDocs) {
        this.desjardinsOnlyDocs = desjardinsOnlyDocs;
    }

    public boolean isShowMonthlyStmt() {
        return showMonthlyStmt;
    }

    public void setShowMonthlyStmt(boolean showMonthlyStmt) {
        this.showMonthlyStmt = showMonthlyStmt;
    }

    public boolean isShowAccounts() {
        return showAccounts;
    }

    public void setShowAccounts(boolean showAccounts) {
        this.showAccounts = showAccounts;
    }

    public boolean isShowUserAdmin() {
        return showUserAdmin;
    }

    public void setShowUserAdmin(boolean showUserAdmin) {
        this.showUserAdmin = showUserAdmin;
    }

    public boolean isShowSource() {
        return showSource;
    }

    public void setShowSource(boolean showSource) {
        this.showSource = showSource;
    }

    public boolean showClientActivity() {
        return showClientActivity;
    }

    public void setShowClientActivity(boolean showClientActivity) {
        this.showClientActivity = showClientActivity;
    }

    public boolean showClientSummary() {
        return showClientSummary;
    }

    public void setShowClientSummary(boolean showClientSummary) {
        this.showClientSummary = showClientSummary;
    }
}
