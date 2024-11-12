package com.sintraqos.portfolioproject.Statics;

import com.sintraqos.portfolioproject.User.UserManager;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    @Autowired
    private UserManager userManager;
    @Autowired
    private GameManager gameManager;

    //region Handle Commands

    /**
     * Handle the commands from the console readline
     */
    public void handleCommand(String input) {
        // Split the string received from the input
        String[] inputSplit = input.replace(",", "").split(" ");    // Split the string by spaces and remove all ","

        // Check if the user has written at least 1 word
        if (inputSplit.length < 1) {
            return;
        }

        // Check if the user has written more than 1 word
        switch (inputSplit[0]) {

            case "help":
                handleHelpCommands(input);
                break;

            case "acc":
            case "user":
                handleAccountCommands(input);
                break;

            case "game":
                handleGameCommands(input);
                break;

            case "test":
                createGameLibrary();
                break;

            default:
                Console.writeLine(displayBaseCommands());
                break;
        }
    }

    /**
     * Handler of all the help commands
     *
     * @param input the input of the readline
     */
    public void handleHelpCommands(String input) {
        String[] inputSplit = input.split(" ");
        // Check if the user has written more than 1 word
        if (inputSplit.length < 2) {
            Console.writeLine(displayHelp());

            return;
        }

        // Switch over the input for the command
        switch (inputSplit[1]) {
            case "user":
                Console.writeLine(displayAccount());

                break;

            default:
                Console.writeLine(displayHelp());

                break;
        }
    }

    /**
     * Handler of all the user commands
     *
     * @param input the input of the readline
     */
    public void handleAccountCommands(String input) {
        String[] inputSplit = input.split(" ");
        // Check if the user has written more than 1 word
        if (inputSplit.length < 2) {
            Console.writeLine(displayAccount());

            return;
        }

        // Switch over the input for the command
        switch (inputSplit[1]) {
            case "register":
            case "create":
                // Check if the input has the correct length
                // Since for creating the user takes a username, e-mail and a password give a check if all are present
                if (inputSplit.length < 5) {
                    Console.writeLine("""
                            Incorrect Syntax use either:
                            user create { Username, E-Mail, Password }
                            or
                            user register { Username, E-Mail, Password }
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.createAccount(inputSplit[2], inputSplit[3], inputSplit[4]).getMessage());

                break;

            // Since both "remove" and "delete" do the exact same thing switch over both of them
            case "remove":
            case "delete":
                // Check if the input has the correct length
                // Since for removing the user takes a username and a password give a check if both are present
                if (inputSplit.length < 4) {
                    Console.writeLine("""
                            Incorrect Syntax use either:
                            user remove { Username, Password }
                            or
                            user delete { Username, Password }
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.deleteAccount(inputSplit[2], inputSplit[3]).getMessage());

                break;
            case "update":
                // Check if the input has the correct length
                // Since for updating the user takes a username check if it is present
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            user update { Username, E-Mail, Password }
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.updateAccount(inputSplit[2], inputSplit[3], inputSplit[4]).getMessage());

                break;
            case "display":
                // Check if the input has the correct length
                // Since for displaying the user takes a username check if it is present
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            user display { Username }
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.displayAccount(inputSplit[2]).getMessage());

                break;
            case "login":
                // Check if the input has the correct length
                // Since for logging into the user takes a username and a password give a check if both are present
                if (inputSplit.length < 4) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            user login username password
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.loginAccount(inputSplit[2], inputSplit[3]).getMessage());

                break;
            case "logout":
                // Check if the input has the correct length
                // Since for  logging out of the user takes a username check if it is present
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            user logout { Username }
                            """);

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.logoutAccount(inputSplit[2]).getMessage());

                break;
            case "add":
                // Check if the input has the correct length
                // Since for adding a game to the user takes a username and a gameID give a check if both are present
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            user add { Username GameID }
                            """);

                    break;
                }

                // Check if the given gameID is a numeric value
                if (!inputSplit[3].chars().allMatch(Character::isDigit)) {
                    Console.writeLine("Invalid input, gameID should be a numeric value");

                    break;
                }

                // Execute the command and write the result in the console
                Console.writeLine(userManager.addGame(inputSplit[2], Integer.parseInt(inputSplit[3])).getMessage());

                break;
        }

    }

    /**
     * Handler of all the game commands
     *
     * @param input the input of the readline
     */
    public void handleGameCommands(String input) {
        // Check if the user has written more than 1 word
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 2) {
            Console.writeLine(displayHelp());

            return;
        }

        // Switch over the input for the command
        switch (inputSplit[1]) {
            case "add":
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax:
                            game add { GameID }
                            """);
                    return;
                }
                break;
            case "get":
                if (inputSplit.length < 3) {
                    Console.writeLine("""
                            Incorrect Syntax Use Either:
                            game get { [ GameName ], [ GameDescription ], [ GameDeveloper] , [ GamePublisher] }
                            or
                            game get all
                            """);
                    return;
                }
                Console.writeLine(displayGame());

                break;

            default:
                Console.writeLine(displayGame());

                break;
        }
    }

    /**
     * Use for quickly adding games to the library
     */
    public void createGameLibrary() {
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

    //endregion

    //region Display Commands

    /**
     * Display of all the base commands
     *
     * @return A string containing all the base commands
     */
    public String displayBaseCommands() {
        return """
                Commands:
                help
                user
                game
                """;
    }

    /**
     * Display of all the help commands
     *
     * @return A string containing all the help commands, with examples
     */
    public String displayHelp() {
        return """
                Commands Have Sub Commands:
                help
                    - user
                    - game
                user
                    - create
                    - remove
                    - delete
                    - update
                    - display
                    - login
                    - logout
                    - add
                game
                """;
    }

    /**
     * Display of all the user commands
     *
     * @return A string containing all the user commands, with examples
     */
    public String displayAccount() {
        return """
                Account:
                user create  { Username, E-Mail, Password }
                user remove  { Username, Password }
                user delete  { Username, Password }
                user update  { Username }
                user display { Username }
                user login   { Username, Password }
                user logout  { Username }
                user add     { Username, GameID }
                """;
    }

    /**
     * Display of all the game commands
     *
     * @return A string containing all the game commands, with examples
     */
    public String displayGame() {
        return """
                Game:
                game add  { [ GameName ], [ GameDescription ], [ GameDeveloper] , [ GamePublisher] }
                game get  { gameID }
                game get all
                """;
    }

    //endregion
}
