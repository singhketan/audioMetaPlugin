package com.ketan.plugin.audio;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaMetadataRetriever;

public class audioMetaPlugin extends CordovaPlugin {
	public static String ALBUM;
	public static String ARTIST;

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals("pullID3")) { 
			
			final String filePath = args.getString(0);
			cordova.getThreadPool().execute(new Runnable() {
		                public void run() {
		                	try {
						JSONObject r = new JSONObject();
						MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever(); 
						metaRetriver.setDataSource(filePath);
						
						ALBUM = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString();
						
						ARTIST = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString(); 
	
						r.put("filePath", filePath.toString());
						r.put("artist", ARTIST.toString());
						r.put("album", ALBUM.toString());
						
						callbackContext.success(r.toString());
					} 
					catch (JSONException e) {
					        Log.e(TAG, "Invalid JSON string: " + json, e);
					        callbackContext.error("Something went wrong");
					}
		                }
        		 });
        		 return true;
		}
		else
		{
			callbackContext.error("No such action defined");
			return false;
		}
	}
}
