package com.swan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class ArcadeRollMenuActivity extends Activity implements View.OnClickListener {

	private Button _newGameButton;
	private Button _optionsButton;
	private Button _quitButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menu);
	    
	    initListeners();
	}
	
	private void initListeners() 
	{
		_newGameButton = (Button)findViewById(R.id.newGameButton);
		_newGameButton.setOnClickListener(this);		
	    
	    _optionsButton = (Button)findViewById(R.id.optionButton);
	    _optionsButton.setOnClickListener(this);
	    
	    _quitButton = (Button)findViewById(R.id.quitButton);
	    _quitButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {

		if (v == _newGameButton) {
			Intent intent = new Intent(ArcadeRollMenuActivity.this, ArcadeRollActivity.class);
			startActivity(intent);
		}
		
		else if (v == _optionsButton) {
			setContentView(R.layout.options);
		}
		
		else if (v == _quitButton) {
			finish();
		}
	}
	
	@Override
	public void onStart() {
		
	}
	
	@Override
	public void onResume() {
		
	}
	
	@Override
	public void onPause() {
		
	}
	
	@Override
	public void onStop() {
		
	}
	
	@Override
	public void onDestroy() {
		
	}
	
}
