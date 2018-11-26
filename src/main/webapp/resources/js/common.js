// global constants
var INT_FORMAT = {format: "#,###", locale: "us"};
var NUMBER_FORMAT = {format: "#,###.00", locale: "us"};
var today = new Date();
var CLIENTS = new Array();
var CLIENTINFO = null;


//---------------------------------------------------------
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    if (token == '' || token == 'undefined') {
        token = 'asdfa2454';
        header = 'asdfwrq54534';
    }

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

});


//---------------------------------------------------------
// method called when tab selected
// send us to the right page based on the selected tab
function handleTabSelect(event) {
    var loc;

    switch (event) {
        case "tabMessages":
            loc = "../support/showMessages.htm";
            break;
        case "tabSummary":
            $('#menuSummaryClient').addClass("active");
            loc = "../showClients.htm";
            break;
        case "tabLookup":
            $('#menuLookupClient').addClass("active");
            loc = "../showClientLookup.htm";
            break;
        case "tabConsumers":
            loc = "../profile/showProfile.htm";
            break;
        case "tabFields":
            $('#menuCustomerField').addClass("active");
            loc = "../settings/customerField.htm";
            break;
        case "tabUserGroup":
            $('#menuUserGroup').addClass("active");
            loc = "../settings/userGrouped.htm";
            break;
        case "tabUserUsage":
            $('#menuUserUsage').addClass("active");
            loc = "../settings/userUsage.htm";
            break;
        case "tabBatching":
            $('#menuBatching').addClass("active");
            loc = "../settings/batching.htm";
            break;
        case "tabFraudList":
            $('#menuTabFruadList').addClass("active");
            loc = "../fraud/listing.htm";
            break;
        case "tabFraudConf":
            $('#menuFraudConf').addClass("active");
            loc = "../fraud/config.htm";
            break;
    }

    location.href = loc;
}

//---------------------------------------------------------
//redirect when sub menu selected
function handleSubMenu(loc) {
    location.href = loc;
}

//---------------------------------------------------------
// SignOff button click handler
function signOffBtnHandler() {
    StorageUtils.storeSSValue("ctpaie", null);
    document.getElementById("logoutForm").submit();
}

//---------------------------------------------------------
function mechantLoadHandler() {
    var _select = $("#typeahead-client")[0].value;

    if (_select == null || _select == 'undefined' || _select.length < 3) {
        return;
    }
    var matcher = new RegExp(_select, "i");
    var _code = $.grep(CLIENTS, function (item) {
        var value = item.display;
        return matcher.test(value);
    });
    var _clientId = _code[0].code;
    $("#_companyId").val(_clientId);
    loadMerchAccts(_clientId);
}

//---------------------------------------------------------
// Docs button click handler
function showDocs() {
    $("#docWindow").modal({
        autoOpen: true,
        draggable: true,
        resizable: true,
        position: "center",
        width: 500,
        height: 350,
        title: TITLE_DOCS,
        modal: true
    });
}

//---------------------------------------------------------
// Contact button click handler
function showContactUs() {
    $("#contactTxnDate").datetimepicker();
    $("#contactForm").validator({lang: "default"}).submit(validateContactForm);

    $("#contactWindow").modal({
        autoOpen: true,
        draggable: true,
        resizable: true,
        position: "center",
        width: 700,
        height: 595,
        title: TITLE_CONTACT,
        modal: true,
        open: resetContactUsForm,
        close: closeContactUs
    });
}

//---------------------------------------------------------
// open the find filter window
function showFilterMerchant(companyId) {

    $("#filterMerchantWindow").modal({
        autoOpen: true,
        draggable: true,
        resizable: true,
        position: "center",
        width: 500,
        height: 350,
        title: "Filter Items",
        modal: true
    });
}

//---------------------------------------------------------
// Contact button click handler
function closeContactUs() {
    $(".error").hide(); // hide form validation errors
}

//---------------------------------------------------------
// Resets all the fields in the contact us form
function resetContactUsForm() {
    $("#contactName").val("");
    $("#contactPhone").val("");
    $("#contactEmail").val("");
    $("#contactDesc").val("");
    $("#acctNumber").val(0);
    $("#contactBrand").val(0);
    $("#contactLast4").val("");
    $("#contactConfNbr").val("");
    $("#contactTxnDate").val("");
}

//---------------------------------------------------------
// handles the server response from sending support email
function contactFormResult(data) {
    $.unblockUI();

    if (data != null) {
        $("#contactForm").data("validator").invalidate(data);
    }
    else {
        $("#contactWindow").modal("close");
    }
}

//---------------------------------------------------------
// Send support email button click handler
function postContactForm() {
    $("#contactForm").submit();
}

//---------------------------------------------------------
//if validation is OK, then post to server
function validateContactForm(event) {
    if (!event.isDefaultPrevented()) {
        $.blockUI({message: PROCESSING});

        $.post("../support/sendSupportEmail.htm",
            $("#contactForm").serialize(),
            contactFormResult,
            "json");

        event.preventDefault();
    }
}

//---------------------------------------------------------
// redirect to login page if default ajax error (most likely session expiry)
function handleAjaxError(event, req, options, error) {
    signOffBtnHandler();
}

//---------------------------------------------------------
// converts form fields to a JSON object
function form2JSON(values) {
    var o = {};
    var a = $(values).serializeArray();

    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
};

//---------------------------------------------------------
function labelTabHeader() {


};

