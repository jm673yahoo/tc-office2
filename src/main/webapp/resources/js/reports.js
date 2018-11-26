// constants
var TODAY = "TODAY";
var YESTERDAY = "YESTERDAY";
var LAST7_DAYS = "LAST7_DAYS";
var CURR_MONTH = "CURR_MONTH";
var LAST_MONTH = "LAST_MONTH";

var selectedStatus = "";
var origBeforeTaxAmount = 0;

//--------------------------------------------------
// sets the date range based on the quick range provided
function quickDate(quickCode) {
    var sDate = $("#sDate");
    var eDate = $("#eDate");

    var oneDayInMilli = 1 * 1000 * 60 * 60 * 24;
    var days;
    var startDt;
    var endDt;

    switch (quickCode) {
        case TODAY:
            startDt = new Date();
            endDt = new Date();
            break;
        case YESTERDAY:
            days = 1 * oneDayInMilli;
            startDt = new Date();
            startDt.setTime(startDt.getTime() - days);
            endDt = startDt;
            break;
        case LAST7_DAYS:
            days = 7 * oneDayInMilli;
            startDt = new Date();
            startDt.setTime(startDt.getTime() - days);
            endDt = new Date();
            break;
        case CURR_MONTH:
            startDt = new Date();
            startDt.setDate(1);
            endDt = new Date();
            break;
        case LAST_MONTH:
            days = 30 * oneDayInMilli;

            startDt = new Date();
            startDt.setDate(1);
            startDt.setTime(startDt.getTime() - days);
            startDt.setDate(1);

            endDt = new Date();
            endDt.setDate(1);
            endDt.setTime(endDt.getTime() - days);
            break;
    }

    sDate.datetimepicker("setDate", startDt);
    eDate.datetimepicker("setDate", endDt);
}

//--------------------------------------------------
// shows/hides the appropriate criteria fields based on the selection
function reportTypeChanged(rptType) {
    $("#activityType").val(rptType);

    if (rptType == "SUMMARY") {
        $("#summaryLbl").addClass("selectedActivityReportType");
        $("#lookupLbl").removeClass("selectedActivityReportType");
        $("#searchGeneral").show();
        $("#criteriaSummary").show();
        $("#criteriaLookup").hide();
        $("#btnSearch").attr("value", btnSummaryLabel);
    }
    else {
        $("#summaryLbl").removeClass("selectedActivityReportType");
        $("#lookupLbl").addClass("selectedActivityReportType");

        $("#searchGeneral").hide();
        $("#criteriaSummary").hide();
        $("#criteriaLookup").show();
        $("#btnSearch").attr("value", btnLookupLabel);
    }
}

//--------------------------------------------------
// resets the search criteria 
function resetActivitySearch() {
    var now = new Date();
    serverAcctNbr = "";

    $("#sDate").datepicker("setDate", now);
    $("#sHour").val("0");
    $("#sMin").val("0");
    $("#eDate").datepicker("setDate", now);
    $("#eHour").val("23");
    $("#eMin").val("59");
    $("#timezone").val("America/Montreal");
    $("#merchantAccts").val("all");
    $("#providedAcct").val("");
    $("#currency").val("NONE");
    $("#groupded").val("");
    $("#cardBrand").val("");
    $("#terminalRadio").prop("checked", "terminalNo");
    $("#trxType").val("");

    $("#refNbr").val("");
    $("#cardEnding").val("");
    $("#txnId").val("");
    $("#authCode").val("");
    $("#firstName").val("");
    $("#lastName").val("");
    $("#amount").val("");
    $("#bankAccountNbr").val("");
    $("#token").val("");
    $("#loginName").val("");


    cardEndingChanged();
    cacheSearchValues();
}

//--------------------------------------------------
// executes the appropriate search 
function doActivitySearch() {
    if ($("#activityType").val() == "SUMMARY") {
        doSummary();
    }
    else {
        doLookup();
    }
}

//--------------------------------------------------
//show/hide fields based on the auto selected
function companyChanged() {
    var _clientId = $("#companyBase").val();
    if (userType == "SYSADMIN") {
        $(".ccFields").show();
        $("#merchAcctField").show();
        $("#amountField").show();
        $("#invoiceField").show();
        $("#nameField").show();
        $("#emailField").show();
        $("#authCode").show();
        $("#terminalField").show();
    }

    loadMerchAccts(_clientId);
}

