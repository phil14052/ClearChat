package me.Phil14052.ClearChat2_0.Api;

import org.bukkit.entity.Player;

public interface Api {
	
	public void ClearGlobalChat(int lines,Boolean InGamePlayersOnly,Boolean MessageEnabled, String Message);
	public void ClearPersonalChat(Player p,int Lines , Boolean MessageEnabled, String Message);
	public boolean isAutoClearOn();
	public boolean isGlobalMuteEnabled();
	public void setGlobalMute(boolean mode);
	public void toggleGlobalMute();
	public void togglePlayerRecevingChat(Player player);
	
}
