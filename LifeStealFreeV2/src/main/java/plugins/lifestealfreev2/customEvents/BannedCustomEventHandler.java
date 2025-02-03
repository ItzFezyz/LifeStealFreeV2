package plugins.lifestealfreev2.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BannedCustomEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private boolean banned;

    private Player player;

    public BannedCustomEvent(boolean banned, Player player){
        this.banned = banned;
        this.player = player;
    }

    public boolean isBanned() {
        return banned;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
