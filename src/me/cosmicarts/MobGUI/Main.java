package me.cosmicarts.MobGUI;

import java.util.ArrayList;
import java.util.List;

import net.ssmc.customentitiesapi.entities.CustomEntities;
import net.ssmc.customentitiesapi.entities.CustomEntitySheep;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public Inventory inv;

	public ItemStack sg = new ItemStack(Material.ENDER_PEARL, 1);
	{
		ItemMeta sgmeta = sg.getItemMeta();
		sgmeta.setDisplayName(ChatColor.RED + "Survival Games");
		List<String> sglore = new ArrayList<String>();
		sglore.add(ChatColor.YELLOW
				+ "Click to join the Survival Games server!");
		sgmeta.setLore(sglore);
		sg.setItemMeta(sgmeta);
	}

	public void onEnable() {

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("mg")
				|| cmd.getName().equalsIgnoreCase("mobgui")
				&& sender instanceof Player) {

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("spawn")) {

					CustomEntitySheep ces = CustomEntities
							.getNewCustomEntitySheep(((Player) sender)
									.getLocation());
					ces.removeGoalSelectorPathfinderGoalAll();
					ces.setCustomName(ChatColor.YELLOW + "Server Selector");
					ces.setCustomNameVisible(true);
					ces.setUnpushable();
					ces.setUnableToMove();

				}

				else if (args[0].equalsIgnoreCase("about")) {

					player.sendMessage(ChatColor.GOLD + "" + ChatColor.ITALIC
							+ "MobGUI " + ChatColor.DARK_GRAY + "• "
							+ ChatColor.AQUA
							+ "MobGUI was developed in full by PistonGaming.");

				}

			}

			else {

				player.sendMessage(ChatColor.GOLD + "" + ChatColor.ITALIC
						+ "MobGUI " + ChatColor.DARK_GRAY + "• "
						+ ChatColor.RED + "Incorrect Usage.\n" + ChatColor.RED
						+ "/mg <about/spawn>");

			}

		}

		return false;

	}

	@EventHandler
	public void onSkeletonClick(PlayerInteractEntityEvent event) {

		if (event.getRightClicked() instanceof Sheep) {
			Player player = event.getPlayer();

			inv = Bukkit.createInventory(null, 54, ChatColor.GRAY
					+ "          Server Selector");

			inv.setItem(31, sg);
			inv.setItem(22, sg);

			player.openInventory(inv);
		}
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();

		if (!player.isOp()) {

			event.setCancelled(true);

		} else {

			return;

		}

	}

}
