package ca.mydemo.tcoffice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Client implements Serializable {
    private Integer businessId;
    private String merchantId;
    private String secondMerchId;
    private String thirdMerchId;
    private String firstPhoneNbr;
    private String secondPhoneNbr;
    private String bizFaxNbr;
    private String bizName;
    private String bizLegalName;
    private String bizAddress;
    private String bizCity;
    private String bizProvince;
    private String bizProvinceCode;
    private Integer bizProvinceId;
    private String bizPostCode;
    private String bizShipAddress;
    private String bizSuite;
    private String timInBusCurnAddr;
    private Integer timInBusCurnAddrId;
    private String timInBusCurnAddrKey;
    private String shipCity;
    private String shipProvince;
    private String shipPostCode;
    private String shipProvinceKey;
    private Integer shipProvinceId;
    private String processorKey;
    private BigDecimal bizAvgMthAmt;
    private BigDecimal bizAcrtAvgMthAmt;
    private BigDecimal debCredPercent;
    private Integer active;
    private String promotCode;
    private String processor;
    private Integer terminalNbr; //terminal nbr
    private Integer nbrOfLocation; //location nbr
    private Integer opsFlag;
    private String saleforceBizId;
    private Integer isLead;
    private Integer partnerId;
    private Integer leadSrcId;
    private Integer channelId;
    private Integer medId; //mediaId
    private String saleForceLeadId;
    private String flSrv; //fricd score
    private String wmSrv; //wyman score
    private Date createdDate;
    private String acctUUId;
    private String customerId;
    private Integer isDuplicated;
    private String industry;
    private String subIndustry;
    private String industryCode;
    private String subIndustryCode;
    private Integer ownerTimeInBizId;
    private Integer timeInBizId;
    private Integer currentAddressId;
    private String ownerTimeInBizKey;
    private String ownerInCurrBizTime;
    private String timeInBizKey;
    private String currentAddressKey;
    private String bizCurrentAddress;
    private String bizChannelKey;
    private String bizMedKey;
    private BigDecimal maxElgebalAmt;
    private Integer isBanked;
    private String leadSrcKey;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSecondMerchId() {
        return secondMerchId;
    }

    public void setSecondMerchId(String secondMerchId) {
        this.secondMerchId = secondMerchId;
    }

    public String getThirdMerchId() {
        return thirdMerchId;
    }

    public void setThirdMerchId(String thirdMerchId) {
        this.thirdMerchId = thirdMerchId;
    }

    public String getFirstPhoneNbr() {
        return firstPhoneNbr;
    }

    public void setFirstPhoneNbr(String firstPhoneNbr) {
        this.firstPhoneNbr = firstPhoneNbr;
    }

    public String getSecondPhoneNbr() {
        return secondPhoneNbr;
    }

    public void setSecondPhoneNbr(String secondPhoneNbr) {
        this.secondPhoneNbr = secondPhoneNbr;
    }

    public String getBizFaxNbr() {
        return bizFaxNbr;
    }

    public void setBizFaxNbr(String bizFaxNbr) {
        this.bizFaxNbr = bizFaxNbr;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizLegalName() {
        return bizLegalName;
    }

    public void setBizLegalName(String bizLegalName) {
        this.bizLegalName = bizLegalName;
    }

    public String getBizAddress() {
        return bizAddress;
    }

    public void setBizAddress(String bizAddress) {
        this.bizAddress = bizAddress;
    }

    public String getBizCity() {
        return bizCity;
    }

    public void setBizCity(String bizCity) {
        this.bizCity = bizCity;
    }

    public String getBizProvince() {
        return bizProvince;
    }

    public void setBizProvince(String bizProvince) {
        this.bizProvince = bizProvince;
    }

    public String getBizProvinceCode() {
        return bizProvinceCode;
    }

    public void setBizProvinceCode(String bizProvinceCode) {
        this.bizProvinceCode = bizProvinceCode;
    }

    public Integer getBizProvinceId() {
        return bizProvinceId;
    }

    public void setBizProvinceId(Integer bizProvinceId) {
        this.bizProvinceId = bizProvinceId;
    }

    public String getBizPostCode() {
        return bizPostCode;
    }

    public void setBizPostCode(String bizPostCode) {
        this.bizPostCode = bizPostCode;
    }

    public String getBizShipAddress() {
        return bizShipAddress;
    }

    public void setBizShipAddress(String bizShipAddress) {
        this.bizShipAddress = bizShipAddress;
    }

    public String getBizSuite() {
        return bizSuite;
    }

    public void setBizSuite(String bizSuite) {
        this.bizSuite = bizSuite;
    }

    public String getTimInBusCurnAddr() {
        return timInBusCurnAddr;
    }

    public void setTimInBusCurnAddr(String timInBusCurnAddr) {
        this.timInBusCurnAddr = timInBusCurnAddr;
    }

    public Integer getTimInBusCurnAddrId() {
        return timInBusCurnAddrId;
    }

    public void setTimInBusCurnAddrId(Integer timInBusCurnAddrId) {
        this.timInBusCurnAddrId = timInBusCurnAddrId;
    }

    public String getTimInBusCurnAddrKey() {
        return timInBusCurnAddrKey;
    }

    public void setTimInBusCurnAddrKey(String timInBusCurnAddrKey) {
        this.timInBusCurnAddrKey = timInBusCurnAddrKey;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipProvince() {
        return shipProvince;
    }

    public void setShipProvince(String shipProvince) {
        this.shipProvince = shipProvince;
    }

    public String getShipPostCode() {
        return shipPostCode;
    }

    public void setShipPostCode(String shipPostCode) {
        this.shipPostCode = shipPostCode;
    }

    public String getShipProvinceKey() {
        return shipProvinceKey;
    }

    public void setShipProvinceKey(String shipProvinceKey) {
        this.shipProvinceKey = shipProvinceKey;
    }

    public Integer getShipProvinceId() {
        return shipProvinceId;
    }

    public void setShipProvinceId(Integer shipProvinceId) {
        this.shipProvinceId = shipProvinceId;
    }

    public String getProcessorKey() {
        return processorKey;
    }

    public void setProcessorKey(String processorKey) {
        this.processorKey = processorKey;
    }

    public BigDecimal getBizAvgMthAmt() {
        return bizAvgMthAmt;
    }

    public void setBizAvgMthAmt(BigDecimal bizAvgMthAmt) {
        this.bizAvgMthAmt = bizAvgMthAmt;
    }

    public BigDecimal getBizAcrtAvgMthAmt() {
        return bizAcrtAvgMthAmt;
    }

    public void setBizAcrtAvgMthAmt(BigDecimal bizAcrtAvgMthAmt) {
        this.bizAcrtAvgMthAmt = bizAcrtAvgMthAmt;
    }

    public BigDecimal getDebCredPercent() {
        return debCredPercent;
    }

    public void setDebCredPercent(BigDecimal debCredPercent) {
        this.debCredPercent = debCredPercent;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getPromotCode() {
        return promotCode;
    }

    public void setPromotCode(String promotCode) {
        this.promotCode = promotCode;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getTerminalNbr() {
        return terminalNbr;
    }

    public void setTerminalNbr(Integer terminalNbr) {
        this.terminalNbr = terminalNbr;
    }

    public Integer getNbrOfLocation() {
        return nbrOfLocation;
    }

    public void setNbrOfLocation(Integer nbrOfLocation) {
        this.nbrOfLocation = nbrOfLocation;
    }

    public Integer getOpsFlag() {
        return opsFlag;
    }

    public void setOpsFlag(Integer opsFlag) {
        this.opsFlag = opsFlag;
    }

    public String getSaleforceBizId() {
        return saleforceBizId;
    }

    public void setSaleforceBizId(String saleforceBizId) {
        this.saleforceBizId = saleforceBizId;
    }

    public Integer getIsLead() {
        return isLead;
    }

    public void setIsLead(Integer isLead) {
        this.isLead = isLead;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getLeadSrcId() {
        return leadSrcId;
    }

    public void setLeadSrcId(Integer leadSrcId) {
        this.leadSrcId = leadSrcId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public String getSaleForceLeadId() {
        return saleForceLeadId;
    }

    public void setSaleForceLeadId(String saleForceLeadId) {
        this.saleForceLeadId = saleForceLeadId;
    }

    public String getFlSrv() {
        return flSrv;
    }

    public void setFlSrv(String flSrv) {
        this.flSrv = flSrv;
    }

    public String getWmSrv() {
        return wmSrv;
    }

    public void setWmSrv(String wmSrv) {
        this.wmSrv = wmSrv;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAcctUUId() {
        return acctUUId;
    }

    public void setAcctUUId(String acctUUId) {
        this.acctUUId = acctUUId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getIsDuplicated() {
        return isDuplicated;
    }

    public void setIsDuplicated(Integer isDuplicated) {
        this.isDuplicated = isDuplicated;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubIndustry() {
        return subIndustry;
    }

    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getSubIndustryCode() {
        return subIndustryCode;
    }

    public void setSubIndustryCode(String subIndustryCode) {
        this.subIndustryCode = subIndustryCode;
    }

    public Integer getOwnerTimeInBizId() {
        return ownerTimeInBizId;
    }

    public void setOwnerTimeInBizId(Integer ownerTimeInBizId) {
        this.ownerTimeInBizId = ownerTimeInBizId;
    }

    public Integer getTimeInBizId() {
        return timeInBizId;
    }

    public void setTimeInBizId(Integer timeInBizId) {
        this.timeInBizId = timeInBizId;
    }

    public Integer getCurrentAddressId() {
        return currentAddressId;
    }

    public void setCurrentAddressId(Integer currentAddressId) {
        this.currentAddressId = currentAddressId;
    }

    public String getOwnerTimeInBizKey() {
        return ownerTimeInBizKey;
    }

    public void setOwnerTimeInBizKey(String ownerTimeInBizKey) {
        this.ownerTimeInBizKey = ownerTimeInBizKey;
    }

    public String getOwnerInCurrBizTime() {
        return ownerInCurrBizTime;
    }

    public void setOwnerInCurrBizTime(String ownerInCurrBizTime) {
        this.ownerInCurrBizTime = ownerInCurrBizTime;
    }

    public String getTimeInBizKey() {
        return timeInBizKey;
    }

    public void setTimeInBizKey(String timeInBizKey) {
        this.timeInBizKey = timeInBizKey;
    }

    public String getCurrentAddressKey() {
        return currentAddressKey;
    }

    public void setCurrentAddressKey(String currentAddressKey) {
        this.currentAddressKey = currentAddressKey;
    }

    public String getBizCurrentAddress() {
        return bizCurrentAddress;
    }

    public void setBizCurrentAddress(String bizCurrentAddress) {
        this.bizCurrentAddress = bizCurrentAddress;
    }

    public String getBizChannelKey() {
        return bizChannelKey;
    }

    public void setBizChannelKey(String bizChannelKey) {
        this.bizChannelKey = bizChannelKey;
    }

    public String getBizMedKey() {
        return bizMedKey;
    }

    public void setBizMedKey(String bizMedKey) {
        this.bizMedKey = bizMedKey;
    }

    public BigDecimal getMaxElgebalAmt() {
        return maxElgebalAmt;
    }

    public void setMaxElgebalAmt(BigDecimal maxElgebalAmt) {
        this.maxElgebalAmt = maxElgebalAmt;
    }

    public Integer getIsBanked() {
        return isBanked;
    }

    public void setIsBanked(Integer isBanked) {
        this.isBanked = isBanked;
    }

    public String getLeadSrcKey() {
        return leadSrcKey;
    }

    public void setLeadSrcKey(String leadSrcKey) {
        this.leadSrcKey = leadSrcKey;
    }


}
