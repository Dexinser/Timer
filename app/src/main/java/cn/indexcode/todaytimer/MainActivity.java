package cn.indexcode.todaytimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> list = new ArrayList<>();
    private int seconds = 0;
    private String time;
    private int num = 1;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        runTimer();
    }

    private void initList() {
//        for (int i = 0; i < 100; i++) {
        if(num == 1){
            Item item = new Item("#" + num + "   " + time, R.drawable.one);
            list.add(item);
        }else if(num == 2) {
            Item item = new Item("#" + num + "   " + time, R.drawable.two);
            list.add(item);
        }else if(num == 3) {
            Item item = new Item("#" + num + "   " + time, R.drawable.three);
            list.add(item);
        }else{
            Item item = new Item("#" + num + "   " + time, R.mipmap.ic_launcher);
            list.add(item);
        }
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.item, list);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        num ++;
//        }
    }

     public void onClickStart(View view){
        running = true;
    }
     public void onClickStop(View view){
        running = false;
    }
     public void onClickReset(View view){
        running = false;
        seconds = 0;
        num = 1;
        list.clear();
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.item, list);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
    }
    public void onClickTag(View view){
        initList();
    }
    private void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler(); handler.post(new Runnable() {
            @Override
            public void run() {
                int totalsec = seconds / 100;
                int minutes = totalsec / 60;
                int secs = totalsec % 60;
                int millsecs = seconds % 100;
                time = String.format("%02d:%02d:%02d",minutes,secs,millsecs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,10);
            }
        });
    }
}
