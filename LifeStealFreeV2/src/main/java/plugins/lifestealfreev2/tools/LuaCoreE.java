package plugins.lifestealfreev2.tools;

import dev.selena.luacore.LuaCore;
import lombok.Getter;
import lombok.SneakyThrows;
import plugins.lifestealfreev2.data.DataManager;

import java.util.UUID;

public class LuaCoreE {
    @SneakyThrows
    public static DataManager getData(UUID uuid){
        return LuaCore.getUserDataManager().getUserDataFolder(DataManager.class, uuid);
    }
}
