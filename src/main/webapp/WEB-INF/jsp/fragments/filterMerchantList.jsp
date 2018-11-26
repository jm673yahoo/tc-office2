<style type="text/css">
    /* CSS REQUIRED */
    .state-icon {
        left: -5px;
    }

    .list-group-item-primary {
        color: rgb(255, 255, 255);
        background-color: rgb(66, 139, 202);
    }

    /* DEMO ONLY - REMOVES UNWANTED MARGIN */
    .well .list-group {
        margin-bottom: 0px;
    }
</style>
<script>

    function loadMtrx(companyNbr) {
        if (companyNbr == '' || companyNbr == 'undefined') {
            return;
        }
        var _hclientId = $('#_companyId');
        $('#sourceList').empty();
        $('#targetList').empty();
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");

        $.post(
            "../reports/getMerchants.htm",
            {
                csrfParameter: csrfToken,
                id: companyNbr
            },
            loadMTrxCallback,
            "json"
        );

    }

    // display remote results
    function loadMTrxCallback(data) {
        var _sourceList = $('select.form-control.sourceList');
        $.each(data, function (key, object) {
            var _option = document.createElement("option");
            _option.setAttribute('value', object.code);
            _option.innerHTML = object.name;
            _sourceList.append(_option);
        });
    }

    function onLeftClick(event) {
        var _sourceLista = $('select.form-control.sourceList');
        var _options = _sourceLista[0].options;
        var _items = [];
        var counter = 0;

        $.each(_options, function (indx, object) {
            if (object.selected == true) {
                _items[counter] = object;
                counter++;
            }
        });

        var _targetList = $('#targetList');
        $.each(_items, function (indx, object) {
            _targetList.append(object);
        });
    }


    function onRightClick(event) {
        var _targetList = $('select.form-control.targetList');
        var _options = _targetList[0].options;
        var _items = [];
        var counter = 0;

        $.each(_options, function (indx, object) {
            if (object.selected == true) {
                _items[counter] = object;
                counter++;
            }
        });

        var _sourceList = $('#sourceList');
        $.each(_items, function (indx, object) {
            _sourceList.append(object);
        });
    }

    function okCloseMerchant(event) {
        var _targetList = $('select.form-control.targetList');
        var _options = _targetList[0].options;
        var _items = [];
        var counter = 0;

        $.each(_options, function (indx, object) {
            if (object.selected == true) {
                _items[counter] = object;
                counter++;
            }
        });
        if (_items.length > 0) {
            //add to merchant list
            var _merchantList = $('#merchantAccts');
            _merchantList.empty();
            $.each(_items, function (indx, object) {
                _merchantList.append(object);
            });
        }
    }

</script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div id="filterMerchantWindow" class="modal fade" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document" style="background-color:#ffffff; width:95em;">
        <div class="modal-content" style="border-radius: 1px;">
            <!-- Header-->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Filter Merchant Accounts</h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="panel panel-default col-lg-4" style="padding:0 0 0 0;">
                        <div class="panel-heading"><h4>Source Item</h4></div>
                        <select id="sourceList" multiple class="form-control sourceList" size="20">
                            <option></option>
                        </select>
                    </div>
                    <div class="panel panel-default col-lg-1" style="padding-top:20em; padding-bottom:15em;">
                        <div>
                            <button type="right" class="btn btn-default" onClick="onLeftClick(this)">></button>
                        </div>
                        <div>
                            <button type="left" class="btn btn-default" onClick="onRightClick(this)"><</button>
                        </div>
                    </div>
                    <div class="panel panel-default col-lg-4" style="padding:0 0 0 0;">
                        <div class="panel-heading"><h4>Target Item</h4></div>
                        <select id="targetList" multiple class="form-control targetList" size="20">
                            <option></option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- Footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onClick='okCloseMerchant(this)'>OK
                </button>
            </div>
        </div>
    </div>
</div>
