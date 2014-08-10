package net.gunuapp.tontone;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * @author Takako Ohshima
 */
public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "net.gunuapp.tontone.MyCustomReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.nc18763);

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification();

		notification.sound = uri;


		try {
			String action = intent.getAction();
			String channel = intent.getExtras().getString("com.parse.Channel");
			JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

			Log.d(TAG,"helohelo");
			Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
			Iterator itr = json.keys();
			while (itr.hasNext()) {
				String key = (String) itr.next();
				Log.d(TAG, "..." + key + " => " + json.getString(key));
			}


		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}

		notificationManager.notify(1, notification	);
	}
}
