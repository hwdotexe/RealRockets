package com.hadenwatne.realrockets.listeners;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.ui.IBlockUI;
import com.hadenwatne.realrockets.ui.OreRefinery;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {
    private RealRockets plugin;

    public BlockListener(RealRockets c){
        plugin = c;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent e){
        if(!e.isCancelled()) {
            // TODO add other types
            if (e.getItemInHand().isSimilar(plugin.getBlocks().getOreRefinery())) {
                IBlockUI b = new OreRefinery();

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e){
        if(!e.isCancelled()) {
            IBlockUI b = plugin.getMapper().getBlockMap().get(e.getBlock().getLocation());

            if(b != null){
                b.unregister();
                plugin.getMapper().getBlockMap().remove(e.getBlock().getLocation());
            }
        }
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            IBlockUI b = plugin.getMapper().getBlockMap().get(e.getClickedBlock().getLocation());

            if(b != null){
                e.setCancelled(true);
                e.getPlayer().openInventory(b.getGUI());
                System.out.println("Player clicked a GUI");
            }
        }
    }
}
