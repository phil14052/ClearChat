package me.Phil14052.ClearChat2_0.Events;

import java.util.UUID;

import me.Phil14052.ClearChat2_0.ClearChat2_0;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{

	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(plugin.globalMute == true){
        	if(plugin.hasPermisson(p, "clearchat.mutechat.bypass", false) == false){
    			e.setCancelled(true);
    			p.sendMessage(plugin.lang.getString("DeniedToChat")
    					.replaceAll("%PlayerName%", p.getName())
						.replaceAll("%PlayerDisplayName%", p.getDisplayName())
						.replaceAll("%World%", p.getWorld().toString())
						.replaceAll("%Prefix%", plugin.getPrefix())
						.replaceAll("&","§"));
    		}
        }
        
        if(e.isCancelled() == false){
        	
        	for(Player p2 : Bukkit.getOnlinePlayers()){
        		
    			UUID uuid = p2.getUniqueId();
    			if(plugin.reciveChat.contains(uuid)){
    				
    				if(e.getMessage().contains(p2.getName())){
    					
    					if(plugin.hasPermisson(p, "clearchat.mutechat.bypass", false) == false){
    						p.sendMessage("  ");
    						p.sendMessage(plugin.lang.getString("CantReceiveChat")
    								.replaceAll("%PlayerName%", p.getName())
    								.replaceAll("%target%", p2.getName())
    								.replaceAll("%PlayerDisplayName%", p.getDisplayName())
    								.replaceAll("%World%", p.getWorld().toString())
    								.replaceAll("%Prefix%", plugin.getPrefix())
    								.replaceAll("&","§"));
    						p.sendMessage("  ");
    					}
    				}
    				
    				
    			}
    				
        	}
        }
        
	}
}
