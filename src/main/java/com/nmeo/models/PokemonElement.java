package com.nmeo.models;

public enum PokemonElement {
        NEUTRAL("neutral"),
        FIRE("fire"),
        WATER("water"),
        GRASS("grass"),
        ELECTRIC("electric"),
        ICE("ice"),
        FIGHTING("fighting"),
        POISON("poison"),
        GROUND("ground");

        public final String element;
        private PokemonElement(String p_element) {
            this.element = p_element;
        }
};

