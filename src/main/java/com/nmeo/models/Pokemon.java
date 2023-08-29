package com.nmeo.models;
import java.util.List;

public class Pokemon {

    public String pokemonName;
    public PokemonType type;
    public int lifePoints;
    public List<Power> powers;

    public Pokemon() {
    }

    public void toLowerCase() {
        this.pokemonName = this.pokemonName.toLowerCase();
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public PokemonType getType() {
        return type;
    }

}
