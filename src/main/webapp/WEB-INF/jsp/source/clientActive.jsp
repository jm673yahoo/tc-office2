<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../fragments/taglib.jspf" %>

<html>
<head>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/commonjs.jspf" %>
    <jsp:include page="../fragments/docs.jsp" flush="true"></jsp:include>
    <jsp:include page="../fragments/contact.jsp" flush="true"></jsp:include>

    <c:if test="${userType == 'SYSADMIN'}">
        <jsp:include page="../fragments/filterMerchantList.jsp" flush="true"></jsp:include>
    </c:if>
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <script src="../js/moment-with-locales.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.min.js"></script>
    <!-- report Scripts - Include with every page -->
    <script src="../js/jquery.multiselect.js"></script>
    <script src="../js/reports.js"></script>

    <script type="text/javascript">
        // variables
        var ERROR_BAD_MERCHACCT_NBR = "<spring:message code='ERROR_BAD_ACCT_NBR'/>";
        var ERROR_BAD_ACCTGROUP_NBR = "<spring:message code='ERROR_BAD_ACCTGROUP_NBR'/>";


        var btnSummaryLabel = "<spring:message code='BTN_SUMMARY'/>";
        var serverAcctNbr;

        function initPage() {

            //init controls
            $("#sDate").datetimepicker();
            $("#eDate").datetimepicker();
            var sDate = $("#sDate");
            var eDate = $("#eDate");
            var userType = '<c:out value="{_userType}"/>';
            $.unblockUI();
            $(window).bind("pageshow", function () {
                $.unblockUI();
            });

            $('#groupded').multiselect();

            // get our form field object that was cached in the browser local storage
            var cachedParamsJSON = StorageUtils.getSSValue("ct_rpt_activity");
            var cachedParams = $.parseJSON(cachedParamsJSON);

            // if our cached form values are empty, then create dummy object
            if (cachedParams == null) {
                cachedParams = {};
                cachedParams.activityType = "SUMMARY";
                cachedParams.timezone = "America/Montreal";
                cachedParams.merchAccts = [];
                cachedParams.sDate = formatDate(today, "yyyy-MM-dd", "en");
                cachedParams.eDate = formatDate(today, "yyyy-MM-dd", "en");
                cachedParams.eHour = 23;
                cachedParams.eMin = 59;
            }

            $("#btnSearch").attr("value", btnSummaryLabel);
            $("#tabReport").addClass('active');


            $(".helpTip").tooltip({
                position: "center right",
                offset: [-2, 10],
                effect: "fade"
            });

            // set date control language
            if (currentLang == "en" || currentLang == "en_US") {
                //  $.datepicker.setDefaults($.datepicker.regional['']);
            }
            else {
                //   sDate.datepicker( "option", $.datepicker.regional[currentLang] );
                //  eDate.datepicker( "option", $.datepicker.regional[currentLang] );
            }

            //   sDate.datepicker( "option", "dateFormat", "yy-mm-dd" );
            //   sDate.datepicker( "option", "maxDate", today );
            //   eDate.datepicker( "option", "dateFormat", "yy-mm-dd" );
            //   eDate.datepicker( "option", "maxDate", today );

            // loop thru and set values for all form elements
            for (fName in cachedParams) {
                if (fName != "csrfToken" && fName != "activityType") {
                    $("#" + fName).val(cachedParams[fName]);
                }
            }

            serverAcctNbr = cachedParams.merchAccts;

            if (userType == "EXT") {

            }
            else {

                $("#merchAcctField").css("height", "30px");
                $("#providedAcct").show().val(serverAcctNbr);

                if (getTerminalRadioValue() == 'terminals') {
                    $("#terminalNoInput").hide();
                    $("#terminaNolable").hide();
                    $("#terminalsInput").show();
                    $("#terminalslable").show();
                } else {
                    $("#terminalsInput").hide();
                    $("#terminalslable").hide();
                    $("#terminaNolable").show();
                    $("#terminalNoInput").show();
                }
            }

        }//end of initPage

        // callback function for the findMerchAcctsPage
        function findBatchCallback(acctNbr) {
            if (userType == 'SYSADMIN') {
                $("#providedAcct").val(acctNbr);
            }
            else if (userType == 'EXT' && acctNbr != 'undefine') {
                if (!$.browser.msie) {
                    $("#merchAccts").removeAttr("multiple");
                }

                var extAcctList = $("#merchAccts")[0];

                $.each(extAcctList, function (index, value) {
                    var alistItem = extAcctList[index];
                    alistItem.selected = false;
                });

                $.each(extAcctList, function (index, value) {
                    var alistItem = extAcctList[index];
                    if (alistItem.value == acctNbr) {
                        alistItem.selected = true;
                    }
                });

                if (!$.browser.msie) {
                    $("#merchAccts").attr("multiple", "true");
                }

            }
        }

        // callback function for the findTerminalPage
        function findTerminalCallback(terminalId, terminalName) {
            $("#terminal").val(terminalId);
        }

    </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="../fragments/docs.jsp" flush="true"></jsp:include>
    <jsp:include page="../fragments/contact.jsp" flush="true"></jsp:include>
    <%@ include file="../fragments/north.jspf" %>
    <%@ include file="../fragments/west.jspf" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="panel panel-default" style="border-color:#f8f8f8; min-height: 45em;">
                <div class="panel-heading"><span class="panelHeader"><spring:message code='SOURCE_SUMMARY'/></span>
                    <h5 id="errorMessage" class="errorBox" hidden='true'></h5>
                </div>
                <form:form id="reportForm" method="post" commandName="command" action="summaryReport.htm">
                <input type="hidden" id="csrfToken" name="csrfToken" value="${_csrf.token}"/>
                <input type="hidden" id="merchAcct" name="merchAcct"/>
                <input type="hidden" id="activityType" name="activityType" value="SUMMARY"/>
                <input type="hidden" id="_companyId" name="companyId"/>
                <table width="100%">
                    <tr>
                        <td colspan="2" class="pod podContent">
                            <table id='searchGeneral' style="width: 100%;">
                                <tr>
                                    <td class="searchLabel"><spring:message code='DATE_RANGE'/></td>
                                    <td class="searchField">
                                        <table style="width: 960px; display: inline;">
                                            <tr>
                                                <td>
                                                    <div class='input-group date' id="sDate">
                                                        <input name="sDate" type='text' class="form-control"
                                                               style="width:14.0em; height:25px;"/>
                                                        <span class="input-group-addon"
                                                              style="padding: 2px 2px; margin:0px; ">
					                            <span class="glyphicon glyphicon-calendar"></span>
                                               </span>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div style="padding-left:1em; width: 130px; float: left;">
                                                        <spring:message code='QUICK_RANGES'/>
                                                    </div>
                                                    <div style="float: left; font-size:90%;">
                                                        <a href="javascript:quickDate( TODAY )"><spring:message
                                                                code='TODAY'/></a>
                                                        <span class="quickDates">/</span>
                                                        <a href="javascript:quickDate( YESTERDAY )"><spring:message
                                                                code='YESTERDAY'/></a>
                                                        <span class="quickDates">/</span>
                                                        <a href="javascript:quickDate( LAST7_DAYS )"><spring:message
                                                                code='LAST7_DAYS'/></a>
                                                        <span class="quickDates">/</span>
                                                        <a href="javascript:quickDate( CURR_MONTH )"><spring:message
                                                                code='CURR_MONTH'/></a>
                                                        <span class="quickDates">/</span>
                                                        <a href="javascript:quickDate( LAST_MONTH )"><spring:message
                                                                code='LAST_MONTH'/></a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspn="2">
                                                    <div class='input-group date' id="eDate">
                                                        <input name="eDate" type='text' class="form-control"
                                                               style="width:14.0em; height:25px;"/ />
                                                        <span class="input-group-addon"
                                                              style="padding: 2px 2px; margin:0px; ">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                       </span>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                </td>
                                </tr>
                            </table>

                            <div style="height: 35px; margin-top: 10px;">
                                <div style="width: 50%; float: left;">
                                    <input type="button" onclick="resetActivitySearch()"
                                           value="<spring:message code='BTN_RESET'/>"/>
                                </div>
                                <div style="width: 50%; float: left; text-align: right;">
                                    <input id="btnSearch" type="button" onclick="doActivitySearch()"/>
                                </div>
                            </div>

                            <c:if test="${command.errorMessage != '' && command.errorMessage != null}">
                            <p class="errorBox">
                                <c:out value="${command.errorMessage}" escapeXml="true"/>
                            </p>
                            </c:if>

                            </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
