package com.example.pokadex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PokemonActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        String name = getIntent().getStringExtra("name");
        int number = getIntent().getIntExtra("number", 0);

        nameTextView = findViewById(R.id.pokemon_name);
        numberTextView = findViewById(R.id.pokemon_number);


        nameTextView.setText(name);
        //converts the int to a string
        numberTextView.setText(String.format("#%03d", number));
    }
}
