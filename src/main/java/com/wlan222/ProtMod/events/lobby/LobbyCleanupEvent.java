package com.wlan222.ProtMod.events.lobby;

import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.events.LobbyEvent;
import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyCleanupEvent extends LobbyEvent {
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Called when a lobby is cleaned up
	 *
	 * @param l The Lobby
	 * @param gid The unique Minigame ID
	 */
	public LobbyCleanupEvent(Lobby l) {
		super(l);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
