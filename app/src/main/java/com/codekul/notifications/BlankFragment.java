package com.codekul.notifications;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends DialogFragment {

    public static final String KEY_DIALOG_ALERT = "alert";
    public static final String KEY_DIALOG_DATEPICKER = "datePicker";
    public static final String KEY_DIALOG_TIMEPICKER = "timePicker";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = null;

        if(getTag().equalsIgnoreCase(KEY_DIALOG_ALERT)) {

            DialogClick click = new DialogClick();

            final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,null,false);

            dialogView.findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    mt("dialog notification");
                }
            });

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity())
                            .setIcon(R.mipmap.ic_launcher)
                            //.setTitle("Title")
                            //.setMessage("Message")
                            //.setPositiveButton("+ve",click)
                            //.setNegativeButton("-ve",click)
                            //.setNeutralButton("=",click)
                            .setView(dialogView);

            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams manager = dialog.getWindow().getAttributes();
            manager.gravity = Gravity.TOP;
        }
        else if(getTag().equalsIgnoreCase(KEY_DIALOG_DATEPICKER)){

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    mt(""+dayOfMonth +" - "+ monthOfYear +" - "+year);
                                }
                            },
                            2016,
                            3,
                            29);
            dialog = datePickerDialog;
        }
        else if(getTag().equalsIgnoreCase(KEY_DIALOG_TIMEPICKER)){

             final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ss:mm:HH");
            Calendar calendar =Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            int minuts = calendar.get(Calendar.MINUTE);

             final Date date =calendar.getTime();
            Log.i("CurrentTime",date.toString() );


            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener(){
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    mt(dateFormat.format(date));
                                }
                            },
                            hour,
                            minuts,
                            false);

            dialog = timePickerDialog;
        }

        return dialog;
    }
    private class DialogClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which ==  DialogInterface.BUTTON_POSITIVE) {

                mt("+ve");
            }
            else if(which == DialogInterface.BUTTON_NEUTRAL){

                mt("==");
            }
            else {
                mt("-ve");
            }
        }
    }

    private void mt(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();
    }
}
