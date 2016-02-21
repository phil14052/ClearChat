package me.Phil14052.ClearChat2_0.Events;

import java.util.UUID;

import me.Phil14052.ClearChat2_0.ClearChat2_0;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ReciveChatEvent implements Listener{
	
	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e){
		if(plugin.hasPermisson(e.getPlayer(), "clearchat.mutechat.bypass" , false) == false){
		
			for(Player p : Bukkit.getOnlinePlayers()){
				UUID uuid = p.getUniqueId();
				if(plugin.reciveChat.contains(uuid)){
					e.getRecipients().remove(p);
				}
			}
			
		}
	}

}
