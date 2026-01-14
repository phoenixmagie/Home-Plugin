import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HomePlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("sethome").setExecutor(this);
        getCommand("home").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        // BEFEHL: /sethome
        if (cmd.getName().equalsIgnoreCase("sethome")) {
            getConfig().set("homes." + uuid, player.getLocation());
            saveConfig();
            player.sendMessage("§aHome wurde erfolgreich gesetzt!");
            return true;
        }

        // BEFEHL: /home
        if (cmd.getName().equalsIgnoreCase("home")) {
            if (getConfig().contains("homes." + uuid)) {
                Location loc = getConfig().getLocation("homes." + uuid);
                player.teleport(loc);
                player.sendMessage("§aTeleportiere zum Home...");
            } else {
                player.sendMessage("§cDu hast noch kein Home gesetzt!");
            }
            return true;
        }
        return true;
    }
}
