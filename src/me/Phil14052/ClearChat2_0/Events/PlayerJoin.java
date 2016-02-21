package me.Phil14052.ClearChat2_0.Events;

import me.Phil14052.ClearChat2_0.ClearChat2_0;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(plugin.getConfig().getBoolean("JoinClear.Enabled") == false){
			//Do nothing!
		}else{
			if(plugin.getConfig().getBoolean("JoinClear.OnlyWithPermission")==false){
				for(int i=1; i<plugin.getConfig().getInt("Lines.join"); i++){
					p.sendMessage("                ");
			    }
				if(plugin.gcfg().getBoolean("JoinClear.DisplayMessageAfterClear.Enabled") == true){
					p.sendMessage(plugin.gcfg().getString("JoinClear.DisplayMessageAfterClear.Message")
					.replaceAll("%PlayerName%", p.getName())
					.replaceAll("%PlayerDisplayName%", p.getDisplayName())
					.replaceAll("%World%", p.getWorld().toString())
					.replaceAll("%Prefix%", plugin.getPrefix())
					.replaceAll("%Newline%", "\n")
					.replaceAll("&","§"));
				}
			}else{
				if(p.hasPermission("clearchat.joinclear") || p.hasPermission("clearchat.*")){
					for(int i=1; i<plugin.getConfig().getInt("Lines.join"); i++){
						p.sendMessage("                ");
				    }
					if(plugin.gcfg().getBoolean("JoinClear.DisplayMessageAfterClear.Enabled") == true){
						p.sendMessage(plugin.gcfg().getString("JoinClear.DisplayMessageAfterClear.Message")
						.replaceAll("%PlayerName%", p.getName())
						.replaceAll("%PlayerDisplayName%", p.getDisplayName())
						.replaceAll("%World%", p.getWorld().toString())
						.replaceAll("%Prefix%", plugin.getPrefix())
						.replaceAll("%Newline%", "\n")
						.replaceAll("&","§"));
					}
				}
			}
		}
		
	}
}