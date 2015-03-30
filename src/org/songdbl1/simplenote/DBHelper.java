package org.songdbl1.simplenote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME="NOTEDB";
	private Context m_context;
	
	private static final String INCOME_CREATE_DDL = 
		      "CREATE TABLE INCOME_NOTE ("
		      + "_ID INTEGER PRIMARY KEY," 
		      + "INCOME_DESCRIPTION TEXT,"
		      +	"INCOME_TIME TEXT);";
	private static final String INCOME_DELETE_DDL = 
		      "DROP TABLE IF EXISTS INCOME_MAIN;";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		m_context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Toast.makeText(m_context, "DB Create", Toast.LENGTH_LONG).show();
        db.execSQL(INCOME_CREATE_DDL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Toast.makeText(m_context, "DB upgrade", Toast.LENGTH_LONG).show();
	    db.execSQL(INCOME_DELETE_DDL);
	    db.execSQL(INCOME_CREATE_DDL);
	}
	


}
