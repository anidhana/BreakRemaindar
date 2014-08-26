package com.ebook.breakremaindar1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;


public class Alarm extends BroadcastReceiver {


	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 MediaPlayer mp = MediaPlayer.create(context, R.raw.anbe);
	     mp.start();
	 	Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
	}}
