package com.cross.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.cross.ashpazi.MainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Navit on 1/24/2015.
 */
public class Dao extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.cross.ashpazi/databases/";

    private static String DB_NAME = "ashpazi";

    private static SQLiteDatabase db;

    private final Context context;
    public Dao(Context context){
        super(context, "ashpazi", null,1);
        this.context=context;
    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            Cursor c=checkDB.rawQuery("select version from version",null);
            c.moveToFirst();
            if(c.getString(0).equals(MainActivity.version))
                return true;
            return false;

        }catch(Exception e){

            return false;

        }

    }
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(db != null)
            db.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

    }
    public List<Recepient> getElements(String query){
        final List<Recepient> list=new LinkedList<Recepient>();
        final Cursor c=db.rawQuery(query,null);
        if(c!=null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                final Recepient r=new Recepient();
                r.id=c.getLong(0);
                r.name=c.getString(1);
                r.avalie=c.getString(2);
                r.dastur=c.getString(3);
                r.ax=c.getString(4);
                r.type=c.getInt(5);
                list.add(r);
                c.moveToNext();
            }
        }
        return list;
    }
}
