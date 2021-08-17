package dygoat.blockrandomizer;

import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
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
        loots.add(LootTables.ABANDONED_MINESHAFT.getLootTable());
        loots.add(LootTables.BASTION_BRIDGE.getLootTable());
        loots.add(LootTables.BASTION_HOGLIN_STABLE.getLootTable());
        loots.add(LootTables.BASTION_OTHER.getLootTable());
        loots.add(LootTables.BASTION_TREASURE.getLootTable());
        loots.add(LootTables.BURIED_TREASURE.getLootTable());
        loots.add(LootTables.DESERT_PYRAMID.getLootTable());
        loots.add(LootTables.END_CITY_TREASURE.getLootTable());
        loots.add(LootTables.WOODLAND_MANSION.getLootTable());
        loots.add(LootTables.IGLOO_CHEST.getLootTable());
        loots.add(LootTables.JUNGLE_TEMPLE.getLootTable());
        loots.add(LootTables.NETHER_BRIDGE.getLootTable());
        loots.add(LootTables.PILLAGER_OUTPOST.getLootTable());
        loots.add(LootTables.RUINED_PORTAL.getLootTable());
        loots.add(LootTables.SHIPWRECK_SUPPLY.getLootTable());
        loots.add(LootTables.SHIPWRECK_TREASURE.getLootTable());
        loots.add(LootTables.SPAWN_BONUS_CHEST.getLootTable());
        loots.add(LootTables.STRONGHOLD_CORRIDOR.getLootTable());
        loots.add(LootTables.STRONGHOLD_CROSSING.getLootTable());
        loots.add(LootTables.STRONGHOLD_LIBRARY.getLootTable());
        loots.add(LootTables.SIMPLE_DUNGEON.getLootTable());
        loots.add(LootTables.VILLAGE_WEAPONSMITH.getLootTable());
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
        ms.add(Material.SOUL_SOIL);
        ms.add(Material.SOUL_FIRE);
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
        Material toReplace = Material.GRASS;
        Material toPlace = Material.CHEST;
        while (toReplace == null || toReplace.isAir() || !toReplace.isBlock()) {
            int num = (int) (Math.random() * ms.size());
            toReplace = ms.get(num);
        }
        boolean chestCheck = true;
        while (toPlace == null || toPlace.isAir() || !toPlace.isBlock() || !chestCheck) {
            chestCheck = true;
            int num = (int) (Math.random() * ms.size());
            toPlace = ms.get(num);
            if (toPlace == Material.CHEST) {
                switch (toReplace) {
                    case GRASS_BLOCK:
                    case DIRT:
                    case STONE:
                    case NETHERRACK:
                    case WATER:
                    case LAVA:
                    case SOUL_SAND:
                    case SOUL_SOIL:
                    case SAND:
                    case SANDSTONE:
                        chestCheck = false;
                }
            }
        }
        Bukkit.broadcastMessage("Now replacing "+toReplace.name()+" with "+toPlace.name()+"!");
        replaced.add(toReplace);
        placed.add(toPlace);
        int count = 0;
        for (World w : Bukkit.getServer().getWorlds()) {
            for (Chunk c : w.getLoadedChunks()) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = w.getMinHeight(); y < w.getMaxHeight(); y++) {
                            if (c.getBlock(x,y,z).getBlockData().getMaterial().equals(toReplace)) {
                                c.getBlock(x,y,z).setType(toPlace,false);
                                if (toPlace == Material.CHEST) {
                                    Chest chest = (Chest)c.getBlock(x,y,z).getState();
                                    chest.setLootTable(loots.get((int) (Math.random()*22)));
                                    chest.update();
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
                                c.getBlock(x,y,z).setType(placed.get(i),false);
                                if (placed.get(i) == Material.CHEST) {
                                    Chest chest = (Chest)c.getBlock(x,y,z).getState();
                                    chest.setLootTable(loots.get((int) (Math.random()*22)));
                                    chest.update();
                                }
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
