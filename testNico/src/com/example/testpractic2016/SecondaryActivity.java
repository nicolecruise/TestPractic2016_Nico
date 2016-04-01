package com.example.testpractic2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity  extends Activity {
	
	private Button okButton = null; 
	private Button cancelButton = null; 
	private TextView numberOfClicksTextView = null;

	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	private class ButtonClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View view) {
			switch(view.getId()) {
				case R.id.ok_button:
					setResult(RESULT_OK, null);
					break;
				case R.id.cancel_button:
					setResult(RESULT_CANCELED, null);
					break;
			}
			finish();
		}
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondary_activity);
		
		numberOfClicksTextView = (TextView)findViewById(R.id.number_of_clicks_text_view);
		Intent intent = getIntent();
		if (intent != null && intent.getExtras().containsKey("numberOfClicks")) {
			int numberOfClicks = intent.getIntExtra("numberOfClicks", -1);
			numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
		}

		okButton = (Button)findViewById(R.id.ok_button);
		okButton.setOnClickListener(buttonClickListener);
		cancelButton = (Button)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(buttonClickListener);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
