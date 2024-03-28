package plugins.lifestealfreev2.customEvents;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class Test extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Location location;
    private final String baseName;

    public Test(Player player, Location location, String baseName){
        this.player = player;
        this.location = location;
        this.baseName = baseName;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }

    public String getBaseName() {
        return baseName;
    }

    public @NotNull HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
