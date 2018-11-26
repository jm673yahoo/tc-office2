<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../fragments/taglib.jspf" %>

<html>
<head>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/commonjs.jspf" %>
    <link href="../css/bootstrap-table.css" rel="stylesheet">
    <script src="../js/bootstrap-table.js"></script>
    <script src="../js/bootstrap-editable.js"></script>
    <script src="../js/bootstrap-table-editable.js"></script>
    <script src="../js/bootstrap-table-export.js"></script>
    <script src="../js/bootstrap-tableExport.js"></script>
    <script src="../js/sprintf.js"></script>

    <script type="text/javascript">
        var msgIdToOpen = 0;

        function initPage() {
            $("#tabSource").addClass('active');
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
            <div class="col-lg-12">
                <h1 class="page-header"><spring:message code='SOURCE_SUMMARY'/> - <span id="clientInfo"><a
                        href="javascript:clickToGetData()">LoadData</a>  </span></h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading col-lg-12">
                        <strong><spring:message code='TXT_START_DATE'/>:</strong>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${startDate}"/> -
                        <strong><spring:message code='TXT_END_DATE'/>:</strong>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${endDate}"/>
                    </div>
                    <div class="panel-body">
                        <table id="tableClients"
                               data-search="true"
                               data-show-refresh="true"
                               data-show-toggle="false"
                               data-show-columns="true"
                               data-show-export="true"
                               data-detail-view="true"
                               data-detail-formatter="detailFormatter"
                               data-minimum-count-columns="2"
                               data-show-pagination-switch="true"
                               data-pagination="true"
                               data-id-field="businessId"
                               data-page-list="[25, 50, 100, ALL]"
                               data-show-footer="false"
                               data-side-pagination="client"
                               data-response-handler="responseHandler">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var $table = $('#tableClients'),
        selections = [];

    function initTable() {
        $table.bootstrapTable({
            height: getHeight(),
            columns: [
                {
                    title: 'Biz Id',
                    field: 'businessId',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Biz Name',
                    field: 'bizName',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Biz Legal Name',
                    field: 'bizLegalName',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Phone #',
                    field: 'firstPhoneNbr',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Nbr Of Location',
                    field: 'nbrOfLocation',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Month Average Amt',
                    field: 'bizAvgMthAmt',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    footerFormatter: totalPriceFormatter
                }, {
                    title: 'Card Percent',
                    field: 'debCredPercent',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Address',
                    field: 'bizAddress',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'City',
                    field: 'bizCity',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Province',
                    field: 'bizProvinceCode',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Post Code',
                    field: 'bizPostCode',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Industry Code',
                    field: 'industryCode',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    title: 'Processor Code',
                    field: 'processorKey',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
                $table.bootstrapTable('resetView');
            },
            200
        );

        $table.on('expand-row.bs.table', function (e, index, row, $detail) {
            if (index % 2 == 1) {
            }
        });

        $table.on('all.bs.table', function (e, name, args) {
            console.log(name, args);
        });

        $(window).resize(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });

    }//end ini

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            if (key == 'businessId') {
                html.push('<p><b>' + key + ':</b> <a href="showClientDetail.htm?bizId=' + value + '">' + value
                    + '</a></p>');
            } else {
                html.push('<p><b>' + key + ':</b> ' + value + '</p>');
            }
        });
        return html.join('');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }

    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },
        'click .remove': function (e, value, row, index) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };

    function totalTextFormatter(data) {
        return 'Total';
    }

    function totalNameFormatter(data) {
        return data.length;
    }

    function totalPriceFormatter(data) {
        var total = 0;
        $.each(data, function (i, row) {
            total += +(row.price.substring(1));
        });
        return '$' + total;
    }

    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }

    $(document).ready(function () {
        initTable();
//        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
//        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = 'asdf4354';
//            $("meta[name='_csrf']").attr("content");
        clickToGetData();


//        labelTabHeader();
    });

    function clickToGetData() {


        var result = $.getJSON(
            'source/getClients.htm',
            function (data) {
                loadback(data);
            })
            .fail(function () {
                console.log("error");
            })
        ;

        var seeIt = result;
    }

    function loadback(data) {
        $table.bootstrapTable('load', data)
    }
</script>
</body>
</html>  
