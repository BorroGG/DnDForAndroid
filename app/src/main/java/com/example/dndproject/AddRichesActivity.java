package com.example.dndproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dndproject.db.CursachDatabase;
import com.example.dndproject.db.entities.riches.Artworks;
import com.example.dndproject.db.entities.riches.ArtworksRichesMM;
import com.example.dndproject.db.entities.riches.Coins;
import com.example.dndproject.db.entities.riches.CoinsRichesMM;
import com.example.dndproject.db.entities.riches.Precious_stones;
import com.example.dndproject.db.entities.riches.Precious_stonesRichesMM;
import com.example.dndproject.db.entities.riches.Riches;
import com.example.dndproject.db.entities.riches.Trinkets;
import com.example.dndproject.db.entities.riches.TrinketsRichesMM;

import java.util.ArrayList;
import java.util.List;

public class AddRichesActivity extends AppCompatActivity {

    TextView tvCurrentRiches;
    EditText edTitle;
    Spinner trinketsSpinner, artworksSpinner, coinsSpinner, precious_stonesSpinner;
    Button addTrinkets, addNewTrinkets, createNewRiches, addArtworks, addNewArtworks, addCoins, addNewCoins, addPrecious_stones, addNewPrecious_stones;
    LinearLayout lineCurrentTrinkets, lineCurrentArtworks, lineCurrentCoins, lineCurrentPrecious_stones;
    int trinketId, tmpTrinket;
    int artworksId, tmpArtworks;
    int coinsId, tmpCoins;
    int precious_stonesId, tmpPrecious_stones;
    List<Trinkets> chooseTrinkets = new ArrayList<>();
    List<Artworks> chooseArtworks = new ArrayList<>();
    List<Coins> chooseCoins = new ArrayList<>();
    List<Precious_stones> choosePrecious_stones = new ArrayList<>();
    RelativeLayout layoutTmp, layoutForCreateRiches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_riches);

        edTitle = findViewById(R.id.edTitle);
        tvCurrentRiches = findViewById(R.id.tvCurrentRiches);
        createNewRiches = findViewById(R.id.createNewRiches);
        trinketsSpinner = findViewById(R.id.trinketsSpinner);
        artworksSpinner = findViewById(R.id.artworksSpinner);
        precious_stonesSpinner = findViewById(R.id.precious_stonesSpinner);
        coinsSpinner = findViewById(R.id.coinsSpinner);
        addTrinkets = findViewById(R.id.addTrinkets);
        addArtworks = findViewById(R.id.addArtworks);
        addCoins = findViewById(R.id.addCoins);
        addPrecious_stones = findViewById(R.id.addPrecious_stones);
        addNewTrinkets = findViewById(R.id.addNewTrinkets);
        addNewArtworks = findViewById(R.id.addNewArtworks);
        addNewCoins = findViewById(R.id.addNewCoins);
        addNewPrecious_stones = findViewById(R.id.addNewPrecious_stones);
        lineCurrentTrinkets = findViewById(R.id.lineCurrentTrinkets);
        lineCurrentArtworks = findViewById(R.id.lineCurrentArtworks);
        lineCurrentCoins = findViewById(R.id.lineCurrentCoins);
        lineCurrentPrecious_stones = findViewById(R.id.lineCurrentPrecious_stones);
        layoutTmp = findViewById(R.id.layoutTmp);
        layoutForCreateRiches = findViewById(R.id.layoutForCreateRiches);

        addNewTrinkets.setOnClickListener(v -> {
            layoutForCreateRiches.setVisibility(View.GONE);
            layoutTmp.removeAllViews();
            layoutTmp.setVisibility(View.VISIBLE);
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Создание безделушки";
            textViewName.setText(tmpName);
            int textViewId = View.generateViewId();
            textViewName.setId(textViewId);
            textViewName.setTextSize(16);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            layoutTmp.addView(textViewName, getRelativeLayoutParams(tvCurrentRiches.getId()));

            EditText editText1 = new EditText(getApplicationContext());
            String hint1 = "Число костей";
            editText1.setTextColor(getResources().getColor(R.color.white));
            editText1.setHintTextColor(getResources().getColor(R.color.grey));
            editText1.setHint(hint1);
            int editText1ViewId = View.generateViewId();
            editText1.setId(editText1ViewId);
            editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            layoutTmp.addView(editText1, getRelativeLayoutParams(textViewId));

            EditText editText2 = new EditText(getApplicationContext());
            String hint2 = "Название";
            editText2.setTextColor(getResources().getColor(R.color.white));
            editText2.setHintTextColor(getResources().getColor(R.color.grey));
            editText2.setHint(hint2);
            int editText2ViewId = View.generateViewId();
            editText2.setId(editText2ViewId);
            layoutTmp.addView(editText2, getRelativeLayoutParams(editText1ViewId));

            Button button1 = new Button(getApplicationContext());
            int buttonStyle = R.drawable.button;
            String text = "Создать и добавить";
            button1.setText(text);
            button1.setId(View.generateViewId());
            button1.setBackgroundResource(buttonStyle);
            button1.setTextColor(getResources().getColor(R.color.white));
            button1.setOnClickListener(v1 -> {
                Thread thread = new Thread(() -> {
                    Trinkets trinkets = new Trinkets();
                    trinkets.trinket_name = editText2.getText().toString();
                    trinkets.bone_number = Integer.parseInt(editText1.getText().toString());
                    tmpTrinket = trinketId;
                    trinketId = (int) CursachDatabase.getInstance(getApplicationContext()).trinketsDao().insertAll(trinkets)[0];
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addTrinkets.performClick();
                trinketId = tmpTrinket;
                layoutForCreateRiches.setVisibility(View.VISIBLE);
                layoutTmp.setVisibility(View.GONE);
            });
            layoutTmp.addView(button1, getRelativeLayoutParams(editText2ViewId));
        });

        addNewArtworks.setOnClickListener(v -> {
            layoutForCreateRiches.setVisibility(View.GONE);
            layoutTmp.removeAllViews();
            layoutTmp.setVisibility(View.VISIBLE);
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Создание произведения искусства";
            textViewName.setText(tmpName);
            int textViewId = View.generateViewId();
            textViewName.setId(textViewId);
            textViewName.setTextSize(16);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            layoutTmp.addView(textViewName, getRelativeLayoutParams(tvCurrentRiches.getId()));

            EditText editText1 = new EditText(getApplicationContext());
            String hint1 = "Число костей";
            editText1.setTextColor(getResources().getColor(R.color.white));
            editText1.setHintTextColor(getResources().getColor(R.color.grey));
            editText1.setHint(hint1);
            int editText1ViewId = View.generateViewId();
            editText1.setId(editText1ViewId);
            editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            layoutTmp.addView(editText1, getRelativeLayoutParams(textViewId));

            EditText editText2 = new EditText(getApplicationContext());
            String hint2 = "Название";
            editText2.setTextColor(getResources().getColor(R.color.white));
            editText2.setHintTextColor(getResources().getColor(R.color.grey));
            editText2.setHint(hint2);
            int editText2ViewId = View.generateViewId();
            editText2.setId(editText2ViewId);
            layoutTmp.addView(editText2, getRelativeLayoutParams(editText1ViewId));

            EditText editText3 = new EditText(getApplicationContext());
            String hint3 = "Цена";
            editText3.setTextColor(getResources().getColor(R.color.white));
            editText3.setHintTextColor(getResources().getColor(R.color.grey));
            editText3.setHint(hint3);
            int editText3ViewId = View.generateViewId();
            editText3.setId(editText3ViewId);
            editText3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            layoutTmp.addView(editText3, getRelativeLayoutParams(editText2ViewId));

            Button button1 = new Button(getApplicationContext());
            int buttonStyle = R.drawable.button;
            String text = "Создать и добавить";
            button1.setText(text);
            button1.setId(View.generateViewId());
            button1.setBackgroundResource(buttonStyle);
            button1.setTextColor(getResources().getColor(R.color.white));
            button1.setOnClickListener(v1 -> {
                Thread thread = new Thread(() -> {
                    Artworks artworks = new Artworks();
                    artworks.art_name = editText2.getText().toString();
                    artworks.bone_number = Integer.parseInt(editText1.getText().toString());
                    artworks.art_price = Integer.parseInt(editText3.getText().toString());
                    tmpArtworks = artworksId;
                    artworksId = (int) CursachDatabase.getInstance(getApplicationContext()).artworksDao().insertAll(artworks)[0];
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addArtworks.performClick();
                artworksId = tmpArtworks;
                layoutForCreateRiches.setVisibility(View.VISIBLE);
                layoutTmp.setVisibility(View.GONE);
            });
            layoutTmp.addView(button1, getRelativeLayoutParams(editText3ViewId));
        });

        addNewPrecious_stones.setOnClickListener(v -> {
            layoutForCreateRiches.setVisibility(View.GONE);
            layoutTmp.removeAllViews();
            layoutTmp.setVisibility(View.VISIBLE);
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Создание драгоценного камня";
            textViewName.setText(tmpName);
            int textViewId = View.generateViewId();
            textViewName.setId(textViewId);
            textViewName.setTextSize(16);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            layoutTmp.addView(textViewName, getRelativeLayoutParams(tvCurrentRiches.getId()));

            EditText editText1 = new EditText(getApplicationContext());
            String hint1 = "Число костей";
            editText1.setTextColor(getResources().getColor(R.color.white));
            editText1.setHintTextColor(getResources().getColor(R.color.grey));
            editText1.setHint(hint1);
            int editText1ViewId = View.generateViewId();
            editText1.setId(editText1ViewId);
            editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            layoutTmp.addView(editText1, getRelativeLayoutParams(textViewId));

            EditText editText2 = new EditText(getApplicationContext());
            String hint2 = "Название";
            editText2.setTextColor(getResources().getColor(R.color.white));
            editText2.setHintTextColor(getResources().getColor(R.color.grey));
            editText2.setHint(hint2);
            int editText2ViewId = View.generateViewId();
            editText2.setId(editText2ViewId);
            layoutTmp.addView(editText2, getRelativeLayoutParams(editText1ViewId));

            EditText editText3 = new EditText(getApplicationContext());
            String hint3 = "Описание";
            editText3.setTextColor(getResources().getColor(R.color.white));
            editText3.setHintTextColor(getResources().getColor(R.color.grey));
            editText3.setHint(hint3);
            int editText3ViewId = View.generateViewId();
            editText3.setId(editText3ViewId);
            layoutTmp.addView(editText3, getRelativeLayoutParams(editText2ViewId));

            EditText editText4 = new EditText(getApplicationContext());
            String hint4 = "Цена";
            editText4.setTextColor(getResources().getColor(R.color.white));
            editText4.setHintTextColor(getResources().getColor(R.color.grey));
            editText4.setHint(hint4);
            int editText4ViewId = View.generateViewId();
            editText4.setId(editText4ViewId);
            editText4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            layoutTmp.addView(editText4, getRelativeLayoutParams(editText3ViewId));

            Button button1 = new Button(getApplicationContext());
            int buttonStyle = R.drawable.button;
            String text = "Создать и добавить";
            button1.setText(text);
            button1.setId(View.generateViewId());
            button1.setBackgroundResource(buttonStyle);
            button1.setTextColor(getResources().getColor(R.color.white));
            button1.setOnClickListener(v1 -> {
                Thread thread = new Thread(() -> {
                    Precious_stones precious_stones = new Precious_stones();
                    precious_stones.stone_description = editText3.getText().toString();
                    precious_stones.stone_name = editText2.getText().toString();
                    precious_stones.bone_number = Integer.parseInt(editText1.getText().toString());
                    precious_stones.stone_price = Integer.parseInt(editText4.getText().toString());
                    tmpPrecious_stones = precious_stonesId;
                    precious_stonesId = (int) CursachDatabase.getInstance(getApplicationContext()).precious_stonesDao().insertAll(precious_stones)[0];
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addPrecious_stones.performClick();
                precious_stonesId = tmpPrecious_stones;
                layoutForCreateRiches.setVisibility(View.VISIBLE);
                layoutTmp.setVisibility(View.GONE);
            });
            layoutTmp.addView(button1, getRelativeLayoutParams(editText4ViewId));
        });

        addNewCoins.setOnClickListener(v -> {
            layoutForCreateRiches.setVisibility(View.GONE);
            layoutTmp.removeAllViews();
            layoutTmp.setVisibility(View.VISIBLE);
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Создание монет";
            textViewName.setText(tmpName);
            int textViewId = View.generateViewId();
            textViewName.setId(textViewId);
            textViewName.setTextSize(16);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            layoutTmp.addView(textViewName, getRelativeLayoutParams(tvCurrentRiches.getId()));

            EditText editText1 = new EditText(getApplicationContext());
            String hint1 = "Валюта";
            editText1.setTextColor(getResources().getColor(R.color.white));
            editText1.setHintTextColor(getResources().getColor(R.color.grey));
            editText1.setHint(hint1);
            int editText1ViewId = View.generateViewId();
            editText1.setId(editText1ViewId);
            layoutTmp.addView(editText1, getRelativeLayoutParams(textViewId));

            EditText editText2 = new EditText(getApplicationContext());
            String hint2 = "Опасность";
            editText2.setTextColor(getResources().getColor(R.color.white));
            editText2.setHintTextColor(getResources().getColor(R.color.grey));
            editText2.setHint(hint2);
            int editText2ViewId = View.generateViewId();
            editText2.setId(editText2ViewId);
            layoutTmp.addView(editText2, getRelativeLayoutParams(editText1ViewId));

            EditText editText3 = new EditText(getApplicationContext());
            String hint3 = "Число костей";
            editText3.setTextColor(getResources().getColor(R.color.white));
            editText3.setHintTextColor(getResources().getColor(R.color.grey));
            editText3.setHint(hint3);
            int editText3ViewId = View.generateViewId();
            editText3.setId(editText3ViewId);
            layoutTmp.addView(editText3, getRelativeLayoutParams(editText2ViewId));

            EditText editText4 = new EditText(getApplicationContext());
            String hint4 = "Количество монет";
            editText4.setTextColor(getResources().getColor(R.color.white));
            editText4.setHintTextColor(getResources().getColor(R.color.grey));
            editText4.setHint(hint4);
            int editText4ViewId = View.generateViewId();
            editText4.setId(editText4ViewId);
            layoutTmp.addView(editText4, getRelativeLayoutParams(editText3ViewId));


            Button button1 = new Button(getApplicationContext());
            int buttonStyle = R.drawable.button;
            String text = "Создать и добавить";
            button1.setText(text);
            button1.setId(View.generateViewId());
            button1.setBackgroundResource(buttonStyle);
            button1.setTextColor(getResources().getColor(R.color.white));
            button1.setOnClickListener(v1 -> {
                Thread thread = new Thread(() -> {
                    Coins coins = new Coins();
                    coins.currencie = editText1.getText().toString();
                    coins.hazard = editText2.getText().toString();
                    coins.bone_number = editText3.getText().toString();
                    coins.coins_amount = editText4.getText().toString();
                    tmpCoins = coinsId;
                    coinsId = (int) CursachDatabase.getInstance(getApplicationContext()).coinsDao().insertAll(coins)[0];
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addCoins.performClick();
                coinsId = tmpCoins;
                layoutForCreateRiches.setVisibility(View.VISIBLE);
                layoutTmp.setVisibility(View.GONE);
            });
            layoutTmp.addView(button1, getRelativeLayoutParams(editText4ViewId));
        });

        createTrinketsSpinner();
        createArtworksSpinner();
        createCoinsSpinner();
        createPrecious_stonesSpinner();

        createNewRiches.setOnClickListener(v -> new Thread(() -> {
            Riches riches = new Riches();
            riches.title = edTitle.getText().toString();
            int id_riches = (int) CursachDatabase.getInstance(getApplicationContext()).richesDao().insertAll(riches)[0];
            for (int i = 0; i < chooseTrinkets.size(); i++) {
                TrinketsRichesMM trinketsRichesMM = new TrinketsRichesMM();
                trinketsRichesMM.riches_id = id_riches;
                trinketsRichesMM.trinkets_id = chooseTrinkets.get(i).trinkets_id;
                CursachDatabase.getInstance(getApplicationContext()).trinketsRichesMMDao().insertAll(trinketsRichesMM);
            }
            for (int i = 0; i < chooseCoins.size(); i++) {
                CoinsRichesMM coinsRichesMM = new CoinsRichesMM();
                coinsRichesMM.riches_id = id_riches;
                coinsRichesMM.coins_id = chooseCoins.get(i).coins_id;
                CursachDatabase.getInstance(getApplicationContext()).coinsRichesMMDao().insertAll(coinsRichesMM);
            }
            for (int i = 0; i < chooseArtworks.size(); i++) {
                ArtworksRichesMM artworksRichesMM = new ArtworksRichesMM();
                artworksRichesMM.riches_id = id_riches;
                artworksRichesMM.artworks_id = chooseArtworks.get(i).artworks_id;
                CursachDatabase.getInstance(getApplicationContext()).artworksRichesMMDao().insertAll(artworksRichesMM);
            }
            for (int i = 0; i < choosePrecious_stones.size(); i++) {
                Precious_stonesRichesMM precious_stonesRichesMM = new Precious_stonesRichesMM();
                precious_stonesRichesMM.riches_id = id_riches;
                precious_stonesRichesMM.precious_stones_id = choosePrecious_stones.get(i).precious_stones_id;
                CursachDatabase.getInstance(getApplicationContext()).precious_stonesRichesMMDao().insertAll(precious_stonesRichesMM);
            }
            Intent intent = new Intent(AddRichesActivity.this, RichesActivity.class);
            startActivity(intent);
        }).start());

        addTrinkets.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Trinkets trinkets = CursachDatabase.getInstance(getApplicationContext()).trinketsDao().getById(trinketId);
                chooseTrinkets.add(trinkets);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lineCurrentTrinkets.removeAllViews();
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Безделушки:";
            textViewName.setText(tmpName);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            lineCurrentTrinkets.addView(textViewName, getLayoutParams());

            for (int i = 0; i < chooseTrinkets.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                String tmp = chooseTrinkets.get(i).trinkets_id + ", " + chooseTrinkets.get(i).trinket_name;
                textView.setText(tmp);
                textView.setTextColor(getResources().getColor(R.color.white));
                lineCurrentTrinkets.addView(textView, getLayoutParams());
            }
        });

        addArtworks.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Artworks artworks = CursachDatabase.getInstance(getApplicationContext()).artworksDao().getById(artworksId);
                chooseArtworks.add(artworks);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lineCurrentArtworks.removeAllViews();
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Произведения искусства:";
            textViewName.setText(tmpName);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            lineCurrentArtworks.addView(textViewName, getLayoutParams());

            for (int i = 0; i < chooseArtworks.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                String tmp = chooseArtworks.get(i).artworks_id + ", " + chooseArtworks.get(i).art_name;
                textView.setText(tmp);
                textView.setTextColor(getResources().getColor(R.color.white));
                lineCurrentArtworks.addView(textView, getLayoutParams());
            }
        });

        addCoins.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Coins coins = CursachDatabase.getInstance(getApplicationContext()).coinsDao().getById(coinsId);
                chooseCoins.add(coins);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lineCurrentCoins.removeAllViews();
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Монеты:";
            textViewName.setText(tmpName);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            lineCurrentCoins.addView(textViewName, getLayoutParams());

            for (int i = 0; i < chooseCoins.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                String tmp = chooseCoins.get(i).coins_id + ", " + chooseCoins.get(i).currencie + ", " + chooseCoins.get(i).coins_amount;
                textView.setText(tmp);
                textView.setTextColor(getResources().getColor(R.color.white));
                lineCurrentCoins.addView(textView, getLayoutParams());
            }
        });

        addPrecious_stones.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Precious_stones precious_stones = CursachDatabase.getInstance(getApplicationContext()).precious_stonesDao().getById(precious_stonesId);
                choosePrecious_stones.add(precious_stones);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lineCurrentPrecious_stones.removeAllViews();
            TextView textViewName = new TextView(getApplicationContext());
            String tmpName = "Драгоценные камни:";
            textViewName.setText(tmpName);
            textViewName.setTextColor(getResources().getColor(R.color.white));
            lineCurrentPrecious_stones.addView(textViewName, getLayoutParams());

            for (int i = 0; i < choosePrecious_stones.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                String tmp = choosePrecious_stones.get(i).precious_stones_id + ", " + choosePrecious_stones.get(i).stone_name;
                textView.setText(tmp);
                textView.setTextColor(getResources().getColor(R.color.white));
                lineCurrentPrecious_stones.addView(textView, getLayoutParams());
            }

        });

    }

    private void createPrecious_stonesSpinner() {
        new Thread(() -> {
            List<Precious_stones> precious_stones = CursachDatabase.getInstance(getApplicationContext()).precious_stonesDao().getAll();
            String[] precious_stonesStrings = new String[precious_stones.size()];
            for (int i = 0; i < precious_stonesStrings.length; i++) {
                precious_stonesStrings[i] = precious_stones.get(i).precious_stones_id + ", " + precious_stones.get(i).stone_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, precious_stonesStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            precious_stonesSpinner.setAdapter(adapter);
            precious_stonesSpinner.setDropDownHorizontalOffset(10);
            precious_stonesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    precious_stonesId = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createCoinsSpinner() {
        new Thread(() -> {
            List<Coins> coins = CursachDatabase.getInstance(getApplicationContext()).coinsDao().getAll();
            String[] coinsStrings = new String[coins.size()];
            for (int i = 0; i < coinsStrings.length; i++) {
                coinsStrings[i] = coins.get(i).coins_id + ", " + coins.get(i).currencie + ", " + coins.get(i).coins_amount;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coinsStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            coinsSpinner.setAdapter(adapter);
            coinsSpinner.setDropDownHorizontalOffset(10);
            coinsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    coinsId = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createArtworksSpinner() {
        new Thread(() -> {
            List<Artworks> artworks = CursachDatabase.getInstance(getApplicationContext()).artworksDao().getAll();
            String[] artworksStrings = new String[artworks.size()];
            for (int i = 0; i < artworksStrings.length; i++) {
                artworksStrings[i] = artworks.get(i).artworks_id + ", " + artworks.get(i).art_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, artworksStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            artworksSpinner.setAdapter(adapter);
            artworksSpinner.setDropDownHorizontalOffset(10);
            artworksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    artworksId = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
    }

    private void createTrinketsSpinner() {
        new Thread(() -> {
            List<Trinkets> trinkets = CursachDatabase.getInstance(getApplicationContext()).trinketsDao().getAll();
            String[] trinketsStrings = new String[trinkets.size()];
            for (int i = 0; i < trinketsStrings.length; i++) {
                trinketsStrings[i] = trinkets.get(i).trinkets_id + ", " + trinkets.get(i).trinket_name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trinketsStrings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            trinketsSpinner.setAdapter(adapter);
            trinketsSpinner.setDropDownHorizontalOffset(10);
            trinketsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    String[] string = item.split(",");
                    trinketId = Integer.parseInt(string[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }).start();
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

    private RelativeLayout.LayoutParams getRelativeLayoutParams(int idBelow) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        //int dpInPx300 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, dm);
        int dpInPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.topMargin = dpInPx20;

        buttonParams.addRule(RelativeLayout.BELOW, idBelow);

        return buttonParams;
    }
}