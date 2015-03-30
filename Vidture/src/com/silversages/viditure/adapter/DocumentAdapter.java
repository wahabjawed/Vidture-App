package com.silversages.viditure.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.silversages.viditure.R;
import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.activity.DocumentViewer;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.objects.fetchDocument.Pages;
import com.silversages.viditure.util.TouchImageView;

public class DocumentAdapter extends ArrayAdapter<Pages> {

	private DocumentViewer activity;
	private Pages[] data;
	Pages obj = null;
	public static TextView stateTextView;
	public int counts;

	class pageData {

		int pageNo;
		int fieldno;
		int y;
		boolean isDone = false;
		String data;

	}

	public DocumentAdapter(Activity context, Pages[] _data) {
		super(context, R.layout.row_document, _data);
		this.activity = (DocumentViewer) context;
		this.data = _data;
		counts = data.length;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		obj = data[position];
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_document, parent, false);

		}
		final TouchImageView documentPic = (TouchImageView) vi
				.findViewById(R.id.docImg);
		final FrameLayout layout = (FrameLayout) vi.findViewById(R.id.docLay);

		if (obj.getPageImage_url() != null) {
			Log.e("Vid", obj.getPageImage_url());
			Ion.with(ViditureApp.getContext())
					.load(obj.getPageImage_url())
					.setHeader("X-Auth-Token",
							ObjectHolder.getAuthXObject().getToken())
					.setLogging("Viditure", Log.ERROR).withBitmap()
					.placeholder(R.drawable.image).intoImageView(documentPic)
					.setCallback(new FutureCallback<ImageView>() {

						@Override
						public void onCompleted(Exception arg0, ImageView arg1) {
							// TODO Auto-generated method stub
							layout.measure(MeasureSpec.UNSPECIFIED,
									MeasureSpec.UNSPECIFIED);
							documentPic.measure(MeasureSpec.UNSPECIFIED,
									MeasureSpec.UNSPECIFIED);

							Log.e("LayoutYOffset",
									""
											+ (layout.getMeasuredHeight() - documentPic
													.getMeasuredHeight()) / 2);
							Log.e("LayoutXOffset",
									""
											+ (layout.getMeasuredWidth() - documentPic
													.getMeasuredWidth()) / 2);

							Log.e("PageWidthFromURL", ""
									+ obj.getPagePosition().getWidth());
							Log.e("PageHeightFromURL", ""
									+ obj.getPagePosition().getHeight());

							Log.e("PageWidthLayout",
									"" + documentPic.getWidth());
							Log.e("PageHeightLayout",
									"" + documentPic.getHeight());

							double PageWidth = obj.getPagePosition().getWidth();
							double ImageWidth = documentPic.getWidth();

							double PageHeight = obj.getPagePosition()
									.getHeight();
							double ImageHeight = documentPic
									.getMeasuredHeight();

							double widthRatio = ImageWidth / PageWidth;
							double heightRatio = ImageHeight / PageHeight;

							Log.e("widthRatio", "" + widthRatio);
							Log.e("heightRatio", "" + heightRatio);

							if (obj.getFields() != null
									&& obj.getFields().length > 0) {
								for (int i = 0; i < obj.getFields().length; i++) {
									final int count = i;
									Log.e("Viditure",
											"Dynamic Layout: "
													+ obj.getFields()[i]
															.getKind()
															.getType());
									// if
									// (obj.getFields()[i].getKind().getType()
									// .equals("TEXT")) {

									final TextView text = new TextView(activity);
									final ImageView filedImage = new ImageView(
											activity);
									Ion.with(ViditureApp.getContext())
											.load(obj.getFields()[i].getKind()
													.getFieldImage_url())
											.setHeader(
													"X-Auth-Token",
													ObjectHolder
															.getAuthXObject()
															.getToken())
											.intoImageView(filedImage);
									// text.setId(R.id.);
									text.setText(obj.getFields()[i].getKind()
											.getName());
									text.setTextSize(8.0f);
									text.setBackgroundColor(Color.BLUE);
									FrameLayout.LayoutParams lpTxt = new FrameLayout.LayoutParams(
											(int) (obj.getFields()[i]
													.getScreenPos().getWidth() * widthRatio),
											(int) (obj.getFields()[i]
													.getScreenPos().getHeight() * heightRatio),
											Gravity.TOP | Gravity.LEFT);
									lpTxt.topMargin = (int) (obj.getFields()[i]
											.getScreenPos().getTop() * heightRatio)
											- ((int) (obj.getFields()[i]
													.getScreenPos().getHeight() * heightRatio));
									lpTxt.leftMargin = (int) (obj.getFields()[i]
											.getScreenPos().getLeft() * widthRatio)
											+ ((layout.getMeasuredWidth() - documentPic
													.getMeasuredWidth()) / 2);

									Log.e("Viditure",
											"Field Top: "
													+ obj.getFields()[i]
															.getScreenPos()
															.getTop()
													+ "--"
													+ obj.getFields()[i]
															.getScreenPos()
															.getTop()
													* heightRatio);

									Log.e("Viditure",
											"Field Left: "
													+ obj.getFields()[i]
															.getScreenPos()
															.getLeft()
													+ "--"
													+ obj.getFields()[i]
															.getScreenPos()
															.getLeft()
													* heightRatio);

									text.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method
											// stub
											if (obj.getFields()[count]
													.getKind().getName()
													.equals("initials")
													|| obj.getFields()[count]
															.getKind()
															.getName()
															.equals("fullname")
													|| obj.getFields()[count]
															.getKind()
															.getName()
															.equals("date")) {
												stateTextView = text;
												DocumentAdapter.this.activity.openDialogName(obj
														.getFields()[count]
														.getKind().getName());
											} else if (obj.getFields()[count]
													.getKind().getName()
													.equals("viditure")) {
												DocumentAdapter.this.activity
														.openDialogVideo();
											} else if (obj.getFields()[count]
													.getKind().getName()
													.equals("photoid")) {
												DocumentAdapter.this.activity
														.openDialogPhoto();
											}
											// Toast.makeText(
											// DocumentAdapter.this.activity,
											// obj.getFields()[count]
											// .getKind()
											// .getName(),
											// Toast.LENGTH_LONG).show();
										}
									});
									// lp.horizontalMargin =
									// obj.getFields()[i].getScreenPos()
									// .getLeft() - 45;
									// lp.verticalMargin =
									// obj.getFields()[i].getScreenPos()
									// .getTop() - 80;
									layout.addView(text, lpTxt);

									layout.addView(filedImage, lpTxt);
									// }
								}
							}
							counts--;

							if (counts == 0) {
								DocumentAdapter.this.activity.dialog.dismiss();
								DocumentAdapter.this.activity.docuemntList
										.setSelection(0);
								counts--;

							} else if (counts > 0) {
								DocumentAdapter.this.activity.docuemntList
										.smoothScrollToPosition(data.length
												- counts - 1);
								double percent = (((double) data.length - (double) counts) / (double) data.length)
										* (double) 100;

								DocumentAdapter.this.activity.dialog
										.setMessage("Fetching Documents..."
												+ percent + "%");
							}
						}
					});

		}
		return vi;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getViewTypeCount() {

		return getCount();
	}

	@Override
	public int getItemViewType(int position) {

		return position;
	}

}
