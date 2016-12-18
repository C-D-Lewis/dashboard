package util;

import android.content.Context;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

import cl_toolkit.Logger;
import config.Runtime;

/**
 * Check watchapp version against a known compatible version on Android
 * 1. Call version_check(char *version, void(*callback)(bool)); from watchapp
 * 2. On reception of that dictionary, call check() below to issue response and trigger callback
 * 
 * @author Chris Lewis
 * Repo: https://github.com/C-D-Lewis/pebble-version-check
 */
public class VersionCheck {
	
	private static final String TAG = VersionCheck.class.getName();
	
	public static final int
		KEY_VERSION_CHECK_VERSION = 58283,
		KEY_VERSION_CHECK_SUCCESS = 58278,
		KEY_VERSION_CHECK_FAILURE = 58232;
	
	/**
	 * To be called from a PebbleDataReceiver implementation
	 * @param context		Context of the Android app
	 * @param uuid			UUID of the watchapp
	 * @param dict			Incoming dictionary
	 * @param localVersion	Compatible version with this Android app version
	 */
	public static boolean check(Context context, UUID uuid, PebbleDictionary dict, String localVersion) {
		if(dict.getString(KEY_VERSION_CHECK_VERSION) != null) {
			String remoteVersion = dict.getString(KEY_VERSION_CHECK_VERSION).toString();
            boolean success = localVersion.equals(remoteVersion);
			
			//Let the watch know
			PebbleDictionary response = new PebbleDictionary();
			
			if (success) {
                response.addInt32(KEY_VERSION_CHECK_SUCCESS, 0);
				Runtime.log(context, TAG, "Version check passed!", Logger.INFO);
            } else {
                response.addInt32(KEY_VERSION_CHECK_FAILURE, 0);
				Runtime.log(context, TAG, "Version check failed! Got local: " + localVersion + " remote: " + remoteVersion, Logger.ERROR);
			}

			PebbleKit.sendDataToPebble(context, uuid, response);
            return success;
		}

        // Not present, ignore
        return false;
	}

}
