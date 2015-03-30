package org.songdbl1.simplenote;



import com.etsy.android.grid.util.DynamicHeightTextView;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private final LayoutInflater mLayoutInflater;
    
	DBHelper helper = new DBHelper(null);
	Context context;
	Cursor cursor;
	Intent intent;
	public MyAdapter(Context context, Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
		mLayoutInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		cursor.moveToPosition(position);
		final ViewHolder vh;
		if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            vh = new ViewHolder();
            vh.note = (DynamicHeightTextView) convertView.findViewById(R.id.note);
            vh.id = (TextView) convertView.findViewById(R.id.id);
            vh.time = (TextView)convertView.findViewById(R.id.time);
            vh.del = (ImageButton)convertView.findViewById(R.id.del);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
		//設定整個背景色
		convertView.setBackgroundColor(COLOR[position % 5]);
		
		vh.id.setText(cursor.getString(0));
		vh.note.setText(cursor.getString(1));
		vh.time.setText(cursor.getString(2));
		vh.note.setTextColor(Color.WHITE);
		vh.del.setBackground(null);
		vh.del.setOnClickListener(new OnClickListener() {
			//按下刪除鍵刪除資料
			@Override
			public void onClick(View v) {
				DBHelper helper = new DBHelper(context);
				SQLiteDatabase db = helper.getWritableDatabase();

				db.delete("INCOME_NOTE", "_ID="+vh.id.getText(), null);
				Toast.makeText(context, "Delete Success:" + vh.id.getText(), Toast.LENGTH_LONG).show();
				db.close();
				//刪除後在轉發到主頁面
				intent = new Intent(context, MainActivity.class);
				//讓使用者按下返回鍵不會回到上一頁
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	//項目介面
	static class ViewHolder {
        DynamicHeightTextView note;
        TextView time,id;
        ImageButton del;
    }
	
	//顏色設定
	private static final int[] COLOR = new int[] {  
        0xff33b5e5, 0xffaa66cc, 0xff99cc00, 0xffffbb33, 0xffff4444  
    };  
}
