package com.ketan.plugin.audio;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaMetadataRetriever;

public class audioMetaPlugin extends CordovaPlugin {
	public static String ALBUM;
	public static String ARTIST;
	public static String GENRE;
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals("pullID3")) { 
			
			final String filePath = args.getString(0);
			cordova.getThreadPool().execute(new Runnable() {
		                public void run() {
		                    	JSONObject r = new JSONObject();
					MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever(); 
					metaRetriver.setDataSource(filePath);
					
					try {
						ALBUM = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString();
	
					}
					catch (Exception e){
						ALBUM = "unknown-album";
					}
					
					try {
						ARTIST = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString(); 
					}
					catch (Exception e){
						ARTIST = "unknown-artist";
					}
					
					try {
						GENRE = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE).toString();
					}
					catch (Exception e){
						GENRE = "unknown-genre";
					}
					
			
				
					r.put("album", ALBUM.toString());
					r.put("artist", ARTIST.toString());
					r.put("genre", GENRE.toString());
					
					callbackContext.success(r.toString());
					
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
