package me.Phil14052.ClearChat2_0.Utils.JSON;

import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JsonSender_v1_10_R1 implements JsonSender{

	public void sendJson(CommandSender sender, FancyMessage fm) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			this.sendJson(p, fm);
			return;
		}else{
			String msg = fm.toOldMessageFormat();
			sender.sendMessage(msg);
		}
	}
	
	public void sendJson(Player player, FancyMessage fm) {
		String json = fm.toJSONString();

		IChatBaseComponent msg = ChatSerializer.a(json);
		    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(msg));
	}

	
}
