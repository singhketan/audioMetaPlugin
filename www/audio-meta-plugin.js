/*global cordova, module*/
module.exports = {
    getID3: function (filePath, successCallback, errorCallback) {
	cordova.exec(successCallback, errorCallback, "audioMetaPlugin", "pullID3", [filePath]);
    }
};
