/*
Javascript DataGrid plugin.
Used to manipulate an HTML table, adds a header, body and optionally a footer.

The columns field takes an array of objects used the specify the behavior of each column in the table.
You can specify the following attributes for each column object:
- name: the name to display in the column header
- dataField: name of the field within the data object to display
- headerCSS: used to specify a CSS class for the header column
- footerCSS: used to specify a CSS class for the footer column
- align: applies the value to the text-align property for the cell content
- labelFunc: function to execute to display the data in this column, function must return a string, overrides the dataField value
- sumInFooter: either TRUE or FALSE (default), determines if the column data should be totalled and included in the footer
- footerFunc: function to execute to display the calculated footer value

example with 2 columns:
columns = [{name: "Employee", dataField: "empName", headerCSS: "css1"},
					{name: "Salary", dataField: "salary", headerCSS: "css1", sumInFooter: true}]
@see http://docs.jquery.com/Plugins/Authoring
@see http://blog.nemikor.com/2010/05/15/building-stateful-jquery-plugins/
*/
(function ($) {
    $.widget("optimal.dataGrid", {
        // default options
        options: {
            columns: [],
            includeFooter: false,
            rowBackgroundColors: ["#EDEDED", "#FFFFFF"],
            headerCSS: null,
            footerCSS: null,
            messageNoData: "There are no rows to display."
        },

        // widget private variables
        _dataProvider: [],
        _footerTotals: [],
        _bodyId: "",

        // public property (getter/setter)
        dataProvider: function (value) {
            if (value == null) {
                return this._dataProvider;
            }
            else {
                this._dataProvider = value;
                this._renderDataProvider();
            }
        },

        // called when any option is set by the user
        // after the widget is created
        _setOption: function (key, value) {
            this.options[key] = value;

            // basically destroy everything and re-render
            this.element.children().remove();
            this._create();
            this._renderDataProvider();
        },

        /**
         * Widget constructor
         */
        _create: function () {
            // STEP 1: build header columns
            this._buildHeaderRow();

            // STEP 2: build footer columns
            if (this.options.includeFooter) {
                this._buildFooterRow();
                this._footerTotals = new Array(this.options.columns.length);
            }

            // STEP 3: create the empty body for now and give it a unique id
            // so we can reference it later
            this._bodyId = "_body" + this.element.attr("id");
            var _dataBody = $(document.createElement("tbody"));
            _dataBody.attr("id", this._bodyId);
            this.element.append(_dataBody);
        },

        /**
         * Widget initialized, called after _create().
         */
        _init: function () {
            //console.info("_init() " + $.ui.keyCode.BACKSPACE);
        },

        /**
         * Widget destructor, just do clean up
         */
        destroy: function () {
            this.element.children().remove();
            $.Widget.prototype.destroy.call(this);
        },

        /**
         * Builds the body rows based on the data provided for this data grid
         */
        _renderDataProvider: function () {
            var col;
            var rec;
            var _dataCell;
            var _dataRow;
            var tblBody = $("#" + this._bodyId);

            // erase body rows and reset footer values
            tblBody.children().remove();
            this._resetFooterValues();

            if (this._dataProvider == null || this._dataProvider.length == 0) {
                // if there is no data, then just display a message
                _dataRow = $(document.createElement("tr"));
                _dataCell = $(document.createElement("td"));
                _dataCell.attr("colSpan", this.options.columns.length);
                _dataCell.css("text-align", "center");
                _dataCell.html(this.options.messageNoData);

                _dataRow.append(_dataCell);
                tblBody.append(_dataRow);
            }
            else {
                // loop thru all the data
                for (var j = 0; j < this._dataProvider.length; j++) {
                    rec = this._dataProvider[j];

                    _dataRow = $(document.createElement("tr"));

                    // apply alternate the row colors
                    if (this.options.rowBackgroundColors != null
                        && this.options.rowBackgroundColors.length > 0) {
                        if ((j % 2) == 1) {
                            _dataRow.css("background-color", this.options.rowBackgroundColors[0]);
                        }
                        else {
                            _dataRow.css("background-color", this.options.rowBackgroundColors[1]);
                        }
                    }

                    // loop thru all columns
                    for (i = 0; i < this.options.columns.length; i++) {
                        col = this.options.columns[i];

                        _dataCell = $(document.createElement("td"));
                        _dataCell.attr("valign", "top");

                        // align cell content
                        if (col.align != null) {
                            _dataCell.css("text-align", col.align);
                        }

                        if (rec[col.dataField] != null) {
                            // apply label function if specified, passing the current data object
                            // for the row we are rendering to the function and the column object
                            if (col.labelFunc != null) {
                                _dataCell.html(col.labelFunc(rec, col));
                            }
                            else {
                                // display text so that it gets HTML encoded
                                _dataCell.text(rec[col.dataField]);
                            }

                            // if the sumInFooter is specified and TRUE, then add to footer total
                            if (this.options.includeFooter && col.sumInFooter) {
                                this._updateFooterTotal(i, rec[col.dataField]);
                            }
                        }

                        _dataRow.append(_dataCell);
                    }

                    tblBody.append(_dataRow);
                }
            }

            // now set footer values
            if (this.options.includeFooter) {
                this._applyFooterValues();
            }
        },

        /**
         * Re-initializes the _footerTotals array.
         */
        _resetFooterValues: function () {
            this._footerTotals = new Array(this.options.columns.length);

            for (var z = 0; z < this._footerTotals.length; z++) {
                this._footerTotals[z] = 0;
            }
        },

        /**
         * Sets the totals for the columns specified in the footer row.
         */
        _applyFooterValues: function () {
            var col;
            var cellRefId;

            for (var i = 0; i < this.options.columns.length; i++) {
                col = this.options.columns[i];

                if (col.sumInFooter) {
                    cellRefId = "#_foot" + this.element.attr("id") + col.dataField;

                    // apply footer function if specified, passing the current calculated value
                    if (col.footerFunc != null) {
                        $(cellRefId).html(col.footerFunc(this._footerTotals[i]));
                    }
                    else {
                        $(cellRefId).html(this._footerTotals[i]);
                    }
                }
            }
        },

        /**
         * Updates the total value in the footer for the column specified.
         * @param colIdx the index of the column total to update
         * @param data the data value
         */
        _updateFooterTotal: function (colIdx, data) {
            var totVal;

            // get current value
            totVal = this._footerTotals[colIdx];

            // init if no current value
            if (totVal == null) {
                totVal = 0;
            }

            // add to value and store
            totVal += Number(data);
            this._footerTotals[colIdx] = totVal;
        },

        /**
         * Builds the header row for this data grid
         */
        _buildFooterRow: function () {
            var col;
            var _footerCell;

            var _footer = $(document.createElement("tfoot"));
            var _footerRow = $(document.createElement("tr"));
            _footer.append(_footerRow);

            for (var i = 0; i < this.options.columns.length; i++) {
                col = this.options.columns[i];

                _footerCell = $(document.createElement("td"));
                _footerCell.attr("id", "_foot" + this.element.attr("id") + col.dataField);

                if (this.options.footerCSS != null || col.footerCSS != null) {
                    _footerCell.addClass((col.footerCSS != null) ? col.footerCSS : this.options.footerCSS);
                }

                if (col.align != null) {
                    _footerCell.css("text-align", col.align);
                }

                _footerRow.append(_footerCell);
            }

            // append header to the table
            this.element.append(_footer);
        },

        /**
         * Builds the header row for this data grid
         */
        _buildHeaderRow: function (table) {
            var col;
            var _headerCell;

            var _header = $(document.createElement("thead"));
            var _headerRow = $(document.createElement("tr"));
            _header.append(_headerRow);

            for (var i = 0; i < this.options.columns.length; i++) {
                col = this.options.columns[i];

                _headerCell = $(document.createElement("td"));
                _headerCell.html(col.name);

                if (col.width != null) {
                    _headerCell.attr("width", col.width);
                }

                if (this.options.headerCSS != null || col.headerCSS != null) {
                    _headerCell.addClass((col.headerCSS != null) ? col.headerCSS : this.options.headerCSS);
                }

                // if the first/last column, curve the top corners
                if (i == 0) {
                    _headerCell.css("border-top-left-radius", "5px");
                }
                else if (i == (this.options.columns.length - 1)) {
                    _headerCell.css("border-top-right-radius", "5px");
                }

                _headerRow.append(_headerCell);
            }

            // append header to the table
            this.element.append(_header);
        }
    });
})(jQuery);
