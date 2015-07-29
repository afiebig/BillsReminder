package cl.afiebig.billsreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * BillsReminder
 * cl.afiebig.billsreminder created with Android Studio
 * Created by afiebig on 7/28/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 */

public class AddNewBill extends Activity {

    public static final String TAG = "Bill";

    private Bill bill;
    private EditText billName;
    private EditText comment;
    private EditText payment;
    private EditText amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);

        billName = (EditText)findViewById(R.id.editBillName);
        comment = (EditText)findViewById(R.id.editBillDescription);
        payment = (EditText)findViewById(R.id.datePicker);
        amount = (EditText)findViewById(R.id.editAmount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_bill, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void save (View v){
        //Do Save Stuffs.
        try{
            bill = new Bill(billName.getText().toString(), comment.getText().toString(),
                    payment.getText().toString(), Integer.parseInt(amount.getText().toString()));
            DB.getDb(this.getApplicationContext()).insertBill(bill);
        } catch (Exception e){
            Log.e(TAG, "Error al grabar bill", e);
            new AlertDialog.Builder(this.getApplicationContext())
                    .setTitle("Error")
                    .setMessage("Unable to Save Bill")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        //Return to previus view.
        this.finish();
    }
}
