package com.silversages.viditure.activity;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.silversages.viditure.R;
import com.silversages.viditure.Network.Request.FetchDocRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.adapter.DocumentAdapter;
import com.silversages.viditure.objects.DocumentObject;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.util.CaptureSignature.signature;

public class DocumentViewer extends ViditureNetworkActivity {

	ListView docuemnt;
	Button startVituring;
	Dialog dialog_camera;
	Dialog dialog_agree;
	Dialog dialog_date;
	DocumentAdapter adapter;
	private Bitmap mBitmap;
	View mView;
	signature mSignature;
	Button mClear, mGetSign, mCancel;
	LinearLayout mContent;
	File mypath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.document_viewer);
		getSupportActionBar().setTitle("Document Name");
		setupView();
		setupListner();
		Intent intent = getIntent();
		// check if this intent is started via custom scheme link
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			Uri uri = intent.getData();
			// may be some test here with your custom uri
			String var = uri.getQueryParameter("documents"); // "str" is set
			String path = uri.getEncodedPath();
			// showToast(uri.getPath(), Toast.LENGTH_LONG);

			new FetchDocRequest(path).PerformTask(this);
		}

	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub

		showToast(ObjectHolder.getDocObj().getPages()[0].getPageImage_url(),
				Toast.LENGTH_LONG);

		DocumentObject[] documentItem = ObjectHolder.getDocumentImageObject();

		adapter = new DocumentAdapter(this, documentItem);
		docuemnt.setAdapter(adapter);

	}

	@Override
	public void preRequestExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void progressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		docuemnt = (ListView) findViewById(R.id.document);
		startVituring = (Button) findViewById(R.id.startVituring);
		dialog_camera = new Dialog(DocumentViewer.this);
		dialog_agree = new Dialog(DocumentViewer.this);
		dialog_date = new Dialog(DocumentViewer.this);
		dialog_date.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_agree.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_camera.requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		dialog_camera.setContentView(R.layout.dialog_picture);
		dialog_camera.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		// Include dialog.xml file

		dialog_date.setContentView(R.layout.dialog_date);
		dialog_date.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_agree.setContentView(R.layout.dialog_name);
		dialog_agree.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		Button viture = (Button) dialog_agree.findViewById(R.id.viture);
		// if decline button is clicked, close the custom dialog
		viture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog

				dialog_camera.show();
			}
		});

		TextView esignature = (TextView) dialog_agree
				.findViewById(R.id.esignature_label);

		// set values for custom dialog components - text, image and
		// button
		esignature.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_date.show();
			}
		});

		Button use_signature = (Button) dialog_date
				.findViewById(R.id.use_signature);
		// if decline button is clicked, close the custom dialog
		use_signature.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog

				startActivity(new Intent(DocumentViewer.this,
						DocumentName.class));
			}
		});

		// Include dialog.xml file

		Button next = (Button) dialog_camera.findViewById(R.id.nextbutton);
		// if decline button is clicked, close the custom dialog
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				dialog_agree.cancel();
				dialog_camera.cancel();
				dialog_date.cancel();
				startActivity(new Intent(DocumentViewer.this, TestCamera.class));
			}
		});

		startVituring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog_agree.show();
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public class signature extends View {
		private static final float STROKE_WIDTH = 5f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		private Paint paint = new Paint();
		private Path path = new Path();

		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();

		public signature(Context context, AttributeSet attrs) {
			super(context, attrs);
			paint.setAntiAlias(true);
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
		}

		public void save(View v) {
			Log.v("log_tag", "Width: " + v.getWidth());
			Log.v("log_tag", "Height: " + v.getHeight());
			if (mBitmap == null) {
				mBitmap = Bitmap.createBitmap(mContent.getWidth(),
						mContent.getHeight(), Bitmap.Config.RGB_565);
				;
			}
			Canvas canvas = new Canvas(mBitmap);
			try {
				FileOutputStream mFileOutStream = new FileOutputStream(mypath);

				v.draw(canvas);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
				mFileOutStream.flush();
				mFileOutStream.close();
				String url = Images.Media.insertImage(getContentResolver(),
						mBitmap, "title", null);
				Log.v("log_tag", "url: " + url);
				// In case you want to delete the file
				// boolean deleted = mypath.delete();
				// Log.v("log_tag","deleted: " + mypath.toString() + deleted);
				// If you want to convert the image to string use base64
				// converter

			} catch (Exception e) {
				Log.v("log_tag", e.toString());
			}
		}

		public void clear() {
			path.reset();
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(path, paint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			mGetSign.setEnabled(true);

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(eventX, eventY);
				lastTouchX = eventX;
				lastTouchY = eventY;
				return true;

			case MotionEvent.ACTION_MOVE:

			case MotionEvent.ACTION_UP:

				resetDirtyRect(eventX, eventY);
				int historySize = event.getHistorySize();
				for (int i = 0; i < historySize; i++) {
					float historicalX = event.getHistoricalX(i);
					float historicalY = event.getHistoricalY(i);
					expandDirtyRect(historicalX, historicalY);
					path.lineTo(historicalX, historicalY);
				}
				path.lineTo(eventX, eventY);
				break;

			default:
				debug("Ignored touch event: " + event.toString());
				return false;
			}

			invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
					(int) (dirtyRect.top - HALF_STROKE_WIDTH),
					(int) (dirtyRect.right + HALF_STROKE_WIDTH),
					(int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

			lastTouchX = eventX;
			lastTouchY = eventY;

			return true;
		}

		private void debug(String string) {
		}

		private void expandDirtyRect(float historicalX, float historicalY) {
			if (historicalX < dirtyRect.left) {
				dirtyRect.left = historicalX;
			} else if (historicalX > dirtyRect.right) {
				dirtyRect.right = historicalX;
			}

			if (historicalY < dirtyRect.top) {
				dirtyRect.top = historicalY;
			} else if (historicalY > dirtyRect.bottom) {
				dirtyRect.bottom = historicalY;
			}
		}

		private void resetDirtyRect(float eventX, float eventY) {
			dirtyRect.left = Math.min(lastTouchX, eventX);
			dirtyRect.right = Math.max(lastTouchX, eventX);
			dirtyRect.top = Math.min(lastTouchY, eventY);
			dirtyRect.bottom = Math.max(lastTouchY, eventY);
		}
	}

}
