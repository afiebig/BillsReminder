package cl.afiebig.billsreminder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import java.lang.reflect.Field;

public class AddNewBill extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);

        //Remove Month and Year from the two Date Picker
        DatePicker picker1 = (DatePicker) findViewById(R.id.datePaymentDay);
        DatePicker picker2 = (DatePicker) findViewById(R.id.dateReminderDay);
        try {
            Field f[] = picker1.getClass().getDeclaredFields();
            Field g[] = picker2.getClass().getDeclaredFields();
            for (Field field : f) {
                if (field.getName().equals("mYearPicker") || field.getName().equals("mYearSpinner")
                        || field.getName().equals("mMonthPicker") || field.getName().equals("mMonthSpinner")) {
                    field.setAccessible(true);
                    Object yearPicker = field.get(picker1);
                    ((View) yearPicker).setVisibility(View.GONE);
                }
            }
            for (Field field2 : g) {
                if (field2.getName().equals("mYearPicker") || field2.getName().equals("mYearSpinner")
                        || field2.getName().equals("mMonthPicker") || field2.getName().equals("mMonthSpinner")) {
                    field2.setAccessible(true);
                    Object yearPicker = field2.get(picker2);
                    ((View) yearPicker).setVisibility(View.GONE);
                }
            }
        }
        catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
            Log.d("ERROR", e.getMessage());
        }
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
}
