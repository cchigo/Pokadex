package com.example.pokadex;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
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


                    intent.putExtra("url", current.getUrl());
//                    intent.putExtra("number", current.getNumber());


                    //start intent
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    private List<Pokemon> pokemon = new ArrayList<>();

    //kick off api request so it starts running
    private RequestQueue requestQueue;

    PokedexAdapter(Context context){
        requestQueue = Volley.newRequestQueue(context);
        loadPokemon();
    }

    public void loadPokemon(){
        String url = "https://pokeapi.co/api/v2/pokemon?limit=151";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i< results.length(); i++){
                                JSONObject result = results.getJSONObject(i);
                                String name = result.getString("name");
                                pokemon.add(new Pokemon(
                                        name.substring(0, 1).toUpperCase()+ name.substring(1),
                                        result.getString("url")
                                ));

                            }
                            notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("cs50", "Json error", e);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", error.getMessage());
            }
        });
        requestQueue.add(request);
    }

    //static list created before
//    private List<Pokemon> pokemon = Arrays.asList(
//            new Pokemon("Bulbasaur", 1),
//            new Pokemon("lanister", 2),
//            new Pokemon("thyron", 3),
//            new Pokemon("cersei", 4)
//    );

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
