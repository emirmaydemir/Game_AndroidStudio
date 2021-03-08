package com.example.kennyorjinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
     TextView textView;
     TextView textView2;
     int score;
     ImageView imageView1;
     ImageView imageView2;
     ImageView imageView3;
     ImageView imageView4;
     ImageView imageView5;
     ImageView imageView6;
     ImageView imageView7;
     ImageView imageView8;
     ImageView imageView9;
     ImageView imageView;
     ImageView[] array;
     Handler handler;
     Runnable runnable;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score=0;
        textView=findViewById(R.id.score);
        textView2=findViewById(R.id.Time);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        array= new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();


        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                textView2.setText("Time "+l/1000);
            }

            @Override
            public void onFinish() {
              textView.setText("Time Off ");
              handler.removeCallbacks(runnable);
                for(ImageView image:array){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Continue");
                alert.setMessage("You are sure next game");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                              startActivity(intent);
                            }
                        });
                      alert.show();
            }
        }.start();
    }


     public void hideImages(){
        handler= new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for(ImageView image:array){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int j=random.nextInt(9);
                array[j].setVisibility(View.VISIBLE);
                imageView=array[j];
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score++;
                        textView.setText("Score: "+score);
                        imageView.setVisibility(View.INVISIBLE);
                    }
                });

                handler.postDelayed(this,400);


            }
        };
        handler.post(runnable);


     }



}