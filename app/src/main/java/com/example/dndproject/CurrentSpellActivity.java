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
import com.example.dndproject.db.entities.Spell;

public class CurrentSpellActivity extends AppCompatActivity {

    TextView tvCurrentSpellId, tvCurrentSpellName;
    Button editSpell, deleteSpell;
    Spell currentSpell;
    EditText[] editTexts;
    TextView[] textViews;
    RelativeLayout relativeLayoutCurrentSpellEditTexts;
    LinearLayout[] textLayouts = new LinearLayout[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_spell);

        Bundle arguments = getIntent().getExtras();

        currentSpell = (Spell) arguments.get("currentSpell");

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

        editSpell = findViewById(R.id.buttEditSpell);
        relativeLayoutCurrentSpellEditTexts = findViewById(R.id.layoutSpellForData);
        deleteSpell = findViewById(R.id.buttDeleteSpell);

        tvCurrentSpellId = findViewById(R.id.tvCurrentSpellId);
        tvCurrentSpellId.setText(currentSpell.id_spell + "");
        tvCurrentSpellName = findViewById(R.id.tvCurrentSpellName);
        tvCurrentSpellName.setText(currentSpell.name_spell);

        editSpell.setOnClickListener(v -> {
            if (editSpell.getText().toString().equals("Редактировать")) {
                editSpell.setText("Принять");
                for (int i = 0; i < editTexts.length; i++) {
                    if (i != 0 ) {
                        editTexts[i].setEnabled(true);
                    }
                }
            } else {
                Thread thread = new Thread(() -> {
                    Spell spell = new Spell();
                    spell.id_spell = Integer.parseInt(editTexts[0].getText().toString());
                    spell.lvl = editTexts[1].getText().toString();
                    spell.components = editTexts[2].getText().toString();
                    spell.source = editTexts[3].getText().toString();
                    spell.name_spell = editTexts[4].getText().toString();
                    spell.description_spell = editTexts[5].getText().toString();
                    spell.school = editTexts[6].getText().toString();
                    spell.time_spell = editTexts[7].getText().toString();
                    spell.type_duration = editTexts[8].getText().toString();
                    spell.type_distance = editTexts[9].getText().toString();
                    spell.classes = editTexts[10].getText().toString();

                    currentSpell = spell;

                    CursachDatabase.getInstance(getApplicationContext()).spellDao().update(spell);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setSpellDataText();
                editSpell.setText("Редактировать");
            }

        });
        deleteSpell.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).spellDao().delete(currentSpell);
            Intent intent = new Intent(CurrentSpellActivity.this, SpellsActivity.class);
            startActivity(intent);
        }).start());

        setSpellDataText();

    }

    private void setSpellDataText() {
        String[] spellData = new String[11];
        spellData[0] = String.valueOf(currentSpell.id_spell);
        spellData[1] = currentSpell.lvl;
        spellData[2] = currentSpell.components;
        spellData[3] = currentSpell.source;
        spellData[4] = currentSpell.name_spell;
        spellData[5] = currentSpell.description_spell;
        spellData[6] = currentSpell.school;
        spellData[7] = currentSpell.time_spell;
        spellData[8] = currentSpell.type_duration;
        spellData[9] = currentSpell.type_distance;
        spellData[10] = currentSpell.classes;

        String[] spellSupportData = new String[11];
        spellSupportData[0] = "ID: ";
        spellSupportData[1] = "Уровень: ";
        spellSupportData[2] = "Компоненты: ";
        spellSupportData[3] = "Источник: ";
        spellSupportData[4] = "Название заклинания: ";
        spellSupportData[5] = "Описание заклинания: ";
        spellSupportData[6] = "Школа: ";
        spellSupportData[7] = "Длительность: ";
        spellSupportData[8] = "Время накладывания: ";
        spellSupportData[9] = "Дистанция: ";
        spellSupportData[10] = "Классы: ";

        editTexts = new EditText[11];
        textViews = new TextView[11];
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