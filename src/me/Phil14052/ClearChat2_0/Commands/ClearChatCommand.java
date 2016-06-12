package me.Phil14052.ClearChat2_0.Commands;

import me.Phil14052.ClearChat2_0.ClearChat2_0;
import mkremins.fanciful.FancyMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor{
	
	private ClearChat2_0 plugin = ClearChat2_0.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("clearchat")){
			if(args.length < 1){
				if(plugin.getConfig().getBoolean("InfoMenu.infoCommandToClearChatCommand") && (sender instanceof Player)){
					Player p = (Player) sender;
					if(plugin.hasPermisson(p, "clearchat.clear.global", true)){
						if(args.length < 2){
							for(int i=1; i<plugin.getConfig().getInt("Lines.global"); i++){
								Bukkit.broadcastMessage("                ");
						    }
							Bukkit.broadcastMessage(plugin.lang.getString("GlobalClear")
									.replaceAll("%PlayerName%", p.getName())
									.replaceAll("%PlayerDisplayName%", p.getDisplayName())
									.replaceAll("%World%", p.getWorld().toString())
									.replaceAll("%Prefix%", plugin.getPrefix())
									.replaceAll("&","§"));
							
						}
					}
				}else{
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage("    ");
					sender.sendMessage(plugin.color("MainColor") + "§m-------------------------------------");
					sender.sendMessage("    ");
					
					FancyMessage fm = new FancyMessage(plugin.color("SecondaryColor") + " " + plugin.getPrefix() + " " + plugin.color("MainColor") + "Version: " + plugin.color("SecondaryColor") + plugin.getPluginVersion())
					.tooltip(plugin.color("SecondaryColor") + "§oBy Phil14052");
					plugin.getJsonSender().sendJson(sender, fm);
					sender.sendMessage("    ");
					FancyMessage fm2 = new FancyMessage(plugin.color("MainColor") + "Want help? Do ")
					.then(plugin.color("SecondaryColor") + "§o/clearchat help")
					.command("/clearchat help")
					.tooltip(ChatColor.RED + "Click here!");
					plugin.getJsonSender().sendJson(sender, fm2);
					if(sender instanceof Player){
						FancyMessage fm3 = new FancyMessage(plugin.color("MainColor") + "Or go on the plugin page ")
						.then(plugin.color("SecondaryColor") + "§oClick here")
						.link("http://goo.gl/5MA6Zl")
						.tooltip(ChatColor.RED + "Click here!");
						plugin.getJsonSender().sendJson(sender, fm3);
					}
					sender.sendMessage("    ");
					sender.sendMessage(plugin.color("MainColor") + "§m-------------------------------------");
					sender.sendMessage("    ");
				}
			}else{	
				/* 
				Template:
				
				if(args[0].equalsIgnoreCase("argument")){
					
				}
								*/
				if(args[0].equalsIgnoreCase("help")){
					if(plugin.showHelpMenu(sender, "clearchat.helpmenu", true)){
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage("    ");
						sender.sendMessage(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.Headers.top"));
						sender.sendMessage("    ");
						plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("SecondaryColor") + " " + plugin.getLangWithPH("HelpMenu.Headers.helpMenuInfo")));
						sender.sendMessage("    ");
						plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat").tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.mainCommand")).suggest("/clearchat"));
						plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat help").tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.helpCommand")).suggest("/clearchat help"));
						if(sender.hasPermission("clearchat.clear.global")){
							plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat global [-s,-a]")
							.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.globalClear"))
							.suggest("/clearchat global"));
						}
						if(sender.hasPermission("clearchat.clear.personal")){
							plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat personal")
							.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.personalClear"))
							.suggest("/clearchat personal"));
						}
						if(sender.hasPermission("clearchat.reload")){
							plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat reload")
							.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.reload"))
							.suggest("/clearchat reload"));
						}
						if(sender.hasPermission("clearchat.mutechat")){
							plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat mutechat")
							.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.muteChat"))
							.suggest("/clearchat mutechat"));
						}
						if(sender.hasPermission("clearchat.mutepersonal")){
							plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat mutepersonal")
							.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.mutePersonal"))
							.suggest("/clearchat mutepersonal"));
						}
						plugin.getJsonSender().sendJson(sender, new FancyMessage(plugin.color("MainColor") + " /clearchat version")
						.tooltip(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.CommandDescription.versionCommand"))
						.suggest("/clearchat version"));
						
						sender.sendMessage("    ");
						sender.sendMessage(plugin.color("SecondaryColor") + plugin.getLangWithPH("HelpMenu.Tips.Prefix") + plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.Tips.tipOne"));
						if(sender instanceof Player){
							sender.sendMessage(plugin.color("SecondaryColor") + plugin.getLangWithPH("HelpMenu.Tips.Prefix") + plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.Tips.tipTwo"));
						}
						sender.sendMessage(plugin.color("MainColor") + plugin.getLangWithPH("HelpMenu.Headers.buttom"));
						return true;
					}	
				}
				else if(args[0].equalsIgnoreCase("reload")){
					if(sender instanceof Player){
						Player p = (Player) sender;
						if(plugin.hasPermisson(p, "clearchat.reload", true)){
							plugin.AutoClearStop();
							plugin.reloadConfigs();
							if(plugin.isOptionEnabled("AutoClear.Enabled") == true){
								plugin.seconds = plugin.gcfg().getInt("AutoClear.Seconds");
								plugin.minutes = plugin.gcfg().getInt("AutoClear.Minutes");	
					        	plugin.hours = plugin.gcfg().getInt("AutoClear.Hours") * 60;
					        	plugin.time = (plugin.hours * 60) + (plugin.minutes * 60) + plugin.seconds;
					        	plugin.getLogger().info("Total Autoclear time = " + plugin.time);
					        	plugin.AutoClearStart();
					        }
							
							p.sendMessage(plugin.lang.getString("ReloadMessage")
									.replaceAll("%PlayerName%", p.getName())
									.replaceAll("%PlayerDisplayName%", p.getDisplayName())
									.replaceAll("%Prefix%", plugin.getPrefix())
									.replaceAll("&","§"));
							
						}
					}else{
						plugin.reloadConfigs();
						if(plugin.isOptionEnabled("AutoClear.Enabled") == true){
							plugin.time = plugin.gcfg().getInt("AutoClear.Time");
				        	plugin.AutoClearStart();
				        }
						sender.sendMessage(plugin.lang.getString("ReloadMessage")
								.replaceAll("%PlayerName%", "Console")
								.replaceAll("%PlayerDisplayName%", "Console")
								.replaceAll("%Prefix%", plugin.getPrefix())
								.replaceAll("&","§"));	
					}
				}
				else if(args[0].equalsIgnoreCase("global")){
					if(sender instanceof Player){
						Player p = (Player) sender;
						if(plugin.hasPermisson(p, "clearchat.clear.global", true)){
							if(args.length < 2){
								for(int i=1; i<plugin.getConfig().getInt("Lines.global"); i++){
									Bukkit.broadcastMessage("                ");
							    }
								Bukkit.broadcastMessage(plugin.lang.getString("GlobalClear")
										.replaceAll("%PlayerName%", p.getName())
										.replaceAll("%PlayerDisplayName%", p.getDisplayName())
										.replaceAll("%World%", p.getWorld().toString())
										.replaceAll("%Prefix%", plugin.getPrefix())
										.replaceAll("&","§"));
								
							}else if(args[1].equalsIgnoreCase("-a")){
								for(int i=1; i<plugin.gcfg().getInt("Lines.global"); i++){
									Bukkit.broadcastMessage("                ");
								}
								Bukkit.broadcastMessage(plugin.lang.getString("AnonymousGlobalClear")
										.replaceAll("&","§"));
								
							}else if(args[1].equalsIgnoreCase("-s")){
								for(int i=1; i<plugin.getConfig().getInt("Lines.global"); i++){
									Bukkit.broadcastMessage("                ");
							    }
							}else{
								sender.sendMessage("    ");
								sender.sendMessage(plugin.lang.getString("UnknownArgument")
										.replaceAll("%Argument%", args[1])
										.replaceAll("%Prefix%", plugin.getPrefix())
										.replaceAll("&","§"));
							}
						}
					}else{
						for(int i=1; i<plugin.getConfig().getInt("Lines.global"); i++){
							Bukkit.broadcastMessage("                ");
					    }
						Bukkit.broadcastMessage(plugin.lang.getString("GlobalClear")
								.replaceAll("%PlayerName%", "Console")
								.replaceAll("%PlayerDisplayName%", "§cConsole")
								.replaceAll("%World%", "The world of consols")
								.replaceAll("%Prefix%", plugin.getPrefix())
								.replaceAll("&","§"));
						
					}
				}
				else if(args[0].equalsIgnoreCase("personal")){
					if(sender instanceof Player){
						Player p = (Player) sender;
						if(plugin.hasPermisson(p, "clearchat.clear.personal", true)){
							if(args.length < 2){
								for(int i=1; i<plugin.getConfig().getInt("Lines.personal"); i++){
									p.sendMessage("  ");
								}
								p.sendMessage(plugin.lang.getString("PersonalClear")
										.replaceAll("%PlayerName%", p.getName())
										.replaceAll("%PlayerDisplayName%", p.getDisplayName())
										.replaceAll("%World%", p.getWorld().toString())
										.replaceAll("%Prefix%", plugin.getPrefix())
										.replaceAll("&","§"));
								
							}else if(args[1].equalsIgnoreCase("-m")){
								for(int i=1; i<plugin.getConfig().getInt("Lines.personal"); i++){
									p.sendMessage("  ");
								}
							}else{
								sender.sendMessage("    ");
								String m = args[1];
								if(m.contains("\\")) return false;
								sender.sendMessage(plugin.lang.getString("UnknownArgument")
										.replaceAll("%Argument%", m)
										.replaceAll("%Prefix%", plugin.getPrefix())
										.replaceAll("&","§"));
							}
						}
						
						
					}else{
						sender.sendMessage(plugin.lang.getString("InGameOnly").replaceAll("&","§"));
					}
				}
				else if(args[0].equalsIgnoreCase("mutechat")){
					if(sender instanceof Player){
						Player p = (Player) sender;
						if(plugin.hasPermisson(p, "clearchat.mutechat", true)){
							ClearChat2_0.getAPI().toggleGlobalMute();
						}
					}else{
						ClearChat2_0.getAPI().toggleGlobalMute();
					}
				}
				
				
				else if(args[0].equalsIgnoreCase("mutepersonal")){
					if(sender instanceof Player){
						Player p = (Player) sender;
						if(plugin.hasPermisson(p, "clearchat.mutepersonal", true)){
							ClearChat2_0.getAPI().togglePlayerRecevingChat(p);
						}
					}else{
						sender.sendMessage(plugin.lang.getString("InGameOnly").replaceAll("&","§"));
					}
		
				}else if(args[0].equalsIgnoreCase("version")){
					sender.sendMessage(plugin.lang.getString("VersionMessage")
							.replaceAll("%Version%", plugin.getPluginVersion())
							.replaceAll("%Prefix%", plugin.getPrefix())
							.replaceAll("&","§"));
				}else{
					sender.sendMessage("    ");
					String m = args[0];
					if(m.contains("\\")) return false;
					sender.sendMessage(plugin.lang.getString("UnknownArgument")
							.replaceAll("%Argument%", m)
							.replaceAll("%Prefix%", plugin.getPrefix())
							.replaceAll("&","§"));
					
				}
			}
		}
		return false;
	}
}
