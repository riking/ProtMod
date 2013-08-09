package com.wlan222.ProtMod;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wlan222.ProtMod.listeners.LobbyJoinListener;
import com.wlan222.ProtMod.listeners.PlayerQuitListener;
import com.wlan222.ProtMod.lobby.Lobby;
import com.wlan222.ProtMod.lobby.LobbyManager;

public class ProtMod extends JavaPlugin {

	ArrayList<LobbyManager> hooks = new ArrayList<LobbyManager>();

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new LobbyJoinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
	}

	/**
	 *
	 * @param lobbyManager The LobbyManager that should be registered
	 */
	public void registerLobbyManager(LobbyManager lobbyManager) {
		getLogger().info("The Minigame '" + lobbyManager.getGameID() + "' hooked into ProtMod");
		hooks.add(lobbyManager);
	}

	/**
	 * Check if ProtMod is managing this player.
	 *
	 * @param p Player to check
	 * @return true if the Player is in any ProtMod powered Minigame
	 */
	public boolean isPlayer(Player p) {
		for (LobbyManager lm : hooks) {
			if (lm.isPlayer(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the ProtMod Lobby that the given player is in.
	 *
	 * @param p Player to check
	 * @return The Lobby of the Player, or null if not in one. This works
	 *         globally across all ProtMod powered Minigames
	 */
	public Lobby getLobbyByPlayer(Player p) {
		for (LobbyManager lm : hooks) {
			if (lm.isPlayer(p)) {
				return lm.getLobbyByPlayer(p);
			}
		}
		return null;
	}
}
