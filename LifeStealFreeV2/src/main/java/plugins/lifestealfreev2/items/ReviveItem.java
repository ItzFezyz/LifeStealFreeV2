package plugins.lifestealfreev2.items;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import dev.selena.luacore.utils.items.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;

public class ReviveItem {
    private static Main plugin = Main.getIsntance();
    @Getter @Setter
    private static ItemStack reviveItem = new ItemBuilder(Material.matchMaterial(plugin.getConfig().getString("ReviveMaterial"))).setTitle(IridiumColorAPI.process(plugin.getConfig().getString("ReviveName"))).setLore(plugin.getConfig().getString("ReviveLore")).build();
}
