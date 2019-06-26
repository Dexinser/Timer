package cn.indexcode.todaytimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
public class MainActivity extends AppCompatActivity {
 private int seconds = 0;
 private String time;
 private int num = 1;
 private boolean running;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        runTimer();
        TextView timeContent = findViewById(R.id.time_content);
        timeContent.setMovementMethod(new ScrollingMovementMethod());
    }
     public void onClickStart(View view){
        running = true;
    }
     public void onClickStop(View view){
        running = false;
    }
     public void onClickReset(View view){
        final TextView timeContent = findViewById(R.id.time_content);
        running = false;
        seconds = 0;
        timeContent.setText("");
    }
    public void onClickTag(View view){
        final TextView timeContent = findViewById(R.id.time_content);
        if(num != 1){
            timeContent.append("\n");
        }
        timeContent.append("#" + num + "   " + time);
        num++;
    }
    private void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler(); handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                time = String.format("%02d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}
