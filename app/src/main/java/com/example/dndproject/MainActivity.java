package com.example.dndproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttBestiary, buttSpells, buttItems, buttRiches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttBestiary = findViewById(R.id.buttBestiary);
        buttSpells = findViewById(R.id.buttSpells);
        buttItems = findViewById(R.id.buttItems);
        buttRiches = findViewById(R.id.buttRiches);

        buttBestiary.setOnClickListener(this);
        buttSpells.setOnClickListener(this);
        buttItems.setOnClickListener(this);
        buttRiches.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttBestiary:
                Intent bestiaryIntent = new Intent(MainActivity.this, BestiaryActivity.class);
                startActivity(bestiaryIntent);
                break;
            case R.id.buttSpells:
                Intent spellsIntent = new Intent(MainActivity.this, SpellsActivity.class);
                startActivity(spellsIntent);
                break;
            case R.id.buttItems:
                Intent itemsIntent = new Intent(MainActivity.this, ItemsActivity.class);
                startActivity(itemsIntent);
                break;
            case R.id.buttRiches:
                Intent richesIntent = new Intent(MainActivity.this, RichesActivity.class);
                startActivity(richesIntent);
                break;
        }
    }
}