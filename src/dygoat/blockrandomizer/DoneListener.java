package dygoat.blockrandomizer;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class DoneListener implements Listener {
    BlockRandomizer pl;

    public DoneListener(BlockRandomizer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.pl = plugin;
    }

    @EventHandler
    public void onWin(PlayerAdvancementDoneEvent e) {
        NamespacedKey key = e.getAdvancement().getKey();
        if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("end/kill_dragon")) {
            Bukkit.broadcastMessage("WHAT HOWD YOU BEAT THE GAME LIKE THAT WHAT NERDS! >:[");
            pl.done = true;
        }
    }
}
