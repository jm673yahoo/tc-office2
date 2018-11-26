<!DOCTYPE HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fragments/taglib.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <title><spring:message code="TITLE"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <!-- stylesheets includes -->
    <link href="<spring:message code='FAV_ICON'/>" rel="SHORTCUT ICON" type="image/x-icon">
    <link href="css/bootstrap/styles.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap/font-awesome/font-awesome.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap/fonts/glyphicons-halflings-regular.ttf">
    <link href="css/bootstrap/fonts/glyphicons-halflings-regular.woff">
    <script src="js/bootstrap/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>

    <style>
        #hero-wrapper {
            height: 100%;
            width: 100%;
            position: fixed;
        }

        #hero-wrapper .carousel-wrapper,
        #hero-carousel {
            height: 100%;
            width: 100%;
            position: absolute;
        }

        #hero-carousel img {
            left: 0;
            bottom: 0;
            min-width: 100%;
            min-height: 100%;
            max-width: none;
            width: auto;
            height: auto;
        }

        #hero-carousel i {
            position: absolute;
            top: 50%;
        }

        .carousel-fade .carousel-inner .item {
            opacity: 0;
            transition-property: opacity;
        }

        .carousel-fade .carousel-inner .active {
            opacity: 1;
        }

        .carousel-fade .carousel-inner .active.left,
        .carousel-fade .carousel-inner .active.right {
            left: 0;
            opacity: 0;
            z-index: 1;
        }

        .carousel-fade .carousel-inner .next.left,
        .carousel-fade .carousel-inner .prev.right {
            opacity: 1;
        }

        .carousel-fade .carousel-control {
            z-index: 2;
        }
    </style>
</head>
<body>
<div id="doc" class="yui-t5">
    <div id="yui-main" style="width:75%;">
        <div class="yui-b">
            <div class="content">
                <div id="myCarousel" class="carousel vertical slide  carousel-fade" data-ride="carousel"
                     data-interval="5000">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="images/cover1.png" height="100%" class="img-rounded" alt="tcoffice">
                        </div>
                        <div class="item">
                            <img src="images/cover2.png" height="100%" class="img-rounded" alt="tcoffice">
                        </div>
                        <div class="item">
                            <img src="images/cover3.png" height="100%" class="img-rounded" alt="tcoffice">
                        </div>
                    </div>

                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="yui-b" style="width:25%;">
        <div id="wrapper">
            <div style="padding-top:0.2em; padding-left:20.0em;">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-align-justify"></i><i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu pull-right">
                        <li>
                            <a href="javascript:langChanged('en')">
                                <div>
                                    <p><strong>Engilsh</strong></p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:langChanged('fr')">
                                <div>
                                    <p><strong>Français</strong></p>
                                </div>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
            </div>
        </div>
        <div class="login-panel panel panel-default" style="border-color:#ffffff;">
            <div class="panel-body" style="border:0;">
                <div style="width:20%;">&nbsp;&nbsp;</div>
                <div style="width:70%; padding-top:5.2em; padding-left:2.0em;">
                    <p><spring:message code='COVER_PAGE_DESCRIPTION'/> <br/> <br/><br/><br/><br/> <br/><br/><br/><br/>
                        <br/><br/><br/></p>
                    <c:url var="loginUrl" value="showClients.htm"/>

                    <form:form id="loginForm" method="GET" commandName="command" action='${loginUrl}'>
                        <fieldset>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="javascript:prePost()" class="btn btn-lg btn-success btn-block"><spring:message
                                    code='BTN_LOGIN'/></a>
                            <c:if test="${!empty command.errorMessage}">
                                <p class="loginErrorField">
                                    <c:out value="${command.errorMessage}" escapeXml="true"/>
                                </p>
                            </c:if>
                            <div style="float:right;">
                                <label>
                                    <a href="javascript:prePost()"><spring:message code='BTN_FORGOT_PWD'/>
                                </label>
                            </div>
                        </fieldset>
                        <!-- input type="hidden" name="_csrf" value="${_csrf.token}"/ -->
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </div>
                <div style="width:100%;">&nbsp;&nbsp;</div>
            </div>
        </div>
    </div>
</div>
<script>
    var INT_FORMAT = {format: "#,###", locale: "us"};
    var NUMBER_FORMAT = {format: "#,###.00", locale: "us"};

    function initPage() {
        $("language").val = currentLang;
    }

    // initialize our jQuery objects

    // when document loaded, assign some functionality
    $(document).ready(function () {
    });


    function handleKeyUp(event) {
        if (event.keyCode == 13) {
            prePost();
        }
    }

    function prePost() {
        $("#loginForm").submit();

    }

    function langChanged(lang) {
        var _lang = lang;
        location.href = "preLogin.htm?siteLang=" + _lang;
    }

    // redirect to login page if default ajax error (most likely session expiry)
    function handleAjaxError(event, req, options, error) {
        signOffBtnHandler();
    }
</script>

</body>
</html>
