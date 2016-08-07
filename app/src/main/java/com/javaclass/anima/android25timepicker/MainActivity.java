package com.javaclass.anima.android25timepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextView textTime1,textTime2;
    private Button BtnGetTime;
    String H;
    String M;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTime1    =    (TextView)findViewById(R.id.textTime1);        //自動取得時間
        textTime2    =    (TextView)findViewById(R.id.textTime2);        //透過按鈕取得時間
        BtnGetTime   =    (Button)findViewById(R.id.BtnGetTime);
        BtnGetTime.setOnClickListener(GTimeListener);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int  hourOfDay, int minute) {

                //取得 hour的值，透過TimeFix方法。轉換成String.並初始H。
                H = TimeFix(hourOfDay);
                //取得 minute的值，透過TimeFix方法。轉換成String.並初始M。
                M = TimeFix(minute);
                //將取得的資料設定到 textTime1
                textTime1.setText(H + ":" +M);
            }
        });
    }

    private Button.OnClickListener GTimeListener =
            new Button.OnClickListener(){
                //按下按鈕後將先前Timepicker取得的值 H和M，
                //設定到 textTime2
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    switch (v.getId())
                    {
                        case R.id.BtnGetTime:
                        {
                            textTime2.setText(H + ":" +M);
                            break;
                        }
                    }
                }
            };

    //Timepicker取得的資料為int，但時間數字小於10，是不會有像06這樣的情況。
    //所以透過TimeFix這個方法將送過來的數字轉換成String，在判斷是否需要加0。
    private static String TimeFix(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
