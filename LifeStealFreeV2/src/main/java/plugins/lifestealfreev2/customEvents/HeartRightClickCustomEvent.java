package plugins.lifestealfreev2.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HeartRightClickCustomEvent extends Event {
    private static final HandlerList handlers = new HandlerList();



    private boolean heart;
    private int heartamount;
    private Player player;

    public HeartRightClickCustomEvent(boolean heart, int heartamount, Player player){
        this.heart = heart;
        this.heartamount = heartamount;
        this.player = player;
    }
    public boolean isHeart() { return heart; }

    public int getHeartamount() { return heartamount; }

    public Player getPlayer() { return player; }
    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
