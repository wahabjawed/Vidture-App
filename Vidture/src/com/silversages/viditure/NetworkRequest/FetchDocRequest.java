package com.silversages.viditure.NetworkRequest;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.objects.fetchDocument.FetchDocObject;
import com.silversages.viditure.util.JSONParser;

public class FetchDocRequest implements IRequestHandler {

	String URL;
	ViditureNetworkActivity activity;

	public FetchDocRequest(String _URL) {

		this.URL = _URL;

	}

	class Task extends AsyncTask<Void, Void, JSONObject> {

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

			try {
				if (result != null) {
					Gson gson = new Gson();
					// JSONObject json_data = result;
					// String gsonS = gson.toJson(result);


					ObjectHolder.setDocObj(gson.fromJson(result.toString(),
							FetchDocObject.class));
					activity.postRequestExecute();
					Log.d("Document Request",ObjectHolder.getDocObj().getPages()[0].getPageImage_url()+"");
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
			JSONParser sendData = new JSONParser();
			JSONObject result = sendData.makeHttpRequest(networkAddress + URL,
					"GET", nameValuePairs);
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
