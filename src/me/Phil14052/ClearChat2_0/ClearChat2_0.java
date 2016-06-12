package me.Phil14052.ClearChat2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import me.Phil14052.ClearChat2_0.Api.Api;
import me.Phil14052.ClearChat2_0.Api.CCApi;
import me.Phil14052.ClearChat2_0.Commands.ClearChatCommand;
import me.Phil14052.ClearChat2_0.Events.ChatEvent;
import me.Phil14052.ClearChat2_0.Events.PlayerJoin;
import me.Phil14052.ClearChat2_0.Events.ReciveChatEvent;
import me.Phil14052.ClearChat2_0.Utils.Config;
import me.Phil14052.ClearChat2_0.Utils.Updater;
import me.Phil14052.ClearChat2_0.Utils.JSON.JsonSender;
import me.Phil14052.ClearChat2_0.Utils.JSON.JsonSender_v1_10_R1;
import me.Phil14052.ClearChat2_0.Utils.JSON.JsonSender_v1_9_R1;
import me.Phil14052.ClearChat2_0.Utils.JSON.JsonSender_v1_9_R2;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearChat2_0 extends JavaPlugin{

	
	private static ClearChat2_0 plugin;

	public Config lang = new Config(this, "lang.yml");
	
	public boolean globalMute = false;
	
	public List<UUID> reciveChat = new ArrayList<UUID>();
	private static Api api;
	
	public int AutoBroadCast;
	public int seconds;
	public int minutes;
	public int hours;
	public int time;
	public Logger log;
	private JsonSender js;
	
	
	public void onEnable() {
		log = getLogger();
	    plugin = this;
        registerEvents();
        registerCommands();
        saveConfigs();
        Updater configUpdater = new Updater();
        configUpdater.updateConfig();
        if(isOptionEnabled("AutoClear.Enabled") == true){
        	seconds = gcfg().getInt("AutoClear.Seconds");
        	minutes = gcfg().getInt("AutoClear.Minutes") * 60;	
        	hours = gcfg().getInt("AutoClear.Hours") * 3600;
        	time = hours + minutes + seconds;
        	log.info("Total Autoclear time = " + time);
        	plugin.AutoClearStart();
        }

		if(plugin.getBukkitVersion().startsWith("v1_10")){
			js = new JsonSender_v1_10_R1();
		}
		if(plugin.getBukkitVersion().startsWith("v1_9_R2")){
			js = new JsonSender_v1_9_R2();
		}
		if(plugin.getBukkitVersion().startsWith("v1_9_R1")){
			js = new JsonSender_v1_9_R1();
		}
	}
	public String getBukkitVersion(){
		String version = "";
		try {
		     version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException ex) {
		ex.printStackTrace();
		}
		return version;
	}
	
	public JsonSender getJsonSender(){
		return js;
	}
	
	private void registerEvents() {
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new ReciveChatEvent(), this);
	}
	
	public FileConfiguration gcfg(){
		return getConfig();
	}
	public void saveConfigs(){
		lang.save();
		saveConfig();
	}
	public void reloadConfigs(){
		lang.reload();
		reloadConfig();
	}
	
	public void AutoClearStart(){
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			@Override
			public void run() {
				AutoBroadCast = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
					@Override
					public void run() {
						for(int i=1; i<plugin.getConfig().getInt("Lines.AutoClear"); i++){
							if(isOptionEnabled("AutoClear.InGamePlayersOnly") == true){
								if(Bukkit.getOnlinePlayers().isEmpty() == false){
									
									for (Player player1 : Bukkit.getOnlinePlayers()){
										player1.sendMessage("                ");	
									}
								}
							}else{
								Bukkit.broadcastMessage("                ");
							}
					    }
					}
				}, 0, time*20);
			}
			
		}, time*20);
		log.info("AutoClear Started!");
	}
	
	public void AutoClearStop(){
		Bukkit.getServer().getScheduler().cancelTask(AutoBroadCast);
		log.info("AutoClear Stopped!");
	}
	
	
	public boolean isOptionEnabled(String Option){
		if(!(gcfg().contains(Option))){
			log.warning("The option: " + Option + " is not in the config!");
		}
		if(gcfg().getBoolean(Option) == true){
			return true;
		}else{
			return false;
		}
	}
	
	
	public String getPrefix(){
		return lang.getString("prefix").replaceAll("&", "§");
	}
	
	public String color(String MainColorSecondatyColor){
		return lang.getString(MainColorSecondatyColor).replaceAll("&", "§");
		
	}
	
	public String getPluginVersion(){
		PluginDescriptionFile pluginYml = this.getDescription();
		return pluginYml.getVersion();
	}
	
	public boolean hasPermisson(Player p, String permission, boolean withMessage){
		if(p.hasPermission("clearchat.*")){
			return true;
		}
		else if(p.hasPermission(permission)){
			return true;
		}
		else{
			if(withMessage == true){
				p.sendMessage(lang.getString("NoPermission")
						.replaceAll("%PlayerName%", p.getName())
						.replaceAll("%PlayerDisplayName%", p.getDisplayName())
						.replaceAll("%World%", p.getWorld().toString())
						.replaceAll("%Permission%", permission)
						.replaceAll("%Prefix%", getPrefix())
						.replaceAll("&","§"));	
			}
			return false;	
		}
	}
	
	private void registerCommands() {
		getCommand("clearchat").setExecutor(new ClearChatCommand());
	}

	public static Api getAPI(){
		if(api == null){
			api = new CCApi();
		}
		return api;
	}
	
	
	
	public String getLangWithPH(String path){
		String message = plugin.lang.getString(path);
		getLogger().info(path);
		getLogger().warning(message);
		message = message.replaceAll("&", "§");
		message = message.replaceAll("%Prefix%",plugin.getPrefix());
		return message;
	}
	
	
	
	public static ClearChat2_0 getInstance() {
		return plugin;
	}
	
	public boolean hasPermisson(CommandSender sender, String permission,boolean withMessage) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(hasPermisson(p, permission, withMessage)){
				return true;
			}else{
				return false;
			}
		}else if(sender instanceof ConsoleCommandSender){
			return true;
		}else{
			getLogger().warning("Found a unknown instance");
			return false;
		}
		
	}
	
	public boolean showHelpMenu(CommandSender sender, String permission,boolean withMessage){
		if(plugin.getConfig().getBoolean("HelpMenu.needPermission")){
			if(plugin.hasPermisson(sender, permission, withMessage)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	
}
