package com.example.david.dontouch.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.david.dontouch.R;

/**
 * Created by david on 2017/5/4.
 */

public class SelfDialog extends Dialog{

    Context mContext;
    private NumberPicker numberPicker;
    private Button button;
    private int number;
    private ICustomDialogEventListener onCustomDialogEventListener;
    public interface ICustomDialogEventListener {
        void customDialogEvent(int number);
    }

    public SelfDialog(@NonNull Context context, ICustomDialogEventListener customDialogEventListener) {
        super(context);
        mContext = context;
        onCustomDialogEventListener = customDialogEventListener;
    }

    public SelfDialog(Context context, ICustomDialogEventListener customDialogEventListener, int theme) {
        super(context, theme);
        mContext = context;
        onCustomDialogEventListener = customDialogEventListener;
    }

    public void init() {
        numberPicker.setMaxValue(120);
        numberPicker.setMinValue(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.self_dialog, null);
        this.setContentView(layout);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        button = (Button) findViewById(R.id.button_conf);
        init();
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                number = numberPicker.getValue();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomDialogEventListener.customDialogEvent(number);
                dismiss();
            }
        });
    }
}
