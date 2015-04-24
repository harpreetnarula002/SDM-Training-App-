package com.narula.har.sunshine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import android.os.Handler;
import android.widget.Toast;


public class ActualGamePlay extends ActionBarActivity {
    TextView laps, Timer;
    String Laps_String, Timer_String;
    int no_of_players_active;
    Handler handler_new;
    Runnable runnable_new;
    int counter = 1;
    //ListView players;
    ArrayAdapter<Player_details> adapter;
     MediaPlayer mp;
    Button buttons[];
    TextView tv_laps[];
    TextView tv_players_name[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_gameplay);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            MainHandler.current_lap = 1;
            //players=(ListView)findViewById(R.id.lv_players);
            laps = (TextView) findViewById(R.id.tvLapCounter);
            Timer = (TextView) findViewById(R.id.tvTimeCounter);
            buttons=new Button[5];
            tv_laps=new TextView[5];
            tv_players_name= new TextView[5];

            buttons[0]=(Button)findViewById(R.id.bt_stop1);
            buttons[1]=(Button)findViewById(R.id.bt_stop2);
            buttons[2]=(Button)findViewById(R.id.bt_stop3);
            buttons[3]=(Button)findViewById(R.id.bt_stop4);
            buttons[4]=(Button)findViewById(R.id.bt_stop5);



            tv_laps[0]=(TextView)findViewById(R.id.tv_lap1);
            tv_laps[1]=(TextView)findViewById(R.id.tv_lap2);
            tv_laps[2]=(TextView)findViewById(R.id.tv_lap3);
            tv_laps[3]=(TextView)findViewById(R.id.tv_lap4);
            tv_laps[4]=(TextView)findViewById(R.id.tv_lap5);

            tv_players_name[0]=(TextView)findViewById(R.id.tv_player1);
            tv_players_name[1]=(TextView)findViewById(R.id.tv_player2);
            tv_players_name[2]=(TextView)findViewById(R.id.tv_player3);
            tv_players_name[3]=(TextView)findViewById(R.id.tv_player4);
            tv_players_name[4]=(TextView)findViewById(R.id.tv_player5);
            for(int i=0;i<MainHandler.number_of_players;i++)
            {
                buttons[i].setVisibility(View.VISIBLE);
            }
            //adapter= new MyListAdapter();





            //Laps_String = Integer.toString(MainHandler.current_lap);
            //Timer_String = Integer.toString(MainHandler.time);
            //laps.setText("LAP :" + MainHandler.current_lap);
            //Timer.setText("TIME :" +MainHandler.time);
            no_of_players_active = MainHandler.number_of_players;
            MainHandler.no_of_players_currently_active = no_of_players_active;
            //players.setAdapter(adapter);
            handler_new = new Handler();
            int x = lapTimerHandle();
            mp= MediaPlayer.create(this, R.drawable.sounds);
            System.out.println("the value of    x     " + x);
            System.out.println("calling function again ::::::::::::::::::::::::::");

