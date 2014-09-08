package com.silversages.viditure.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.silversages.viditure.R;
import com.silversages.viditure.objects.DocumentObject;

public class DocumentAdapter extends ArrayAdapter<DocumentObject> {

	private Activity activity;
	private DocumentObject[] data;

	public DocumentAdapter(Activity context, DocumentObject[] _data) {
		super(context, R.layout.row_document, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {

		public ImageView documentPic;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_document, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.documentPic = (ImageView) vi.findViewById(R.id.docImg);
			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		DocumentObject obj = data[position];
		if (obj.getImgURL() != null) {

			Ion.with(holder.documentPic)
					.placeholder(R.drawable.image)
					.load("http://54.183.77.229:8080/rbs/documents/53f8d90ae4b0e2d5a4aae1c6/pages/0/image");

			// Bitmap bmp = BitmapFactory.decodeByteArray(obj.getImg(), 0,
			// obj.getImg().length);
			// holder.documentPic.setImageBitmap(bmp);

		} else {

		}

		return vi;
	}

}
