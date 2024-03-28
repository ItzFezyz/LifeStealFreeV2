package plugins.lifestealfreev2.events;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.BannedCustomEvent;

public class BannedEvent implements Listener {
    private Main plugin = Main.getIsntance();
    @EventHandler
    public void onBanned(BannedCustomEvent event){
        event.getPlayer().getInventory().clear();
        FileConfiguration config = plugin.getConfig();
        String Message = config.getString("BanMessage");
        Message = Message.replace("${player}", "${Player}");
        Message = Message.replace("${Player}", event.getPlayer().getDisplayName());
        Bukkit.getServer().broadcastMessage(IridiumColorAPI.process(Message));
        event.getPlayer().kickPlayer(IridiumColorAPI.process(Message));
    }
}
