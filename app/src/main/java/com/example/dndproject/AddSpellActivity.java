package com.example.dndproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.dndproject.db.CursachDatabase;
import com.example.dndproject.db.entities.Spell;

public class AddSpellActivity extends AppCompatActivity {

    EditText etLvl, etComponents, etSource, etName_spell, etDescription_spell, etSchool, etTime_spell, etType_duration, etType_distance, etClasses;
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spell_acticity);

        etLvl = findViewById(R.id.lvl);
        etComponents = findViewById(R.id.components);
        etSource = findViewById(R.id.source);
        etName_spell = findViewById(R.id.name_spell);
        etDescription_spell = findViewById(R.id.description_spell);
        etSchool = findViewById(R.id.school);
        etTime_spell = findViewById(R.id.time_spell);
        etType_duration = findViewById(R.id.type_duration);
        etType_distance = findViewById(R.id.type_distance);
        etClasses = findViewById(R.id.classes);

        btnAccept = findViewById(R.id.acceptSpell);

        btnAccept.setOnClickListener(v -> {
            insertSpell();
            Intent intent = new Intent(AddSpellActivity.this, SpellsActivity.class);
            startActivity(intent);
        });

    }

    private void insertSpell() {
        new Thread(() -> {
            Spell spell = new Spell();
            spell.lvl = etLvl.getText().toString();
            spell.components = etComponents.getText().toString();
            spell.source = etSource.getText().toString();
            spell.name_spell = etName_spell.getText().toString();
            spell.description_spell = etDescription_spell.getText().toString();
            spell.school = etSchool.getText().toString();
            spell.time_spell = etTime_spell.getText().toString();
            spell.type_duration = etType_duration.getText().toString();
            spell.type_distance = etType_distance.getText().toString();
            spell.classes = etClasses.getText().toString();

            CursachDatabase.getInstance(getApplicationContext()).spellDao().insertAll(spell);
        }).start();
    }
}
