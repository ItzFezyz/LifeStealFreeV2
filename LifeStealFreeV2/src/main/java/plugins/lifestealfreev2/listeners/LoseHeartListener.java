package plugins.lifestealfreev2.listeners;

import dev.selena.luacore.LuaCore;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import plugins.lifestealfreev2.Main;
import plugins.lifestealfreev2.customEvents.BannedCustomEvent;
import plugins.lifestealfreev2.customEvents.LoseHeartCustomEvent;
import plugins.lifestealfreev2.data.DataManager;

public class LoseHeartListener implements Listener {
    private Main plugin = Main.getIsntance();

    @SneakyThrows
    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event){
        if (event.getEntity() instanceof Player p) {
            DataManager pData = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, p.getPlayer().getUniqueId());
            if (plugin.getConfig().getBoolean("LoseOnEnviromentalDeath") == true) {
                if (event.getEntity().getKiller() == null) {
                    if (pData.getData1().getHearts() > 2) {
                    Player player = p.getPlayer();
                    boolean banned = pData.getData1().isBanned();
                    int hearts = pData.getData1().getHearts();
                    Bukkit.getServer().getPluginManager().callEvent(new LoseHeartCustomEvent(player, banned, hearts, event.getEntity().getKiller()));
                    }
                    else {
                        boolean banned = true;
                        pData.getData1().setBanned(true);
                        Player player = p.getPlayer();
                        Bukkit.getServer().getPluginManager().callEvent(new BannedCustomEvent(banned, player));
                        LuaCore.save();
                    }
                }
            }
            if (event.getEntity().getKiller() != null) {
                if (pData.getData1().getHearts() > 2) {
                    Player player = p.getPlayer();
                    boolean banned = pData.getData1().isBanned();
                    int hearts = pData.getData1().getHearts();
                    Bukkit.getServer().getPluginManager().callEvent(new LoseHeartCustomEvent(player, banned, hearts, event.getEntity().getKiller()));
                }
                else {
                    boolean banned = true;
                    pData.getData1().setBanned(true);
                    Player player = p.getPlayer();
                    Bukkit.getServer().getPluginManager().callEvent(new BannedCustomEvent(banned, player));
                    LuaCore.save();
                }
            }
        }
    }
}
