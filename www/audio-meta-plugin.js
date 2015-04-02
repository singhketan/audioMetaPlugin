/*global cordova, module*/
var exec = require('cordova/exec'),
channel = require('cordova/channel');


// Override back button action to prevent being killed
document.addEventListener('backbutton', function () {}, false);

// Called before 'deviceready' listener will be called
channel.onCordovaReady.subscribe(function () {
    // Device plugin is ready now
    channel.onCordovaInfoReady.subscribe(function () {
        // Set the defaults
        exports.getID3 = function(filePath, successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "audioMetaPlugin", "pullID3", [filePath]);
		};
    });
});

exports.getID3 = function(filePath, successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "audioMetaPlugin", "pullID3", [filePath]);
		};