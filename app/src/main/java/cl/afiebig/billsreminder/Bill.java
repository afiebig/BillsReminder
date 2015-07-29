package cl.afiebig.billsreminder;

import java.util.Date;

/**
 * BillsReminder
 * cl.afiebig.billsreminder created with Android Studio
 * Created by afiebig on 7/28/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 */

public class Bill {

    private String billName;
    private String description;
    private String paymentDate;
    private int amount;

    public Bill(String name, String description, String date, int amount){
        this.billName = name;
        this.description = description;
        this.paymentDate = date;
        this.amount = amount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
