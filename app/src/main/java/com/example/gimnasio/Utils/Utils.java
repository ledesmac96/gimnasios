package com.example.gimnasio.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static final String IS_FIRST_TIME_LAUNCH = "is_first";
    public static final String PREF_NAME = "gimnasio_kratos";
    public static final String DNI = "dni_user";
    public static final String VERIFICADO = "dni_verificado_%s";
    public static final String TURNOS = "turno";
    public static final String USUARIOS = "user";
    public static final String NOTICIA = "noticia";
    public static final String CUOTA = "cuota";
    public static final int LIST_RESET = 1;
    public static final int LIST_NOMBRE = 2;
    public static final int LIST_DNI = 3;

    public static void showLog(String t, String msj) {
        if (false)
            Log.e(t, msj);
    }

    public static void showToast(Context c, String msj) {
        Toast.makeText(c, msj, Toast.LENGTH_SHORT).show();
    }


    public static String getFecha() {

        Date date = new Date(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String value = cal.get(Calendar.DAY_OF_MONTH) + "-"
                + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" +
                cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + ":" + cal.get(Calendar.MILLISECOND);

        return value;


    }

}
