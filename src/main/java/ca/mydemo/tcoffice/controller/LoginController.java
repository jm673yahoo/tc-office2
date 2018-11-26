package ca.mydemo.tcoffice.controller;

import ca.mydemo.tcoffice.TCOfficeConstants;
import ca.mydemo.tcoffice.domain.I18NMessage;
import ca.mydemo.tcoffice.domain.LoginMessage;
import ca.mydemo.tcoffice.service.StaticDataService;
import ca.mydemo.tcoffice.service.UserService;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author mmiao
 */
@Controller
@RequestMapping("/")
public class LoginController {
    private static final String LOGIN_ERROR_MESS = "errorMessage";

    private Logger logger = LogManager.getLogger(this.getClass());

    private static String versionAndBuild;

    private static final String ALLOW_MULTI_LINGUAL = "ALLOW_MULTI_LINGUAL";

    private int minFailedAttempts4PwdRecovery = 2;
    private int minFailedAttempts = 3;
    private int maxFailedAttempts = 6;
    private int minPasswordLength = 8;

    private static final List<String> activeAccts = new ArrayList<>();

    @Autowired
    private UserService userService;
    @Autowired
    private StaticDataService staticDataService;

    @Autowired
    private MessageSource messageSource;

    static {
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_SUBMITTED);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_WAITING);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_REJECTED);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_DEFERRED);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_FUNDED);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_ENABLED);
        activeAccts.add(TCOfficeConstants.ACCT_STATUS_DISABLED);
    }


    @RequestMapping("preLogin.htm")
    public ModelAndView preLogin(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        try {
            // get any error message in session for display purposes.
            String err = (String) WebUtils.getSessionAttribute(req, LOGIN_ERROR_MESS);
            if (StringUtils.isBlank(err)) {
                err = req.getParameter(LOGIN_ERROR_MESS);
            }

            req.getSession().invalidate();

            // init and store certain values in the session
            initTheme(req, res);

            String siteLang = req.getParameter(TCOfficeConstants.LANG_SITE);
            logger.info("_siteLang = {} ", siteLang);

            if (StringUtils.isBlank(siteLang)) {
                req.setAttribute("siteLang", "en_US");
            }

            Locale locale = LocaleUtils.toLocale(siteLang);
            WebUtils.setSessionAttribute(req, "locale", locale);

//            MessageSource messageSource = RequestContextUtils.getTheme(req).getMessageSource();
            String multiLingualFlag = messageSource.getMessage(ALLOW_MULTI_LINGUAL, null, locale);
            WebUtils.setSessionAttribute(req, I18NMessage.TITLE,
                    messageSource.getMessage(I18NMessage.TITLE, null, locale));
            WebUtils.setSessionAttribute(req, I18NMessage.FAV_ICON,
                    messageSource.getMessage(I18NMessage.FAV_ICON, null, locale));
            logger.info("multiLingualFlag {}", multiLingualFlag);
            multiLingualFlag = "true";
            LoginMessage cmd = new LoginMessage();
            cmd.setAllowMultiLingual(Boolean.valueOf(multiLingualFlag));
            cmd.setVersionAndBuild(versionAndBuild);

            if (StringUtils.isNotEmpty(err)) {
                cmd.setErrorMessage(err);
            }

            return new ModelAndView("login", "command", cmd);
        } catch (Exception e) {
            logger.error("failed!", e);
            return new ModelAndView("critical_error");
        }
    }

    /**
     * the theme initialization variables to be used after a successful login.
     */
    private void initTheme(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String ipAddr = (String) WebUtils.getSessionAttribute(req, TCOfficeConstants.SESSION_IP);

        // get theme name from URL name
        versionAndBuild = MavenVersionReader.readVersions();

        WebUtils.setSessionAttribute(req, "version", versionAndBuild);

        WebUtils.setSessionAttribute(req, "versionAndBuild", versionAndBuild);
        WebUtils.setSessionAttribute(req, TCOfficeConstants.SESSION_IP, ipAddr);

    }


    /**
     * Terminates the current session and returns the user to the login page.
     */
    @RequestMapping("critical_error")
    public ModelAndView criticalError(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        req.getSession().invalidate();
        return new ModelAndView("redirect:critical_error.htm", "errorMessage", "");
    }

    @RequestMapping("signOff")
    public ModelAndView signOff(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        req.getSession().invalidate();
        return new ModelAndView("redirect:preLogin.htm", "errorMessage", "");
    }


    /**
     * Builds some permissions based on values in the branding property files.
     */
    private void buildBrandingPermissions(HttpServletRequest req,
                                          MessageSource messageSource, Locale locale) {
        String flag = messageSource.getMessage(TCOfficeConstants.ALLOW_MULTI_LINGUAL_SYS, null, locale);
        WebUtils.setSessionAttribute(req, TCOfficeConstants.ALLOW_MULTI_LINGUAL_APP, Boolean.valueOf(flag));

        flag = messageSource.getMessage(TCOfficeConstants.ALLOW_MONTHLY_STMT_SYS, null, locale);
        WebUtils.setSessionAttribute(req, TCOfficeConstants.ALLOW_MONTHLY_STMT_APP, Boolean.valueOf(flag));

    }


}
