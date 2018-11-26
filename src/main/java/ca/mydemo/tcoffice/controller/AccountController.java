package ca.mydemo.tcoffice.controller;


import ca.mydemo.tcoffice.domain.Client;
import ca.mydemo.tcoffice.domain.I18NMessage;
import ca.mydemo.tcoffice.domain.Opportunity;
import ca.mydemo.tcoffice.domain.profile.User;
import ca.mydemo.tcoffice.domain.profile.UserType;
import ca.mydemo.tcoffice.service.ClientService;
import ca.mydemo.tcoffice.utils.OfficeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class AccountController extends MultiActionController {

    private Logger loggera = LogManager.getLogger(this.getClass());

    @Autowired
    ClientService clientService;


    @RequestMapping(value = "loadClients.htm", method = RequestMethod.POST)
    public ModelAndView loadClientAccounts(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        Locale locale = (Locale) WebUtils.getSessionAttribute(req, "locale");

        WebUtils.setSessionAttribute(req, I18NMessage.TITLE,
                messageSource.getMessage(I18NMessage.TITLE, null, locale));
        WebUtils.setSessionAttribute(req, "access", OfficeUtil.buildTabAccess());

        Date from = Calendar.getInstance().getTime();
        Date to = Calendar.getInstance().getTime();
        User user = buildUser();
        List<Client> clients = clientService.loadClients(from, to);
        loggera.info("==========load all clients size={}", clients.size());
        Map<String, Object> model = new HashMap<>();

        model.put("user", user);
        model.put("clientsData", clients);
        model.put("startDate", from);
        model.put("endDate", to);
        return new ModelAndView("account/clientSummary", model);
    }

    @RequestMapping(value = "showClients.htm", method = RequestMethod.GET)
    public ModelAndView showClients(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        Locale locale = (Locale) WebUtils.getSessionAttribute(req, "locale");

        if (locale == null) {
            locale = Locale.CANADA;
        }

        WebUtils.setSessionAttribute(req, I18NMessage.TITLE,
                messageSource.getMessage(I18NMessage.TITLE, null, locale));
        WebUtils.setSessionAttribute(req, "access", OfficeUtil.buildTabAccess());

        Date from = Calendar.getInstance().getTime();
        Date to = Calendar.getInstance().getTime();
        User user = buildUser();
        Map<String, Object> model = new HashMap<>();

        model.put("user", user);
        model.put("startDate", from);
        model.put("endDate", to);
        return new ModelAndView("source/clientSummary", model);
    }

    @RequestMapping(value = "showClientLookup.htm", method = RequestMethod.GET)
    public ModelAndView showClientLookup(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        Locale locale = (Locale) WebUtils.getSessionAttribute(req, "locale");

        if (locale == null) {
            locale = Locale.CANADA;
        }

        WebUtils.setSessionAttribute(req, I18NMessage.TITLE,
                messageSource.getMessage(I18NMessage.TITLE, null, locale));
        WebUtils.setSessionAttribute(req, "access", OfficeUtil.buildTabAccess());

        Date from = Calendar.getInstance().getTime();
        Date to = Calendar.getInstance().getTime();
        User user = buildUser();
        Map<String, Object> model = new HashMap<>();

        model.put("user", user);
        model.put("startDate", from);
        model.put("endDate", to);
        return new ModelAndView("source/clientActive", model);
    }

    @RequestMapping(value = "showClientDetail.htm", method = RequestMethod.GET)
    public ModelAndView showClientDetail(HttpServletRequest req, HttpServletResponse res, @RequestParam int bizId)
            throws Exception {

        Locale locale = (Locale) WebUtils.getSessionAttribute(req, "locale");

        if (locale == null) {
            locale = Locale.CANADA;
        }

        WebUtils.setSessionAttribute(req, I18NMessage.TITLE,
                messageSource.getMessage(I18NMessage.TITLE, null, locale));
        WebUtils.setSessionAttribute(req, "access", OfficeUtil.buildTabAccess());

        Date from = Calendar.getInstance().getTime();
        Date to = Calendar.getInstance().getTime();
        User user = buildUser();

        Client data = clientService.getClient(bizId);
        loggera.info("==========get all clients size={}", data);

        List<Opportunity> opportunities = clientService.loadOpportunity(bizId);
        loggera.info("==========get all clients size={}", opportunities);

        Map<String, Object> model = new HashMap<>();

        model.put("startDate", from);
        model.put("endDate", to);
        model.put("bizDetail", data);
        model.put("opportunities", opportunities);
        return new ModelAndView("account/accountDetail2", model);
    }

    @RequestMapping(value = "source/getClients.htm", method = RequestMethod.GET)
    public @ResponseBody
    String getClients(HttpServletRequest req, HttpServletResponse res) throws Exception {


        Date from = Calendar.getInstance().getTime();
        Date to = Calendar.getInstance().getTime();

        List<Client> data = clientService.loadClients(from, to);
        loggera.info("==========get all clients size={}", data.size());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(data);
    }

    User buildUser() {
        User user = new User();
        user.setLoginId("mmiao");
        user.setFirstName("Mike");
        user.setLastName("Miao");

        user.setType(UserType.SYSADMIIN);

        return user;
    }
}
