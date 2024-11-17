package plugins.lifestealfreev2.data;

import dev.selena.luacore.utils.data.UserFolder;
import lombok.Getter;

import java.util.UUID;

public class DataManager extends UserFolder {

    @Getter
    private PlayerData data1;

    public DataManager(UUID uuid) {
        super(uuid);
        data1 = loadData(PlayerData.class, "Data.json");
    }
}
