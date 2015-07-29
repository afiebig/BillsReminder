package cl.afiebig.billsreminder;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * BillsReminder
 * cl.afiebig.billsreminder created with Android Studio
 * Created by afiebig on 7/28/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 */

public class BillsList extends Activity {

    public static final String TAG = "Bill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_list);
        Log.d(TAG, "Hola Mundo!");

        //Inicializa la BD y llama a una BD de solo lectura para pintar la lista de bills
        //TODO: Almacenear la BD en variable para poder pintar el select *;
        PaintBills();


    }

    //Recore el cursor con bills y las dibuja en el listado principal
    private void PaintBills(){
        Bill[] bills = DB.getDb(this.getApplicationContext()).selectBill();
        TableLayout layout = (TableLayout)findViewById(R.id.billsTable);
        for (int i = 0; i < bills.length; i++){
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView bill = new TextView(row.getContext());
            TextView date = new TextView(row.getContext());
            TextView amount = new TextView(row.getContext());

            bill.setLayoutParams( new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,1));
            date.setLayoutParams( new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,1));
            amount.setLayoutParams( new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,1));

            row.addView(bill);
            row.addView(date);
            row.addView(amount);
            layout.addView(row);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bills_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                return true;
            case R.id.action_newBill:
                newBill();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called the New Bill Activity */
    public void newBill() {
        Intent intent = new Intent(this, AddNewBill.class);
        startActivity(intent);
    }
}
