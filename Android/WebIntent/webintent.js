/**
 *  * Phonegap Web Intent plugin
 *   * Copyright (c) Boris Smus 2010
 *    *
 *     */
var WebIntent = function() { 

};

WebIntent.ACTION_SEND   = "android.intent.action.SEND";
WebIntent.ACTION_VIEW   = "android.intent.action.VIEW";
WebIntent.EXTRA_TEXT    = "android.intent.extra.TEXT";
WebIntent.EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
WebIntent.EXTRA_STREAM  = "android.intent.extra.STREAM";
WebIntent.EXTRA_EMAIL   = "android.intent.extra.EMAIL";

WebIntent.prototype.startActivity = function(params, success, fail) {
    return PhoneGap.exec(success, fail, 'WebIntent', 'startActivity', [params]);
};

WebIntent.prototype.hasExtra = function(params, success, fail) {
    return PhoneGap.exec(success, fail, 'WebIntent', 'hasExtra', [params]);
};

WebIntent.prototype.getExtra = function(params, success, fail) {
    return PhoneGap.exec(success, fail, 'WebIntent', 'getExtra', [params]);
};

WebIntent.prototype.getUri = function(success, fail) {
    return PhoneGap.exec(success, fail, 'WebIntent', 'getUri', []);
};

WebIntent.prototype.onNewIntent = function(callback) {
    return PhoneGap.exec(callback, function(){}, 'WebIntent', 'onNewIntent', []);
};

PhoneGap.addConstructor(function() {
    PhoneGap.addPlugin('webintent', new WebIntent());
});
