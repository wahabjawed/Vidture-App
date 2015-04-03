package com.silversages.viditure.Networks.request;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.silversages.viditure.abstracts.AbstractRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.model.AuthXObject;
import com.silversages.viditure.model.ObjectHolder;
import com.silversages.viditure.model.fetchDocument.FetchDocObject;
import com.silversages.viditure.util.JSONParser;

public class FetchDocRequest extends AbstractRequest implements IRequestHandler {

	String URL;
	ViditureNetworkActivity activity;

	public FetchDocRequest(String _URL) {

		this.URL = _URL;

	}

	class Task extends AsyncTask<Void, Void, JSONObject> {
		JSONParser sendData;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			activity.preRequestExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ObjectHolder.setAuthXObject(new AuthXObject(sendData.httpResponse
					.getFirstHeader("X-Auth-Token").getValue()));
			try {
				if (result != null) {
					Gson gson = new Gson();
					// JSONObject json_data = result;
					// String gsonS = gson.toJson(result);

					ObjectHolder.setDocObj(gson.fromJson(result.toString(),
							FetchDocObject.class));
					activity.postRequestExecute();

				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		}

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Log.d("Vidture", "Start Register");
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			sendData = new JSONParser();
			sendData.setHeader("X-Viditure-App", "android");
			JSONObject result = sendData.makeHttpRequest(networkAddress + URL,
					"GET", nameValuePairs);
			Log.d("Vidture", "URL: " + networkAddress + URL);

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
