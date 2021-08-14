package dygoat.blockrandomizer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.ArrayList;
import java.util.List;

public class ChunkListener implements Listener {
    BlockRandomizer pl;

    public ChunkListener(BlockRandomizer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.pl = plugin;
    }

    @EventHandler
    public void onDiscover(ChunkLoadEvent e) {
        if (pl.replaced.size() != 0) {
            Chunk c = e.getChunk();
            Integer changeInd = pl.getChunkChangePos(c.getX(),c.getZ());
            if (changeInd != null) {
                pl.doRandomizers(changeInd,c);
            }
            else {
                pl.doRandomizers(0,c);
            }
            pl.setChunkChangePos(c.getX(),c.getZ(),pl.replaced.size());
        }
    }
}