         /*  x= lapTimerHandle();
            System.out.println("calling function again ::::::::::::::::::::::::::");
            System.out.println("the value of    x     "+x);*/
           /* while((no_of_players_active>0)&&(MainHandler.time>0))
            {

                MainHandler.show();
                //lapTimerHandle();
                synchronized (this)
                {
                    runnable = new Runnable() {


                        @Override
                        public void run() {
                            try {
                                System.out.println("in the Run Function");
                                //int current_time = MainHandler.time;

                                //int lap = MainHandler.current_lap;

                                for (int i = MainHandler.time; i >= 1; i--) {
                                    Thread.sleep(1000);
                                    final int timer_value = i;
                                    final int lap_value=MainHandler.current_lap;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Timer.setText("Time :" + timer_value);
                                            laps.setText("LAP :"+lap_value);
                                        }
                                    });


                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    };
                    new Thread(runnable).start();

                }
                MainHandler.current_lap++;
                no_of_players_active--; // to be removed just for debugging
                MainHandler.no_of_players_currently_active=no_of_players_active;
                MainHandler.time= (int) Math.floor(MainHandler.time-((MainHandler.time*MainHandler.percentage_decreament)/100.0));

            }

*/

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_credentials, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_actual_gameplay, container, false);
            return rootView;
        }
    }

    public synchronized int lapTimerHandle() {
        //while ((no_of_players_active > 0) && (MainHandler.time > 0)) {
        final MediaPlayer mp = MediaPlayer.create(this, R.drawable.sounds);

        runnable_new = new Runnable()
        {


            @Override
            public void run() {
                try {
                    //playSound();
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    try {

                        mp.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    handler_new.post(new Runnable() {
                        @Override
                        public void run() {
                            CharSequence toast_text = " LAP  :"+MainHandler.current_lap+" STARTED !....";
                            Toast toast = Toast.makeText(getBaseContext(), toast_text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    System.out.println("in the Run Function");
                    MainHandler.show();
                    int current_time = MainHandler.time;

                    int lap = MainHandler.current_lap;
                    for(Player_details player:MainHandler.playerDetails)
                    {
                        if(player.playing==true)
                        {
                            player.laps=MainHandler.current_lap;
                        }
                    }
                    for (int i = MainHandler.time; i >= 0; i--) {
                        System.out.println("In the Timer ===================================" + i);
                        Thread.sleep(1000);
                        final int timer_value = i;
                        final int lap_value = MainHandler.current_lap;
                        if(i==Math.floor(MainHandler.time/2.0))
                        {
                            //playSound();
                            if (mp.isPlaying()) {
                                mp.stop();
                            }
                            try {

                                mp.start();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            }
                            handler_new.post(new Runnable() {
                                @Override
                                public void run() {
                                    CharSequence toast_text = "  LAP  :"+MainHandler.current_lap+" HALF TIME OVER !....";
                                    Toast toast = Toast.makeText(getBaseContext(), toast_text, Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                        }
                        if (MainHandler.no_of_players_currently_active == 0)
                        {
                            Intent intent_new=new Intent(getBaseContext(),Result.class);
                            startActivity(intent_new);

                            ActualGamePlay.this.finish();
                            break;
                        }
                        handler_new.post(new Runnable() {
                            @Override
                            public void run() {


                                Timer.setText("Time :" + timer_value);
                                laps.setText("LAP :" + lap_value);
                                for(int i=0;i<MainHandler.number_of_players;i++)
                                {
                                    if(MainHandler.playerDetails.get(i).playing)
                                    {
                                        tv_players_name[i].setText(MainHandler.playerDetails.get(i).name);
                                        tv_laps[i].setText("LAPS :"+MainHandler.playerDetails.get(i).laps+"");
                                    }

                                }


                            }

                        });


                    }
                    System.out.println("exiting from the TRY   function");

                } catch (Exception e) {

                    //e.printStackTrace();
                    System.out.println("---------------------------Exception--------------------------");
                }
                System.out.println("in the Run Function/////////////////////////////////////////2nd time/////////////////////////");

                MainHandler.current_lap++;
                //no_of_players_active--; // to be removed just for debugging
                MainHandler.no_of_players_currently_active = no_of_players_active;
                MainHandler.time = (int) Math.floor(MainHandler.time - ((MainHandler.time * MainHandler.percentage_decreament) / 100.0));
                MainHandler.show();
                System.out.println("exiting from the Run   function");
                if (MainHandler.no_of_players_currently_active > 0)
                {
                    lapTimerHandle();
                }

                /*else
                {
                    Intent intent_new=new Intent(getBaseContext(),Result.class);
                    startActivity(intent_new);

                    ActualGamePlay.this.finish();

                }*/

            }
        };
        new Thread(runnable_new).start();

        return (counter++);
    }
    // }

    public void playSound() {

        if (mp.isPlaying()) {
            mp.stop();
        }
        try {
            /*mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("AudioFile.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();*/
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    public void eliminate(View v)
    {
        switch(v.getId()){

            case R.id.bt_stop1:
                MainHandler.playerDetails.get(0).playing=false;
                no_of_players_active--;
                MainHandler.no_of_players_currently_active = no_of_players_active;
                v.setVisibility(View.GONE);
                MainHandler.show();
                break;
            case R.id.bt_stop2:
                MainHandler.playerDetails.get(1).playing=false;
                no_of_players_active--;
                MainHandler.no_of_players_currently_active = no_of_players_active;
                v.setVisibility(View.GONE);
                MainHandler.show();
                break;
            case R.id.bt_stop3:
                MainHandler.playerDetails.get(2).playing=false;
                no_of_players_active--;
                MainHandler.no_of_players_currently_active = no_of_players_active;
                v.setVisibility(View.GONE);
                MainHandler.show();
                break;
            case R.id.bt_stop4:
                MainHandler.playerDetails.get(3).playing=false;
                no_of_players_active--;
                MainHandler.no_of_players_currently_active = no_of_players_active;
                v.setVisibility(View.GONE);
                MainHandler.show();
                break;
            case R.id.bt_stop5:
                MainHandler.playerDetails.get(4).playing=false;
                no_of_players_active--;
                MainHandler.no_of_players_currently_active = no_of_players_active;
                v.setVisibility(View.GONE);
                MainHandler.show();
                break;
        }
    }

    public void endTraining(View v)
    {
        Intent intent_new=new Intent(getBaseContext(),Result.class);
        startActivity(intent_new);

        ActualGamePlay.this.finish();


    }
    /*class MyListAdapter extends ArrayAdapter<Player_details>
    {
        public MyListAdapter()
        {
            super(ActualGamePlay.this,R.layout.fragment_actual_gameplay,MainHandler.playerDetails);
        }
        @Override
        public View getView(final int positon, View convertView,ViewGroup parent)
        {

            View players_view=convertView;
            if(players_view==null) {
               players_view=getLayoutInflater().inflate(R.layout.fragment_actual_gameplay,parent,false);
               Player_details current_player=MainHandler.playerDetails.get(positon);
               TextView tv_player=(TextView)players_view.findViewById(R.id.tv_player);
               TextView tv_lap=(TextView)players_view.findViewById(R.id.tv_lap);
                Button bt_stop= (Button)players_view.findViewById(R.id.bt_stop);
                tv_player.setText(current_player.name);
                 tv_lap.setText("LAP :"+current_player.laps);
                bt_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    mp.reset();

                        MainHandler.playerDetails.get(positon).playing=false;

                    }
                });
            }
           return players_view;
        }
    }*/
}