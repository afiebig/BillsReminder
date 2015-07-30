package cl.afiebig.billsreminder;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
    }

    @Override
    protected  void onResume(){
        super.onResume();

        PaintBills();
    }

    //Recore el cursor con bills y las dibuja en el listado principal
    private void PaintBills(){
        final float scale = getResources().getDisplayMetrics().density;
        int padding_5dp = (int) (6 * scale + 0.5f);

        //Get the complete bills list from te DB
        Bill[] bills = DB.getDb(this.getApplicationContext()).selectBill();

        //Get the table layout to add new rows and remove previous items
        TableLayout layout = (TableLayout)findViewById(R.id.billsTable);
        layout.removeAllViews();

        //Add all the bills to the view as new row
        for (int i = 0; i < bills.length; i++){

            //New Table Row for each bill
            TableRow row = new TableRow(this.getApplicationContext());
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            //Textview for bills data
            TextView bill = new TextView(this.getApplicationContext());
            TextView date = new TextView(this.getApplicationContext());
            TextView amount = new TextView(this.getApplicationContext());

            //Layout for the TextView
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            params.setMargins(padding_5dp,padding_5dp,padding_5dp,padding_5dp);
            params.gravity = Gravity.LEFT;

            bill.setLayoutParams(params);

          //  params.weight = 2f;

         //   params.gravity = Gravity.CENTER;
            date.setLayoutParams(params);

         //   params.gravity = Gravity.RIGHT;
            ViewGroup.LayoutParams params1 = amount.getLayoutParams();
            amount.setLayoutParams(params);

            bill.setPadding(padding_5dp, padding_5dp, padding_5dp, padding_5dp);
            date.setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);
            amount.setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);

            //Set the text for the textviews
            bill.setText(bills[i].getBillName());
            bill.setTextColor(Color.BLACK);
            date.setText(bills[i].getPaymentDate());
            date.setTextColor(Color.BLACK);
            amount.setText(Integer.toString(bills[i].getAmount()));
            amount.setTextColor(Color.BLACK);

            //Add the new textviews and the row to the screen.
            row.addView(bill);
            row.addView(date);
            row.addView(amount);
            layout.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        }
    }

    private void ClearBills(){

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
