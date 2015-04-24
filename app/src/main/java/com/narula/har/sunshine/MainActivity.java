package com.narula.har.sunshine;

import android.app.ListActivity;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button add_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            add_Button = (Button) findViewById(R.id.add_button);

            /*add_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                  System.out.println("the button pressed");
                }
            });
        */
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ArrayList<String> weather_list =new ArrayList<>();
            weather_list.add("Team 1->LAPS : 3  No.of Players:5");
            weather_list.add("Team 2->LAPS : 5  No.of Players:2");
            weather_list.add("Team 3->LAPS : 7  No.of Players:3");
            weather_list.add("Team 4->LAPS : 9  No.of Players:4");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.lkist_item_layout,R.id.list_item_layout_textview,weather_list);
            ListView weather_listview= (ListView)getActivity().findViewById(R.id.listview_forecast);
            weather_listview.setAdapter(adapter);
            return rootView;
    }

}
    public void click(View view)
    {
        CharSequence sequence="Proceeding to Set Workout parameters";
        Toast toast = Toast.makeText(this, sequence, Toast.LENGTH_SHORT);
        toast.show();
        Intent intent= new Intent(this,SetWorkoutParameters.class);
        startActivity(intent);
        finish();
    }
}
