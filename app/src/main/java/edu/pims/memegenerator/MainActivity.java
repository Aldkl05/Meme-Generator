package edu.pims.memegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity  {

    ImageView meme1, meme2, meme3, meme4, meme5, meme6, meme7, meme8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        meme1 = findViewById(R.id.meme_one);
        meme2 = findViewById(R.id.meme_two);
        meme3 = findViewById(R.id.meme_three);
        meme4 = findViewById(R.id.meme_four);
        meme5 =  findViewById(R.id.meme_five);
        meme6 = findViewById(R.id.meme_six);
        meme7 = findViewById(R.id.meme_seven);
        meme8 = findViewById(R.id.meme_eight);

        meme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(1);
            }
        });

        meme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(2);
            }
        });

        meme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(3);
            }
        });


        meme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(4);
            }
        });




        meme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(5);
            }
        });



        meme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(6);
            }
        });

        meme7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(7);
            }
        });


        meme8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGenerateMEMEScreen(8);
            }
        });





    }

   void goToGenerateMEMEScreen(int name){
        Intent intent = new Intent(MainActivity.this, MemeGenerator.class);
        intent.putExtra("meme", name);
        startActivity(intent);

   }
}