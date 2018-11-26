<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Calendar" %>
<html>
<head>

    <title>Error</title>

    <link href="../<spring:message code='FAV_ICON'/>" rel="SHORTCUT ICON">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../css/tcoffice.css" rel="stylesheet" media="screen">
    <link href="../css/font-awesome/font-awesome.css" rel="stylesheet" media="screen">
</head>

<body>
<div class="header">
    <div class="content">
        <img alt="<spring:message code='TITLE'/>" src="<spring:message code='TC_LOGO'/>"/>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="padder">
                    <div style="padding: 80px;" align="center">
              <span class="titleStep">
                An error occurred, please contact Technical Support.
              </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- product marketing info & ads -->
<div class="row">
    <div class="lg">
        <div class="panel panel-default">
            <spring:message code='SUPPORT_EMAIL'/><br/>
            <spring:message code='SUPPORT_PHONE'/>
        </div>
    </div>
</div>

<!-- pf footer -->
<div class="footer">
    <%
        Calendar cal = Calendar.getInstance();
        pageContext.setAttribute("calendarYear", "" + cal.get(Calendar.YEAR));
    %>
    <spring:message code='COPYRIGHT' arguments="${calendarYear}"/>
</div>
</body>
</html>
