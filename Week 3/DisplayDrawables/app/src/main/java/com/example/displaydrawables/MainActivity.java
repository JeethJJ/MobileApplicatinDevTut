package com.example.displaydrawables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
//        String resource = "brittany_02625";
//        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.displaydrawables");
        ImageView v1 = findViewById(R.id.im_view);
        v1.setImageResource(R.drawable.brittany_02625);
//        String resource = "welsh_springer_spaniel_08203";
//        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.displaydrawables");
//        v1.setImageResource(resource_id);

    }


    public void onclickMtd(View view) {
        String resource = "welsh_springer_spaniel_08203";
        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.displaydrawables");
        ImageView v1 = findViewById(R.id.im_view);
        v1.setImageResource(resource_id);
    }
}

