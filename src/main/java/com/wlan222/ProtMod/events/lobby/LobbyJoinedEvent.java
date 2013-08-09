package com.wlan222.ProtMod.events.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyJoinedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String gameid;
    private Lobby lobby;
    private Player player;

    /**
     * Called when a Player successfully joined a Lobby
     * 
     * @param p The Player
     * @param l The Lobby
     * @param gid The unique Minigame ID
     */
    public LobbyJoinedEvent(Player p, Lobby l, String gid) {
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
