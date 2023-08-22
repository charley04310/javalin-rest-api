package com.nmeo.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nmeo.App;

import io.javalin.http.Context;

public class StatusHandler {

    private static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void statusHandler(Context ctx) {
        logger.debug("Status handler triggered", ctx);
        ctx.status(200);
    }
}
