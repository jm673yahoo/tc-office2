// lists the months and days in various languages
var _props = {
    en: {
        fullMonths: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        fullDays: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
    },
    fr: {
        fullMonths: ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"],
        fullDays: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"]
    }
};

// format tokens
var _formatKeys = ["yyyy", "yy", "MMMM", "MMM", "MM", "dddd", "ddd", "dd", "hh24", "hh", "mi", "ss"];

/**
 * Formats the date object in format and language specified.
 * @param dt the Date object to format
 * @param fmt the format string
 * @param lang output the language to use
 */
function formatDate(dt, fmt, lang) {
    var v = 0;
    var props = _props[lang];

    for (idx in _formatKeys) {
        if (fmt.indexOf(_formatKeys[idx]) != -1) {
            if (_formatKeys[idx] == "yyyy") {
                fmt = fmt.replace("yyyy", dt.getFullYear());
            }
            else if (_formatKeys[idx] == "yy") {
                fmt = fmt.replace("yy", new String(dt.getFullYear()).substr(2, 3));
            }
            else if (_formatKeys[idx] == "MMMM") {
                v = dt.getMonth();
                fmt = fmt.replace("MMMM", props.fullMonths[v]);
            }
            else if (_formatKeys[idx] == "MMM") {
                v = dt.getMonth();
                fmt = fmt.replace("MMM", props.fullMonths[v].substr(0, 3));
            }
            else if (_formatKeys[idx] == "MM") {
                v = dt.getMonth() + 1;
                fmt = fmt.replace("MM", (v < 10) ? "0" + v : v);
            }
            else if (_formatKeys[idx] == "dddd") {
                v = dt.getDay();
                fmt = fmt.replace("dddd", props.fullDays[v]);
            }
            else if (_formatKeys[idx] == "ddd") {
                v = dt.getDay();
                fmt = fmt.replace("ddd", props.fullDays[v].substr(0, 3));
            }
            else if (_formatKeys[idx] == "dd") {
                v = dt.getDate();
                fmt = fmt.replace("dd", (v < 10) ? "0" + v : v);
            }
            else if (_formatKeys[idx] == "hh24") {
                v = dt.getHours();
                fmt = fmt.replace("hh24", (v < 10) ? "0" + v : v);
            }
            else if (_formatKeys[idx] == "hh") {
                v = dt.getHours();
                v = (v > 12) ? v - 12 : v;
                fmt = fmt.replace("hh", (v < 10) ? "0" + v : v);
            }
            else if (_formatKeys[idx] == "mi") {
                v = dt.getMinutes();
                fmt = fmt.replace("mi", (v < 10) ? "0" + v : v);
            }
            else if (_formatKeys[idx] == "ss") {
                v = dt.getSeconds();
                fmt = fmt.replace("ss", (v < 10) ? "0" + v : v);
            }
        }
    }

    return fmt;
}

//--------------------------------------------------
// Converts an ISO date string (yyyy-MM-dd) into a Date object
function parseISODate(str) {
    if (str == null || str == "") {
        return null;
    }

    var tokens = str.split("-");
    var monthToken = tokens[1];
    var m = (monthToken.charAt(0) == "0")
        ? parseInt(monthToken.charAt(1))
        : parseInt(monthToken);

    var dayToken = tokens[2];
    var d = (dayToken.charAt(0) == "0")
        ? parseInt(dayToken.charAt(1))
        : parseInt(dayToken);

    var d1 = new Date();
    d1.setFullYear(parseInt(tokens[0]), (m - 1), d);
    d1.setHours(0, 0, 0, 0);

    return d1;
}

//--------------------------------------------------
// Converts a Date object into an ISO date string (yyyy-MM-dd)
function createISOString(dt) {
    if (dt == null) {
        return "";
    }

    var monthVal = dt.getMonth() + 1;
    var monthStr = ( monthVal > 9 ) ? monthVal : "0" + monthVal;

    var dayVal = dt.getDate();
    var dayStr = ( dayVal > 9 ) ? dayVal : "0" + dayVal;

    return dt.getFullYear() + "-" + monthStr + "-" + dayStr;
}
