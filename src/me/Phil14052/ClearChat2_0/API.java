package me.Phil14052.ClearChat2_0;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class API {

	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	
	public void ClearGlobalChat(int lines,Boolean InGamePlayersOnly,Boolean MessageEnabled, String Message){

		if(InGamePlayersOnly == true){
			for (Player player1 : Bukkit.getOnlinePlayers()){
		        player1.sendMessage("                ");	
			}
			if(MessageEnabled == true){
				Bukkit.broadcastMessage(Message
						.replaceAll("%PlayerName%", "Console")
						.replaceAll("%PlayerDisplayName%", "§cConsole")
						.replaceAll("%World%", "The world of consols")
						.replaceAll("%Prefix%", plugin.getPrefix())
						.replaceAll("&","§"));
			}
		}else{
		for(int i=0; i<lines; i++){
			Bukkit.broadcastMessage("                ");
	    }
		
		if(MessageEnabled == true){
			Bukkit.broadcastMessage(Message
					.replaceAll("%PlayerName%", "Console")
					.replaceAll("%PlayerDisplayName%", "§cConsole")
					.replaceAll("%World%", "The world of consols")
					.replaceAll("%Prefix%", plugin.getPrefix())
					.replaceAll("&","§"));
		}
		}

	}
	
	public void ClearPersonalChat(Player p,int Lines , Boolean MessageEnabled, String Message){
		for(int i=0; i<Lines; i++){
			p.sendMessage("                ");
		}
		if(MessageEnabled == true){
			p.sendMessage(Message
					.replaceAll("%PlayerName%", "Console")
					.replaceAll("%PlayerDisplayName%", "§cConsole")
					.replaceAll("%World%", "The world of consols")
					.replaceAll("%Prefix%", plugin.getPrefix())
					.replaceAll("&","§"));
		}
	}

	public boolean isAutoClearOn(){
		return plugin.isOptionEnabled("AutoClear.Enabled");
	}
	
	public boolean isGlobalMuteEnabled(){
		return plugin.globalMute;
	}
	
	
	public void setGlobalMute(boolean mode){
		plugin.globalMute = mode;
	}
	
	public void toggleGlobalMute(){
		if(isGlobalMuteEnabled() == true){
			setGlobalMute(false);
			Bukkit.broadcastMessage(plugin.lang.getString("MuteDisabled").replaceAll("&", "§"));
			
		}else{
			setGlobalMute(true);
			Bukkit.broadcastMessage(plugin.lang.getString("MuteEnabled").replaceAll("&", "§"));
		}
	}
	
	public void togglePlayerRecevingChat(Player player){
		if(!(plugin.reciveChat.contains(player.getUniqueId()))){
			plugin.reciveChat.add(player.getUniqueId());
			player.sendMessage(plugin.lang.getString("DisableReceivingChat")
					.replaceAll("%PlayerName%", player.getName())
					.replaceAll("%PlayerDisplayName%", player.getDisplayName())
					.replaceAll("%World%", player.getWorld().toString())
					.replaceAll("%Prefix%", plugin.getPrefix())
					.replaceAll("&","§"));
		}else{
			plugin.reciveChat.remove(player.getUniqueId());
			player.sendMessage(plugin.lang.getString("EnableReceivingChat")
					.replaceAll("%PlayerName%", player.getName())
					.replaceAll("%PlayerDisplayName%", player.getDisplayName())
					.replaceAll("%World%", player.getWorld().toString())
					.replaceAll("%Prefix%", plugin.getPrefix())
					.replaceAll("&","§"));
		}
	}
	
}
