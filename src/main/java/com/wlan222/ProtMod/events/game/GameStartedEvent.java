package com.wlan222.ProtMod.events.game;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.events.LobbyEvent;
import com.wlan222.ProtMod.lobby.Lobby;

public class GameStartedEvent extends LobbyEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;

	/**
	 * Called when a Game started
	 *
	 * @param l The Lobby
	 * @param gid The unique Minigame ID
	 */
	public GameStartedEvent(Lobby l, String gid) {
		super(l);
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
