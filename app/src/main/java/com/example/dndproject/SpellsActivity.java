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
import com.example.dndproject.db.entities.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellsActivity extends AppCompatActivity {

    List<Spell> spellList = new ArrayList<>();
    Button[] buttonsSpells;
    Button buttAddSpells;
    private RelativeLayout layoutSpellForData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spells);

        buttAddSpells = findViewById(R.id.buttAddSpell);
        buttAddSpells.setOnClickListener(v -> {
            Intent intent = new Intent(SpellsActivity.this, AddSpellActivity.class);
            startActivity(intent);
        });

        layoutSpellForData = findViewById(R.id.layoutSpellForData);
    }

    @SuppressLint("ResourceType")
    private void setButtonsData() {

        layoutSpellForData.removeAllViews();

        try {
            getAllSpells().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonsSpells = new Button[spellList.size()];
        for (int i = 0; i < spellList.size(); i++) {
            int buttonStyle = R.drawable.button;
            buttonsSpells[i] = new Button(getApplicationContext());
            String text = spellList.get(i).id_spell + " " + spellList.get(i).name_spell;
            buttonsSpells[i].setText(text);
            buttonsSpells[i].setId(View.generateViewId());
            buttonsSpells[i].setBackgroundResource(buttonStyle);
            buttonsSpells[i].setTextColor(getResources().getColor(R.color.white));
            int finalI = i;
            buttonsSpells[i].setOnClickListener(v -> {
                Intent intent = new Intent(SpellsActivity.this, CurrentSpellActivity.class);
                intent.putExtra("currentSpell", spellList.get(finalI));
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
                buttonParams.addRule(RelativeLayout.BELOW, buttonsSpells[i - 1].getId());
            } else {
                buttonParams.addRule(RelativeLayout.BELOW, buttAddSpells.getId());
            }

            layoutSpellForData.addView(buttonsSpells[i], buttonParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsData();
    }

    private synchronized Thread getAllSpells() {
        Thread thread = new Thread(() -> spellList = CursachDatabase.getInstance(getApplicationContext()).spellDao().getAll());
        thread.start();
        return thread;
    }

}