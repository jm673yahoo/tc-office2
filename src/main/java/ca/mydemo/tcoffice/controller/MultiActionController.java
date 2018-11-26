package ca.mydemo.tcoffice.controller;

import ca.mydemo.tcoffice.TCOfficeConstants;
import ca.mydemo.tcoffice.domain.I18NMessage;
import ca.mydemo.tcoffice.domain.profile.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class MultiActionController {

    Logger logger = LogManager.getLogger(this.getClass());

    protected static final SimpleDateFormat sdf = new SimpleDateFormat(TCOfficeConstants.DEFAULT_DATE_FORMAT);

    @Autowired
    protected MessageSource messageSource;

    /**
     * Builds a JSON object that contains a list of error messages per field name.
     * It is basicaly a map of name-value pairs that is then used for display on the UI.
     *
     * @@return a JSON object
     */
    protected String convertValidationErrors2JSON(BindingResult bindingResult, String classLID,
                                                  MessageSource messageSource, Locale locale)
            throws Exception {
        FieldError err;
        ObjectMapper _mapper = new ObjectMapper();
        ObjectNode json = _mapper.createObjectNode();
        json.put(TCOfficeConstants.JSON_RESULT, TCOfficeConstants.JSON_VAL_ERR);

        List errs = bindingResult.getAllErrors();
        String fieldName;

        for (int i = 0; i < errs.size(); i++) {
            err = (FieldError) bindingResult.getAllErrors().get(i);
            fieldName = err.getField();

            // if the fieldName contains a dot '.', them surround it with single
            // quotes so that any jQuery validation works properly
            // this was done with upgrade to jQuery 1.7.1
            if (StringUtils.contains(fieldName, ".")) {
                fieldName = "'" + fieldName + "'";
            }

            json.put(fieldName,
                    messageSource.getMessage(err.getCode(), err.getArguments(), locale));
        }

        return _mapper.writeValueAsString(json);
    }

    /**
     * createInternalJSONError
     *
     * @param message
     * @return
     */
    protected String createInternalJSONError(String message) {
        return createJSON(message, TCOfficeConstants.JSON_INT_ERR);
    }

    /**
     * Creates a JSONObject with a result type of INTERNAL_ERROR with the
     * specified error message.
     *
     * @param message the message to include
     * @return a JSON object
     */
    protected String createJSON(String message, String result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode _node = mapper.createObjectNode();
            _node.put(TCOfficeConstants.JSON_RESULT, result);
            _node.put(TCOfficeConstants.JSON_MESSAGE, message);
            return mapper.writeValueAsString(_node);
        } catch (IOException ex) {
            logger.error("Unable to generate JSON object.", ex);
            return "";
        }
    }

    /**
     * Creates a JSONObject with a result type of VALIDATION_ERROR with the
     * specified error message.
     *
     * @param message the message to include
     * @return a JSON object
     */
    protected String createValidationJSONError(String message) {
        try {
            ObjectMapper _mapper = new ObjectMapper();
            ObjectNode jsonNode = _mapper.createObjectNode();
            jsonNode.put(TCOfficeConstants.JSON_RESULT, TCOfficeConstants.JSON_VAL_ERR);
            jsonNode.put(TCOfficeConstants.JSON_MESSAGE, message);

            return _mapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            logger.error("Unable to generate JSON object.", e);
            return "";
        }
    }

    /**
     * Makes sure the account number provided belows to the user
     *
     * @param req     the current HTTP request
     * @param acctNbr the account number to validate
     * @throws SecurityException thrown if the account does not belong to the user
     */
    protected void validateAccount(HttpServletRequest req, String acctNbr) {
        User user = (User) WebUtils.getSessionAttribute(req, TCOfficeConstants.SESSION_USER);
//    UserFmaVO acct;
//
//    for( int i = 0; i < user.getFmaAccounts().size(); i++ )
//    {
//      acct = (UserFmaVO) user.getFmaAccounts().get( i );f
//
//      if( StringUtils.equals(acctNbr, acct.getAcctNumber()) )
//      {
//        return;
//      }
//    }

//    throw new SecurityException("Account " + acctNbr
//                      + " does not belong to " + user.getLoginName());
    }

    /**
     * Validates that the date range provided is valid and within allowed range.
     */
    protected void validateDateRange(HttpServletRequest req,
                                     String sDate, String eDate, int maxRangeInMonths) throws Exception {
        Locale locale = RequestContextUtils.getLocale(req);
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = sdf.parse(sDate);
            endDate = sdf.parse(eDate);
        } catch (ParseException e) {
            throw new Exception(
                    messageSource.getMessage(I18NMessage.ERROR_DT_FORMAT_INVALID, null, locale));
        }

        // validate the date range provided
        if (startDate.after(endDate)) {
            throw new Exception(
                    messageSource.getMessage(I18NMessage.ERROR_DT_RANGE_INVALID, null, locale));
        }

        Calendar earliestDate = Calendar.getInstance();
        earliestDate.setTime(endDate);
        earliestDate.add(Calendar.MONTH, -maxRangeInMonths);

        if (startDate.before(earliestDate.getTime())) {
            Object[] args = new Object[]{Integer.toString(maxRangeInMonths)};
            throw new Exception(
                    messageSource.getMessage(I18NMessage.ERROR_DT_RANGE_TOO_LONG, args, locale));
        }
    }

    /**
     * Binds the incoming request data to the command object provided.
     */
    protected void bind(HttpServletRequest request, Object command) throws Exception {
//        ServletRequestDataBinder binder = createBinder(request, command);
//        binder.bind(request);
    }

    /**
     * Validates the command object provided using the appropriate validators.
     *
     * @param command
     */
    public void validate(BindingResult bindingResult, Object command) {
//        Validator[] validators = getValidators();
//
//        if( validators != null )
//        {
//            for( int index = 0; index < validators.length; index++ )
//            {
//                Validator validator = validators[index];
//
//                if( validator.supports(command.getClass()) )
//                {
//                    ValidationUtils.invokeValidator(validator, command, bindingResult);
//                }
//            }
//        }
    }

}
