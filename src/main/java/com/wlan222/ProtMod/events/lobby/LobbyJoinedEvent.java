package com.wlan222.ProtMod.events.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.events.LobbyEvent;
import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyJoinedEvent extends LobbyEvent {
	private static final HandlerList handlers = new HandlerList();
	private Player player;

	/**
	 * Called when a Player successfully joined a Lobby
	 *
	 * @param p The Player
	 * @param l The Lobby
	 * @param gid The unique Minigame ID
	 */
	public LobbyJoinedEvent(Player p, Lobby l) {
		super(l);
		player = p;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
