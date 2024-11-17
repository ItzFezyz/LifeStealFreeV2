package plugins.lifestealfreev2.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ReviveRightClickCustomEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    
    private Player player;
    
    private ItemStack item;
    
    private Event e;
    
    public ReviveRightClickCustomEvent(Player player, ItemStack item){
        this.player = player;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }


    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
