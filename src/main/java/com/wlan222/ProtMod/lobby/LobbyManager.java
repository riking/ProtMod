package com.wlan222.ProtMod.lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wlan222.ProtMod.ProtMod;
import com.wlan222.ProtMod.tokens.TokenManager;

public class LobbyManager {
    private HashMap<Player, Lobby> players = new HashMap<Player, Lobby>();
    private HashMap<String, Lobby> lobbyids = new HashMap<String, Lobby>();
    private ArrayList<Lobby> lobbys = new ArrayList<Lobby>();
    private String gameid;
    private JavaPlugin pl;

    /**
     *
     * @param gid Unique Game ID
     * @param pl The ProtMod Plugin ( to manage stuff )
     * @param yourpl Your Plugin
     */

    public LobbyManager(String gid, ProtMod pl, JavaPlugin yourpl) {
        gameid = gid;
        this.pl = yourpl;
        pl.registerLobbyManager(this);
    }

    /**
     * Adds a Lobby
     *
     * @param lobbyid A unique id needed for config files
     */
    public Lobby addLobby(String lobbyid) {
        return new Lobby(this, gameid, lobbyid, pl);
    }

    /**
     *
     * @param l Registers the Lobby
     */

    public void registerLobby(Lobby l) {
        lobbys.add(l);
    }

    /**
     *
     * @return returns the unique Game ID
     */
    public String getGameID() {
        return gameid;
    }

    /**
     *
     * @param p Player to check
     * @return If the Player is in this Game
     */
    public boolean isPlayer(Player p) {
        return players.containsKey(p);
    }

    /**
     *
     * @param p Player to get Lobby from
     * @return The Lobby of the Player or null if the Player has no Lobby
     */
    public Lobby getLobbyByPlayer(Player p) {
        return players.get(p);
    }

    /**
     *
     * @param pl Your Plugin or null if you want the global token system. Read
     *            the Warning in TokenManager.java
     * @return The TokenManager Object
     */
    public TokenManager getTokenSystem(JavaPlugin pl) {
        return new TokenManager(pl);
    }

    /**
     * Shuts down the Lobbys
     */
    public void shutdown() {
        for (Lobby l : lobbys) {
            l.cleanup();
        }
        players.clear();
    }

    /**
     * @return returns all lobby ids
     */
    public Set<String> getLobbyIDs() {
        return lobbyids.keySet();
    }

    /**
     *
     * @param p Player to be registered
     * @param l Lobby of the Player
     */
    public void addPlayer(Player p, Lobby l) {
        players.put(p, l);
    }

    /**
     *
     * @param p Player to be removed
     */
    public void removePlayer(Player p) {
        players.remove(p);
    }

}
