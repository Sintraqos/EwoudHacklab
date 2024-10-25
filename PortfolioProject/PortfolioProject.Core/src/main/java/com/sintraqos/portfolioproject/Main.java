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

        // Create a test account and test login
        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());
        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "wrongPassword").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
        Console.writeLine(accountManager.deleteAccount("Username", "wrongPassword").getMessage());
        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());
        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());
    }

    /**
     * Use for quickly adding games to the library
     */
    void createGameLibrary() {
        gameManager.addGame(new Game(
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in the kingdom of Hyrule.",
                "Nintendo EPD",
                "Nintendo"));

        gameManager.addGame(new Game(
                "The Witcher 3: Wild Hunt",
                "An action RPG with a rich story and an expansive open world.",
                "CD Projekt Red",
                "CD Projekt"));

        gameManager.addGame(new Game(
                "Dark Souls",
                "A challenging action RPG known for its difficulty and deep lore.",
                "FromSoftware",
                "Bandai Namco Entertainment"));

        gameManager.addGame(new Game(
                "Stardew Valley",
                "A farming simulation game with RPG elements.",
                "ConcernedApe",
                "ConcernedApe"));

        gameManager.addGame(new Game(
                "Among Us",
                "A multiplayer game of teamwork and betrayal in space.",
                "Innersloth",
                "Innersloth"));

        gameManager.addGame(new Game(
                "Minecraft",
                "A sandbox game that allows players to build and explore worlds.",
                "Mojang Studios",
                "Mojang Studios"));

        gameManager.addGame(new Game(
                "Fortnite",
                "A battle royale game where players fight to be the last one standing.",
                "Epic Games",
                "Epic Games"));

        gameManager.addGame(new Game(
                "Cyberpunk 2077",
                "An open-world RPG set in a dystopian future.",
                "CD Projekt Red",
                "CD Projekt"));

        gameManager.addGame(new Game(
                "Overwatch",
                "A team-based multiplayer first-person shooter.",
                "Blizzard Entertainment",
                "Blizzard Entertainment"));

        gameManager.addGame(new Game(
                "Animal Crossing: New Horizons",
                "A social simulation game where players develop a deserted island.",
                "Nintendo",
                "Nintendo"));

        gameManager.addGame(new Game(
                "Final Fantasy VII Remake",
                "A reimagining of the classic RPG with modern graphics and gameplay.",
                "Square Enix",
                "Square Enix"));

        gameManager.addGame(new Game(
                "Super Mario Odyssey",
                "A 3D platformer featuring Mario as he travels to various kingdoms.",
                "Nintendo EPD",
                "Nintendo"));

        gameManager.addGame(new Game(
                "God of War",
                "An action-adventure game that follows Kratos and his son.",
                "Santa Monica Studio",
                "Sony Interactive Entertainment"));

        gameManager.addGame(new Game(
                "Hollow Knight",
                "A metroidvania action-adventure game set in a vast, interconnected world.",
                "Team Cherry",
                "Team Cherry"));

        gameManager.addGame(new Game(
                "Persona 5",
                "A JRPG that blends life simulation with dungeon crawling.",
                "Atlus",
                "Atlus"));

        gameManager.addGame(new Game(
                "Doom Eternal",
                "A fast-paced first-person shooter and sequel to Doom (2016).",
                "id Software",
                "Bethesda Softworks"));

        gameManager.addGame(new Game(
                "Apex Legends",
                "A free-to-play battle royale game set in the Titanfall universe.",
                "Respawn Entertainment",
                "Electronic Arts"));

        gameManager.addGame(new Game(
                "Resident Evil Village",
                "A survival horror game featuring Ethan Winters in a mysterious village.",
                "Capcom",
                "Capcom"));

        gameManager.addGame(new Game(
                "Genshin Impact",
                "An action RPG set in an open world with elemental magic.",
                "miHoYo",
                "miHoYo"));

        gameManager.addGame(new Game(
                "The Elder Scrolls V: Skyrim",
                "An open-world fantasy RPG set in the land of Skyrim.",
                "Bethesda Game Studios",
                "Bethesda Softworks"));

        gameManager.addGame(new Game(
                "League of Legends",
                "A multiplayer online battle arena game where players compete in teams.",
                "Riot Games",
                "Riot Games"));

        gameManager.addGame(new Game(
                "Valorant",
                "A tactical first-person shooter with hero-based abilities.",
                "Riot Games",
                "Riot Games"));

        gameManager.addGame(new Game(
                "Battlefield V",
                "A first-person shooter set during World War II.",
                "DICE",
                "Electronic Arts"));

        gameManager.addGame(new Game(
                "Ghost of Tsushima",
                "An action-adventure game set in feudal Japan.",
                "Sucker Punch Productions",
                "Sony Interactive Entertainment"));

        gameManager.addGame(new Game(
                "Death Stranding",
                "An open-world action game featuring a unique story and mechanics.",
                "Kojima Productions",
                "Sony Interactive Entertainment"));

        gameManager.addGame(new Game(
                "Cuphead",
                "A run-and-gun indie game with a unique hand-drawn art style.",
                "Studio MDHR",
                "Studio MDHR"));

        gameManager.addGame(new Game(
                "Returnal",
                "A roguelike third-person shooter with a time-loop mechanic.",
                "Housemarque",
                "Sony Interactive Entertainment"));

        gameManager.addGame(new Game(
                "Monster Hunter: World",
                "An action RPG focused on hunting giant monsters.",
                "Capcom",
                "Capcom"));

        gameManager.addGame(new Game(
                "Celeste",
                "A platformer that tells the story of a young woman climbing a mountain.",
                "Maddy Makes Games",
                "Maddy Makes Games"));

        gameManager.addGame(new Game(
                "Hades",
                "A roguelike dungeon crawler set in the Greek underworld.",
                "Supergiant Games",
                "Supergiant Games"));

        gameManager.addGame(new Game(
                "The Last of Us Part II",
                "A narrative-driven action-adventure game about survival and revenge.",
                "Naughty Dog",
                "Sony Interactive Entertainment"));

        gameManager.addGame(new Game(
                "Nioh 2",
                "An action RPG set in a dark fantasy version of the Sengoku period.",
                "Team Ninja",
                "Koei Tecmo"));

        gameManager.addGame(new Game(
                "Sekiro: Shadows Die Twice",
                "An action-adventure game set in Sengoku-era Japan.",
                "FromSoftware",
                "Activision"));

        gameManager.addGame(new Game(
                "Far Cry 5",
                "An open-world first-person shooter set in rural Montana.",
                "Ubisoft",
                "Ubisoft"));

        gameManager.addGame(new Game(
                "Control",
                "An action-adventure game set in a mysterious government building.",
                "Remedy Entertainment",
                "505 Games"));

        gameManager.addGame(new Game(
                "Assassin's Creed Valhalla",
                "An action RPG set during the Viking invasion of England.",
                "Ubisoft",
                "Ubisoft"));

        gameManager.addGame(new Game(
                "Watch Dogs: Legion",
                "An open-world game set in a near-future London.",
                "Ubisoft Toronto",
                "Ubisoft"));

        gameManager.addGame(new Game(
                "Minecraft Dungeons",
                "A dungeon crawler inspired by classic games.",
                "Mojang Studios",
                "Mojang Studios"));

        gameManager.addGame(new Game(
                "Ghost Recon Breakpoint",
                "A tactical shooter set in a large open-world environment.",
                "Ubisoft",
                "Ubisoft"));

        gameManager.addGame(new Game(
                "FIFA 21",
                "The latest installment in the popular football video game series.",
                "EA Sports",
                "Electronic Arts"));

        gameManager.addGame(new Game(
                "NBA 2K21",
                "A basketball simulation game featuring realistic gameplay and graphics.",
                "Visual Concepts",
                "2K Sports"));

        gameManager.addGame(new Game(
                "Rogue Company",
                "A team-based multiplayer shooter with various game modes.",
                "Hi-Rez Studios",
                "Hi-Rez Studios"));

        gameManager.addGame(new Game(
                "Dead by Daylight",
                "A multiplayer horror game where players take on the roles of survivors and killers.",
                "Behaviour Interactive",
                "Behaviour Interactive"));

        gameManager.addGame(new Game(
                "Brawlhalla",
                "A free-to-play platform fighting game with a variety of characters.",
                "Blue Mammoth Games",
                "Ubisoft"));

        gameManager.addGame(new Game(
                "ARK: Survival Evolved",
                "A survival game set in a world filled with dinosaurs.",
                "Studio Wildcard",
                "Studio Wildcard"));

        gameManager.addGame(new Game(
                "Fall Guys: Ultimate Knockout",
                "A multiplayer party game with mini-games and obstacle courses.",
                "Mediatonic",
                "Devolver Digital"));

        gameManager.addGame(new Game(
                "Spyro Reignited Trilogy",
                "A remaster of the classic platforming games featuring Spyro the Dragon.",
                "Toys for Bob",
                "Activision"));

        gameManager.addGame(new Game(
                "Tony Hawk's Pro Skater 1+2",
                "A remaster of the classic skateboarding games.",
                "Vicarious Visions",
                "Activision"));

        gameManager.addGame(new Game(
                "Riders Republic",
                "A multiplayer sports game set in a massive open-world environment.",
                "Ubisoft Annecy",
                "Ubisoft"));
    }
}
