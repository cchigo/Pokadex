package com.example.pokadex;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {
    public static class PokedexViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout containerView;
        public TextView textView;

        PokedexViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.pokadex_row);
            textView = view.findViewById(R.id.pokadex_row_text_view);

            //event handler to be clicked when the row is tapped

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pokemon current = (Pokemon) containerView.getTag();

                    // PokemonActivity.class
                    Intent intent = new Intent(v.getContext(), PokemonActivity.class);


                    intent.putExtra("name", current.getName());
                    intent.putExtra("number", current.getNumber());


                    //start intent
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    private List<Pokemon> pokemon = Arrays.asList(
            new Pokemon("Bulbasaur", 1),
            new Pokemon("lanister", 2),
            new Pokemon("thyron", 3),
            new Pokemon("cersei", 4)
    );

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokadex_row, parent, false);

        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon current = pokemon.get(position);
        holder.textView.setText(current.getName());
        holder.containerView.setTag(current);

    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }
}
