package plugins.lifestealfreev2.tools;

import org.bukkit.plugin.Plugin;
import plugins.lifestealfreev2.Main;

public class testindent {
    public static Main plugin = Main.getIsntance();
    private String name;
    public testindent(String hi){
        this.name = hi;
    }
    public testindent(){
        this(null);
    }
    public testindent setStringName(String name){
        this.name = name;
        return this;
    }
    public String build(){
        return name;
    }
}
