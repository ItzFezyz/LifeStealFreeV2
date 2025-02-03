package plugins.lifestealfreev2.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class LoseHeartCustomEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private boolean banned;
    private int hearts;

    private Player killer;

    public LoseHeartCustomEvent(Player player, boolean banned, int hearts, Player killer){
        this.player = player;
        this.banned = banned;
        this.hearts = hearts;
        this.killer = killer;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isBanned() {
        return banned;
    }

    public int getHearts() {
        return hearts;
    }

    public Player getKiller() {return killer;}


    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
