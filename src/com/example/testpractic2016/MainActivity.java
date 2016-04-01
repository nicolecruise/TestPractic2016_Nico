package com.example.testpractic2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	private EditText leftEditText = null;
	private EditText rightEditText = null;
	private Button leftButton = null;
	private Button rightButton = null;
	private Button navigateToSecondaryActivityButton = null;

	private ButtonClickListener buttonClickListener = new ButtonClickListener();

	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View view) {
			
			//int leftNumberOfClicks = Integer.parseInt(leftEditText.getText().toString());
			//int rightNumberOfClicks = Integer.parseInt(rightEditText.getText().toString());

			EditText leftEditText = (EditText) MainActivity.this
					.findViewById(R.id.left_edit_text);
			EditText rightEditText = (EditText) MainActivity.this
					.findViewById(R.id.right_edit_text);
			int leftButtonClickedNumber = Integer.parseInt(leftEditText
					.getText().toString());
			int rightButtonClickedNumber = Integer.parseInt(rightEditText
					.getText().toString());
			

			int id = view.getId();

			switch (id) {
			case R.id.navigate_to_secondary_activity_button:
				Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
				int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) + Integer.parseInt(rightEditText.getText().toString());
				intent.putExtra("numberOfClicks", numberOfClicks);
				startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
				break;
			case R.id.left_button:
				leftButtonClickedNumber++;
				leftEditText.setText(String.valueOf(leftButtonClickedNumber));
				break;
			case R.id.right_button:
				rightButtonClickedNumber++;
				rightEditText.setText(String.valueOf(rightButtonClickedNumber));
				break;
			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EditText leftEditText = (EditText) findViewById(R.id.left_edit_text);
		EditText rightEditText = (EditText) findViewById(R.id.right_edit_text);

		leftEditText.setText(String.valueOf(0));
		rightEditText.setText(String.valueOf(0));
		
		Button leftButton = (Button)findViewById(R.id.left_button);
		leftButton.setOnClickListener(buttonClickListener);

		Button rightButton = (Button)findViewById(R.id.right_button);
		rightButton.setOnClickListener(buttonClickListener);
		
		Button navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
		navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
		
		 if (savedInstanceState != null) {
		      String leftCount = savedInstanceState.getString("leftCount");
		      if (leftCount != null) {
		        leftEditText.setText(leftCount);
		      } else {
		        leftEditText.setText(String.valueOf(0));
		      }
		      String rightCount = savedInstanceState.getString("rightCount");
		      if (rightCount != null) {
		        rightEditText.setText(rightCount);
		      } else {
		        rightEditText.setText(String.valueOf(0));
		      }
		    } else {
		      leftEditText.setText(String.valueOf(0));
		      rightEditText.setText(String.valueOf(0));
		    }

		
	}
	
	 @Override
	  protected void onSaveInstanceState(Bundle savedInstanceState) {
	    EditText leftEditText = (EditText)findViewById(R.id.left_edit_text);
	    EditText rightEditText = (EditText)findViewById(R.id.right_edit_text);
	    savedInstanceState.putString("leftCount", leftEditText.getText().toString());
	    savedInstanceState.putString("rightCount", rightEditText.getText().toString());
	  }
	 
	 
	 @Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			if (savedInstanceState.containsKey("leftCount")) {
				leftEditText.setText(savedInstanceState.getString("leftCount"));
			} else {
				leftEditText.setText(String.valueOf(0));
			}
			if (savedInstanceState.containsKey("rightCount")) {
				rightEditText.setText(savedInstanceState.getString("rightCount"));
			} else {
				rightEditText.setText(String.valueOf(0));
			}
	 }
	 
	 
	 
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
			if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
				Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
			}
		}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
