package com.wlan222.ProtMod.lobby;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wlan222.ProtMod.events.game.GameEndEvent;
import com.wlan222.ProtMod.events.game.GameForceQuitEvent;
import com.wlan222.ProtMod.events.game.GameStartedEvent;
import com.wlan222.ProtMod.events.lobby.LobbyCleanupEvent;
import com.wlan222.ProtMod.events.lobby.LobbyJoinEvent;
import com.wlan222.ProtMod.events.lobby.LobbyJoinedEvent;
import com.wlan222.ProtMod.events.lobby.LobbyLeaveEvent;

public class Lobby {

	private String gameid;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean gameRunning = false;
	private String lobbyid;
	private JavaPlugin pl;
	private FileConfiguration cfg;
	private File cfgf;
	private LobbyManager lm;

	/**
	 * 
	 * @param lm
	 *            The LobbyManager of your Minigame
	 * @param gameid
	 *            The unique Minigame ID
	 * @param lobbyid
	 *            The unique id of this Lobby
	 * @param pl
	 *            Your Plugin
	 */
	public Lobby(LobbyManager lm, String gameid, String lobbyid, JavaPlugin pl) {
		this.gameid = gameid;
		this.lobbyid = lobbyid;
		this.pl = pl;
		lm.registerLobby(this);
		this.lm = lm;
	}

	/**
	 * 
	 * @param p
	 *            Player that should join
	 */
	public void join(Player p) {
		LobbyJoinEvent e = new LobbyJoinEvent(p, this, gameid);
		Bukkit.getPluginManager().callEvent(e);
		if (e.isCancelled()) {
			return;
		}
		players.add(p);
		lm.addPlayer(p, this);
		LobbyJoinedEvent lje = new LobbyJoinedEvent(p, this, gameid);
		Bukkit.getPluginManager().callEvent(lje);
	}

	/**
	 * 
	 * @param p
	 *            Player that should leave the Lobby
	 */
	public void leave(Player p) {
		LobbyLeaveEvent e = new LobbyLeaveEvent(p, this, gameid);
		Bukkit.getPluginManager().callEvent(e);
		players.remove(p);
		lm.removePlayer(p);
	}

	/**
	 * 
	 * @param s
	 *            Message to be broadcasted
	 */
	public void broadcast(String s) {
		for (Player p : players) {
			p.sendMessage(s);
		}
	}

	/**
	 * 
	 * @param s
	 *            Message to be broadcasted
	 * @param exclude
	 *            A List of Players that should not receive this message
	 */
	public void broadcastExclusive(String s, ArrayList<Player> exclude) {
		for (Player p : players) {
			if (!exclude.contains(p)) {
				p.sendMessage(s);
			}
		}
	}

	/**
	 * 
	 * @param s
	 *            Message to be broadcasted
	 * @param exclude
	 *            A Player that should not receive the Message
	 */
	public void broadcastExclusive(String s, Player exclude) {
		ArrayList<Player> excludeList = new ArrayList<Player>();
		excludeList.add(exclude);
		broadcastExclusive(s, excludeList);
	}

	/**
	 * 
	 * @return A List of Players. WARNING don't add or remove Elements!
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * 
	 * @return The Amount of Players
	 */
	public int getPlayerCount() {
		return players.size();
	}

	/**
	 * 
	 * @return Returns whether the game is running or not
	 */
	public boolean isGameRunning() {
		return gameRunning;
	}

	/**
	 * Starts the Game
	 */
	public void startGame() {
		gameRunning = true;
		GameStartedEvent gse = new GameStartedEvent(this, gameid);
		Bukkit.getPluginManager().callEvent(gse);
		if (gse.isCancelled()) {
			gameRunning = false;
			return;
		}
	}

	/**
	 * Stops the Game
	 */
	public void stopGame() {
		gameRunning = false;
		GameEndEvent gee = new GameEndEvent(this, gameid);
		Bukkit.getPluginManager().callEvent(gee);
		cleanup();
	}

	/**
	 * Resets the Lobby so it can be used again
	 */
	public void cleanup() {
		if (isGameRunning()) {
			GameForceQuitEvent gfqe = new GameForceQuitEvent(this, gameid);
			Bukkit.getPluginManager().callEvent(gfqe);
		}
		for (Player p : players) {
			lm.removePlayer(p);
		}
		LobbyCleanupEvent lce = new LobbyCleanupEvent(this, gameid);
		Bukkit.getPluginManager().callEvent(lce);
		players.clear();
	}

	/**
	 * 
	 * @return The unique Minigame ID
	 */
	public String getGameID() {
		return gameid;
	}

	/**
	 * Reloads the per lobby Config File
	 */
	public void reloadConfig() {
		if (cfgf == null) {
			File directory = new File(pl.getDataFolder(), "lobbys");
			directory.mkdir();
			cfgf = new File(directory, lobbyid + ".yml");
		}
		cfg = YamlConfiguration.loadConfiguration(cfgf);

	}

	/**
	 * 
	 * @return The per Lobby Config File
	 */
	public FileConfiguration getConfig() {
		if (cfg == null) {
			this.reloadConfig();
		}
		return cfg;
	}

	/**
	 * Saves the Per Lobby Config File
	 */
	public void saveConfig() {
		if (cfg == null || cfgf == null) {
			return;
		}
		try {
			getConfig().save(cfgf);
		} catch (IOException ex) {
			pl.getLogger().log(Level.SEVERE,
					"Could not save config to " + cfgf, ex);
		}
		// Constant reloading of the save file
		cfgf = null;
		cfg = null;
	}
}
