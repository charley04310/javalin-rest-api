package com.nmeo.services;
import java.util.ArrayList;
import java.util.List;
import com.nmeo.models.Pokemon;

public class PokemonService {

    public List<Pokemon> pokemonList;

    public PokemonService() {
        this.pokemonList = new ArrayList<>();
    }


    public List<Pokemon> getAllPokemonsByName(String pokemonName) {
    List<Pokemon> pokemonList = new ArrayList<>();
    for (Pokemon pokemon : this.pokemonList) {
        if (pokemon.getPokemonName().contains(pokemonName)) {
            pokemonList.add(pokemon);
        }
    }
    return pokemonList;

    }

    public List<Pokemon> getAllPokemonsByType(String type) {
        List<Pokemon> pokemonList = new ArrayList<>();
        for (Pokemon pokemon : this.pokemonList) {
            if (pokemon.getType().toString().equals(type)) {
                pokemonList.add(pokemon);
            }
        }

        
        return pokemonList;
    }

    // public Pokemon modifyPokemon(Pokemon pokemon) {
    //     Pokemon pokemonToUpdate = this.getPokemonByName(pokemon.pokemonName);
    //     if (pokemonToUpdate == null) {
    //         return null;
    //     }
    //     pokemonToUpdate.lifePoints = pokemon.lifePoints;
    //     pokemonToUpdate.powers = pokemon.powers;
    //     pokemonToUpdate.type = pokemon.type;
    //     return pokemonToUpdate;
    // }

    public Pokemon addPokemon(Pokemon pokemon) {

    boolean pokemonExists = false;

    for (Pokemon pokemonInList : this.pokemonList) {
        if (pokemonInList.pokemonName.equals(pokemon.pokemonName)) {
            pokemonExists = true;
            break;
        }
    }

    pokemon.pokemonName.toLowerCase();

    if (!pokemonExists) {
        this.pokemonList.add(pokemon);
        return pokemon;
    } else {
        return null;

    }

    }

}