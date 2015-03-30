package org.songdbl1.simplenote;


import java.text.SimpleDateFormat;
import java.util.Date;



import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNote extends Activity {
	EditText input;
	Button add;
	TextView remain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		remain = (TextView)findViewById(R.id.remain);
		input = (EditText)findViewById(R.id.input);	
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				//計算剩餘可寫有幾個字
				String text = input.getText().toString();
				int len = 140 - text.length();
				text = String.valueOf(len);
				remain.setText(text);
			}
		});

		
		add = (Button)findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			//將資料存放在資料庫
			@Override
			public void onClick(View v) {
				DBHelper helper = new DBHelper(AddNote.this);
				SQLiteDatabase db = helper.getWritableDatabase();
				
				ContentValues args = new ContentValues();
				args.put("INCOME_DESCRIPTION", input.getText().toString());
				args.put("INCOME_TIME", getNowTime());
				
				long rowid = db.insert("INCOME_NOTE", null, args);
				Toast.makeText(AddNote.this, "Success",
						Toast.LENGTH_LONG).show();
				db.close();
				finish();
			}
		});
		
	}
		
	//取得系統時間
	public String getNowTime() {
		SimpleDateFormat formatter = 
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = new Date(System.currentTimeMillis()) ;
		String nowtime = formatter.format(now);
		return nowtime;
	}

}
