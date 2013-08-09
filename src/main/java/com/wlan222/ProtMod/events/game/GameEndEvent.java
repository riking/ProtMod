package com.wlan222.ProtMod.events.game;

import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.events.LobbyEvent;
import com.wlan222.ProtMod.lobby.Lobby;

/**
 * This event is called when a game is ended for any reason.
 */
public class GameEndEvent extends LobbyEvent {

	private static final HandlerList handlers = new HandlerList();
	private Lobby lobby;
	private String gameid;

	public GameEndEvent(Lobby l) {
		super(l);
	}

	/**
	 * The Lobby whose game is ending.
	 *
	 * @return the lobby
	 */
	public Lobby getLobby() {
		return lobby;
	}

	/**
	 * The unique game identifier of the game that is ending.
	 *
	 * @return game identifier
	 */
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
