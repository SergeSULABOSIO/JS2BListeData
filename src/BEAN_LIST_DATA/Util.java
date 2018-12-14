/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP Pavilion
 */
public class Util {
    
    public static String getDate(Date date) {
        String strDate = "";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        strDate = df.format(date);
        return strDate;
    }

    public static String getDateFormatMySQL(Date date) {
        String strDate = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = df.format(date);
        return strDate;
    }

    public static String getDate_today_morning() {
        String strDate = "";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        strDate = df.format(new Date());
        return strDate + " 00:00:01";
    }

    public static String getDate_today_night() {
        String strDate = "";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        strDate = df.format(new Date());
        return strDate + " 23:59:59";
    }

    public static String getDate_today_now() {
        return getDate(new Date());
    }

    public static Date getDate(String date) {
        return new Date(date);
    }
    
}
