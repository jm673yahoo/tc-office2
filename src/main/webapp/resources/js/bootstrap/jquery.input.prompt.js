/*
 * jQuery fix checkbox plugin 0.2
 * requires jQuery Color Animations John Resig
 * 
 * Copyright (c) 2009 Gen Ichino
 * 
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

(function (jQuery) {
    jQuery.fn.extend({
        prompt: function (option) {
            var defaultText;
            if ((typeof option) == "string") defaultText = option;
            else if (option && option.text) defaultText = option.text;
            else return;

            var promptColor = (option && option.color) ? option.color : "#999999";
            var array = [];
            this.each(function () {
                var $input = jQuery(this);

                isPasswordField($input, defaultText);

                var normalColor = $input.css("color");
                var backgroundColor = $input.css("background-color");

                $input.focus(function () {
                    if (jQuery.trim($input.val()) == defaultText) {
                        isPasswordField($input, defaultText);
                        $input.css({color: normalColor}).val("");
                    }
                })
                    .blur(function () {
                        if (jQuery.trim($input.val()) == "") {
                            isPasswordField($input, defaultText);
                            $input.css({color: backgroundColor}).val(defaultText).animate({color: promptColor});
                        }
                    });

                var value = $.trim($input.val());
                if (value == "" || value == defaultText) {
                    isPasswordField($input, defaultText);
                    $input.css({color: promptColor}).val(defaultText);
                }

                array[array.length] = this;
            });

            return array;
        }
    });
})(jQuery);

//-----------------
//---Convert password type to text type if there is no input.-------------
function isPasswordField($input, defaultText) {
    if ($input.attr('id') == "j_password" && $.trim($input.val()) == "") {
        $input.prop('type', 'text');
    } else if ($input.attr('id') == "j_password" && $.trim($input.val()) != "") {
        $input.prop('type', 'password');
    }
}
