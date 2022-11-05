package me.koba1.serverswitch.Commands;

import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Utils.playertools;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_staffchat extends Command {

    public Command_staffchat() {
        super("bungeestaffchat", "serverswitch.command.staffchat", "bungeesc", "bsc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (playertools.isStaff(p)) {
                if(args.length > 0) {
                    String message = Configs.get().getString("staff_chat");
                    message = message
                            .replace("%player%", p.getName())
                            .replace("%message%", setupArgs(args));
                    playertools.sendToAllStaff(message);
                }
            }
        }
    }

    public static String setupArgs(String[] args) {
        String output = "";
        for (String arg : args) {
            output += arg;
            output += " ";
        }

        return output;
    }
}