//--------------------------------------------------
function loadMerchAccts(clientId) {
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    $.post(
        "../reports/getMerchants.htm",
        {
            csrfParameter: csrfToken,
            id: clientId
        },
        merchAcctsResult,
        "json"
    );
}

//--------------------------------------------------------
//handles the server response for retrieving accts
function merchAcctsResult(data) {
    if (data != null) {
        if (data.result == "INTERNAL_ERROR") {
            $.blockUI({
                message: $('#criticalMess'),
                overlayCSS: {backgroundColor: '#E5F3FF'},
                css: {zIndex: 11000}
            });
        }
        else {
            // build account list
            var _merchAcctField = $('#merchantAccts');
            var option;
            var acct;

            _merchAcctField.empty();
            option = $(document.createElement("option"));
            option.attr("value", 'ALL');
            option.text("<spring:theme code='ALL'/>");
            option.attr("selected", 'true');
            _merchAcctField.children().end().append(option);

            for (i = 0; i < data.length; i++) {
                acct = data[i];

                option = $(document.createElement("option"));
                option.attr("value", acct.code);
                option.text(acct.name);

                _merchAcctField.children().end().append(option);
            }

            if (serverAcctNbr != "") {
                _merchAcctField.val(serverAcctNbr.split(","));
            }
        }
    }
}

//--------------------------------------------------
// show/hide the card brand lookup field depending on 
// if a full card no. was provided
function cardEndingChanged() {
    var cardEndingVal = $("#cardEnding").val();

    if (cardEndingVal != "" && cardEndingVal.length > 4) {
        $("#cardBrandLookupField").show();
    }
    else {
        $("#cardBrandLookupField").hide();
    }
}

//--------------------------------------------------
// performs the actual report search
function doSummary() {
    if (userType == "EXT") {
        $("#merchAccts").val($("#merchAccts").val());
    }
    else {
        validateSummary4INT();
        $("#merchAccts").val();
    }

    // cacheSearchValues();

    $.blockUI({
        message: PROCESSING,
        overlayCSS: {backgroundColor: '#E5F3FF'}
    });

    $("#reportForm").attr("action", "summaryReport.htm");
    $("#reportForm").submit();
}

//--------------------------------------------------
//performs the actual report search
function doLookup() {
    try {
        if (userType == "EXT") {
            validateLookup4EXT();
            $("#merchAccts").val($("#merchAccts").val());
        }
        else {
            validateLookup4INT();
            $("#merchAccts").val();
        }

        //  cacheSearchValues();

        $.blockUI({
            message: PROCESSING,
            overlayCSS: {backgroundColor: '#E5F3FF'}
        });

        $("#reportForm").attr("action", "lookup.htm");
        $("#reportForm").submit();
    }
    catch (ex) {
        if (ex == "BAD_ACCT_NBR") {
            alert(ERROR_BAD_ACCT_NBR);
        }
        else if (ex == "BAD_ACCTGROUP_NBR") {
            alert(ERROR_BAD_ACCTGROUP_NBR);
        }
        else if (ex == "BAD_SHOP_NBR") {
            alert(ERROR_BAD_SHOP_NBR);
        }
        else if (ex == "MISSING_CRITERIA") {
            alert(ERROR_MISSING_CRITERIA);
        }
        else {
            alert(ex);
        }
    }
}

//--------------------------------------------------
//performs an post auth action
function doPostAuth() {
    $("#btnPostAuth").attr("disabled", "disabled");
    $("#btnPostAuth2").attr("disabled", "disabled");
    $("#btnPostAuth3").attr("disabled", "disabled");

    $(".error").hide();

    $.blockUI({
        message: PROCESSING,
        overlayCSS: {backgroundColor: '#E5F3FF'},
        css: {zIndex: 11000}
    });

    $.post("processPostAuth.htm",
        $("#postAuthForm").serialize(),
        txnActionResult,
        "json");
}

//--------------------------------------------------------
//check account selection max 1000
function checkAcctLimit(elem) {
    var _merchAcctField = $(elem);

    if (_merchAcctField.val() && _merchAcctField.val().length > 1000) {
        alert(ERROR_ACCOUNT_SELECTION_MAX);
    }
}

