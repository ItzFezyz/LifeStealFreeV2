package plugins.lifestealfreev2.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import plugins.lifestealfreev2.customEvents.Test;

public class TestListener implements Listener {
    @EventHandler
    public void Move(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();
        Bukkit.getServer().getPluginManager().callEvent(new Test(player, location, "a"));
    }
    @EventHandler
    public void onEnterBaseEvent(Test event){
        event.getPlayer().sendMessage(event.getBaseName(), event.getPlayer().getName(), event.getLocation().toString());
        System.out.println(event.getPlayer().getName()+" -> This was trigged by ItzFezy's cool ass api.");
        System.out.println(event.getLocation().getBlock()+" -> This was trigged by ItzFezy's cool ass api.");
        System.out.println(event.getBaseName()+" -> This was trigged by ItzFezy's cool ass api. 100% totally");
    }
}
