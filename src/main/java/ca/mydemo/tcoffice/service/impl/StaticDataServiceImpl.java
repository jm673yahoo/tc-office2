package ca.mydemo.tcoffice.service.impl;

import ca.mydemo.tcoffice.domain.BasicItem;
import ca.mydemo.tcoffice.service.StaticDataService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaticDataServiceImpl implements StaticDataService {

    @Override
    public void loadStaticData(String userProfile) {

    }

    @Override
    public List<BasicItem> getProcessors() {
        return null;
    }
}