//--------------------------------------------------------
//handles the server response from processing
function txnActionResult(data) {
    $("#btnPostAuth").removeAttr("disabled");
    $("#btnPostAuth2").removeAttr("disabled");

    if (data != null) {
        if (data.result == "VALIDATION_ERROR") {
            $.unblockUI();
            $("#postAuthForm").data("validator").invalidate(data);
        }
        else if (data.result == "INTERNAL_ERROR") {
            location.href = "showError.htm?csrfToken=" + csrfToken;
        }
        else if (data.result == "PROCESSING_ERROR") {
            location.href = "../reports/showActionError.htm?csrfToken="
                + csrfToken
                + "&code=" + data.code
                + "&description=" + data.description
                + "&confNbr=" + data.confNbr;
        }
        else {
            // if all good reload the txn
            $("#txnDetailForm").submit();
        }
    }
}

//--------------------------------------------------
//process another transaction
function doProcessTxn() {
    $("#btnPostAuth").attr("disabled", "disabled");
    $("#btnPostAuth2").attr("disabled", "disabled");
    $("#btnPostAuth3").attr("disabled", "disabled");

    $(".error").hide();

    $.blockUI({
        message: PROCESSING,
        overlayCSS: {backgroundColor: '#E5F3FF'},
        css: {zIndex: 11000}
    });

    $.post("processTxn.htm",
        $("#processTxnForm").serialize(),
        processTxnResult,
        "json");
}

//--------------------------------------------------------
//handles the server response from processing
function processTxnResult(data) {
    $("#btnPostAuth").removeAttr("disabled");
    $("#btnPostAuth2").removeAttr("disabled");

    if (data != null) {
        if (data.result == "VALIDATION_ERROR") {
            $.unblockUI();
            $("#processTxnForm").data("validator").invalidate(data);
        }
        else if (data.result == "INTERNAL_ERROR") {
            location.href = "error.jsp?csrfToken=" + csrfToken;
        }
        else if (data.result == "PROCESSING_ERROR") {
            location.href = "../reports/showActionError.htm?csrfToken="
                + csrfToken
                + "&code=" + data.code
                + "&description=" + data.description
                + "&confNbr=" + data.confNbr;
        }
        else {
            // redirect to the result page if processing occurred
            location.href = "showResult.htm?csrfToken=" + csrfToken;
        }
    }
}

//--------------------------------------------------------
// validates fields for internal users for a summary search
function validateSummary4INT() {
    var _companyId = $("#companyId").val();
    var acctGroupId = $("#acctGroup").val();
    var shopId = $("#terminal").val();

    if (_companyId && _companyId != "" && isNaN(_companyId)) {
        alert(ERROR_BAD_ACCT_NBR);
        throw "BAD_ACCT_NBR";
    }

    if (acctGroupId && acctGroupId != "" && isNaN(acctGroupId)) {
        alert(ERROR_BAD_ACCTGROUP_NBR);
        throw "BAD_ACCTGROUP_NBR";
    }

    if (shopId && shopId != "" && isNaN(shopId)) {
        alert(ERROR_BAD_SHOP_NBR);
        throw "BAD_SHOP_NBR";
    }
}

//--------------------------------------------------------
// validates fields for internal users for a lookup search
function validateLookup4INT() {

    validateLookup4EXT();
}

//--------------------------------------------------------
// validates fields for external users for a lookup search
function validateLookup4EXT() {
    var paymentMethod = $("#paymentMethod").val();

    switch (paymentMethod) {
        case "CC":
            if ($.trim($("#cardEnding").val()) == ""
                && $.trim($("#txnId").val()) == ""
                && $.trim($("#authCode").val()) == ""
                && $.trim($("#refNbr").val()) == ""
                && $.trim($("#firstName").val()) == ""
                && $.trim($("#lastName").val()) == ""
                && $.trim($("#email").val()) == ""
                && $.trim($("#amount").val()) == ""
                && $.trim($("#keyword").val()) == ""
                && $.trim($("#loginName").val()) == "") {
                throw "MISSING_CRITERIA";
            }
            break;
    }
}

//--------------------------------------------------------
// allows a txn that just failed from the VT to be re-tried
function doRetry() {
    location.href = "../vt/retryTxn.htm?csrfToken=" + csrfToken;
}

//--------------------------------------------------------
// returns back to the virtual terminal
function goToVT() {
    location.href = "../vt/showVT.htm?csrfToken=" + csrfToken;
}

//--------------------------------------------------------
//returns back to the cpm
function goToCPM() {
    location.href = "../profile/showSearch.htm?csrfToken=" + csrfToken;
}

