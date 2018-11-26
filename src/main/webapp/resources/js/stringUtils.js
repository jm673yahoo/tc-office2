//--------------------------------------------------
// returns an empty string if the string is NULL
function defaultString(str) {
    return ( str == null ) ? "" : str;
}

//--------------------------------------------------
// returns an abbreviated string based on the max length provided
function stringAbbr(str, maxLen) {
    if (str != null) {
        if (str.length > maxLen) {
            return str.substr(0, maxLen - 3) + "...";
        }
        else {
            return str;
        }
    }

    return str;
}

// --------------------------------------------------
// determines if a token is contained within a string
function stringContains(str, token) {
    if (str == null) {
        return false;
    }

    return ( str.indexOf(token) != -1 );
}

// --------------------------------------------------
// returns the sub-string before the specified delimiter
function subStringBefore(str, delimiter) {
    if (str == null) {
        return "";
    }

    var idx = str.indexOf(delimiter);

    if (idx > -1) {
        return str.substring(0, idx);
    }

    return str;
}

// --------------------------------------------------
// returns the sub-string after the specified delimiter
function subStringAfter(str, delimiter) {
    if (str == null) {
        return "";
    }

    var idx = str.indexOf(delimiter);

    if (idx > -1) {
        return str.substring(idx + delimiter.length);
    }

    return str;
}
