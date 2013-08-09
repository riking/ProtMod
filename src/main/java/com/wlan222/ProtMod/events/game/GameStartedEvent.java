package com.wlan222.ProtMod.events.game;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.lobby.Lobby;

public class GameStartedEvent extends Event implements Cancellable {

	private boolean cancelled = false;
	private static final HandlerList handlers = new HandlerList();
	private Lobby lobby;
	private String gameid;

	/**
	 * Called when a Game started
	 * 
	 * @param l
	 *            The Lobby
	 * @param gid
	 *            The unique Minigame ID
	 */
	public GameStartedEvent(Lobby l, String gid) {
		lobby = l;
		gameid = gid;
	}

	public Lobby getLobby() {
		return lobby;
	}

	public String getGameID() {
		return gameid;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancelled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
