package orientacion.com.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "admin", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(id integer primary key autoincrement, puntos int)");
        db.execSQL("create table areas(id integer primary key autoincrement, puntos int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(id integer primary key autoincrement, puntos int )");
        db.execSQL("create table areas(id integer primary key autoincrement, puntos int )");
    }

}
