package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Account.AccountController;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameController;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.sintraqos.portfolioproject")
public class Main {
    public static void main(String[] args) {
        Console.writeLine("Initializing Spring-Boot");
        SpringApplication.run(Main.class, args);

        Console.writeLine("Finished initializing Spring-Boot");
        Console.writeLine("#################################");

//        // Setup components
//        GameController.getInstance();
//        AccountController.getInstance();
//
//        createGameLibrary();
//
//        Console.writeLine("#################################");
//
//        AccountController.getInstance().createAccount("Username", "e@mail.com", "password");
//        AccountController.getInstance().loginAccount("Username", "password");
//        AccountController.getInstance().loginAccount("Username", "wrong password");
//
//        AccountController.getInstance().updateLibrary(0);


    }

    /**
     * Use for quickly adding games to the library
     */
    static void createGameLibrary() {
        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in the kingdom of Hyrule.",
                "Nintendo EPD",
                "Nintendo"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "The Witcher 3: Wild Hunt",
                "An action RPG with a rich story and an expansive open world.",
                "CD Projekt Red",
                "CD Projekt"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Dark Souls",
                "A challenging action RPG known for its difficulty and deep lore.",
                "FromSoftware",
                "Bandai Namco Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Stardew Valley",
                "A farming simulation game with RPG elements.",
                "ConcernedApe",
                "ConcernedApe"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Among Us",
                "A multiplayer game of teamwork and betrayal in space.",
                "Innersloth",
                "Innersloth"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Minecraft",
                "A sandbox game that allows players to build and explore worlds.",
                "Mojang Studios",
                "Mojang Studios"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Fortnite",
                "A battle royale game where players fight to be the last one standing.",
                "Epic Games",
                "Epic Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Cyberpunk 2077",
                "An open-world RPG set in a dystopian future.",
                "CD Projekt Red",
                "CD Projekt"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Overwatch",
                "A team-based multiplayer first-person shooter.",
                "Blizzard Entertainment",
                "Blizzard Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Animal Crossing: New Horizons",
                "A social simulation game where players develop a deserted island.",
                "Nintendo",
                "Nintendo"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Final Fantasy VII Remake",
                "A reimagining of the classic RPG with modern graphics and gameplay.",
                "Square Enix",
                "Square Enix"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Super Mario Odyssey",
                "A 3D platformer featuring Mario as he travels to various kingdoms.",
                "Nintendo EPD",
                "Nintendo"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "God of War",
                "An action-adventure game that follows Kratos and his son.",
                "Santa Monica Studio",
                "Sony Interactive Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Hollow Knight",
                "A metroidvania action-adventure game set in a vast, interconnected world.",
                "Team Cherry",
                "Team Cherry"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Persona 5",
                "A JRPG that blends life simulation with dungeon crawling.",
                "Atlus",
                "Atlus"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Doom Eternal",
                "A fast-paced first-person shooter and sequel to Doom (2016).",
                "id Software",
                "Bethesda Softworks"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Apex Legends",
                "A free-to-play battle royale game set in the Titanfall universe.",
                "Respawn Entertainment",
                "Electronic Arts"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Resident Evil Village",
                "A survival horror game featuring Ethan Winters in a mysterious village.",
                "Capcom",
                "Capcom"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Genshin Impact",
                "An action RPG set in an open world with elemental magic.",
                "miHoYo",
                "miHoYo"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "The Elder Scrolls V: Skyrim",
                "An open-world fantasy RPG set in the land of Skyrim.",
                "Bethesda Game Studios",
                "Bethesda Softworks"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "League of Legends",
                "A multiplayer online battle arena game where players compete in teams.",
                "Riot Games",
                "Riot Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Valorant",
                "A tactical first-person shooter with hero-based abilities.",
                "Riot Games",
                "Riot Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Battlefield V",
                "A first-person shooter set during World War II.",
                "DICE",
                "Electronic Arts"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Ghost of Tsushima",
                "An action-adventure game set in feudal Japan.",
                "Sucker Punch Productions",
                "Sony Interactive Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Death Stranding",
                "An open-world action game featuring a unique story and mechanics.",
                "Kojima Productions",
                "Sony Interactive Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Cuphead",
                "A run-and-gun indie game with a unique hand-drawn art style.",
                "Studio MDHR",
                "Studio MDHR"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Returnal",
                "A roguelike third-person shooter with a time-loop mechanic.",
                "Housemarque",
                "Sony Interactive Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Monster Hunter: World",
                "An action RPG focused on hunting giant monsters.",
                "Capcom",
                "Capcom"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Celeste",
                "A platformer that tells the story of a young woman climbing a mountain.",
                "Maddy Makes Games",
                "Maddy Makes Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Hades",
                "A roguelike dungeon crawler set in the Greek underworld.",
                "Supergiant Games",
                "Supergiant Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "The Last of Us Part II",
                "A narrative-driven action-adventure game about survival and revenge.",
                "Naughty Dog",
                "Sony Interactive Entertainment"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Nioh 2",
                "An action RPG set in a dark fantasy version of the Sengoku period.",
                "Team Ninja",
                "Koei Tecmo"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Sekiro: Shadows Die Twice",
                "An action-adventure game set in Sengoku-era Japan.",
                "FromSoftware",
                "Activision"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Far Cry 5",
                "An open-world first-person shooter set in rural Montana.",
                "Ubisoft",
                "Ubisoft"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Control",
                "An action-adventure game set in a mysterious government building.",
                "Remedy Entertainment",
                "505 Games"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Assassin's Creed Valhalla",
                "An action RPG set during the Viking invasion of England.",
                "Ubisoft",
                "Ubisoft"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Watch Dogs: Legion",
                "An open-world game set in a near-future London.",
                "Ubisoft Toronto",
                "Ubisoft"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Minecraft Dungeons",
                "A dungeon crawler inspired by classic games.",
                "Mojang Studios",
                "Mojang Studios"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Ghost Recon Breakpoint",
                "A tactical shooter set in a large open-world environment.",
                "Ubisoft",
                "Ubisoft"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "FIFA 21",
                "The latest installment in the popular football video game series.",
                "EA Sports",
                "Electronic Arts"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "NBA 2K21",
                "A basketball simulation game featuring realistic gameplay and graphics.",
                "Visual Concepts",
                "2K Sports"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Rogue Company",
                "A team-based multiplayer shooter with various game modes.",
                "Hi-Rez Studios",
                "Hi-Rez Studios"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Dead by Daylight",
                "A multiplayer horror game where players take on the roles of survivors and killers.",
                "Behaviour Interactive",
                "Behaviour Interactive"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Brawlhalla",
                "A free-to-play platform fighting game with a variety of characters.",
                "Blue Mammoth Games",
                "Ubisoft"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "ARK: Survival Evolved",
                "A survival game set in a world filled with dinosaurs.",
                "Studio Wildcard",
                "Studio Wildcard"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Fall Guys: Ultimate Knockout",
                "A multiplayer party game with mini-games and obstacle courses.",
                "Mediatonic",
                "Devolver Digital"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Spyro Reignited Trilogy",
                "A remaster of the classic platforming games featuring Spyro the Dragon.",
                "Toys for Bob",
                "Activision"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Tony Hawk's Pro Skater 1+2",
                "A remaster of the classic skateboarding games.",
                "Vicarious Visions",
                "Activision"));

        GameController.getInstance().addGame(new Game(
                GameController.getInstance().getAvailableGameID(),
                "Riders Republic",
                "A multiplayer sports game set in a massive open-world environment.",
                "Ubisoft Annecy",
                "Ubisoft"));
    }
}
