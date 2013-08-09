package com.wlan222.ProtMod.events.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyJoinEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Player player;
    private Lobby lobby;
    private String gameid;

    /**
     * Called when a Player tries to join a Lobby
     * 
     * @param p The Player
     * @param l The Lobby
     * @param gid The unique Minigame ID
     */
    public LobbyJoinEvent(Player p, Lobby l, String gid) {
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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean arg0) {
        cancelled = arg0;
    }

}
