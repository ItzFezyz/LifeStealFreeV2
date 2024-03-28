package plugins.lifestealfreev2.events;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import dev.selena.luacore.LuaCore;
import dev.selena.luacore.utils.items.ItemBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.ReviveRightClickCustomEvent;
import plugins.lifestealfreev2.data.DataManager;

import java.net.URL;
import java.util.UUID;


public class ReviveRightClickEvent implements Listener {
    private static Main plugin = Main.getIsntance();

    @SneakyThrows
    @EventHandler
    private void click(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission("LifeSteal.Revive")) {
            if (event.getView().getTitle().equals(IridiumColorAPI.process(plugin.getConfig().getString("ReviveGuiName")))) {
                if (event.getCurrentItem() != null) {
                    if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                        String name = event.getCurrentItem().getItemMeta().getDisplayName();
                        UUID uuid = Bukkit.getPlayerUniqueId(name);
                        DataManager pData = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, uuid);
                        pData.getData1().setBanned(false);
                        pData.getData1().setHearts(plugin.getConfig().getInt("ReviveHeartAmount") *2);
                        LuaCore.save();
                        event.getWhoClicked().closeInventory();
                        int amount = event.getWhoClicked().getInventory().getItemInMainHand().getAmount();
                        amount -= 1;
                        event.getWhoClicked().getInventory().getItemInMainHand().setAmount(amount);
                        String ReviveMessage = plugin.getConfig().getString("ReviveMessage");
                        ReviveMessage = ReviveMessage.replace("${reviver}", "${Reviver}");
                        ReviveMessage = ReviveMessage.replace("${Reviver}", event.getWhoClicked().getName());
                        ReviveMessage = ReviveMessage.replace("${revived}", "${Revived}");
                        ReviveMessage = ReviveMessage.replace("${Revived}", pData.getData1().getName());
                        Bukkit.broadcastMessage(IridiumColorAPI.process(ReviveMessage));
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @SneakyThrows
    @EventHandler
    private void Revive(ReviveRightClickCustomEvent event) {
        if (event.getPlayer().hasPermission("LifeSteal.Revive")) {
            Inventory gui = Bukkit.createInventory(event.getPlayer(), 54, IridiumColorAPI.process(plugin.getConfig().getString("ReviveGuiName")));

            OfflinePlayer[] a = Bukkit.getOfflinePlayers();
            // sadly chatGpt helped me with the c = 0; part because I was being an idiot 2 am brain moron
            int c = 0;
            ItemStack lol = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatColor.RESET + ("")).build();
            for (int i = 0; i < 9; i++) {
                gui.setItem(i, lol);
            }
            for (OfflinePlayer b : a) {
                UUID uuid = b.getUniqueId();
                DataManager pData = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, uuid);
                if (pData != null) {
                    if (pData.getData1().isBanned()) {
                        if (pData.getData1().getName() != null) {
                            URL url = pData.getData1().getSkin();
                            ItemStack enb = new ItemBuilder(Material.PLAYER_HEAD).setSkullProfile(url, b.getUniqueId(), pData.getData1().getName()).setTitle(pData.getData1().getName()).build();
                            int d = c + 9;
                            gui.setItem(d, enb);
                            c++;
                        }
                    }
                }
            }
            event.getPlayer().openInventory(gui);
        }
    }
}


