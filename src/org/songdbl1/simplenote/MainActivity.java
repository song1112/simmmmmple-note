package org.songdbl1.simplenote;

import com.etsy.android.grid.StaggeredGridView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
	private StaggeredGridView mGridView;  
	private ImageButton write;
	DBHelper helper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        write = (ImageButton)findViewById(R.id.write);
        write.setBackground(null);
        
        //點擊轉轉換到增加筆記頁面
        write.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, AddNote.class));
			}
		});
        //讀取資料並顯示在mGridView上
        readDate();  
        
    }

	@Override
	protected void onRestart() {
    	readDate();  
		super.onRestart();
	}

    private void readDate() {
    	helper = new DBHelper(MainActivity.this);
    	SQLiteDatabase db = helper.getWritableDatabase();
    	Cursor cursor = db.query("INCOME_NOTE", new String[] { "_ID",
    	"INCOME_DESCRIPTION", "INCOME_TIME" }, null, null, null, null, null);
    	MyAdapter ma = new MyAdapter(MainActivity.this, cursor);
    	mGridView.setAdapter(ma); 

    	db.close();
    }

}
