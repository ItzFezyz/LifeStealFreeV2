package plugins.lifestealfreev2.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.OfflinePlayerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.selena.libs.iridiumcolorapi.IridiumColorAPI;
import dev.selena.luacore.LuaCore;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import plugins.lifestealfreev2.data.DataManager;
import plugins.lifestealfreev2.items.HeartItem;
import plugins.lifestealfreev2.items.ReviveItem;
import plugins.lifestealfreev2.tools.LuaCoreE;

import java.util.UUID;

public class ACommand {
    private Plugin plugin;
    private static ItemStack heart = HeartItem.getHeart();

    private static ItemStack reviveItem = ReviveItem.getReviveItem();

    public ACommand(Plugin plugin){
        this.plugin = plugin;
    }
    @SneakyThrows
    public void register(){
        new CommandAPICommand("withdraw")
                .withArguments(new IntegerArgument("Amount"))
                .executesPlayer((player, args) -> {
                    if (player.hasPermission("LifeSteal.Heart")){
                        int amount = (int) args.get("Amount");
                        UUID uuid = player.getUniqueId();
                        DataManager data = LuaCoreE.getData(uuid);
                        int health = data.getData1().getHearts();
                        if (amount >= 0) {
                            if (amount*2-health <= -2) {
                                player.getPlayer().sendMessage(IridiumColorAPI.process("&4-" + amount + " Hearts!"));
                                health -= (2 * amount);
                                data.getData1().setHearts(health);
                                player.setMaxHealth(health);
                                heart.setAmount(amount);
                                player.getInventory().addItem(heart);
                                LuaCore.save();
                            }
                        }
                    }
                }).register();
        new CommandAPICommand("sethealth")
                .withArguments(new PlayerArgument("Player"))
                .withArguments(new IntegerArgument("Amount"))
                .executes((sender, args) -> {
                    if (sender.hasPermission("LifeSteal.Sethealth")){
                    int a = (int) args.get("Amount");
                    Player player = (Player) args.get("Player");
                        DataManager vData = LuaCoreE.getData(player.getUniqueId());
                        if (player != null) {
                            if (args.get("Amount") != null) {
                                if (a > 0) {
                                    int health = a * 2;
                                    player.setMaxHealth(health);
                                    vData.getData1().setHearts(health);
                                    LuaCore.save();
                                }
                            }
                        }
                    }
                }).register();
        new CommandAPICommand("item")
                .withShortDescription("Gives the Revive Item.")
                .executesPlayer((player, args) -> {
                    if (player.hasPermission("LifeSteal.Item")) {
                        player.getInventory().addItem(reviveItem);
                    }
                }).register();
        new CommandAPICommand("revive")
                .withArguments(new StringArgument("BannedPlayer"))
                .executes((sender, args) -> {
                    if (sender.hasPermission("LifeSteal.Revive.Player")){
                    OfflinePlayer[] a = Bukkit.getOfflinePlayers();
                        for (OfflinePlayer b : a) {
                            UUID uuid = b.getUniqueId();
                            DataManager pData = LuaCoreE.getData(uuid);
                            if (pData != null) {
                                if (pData.getData1().isBanned()) {
                                    if (pData.getData1().getName() != null) {
                                        if (pData.getData1().getName().equals(args.get("BannedPlayer"))) {
                                        pData.getData1().setBanned(false);
                                        LuaCore.save();
                                        String ReviveMessage = plugin.getConfig().getString("ReviveMessage");
                                        ReviveMessage = ReviveMessage.replace("${reviver}", "${Reviver}");
                                        ReviveMessage = ReviveMessage.replace("${Reviver}", sender.getName());
                                        ReviveMessage = ReviveMessage.replace("${revived}", "${Revived}");
                                        ReviveMessage = ReviveMessage.replace("${Revived}", pData.getData1().getName());
                                        Bukkit.broadcastMessage(IridiumColorAPI.process(ReviveMessage));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }).register();
    }
}
