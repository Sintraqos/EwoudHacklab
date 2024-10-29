package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.sintraqos.portfolioproject")
public class Main {

    @Autowired
    private AccountManager accountManager;
    @Autowired
    private GameManager gameManager;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * After the initialization of Spring-Boot this will start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        Console.writeLine("Finished initializing Spring-Boot");
        Console.writeLine("#################################");

        // Create test Library
        createGameLibrary();
        Console.writeLine("#################################");
        Console.writeLine("Create a test account and test login");

        // Register
        Console.writeLine("-- Register");
        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());
        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());

        // Login
        Console.writeLine("-- Login");
        Console.writeLine(accountManager.loginAccount("Username", "wrongPassword").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());

        // Logout And Login
        Console.writeLine("-- Logout And Login");
        Console.writeLine(accountManager.logoutAccount("Username").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());

        // Add And Remove Game From Account Library
        Console.writeLine("-- Add And Remove Game From Account Library");
        Console.writeLine(accountManager.addGame("Username",1).getMessage());  // Using gameID
        Console.writeLine(accountManager.addGame("Username",2).getMessage()); // Using gameName

        // Remove Account
        Console.writeLine("-- Remove Account");
        Console.writeLine(accountManager.deleteAccount("Username", "wrongPassword").getMessage());
        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());
        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());
    }

    /**
     * Use for quickly adding games to the library
     */
    void createGameLibrary() {
        Console.writeLine(gameManager.addGame(new Game(
                "World of Warcraft",
                "World of Warcraft (WoW) is a 2004 massively multiplayer online role-playing (MMORPG) video game produced by Blizzard Entertainment.",
                "Blizzard Entertainment",
                "Blizzard Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in a large open world, allowing players to explore and complete quests in any order.",
                "Nintendo",
                "Nintendo EPD")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "The Witcher 3: Wild Hunt",
                "An action RPG set in a fantasy world, following Geralt of Rivia as he searches for his adopted daughter.",
                "CD Projekt Red",
                "CD Projekt")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Minecraft",
                "A sandbox game that allows players to build and explore their own worlds made up of blocks.",
                "Mojang Studios",
                "Mojang Studios")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Red Dead Redemption 2",
                "An action-adventure game set in the late 1800s, focusing on the life of an outlaw in the American frontier.",
                "Rockstar Games",
                "Rockstar North")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Dark Souls",
                "An action RPG known for its challenging gameplay and deep lore, set in a dark fantasy world.",
                "FromSoftware",
                "FromSoftware")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Super Mario Odyssey",
                "A platform game featuring Mario as he travels across various worlds to rescue Princess Peach.",
                "Nintendo",
                "Nintendo EPD")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Overwatch",
                "A team-based multiplayer first-person shooter featuring diverse characters with unique abilities.",
                "Blizzard Entertainment",
                "Blizzard Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Final Fantasy VII Remake",
                "A reimagining of the classic JRPG that follows Cloud Strife and his allies as they battle against Shinra.",
                "Square Enix",
                "Square Enix")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Among Us",
                "A multiplayer game where players work together to complete tasks while trying to identify the impostors.",
                "Innersloth",
                "Innersloth")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Animal Crossing: New Horizons",
                "A life simulation game where players develop and customize their own island paradise.",
                "Nintendo",
                "Nintendo EPD")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Cyberpunk 2077",
                "An open-world RPG set in a dystopian future where players navigate through a vast urban environment.",
                "CD Projekt Red",
                "CD Projekt")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Hollow Knight",
                "A metroidvania action-adventure game set in a beautifully hand-drawn underground world.",
                "Team Cherry",
                "Team Cherry")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Call of Duty: Warzone",
                "A free-to-play battle royale game set in the Call of Duty universe, featuring large-scale combat.",
                "Infinity Ward",
                "Activision")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Sekiro: Shadows Die Twice",
                "An action-adventure game set in feudal Japan, focusing on stealth, exploration, and intense combat.",
                "FromSoftware",
                "FromSoftware")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Fortnite",
                "A battle royale game featuring building mechanics and a vibrant world with frequent updates.",
                "Epic Games",
                "Epic Games")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Destiny 2",
                "A free-to-play online multiplayer first-person shooter with RPG elements and a sci-fi setting.",
                "Bungie",
                "Bungie")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Ghost of Tsushima",
                "An action-adventure game set in feudal Japan, focusing on a samurai's quest to protect his homeland.",
                "Sucker Punch Productions",
                "Sony Interactive Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Genshin Impact",
                "An open-world action RPG with gacha elements, allowing players to explore a fantasy world.",
                "miHoYo",
                "miHoYo")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Resident Evil 2 Remake",
                "A survival horror game that revitalizes the classic with modern graphics and gameplay mechanics.",
                "Capcom",
                "Capcom")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Stardew Valley",
                "A farming simulation game where players can grow crops, raise animals, and build their farm.",
                "ConcernedApe",
                "ConcernedApe")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "DOOM Eternal",
                "A fast-paced first-person shooter that pits players against demonic forces in a high-octane battle.",
                "id Software",
                "Bethesda Softworks")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "League of Legends",
                "A multiplayer online battle arena (MOBA) game where players compete in strategic team battles.",
                "Riot Games",
                "Riot Games")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Apex Legends",
                "A free-to-play battle royale game set in the Titanfall universe, featuring unique character abilities.",
                "Respawn Entertainment",
                "Electronic Arts")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Monster Hunter: World",
                "An action RPG where players hunt large monsters in diverse environments with friends.",
                "Capcom",
                "Capcom")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Celeste",
                "A platformer about climbing a mountain, featuring challenging gameplay and a touching narrative.",
                "Maddy Makes Games",
                "Maddy Makes Games")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Dying Light",
                "A survival horror game set in a zombie-infested open world, featuring parkour mechanics.",
                "Techland",
                "Techland")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Dead by Daylight",
                "A multiplayer horror game where one player takes on the role of a killer while others try to escape.",
                "Behaviour Interactive",
                "Behaviour Interactive")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Persona 5 Royal",
                "A JRPG that blends life simulation with dungeon crawling, following a group of high school students.",
                "Atlus",
                "Atlus")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Hades",
                "A roguelike dungeon crawler where players battle through the underworld, aiming to escape from Hades.",
                "Supergiant Games",
                "Supergiant Games")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Metal Gear Solid V: The Phantom Pain",
                "An open-world stealth game that follows Big Boss as he seeks revenge and uncovers conspiracies.",
                "Kojima Productions",
                "Konami")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Splatoon 2",
                "A colorful team-based shooter where players control squid-like characters in turf wars.",
                "Nintendo",
                "Nintendo EPD")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Nier: Automata",
                "An action RPG that blends deep narrative with hack-and-slash gameplay in a post-apocalyptic world.",
                "PlatinumGames",
                "Square Enix")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "The Elder Scrolls V: Skyrim",
                "An open-world RPG set in a rich fantasy world, allowing players to explore and complete quests freely.",
                "Bethesda Game Studios",
                "Bethesda Softworks")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "FIFA 22",
                "A football simulation game that allows players to manage teams and compete in matches.",
                "EA Sports",
                "Electronic Arts")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Gran Turismo 7",
                "A realistic racing simulation game that features a variety of cars and tracks.",
                "Polyphony Digital",
                "Sony Interactive Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Rainbow Six Siege",
                "A tactical first-person shooter that emphasizes teamwork and strategy in objective-based gameplay.",
                "Ubisoft",
                "Ubisoft")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Assassin's Creed Valhalla",
                "An action RPG set in the Viking Age, following Eivor as they navigate England's history.",
                "Ubisoft",
                "Ubisoft Montreal")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Control",
                "A supernatural action-adventure game where players manipulate objects and powers in a mysterious building.",
                "Remedy Entertainment",
                "505 Games")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Fire Emblem: Three Houses",
                "A tactical RPG that combines strategic battles with relationship-building elements.",
                "Intelligent Systems",
                "Nintendo")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Divinity: Original Sin 2",
                "A critically acclaimed RPG with deep storytelling and tactical turn-based combat.",
                "Larian Studios",
                "Larian Studios")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Ori and the Will of the Wisps",
                "A visually stunning platformer with a heartfelt story and challenging gameplay.",
                "Moon Studios",
                "Xbox Game Studios")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "The Last of Us Part II",
                "An action-adventure game focusing on the journey of Ellie in a post-apocalyptic world.",
                "Naughty Dog",
                "Sony Interactive Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Monster Hunter Rise",
                "An action RPG that builds upon the Monster Hunter formula with new mechanics and locales.",
                "Capcom",
                "Capcom")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Forza Horizon 5",
                "An open-world racing game set in a vibrant and diverse representation of Mexico.",
                "Playground Games",
                "Xbox Game Studios")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Returnal",
                "A roguelike third-person shooter that features fast-paced gameplay and a mysterious story.",
                "Housemarque",
                "Sony Interactive Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Hitman 3",
                "A stealth game that allows players to take on the role of Agent 47 in various global locations.",
                "IO Interactive",
                "IO Interactive")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "Subnautica",
                "An open-world survival game set in an alien ocean planet, where players explore and gather resources.",
                "Unknown Worlds Entertainment",
                "Unknown Worlds Entertainment")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "The Outer Worlds",
                "A first-person RPG set in an alternate future, allowing players to explore and make impactful choices.",
                "Obsidian Entertainment",
                "Private Division")).getMessage());

        Console.writeLine(gameManager.addGame(new Game(
                "It Takes Two",
                "A cooperative action-adventure game focused on collaboration between two players to navigate a whimsical world.",
                "Hazelight Studios",
                "Electronic Arts")).getMessage());
    }
}
