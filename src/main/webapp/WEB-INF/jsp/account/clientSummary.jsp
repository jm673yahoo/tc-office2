<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../fragments/taglib.jspf" %>

<html>
<head>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/commonjs.jspf" %>
    <link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <script src="../js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script type="text/javascript">
        // variables
        function initPage() {
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
            <h1 class="page-header">Clients account for recent 3 month</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading col-lg-12">
                    <spring:message code='TXT_START_DATE'/>:
                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${startDate}"/> -
                    <spring:message code='TXT_END_DATE'/>:
                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${endDate}"/>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-MERCHANT">
                            <thead>
                            <tr>
                                <th>Biz Id</th>
                                <th>Biz Name</th>
                                <th>Biz Legal Name</th>
                                <th>Phone #</th>
                                <th>Nbr Of Location</th>
                                <th>Month Average Amt</th>
                                <th>ACRT average Amt</th>
                                <th>Card Percent</th>
                                <th>Address</th>
                                <th>City</th>
                                <th>Province</th>
                                <th>Post Code</th>
                                <th>Industry Code</th>
                                <th>Processor Code</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${clientsData}" var="data">
                                <tr>
                                    <td><a href='getClientDetail.htm?bizId=<c:out value="${data.businessId}" />'>
                                        <c:out value="${data.businessId}"/></a></td>
                                    <td><c:out value="${data.bizName}"/></td>
                                    <td><c:out value="${data.bizLegalName}"/></td>
                                    <td><c:out value="${data.firstPhoneNbr}"/></td>
                                    <td><c:out value="${data.nbrOfLocation}"/></td>
                                    <td><c:out value="${data.bizAvgMthAmt}"/></td>
                                    <td><c:out value="${data.bizAcrtAvgMthAmt}"/></td>
                                    <td><c:out value="${data.debCredPercent}"/></td>
                                    <td><c:out value="${data.bizAddress}"/></td>
                                    <td><c:out value="${data.bizCity}"/></td>
                                    <td><c:out value="${data.bizProvinceCode}"/></td>
                                    <td><c:out value="${data.bizPostCode}"/></td>
                                    <td><c:out value="${data.industryCode}"/></td>
                                    <td><c:out value="${data.processorKey}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        $(document).ready(function () {
            var oTable = $('#dataTables-MERCHANT').dataTable();
            // Highlight every second row
            oTable.$('tr:odd').css('backgroundColor', '#e8f4f8');
        });
    </script>

</div>
</body>
</html>
