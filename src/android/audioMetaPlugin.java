package com.ketan.plugin.audio;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.media.MediaMetadataRetriever;

public class audioMetaPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("pullID3")) {
            
			MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();
			String filePath = args.getString(0);
			metaRetriver.setDataSource(filePath);
			
			try{
				String result = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString(); 
			}
			catch(e){
				String result = "Unknown";
			}

			callbackContext.success(result);
			return true;

        } else {
	    callbackContext.error("Unknown method.");
            return false;

        }
    }
}
