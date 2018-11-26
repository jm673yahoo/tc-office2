<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../fragments/taglib.jspf" %>

<html>
<head>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/commonjs.jspf" %>
    <script type="text/javascript">

        $("#tabSource").addClass('active');

        // variables
        function initPage() {
            var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            var csrfToken = $("meta[name='_csrf']").attr("content");

        }

        $('#list-tab a[href="#list-home"]').on('click', function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
        $('#list-tab a[href="#aoc"]').on('click', function (e) {
            e.preventDefault()
            $(this).tab('show')
        });

    </script>

    <style>
        .splitter {
            flex: 0 0 auto;
            width: 18px;
            background: url(https://raw.githubusercontent.com/RickStrahl/jquery-resizable/master/assets/vsizegrip.png) center center no-repeat #535353;
            min-height: 200px;
            cursor: col-resize;
        }

        .splitter-horizontal {
            flex: 0 0 auto;
            height: 15px;
            background: #f1f1f1;
            cursor: row-resize;
        }

        .panel-container-vertical {
            display: flex;
            flex-direction: column;
            height: 500px;
            border: 1px solid silver;
            overflow: hidden;
        }

        .panel-top {
            flex: 0 0 auto;
            /* only manually resize */
            padding: .5em;
            height: 46em;
            width: 100%;
            white-space: nowrap;
            color: #00396B;
            resize: vertical;
            overflow: auto;
        }

        .panel-bottom {
            flex: 1 1 auto;
            /* resizable */
            padding: .5em;
            min-height: 20em;
        }

        .panel-right {
            flex: 1 1 auto;
            /* resizable */
            padding: 10px;
            width: 100%;
            min-height: 30em;
            min-width: 20em;
        }

        .panel-left {
            flex: 0 0 auto;
            /* only manually resize */
            padding: 10px;
            width: 300px;
            min-height: 30em;
            min-width: 30em;
            white-space: nowrap;
            resize: horizontal;
            overflow: auto;
        }

        .lower-panel-right {
            flex: 1 1 auto;
            /* resizable */
            padding: 10px;
            width: 100%;
            min-height: 30em;
            min-width: 40em;
            color: #662222;
            background: #eeeeee;
        }

        .lower-panel-left {
            flex: 0 0 auto;
            /* only manually resize */
            padding: 10px;
            width: 300px;
            min-height: 30em;
            min-width: 43em;
            white-space: nowrap;
            resize: horizontal;
            overflow: auto;
        }

        .panel-container {
            display: flex;
            flex-direction: row;
            border: 1px solid silver;
            overflow: hidden;

            /* avoid browser level touch actions */
            xtouch-action: none;
        }

        .card {
            background: #FFF none repeat scroll 0% 0%;
            box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3);
            margin-bottom: 30px;
        }
    </style>
</head>

<body>
<jsp:include page="../fragments/docs.jsp" flush="true"></jsp:include>
<jsp:include page="../fragments/contact.jsp" flush="true"></jsp:include>
<%@ include file="../fragments/north.jspf" %>
<%@ include file="../fragments/west.jspf" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Detail Information</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <p style="float:right;">
                    <button type="button" class="btn btn-danger">Decline</button>
                    <button type="button" class="btn btn-primary">Fund</button>
                    <button type="button" class="btn btn-primary btn-circle" ata-toggle="tooltip" data-placement="left"
                            title="print"><i class="fa fa-list"></i></button>
                </P>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel-top" id="panel-top">
            <div class="tabbable-line">
                <ul class="nav nav-tabs" style="padding-left: 1.3em;">
                    <li class="active"><a class="menuItemCSS" href="#tab_default_1" data-toggle="tab">Biz Info</a></li>
                    <li><a class="menuItemCSS" href="#tab_default_2" data-toggle="tab">Owners</a></li>
                    <li><a class="menuItemCSS" href="#tab_default_3" data-toggle="tab">UpLoads</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab_default_1">
                        <form>
                            <div class="col-lg-12">
                                <div class="well">
                                    <div class="row show-grid" style="font-size:1.15em;">
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_ID'/>:</labe>
                                            <input name="bizId" style="width:15em;"
                                                   value="<c:out value="${bizDetail.businessId}" />" disabled/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_SRC'/>:</labe>
                                            <input name="leadSrc" style="width:10em;"
                                                   value="<c:out value="${bizDetail.leadSrcKey}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_NAME'/>:</labe>
                                            <input name="bizName" value="<c:out value="${bizDetail.bizName}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_LEGAL_NAME'/>:</labe>
                                            <input name="bizLegalName" style="width:10em;"
                                                   value="<c:out value="${bizDetail.bizLegalName}" /> "/></div>
                                    </div>
                                    <div class="row show-grid" style="font-size:1.15em;">
                                        <div class="col-xs-3">
                                            <labe><spring:message code='APP_UUID'/>:</labe>
                                            <input name="acctUUId" style="width:15em;"
                                                   value="<c:out value="${bizDetail.acctUUId}" />" disabled/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='AVG_MNTH_AMOUNT'/>:</labe>
                                            <input name="bizAvgMthAmt" style="width:6em;"
                                                   value="<c:out value="${bizDetail.bizAvgMthAmt}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='NBR_LOCATION'/>:</labe>
                                            <input name="nbrOfLocation" style="width:10em;"
                                                   value="<c:out value="${bizDetail.nbrOfLocation}"/> "/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='TXT_PHONE'/>:</labe>
                                            <input name="firstPhoneNbr"
                                                   value="<c:out value="${bizDetail.firstPhoneNbr}" />"/></div>

                                    </div>
                                    <div class="row show-grid" style="font-size:1.15em;">
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_ADDR'/>:</labe>
                                            <input name="bizAddress" style="width:15em;"
                                                   value="<c:out value="${bizDetail.bizAddress}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_CITY'/>:</labe>
                                            <input name="bizCity" style="width:15em;"
                                                   value="<c:out value="${bizDetail.bizCity}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_PROVINCE'/>:</labe>
                                            <input name="bizProvinceCode" style="width:15em;"
                                                   value="<c:out value="${bizDetail.bizProvinceCode}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='BIZ_POSTCODE'/>:</labe>
                                            <input name="bizPostCode"
                                                   value="<c:out value="${bizDetail.bizPostCode}" />"/></div>
                                    </div>
                                    <div class="row show-grid" style="font-size:1.15em;">
                                        <div class="col-xs-3">
                                            <labe><spring:message code='PARTNER_CODE'/>:</labe>
                                            <input name="partnerId" style="width:10em;"
                                                   value="<c:out value="${bizDetail.partnerId}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='NBR_TERMINAL'/>:</labe>
                                            <input name="terminalNbr" style="width:5em;"
                                                   value="<c:out value="${bizDetail.terminalNbr}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='INDUST_CODE'/>:</labe>
                                            <input name="industryCode"
                                                   value="<c:out value="${bizDetail.industryCode}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='SUBINDUST_CODE'/>:</labe>
                                            <input name="subIndustryCode"
                                                   value="<c:out value="${bizDetail.subIndustryCode}" />"/></div>
                                    </div>
                                    <div class="row show-grid" style="font-size:1.15em;">
                                        <div class="col-xs-3">
                                            <labe><spring:message code='CUSTOMER_ID'/>:</labe>
                                            <input name="customerId" style="width:10em;"
                                                   value="<c:out value="${bizDetail.customerId}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='TIM_IN_BIZ'/>:</labe>
                                            <input name="timeInBizId" style="width:5em;"
                                                   value="<c:out value="${bizDetail.timeInBizId}" />"/></div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='PROCESSOR_CODE'/>:</labe>
                                            <input name="processor" value="<c:out value="${bizDetail.processor}" />"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <labe><spring:message code='PERCENT_DIB_CRDIT'/>:</labe>
                                            <input name="debCredPercent" style="width:6em;"
                                                   value="<c:out value="${bizDetail.debCredPercent}" />"/></div>
                                    </div>
                                    <div class="row show-grid" style="text-align:right">
                                        <button type="button" class="btn"><spring:message code='BTN_SUBMIT'/></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="tab_default_2">
                        <div class="panel-container">
                            <div class="panel-left">
                                <div class="col-md-12">
                                    <div class="list-group" id="list-tab" role="tablist">
                                        <a class="list-group-item list-group-item-action active" id="list-home-list"
                                           data-toggle="list" href="#" role="tab" aria-controls="home">
                                            Marco Simth - PRC <span class="badge badge-primary badge-pill">56</span></a>
                                        <a class="list-group-item list-group-item-action" id="list-profile-list"
                                           data-toggle="list" href="#" role="tab" aria-controls="profile">
                                            Mike Miao - AOC <span class="badge badge-primary badge-pill">25</span></a>
                                        <a class="list-group-item list-group-item-action" id="list-messages-list"
                                           data-toggle="list" href="#" role="tab" aria-controls="messages">
                                            Olege Soots - AOC 2 <span
                                                class="badge badge-primary badge-pill">15</span></a>
                                        <a class="list-group-item list-group-item-action" id="list-settings-list"
                                           data-toggle="list" href="#" role="tab" aria-controls="settings">
                                            Valadmir Koo - AOC 3 <span
                                                class="badge badge-primary badge-pill">5</span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-right">
                                <div class="col-md-12">
                                    <form:form>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputEmail4">Email</label>
                                                <input type="email" class="form-control" id="inputEmail4"
                                                       placeholder="Email">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="inputPassword4">Password</label>
                                                <input type="password" class="form-control" id="inputPassword4"
                                                       placeholder="Password">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-12">
                                                <label for="inputAddress">Address</label>
                                                <input type="input" class="form-control" id="inputAddress"
                                                       placeholder="1234 Main St">
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputCity">City</label>
                                                <input type="input" class="form-control" id="inputCity">
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="inputState">Province</label>
                                                <select id="inputState" class="form-control">
                                                    <option selected>Choose...</option>
                                                    <option>...</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="inputZip">Postcode</label>
                                                <input type="text" class="form-control" id="inputZip">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="relation">Relation Ship</label>
                                                <input type="input" class="form-control" id="relation"
                                                       placeholder="PRC">
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="mobile">Mobile</label>
                                                <input type="input" class="form-control" id="mobile"
                                                       placeholder="514-233-1234r">
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="percent">Percentage</label>
                                                <input type="text" class="form-control" id="percent" placeholder="56%">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="gridCheck"/>
                                                    <label class="form-check-label" for="gridCheck">
                                                        Email alert me
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <input id="contactUpdate" type="button" onclick="doUpdate()"
                                                       value="<spring:message code='BTN_SUBMIT'/>"/>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab_default_3">
                        <ul class="list-group">
                            <li class="list-group-item disabled">Bank statement (2018/01/20)
                                <span class="badge badge-primary badge-pill">5</span></li>
                            <li class="list-group-item">Company register document</li>
                            <li class="list-group-item">LandLord Leasing Form</li>
                            <li class="list-group-item">Porta ac consectetur ac</li>
                            <li class="list-group-item">Vestibulum at eros</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="splitter-horizontal"></div>
    <div class="panel-bottom">
        <!-- Nav tabs -->
        <div class="card">
            <ul class="nav nav-pills" role="tablist">
                <li role="presentation" class="active"><a href="#opportunity" aria-controls="opportunity" role="tab"
                                                          data-toggle="tab">Opportunity</a></li>
                <li role="presentation"><a href="#quoting" aria-controls="quoting" role="tab"
                                           data-toggle="tab">Quoting</a></li>
                <li role="presentation"><a href="#agreement" aria-controls="agreement" role="tab" data-toggle="tab">Agreement</a>
                </li>
                <li role="presentation"><a href="#uploaddocs" aria-controls="uploaddocs" role="tab" data-toggle="tab">Upload
                    Docs</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="opportunity">
                    <div class="panel-container">
                        <div class="lower-panel-left">
                            <h3>Applications</h3>

                            <ol> <!-- ordered list -->
                                <c:forEach items="${opportunities}" var="opports">
                                    <c:set var="contact" value="${opports.contact}"/>

                                    <li><c:out value="${opports.applicationNbr}"/> @
                                        <span>
                                  <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                  value="${opports.createdDate}"/>
                                    </span></li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="lower-panel-right">
                            <!-- ./col -->
                            <div class="col-md-6">
                                <div class="box box-solid">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Detail Information</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body" style="font-size: medium">
                                        <dl class="dl-horizontal">
                                            <dt>Id:</dt>
                                            <dd><c:out value="${contact.conId}"/></dd>
                                            <dt>Phone&email:</dt>
                                            <dd><span><c:out value="${contact.email}"/></span> <span>mobile:
                                                 <c:out value="${contact.mobile}"/> </span> <span>Prefered Phone number: <c:out
                                                    value="${contact.preferPhone}"/></span></dd>
                                            <dt>Name:</dt>
                                            <dd><c:out value="${contact.firstName}"/> <span><c:out
                                                    value="${contact.lastName}"/></span></dd>
                                            <dt>Address:</dt>
                                            <dd><c:out value="${contact.address}"/> <span><c:out
                                                    value="${contact.city}"/></span>
                                                <span><c:out value="${contact.provinceKey}"/></span> <span><c:out
                                                        value="${contact.postCode}"/></span></dd>
                                            <dt><c:out value="${contact.contactTypeKey}"/>:</dt>
                                            <dd
                                            <span><c:out value="${contact.ownPercentage}"/></span></dd>
                                            <dt>Number of Business:</dt>
                                            <dd><span><c:out value="${contact.nbrOfBiz}"/></span></dd>
                                        </dl>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <!-- /.box -->
                            </div>
                            <!-- ./col -->
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="quoting">
                    <div class="panel-container">
                        <div class="panel-left">
                            <h4>Quote Options:</h4>
                        </div>
                        <div class="panel-right">
                            <div class="col-md-12">
                                <form:form>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="inputApprovalAmt">Approved Amount</label>
                                            <input type="email" class="form-control" id="inputApprovalAmt"
                                                   value="$5000.00">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label for="inputReqAmt">Requested Amount</label>
                                            <input type="password" class="form-control" id="inputReqAmt"
                                                   value="$8000.00">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label for="inputReimburReqAmt">Daily Reimbursement Amount</label>
                                            <input type="password" class="form-control" id="inputReimburReqAmt"
                                                   value="$800.00">
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="inputTermLoan">Initial Term of Loan</label>
                                            <input type="input" class="form-control" id="inputTermLoan" value="7 month">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label for="inputNbrPay">Number of Payments</label>
                                            <input type="input" class="form-control" id="inputNbrPay" value="17">
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label for="inputInterstRate">Interest Rate (Initial Term)</label>
                                            <input type="text" class="form-control" id="inputInterstRate" value="122%">
                                        </div>
                                    </div>
                                    <div style="height: 35px; margin-top: 10px;">
                                        <div style="width: 50%; float: left;">
                                            <input type="button" onclick="resetActivitySearch()"
                                                   value="<spring:message code='BTN_QUOTE'/>"/>
                                        </div>
                                        <div style="width: 50%; float: left; text-align: right;">
                                            <input id="btnSearch" type="button" onclick="doActivitySearch()"
                                                   value="<spring:message code='BTN_AGREEMENT'/>"/>
                                        </div>
                                    </div>
                                </form:form>
                            </div>

                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="agreement">
                    <div class="panel-container">
                        <div class="panel-left">
                            <h4>Contacts Info</h4>
                        </div>
                        <div class="panel-right">
                            <h4>Contact Detail</h4>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="uploaddocs">
                    <div class="panel-container">
                        <div class="panel">
                            <h4>Contacts Info</h4>
                        </div>
                        <div class="panel">
                            <h4>Contact Detail</h4>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
</div>

<script>
    $(document).ready(function () {
//        initializeFilePreview();

    });

    //
    //    function initializeFilePreview() {
    //        $("#panel-top").resizable({
    //            handles: "n, s",
    //            minHeight: 100,
    //            resize: function(event, ui) {
    //                adjustHandlePosition();
    //            }
    //        });
    //    }
    //
    //
    //    function adjustHandlePosition() {
    //        var offset = 27;
    //        if ($('#panel-top')[0].scrollWidth > $('#panel-top')[0].clientWidth) {
    //            offset = 0;
    //        }
    //        $('.ui-resizable-s').css('top', ($("#panel-top").scrollTop() + $('.tab-content').height() + offset) + "px");
    //    }

    function loadBack(data) {


    }

</script>
</body>
</html>
