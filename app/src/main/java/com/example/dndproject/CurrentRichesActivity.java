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
import com.example.dndproject.db.entities.riches.ArtworksRichesEntity;
import com.example.dndproject.db.entities.riches.CoinsRichesEntity;
import com.example.dndproject.db.entities.riches.Precious_stonesRichesEntity;
import com.example.dndproject.db.entities.riches.Riches;
import com.example.dndproject.db.entities.riches.TrinketsRichesEntity;

import java.util.ArrayList;
import java.util.List;

public class CurrentRichesActivity extends AppCompatActivity {

    Riches currentRiches;
    List<TrinketsRichesEntity> currentTrinkets;
    List<Precious_stonesRichesEntity> currentPrecious_stones;
    List<ArtworksRichesEntity> currentArtworks;
    List<CoinsRichesEntity> currentCoins;

    TextView tvCurrentRichesId, tvTrinkets, tvPrecious_stones, tvCoins, tvArtworks, tvCurrentRichesTitle;
    Button buttEditRiches, buttDeleteRiches;
    LinearLayout layoutTrinketsForData, layoutPrecious_stonesForData, layoutCoinsForData, layoutArtworksForData;

    List<EditText[]> trinketsEditText, precious_stoneEditText, coinsEditText, artworksEditText;
    List<TextView[]> trinketsTextView, precious_stoneTextView, coinsTextView, artworksTextView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_current_riches);

        Bundle arguments = getIntent().getExtras();

        currentRiches = (Riches) arguments.get("currentRiches");

        tvTrinkets = findViewById(R.id.tvTrinkets);
        tvPrecious_stones = findViewById(R.id.tvPrecious_stones);
        tvCoins = findViewById(R.id.tvCoins);
        tvArtworks = findViewById(R.id.tvArtworks);

        layoutTrinketsForData = findViewById(R.id.layoutTrinketsForData);
        layoutPrecious_stonesForData = findViewById(R.id.layoutPrecious_stonesForData);
        layoutCoinsForData = findViewById(R.id.layoutCoinsForData);
        layoutArtworksForData = findViewById(R.id.layoutArtworksForData);

        setArtworks();

        setTrinkets();

        setPrecious_stone();

        setCoins();

        tvCurrentRichesId = findViewById(R.id.tvCurrentRichesId);
        tvCurrentRichesTitle = findViewById(R.id.tvCurrentRichesTitle);
        tvCurrentRichesId.setText(currentRiches.id_riches + "");
        tvCurrentRichesTitle.setText(currentRiches.title);

        buttEditRiches = findViewById(R.id.buttEditRiches);
        buttEditRiches.setOnClickListener(v -> {
            // TODO edit
        });

        buttDeleteRiches = findViewById(R.id.buttDeleteRiches);
        buttDeleteRiches.setOnClickListener(v -> new Thread(() -> {
            CursachDatabase.getInstance(getApplicationContext()).richesDao().delete(currentRiches);
            CursachDatabase.getInstance(getApplicationContext()).precious_stonesRichesMMDao().delete(currentRiches.id_riches);
            CursachDatabase.getInstance(getApplicationContext()).artworksRichesMMDao().delete(currentRiches.id_riches);
            CursachDatabase.getInstance(getApplicationContext()).coinsRichesMMDao().delete(currentRiches.id_riches);
            CursachDatabase.getInstance(getApplicationContext()).trinketsRichesMMDao().delete(currentRiches.id_riches);
            Intent intent = new Intent(CurrentRichesActivity.this, RichesActivity.class);
            startActivity(intent);
        }).start());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setArtworks() {
            try {
                getArtworksById(currentRiches.id_riches).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        layoutArtworksForData.removeAllViews();
        if (!currentArtworks.isEmpty()) {
            String[] artworksSupportData = new String[4];
            artworksSupportData[0] = "Число костей: ";
            artworksSupportData[1] = "Название: ";
            artworksSupportData[2] = "Цена: ";

            List<String[]> artworksData = new ArrayList<>();
            for (int i = 0; i < currentArtworks.size(); i++) {
                artworksData.add(new String[]{
                        currentArtworks.get(i).bone_number + "",
                        currentArtworks.get(i).art_name,
                        currentArtworks.get(i).art_price + ""});
            }

            artworksEditText = new ArrayList<>();
            artworksTextView = new ArrayList<>();
            for (int i = 0; i < artworksData.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Id: " + currentArtworks.get(i).artworks_id);
                textView.setTextColor(getResources().getColor(R.color.white));
                layoutArtworksForData.addView(textView, getLayoutParams());
                artworksEditText.add(new EditText[artworksData.get(i).length]);
                artworksTextView.add(new TextView[artworksData.get(i).length]);
                for (int j = 0; j < artworksData.get(i).length; j++) {
                    artworksEditText.get(i)[j] = new EditText(getApplicationContext());
                    artworksTextView.get(i)[j] = new TextView(getApplicationContext());

                    artworksEditText.get(i)[j].setText(artworksData.get(i)[j]);
                    artworksTextView.get(i)[j].setText(artworksSupportData[j]);

                    artworksEditText.get(i)[j].setTextColor(getResources().getColor(R.color.white));
                    artworksTextView.get(i)[j].setTextColor(getResources().getColor(R.color.white));

                    artworksEditText.get(i)[j].setEnabled(false);

                    layoutArtworksForData.addView(artworksTextView.get(i)[j], getLayoutParams());
                    layoutArtworksForData.addView(artworksEditText.get(i)[j], getLayoutParams());
                }
                View view = new View(getApplicationContext());
                view.setBackgroundColor(getResources().getColor(R.color.white));
                view.setMinimumHeight(2);
                layoutArtworksForData.addView(view, getLayoutParams());
            }
        } else {
            tvArtworks.setVisibility(View.GONE);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCoins() {
        try {
            getCoinsById(currentRiches.id_riches).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layoutCoinsForData.removeAllViews();
        if (!currentCoins.isEmpty()) {
            String[] coinsSupportData = new String[4];
            coinsSupportData[0] = "Валюта: ";
            coinsSupportData[1] = "Опасность: ";
            coinsSupportData[2] = "Число костей: ";
            coinsSupportData[3] = "Количество монет: ";

            List<String[]> coinsData = new ArrayList<>();
            for (int i = 0; i < currentCoins.size(); i++) {
                coinsData.add(new String[]{
                        currentCoins.get(i).currencie,
                        currentCoins.get(i).hazard,
                        currentCoins.get(i).bone_number,
                        currentCoins.get(i).coins_amount});
            }

            coinsEditText = new ArrayList<>();
            coinsTextView = new ArrayList<>();
            for (int i = 0; i < coinsData.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Id: " + currentCoins.get(i).coins_id);
                textView.setTextColor(getResources().getColor(R.color.white));
                layoutCoinsForData.addView(textView, getLayoutParams());
                coinsEditText.add(new EditText[coinsData.get(i).length]);
                coinsTextView.add(new TextView[coinsData.get(i).length]);
                for (int j = 0; j < coinsData.get(i).length; j++) {
                    coinsEditText.get(i)[j] = new EditText(getApplicationContext());
                    coinsTextView.get(i)[j] = new TextView(getApplicationContext());

                    coinsEditText.get(i)[j].setText(coinsData.get(i)[j]);
                    coinsTextView.get(i)[j].setText(coinsSupportData[j]);

                    coinsEditText.get(i)[j].setTextColor(getResources().getColor(R.color.white));
                    coinsTextView.get(i)[j].setTextColor(getResources().getColor(R.color.white));

                    coinsEditText.get(i)[j].setEnabled(false);

                    layoutCoinsForData.addView(coinsTextView.get(i)[j], getLayoutParams());
                    layoutCoinsForData.addView(coinsEditText.get(i)[j], getLayoutParams());
                }
                View view = new View(getApplicationContext());
                view.setBackgroundColor(getResources().getColor(R.color.white));
                view.setMinimumHeight(2);
                layoutCoinsForData.addView(view, getLayoutParams());
            }
        } else {
            tvCoins.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setPrecious_stone() {
        try {
            getPrecious_stonesById(currentRiches.id_riches).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layoutPrecious_stonesForData.removeAllViews();
        if (!currentPrecious_stones.isEmpty()) {
            String[] precious_stoneSupportData = new String[4];
            precious_stoneSupportData[0] = "Число костей: ";
            precious_stoneSupportData[1] = "Название камня: ";
            precious_stoneSupportData[2] = "Описание камня: ";
            precious_stoneSupportData[3] = "Цена камня: ";

            List<String[]> precious_stoneData = new ArrayList<>();
            for (int i = 0; i < currentPrecious_stones.size(); i++) {
                precious_stoneData.add(new String[]{
                        currentPrecious_stones.get(i).bone_number + "",
                        currentPrecious_stones.get(i).stone_name,
                        currentPrecious_stones.get(i).stone_description,
                        currentPrecious_stones.get(i).stone_price + ""});
            }

            precious_stoneEditText = new ArrayList<>();
            precious_stoneTextView = new ArrayList<>();
            for (int i = 0; i < precious_stoneData.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Id: " + currentPrecious_stones.get(i).precious_stones_id);
                textView.setTextColor(getResources().getColor(R.color.white));
                layoutPrecious_stonesForData.addView(textView, getLayoutParams());
                precious_stoneEditText.add(new EditText[precious_stoneData.get(i).length]);
                precious_stoneTextView.add(new TextView[precious_stoneData.get(i).length]);
                for (int j = 0; j < precious_stoneData.get(i).length; j++) {
                    precious_stoneEditText.get(i)[j] = new EditText(getApplicationContext());
                    precious_stoneTextView.get(i)[j] = new TextView(getApplicationContext());

                    precious_stoneEditText.get(i)[j].setText(precious_stoneData.get(i)[j]);
                    precious_stoneTextView.get(i)[j].setText(precious_stoneSupportData[j]);

                    precious_stoneEditText.get(i)[j].setTextColor(getResources().getColor(R.color.white));
                    precious_stoneTextView.get(i)[j].setTextColor(getResources().getColor(R.color.white));

                    precious_stoneEditText.get(i)[j].setEnabled(false);

                    layoutPrecious_stonesForData.addView(precious_stoneTextView.get(i)[j], getLayoutParams());
                    layoutPrecious_stonesForData.addView(precious_stoneEditText.get(i)[j], getLayoutParams());
                }
                View view = new View(getApplicationContext());
                view.setBackgroundColor(getResources().getColor(R.color.white));
                view.setMinimumHeight(2);
                layoutPrecious_stonesForData.addView(view, getLayoutParams());
            }
        } else {
            tvPrecious_stones.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTrinkets() {
        try {
            getTrinketsById(currentRiches.id_riches).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layoutTrinketsForData.removeAllViews();
        if (!currentTrinkets.isEmpty()) {
            String[] trinketsSupportData = new String[2];
            trinketsSupportData[0] = "Число костей: ";
            trinketsSupportData[1] = "Название: ";

            List<String[]> trinketsData = new ArrayList<>();
            for (int i = 0; i < currentTrinkets.size(); i++) {
                trinketsData.add(new String[]{currentTrinkets.get(i).bone_number + "", currentTrinkets.get(i).trinket_name});
            }

            trinketsEditText = new ArrayList<>();
            trinketsTextView = new ArrayList<>();
            for (int i = 0; i < trinketsData.size(); i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Id: " + currentTrinkets.get(i).trinkets_id);
                textView.setTextColor(getResources().getColor(R.color.white));
                layoutTrinketsForData.addView(textView, getLayoutParams());
                trinketsEditText.add(new EditText[trinketsData.get(i).length]);
                trinketsTextView.add(new TextView[trinketsData.get(i).length]);
                for (int j = 0; j < trinketsData.get(i).length; j++) {
                    trinketsEditText.get(i)[j] = new EditText(getApplicationContext());
                    trinketsTextView.get(i)[j] = new TextView(getApplicationContext());

                    trinketsEditText.get(i)[j].setText(trinketsData.get(i)[j]);
                    trinketsTextView.get(i)[j].setText(trinketsSupportData[j]);

                    trinketsEditText.get(i)[j].setTextColor(getResources().getColor(R.color.white));
                    trinketsTextView.get(i)[j].setTextColor(getResources().getColor(R.color.white));

                    trinketsEditText.get(i)[j].setEnabled(false);

                    layoutTrinketsForData.addView(trinketsTextView.get(i)[j], getLayoutParams());
                    layoutTrinketsForData.addView(trinketsEditText.get(i)[j], getLayoutParams());
                }
                View view = new View(getApplicationContext());
                view.setBackgroundColor(getResources().getColor(R.color.white));
                view.setMinimumHeight(2);
                layoutTrinketsForData.addView(view, getLayoutParams());
            }
        } else {
            tvTrinkets.setVisibility(View.GONE);
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

    private synchronized Thread getTrinketsById(int id) {
        Thread thread = new Thread(() -> currentTrinkets = CursachDatabase.getInstance(getApplicationContext()).richesDao().getTrinketsRichesEntity(id));
        thread.start();
        return thread;
    }

    private synchronized Thread getCoinsById(int id) {
        Thread thread = new Thread(() -> currentCoins = CursachDatabase.getInstance(getApplicationContext()).richesDao().getCoinsRichesEntity(id));
        thread.start();
        return thread;
    }

    private synchronized Thread getPrecious_stonesById(int id) {
        Thread thread = new Thread(() -> currentPrecious_stones = CursachDatabase.getInstance(getApplicationContext()).richesDao().getPrecious_stonesRichesEntity(id));
        thread.start();
        return thread;
    }


    private synchronized Thread getArtworksById(int id) {
        Thread thread = new Thread(() -> currentArtworks = CursachDatabase.getInstance(getApplicationContext()).richesDao().getArtworksRichesEntity(id));
        thread.start();
        return thread;
    }
}