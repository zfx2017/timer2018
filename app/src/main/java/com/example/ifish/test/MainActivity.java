package com.example.ifish.test;
        import android.os.SystemClock;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Chronometer;
        import android.widget.TextView;
        import java.util.Locale;
        import java.util.Timer;
        import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    private Chronometer timer;
    private Timer timer1;
    private TextView textView;
    private TimerTask timerTask;
    Boolean zfx = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (Chronometer) findViewById(R.id.timer);
        textView = (TextView) findViewById(R.id.text);
        timer1 = new Timer();
    }
    public void btnClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
    }
    public void stopClick(View view) {
        timer.stop();
    }
    public void startClick(View view) {
        timerTask = new TimerTask() {
            int cnt = 900;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(getStringTime(cnt--));
                    }
                });
            }
        };
        timer1.schedule(timerTask,0,1000);
    }
    private String getStringTime(int cnt) {
        int hour = cnt/3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,second);
    }
    public int lengthOfLastWord(String s) {
        int j = 0;
        Boolean zfx = false;
        char [] ch=s.toCharArray();
        a:for (int i = ch.length-1; i >= 0; i--) {
            if (Character.isSpaceChar(ch[i])&&zfx == true) {
                break a;
            }else{
                j++;
                zfx = true;
            }
        }
        return j;

    }
    public void stopClick1(View view) {
        if (!timerTask.cancel()){
            timerTask.cancel();
            timer1.cancel();
        }
    }
}
