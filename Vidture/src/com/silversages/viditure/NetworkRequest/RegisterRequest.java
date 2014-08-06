package com.silversages.viditure.NetworkRequest;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.util.JSONParser;
import com.silversages.viditure.util.SQLHelper;

public class RegisterRequest implements IRequestHandler {

	String name;
	String password;
	String email;
	ViditureNetworkActivity activity;

	public RegisterRequest(String name, String email, String password) {

		this.password = password;
		this.email = email;
	}

	class Task extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			try {
				if (result != null) {
					JSONObject json_data = result;

					int success = json_data.getInt("success");
					if (success == 1) {
						int ID = json_data.getInt("ID");
						SQLHelper.registerUser(ID, name, email, password);
						activity.postRequestExecute();
					} else {
						String text = json_data.getString("message");
						activity.showToast(text, Toast.LENGTH_LONG);
					}

				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Log.d("Vidture", "Start Register");
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("email", email));
			nameValuePairs.add(new BasicNameValuePair("name", name));

			JSONParser sendData = new JSONParser();
			JSONObject result = sendData.makeHttpRequest(networkAddress
					+ "register.php", "POST", nameValuePairs);
			Log.d("Vidture", "finish sending register info");

			return result;

		}

	}

	@Override
	public void PerformTask(ViditureNetworkActivity _activity) {
		// TODO Auto-generated method stub
		activity = _activity;
		new Task().execute();

	}

}