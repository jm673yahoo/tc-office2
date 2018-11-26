<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../fragments/taglib.jspf" %>

<html>
<head>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/commonjs.jspf" %>

    <script type="text/javascript">


        // variables
        function initPage() {
            var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            var csrfToken = $("meta[name='_csrf']").attr("content");

            $("#tabSourcet").addClass('active');
            var _lang = 'en';
            $("#language")[0].innerText = _lang;


        }


    </script>
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
                    <button type="button" class="btn btn-danger">Cancel</button>
                    <button type="button" class="btn btn-primary">Refund</button>
                    <button type="button" class="btn btn-primary btn-circle" ata-toggle="tooltip" data-placement="left"
                            title="print reciept"><i class="fa fa-list"></i></button>
                </P>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="well">
                <div class="row show-grid" style="font-size:1.15em;">
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_ID'/>:</labe>
                        <input name="bizId" value="<c:out value="${bizDetail.businessId}" />"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_SRC'/>:</labe>
                        <c:out value="${bizDetail.leadSrcKey}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='APP_STATE'/>:</labe>
                        <c:out value="${bizDetail.businessId}"/><span></div>
                </div>
                <div class="row show-grid" style="font-size:1.15em;">
                    <div class="col-xs-4">
                        <labe><spring:message code='AVG_MNTH_AMOUNT'/>:</labe>
                        <c:out value="${bizDetail.bizAvgMthAmt}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='PARTNER_CODE'/>:</labe>
                        <c:out value="${bizDetail.partnerId}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_NAME'/>:</labe>
                        <c:out value="${bizDetail.bizName}"/></div>
                </div>
                <div class="row show-grid" style="font-size:1.15em;">
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_LEGAL_NAME'/>:</labe>
                        <c:out value="${bizDetail.bizLegalName}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='NBR_LOCATION'/>:</labe>
                        <c:out value="${bizDetail.nbrOfLocation}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='NBR_TERMINAL'/>:</labe>
                        <c:out value="${bizDetail.terminalNbr}"/></div>
                </div>
                <div class="row show-grid" style="font-size:1.15em;">
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_CITY'/>:</labe>
                        <c:out value="${bizDetail.bizCity}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_PROVINCE'/>:</labe>
                        <c:out value="${bizDetail.bizProvinceCode}"/></div>
                    <div class="col-xs-4">
                        <labe><spring:message code='BIZ_POSTCODE'/>:</labe>
                        <c:out value="${bizDetail.bizPostCode}"/></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="well">
                <h4>Merchant Info</h4>
                <blockquote style="font-size:1.15em;">
                    <p>
                        <labe><spring:message code='BIZ_CITY'/>:</labe>
                        <c:out value="${bizDetail.bizCity}"/></p>
                    <p>
                        <labe><spring:message code='BIZ_PROVINCE'/>:</labe>
                        <c:out value="${bizDetail.bizProvinceCode}"/></p>
                    <p>
                        <labe><spring:message code='BIZ_POSTCODE'/>:</labe>
                        <c:out value="${bizDetail.bizPostCode}"/></p>
                </blockquote>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="well">
                <h4>Terminal Info</h4>
                <blockquote style="font-size:1.15em;">
                    <p>
                        <labe><spring:message code='BIZ_CITY'/>:</labe>
                        <c:out value="${bizDetail.bizCity}"/></p>
                    <p>
                        <labe><spring:message code='BIZ_PROVINCE'/>:</labe>
                        <c:out value="${bizDetail.bizProvinceCode}"/></p>
                    <p>
                        <labe><spring:message code='BIZ_POSTCODE'/>:</labe>
                        <c:out value="${bizDetail.bizPostCode}"/></p>
                </blockquote>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {

    });

    function loadBack(data) {


    }

</script>
</body>
</html>
