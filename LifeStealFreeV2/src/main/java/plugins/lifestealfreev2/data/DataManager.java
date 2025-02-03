package plugins.lifestealfreev2.data;

import dev.selena.luacore.utils.data.UserFolder;
import lombok.Getter;

import java.util.UUID;

public class DataManager extends UserFolder {

    @Getter
    private PlayerData playerData;

    public DataManager(UUID uuid) {
        super(uuid);
        playerData = loadData(PlayerData.class, "Data.json");
    }
}
