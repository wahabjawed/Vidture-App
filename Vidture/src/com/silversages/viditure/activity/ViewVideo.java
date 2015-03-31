package com.silversages.viditure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;

public class ViewVideo extends ViditureActivity {

	VideoView video;
	Button useThis;
	Button recordNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_video);
		getSupportActionBar().setTitle("Record");
		setupView();
		setupListner();

	}

	@Override
	public void setupView() {
		// TODO Auto-generated method stub

		useThis = (Button) findViewById(R.id.useThis);

		recordNew = (Button) findViewById(R.id.recordNew);

		video = (VideoView) findViewById(R.id.videoView1);

		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			video.setVideoPath(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/myvideo.mp4");
		} else {
			video.setVideoPath(getFilesDir().getAbsolutePath() + "/video.mp4");
		}
		MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(video);
		video.setMediaController(mediaController);
		video.start();

	}

	@Override
	public void setupListner() {
		// TODO Auto-generated method stub

		recordNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ViewVideo.this.finish();
				startActivity(new Intent(ViewVideo.this, ReadSentence.class));
			}
		});

		useThis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// startActivity(new Intent(ViewVideo.this,
				// DocumentName.class));
				ViewVideo.this.finish();

			}
		});

	}

}
