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
		Bukkit.getPluginManager().registerEvents(new LobbyJoinListener(this),
				this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this),
				this);
	}

	/**
	 * 
	 * @param lobbyManager
	 *            The LobbyManager that should be registered
	 */
	public void registerLobbyManager(LobbyManager lobbyManager) {
		getLogger().info(
				"The Minigame '" + lobbyManager.getGameID()
						+ "' hooked into ProtMod");
		hooks.add(lobbyManager);
	}

	/**
	 * 
	 * @param p
	 *            Player to check
	 * @return If the Player is in any ProtMod powered Minigame
	 */
	public boolean isPlayer(Player p) {
		boolean flag = false;
		for (LobbyManager lm : hooks) {
			if (lm.isPlayer(p)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param p
	 *            Player to check
	 * @return The Lobby of the Player. This works globally across all ProtMod
	 *         powered Minigames
	 */
	public Lobby getLobbyByPlayer(Player p) {
		Lobby l = null;
		for (LobbyManager lm : hooks) {
			if (lm.isPlayer(p)) {
				l = lm.getLobbyByPlayer(p);
			}
		}
		return l;
	}
}
