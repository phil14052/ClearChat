package me.Phil14052.ClearChat2_0.Utils;

import me.Phil14052.ClearChat2_0.ClearChat2_0;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class Updater extends YamlConfiguration{
	
	//First version of a updater. Only updates the config!
	
	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	private FileConfiguration gc;
	
	public void updateConfig(){
		PluginDescriptionFile pluginYml = plugin.getDescription();
		gc = plugin.getConfig();
		gc.options().header("ClearChat! Version: " + pluginYml.getVersion() + 
				" By Phil14052"
				+ "\n\n HelpMenu;needPermission: Does so you need permission to se the help menu"
				+ "\n InfoMenu;infoCommandToClearChatCommand: Want it so when you do /cc or /clearchat it will clear the chat? Enable this feature!"
				+ "\n Lines: How many lines it will clear! 100 is recommend"
				+ "\n JoinClear: Clears the chat on join"
				+ "\n OnlyWithPermission: Only players with the permisson clearchat.joinclear will get there chat cleared on join"
				+ "\n DisplayMessageAfterClear: Send a messages after join clear"
				+ "\n AutoClear: Autoclear is a system that clears your chat after some time"
				+ "\n InGamePlayersOnly: Only players will get there chat cleared when the autoclear goes on."
				+ "\n\nDisplayMessageAfterClear only: %Newline%");
		gc.options().copyHeader();
		gc.addDefault("HelpMenu.needPermission", true);
		gc.addDefault("InfoMenu.infoCommandToClearChatCommand", true);
		gc.addDefault("Lines.global", 100);
		gc.addDefault("Lines.personal", 100);
		gc.addDefault("Lines.join", 100);
		gc.addDefault("Lines.AutoClear", 100);
		gc.addDefault("JoinClear.Enabled", true);
		gc.addDefault("JoinClear.OnlyWithPermission", false);
		gc.addDefault("JoinClear.DisplayMessageAfterClear.Enabled", false);
		gc.addDefault("JoinClear.DisplayMessageAfterClear.Message", "&3Your chat was cleared on join");
		gc.addDefault("AutoClear.Enabled", true);
		gc.addDefault("AutoClear.Seconds", 0);
		gc.addDefault("AutoClear.Minutes", 30);
		gc.addDefault("AutoClear.Hours", 0);
		gc.addDefault("AutoClear.InGamePlayersOnly", true);
		gc.options().copyDefaults(true);
		plugin.saveDefaultConfig();
		plugin.saveConfig();
	}
	

}
