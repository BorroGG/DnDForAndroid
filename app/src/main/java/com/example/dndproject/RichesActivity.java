package com.example.dndproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dndproject.db.CursachDatabase;
import com.example.dndproject.db.entities.riches.Riches;

import java.util.ArrayList;
import java.util.List;

public class RichesActivity extends AppCompatActivity {

    List<Riches> richesList = new ArrayList<>();
    Button[] buttonsRiches;
    Button buttAddRiches;
    private RelativeLayout layoutRichesForData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_riches);

        buttAddRiches = findViewById(R.id.buttAddRiches);
        buttAddRiches.setOnClickListener(v -> {
            Intent intent = new Intent(RichesActivity.this, AddRichesActivity.class);
            startActivity(intent);
        });

        layoutRichesForData = findViewById(R.id.layoutRichesForData);
    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        layoutRichesForData.removeAllViews();

        try {
            getAllVRiches().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonsRiches = new Button[richesList.size()];
        for (int i = 0; i < richesList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttonsRiches[i] = new Button(getApplicationContext());
            String text = richesList.get(i).id_riches + " " + richesList.get(i).title;
            buttonsRiches[i].setText(text);
            buttonsRiches[i].setId(View.generateViewId());
            buttonsRiches[i].setBackgroundResource(buttonStyle);
            buttonsRiches[i].setTextColor(getResources().getColor(R.color.white));
            int finalI = i;
            buttonsRiches[i].setOnClickListener(v -> {
                Intent intent = new Intent(RichesActivity.this, CurrentRichesActivity.class);
                intent.putExtra("currentRiches", richesList.get(finalI));
                startActivity(intent);
            });

            DisplayMetrics dm = getResources().getDisplayMetrics();
            //int dpInPx300 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, dm);
            int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);

            RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonParams.topMargin = dpInPx20;

            if (i != 0) {
                buttonParams.addRule(RelativeLayout.BELOW, buttonsRiches[i - 1].getId());
            } else {
                buttonParams.addRule(RelativeLayout.BELOW, buttAddRiches.getId());
            }

            layoutRichesForData.addView(buttonsRiches[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllVRiches() {
        Thread thread = new Thread(() -> richesList = CursachDatabase.getInstance(getApplicationContext()).richesDao().getAll());
        thread.start();
        return thread;
    }

}