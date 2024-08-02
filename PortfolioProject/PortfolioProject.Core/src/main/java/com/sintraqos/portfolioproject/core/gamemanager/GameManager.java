package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.connect.MariaDB.MariaDBConnectHandler;
import com.sintraqos.portfolioproject.dataobjects.items.ItemManager;
import com.sintraqos.portfolioproject.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.Console;

public class GameManager {

   public GameManager() {

       new MariaDBConnectHandler();
        // TODO: Await for connection to be made, if there is no connection made after a certain amount of time stop game from starting any further

       Console.writeHeader("Setup Game Manager");

       ItemManager.getInstance();
       PlayerManager.getInstance();

       DialogueManager.getInstance();

       Console.writeLine("Finished Setup Game Manager");
       Console.writeLine();

       OutputManager.getInstance();
   }
}
