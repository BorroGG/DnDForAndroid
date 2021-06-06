package com.example.dndproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dndproject.db.CursachDatabase;
import com.example.dndproject.db.entities.items.Items;
import com.example.dndproject.db.entities.items.WeaponItemsEntity;

import java.util.ArrayList;
import java.util.List;

public class CurrentItemActivity extends AppCompatActivity {

    Items currentItem;
    List<WeaponItemsEntity> currentWeapons;

    TextView tvCurrentItemsId, tvWeapons, tvCurrentItemsTitle;
    Button buttEditItems, buttDeleteItems;
    LinearLayout layoutWeaponsForData;

    List<EditText[]> itemsEditText;
    List<TextView[]> itemsTextView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_current_item);

        Bundle arguments = getIntent().getExtras();

        currentItem = (Items) arguments.get("currentItem");

        tvWeapons = findViewById(R.id.tvWeapons);

        layoutWeaponsForData = findViewById(R.id.layoutWeaponsForData);

        setWeapons();

        tvCurrentItemsId = findViewById(R.id.tvCurrentItemsId);
        tvCurrentItemsTitle = findViewById(R.id.tvCurrentItemsTitle);
        tvCurrentItemsId.setText(currentItem.id + "");
        tvCurrentItemsTitle.setText(currentItem.name);

        buttEditItems = findViewById(R.id.buttEditItems);
        buttEditItems.setOnClickListener(v -> {
            // TODO edit
        });

        buttDeleteItems = findViewById(R.id.buttDeleteItems);
        buttDeleteItems.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).itemsDao().delete(currentItem);
            CursachDatabase.getInstance(getApplicationContext()).weaponItemsMMDao().delete(currentItem.id);
            Intent intent = new Intent(CurrentItemActivity.this, ItemsActivity.class);
            startActivity(intent);
        }).start());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setWeapons() {
        try {
            getWeaponsById(currentItem.id).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layoutWeaponsForData.removeAllViews();
        if (!currentWeapons.isEmpty()) {
            String[] weaponSupportData = new String[7];
            weaponSupportData[0] = "Название: ";
            weaponSupportData[1] = "Описание: ";
            weaponSupportData[2] = "Тип: ";
            weaponSupportData[3] = "Цена: ";
            weaponSupportData[4] = "Вес: ";
            weaponSupportData[5] = "Свойства: ";
            weaponSupportData[6] = "Урон: ";

            List<String[]> weaponsData = new ArrayList<>();
            for (int i = 0; i < currentWeapons.size(); i++) {
                weaponsData.add(new String[]{currentWeapons.get(i).weapon_name + "",
                        currentWeapons.get(i).weapon_description, currentWeapons.get(i).weapon_type,
                        currentWeapons.get(i).weapon_price, currentWeapons.get(i).weapon_weight,
                        currentWeapons.get(i).weapon_properties, currentWeapons.get(i).weapon_damage});
            }

            itemsEditText = new ArrayList<>();
            itemsTextView = new ArrayList<>();
            for (int i = 0; i < weaponsData.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Id: " + currentWeapons.get(i).weapon_id);
                textView.setTextColor(getResources().getColor(R.color.white));
                layoutWeaponsForData.addView(textView, getLayoutParams());
                itemsEditText.add(new EditText[weaponsData.get(i).length]);
                itemsTextView.add(new TextView[weaponsData.get(i).length]);
                for (int j = 0; j < weaponsData.get(i).length; j++) {
                    itemsEditText.get(i)[j] = new EditText(getApplicationContext());
                    itemsTextView.get(i)[j] = new TextView(getApplicationContext());

                    itemsEditText.get(i)[j].setText(weaponsData.get(i)[j]);
                    itemsTextView.get(i)[j].setText(weaponSupportData[j]);

                    itemsEditText.get(i)[j].setTextColor(getResources().getColor(R.color.white));
                    itemsTextView.get(i)[j].setTextColor(getResources().getColor(R.color.white));

                    itemsEditText.get(i)[j].setEnabled(false);

                    layoutWeaponsForData.addView(itemsTextView.get(i)[j], getLayoutParams());
                    layoutWeaponsForData.addView(itemsEditText.get(i)[j], getLayoutParams());
                }
                View view = new View(getApplicationContext());
                view.setBackgroundColor(getResources().getColor(R.color.white));
                view.setMinimumHeight(2);
                layoutWeaponsForData.addView(view, getLayoutParams());
            }
        } else {
            tvWeapons.setVisibility(View.GONE);
        }
    }

    private LinearLayout.LayoutParams getLayoutParams() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textViewParams.topMargin = dpInPx20;
        textViewParams.leftMargin = dpInPx20;
        return textViewParams;
    }

    private synchronized Thread getWeaponsById(int id) {
        Thread thread = new Thread(() -> currentWeapons = CursachDatabase.getInstance(getApplicationContext()).itemsDao().getWeaponItemsEntity(id));
        thread.start();
        return thread;
    }
}