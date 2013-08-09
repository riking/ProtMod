package com.wlan222.ProtMod.events.game;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.lobby.Lobby;

public class GameForceQuitEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Lobby lobby;
	private String gameid;

	/**
	 * Called when a Game is being force closed
	 * 
	 * @param l
	 *            The Lobby
	 * @param gid
	 *            The unique Minigame ID
	 */
	public GameForceQuitEvent(Lobby l, String gid) {
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
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