//--------------------------------------------------------
// opens the receipt credit window
function showReceipt(trxType, ccIndex) {
    var winOptions = "width=650,height=550,location=no,menubar=no,status=no,toolbar=no";
    if (trxType == 'CR') {
        window.open("../reports/showCreditReceipt.htm?csrfToken=" + csrfToken + "&ccIndex=" + ccIndex,
            "_blank", winOptions);
    } else if (trxType == 'S') {
        window.open("../reports/showSettlementReceipt.htm?csrfToken=" + csrfToken + "&ccIndex=" + ccIndex,
            "_blank", winOptions);
    } else {
        window.open("../reports/showReceipt.htm?csrfToken=" + csrfToken, "_blank", winOptions);
    }
}

//--------------------------------------------------------
// download a CB attachment
function downloadCBAttachment(attachmentId) {
    $("#attachmentId").val(attachmentId);
    $("#cbAttachmentForm").submit();
}


//--------------------------------------------------
// creates a new radio button
function createRadio(statusCode) {
    var elem = $(document.createElement("input"));
    elem.attr("name", "status");
    elem.attr("type", "radio");
    elem.attr("value", statusCode);

    return elem;
}

//--------------------------------------------------
// performs the cancellation of the echeck
function confirmCancel() {
    selectedStatus = "X";
    updateDDStatus();
}


//--------------------------------------------------
// cache data in browser local storage
function cacheSearchValues() {
    var formValues = form2JSON($("#reportForm"));
    formValues.bankAccountNbr = "";

    // clear full value
    if (formValues.cardEnding.length > 4) {
        formValues.cardEnding = "";
    }

    StorageUtils.storeSSValue("ctpayoffice_activity", JSON.stringify(formValues));
}


//--------------------------------------------------------
//displays the amount in decimal format
function amountChanged() {
    var origAmount = $('#amount').val();

    if ($('#amountFormat').val() == "DECIMAL") {
        var amt = $.formatNumber(origAmount, {format: "#,###.00", locale: "us"});
        $('#realCCAmt').html(amt);
    }
    else {
        origAmount + "00";
        var amt = Number(origAmount) / 100;
        amt = $.formatNumber(amt, {format: "#,###.00", locale: "us"});
        $('#realCCAmt').html(amt);
    }
}

//--------------------------------------------------
//calculate sales tax
function getSaleTaxes(field, confNbr) {
    var amtBefTax = $(field).val();
    if (amtBefTax.length >= 1 && origBeforeTaxAmount != Number(amtBefTax)) {
        if (Number(amtBefTax) > origBeforeTaxAmount) {
            $("#amount").val(origBeforeTaxAmount);
            amtBefTax = origBeforeTaxAmount
        }
        $.post("../reports/getSaleTaxes.htm",
            {amountBeforeTax: amtBefTax, confirmationNbr: confNbr, csrfToken: csrfToken},
            getSalesTaxesResult,
            "json");
    }
}

//--------------------------------------------------------
//display sale taxes from JSON result
function getSalesTaxesResult(event) {
    if (event != null) {
        if (event.result == "OK") {
            // Update the total amount
            var amt = $.formatNumber(event.data.totalAmount, {format: "#,###.00", locale: "us"});
            $('#totalAmount').html(amt);
            // Update each sale tax item amount
            for (var i = 0; i < event.data.saleTaxes.length; i++) {
                saleTax = event.data.saleTaxes[i];
                var taxAmt = $.formatNumber(saleTax.amount, {format: "#,###.00", locale: "us"});
                var taxTypeSelector = "#" + saleTax.type + "_AMT";
                $(taxTypeSelector).html(taxAmt);
            }

        } else {
            var errMess = SERVER_ERROR.replace("{0}", event.message);

            $.blockUI({
                message: errMess,
                css: {zIndex: 11000},
                overlayCSS: {backgroundColor: '#E5F3FF'}
            });
        }
    }
}

//--------------------------------------------------------
function getTerminalRadioValue() {
    var _elements = document.getElementById('terminalRadio');
    if (_elements == null || _elements == 'undefined') {
        return;
    }
    for (var i = 0; i < _elements.length; i++) {
        if (_elements[i].checked) {
            return _elements[i].val();
        }
    }
}

//--------------------------------------------------------
//add radio click handler
function terminalRadioHandler(it) {
    if (it.value == 'terminals') {
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
