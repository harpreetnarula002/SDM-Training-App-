package com.narula.har.sunshine;

import java.util.ArrayList;

/**
 * Created by Har on 21/04/2015.
 */
public class MainHandler
{
    public static ArrayList<Player_details> playerDetails;
    public static int number_of_players;
    public static int no_of_players_currently_active;
    public static int total_laps;
    public static int current_lap;
    public static int distance;
    public static int percentage_decreament;
    public static int time;
    public static void  show()
    {
        System.out.println("no of Players total "+number_of_players);
        System.out.println("No. of Players currently active :"+no_of_players_currently_active);
        System.out.println("Current lap"+current_lap);
        System.out.println("distance"+distance);
        System.out.println("percetage dec "+percentage_decreament);
        System.out.println("Time "+time);

        for(Player_details player:playerDetails)
            System.out.println(player.name+"        "+player.laps+"     "+player.playing);
        System.out.println("////////////////////////////////////////////////////////////////////");
    }


}
