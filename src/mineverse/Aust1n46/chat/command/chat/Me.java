package mineverse.Aust1n46.chat.command.chat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.api.MineverseChatAPI;
import mineverse.Aust1n46.chat.command.MineverseCommand;
import mineverse.Aust1n46.chat.localization.LocalizedMessage;
import mineverse.Aust1n46.chat.utilities.Format;

public class Me extends MineverseCommand {
	private MineverseChat plugin = MineverseChat.getInstance();

	public Me(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if(sender.hasPermission("venturechat.me")) {
			if(args.length > 0) {
				String msg = "";
				for(int x = 0; x < args.length; x++) 
					if(args[x].length() > 0) 
						msg += " " + args[x];	
				if(sender instanceof Player && MineverseChatAPI.getMineverseChatPlayer((Player) sender).hasFilter()) {
					msg = Format.FilterChat(msg);
				}
				if(sender.hasPermission("venturechat.color.legacy")) {
					msg = Format.FormatStringLegacyColor(msg);
				}
				if(sender.hasPermission("venturechat.color")) 
					msg = Format.FormatStringColor(msg);
				if(sender.hasPermission("venturechat.format")) 
					msg = Format.FormatString(msg);
				if(sender instanceof Player) {
					Player p = (Player) sender;
					plugin.getServer().broadcastMessage("* " + p.getDisplayName() + msg);
					return;
				}
				plugin.getServer().broadcastMessage("* " + sender.getName() + msg);
				return;
			}
			sender.sendMessage(LocalizedMessage.COMMAND_INVALID_ARGUMENTS.toString()
					.replace("{command}", "/me")
					.replace("{args}", "[message]"));
			return;
		}
		sender.sendMessage(LocalizedMessage.COMMAND_NO_PERMISSION.toString());
	}
}