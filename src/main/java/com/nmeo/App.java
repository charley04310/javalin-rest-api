package com.nmeo;

import io.javalin.Javalin;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

// import com.nmeo.dto.ModifyPokemonRequest;
import com.nmeo.dto.ResponseMessage;
import com.nmeo.models.Pokemon;
import com.nmeo.services.PokemonService;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);
        logger.info("Pokedex backend is booting...");

        int port = System.getenv("SERVER_PORT") != null? Integer.parseInt(System.getenv("SERVER_PORT")) : 8080;

        PokemonService pokemonService = new PokemonService();

        Javalin.create()
        .get("/api/status", ctx -> {
            logger.debug("Status handler triggered", ctx);
            ctx.status(200);
        })
        .post("/api/create", ctx -> {
            try {

            logger.debug("Create handler triggered", ctx);

            String requestBody = ctx.body();

            if (requestBody.isEmpty()) {
                ctx.status(400); 
                return;
            }

            Pokemon pokemon = ctx.bodyAsClass(Pokemon.class);

            if (pokemon == null) {
                ctx.status(400);
                return;
            }

            Pokemon pokemonToAdd = pokemonService.addPokemon(pokemon);

            if (pokemonToAdd == null) {
                ctx.status(400);
                return;
            }
            
            ctx.status(200);
            ctx.json("Pokemon created");

        } catch (Exception e) {
            logger.error("Error while creating pokemon", e);
            ctx.status(400);
        }

        })
        .get("/api/searchByName", ctx -> {
            try {
            String nameToSearch = ctx.queryParam("name");
            
            if (nameToSearch == null) {
                ctx.status(400);
                return;
            }   

            List<Pokemon> pokemon = pokemonService.getAllPokemonsByName(nameToSearch);
            if (pokemon == null) {
                ctx.status(404);
                return;
            }

            ResponseMessage<Pokemon> reponseMessage = new ResponseMessage<Pokemon>();

            reponseMessage.addElements(pokemon);

            ctx.status(200);

            ctx.json(reponseMessage);
           
            } catch (Exception e) {
                logger.error("Error while creating pokemon", e);
                ctx.status(500);
            }
   
        })
        .post("/api/modify", ctx -> {

        // ModifyPokemonRequest request = ctx.bodyAsClass(ModifyPokemonRequest.class);


        })
        .get("/api/searchByType", ctx -> {
            
            try {

            String typeToSearch = ctx.queryParam("type");

            if (typeToSearch == null) {
                ctx.status(400);
                return;
            }

            List<Pokemon> pokemon = pokemonService.getAllPokemonsByType(typeToSearch);
            if (pokemon == null) {
                ctx.status(404);
                return;
            }

            ResponseMessage<Pokemon> reponseMessage = new ResponseMessage<Pokemon>();

            reponseMessage.addElements(pokemon);

            ctx.status(200);

            ctx.json(reponseMessage);


            } catch (Exception e) {
                logger.error("Error while creating pokemon", e);
                ctx.status(400);
            }


        })
        .start(port);
    }
}