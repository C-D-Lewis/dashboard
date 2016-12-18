package background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.getpebble.android.kit.PebbleKit;

import cl_toolkit.Logger;
import config.Build;
import config.Runtime;
import util.PebbleUtils;

import static com.getpebble.android.kit.Constants.MSG_DATA;
import static com.getpebble.android.kit.Constants.TRANSACTION_ID;

public class PebbleReceiver extends BroadcastReceiver {
	//Configuration
	public final String TAG = PebbleReceiver.class.getName();

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if(PebbleUtils.isThisApp(intent, Build.WATCH_APP_UUID)) {
				Runtime.startNewSession(context);
                Runtime.log(context, TAG, "Dashboard received Intent with matching UUID.", Logger.INFO);

				//Ack
				int transactionId = intent.getIntExtra(TRANSACTION_ID, -1);
				PebbleKit.sendAckToPebble(context, transactionId);
	
				//Get dictionary
				String jsonData = intent.getStringExtra(MSG_DATA);
				if (jsonData != null && !jsonData.isEmpty()) {
					//Launch service w/dictionary JSON
					Intent service = new Intent(context, HandlerService.class);
					service.putExtra("json", jsonData);
					context.startService(service);
				} else {
					Runtime.log(context, TAG, "jsonData was null or empty!", Logger.ERROR);
				}
			}
		} catch(Exception e) {
			Runtime.log(context, TAG, "Receiver threw exception: " + e.getLocalizedMessage(), Logger.ERROR);
			Runtime.logStackTrace(context, e);
		}
	}

}
