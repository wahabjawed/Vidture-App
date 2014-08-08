package com.silversages.viditure.activity;

import android.os.Bundle;
import android.widget.VideoView;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;

public class ViewVideo extends ViditureActivity {

	VideoView video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_video);
		getSupportActionBar().setTitle("Record");
		setupView();
		setupListner();

	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		video = (VideoView) findViewById(R.id.videoView1);
		video.setVideoPath("/sdcard/myvideo.mp4");

	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

	}

}
