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
import com.example.dndproject.db.entities.items.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    List<Items> itemsList = new ArrayList<>();
    Button[] buttonsItems;
    Button buttAddItems;
    private RelativeLayout layoutItemsForData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_items);

        buttAddItems = findViewById(R.id.buttAddItems);
        buttAddItems.setOnClickListener(v -> {
            /*Intent intent = new Intent(ItemsActivity.this, AddItemActivity.class);
            startActivity(intent);*/
        });

        layoutItemsForData = findViewById(R.id.layoutItemsForData);
    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        layoutItemsForData.removeAllViews();

        try {
            getAllItems().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonsItems = new Button[itemsList.size()];
        for (int i = 0; i < itemsList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttonsItems[i] = new Button(getApplicationContext());
            String text = itemsList.get(i).id + " " + itemsList.get(i).name;
            buttonsItems[i].setText(text);
            buttonsItems[i].setId(View.generateViewId());
            buttonsItems[i].setBackgroundResource(buttonStyle);
            buttonsItems[i].setTextColor(getResources().getColor(R.color.white));
            int finalI = i;
            buttonsItems[i].setOnClickListener(v -> {
                Intent intent = new Intent(ItemsActivity.this, CurrentItemActivity.class);
                intent.putExtra("currentItem", itemsList.get(finalI));
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
                buttonParams.addRule(RelativeLayout.BELOW, buttonsItems[i - 1].getId());
            } else {
                buttonParams.addRule(RelativeLayout.BELOW, buttAddItems.getId());
            }

            layoutItemsForData.addView(buttonsItems[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllItems() {
        Thread thread = new Thread(() -> itemsList = CursachDatabase.getInstance(getApplicationContext()).itemsDao().getAll());
        thread.start();
        return thread;
    }

}