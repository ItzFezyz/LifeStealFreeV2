package plugins.lifestealfreev2.data;

import dev.selena.libs.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

public class PlayerData {
    @Getter @Setter @Expose
    private String name = null;
    @Getter @Setter @Expose
    private int Hearts = 20;
    @Getter @Setter @Expose
    private boolean Banned = false;
    @Getter @Setter @Expose
    private String Prefix = null;
    @Getter @Setter @Expose
    private URL Skin = null;
}
