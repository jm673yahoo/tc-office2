package ca.mydemo.tcoffice.service;

import ca.mydemo.tcoffice.domain.BasicItem;

import java.util.List;

public interface StaticDataService {

    void loadStaticData(String userProfile);

    List<BasicItem> getProcessors();
}
