package com.diandev.sharedpreferences;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SharedPreferences pref;
	private final String KEY_NAME = "key_name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
		
		showSavedName();
		
		final EditText etName = (EditText) findViewById(R.id.et_name);
		
		Button bSave = (Button) findViewById(R.id.bt_save);
		bSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String name = etName.getText().toString();
				if(name.equals("")){
					Toast.makeText(MainActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
				}else{
					saveName(name);
					showSavedName();
				}
			}
		});
	}
	
	private void showSavedName(){
		String savedName = getSavedName();
		
		TextView tvSavedName = (TextView) findViewById(R.id.tv_saved_name);
		tvSavedName.setText(savedName);
	}
	
	private String getSavedName(){
		return pref.getString(KEY_NAME, "-"); 
	}
	
	private void saveName(String name){
		Editor editor = pref.edit();
		editor.putString(KEY_NAME, name);
		editor.commit();
	}
}
