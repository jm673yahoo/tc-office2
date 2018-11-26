package ca.mydemo.tcoffice.controller;

import ca.mydemo.tcoffice.TCOfficeConstants;
import ca.mydemo.tcoffice.domain.BasicItem;
import ca.mydemo.tcoffice.domain.Message;
import ca.mydemo.tcoffice.domain.MessageInfo;
import ca.mydemo.tcoffice.domain.Tab;
import ca.mydemo.tcoffice.domain.TabAccess;
import ca.mydemo.tcoffice.domain.profile.UserProfile;
import ca.mydemo.tcoffice.domain.profile.UserType;
import ca.mydemo.tcoffice.domain.report.ActivityRequest;
import ca.mydemo.tcoffice.domain.report.DonutsData;
import ca.mydemo.tcoffice.domain.report.SummaryData;
import ca.mydemo.tcoffice.service.ReportService;
import ca.mydemo.tcoffice.service.StaticDataService;
import ca.mydemo.tcoffice.service.UserService;
import ca.mydemo.tcoffice.utils.OfficeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
public class GeneralController extends MultiActionController {
    private static final String LOGIN_ERROR_MESS = "errorMessage";
    private static final String RPT_REQUEST = "rptRequest";
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private StaticDataService staticDataService;
    @Autowired
    private ReportService reportService;

    /**
     * Handles the successful authentication scenario. Loads the user, redirects to the
     * changePassword.htm if neccesary or takes them to the home page.
     * we need to change after real login for method= RequestMethod.GET
     */
    @RequestMapping(value = "storeLoginName", method = RequestMethod.POST)
    public ModelAndView loginSuccess(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        logger.debug("storeLoginName");

        String _loginId = (String) WebUtils.getSessionAttribute(req, TCOfficeConstants.SESSION_USER_ID);

        logger.info("loginSuccess: " + _loginId + " from " + req.getRemoteAddr()
                + " - " + req.getHeader(TCOfficeConstants.USER_AGENT));
        Locale _locale = (Locale) WebUtils.getSessionAttribute(req, "locale");


        //loading all authorization config
        UserProfile _userProfile = userService.getUserProfileById(_loginId);
        UserType _access = UserType.getUserTypeByCode(_userProfile.getGroupLevelId());
        _userProfile.setUserType(_access);
        List<BasicItem> processors = staticDataService.getProcessors();
//        logger.info("processors: " + processors.size());


        // reset any error messages
        WebUtils.setSessionAttribute(req, LOGIN_ERROR_MESS, "");
        WebUtils.setSessionAttribute(req, "access", OfficeUtil.buildTabAccess());
        WebUtils.setSessionAttribute(req, TCOfficeConstants.SESSION_USER, _userProfile);
        WebUtils.setSessionAttribute(req, TCOfficeConstants.SESSION_PROCESSORS, processors);

        //load message
        MessageInfo messageInfo = loadMessage(_userProfile.getPartnerId(), _userProfile.getBizId());
        WebUtils.setSessionAttribute(req, "messageInfo", messageInfo);

        //even thought the authentication was successful, if the user is attempting

        return new ModelAndView("redirect:/account/merchantSummary.htm");
    }

