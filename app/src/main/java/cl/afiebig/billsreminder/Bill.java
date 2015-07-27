package cl.afiebig.billsreminder;

import java.util.GregorianCalendar;

/**
 * Created by afiebig on 7/27/15.
 */
public class Bill {

    private String billName;
    private String description;
    private GregorianCalendar paymentDate;

    public Bill(String name, String description, GregorianCalendar date){
        this.billName = name;
        this.description = description;
        this.paymentDate = date;
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

    public GregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(GregorianCalendar paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
