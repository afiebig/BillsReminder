package cl.afiebig.billsreminder;

import java.util.GregorianCalendar;

/**
 *
 * ${FileName} created with Android Studio
 * Created by afiebig on 7/27/15.
 * Alfredo Fiebig C. - afiebigc[AT]gmail[DOT]com
 * --------------------------------------------------------------------------
 *    ${projectName}
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    --------------------------------------------------------------------------
 **/
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
