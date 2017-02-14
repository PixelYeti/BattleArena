package com.pixelyeti.BattleArena.GameMechs;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Callum on 11/06/2015.
 */
public class Game {

    public int minPlayers;
    public String gameName;
    public GameState gameState;
    public ArrayList<UUID> players = new ArrayList<UUID>();
    public Map map;

    public Game(int minPlayers, String gameName, GameState gameState) {
        this.minPlayers = minPlayers;
        this.gameName = gameName;
        this.gameState = gameState;
    }

    public ArrayList<UUID> getPlayers() { return players; }
}