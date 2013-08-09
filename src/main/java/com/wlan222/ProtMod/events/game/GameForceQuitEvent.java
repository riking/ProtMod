package com.wlan222.ProtMod.events.game;

import com.wlan222.ProtMod.lobby.Lobby;

/**
 * This event is called when a game is force-quit.
 */
public class GameForceQuitEvent extends GameEndEvent {
    public GameForceQuitEvent(Lobby l) {
        super(l);
    }
}
