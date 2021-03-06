package com.hadenwatne.realrockets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hadenwatne.realrockets.listeners.BlockListener;
import com.hadenwatne.realrockets.storage.RocketMan;
import com.hadenwatne.realrockets.storage.UIMapper;
import com.hadenwatne.realrockets.ui.RocketBlocks;
import com.hadenwatne.realrockets.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class RealRockets extends JavaPlugin {
    private Gson gson;
    private UIMapper mapper;
    private RocketMan rocketMan;

    public void onEnable(){
        RandomUtil.init();
        GsonBuilder gb = new GsonBuilder();
        gson = gb.excludeFieldsWithoutExposeAnnotation().create();
        rocketMan = new RocketMan(this);
        mapper = new UIMapper(this);
        RRCommand rrc = new RRCommand(this);

        this.getCommand("rr").setExecutor(rrc);
        this.getCommand("realrockets").setExecutor(rrc);

        Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);

        registerRecipes();
    }

    public void onDisable(){
        mapper.serializeData();

        this.saveConfig();
    }

    public RocketMan getRocketMan(){
        return rocketMan;
    }

    public UIMapper getMapper(){
        return mapper;
    }

    public Gson getGson(){
        return gson;
    }

    private void registerRecipes(){
        //OreRefinery
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_orerefinery");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getOreRefinery());
            sr.shape("CFC", "CLC");
            sr.setIngredient('C', Material.COBBLESTONE);
            sr.setIngredient('F', Material.FURNACE);
            sr.setIngredient('L', Material.LAVA_BUCKET);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }

        //BiodieselReactor
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_biodieselreactor");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getBiodieselReactor());
            sr.shape("COC", "CLC");
            sr.setIngredient('C', Material.COBBLESTONE);
            sr.setIngredient('O', Material.COMPOSTER);
            sr.setIngredient('L', Material.LAVA_BUCKET);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }

        //WarheadForge
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_warheadforge");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getWarheadForge());
            sr.shape("CAC", "CLC");
            sr.setIngredient('C', Material.COBBLESTONE);
            sr.setIngredient('A', Material.ANVIL);
            sr.setIngredient('L', Material.LAVA_BUCKET);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }

        //RocketFoundry
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_rocketfoundry");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getRocketFoundry());
            sr.shape("III", "IDI", "III");
            sr.setIngredient('I', Material.IRON_BLOCK);
            sr.setIngredient('D', Material.DISPENSER);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }

        //Rocket Hull
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_rockethull");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getRocketHull());
            sr.shape("IAI", "IAI", "IAI");
            sr.setIngredient('I', Material.IRON_BLOCK);
            sr.setIngredient('A', Material.AIR);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }

        //Targeting Computer
        {
            NamespacedKey key = new NamespacedKey(this, "realrockets_targetingcomputer");
            ShapedRecipe sr = new ShapedRecipe(key, RocketBlocks.getTargetingComputer());
            sr.shape("RRR", "TDT", "GGG");
            sr.setIngredient('R', Material.REDSTONE);
            sr.setIngredient('T', Material.REDSTONE_TORCH);
            sr.setIngredient('D', Material.DAYLIGHT_DETECTOR);
            sr.setIngredient('G', Material.GOLD_INGOT);
            sr.setGroup("realrockets");
            Bukkit.addRecipe(sr);
        }
    }
}