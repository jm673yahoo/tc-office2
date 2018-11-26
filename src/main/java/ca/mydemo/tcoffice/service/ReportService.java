package ca.mydemo.tcoffice.service;

import ca.mydemo.tcoffice.domain.report.SummaryData;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<SummaryData> getTrxForMerchantChar(Map<String, Object> params);

    List<SummaryData> getTrxSummaryOnCompanies(String id);
}
