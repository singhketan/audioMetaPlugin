package com.ketan.plugin.audio;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaMetadataRetriever;

public class audioMetaPlugin extends CordovaPlugin {
	public static String TITLE;
	public static String ALBUM;
	public static String ARTIST;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("pullID3")) { 
			
			String fullPath = args.getString(0);
			MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever(); 
			metaRetriver.setDataSource(fullPath);
			
			TITLE = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString();	
			ALBUM = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString();
			ARTIST = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString(); 
			
                	try {
				JSONObject r = new JSONObject();
				r.put("fullPath", fullPath.toString());
				r.put("artist", ARTIST.toString());
				r.put("album", ALBUM.toString());
				r.put("title", TITLE.toString());
				callbackContext.success(r.toString());
			} 
			catch (JSONException e) {
			        callbackContext.error("Something went wrong with JSONObject most probably.");
			}
			
        		return true;
		}
		else
		{
			callbackContext.error("No such action defined");
			return false;
		}
	}
}
