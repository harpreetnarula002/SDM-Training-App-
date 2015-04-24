package com.narula.har.sunshine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class SetWorkoutParameters extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_workout_parameters);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_workout_parameters, menu);
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
    public void next(View v)
    {
        EditText all_edit_texts[]=new EditText[4];
        all_edit_texts[0]=(EditText)findViewById(R.id.et_players);
        all_edit_texts[1]=(EditText)findViewById(R.id.et_distance);
        all_edit_texts[2]=(EditText)findViewById(R.id.et_time);
        all_edit_texts[3]=(EditText)findViewById(R.id.et_percentage);
        String text[]=new String[4];

        boolean everything_filled=false;
        int count=0;
        for(int i=0;i<4;i++)
        {
            text[i]=all_edit_texts[i].getText().toString();
        }
        for(int i=0;i<text.length;i++)
        {
            count=i;

            System.out.println("The values of Text field is "+text[i]);
            if(text[i].length()==0)
            {
                CharSequence toast_text="Kindly enter the values in all Fields !";
                Toast toast= Toast.makeText(this,toast_text,Toast.LENGTH_SHORT);
                toast.show();
                break;
            }

        }
        if(count==3)
        {
            everything_filled=true;
            int i=0;
            MainHandler.number_of_players=Integer.parseInt(text[i++]);
            MainHandler.distance=Integer.parseInt(text[i++]);
            MainHandler.time=Integer.parseInt(text[i++]);
            MainHandler.percentage_decreament=Integer.parseInt(text[i++]);
            MainHandler.playerDetails= new ArrayList<Player_details>(MainHandler.number_of_players);
            for(int j=0;j<MainHandler.number_of_players;j++)
            {
                MainHandler.playerDetails.add(new Player_details(("player"+(j+1)),0));
            }
            System.out.println("the value of the i is     "+count+" "+everything_filled);

        }

        if(everything_filled) {
            Intent intent = new Intent(this, ActualGamePlay.class);

            startActivity(intent);
            finish();
        }
    }
}
