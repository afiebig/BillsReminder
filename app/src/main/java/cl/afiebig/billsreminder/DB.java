package cl.afiebig.billsreminder;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * BillsReminder
 * cl.afiebig.billsreminder created with Android Studio
 * Created by afiebig on 7/28/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 */

public class DB extends SQLiteOpenHelper {

    public static final String TAG = "Bill";

    private static DB db;

    public static DB getDb (){
        return db;
    }


    public static DB getDb (Context context){
     if (db == null) {
         db = new DB(context);
     }
        return DB.getDb();
    }


    final static int DB_VERSION = 1;
    final static String DB_NAME = "BillsDB.s3db";
    Context context;

    public DB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        // Store the context for later use
        this.context = context;
    }

    public void insertBill(Bill bill) throws Exception{
        SQLiteDatabase base = this.db.getWritableDatabase();
        base.beginTransaction();
        try {
            base.execSQL("INSERT INTO bills ( billName, description, payment, billValue ) VALUES ( \"" +
                    bill.getBillName() + "\" , \"" + bill.getDescription() + "\" , \""
                        + bill.getPaymentDate() + "\" , " +
                    bill.getAmount() + " );");
            base.setTransactionSuccessful();
            Log.i(TAG, "Bill Insertado");
        } catch (Exception e) {
            Log.e(TAG, "Unable to Insert Bill");
            throw e;
        }finally {
            base.endTransaction();
        }
    }

    public Bill[] selectBill() {
        SQLiteDatabase base = this.db.getReadableDatabase();
        base.beginTransaction();
        Bill [] result = new Bill[1];;
        SQLiteCursor cursor;

        try {
            cursor = (SQLiteCursor)base.query("bills", null, null, null, null, null, null);
            base.setTransactionSuccessful();
            int i = 0;
            if (cursor.getCount() > 0){
                result = new Bill[cursor.getCount()];
                while (cursor.moveToNext()){
                    Bill a = new Bill(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                            cursor.getInt(4));
                    result[i] = a;
                    i++;
                    Log.i(TAG,"Objeto "+i+" :" + a.toString());
                }
            } else {
                Log.i(TAG,"Cursor sin resultados");
            }
        } catch (Exception e) {
            Log.e(TAG, "Unable to get Bills");
        } finally {
            base.endTransaction();
        }
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "On Create");
        executeSQLScript(db, "create.sql");
    }

    @Override
    //TODO: Verificar que esto funciona... no es mas facil hacer un while o un for?
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            switch (oldVersion) {
                case 1:
                    executeSQLScript(this.db.getWritableDatabase() , "update_v" + (1+oldVersion) + ".sql");
                case 2:
                    executeSQLScript(this.db.getWritableDatabase(),  "update_v" + (1+oldVersion) + ".sql");
            }
        }
    }

    private void executeSQLScript(SQLiteDatabase database, String script) {
        try{
            Log.i(TAG,"Entre al SQL");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("scripts/"+script);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] createScript = outputStream.toString().split(";");
            for (int i = 0; i < createScript.length; i++) {
                String sqlStatement = createScript[i].trim();
                // TODO You may want to parse out comments here
                if (sqlStatement.length() > 0) {
                    database.execSQL(sqlStatement + ";");
                }
            }
        } catch (IOException | SQLException e){
            Log.e(TAG, "Unable to open File", e);
        }
    }

}
