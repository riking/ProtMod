package com.wlan222.ProtMod.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.wlan222.ProtMod.ProtMod;
import com.wlan222.ProtMod.events.lobby.LobbyJoinEvent;

public class LobbyJoinListener implements Listener {

	private ProtMod pl;

	public LobbyJoinListener(ProtMod pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onLobbyJoinEvent(LobbyJoinEvent event) {
		if (pl.isPlayer(event.getPlayer())) {
			if (event.getLobby().getGameID().equals(pl.getLobbyByPlayer(event.getPlayer()).getGameID())) {
				event.getPlayer().sendMessage(ChatColor.RED + "You already joined another Lobby");
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "You are in a Lobby of " + pl.getLobbyByPlayer(event.getPlayer()).getGameID());
			}
			event.setCancelled(true);
		}
	}
}
