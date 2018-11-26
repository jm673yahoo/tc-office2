package ca.mydemo.tcoffice.utils;

import ca.mydemo.tcoffice.domain.Tab;
import ca.mydemo.tcoffice.domain.TabAccess;

public class OfficeUtil {

    public static TabAccess buildTabAccess() {
        TabAccess _tab = new TabAccess();

        _tab.setAllowBatchUpload(true);
        _tab.setAllowReprocessTxn(true);
        _tab.setAllowVTSubmit(false);
        _tab.setHasOneCCAccount(true);
        _tab.setShowAccounts(false);
        _tab.setShowBatchUpload(true);
        _tab.setShowCascading(false);
        _tab.setShowConsumers(true);
        _tab.setShowEmailSetup(false);
        _tab.setShowMessages(false);
        _tab.setShowSource(true);
        _tab.setShowClientActivity(true);
        _tab.setShowClientSummary(true);
        _tab.setShowRptSearch(true);
        _tab.setShowSettings(false);
        _tab.setShowSFTP(false);
        _tab.setShowStatement(false);
        _tab.setShowTerminal(true);
        _tab.setShowUserAdmin(false);


        _tab.setSelectedTab(Tab.Source);
        return _tab;
    }
}
