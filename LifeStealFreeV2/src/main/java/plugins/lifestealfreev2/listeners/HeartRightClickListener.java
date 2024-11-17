package plugins.lifestealfreev2.listeners;


import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.HeartRightClickCustomEvent;
import plugins.lifestealfreev2.items.HeartItem;

public class HeartRightClickListener implements Listener {
    private ItemStack heart = HeartItem.getHeart();
    private Main plugin = Main.getIsntance();
    @EventHandler
    private void HeartClickEvent(PlayerInteractEvent event){
        if(event.getAction().isRightClick()) {
            String heartName = plugin.getConfig().getString("HeartName");
            if (event.getPlayer().getItemInHand().getItemMeta() != null) {
                if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(IridiumColorAPI.process(heartName))) {
                    event.setCancelled(true);
                    Bukkit.getServer().getPluginManager().callEvent(new HeartRightClickCustomEvent(true, (int) event.getPlayer().getMaxHealth(), event.getPlayer()));
                }
            }
        }
    }
}
