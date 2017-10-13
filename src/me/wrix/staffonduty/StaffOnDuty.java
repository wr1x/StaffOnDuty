package me.wrix.staffonduty;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.wrix.staffonduty.commands.StaffModeCMD;
import me.wrix.staffonduty.listeners.PlayerListener;
import me.wrix.staffonduty.utils.User;

public class StaffOnDuty extends JavaPlugin{

	public static HashMap<UUID, User> USERS = new HashMap<>();
	
	@Override
	public void onEnable() {
		registerCommands();
		registerListeners();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerCommands() {
		getCommand("staffmode").setExecutor(new StaffModeCMD());
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
	}
	
	public static User getUser(Player p) {
		if(USERS.containsKey(p.getUniqueId())) {
			User u = USERS.get(p.getUniqueId());
			return u;
		}else {
			return null;
		}
	}
	
}
