package plugins.lifestealfreev2.items;

import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import dev.selena.luacore.utils.items.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import plugins.lifestealfreev2.Main;


public class HeartItem {
    private static Main plugin = Main.getIsntance();
    public static FileConfiguration config = plugin.getConfig();
    public static String material = config.getString("HeartMaterial");

    public static String name = config.getString("HeartName");
    public static String lore = config.getString("HeartLore");

    @Getter @Setter
    private static ItemStack Heart = new ItemBuilder(Material.matchMaterial(material.toUpperCase())).setTitle(IridiumColorAPI.process(name)).setLore(IridiumColorAPI.process(lore.toString())).build();
}
