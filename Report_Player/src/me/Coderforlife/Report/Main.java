package me.shadowtrain.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Tester extends JavaPlugin implements Listener {

	/**
	 * @author CrazeTheDev
	 *         Compatible with all CB and Spigot version of 1.8
	 *         Code is up to date!
	 *         Code will be updated when needed!
	 */
	Logger logger = Logger.getLogger("Minecraft");
	public final String prefix = ChatColor.DARK_RED + "[" + ChatColor.RED + "CoreReports" + ChatColor.DARK_RED + "] ";
	private int i;

	@Override
	public void onEnable() {
		super.onEnable();
		Bukkit.getServer().getLogger().info("Please note that this code has been updated!");
		Bukkit.getServer().getConsoleSender()
				.sendMessage(Tester.Format("&c&lCoreReports&8> &6has been enabled successfully!"));
		final PluginDescriptionFile pdffile = getDescription();
		this.logger.info(pdffile.getName() + Tester.Format(
				" &a&lHas Been Enabled." + "Version: " + pdffile.getVersion() + " Website: " + pdffile.getWebsite()));

		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 0, 2);
		p.sendMessage(
				Tester.Format("&9Please keep in mind not to Hackusate other players &6&Report them using /report!"));
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("report")) {
			if (sender instanceof Player) {
				if (sender.getServer().getPlayer(args[0]) != null) {
					final StringBuilder msg = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						if (i > 1) {
							msg.append(" ");
						}
						msg.append(args[i]);
					}
					final Player player = (Player) sender;
					if (player.hasPermission("report.player")) {
						if (args.length < 1) {
							sender.sendMessage(
									Tester.Format("&c&LError: " + Tester.Format("&9Didn't specify enough arguements")));
							sender.sendMessage(this.prefix + Tester.Format("&6/report " + Tester.Format("&e<Player>")));
							return true;
						}
						final Player target = sender.getServer().getPlayer(args[0]);
						if (target != null) {
							player.sendMessage(this.prefix + "&aReport Sent!");
							final Player online = (Player) sender;
							if (online.hasPermission("report.view")) {
								online.sendMessage(this.prefix
										+ Tester.Format(player.getName() + Tester.Format(" &ahas reported " + Tester
												.Format(target.getName() + Tester.Format(" &bfor " + args[i])))));
							}
						}
					} else {
						sender.sendMessage(Tester.Format(
								"&c&LError: " + Tester.Format("&4You Do Not Have Permission To Do That Command!")));
					}
				} else {
					sender.sendMessage(Tester
							.Format("&c&lError: " + Tester.Format("&3That Command can not be done by the console")));
				}
			}
			return true;
		}
		return true;
	}

	public static String Format(String text) {
		return text.replaceAll("&", "§");
	}
}
