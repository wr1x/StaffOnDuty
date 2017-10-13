package me.wrix.staffonduty.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.Dye;

import me.wrix.staffonduty.StaffOnDuty;
import me.wrix.staffonduty.utils.StaffMode;
import me.wrix.staffonduty.utils.User;
import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getName().equalsIgnoreCase("wrix")) {
			p.sendMessage(ChatColor.BLUE+"This server is running '"+ChatColor.AQUA+"StaffOnDuty"+ChatColor.BLUE+"' by you "+ChatColor.RED+"<3");
		}
		if(p.hasPermission("staffonduty.all")) {
			User u = new User(p);
			StaffOnDuty.USERS.put(p.getUniqueId(), u);
			u.setInStaffMode(false);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("staffonduty.all")) {
			StaffOnDuty.USERS.remove(p.getUniqueId());
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player){
			Player p = (Player)e.getEntity();
			if(p.hasPermission("staffonduty.all")) {
				if(StaffOnDuty.getUser(p).isInStaffMode()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractAtEntityEvent e) {
		if(e.getRightClicked() instanceof Player) {
			Player p = (Player)e.getPlayer();
	
			if(!e.getPlayer().getInventory().getItemInHand().hasItemMeta() && !e.getPlayer().getInventory().getItemInHand().getItemMeta().hasDisplayName() && !StaffOnDuty.getUser(p).isInStaffMode()) {
				return;
			}else {
				Player target = (Player) e.getRightClicked();
				Inventory inv = target.getInventory();
            
    			if(e.getPlayer().getInventory().getItemInHand().getType() == Material.BOOK && StaffOnDuty.getUser(p).isInStaffMode()) {
    				e.getPlayer().openInventory(inv);
    			}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		if (!StaffOnDuty.getUser(p).isInStaffMode()) {
			return;
		}
		
		if (!p.getInventory().getItemInHand().hasItemMeta() && !p.getInventory().getItemInHand().getItemMeta().hasDisplayName()) {
			return;
		}
		
		e.setCancelled(true);
		
		if(e.getItem().getType() == Material.BLAZE_ROD) {
			StaffMode.randomTp(p);
		}
		
		if(e.getItem().getType() == Material.FEATHER) {
			StaffMode.fly(p);
		}
		Dye dye = new Dye();
		if(e.getItem().getType() == dye.getItemType()) {
			StaffMode.vanish(p);
		}
		
	}

}
