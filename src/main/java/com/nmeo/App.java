package com.nmeo;

import io.javalin.Javalin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.nmeo.handlers.StatusHandler;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);
        logger.info("Pokedex backend is booting...");

        int port = System.getenv("SERVER_PORT") != null? Integer.parseInt(System.getenv("SERVER_PORT")) : 8080;

        Javalin.create()
            .get("/api/status", ctx -> {StatusHandler.statusHandler(ctx);})
            .start(port);
    }
}