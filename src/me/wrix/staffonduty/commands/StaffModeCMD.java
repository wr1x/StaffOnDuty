package me.wrix.staffonduty.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wrix.staffonduty.StaffOnDuty;
import me.wrix.staffonduty.utils.StaffMode;

public class StaffModeCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("staffonduty.all")) {
				if(StaffOnDuty.getUser(p).isInStaffMode()) {
					//Disable staff mode
					StaffMode.removeStaffMode(p);
				}else {
					//Enable staff mode'
					StaffMode.addToStaffMode(p);
				}
			}
		}
		return false;
	}
}