package lootmc.antitoolbox;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.LoginChainData;

import java.util.ArrayList;
import java.util.List;

public class AntiToolbox extends PluginBase {

    private List<String> onToolboxCmds;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Config config = this.getConfig();
        this.onToolboxCmds = config.get("on-toolbox-cmds", new ArrayList<>());

        this.getServer().getPluginManager().registerEvents(new ToolboxListener(this), this);
    }

    public void applyDefaultAction(Player player) {
        for (String cmd : this.onToolboxCmds) {
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), cmd.replace("%player%", player.getName()));
        }
    }

    public static boolean testToolbox(Player player) {
        LoginChainData clientData = player.getLoginChainData();
        if (clientData.getDeviceOS() == 1) { // is Android
            String[] modelSplit = clientData.getDeviceModel().split(" ");
            if (modelSplit.length != 0) {
                return !modelSplit[0].equals(modelSplit[0].toUpperCase());
            }
        }
        return false;
    }

}
