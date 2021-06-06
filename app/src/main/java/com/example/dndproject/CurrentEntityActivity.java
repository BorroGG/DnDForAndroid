package com.example.dndproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dndproject.db.CursachDatabase;
import com.example.dndproject.db.entities.Entity;

public class CurrentEntityActivity extends AppCompatActivity {

    TextView tvCurrentEntityId, tvCurrentEntityName;
    Button editEntity, deleteEntity;
    Entity currentEntity;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentEntityEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_entity);

        Bundle arguments = getIntent().getExtras();

        currentEntity = (Entity) arguments.get("currentEntity");

        textLayouts[0] = findViewById(R.id.lineText1);
        textLayouts[1] = findViewById(R.id.lineText2);
        textLayouts[2] = findViewById(R.id.lineText3);
        textLayouts[3] = findViewById(R.id.lineText4);
        textLayouts[4] = findViewById(R.id.lineText5);
        textLayouts[5] = findViewById(R.id.lineText6);
        textLayouts[6] = findViewById(R.id.lineText7);
        textLayouts[7] = findViewById(R.id.lineText8);
        textLayouts[8] = findViewById(R.id.lineText9);
        textLayouts[9] = findViewById(R.id.lineText10);
        textLayouts[10] = findViewById(R.id.lineText11);
        textLayouts[11] = findViewById(R.id.lineText12);
        textLayouts[12] = findViewById(R.id.lineText13);
        textLayouts[13] = findViewById(R.id.lineText14);
        textLayouts[14] = findViewById(R.id.lineText15);
        textLayouts[15] = findViewById(R.id.lineText16);
        textLayouts[16] = findViewById(R.id.lineText17);
        textLayouts[17] = findViewById(R.id.lineText18);

        editEntity = findViewById(R.id.buttEditEntity);
        relativeLayoutCurrentEntityEditTexts = findViewById(R.id.layoutEntityForData);
        deleteEntity = findViewById(R.id.buttDeleteEntity);

        tvCurrentEntityId = findViewById(R.id.tvCurrentEntityId);
        tvCurrentEntityId.setText(currentEntity.id + "");
        tvCurrentEntityName = findViewById(R.id.tvCurrentEntityTitle);
        tvCurrentEntityName.setText(currentEntity.name);

        editEntity.setOnClickListener(v -> {
            //TODO
        });
        deleteEntity.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).entityDao().delete(currentEntity);
            Intent intent = new Intent(CurrentEntityActivity.this, BestiaryActivity.class);
            startActivity(intent);
        }).start());

        setEntityDataText();



    }

    private void setEntityDataText() {
        String[] spellData = new String[18];
        spellData[0] = String.valueOf(currentEntity.id);
        spellData[1] = currentEntity.name;
        spellData[2] = currentEntity.hp;
        spellData[3] = currentEntity.description;
        spellData[4] = currentEntity.speed_id + "";
        spellData[5] = currentEntity.stats_id + "";
        spellData[6] = currentEntity.armor_id + "";
        spellData[7] = currentEntity.skills_id + "";
        spellData[8] = currentEntity.fillings_id + "";
        spellData[9] = currentEntity.actions_id + "";
        spellData[10] = currentEntity.languages_id + "";
        spellData[11] = currentEntity.abilities_id + "";
        spellData[12] = currentEntity.resistances_id + "";
        spellData[13] = currentEntity.hits;
        spellData[14] = currentEntity.danger;
        spellData[15] = currentEntity.exp;
        spellData[16] = currentEntity.items_id + "";
        spellData[17] = currentEntity.riches_id + "";

        String[] spellSupportData = new String[18];
        spellSupportData[0] = "ID: ";
        spellSupportData[1] = "Название: ";
        spellSupportData[2] = "HP: ";
        spellSupportData[3] = "Описание: ";
        spellSupportData[4] = "speed ID: ";
        spellSupportData[5] = "stats ID: ";
        spellSupportData[6] = "armor ID: ";
        spellSupportData[7] = "skills ID: ";
        spellSupportData[8] = "fillings ID: ";
        spellSupportData[9] = "actions ID: ";
        spellSupportData[10] = "languages ID: ";
        spellSupportData[11] = "abilities ID: ";
        spellSupportData[12] = "resistances ID: ";
        spellSupportData[13] = "Хиты: ";
        spellSupportData[14] = "Опасность: ";
        spellSupportData[15] = "Опыт: ";
        spellSupportData[16] = "items ID: ";
        spellSupportData[17] = "riches ID: ";

        editTexts = new EditText[18];
        textViews = new TextView[18];
        for (int i = 0; i < editTexts.length; i++) {
            textLayouts[i].removeAllViews();

            editTexts[i] = new EditText(getApplicationContext());
            textViews[i] = new TextView(getApplicationContext());

            editTexts[i].setText(spellData[i]);
            textViews[i].setText(spellSupportData[i]);

            editTexts[i].setTextColor(getResources().getColor(R.color.white));
            textViews[i].setTextColor(getResources().getColor(R.color.white));

            editTexts[i].setTextSize(20);
            textViews[i].setTextSize(20);

            editTexts[i].setEnabled(false);


            DisplayMetrics dm = getResources().getDisplayMetrics();
            int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textViewParams.topMargin = dpInPx20;
            textViewParams.leftMargin = dpInPx20;
            textLayouts[i].addView(textViews[i], textViewParams);
            textLayouts[i].addView(editTexts[i], textViewParams);
        }
    }
}