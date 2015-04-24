package com.narula.har.sunshine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.logging.LogRecord;


public class FrontScreen extends ActionBarActivity {
    LinearLayout linear;
    private Handler handler;
     Drawable drawable_resource1;
     Drawable drawable_resource2;
    ProgressBar progress;
    TextView info;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_screen);
        linear=(LinearLayout)findViewById(R.id.main_layout);
        Resources res= getResources();
        drawable_resource1=res.getDrawable(R.drawable.image);
        drawable_resource2=res.getDrawable(R.drawable.image2);
        handler= new Handler();
        progress=(ProgressBar)findViewById(R.id.progress);

        info=(TextView)findViewById(R.id.info);
        info.setTextColor(Color.rgb(200,200,0));
        progress.getProgressDrawable().setColorFilter( Color.YELLOW, PorterDuff.Mode.SRC_IN);


       handleGui(drawable_resource1, drawable_resource2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void handleGui(Drawable drawable_local,Drawable drawable_another_local)
    {
        final Drawable drawable1=drawable_local;
        final Drawable drawable2=drawable_another_local;
        final String modules[]={"Intializing IntervalTraining.....","Loading modules.....","Connection to Database......","Optimizing UI...","Starting interval training......"};
       Runnable runnalble=new Runnable() {
            @Override
            public void run()
            {

                try
                {
                    for(int i=0;i<10;i++)
                    {
                        Thread.sleep(500);
                        final int value=i;
                        if(i<5) {

                            ;
                            handler.post(new Runnable() {
                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void run() {
                                    linear.setBackground(drawable2);
                                    progress.setProgress(value*10);
                                    if(value<=2)
                                    {
                                        info.setText(modules[0]);
                                    }
                                    else if(value<=4)
                                    {
                                        info.setText(modules[1]);
                                    }

                                }
                            });



                        }
                        else
                        {

                            handler.post(new Runnable() {
                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void run() {
                                    linear.setBackground(drawable1);
                                    progress.setProgress(value*10);
                                    if(value<=6)
                                    {
                                        info.setText(modules[2]);
                                    }
                                    else if(value<=8)
                                    {
                                        info.setText(modules[3]);
                                    }
                                    else if(value<=10)
                                    {
                                        info.setText(modules[4]);

                                    }
                                }
                            });


                        }
                        //Thread.sleep(1000);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent= new Intent(getBaseContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnalble).start();
    }
}
