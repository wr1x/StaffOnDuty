package me.wrix.staffonduty.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import me.wrix.staffonduty.StaffOnDuty;
import net.md_5.bungee.api.ChatColor;

public class StaffMode {	
	private static List<UUID> vanish = new ArrayList<UUID>();
	
	public static void addToStaffMode(Player p) {
		p.getInventory().clear();		
			ItemStack worldEdit = new ItemStack(Material.WOOD_AXE, 1);
			ItemMeta worldMeta = worldEdit.getItemMeta();
			worldMeta.setDisplayName(ChatColor.GREEN+"WorldEdit");
			worldEdit.setItemMeta(worldMeta);
			p.getInventory().setItem(0, worldEdit);
		
			ItemStack mover = new ItemStack(Material.COMPASS, 1);
			ItemMeta moverMeta = mover.getItemMeta();
			moverMeta.setDisplayName(ChatColor.BLUE + "Mover");
			mover.setItemMeta(moverMeta);
			p.getInventory().setItem(1, mover);
		
			ItemStack inspect = new ItemStack(Material.BOOK, 1);
			ItemMeta inspectMeta = inspect.getItemMeta();
			inspectMeta.setDisplayName(ChatColor.GOLD+"Inspect");
			inspect.setItemMeta(inspectMeta);
			p.getInventory().setItem(3, inspect);
		
			ItemStack teleport = new ItemStack(Material.BLAZE_ROD, 1);
			ItemMeta teleportMeta = teleport.getItemMeta();
			teleportMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Random Teleport");
			teleport.setItemMeta(teleportMeta);
			p.getInventory().setItem(5, teleport);
		
			ItemStack fly = new ItemStack(Material.FEATHER, 1);
			ItemMeta flyMeta = fly.getItemMeta();
			flyMeta.setDisplayName(ChatColor.AQUA+"Fly");
			fly.setItemMeta(flyMeta);
			p.getInventory().setItem(7, fly);
	
			Dye dye = new Dye();
			dye.setColor(DyeColor.GRAY);
		
			ItemStack testStack = dye.toItemStack(1);
			ItemMeta testMeta = testStack.getItemMeta();
			testMeta.setDisplayName(ChatColor.GRAY+"Vanish (OFF)");
			testStack.setItemMeta(testMeta);
		
			p.getInventory().setItem(8, testStack);
		
			StaffOnDuty.getUser(p).setInStaffMode(true);
			
			p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.GREEN + "Staff Mode "+ChatColor.DARK_GREEN+"Enabled"+ChatColor.GREEN+"!");
	}
	
	public static void removeStaffMode(Player p) {
		p.getInventory().clear();
		StaffOnDuty.getUser(p).setInStaffMode(false);
		p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.RED + "Staff Mode "+ChatColor.DARK_RED+"Disabled"+ChatColor.RED+"!");
	}
	
	public static boolean isVanish(Player p) {
		if(vanish.contains(p.getUniqueId())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void randomTp(Player p) {
		if(StaffOnDuty.getUser(p).isInStaffMode()) {
			Random r = new Random();
			int randomPlayerNumber = r.nextInt(Bukkit.getOnlinePlayers().size());
			
			Player target = (Player)Bukkit.getOnlinePlayers().toArray()[randomPlayerNumber];
			p.teleport(target);
			p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.GREEN + "Teleported to "+ChatColor.DARK_GREEN+target.getName()+ChatColor.GREEN+"!");
		}
	}
	
	public static void fly(Player p) {
		if(StaffOnDuty.getUser(p).isInStaffMode()) {
			if(!p.getAllowFlight()) {
				p.setAllowFlight(true);
				p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.GREEN + "Fly "+ChatColor.DARK_GREEN+"Enabled"+ChatColor.GREEN+"!");
			}else {
				p.setAllowFlight(false);
				p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.RED + "Fly "+ChatColor.DARK_RED+"Disabled"+ChatColor.RED+"!");
			}
		}
	}
	
	public static void vanish(Player p) {
		if(StaffOnDuty.getUser(p).isInStaffMode()) {
			if(!vanish.contains(p.getUniqueId())) {
				vanish.add(p.getUniqueId());
				p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.GREEN + "Vanish "+ChatColor.DARK_GREEN+"Enabled"+ChatColor.GREEN+"!");
				
				
				Dye dye = new Dye();
				dye.setColor(DyeColor.LIME);
			
				ItemStack testStack = dye.toItemStack(1);
				ItemMeta testMeta = testStack.getItemMeta();
				testMeta.setDisplayName(ChatColor.GREEN+"Vanish (ON)");
				testStack.setItemMeta(testMeta);
			
				p.getInventory().setItem(8, testStack);
			}else {
				vanish.remove(p.getUniqueId());
				p.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.BLUE+"Staff Mode"+ChatColor.DARK_GRAY+"] "+ChatColor.RED + "Vanish "+ChatColor.DARK_RED+"Disabled"+ChatColor.RED+"!");
				
				Dye dye = new Dye();
				dye.setColor(DyeColor.GRAY);
			
				ItemStack testStack = dye.toItemStack(1);
				ItemMeta testMeta = testStack.getItemMeta();
				testMeta.setDisplayName(ChatColor.GRAY+"Vanish (OFF)");
				testStack.setItemMeta(testMeta);
			
				p.getInventory().setItem(8, testStack);
				
			}
		}
	}
	
}