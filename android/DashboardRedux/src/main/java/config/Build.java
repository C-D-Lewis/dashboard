package config;

import java.util.UUID;

/**
 * Build-time data
 */
public class Build {
	
	public static final UUID 
		WATCH_APP_UUID = UUID.fromString("d522bc8e-65f3-4edf-9651-05e1e4567021");
	
	public static final String 
		DEBUG_LOG_NAME = "Dashboard-log.txt",
		WATCH_APP_PBW_NAME = "dashboard.pbw";

    public static final String
        PACKAGE_NAME = "com.wordpress.ninedof.dashboard";
	
	public static final int
		DEBUG_LOG_MAX_SIZE_BYTES = 100000;	// 100kB
	
	/**
	 * Keep these up-to-date!
	 */
	public static final int
		VERSION_CODE = 41;
	public static final String
		VERSION = "4.10",
		WATCH_APP_COMPATIBLE_VERSION = "4.8";  // Most recent compatible watchapp version
	public static final boolean
		RELEASE = true;  // Hide some log details like the protocol in production

	public static final int
		NUM_TOGGLES = 9;	//Used for safe iteration over all configuration spinners (NEVER CHANGE THIS AGAIN)

}
