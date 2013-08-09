package com.wlan222.ProtMod.events.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.wlan222.ProtMod.events.LobbyEvent;
import com.wlan222.ProtMod.lobby.Lobby;

public class LobbyLeaveEvent extends LobbyEvent {
	private static final HandlerList handlers = new HandlerList();
	private Player player;

	/**
	 * Called when a Player leaves a Lobby
	 *
	 * @param p Player that left the Game
	 * @param l Lobby of the Player
	 * @param gid Unique Minigame ID
	 */

	public LobbyLeaveEvent(Player p, Lobby l) {
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
