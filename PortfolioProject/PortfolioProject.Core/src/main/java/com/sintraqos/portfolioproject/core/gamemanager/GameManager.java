package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.dataobjects.items.ItemManager;
import com.sintraqos.portfolioproject.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.OutputManager;

public class GameManager {

   public GameManager() {
       ItemManager.getInstance();
       PlayerManager.getInstance();

       DialogueManager.getInstance();

       OutputManager.getInstance();
   }
}
