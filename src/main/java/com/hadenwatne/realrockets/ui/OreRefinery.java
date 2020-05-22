package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.RealRockets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OreRefinery implements IBlockUI {
    private Inventory gui;
    private RealRockets plugin;
    private boolean isRefining;
    private boolean active;

    public OreRefinery(RealRockets p){
        plugin = p;
        isRefining = false;
        active = true;
        gui = Bukkit.createInventory(null, InventoryType.DISPENSER, ChatColor.translateAlternateColorCodes('&', "&7&lOre Refinery"));

        gui.setItem(0, RocketBlocks.getUIBlock());
        gui.setItem(1, RocketBlocks.getUIBlock());
        gui.setItem(2, RocketBlocks.getUIBlock());
    }

    @Override
    public Inventory getGUI() {
        return gui;
    }

    @EventHandler
    public void onClickGUI(InventoryClickEvent e){
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(gui)){
            if(e.getCurrentItem().isSimilar(RocketBlocks.getUIBlock()))
                e.setCancelled(true);

            if(!isRefining) {
                isRefining = true;

                if(gui.firstEmpty() > -1)
                    new OreRefineryTask(this).runTaskTimer(plugin, 20, 20);
            }
        }
    }

    @Override
    public String getType() {
        return "OreRefinery";
    }

    @Override
    public void unregister() {
        active = false;
        InventoryClickEvent.getHandlerList().unregister(this);
    }

    public void setRefining(boolean r){
        isRefining = r;
    }

    public boolean isActive(){
        return active;
    }

    public void playTick(){
        for(HumanEntity he : gui.getViewers()){
            Player p = (Player)he;

            p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 3f, 1f);
        }
    }

    public void playFinish(){
        for(HumanEntity he : gui.getViewers()){
            Player p = (Player)he;

            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3f, 1f);
        }
    }
}