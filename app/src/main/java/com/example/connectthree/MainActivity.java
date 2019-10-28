package com.example.connectthree;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] track={0,0,0,0,0,0,0,0,0};
    int[][] winners={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int status=1;
    boolean gamestatus=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void intro(View view)
    {
        ImageView current=(ImageView) view;

        int tag=Integer.parseInt(current.getTag().toString());

        if(track[tag]==0 && gamestatus==true)
        {
            track[tag]=status;
            current.setTranslationY(-1500);

            if(status==1)
            {
                current.setImageResource(R.drawable.apple);
                status=2;
            }

            else
            { current.setImageResource(R.drawable.windows);
                status=1;

            }

            current.animate().translationYBy(1500).setDuration(150);
        }


        for(int[] winner:winners) {
            if (track[winner[0]] == track[winner[1]] && (track[winner[1]] == track[winner[2]] && (track[winner[0]] != 0))) {
                gamestatus = false;
                String win = "";
                if (status == 1) {
                    win = "Windows has Won";
                } else {
                    win = "Apple has Won";
                }
                TextView text = (TextView) findViewById(R.id.textView);
                Button press = (Button) findViewById(R.id.button);

                text.setText(win);
                text.setVisibility(View.VISIBLE);
                press.setVisibility(View.VISIBLE);

                MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.clip);
                mediaplayer.start();

            }
        }

        boolean tie=true;

            for(int i=0;i<track.length;i++)
            {
                if(track[i]==0)
                {
                    tie=false;
                }

            }

            if(tie==true)
            {
                TextView text = (TextView) findViewById(R.id.textView);
                Button press = (Button) findViewById(R.id.button);

                text.setText("Game Tied!");
                text.setVisibility(View.VISIBLE);
                press.setVisibility(View.VISIBLE);
            }

    }


    public void playAgain(View view)
    {
        TextView text=(TextView)findViewById(R.id.textView);
        Button press=(Button) findViewById(R.id.button);
        android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView counter=(ImageView)  grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        text.setVisibility(View.INVISIBLE);
        press.setVisibility(View.INVISIBLE);

        for(int i=0;i<track.length;i++)
        {
            track[i]=0;
        }

        status=1;
        gamestatus=true;
    }





}
