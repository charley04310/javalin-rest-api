package com.nmeo;

import io.javalin.Javalin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);
        logger.info("Pokedex backend is booting...");

        int port = System.getenv("SERVER_PORT") != null? Integer.parseInt(System.getenv("SERVER_PORT")) : 8080;

        Javalin.create()
        .get("/api/status", ctx -> {
            logger.debug("Status handler triggered", ctx);
            ctx.status(200);
        })
        .post("/api/create", ctx -> {
            //Pokemon pokemon = ctx.bodyAsClass(Pokemon.class);
        })
        .get("/api/searchByName", ctx -> {
            //String nameToSearch = ctx.queryParam("name");
        })
        .post("/api/modify", ctx -> {
            //String typeToSearch = ctx.queryParam("type");
        })
        .get("/api/searchByType", ctx -> {
            //ModifyPokemonRequest request = ctx.bodyAsClass(ModifyPokemonRequest.class);
        })
        .start(port);
    }
}