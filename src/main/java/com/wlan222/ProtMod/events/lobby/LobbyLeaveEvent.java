package com.wlan222.ProtMod.events.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Lobby lobby;
    private String gameid;

    /**
     * Called when a Player leaves a Lobby
     * 
     * @param p Player that left the Game
     * @param l Lobby of the Player
     * @param gid Unique Minigame ID
     */

    public LobbyLeaveEvent(Player p, Lobby l, String gid) {
        player = p;
        lobby = l;
        gameid = gid;
    }

    public Player getPlayer() {
        return player;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public String getGameID() {
        return gameid;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