    /**
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        logger.debug("showDashboard");

        String _loginId = (String) WebUtils.getSessionAttribute(req, TCOfficeConstants.SESSION_USER_ID);
        Locale locale = RequestContextUtils.getLocale(req);

        //setup tab
        TabAccess access = (TabAccess) WebUtils.getSessionAttribute(req, "access");
        access.setSelectedTab(Tab.Source);
        access.setSelectedSubTab(Tab.RPT_ACTIVITY);

        //get user from session
        UserProfile user = (UserProfile) WebUtils.getSessionAttribute(req, TCOfficeConstants.SESSION_USER);
        logger.debug("reportActive user:" + user);


        List<SummaryData> _companyTrxSummary = reportService.getTrxSummaryOnCompanies("123456");

        List<String> uniqueBrands;
        uniqueBrands = new ArrayList<String>();
        uniqueBrands.add("tcoffice");
        ActivityRequest command = new ActivityRequest();
        DateFormat format2 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        Calendar begingOfMonth = Calendar.getInstance();
        begingOfMonth.set(Calendar.YEAR, 2015);
        begingOfMonth.set(Calendar.MONTH, 11);
        begingOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endDate = Calendar.getInstance();
        ParsePosition pos = new ParsePosition(3);
        Date sdate = format2.parse(format2.format(begingOfMonth.getTime()));
//        command.setStartDateTZ(_sdate);

        endDate.set(Calendar.YEAR, 2015);
        endDate.set(Calendar.MONTH, 12);
        endDate.set(Calendar.DAY_OF_MONTH, 30);
        Date _edate = format2.parse(format2.format(endDate.getTime()));
//        command.setEndDateTZ(_edate);
        Set<String> allSet = new HashSet<String>();
        List<String> allList = new ArrayList<String>();
        allSet.add("ALL");
        allList.add("ALL");
//        command.setMerchAccts(allSet);
//        command.setCardBrands(allList);
//        command.setCurrencyList(allList);
//        command.setTrxTypes(allSet);

        //command.setInputModes(_allList);
        Integer[] _trxStatus = new Integer[1];
        _trxStatus[0] = 2;
//        command.setTrxTypes(allSet);
//        command.setStatusCodes(_trxStatus);
        // get the user amount format setting
        String amountFormat = "#.000";

        int toltalCount = 0;
        List<DonutsData> donutsDatas = new ArrayList<>();
        for (SummaryData _data : _companyTrxSummary) {
            donutsDatas.add(new DonutsData(_data.getCurrency(), _data.getTotal()));

            toltalCount += _data.getTotal();
        }

        for (DonutsData _ddata : donutsDatas) {
            double _value = (_ddata.getValue() * 100) / toltalCount;
            _ddata.setValue(Math.round(_value));
        }
        //sort it
        Collections.sort(donutsDatas);

        List<SummaryData> top10Client = new ArrayList<>(10);
        if (_companyTrxSummary.size() > 10) {
            int _top10 = 0;
            for (DonutsData _donut : donutsDatas) {
                if (_top10 == 10) {
                    break;
                }
                for (SummaryData _Citem : _companyTrxSummary) {
                    if (_donut.getLabel().equals(_Citem.getCurrency())) {
                        top10Client.add(_Citem);
                    }
                }
                _top10++;

            }
        }

        String bizId = top10Client.get(0).getBizId();
        logger.info("loading trx for company id: " + bizId);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", bizId);
        params.put("sDate", sdate);
        params.put("eDate", _edate);
        // List<CtSummaryData> _merchantCharSummary = reportService.getTrxForMerchantChar(params);

        //logger.debug("toltalCount " + toltalCount + " _merchantCharSummary size: " + _merchantCharSummary.size());

        command.setBizId(bizId);


        ObjectMapper _jsonMapper = new ObjectMapper();
        //TODO: build own access
//        CtReportAccess rptAccess = reportService.buildAccess( user, locale, messageSource );

        WebUtils.setSessionAttribute(req, "amountFormat", amountFormat);
//        WebUtils.setSessionAttribute( req, "rptAccess", rptAccess );
        WebUtils.setSessionAttribute(req, "uniqueBrands", uniqueBrands);
        WebUtils.setSessionAttribute(req, "companyTrxSummary", top10Client);
        WebUtils.setSessionAttribute(req, "merchantTrxSummary", null);
        WebUtils.setSessionAttribute(req, "donutsData", _jsonMapper.writeValueAsString(donutsDatas));
        //WebUtils.setSessionAttribute( req, "barCharData", _jsonMapper.writeValueAsString(_merchantCharSummary) );
//        WebUtils.setSessionAttribute( req, "companyList", rptAccess.getCompanyList());

        return new ModelAndView("general/dashboard", "command", command);

    }

    @RequestMapping(value = "{userId}/{companyId}/loadMtrx", method = RequestMethod.GET)
    public String loadMerchantTrxVolume(@PathVariable("userId") String userId,
                                        @PathVariable("companyId") String companyId, HttpServletRequest req,
                                        HttpServletResponse res) {
        Date _sdate = (Date) WebUtils.getSessionAttribute(req, "startDate");
        Date _edate = (Date) WebUtils.getSessionAttribute(req, "endDate");
        logger.info(" loadMerchantTrxVolume " + "companyId= " + companyId + " _sdate " + _sdate);

        Map<String, Object> _outJson = new HashMap<>();
        Map<String, Object> _params = new HashMap<>();
        _params.put("startDate", _sdate);
        _params.put("endDate", _edate);
        _params.put("companyId", companyId);
        List<SummaryData> _merchantCharSummary = null;
        StringBuilder result = new StringBuilder();
        try {
            _merchantCharSummary = reportService.getTrxForMerchantChar(_params);
            _outJson.put("data", _merchantCharSummary);
            _outJson.put("total", _merchantCharSummary.size());
            ObjectMapper _jsonMapper = new ObjectMapper();
            result.append(_jsonMapper.writeValueAsString(_outJson));
            //result.append(_jsonMapper.writeValueAsString(_merchantData));
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                // return JSON object
                res.setContentType(TCOfficeConstants.RES_CONTENT_TYPE);
                res.getWriter().write(result.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
        }

        return null;


    }

    /**
     * convert to language accordingly.
     *
     * @param trxTypes_
     * @param messageSource_
     * @param locale_
     * @return
     */
    private List<BasicItem> convertItemMessage(List<BasicItem> trxTypes_,
                                               MessageSource messageSource_, Locale locale_) {
        List<BasicItem> _items = new ArrayList<>(trxTypes_.size());
        for (BasicItem _item : trxTypes_) {
            String _localType = _item.getCode();
            _item.setName(messageSource_.getMessage(_localType, null, locale_));
            _items.add(_item);
        }
        return _items;
    }


    /**
     * load unread message
     *
     * @param company_id
     * @param merchantId_
     * @return
     */
    private MessageInfo loadMessage(String company_id, String merchantId_) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setUnreadCnt(1);
        messageInfo.setAtLeastOneForceMess(true);
        messageInfo.setMessages(new ArrayList<Message>());

        return messageInfo;
    }


}
