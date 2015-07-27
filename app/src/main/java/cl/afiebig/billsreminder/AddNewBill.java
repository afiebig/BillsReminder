package cl.afiebig.billsreminder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddNewBill extends Activity {

    private Bill bill;
    private EditText billName;
    private EditText comment;
    private DatePicker payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);

        billName = (EditText)findViewById(R.id.editBillName);
        comment = (EditText)findViewById(R.id.editBillDescription);
        payment = (DatePicker)findViewById(R.id.datePicker);
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

    private void dateChanged(View v){

    }

    public void save (View v){
        //Do Save Stuffs.
        int day = payment.getDayOfMonth();
        int month = payment.getMonth();
        int year = payment.getYear();

        GregorianCalendar date = new GregorianCalendar( year, month, day);
        bill = new Bill(billName.getText().toString(), comment.getText().toString(),date);
        //Return no previus view.
    }
}
