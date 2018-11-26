<%@ include file="../fragments/taglib.jspf" %>
<script src="../js/moment-with-locales.min.js"></script>
<link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="../js/bootstrap-datetimepicker.min.js"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


<div id="contactWindow" class="modal fade" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="background-color:#ffffff;">
        <!-- Header-->
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&times;</span>
                <span class="sr-only">Close</span>
            </button>
            <h2 class="modal-title">Contact Us - CTPay Office</h2>
        </div>
        <div class="modal-content" style="border-radius: 1px;">
            <div class="panel-body">
                <div>
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#contactTabs-1" data-toggle="tab"><spring:message
                                code='TAB_CONTACT_FORM'/></a></li>
                        <li><a href="#contactTabs-2" data-toggle="tab"><spring:message code='TAB_CONTACT_NBRS'/></a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="contactTabs-1">
                            <h4><spring:message code='TAB_CONTACT_FORM'/></h4>
                            <p><spring:message code='INS_SUP_FORM2'/></p>
                            <p><spring:message code='INS_REQUIRED_FIELD'/></p>

                            <form id="contactForm" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <table>
                                    <col width="130"/>
                                    <col width="520"/>

                                    <tr>
                                        <td>
                                            <spring:message code='COL_NAME'/>
                                            <span class="requiredStar">*</span>
                                        </td>
                                        <td>
                                            <input type="text" id="contactName" name="contactName"
                                                   size="25" maxlength="40" required="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <spring:message code='TXT_PHONE'/>
                                            <span class="requiredStar">*</span>
                                        </td>
                                        <td>
                                            <input type="phone" id="contactPhone" name="contactPhone"
                                                   size="25" maxlength="40" required="true"
                                                   placeholder="514-111-0000"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <spring:message code='TXT_EMAIL'/>
                                            <span class="requiredStar">*</span>
                                        </td>
                                        <td>
                                            <input type="email" id="contactEmail" name="contactEmail"
                                                   size="50" maxlength="40" required="true"
                                                   placeholder="test@ctcompany.com"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <spring:message code='TXT_DESCRIPTION'/>
                                            <span class="requiredStar">*</span>
                                        </td>
                                        <td>
          <textarea id="contactDesc" name="contactDesc"
                    rows="4" cols="70" required="true"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <spring:message code='TXT_ACCOUNT'/>
                                        </td>
                                        <td>
                                            <select id="contactAcct" name="contactAcct" style="width: 350px;">

                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code='TXT_CARD_INFO'/></td>
                                        <td>
                                            <select id="contactBrand" name="contactBrand">
                                                <option value=""></option>
                                                <option value="AM">American Express</option>
                                                <option value="DI">Discover</option>
                                                <option value="MD">Maestro</option>
                                                <option value="MC">MasterCard</option>
                                                <option value="VI">Visa</option>
                                                <option value="VE">Visa Electron</option>
                                            </select>
                                            <input type="text" id="contactLast4" name="contactLast4" size="5"
                                                   maxlength="4"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code='TXT_CONF_NBR'/></td>
                                        <td>
                                            <input type="text" id="contactConfNbr" name="contactConfNbr" size="20"
                                                   maxlength="20"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code='TXT_TXN_DATE'/></td>
                                        <td>
                                            <div class='input-group date' id="contactTxnDate">
                                                <input type='text' name="contactTxnDate" class="form-control"
                                                       style="width:14.0em; height:25px;"/>
                                                <span class="input-group-addon" style="padding: 2px 2px; margin:0px; ">
             <span class="glyphicon glyphicon-calendar"></span>
            </span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="right">
                                            <input type="button"
                                                   value="<spring:message code='BTN_SUBMIT'/>"
                                                   onclick="postContactForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div class="tab-pane fade" id="contactTabs-2">
                            <h4><spring:message code='TAB_CONTACT_NBRS'/></h4>
                            <p><spring:message code='INS_SUP_FORM1'/></p>
                            <spring:message code='SUPPORT_EMAIL'/>
                            <br/>
                            <spring:message code='SUPPORT_PHONE'/>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>
