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
import com.example.dndproject.db.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class BestiaryActivity extends AppCompatActivity {

    List<Entity> entityList = new ArrayList<>();
    Button[] buttonsEntities;
    Button buttAddEntity;
    private RelativeLayout layoutEntitiesForData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bestiary);

        buttAddEntity = findViewById(R.id.buttAddBestiary);
        buttAddEntity.setOnClickListener(v -> {
            /*Intent intent = new Intent(BestiaryActivity.this, AddItemActivity.class);
            startActivity(intent);*/
        });

        layoutEntitiesForData = findViewById(R.id.layoutBestiaryForData);
    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        layoutEntitiesForData.removeAllViews();

        try {
            getAllEntities().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonsEntities = new Button[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttonsEntities[i] = new Button(getApplicationContext());
            String text = entityList.get(i).id + " " + entityList.get(i).name;
            buttonsEntities[i].setText(text);
            buttonsEntities[i].setId(View.generateViewId());
            buttonsEntities[i].setBackgroundResource(buttonStyle);
            buttonsEntities[i].setTextColor(getResources().getColor(R.color.white));
            int finalI = i;
            buttonsEntities[i].setOnClickListener(v -> {
                Intent intent = new Intent(BestiaryActivity.this, CurrentEntityActivity.class);
                intent.putExtra("currentEntity", entityList.get(finalI));
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
                buttonParams.addRule(RelativeLayout.BELOW, buttonsEntities[i - 1].getId());
            } else {
                buttonParams.addRule(RelativeLayout.BELOW, buttAddEntity.getId());
            }

            layoutEntitiesForData.addView(buttonsEntities[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllEntities() {
        Thread thread = new Thread(() -> entityList = CursachDatabase.getInstance(getApplicationContext()).entityDao().getAll());
        thread.start();
        return thread;
    }

}