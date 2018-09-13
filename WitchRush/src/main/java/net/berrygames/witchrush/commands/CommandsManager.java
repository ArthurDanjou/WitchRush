package net.berrygames.witchrush.commands;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.commands.admins.GameCMD;
import net.berrygames.witchrush.commands.admins.TestCMD;

public class CommandsManager {

    public void register(WitchRush main){
        main.getCommand("game").setExecutor(new GameCMD());
        main.getCommand("test").setExecutor(new TestCMD());

        System.out.println("Commandes register");
    }
}
