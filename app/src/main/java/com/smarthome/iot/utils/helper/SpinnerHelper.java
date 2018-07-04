package com.smarthome.iot.utils.helper;

import android.widget.Spinner;

import com.smarthome.iot.ui.widget.io.types.Option;

public class SpinnerHelper {

    public static int findPositionSpinner(Spinner spinner, int value){
        for (int i=0;i<spinner.getCount();i++){
            Option option = (Option)spinner.getItemAtPosition(i);
            if (option.value == value){
                return i;
            }
        }

        return 0;
    }

}
