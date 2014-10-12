package com.silversages.viditure.adapter;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.koushikdutta.ion.Ion;
import com.silversages.viditure.R;
import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.objects.fetchDocument.Pages;
import com.silversages.viditure.util.TouchImageView;

public class DocumentAdapter extends ArrayAdapter<Pages> {

	private Activity activity;
	private Pages[] data;

	public DocumentAdapter(Activity context, Pages[] _data) {
		super(context, R.layout.row_document, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {

		public TouchImageView documentPic;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		Pages obj = data[position];
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_document, parent, false);

			ViewHolder viewHolder = new ViewHolder();
			FrameLayout layout = (FrameLayout) vi.findViewById(R.id.docLay);
			viewHolder.documentPic = (TouchImageView) vi
					.findViewById(R.id.docImg);
			vi.setTag(viewHolder);
			if (obj.getFields().length > 0) {
				Display display = ((WindowManager) activity
						.getApplicationContext().getSystemService(
								Context.WINDOW_SERVICE)).getDefaultDisplay();
				@SuppressWarnings("deprecation")
				int width = display.getWidth() / 3;
				EditText text = new EditText(activity);
				LayoutParams lp = new LayoutParams(width,
						LayoutParams.WRAP_CONTENT);
				layout.addView(text, lp);
			}
		}
		ViewHolder holder = (ViewHolder) vi.getTag();

		if (obj.getPageImage_url() != null) {
			Log.e("Vid", obj.getPageImage_url());
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("X-Auth-Token",
					ObjectHolder.getAuthXObject().getToken()));
			// Ion.with(holder.documentPic).placeholder(R.drawable.image)
			// .load(obj.getPageImage_url(), nameValuePairs);
			Ion.with(ViditureApp.getContext())
					.load(obj.getPageImage_url())
					.setHeader("X-Auth-Token",
							ObjectHolder.getAuthXObject().getToken())
					.setLogging("Viditure", Log.ERROR).withBitmap()
					.placeholder(R.drawable.image)
					.intoImageView(holder.documentPic);

		} else {

		}
		return vi;
	}

}
