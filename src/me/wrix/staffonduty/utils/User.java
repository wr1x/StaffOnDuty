package me.wrix.staffonduty.utils;

import org.bukkit.entity.Player;

public class User {

	private Player player;
	private boolean inStaffMode;
	
	public User(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public boolean isInStaffMode() {
		return inStaffMode;
	}

	public void setInStaffMode(boolean inStaffMode) {
		this.inStaffMode = inStaffMode;
	}
}
