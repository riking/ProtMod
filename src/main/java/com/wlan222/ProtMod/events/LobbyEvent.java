package com.wlan222.ProtMod.events;

import org.bukkit.event.Event;

import com.wlan222.ProtMod.lobby.Lobby;

public abstract class LobbyEvent extends Event {
	protected Lobby lobby;

	public LobbyEvent(Lobby l) {
		lobby = l;
	}

	/**
	 * The Lobby involved in this event.
	 *
	 * @return the lobby
	 */
	public Lobby getLobby() {
		return lobby;
	}

	/**
	 * The unique game identifier of the Lobby in this event..
	 *
	 * @return game identifier
	 */
	public String getGameID() {
		return lobby.getGameID();
	}
}
