
package com.example.lekcja_2_gebicz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText poleKostek;
    private TextView wynikTekstowy;
    private Button losowanie;
    private Button rosnaco;
    private Button malejaco;
    private GridLayout plansza;

    private ArrayList<Integer> liczby = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        poleKostek = findViewById(R.id.editTextLiczbaKostek);
        wynikTekstowy = findViewById(R.id.textViewWyniki);
        losowanie = findViewById(R.id.buttonLosuj);
        rosnaco = findViewById(R.id.buttonRosnaco);
        malejaco = findViewById(R.id.buttonMalejaco);
        plansza = findViewById(R.id.gridKostki);

        losowanie.setOnClickListener(v -> losujKostki());

        rosnaco.setOnClickListener(v -> {
            if (liczby.isEmpty()) {
                komunikat("Najpierw wylosuj kostki!");
            } else {
                Collections.sort(liczby);
                wyswietlKostki();
            }
        });

        malejaco.setOnClickListener(v -> {
            if (liczby.isEmpty()) {
                komunikat("Najpierw wylosuj kostki!");
            } else {
                Collections.sort(liczby, Collections.reverseOrder());
                wyswietlKostki();
            }
        });
    }

    private void losujKostki() {
        String tekst = poleKostek.getText().toString().trim();

        if (tekst.isEmpty()) {
            komunikat("Wpisz liczbę kostek!");
            return;
        }

        int ilosc;

        try {
            ilosc = Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            komunikat("Podaj poprawną liczbę!");
            return;
        }

        if (ilosc < 1 || ilosc > 100) {
            komunikat("Możesz wylosować od 1 do 100 kostek!");
            return;
        }

        Random generator = new Random();
        liczby.clear();

        for (int i = 0; i < ilosc; i++) {
            int oczka = generator.nextInt(6) + 1;
            liczby.add(oczka);
        }

        wyswietlKostki();
    }

    private void wyswietlKostki() {
        plansza.removeAllViews();

        for (int liczba : liczby) {
            TextView element = new TextView(this);

            element.setText(String.valueOf(liczba));
            element.setTextSize(24);
            element.setPadding(12, 8, 12, 8);

            plansza.addView(element);
        }

        wynikTekstowy.setText(liczby.toString());
    }

    private void komunikat(String wiadomosc) {
        Toast.makeText(this, wiadomosc, Toast.LENGTH_SHORT).show();
    }
}
