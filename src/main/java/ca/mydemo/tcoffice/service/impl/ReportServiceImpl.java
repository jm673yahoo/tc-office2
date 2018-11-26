package ca.mydemo.tcoffice.service.impl;

import ca.mydemo.tcoffice.domain.report.SummaryData;
import ca.mydemo.tcoffice.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public List<SummaryData> getTrxForMerchantChar(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SummaryData> getTrxSummaryOnCompanies(String id) {
        return null;
    }
}
