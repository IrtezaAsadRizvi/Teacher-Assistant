package bd.edu.ulab.teacherassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Irteza on 05-May-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "T_assistant";
    public static final String TABLE_NAME = "schedules";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "day_in_month";
    public static final String COL_4 = "day_in_week";
    public static final String COL_5 = "month";
    public static final String COL_6 = "year";
    public static final String COL_7 = "hour";
    public static final String COL_8 = "min";
    public static final String COL_9 = "importance";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT NOT NULL, "+COL_3+" TEXT NOT NULL,"+COL_4+" TEXT NOT NULL,"+COL_5+" TEXT NOT NULL,"+COL_6+" TEXT NOT NULL,"+COL_7+" TEXT NOT NULL,"+COL_8+" TEXT NOT NULL,"+COL_9+" TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String NAME, String DAY_IN_MONTH, String DAY_IN_WEEK, String MONTH, String YEAR, String HOUR,String MIN, String IMPORTANCE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,DAY_IN_MONTH);
        contentValues.put(COL_4,DAY_IN_WEEK);
        contentValues.put(COL_5,MONTH);
        contentValues.put(COL_6,YEAR);
        contentValues.put(COL_7,HOUR);
        contentValues.put(COL_8,MIN);
        contentValues.put(COL_9,IMPORTANCE);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return res;
    }
    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}
