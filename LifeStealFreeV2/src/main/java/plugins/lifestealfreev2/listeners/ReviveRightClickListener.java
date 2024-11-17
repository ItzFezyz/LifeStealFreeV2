package plugins.lifestealfreev2.listeners;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.ReviveRightClickCustomEvent;

public class ReviveRightClickListener implements Listener {
    private Main plugin = Main.getIsntance();
    @EventHandler
    private void ReviveClick(PlayerInteractEvent event){
        if (event.getAction().isRightClick()) {
            String name = plugin.getConfig().getString("ReviveName");
            if (event.getPlayer().getItemInHand().getItemMeta() != null) {
                if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(IridiumColorAPI.process(name))) {
                    event.setCancelled(true);
                    Bukkit.getServer().getPluginManager().callEvent(new ReviveRightClickCustomEvent(event.getPlayer(), event.getPlayer().getItemInHand()));
                }
            }
        }
    }
}
