package plugins.lifestealfreev2.events;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import dev.selena.luacore.LuaCore;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.LoseHeartCustomEvent;
import plugins.lifestealfreev2.data.DataManager;
import plugins.lifestealfreev2.items.HeartItem;
import plugins.lifestealfreev2.tools.LuaCoreE;

import java.util.UUID;

public class LoseHeartEvent implements Listener {
    private Main plugin = Main.getIsntance();

    @SneakyThrows
    @EventHandler
    public void loseHeart(LoseHeartCustomEvent event){
        int hearts = event.getHearts();
        String Message = plugin.getConfig().getString("DeathMessage");
        Message = Message.replace("${player}", "${Player}");
        Message = Message.replace("${Player}", event.getPlayer().getDisplayName());
        if (plugin.getConfig().getBoolean("Prefix")) {
            String Prefix = plugin.getConfig().getString("PrefixMessage");
            Bukkit.broadcastMessage(IridiumColorAPI.process(Prefix+Message));
        } else {
            Bukkit.broadcastMessage(IridiumColorAPI.process(Message));
        }
        hearts -= 2;
        DataManager pData = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class,event.getPlayer().getUniqueId());
        event.getPlayer().setMaxHealth(hearts);
        pData.getData1().setHearts(hearts);
        if (event.getKiller() != null) {
            DataManager vData = LuaCoreE.getData(event.getKiller().getUniqueId());
            int vhearts = vData.getData1().getHearts();
            vhearts += 2;
            if (plugin.getConfig().getString("KillType").equals("GAIN")) {
                vData.getData1().setHearts(vhearts);
                event.getKiller().setMaxHealth(vhearts);
            }
            if (plugin.getConfig().getString("KillType").equals("DROP")) {
                Location location = event.getPlayer().getLocation();
                location.getWorld().dropItemNaturally(location, HeartItem.getHeart());
            }
            if (plugin.getConfig().getString("KillType").equals("BOTH")){
                if (vData.getData1().getHearts() < plugin.getConfig().getInt("MaxHearts")){
                    event.getKiller().setMaxHealth(vhearts);
                    vData.getData1().setHearts(vhearts);
                } else {
                    Location location = event.getPlayer().getLocation();
                    location.getWorld().dropItemNaturally(location, HeartItem.getHeart());
                }
            }
            LuaCore.save();
        }
    }
}
