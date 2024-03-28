package plugins.lifestealfreev2;

import dev.selena.luacore.LuaCore;
import dev.selena.luacore.utils.data.UserDataManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;
import plugins.lifestealfreev2.commands.ACommand;
import plugins.lifestealfreev2.customEvents.BannedCustomEvent;
import plugins.lifestealfreev2.data.DataManager;
import plugins.lifestealfreev2.events.BannedEvent;
import plugins.lifestealfreev2.events.HeartRightClickEvent;
import plugins.lifestealfreev2.events.LoseHeartEvent;
import plugins.lifestealfreev2.events.ReviveRightClickEvent;
import plugins.lifestealfreev2.items.HeartItem;
import plugins.lifestealfreev2.items.ReviveItem;
import plugins.lifestealfreev2.listeners.HeartRightClickListener;
import plugins.lifestealfreev2.listeners.LoseHeartListener;
import plugins.lifestealfreev2.listeners.ReviveRightClickListener;

import java.util.List;

public final class Main extends JavaPlugin implements Listener {

    @Getter
    private static Main isntance;

    @Override
    public void onEnable() {
        isntance = this;
        registerRecipies();
        saveDefaultConfig();
        LuaCore.setPlugin(this);
        LuaCore.setUserDataManager(new UserDataManager("PlayerData"));
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new LoseHeartListener(), this);
        getServer().getPluginManager().registerEvents(new LoseHeartEvent(), this);
        getServer().getPluginManager().registerEvents(new BannedEvent(), this);
        getServer().getPluginManager().registerEvents(new HeartRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new HeartRightClickEvent(), this);
        getServer().getPluginManager().registerEvents(new ReviveRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new ReviveRightClickEvent(), this);
        ACommand acommand = new ACommand(this);
        acommand.register();
    }
    @SneakyThrows
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        DataManager pdata = LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, event.getPlayer().getUniqueId());
        pdata.getData1().setName(event.getPlayer().getName());
        pdata.getData1().setSkin(event.getPlayer().getPlayerProfile().getTextures().getSkin());
        event.getPlayer().setMaxHealth(pdata.getData1().getHearts());
        if (pdata.getData1().isBanned()){
            boolean banned = true;
            Player player = event.getPlayer();
            getServer().getPluginManager().callEvent(new BannedCustomEvent(banned, player));
        }
        LuaCore.save();
    }
    @Override
    public void onDisable() {
        LuaCore.save();
        // Plugin shutdown logic
    }
    public void registerRecipies(){
        List<String> heartRecipe = isntance.getConfig().getStringList("HeartRecipe");
        ShapedRecipe Heart = new ShapedRecipe(new NamespacedKey(this, "heart"), HeartItem.getHeart());
        List<String> reviveRecipe = isntance.getConfig().getStringList("ReviveRecipe");
        ShapedRecipe Revive = new ShapedRecipe(new NamespacedKey(this, "revive"), ReviveItem.getReviveItem());
        Material X = Material.matchMaterial(heartRecipe.get(0));
        Material Y = Material.matchMaterial(heartRecipe.get(1));
        Material Z = Material.matchMaterial(heartRecipe.get(2));
        Material H = Material.matchMaterial(heartRecipe.get(3));
        Material M = Material.matchMaterial(heartRecipe.get(4));
        Material L = Material.matchMaterial(heartRecipe.get(5));
        Material O = Material.matchMaterial(heartRecipe.get(6));
        Material P = Material.matchMaterial(heartRecipe.get(7));
        Material Q = Material.matchMaterial(heartRecipe.get(8));
        Heart.shape("XYZ", "HML", "OPQ");
        // group 1
        Heart.setIngredient('X', X);
        Heart.setIngredient('Y', Y);
        Heart.setIngredient('Z', Z);
        // group 2
        Heart.setIngredient('H', H);
        Heart.setIngredient('M', M);
        Heart.setIngredient('L', L);
        // group 3
        Heart.setIngredient('O', O);
        Heart.setIngredient('P', P);
        Heart.setIngredient('Q', Q);
        // Adding to a recipe!
        Heart.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(Heart);
        Material A = Material.matchMaterial(reviveRecipe.get(0));
        Material B = Material.matchMaterial(reviveRecipe.get(1));
        Material C = Material.matchMaterial(reviveRecipe.get(2));
        Material D = Material.matchMaterial(reviveRecipe.get(3));
        Material E = Material.matchMaterial(reviveRecipe.get(4));
        Material F = Material.matchMaterial(reviveRecipe.get(5));
        Material G = Material.matchMaterial(reviveRecipe.get(6));
        Material T = Material.matchMaterial(reviveRecipe.get(7));
        Material K = Material.matchMaterial(reviveRecipe.get(8));
        Revive.shape("XYZ", "HML", "OPQ");
        // group 1
        Revive.setIngredient('X', A);
        Revive.setIngredient('Y', B);
        Revive.setIngredient('Z', C);
        // group 2
        Revive.setIngredient('H', D);
        Revive.setIngredient('M', E);
        Revive.setIngredient('L', F);
        // group 3
        Revive.setIngredient('O', G);
        Revive.setIngredient('P', T);
        Revive.setIngredient('Q', K);
        // Adding to a recipe!
        Revive.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(Revive);
    }
}