/**
 * Static class used to group Web Storage utility functions.
 */
function StorageUtils() {

}

/**
 * Determines if the browser supports HTML5 sessionStorage object
 */
StorageUtils.supportsSS = function () {
    try {
        return 'sessionStorage' in window && window['sessionStorage'] !== null;
    }
    catch (ex) {
        return false;
    }

    return false;
}

/**
 * retrieves the value for the key provided from the browser's sessionStorage
 */
StorageUtils.getSSValue = function (keyCode) {
    if (StorageUtils.supportsSS()) {
        return sessionStorage.getItem(keyCode);
    }
    else {
        return null;
    }
}

/**
 * saves the specified value using the key provided in the browser's sessionStorage
 */
StorageUtils.storeSSValue = function (keyCode, value) {
    if (StorageUtils.supportsSS()) {
        sessionStorage.setItem(keyCode, value);
    }
}

/**
 * determines if the browser supports HTML5 localStorage object
 */
StorageUtils.supportsLS = function () {
    try {
        return 'localStorage' in window && window['localStorage'] !== null;
    }
    catch (ex) {
        return false;
    }

    return false;
}

/**
 * retrieves the value for the key provided from the browser's localStorage
 */
StorageUtils.getLSValue = function (keyCode) {
    if (StorageUtils.supportsLS()) {
        return localStorage.getItem(keyCode);
    }
    else {
        return null;
    }
}

/**
 * saves the specified value using the key provided in the browser's localStorage
 */
StorageUtils.storeLSValue = function (keyCode, value) {
    if (StorageUtils.supportsLS()) {
        localStorage.setItem(keyCode, value);
    }
}

/**
 * removes the value for the key provided from the browser's localStorage
 */
StorageUtils.removeLSValue = function (keyCode) {
    if (StorageUtils.supportsLS()) {
        return localStorage.removeItem(keyCode);
    }
    else {
        return null;
    }
}
