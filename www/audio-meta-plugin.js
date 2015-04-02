/*global cordova, module*/
 window.getID3 = function(filePath, successCallback, errorCallback) {
 			console.log("IN here!");
			cordova.exec(successCallback, errorCallback, "audioMetaPlugin", "pullID3", [filePath]);
		};
