package me.Phil14052.ClearChat2_0.Utils.JSON;

import mkremins.fanciful.FancyMessage;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface JsonSender {

	public void sendJson(CommandSender sender, FancyMessage fm);
	public void sendJson(Player player, FancyMessage fm);
	
	
}
