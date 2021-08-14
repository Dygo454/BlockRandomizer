package dygoat.blockrandomizer;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;
import java.util.stream.Collectors;

public class BlockRandomizer extends JavaPlugin {
    public boolean done = false;
    private int tickNum = 5400;
    private static List<Material> ms;
    public List<Material> replaced;
    public List<Material> placed;
    private Map<Point, Integer> changePoint = new HashMap<>();
    public boolean isProcessing = false;
    DoneListener dl;
    ChunkListener cl;

    static {
        ms = Arrays.stream(Material.values()).filter(Material::isBlock).collect(Collectors.toList());
        ms.remove(Material.END_PORTAL_FRAME);
        ms.remove(Material.BARRIER);
        ms.remove(Material.COMMAND_BLOCK);
        ms.remove(Material.SPAWNER);
        ms.remove(Material.END_PORTAL);
    }

    @Override
    public void onEnable() {
        dl = new DoneListener(this);
        cl = new ChunkListener(this);
        replaced = new ArrayList<Material>();
        placed = new ArrayList<Material>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start") && sender.hasPermission("BlockRandomizer.start")) {
            Bukkit.broadcastMessage("Started!");
            scheduleWarning();
        }
        else {
            sender.sendMessage(ChatColor.RED+"Error: not enough permission!");
        }
        return true;
    }

    public void scheduleWarning() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (!done) {
                    Bukkit.broadcastMessage("30 SECONDS GET TF READY!");
                    countDown(10, true);
                }
            }
        }, tickNum);
    }

    public void countDown(int num, boolean isTen) {
        if (num == 0) {
            doRandomizer();
            return;
        }
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (!done) {
                    Bukkit.broadcastMessage(num + " SECONDS LEFT!");
                    countDown(num - 1, false);
                }
            }
        }, (isTen ? 400 : 20));
    }

    public void doRandomizer() {
        Material toReplace = null;
        Material toPlace = null;
        while (toReplace == null || toReplace.isAir() || !toReplace.isBlock()) {
            int num = (int) (Math.random() * ms.size());
            toReplace = ms.get(num);
        }
        while (toPlace == null || toPlace.isAir() || !toPlace.isBlock()) {
            int num = (int) (Math.random() * ms.size());
            toPlace = ms.get(num);
        }
        Bukkit.broadcastMessage("Now replacing "+toReplace.name()+" with "+toPlace.name()+"!");
        replaced.add(toReplace);
        placed.add(toPlace);
        for (World w : Bukkit.getServer().getWorlds()) {
            for (Chunk c : w.getLoadedChunks()) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = w.getMinHeight(); y < w.getMaxHeight(); y++) {
                            if (c.getBlock(x,y,z).getBlockData().getMaterial().equals(toReplace)) {
                                c.getBlock(x,y,z).setType(toPlace,false);
                            }
                        }
                    }
                }
                setChunkChangePos(c.getX(),c.getZ(),replaced.size());
            }
        }
        scheduleWarning();
    }

    public void doRandomizers(int index, Chunk c) {
        for (int i = index; i < replaced.size(); i++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = c.getWorld().getMinHeight(); y < c.getWorld().getMaxHeight(); y++) {
                        if (c.getBlock(x, y, z).getBlockData().getMaterial().equals(replaced.get(i))) {
                            c.getBlock(x, y, z).setType(placed.get(i),false);
                        }
                    }
                }
            }
        }
    }

    public void setChunkChangePos(int x, int z, int pos) {
        changePoint.put(new Point(x,z), pos);
    }

    public Integer getChunkChangePos(int x, int z) {
        return changePoint.get(new Point(x,z));
    }
}
