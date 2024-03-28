package plugins.lifestealfreev2.events;

import dev.selena.luacore.LuaCore;
import lombok.SneakyThrows;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.HeartRightClickCustomEvent;
import plugins.lifestealfreev2.data.DataManager;
import plugins.lifestealfreev2.items.HeartItem;

public class HeartRightClickEvent implements Listener {
    private Main plugin = Main.getIsntance();
    private static ItemStack heart = HeartItem.getHeart();
    @SneakyThrows
    @EventHandler
    private void HeartRightClient(HeartRightClickCustomEvent event) {
        if (event.getPlayer().hasPermission("LifeSteal.Heart")) {
            DataManager pData = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, event.getPlayer().getUniqueId());
            int hearts = pData.getData1().getHearts();
            System.out.println(hearts);
            int heartamount = plugin.getConfig().getInt("MaxHearts");
            System.out.println(heartamount);
            heartamount = heartamount*2;
            if (hearts < heartamount) {
                hearts += 2;
                event.getPlayer().setMaxHealth(hearts);
                pData.getData1().setHearts(hearts);
                LuaCore.save();
                int amount = event.getPlayer().getInventory().getItemInMainHand().getAmount();
                amount -= 1;
                event.getPlayer().getInventory().getItemInMainHand().setAmount(amount);
            }
        }
    }
}
