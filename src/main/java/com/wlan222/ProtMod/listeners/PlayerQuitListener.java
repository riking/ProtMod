package com.wlan222.ProtMod.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.wlan222.ProtMod.ProtMod;

public class PlayerQuitListener implements Listener {

    private ProtMod pl;

    public PlayerQuitListener(ProtMod pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (pl.isPlayer(event.getPlayer())) {
            pl.getLobbyByPlayer(event.getPlayer()).leave(event.getPlayer());
        }
    }
}
