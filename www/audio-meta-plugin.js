/*global cordova, module*/
module.exports = {
    getID3: function (filePath, successCallback, errorCallback) {
        console.log("In here!");
	cordova.exec(successCallback, errorCallback, "audioMetaPlugin", "pullID3", [filePath]);
    }
};
