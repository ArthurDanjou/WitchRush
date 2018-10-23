package net.berrygames.witchrush.commands;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.commands.admins.GameCMD;

public class CommandsManager {

    public void register(WitchRush main){
        main.getCommand("gamestart").setExecutor(new GameCMD());
    }
}
