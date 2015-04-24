package com.narula.har.sunshine;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Result extends ActionBarActivity {
       ListView lv_players_results;
       ListAdapter adapter;
    TextView tv_winner;
    Player_details winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        lv_players_results=(ListView)findViewById(R.id.lv_players_results);
        adapter= new MyNewListAdapter();
        lv_players_results.setAdapter(adapter);
        tv_winner=(TextView)findViewById(R.id.tv_winner);
        winner=MainHandler.playerDetails.get(0);
        for(Player_details player:MainHandler.playerDetails)
        {
            if(player.laps>winner.laps)
            {
                winner=player;
            }

        }
        tv_winner.setText("WINNER IS "+winner.name);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_result, container, false);
            return rootView;
        }
    }

class MyNewListAdapter extends ArrayAdapter<Player_details> {
     public MyNewListAdapter()

    {
        super(Result.this, R.layout.fragment_result, MainHandler.playerDetails);
    }

    @Override
    public View getView(final int positon, View convertView, ViewGroup parent) {

        View players_view = convertView;
        if (players_view == null) {
            players_view = getLayoutInflater().inflate(R.layout.fragment_result, parent, false);
            Player_details current_player = MainHandler.playerDetails.get(positon);
            TextView tv_playerName = (TextView) players_view.findViewById(R.id.tv_playerName);
            TextView tv_palyerLap=(TextView)players_view.findViewById(R.id.tv_playerLaps);
            Player_details player=MainHandler.playerDetails.get(positon);
            tv_palyerLap.setText("LAPS :"+player.laps);
            tv_playerName.setText(player.name);

        }
        return  players_view;

    }
}
}

