package dygoat.blockrandomizer;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class BlockRandomizer extends JavaPlugin {
    public boolean done = false;
    private int tickNum = 1800;
    private static List<Material> ms;
    private static List<LootTable> loots;
    public List<Material> replaced;
    public List<Material> placed;
    private Map<Point, Integer> changePoint = new HashMap<>();
    DoneListener dl;
    ChunkListener cl;

    static {
        loots = new ArrayList<>();
        loots.add(Bukkit.getLootTable(LootTables.ABANDONED_MINESHAFT.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.BASTION_BRIDGE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.BASTION_HOGLIN_STABLE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.BASTION_OTHER.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.BASTION_TREASURE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.BURIED_TREASURE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.DESERT_PYRAMID.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.END_CITY_TREASURE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.WOODLAND_MANSION.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.IGLOO_CHEST.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.JUNGLE_TEMPLE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.NETHER_BRIDGE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.PILLAGER_OUTPOST.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.RUINED_PORTAL.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.SHIPWRECK_SUPPLY.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.SHIPWRECK_TREASURE.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.SPAWN_BONUS_CHEST.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.STRONGHOLD_CORRIDOR.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.STRONGHOLD_CROSSING.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.STRONGHOLD_LIBRARY.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.SIMPLE_DUNGEON.getKey()));
        loots.add(Bukkit.getLootTable(LootTables.VILLAGE_WEAPONSMITH.getKey()));
        ms = new ArrayList<>();
        ms.add(Material.STONE);
        ms.add(Material.GRASS_BLOCK);
        ms.add(Material.DIRT);
        ms.add(Material.WATER);
        ms.add(Material.LAVA);
        ms.add(Material.SAND);
        ms.add(Material.GRAVEL);
        ms.add(Material.GOLD_BLOCK);
        ms.add(Material.GOLD_ORE);
        ms.add(Material.IRON_BLOCK);
        ms.add(Material.IRON_ORE);
        ms.add(Material.COAL_BLOCK);
        ms.add(Material.COAL_ORE);
        ms.add(Material.ACACIA_LOG);
        ms.add(Material.BIRCH_LOG);
        ms.add(Material.DARK_OAK_LOG);
        ms.add(Material.JUNGLE_LOG);
        ms.add(Material.OAK_LOG);
        ms.add(Material.SPRUCE_LOG);
        ms.add(Material.ACACIA_LEAVES);
        ms.add(Material.BIRCH_LEAVES);
        ms.add(Material.DARK_OAK_LEAVES);
        ms.add(Material.JUNGLE_LEAVES);
        ms.add(Material.OAK_LEAVES);
        ms.add(Material.SPRUCE_LEAVES);
        ms.add(Material.LAPIS_BLOCK);
        ms.add(Material.LAPIS_LAZULI);
        ms.add(Material.SANDSTONE);
        ms.add(Material.GRASS);
        ms.add(Material.TALL_GRASS);
        ms.add(Material.FERN);
        ms.add(Material.DEAD_BUSH);
        ms.add(Material.VINE);
        ms.add(Material.DANDELION);
        ms.add(Material.POPPY);
        ms.add(Material.BROWN_MUSHROOM);
        ms.add(Material.RED_MUSHROOM);
        ms.add(Material.DIAMOND_BLOCK);
        ms.add(Material.DIAMOND_ORE);
        ms.add(Material.REDSTONE_BLOCK);
        ms.add(Material.REDSTONE_ORE);
        ms.add(Material.SNOW);
        ms.add(Material.SNOW_BLOCK);
        ms.add(Material.ICE);
        ms.add(Material.PACKED_ICE);
        ms.add(Material.BLUE_ICE);
        ms.add(Material.CACTUS);
        ms.add(Material.CLAY);
        ms.add(Material.SUGAR_CANE);
        ms.add(Material.PUMPKIN);
        ms.add(Material.MELON);
        ms.add(Material.COCOA);
        ms.add(Material.BROWN_MUSHROOM_BLOCK);
        ms.add(Material.RED_MUSHROOM_BLOCK);
        ms.add(Material.LILY_PAD);
        ms.add(Material.BAMBOO);
        ms.add(Material.TALL_SEAGRASS);
        ms.add(Material.SEAGRASS);
        ms.add(Material.KELP_PLANT);
        ms.add(Material.COBBLESTONE);
        ms.add(Material.DEEPSLATE);
        ms.add(Material.COBBLED_DEEPSLATE);
        ms.add(Material.DEEPSLATE_COAL_ORE);
        ms.add(Material.DEEPSLATE_IRON_ORE);
        ms.add(Material.DEEPSLATE_GOLD_ORE);
        ms.add(Material.DEEPSLATE_DIAMOND_ORE);
        ms.add(Material.DEEPSLATE_LAPIS_ORE);
        ms.add(Material.DEEPSLATE_REDSTONE_ORE);
        ms.add(Material.OBSIDIAN);
        ms.add(Material.FIRE);
        ms.add(Material.ACACIA_PLANKS);
        ms.add(Material.BIRCH_PLANKS);
        ms.add(Material.DARK_OAK_PLANKS);
        ms.add(Material.JUNGLE_PLANKS);
        ms.add(Material.OAK_PLANKS);
        ms.add(Material.SPRUCE_PLANKS);
        ms.add(Material.TORCH);
        ms.add(Material.CRAFTING_TABLE);
        ms.add(Material.LOOM);
        ms.add(Material.FURNACE);
        ms.add(Material.COBWEB);
        ms.add(Material.BOOKSHELF);
        ms.add(Material.CHEST);
        ms.add(Material.ENDER_CHEST);
        ms.add(Material.FARMLAND);
        ms.add(Material.STONE_BRICKS);
        ms.add(Material.IRON_BARS);
        ms.add(Material.BEDROCK);
        ms.add(Material.NETHERRACK);
        ms.add(Material.SOUL_SAND);
        ms.add(Material.GLOWSTONE);
        ms.add(Material.NETHER_GOLD_ORE);
        ms.add(Material.NETHER_QUARTZ_ORE);
        ms.add(Material.QUARTZ_BLOCK);
        ms.add(Material.WARPED_HYPHAE);
        ms.add(Material.WARPED_PLANKS);
        ms.add(Material.WARPED_NYLIUM);
        ms.add(Material.CRIMSON_HYPHAE);
        ms.add(Material.CRIMSON_PLANKS);
        ms.add(Material.CRIMSON_NYLIUM);
        ms.add(Material.NETHER_WART_BLOCK);
        ms.add(Material.WARPED_WART_BLOCK);
        ms.add(Material.BLACKSTONE);
        ms.add(Material.BASALT);
        ms.add(Material.MAGMA_BLOCK);
        ms.add(Material.END_STONE);
        ms.add(Material.DIORITE);
        ms.add(Material.GRANITE);
        ms.add(Material.ANDESITE);
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
                                if (toPlace == Material.CHEST) {
                                    ((Lootable) c.getBlock(x, y, z).getBlockData()).setLootTable(loots.get((int) (Math.random()*22)));
                                }
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
                            if (placed.get(i) == Material.CHEST) {
                                ((Lootable) c.getBlock(x, y, z).getBlockData()).setLootTable(loots.get((int) (Math.random()*22)));
                            }
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
