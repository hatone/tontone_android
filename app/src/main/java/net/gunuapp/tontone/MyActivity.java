package net.gunuapp.tontone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MyActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

		Parse.initialize(this, "KWLpWyQm5Ymw5lf4ubQ6yCMB0CBG9cVYwdsL0Poy", "PaEUOIgaOBm9kVQ7Brs5AwfOhNw9MNA5SdLkxwCa");
		PushService.setDefaultPushCallback(this, MyActivity.class);
		ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		installation.addAllUnique("channels", Arrays.asList("test"));
		installation.saveInBackground();

		Button sendMsgButton;
		sendMsgButton = (Button)this.findViewById(R.id.sendMsg);
		sendMsgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ParsePush push = new ParsePush();
				push.setChannel("test");

				JSONObject data = new JSONObject();
				try {
					data.put("action", "net.gunuapp.tontone.UPDATE_STATUS");
					data.put("sound", "famima.caf");
				} catch (JSONException e) {
					e.printStackTrace();
				}

				push.setData(data);
				push.sendInBackground();
			}
		});

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
