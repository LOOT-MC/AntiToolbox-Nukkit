package lootmc.antitoolbox;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import lootmc.antitoolbox.event.ToolboxDetectedEvent;

public class ToolboxListener implements Listener {

    private final AntiToolbox plugin;

    ToolboxListener(AntiToolbox plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void handleLogin(PlayerLoginEvent ev) {
        Player player = ev.getPlayer();
        if (AntiToolbox.testToolbox(player)) {
            ToolboxDetectedEvent event = new ToolboxDetectedEvent(player);
            this.plugin.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.plugin.applyDefaultAction(player);
            }
        }
    }

}
