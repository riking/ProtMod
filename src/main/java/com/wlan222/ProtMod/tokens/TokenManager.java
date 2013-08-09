package com.wlan222.ProtMod.tokens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenManager {

    private File pointsFolder;
    private File tokenFile;
    private JavaPlugin pl;

    /**
     * @param pl Use Plugin for local Tokensystem or null for global. WARNING
     *            The global token system is not perfect because the value of
     *            one token is different per minigame so it might be too easy
     *            to reach a amount of tokens
     */

    public TokenManager(JavaPlugin pl) {
        if (pl == null) {
            pointsFolder = Bukkit.getPluginManager().getPlugin("ProtMod").getDataFolder();
        } else {
            this.pl = pl;
            pointsFolder = pl.getDataFolder();
        }
        pointsFolder.mkdir();
        tokenFile = new File(pointsFolder, "tokens");
    }

    /**
     *
     * @param p Player that account should be used
     * @param amount Amount of Tokens the Player needs
     * @return Does the Player has enough tokens
     */

    public boolean hasEnoughTokens(Player p, int amount) {
        if (loadWinPoints().get(p.getName()) >= amount) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param p Player that account should be used
     * @return Amount of Tokens the Player has
     */

    public int getTokens(Player p) {
        if (!loadWinPoints().containsKey(p.getName())) {
            return 0;
        } else {
            return loadWinPoints().get(p.getName());
        }

    }

    /**
     *
     * @param p Player that account should be used
     * @param amount Amount of Tokens the Player should get
     */

    public void giveTokens(Player p, int amount) {
        HashMap<String, Integer> winpoints = loadWinPoints();
        if (winpoints.containsKey(p.getName())) {
            winpoints.put(p.getName(), (winpoints.get(p.getName()) - amount));
        } else {
            winpoints.put(p.getName(), (0 - amount));
        }

        saveWinPoints(winpoints);
    }

    /**
     *
     * @param hashmap The Tokens are saved as HashMap in a GZipped File
     */

    private void saveWinPoints(HashMap<String, Integer> hashmap) {
        try {
            FileOutputStream fos = new FileOutputStream(tokenFile);
            GZIPOutputStream gzos = new GZIPOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(gzos);
            out.writeObject(hashmap);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return returns the Tokens as HashMap
     */

    @SuppressWarnings("unchecked")
    private HashMap<String, Integer> loadWinPoints() {
        HashMap<String, Integer> out = null;
        if (!tokenFile.exists()) {
            HashMap<String, Integer> newHM = new HashMap<String, Integer>();
            saveWinPoints(newHM);
        }
        try {
            FileInputStream fin = new FileInputStream(tokenFile);
            GZIPInputStream gzin = new GZIPInputStream(fin);
            ObjectInputStream in = new ObjectInputStream(gzin);
            Object o = in.readObject();
            if (!(o instanceof HashMap<?, ?>)) {
                if (pl != null) {
                    Logger.getLogger("ProtMod").severe("Fatal Error! Tokens file from " + pl.getName() + " was corrupted!");
                } else {
                    Logger.getLogger("ProtMod").severe("Fatal Error! Global Tokens file was corrupted!");
                }

                out = new HashMap<String, Integer>();
            } else {
                out = (HashMap<String, Integer>) o;
            }
            in.close();
            gzin.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
