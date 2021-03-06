package cn.edu.bupt.lab805.pestguide.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBcopyUtils extends SQLiteOpenHelper {
    private static final String LOG_TAG = "DataHelper";

    private SQLiteDatabase mDataBase;
    private final Context mContext;

    // private final String DATABASE_PATH = android.os.Environment
    //       .getExternalStorageDirectory().getAbsolutePath() + "/pest/";
    private static final String DATABASE_PATH = "/data/data/cn.edu.bupt.lab805.pestguide/";
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 3;


    public DBcopyUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public DBcopyUtils(Context context, String name, CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        Log.d(LOG_TAG, "dbExist: " + dbExist);

        if (dbExist) {
            // do nothing - database already exist  
        } else {
            // By calling this method and empty database will be created into  
            // the default system path  
            // of your application so we are gonna be able to overwrite that  
            // database with our database.  
//            this.getReadableDatabase();  
            copyDataBase();
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        return file.exists();
       /* Log.d(LOG_TAG, "checkDataBase");
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            Log.i("info", "checkDataBase: " + "SQLiteException");
            return true;
        } finally {
            if (checkDB != null) {
                checkDB.close();
            }
            boolean flag = checkDB != null ? true : false;
            Log.i("DBcopyUtils", "checkDataBase: 数据库是否存在：" + flag);
            return flag;
        }*/
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        Log.d(LOG_TAG, "=================copyDataBase");
        // Open your local db as the input stream  
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db  
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile  
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams  
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }


    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub  
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub  
    }

}  

