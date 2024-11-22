package com.sintraqos.portfolioproject.API.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class GameReviewAPI {

    private final List<GameReviewObject> gamesList = new ArrayList<>();

    public GameReviewAPI() {
        // Prepopulate the list with 15 games
        generateGames();
    }

    private void generateGames() {
        // Directly creating objects with random reviews and scores
        gamesList.add(createGame("Star Wars: Knights of the Old Republic", "A role-playing game set in the Star Wars universe, where players shape their destiny as they battle the forces of the Sith.", "BioWare", "Lucas Learning"));
        gamesList.add(createGame("Star Wars: Knights of the Old Republic II - The Sith Lords", "The sequel to Knights of the Old Republic, where players must navigate the galaxy as a Jedi exiled from the Order.", "Obsidian Entertainment", "LucasArts"));
        gamesList.add(createGame("Star Wars: The Old Republic", "An MMORPG set in the Star Wars universe, allowing players to choose from different factions and explore a galaxy full of story-driven quests.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("World of Warcraft", "The iconic MMORPG set in the world of Azeroth, where players engage in quests, dungeons, raids, and PvP combat.", "Blizzard Entertainment", "Blizzard Entertainment"));
        gamesList.add(createGame("Guild Wars 2", "A fantasy MMORPG with a unique approach to quests and combat, featuring dynamic events and a story that evolves based on player choices.", "ArenaNet", "NCSoft"));
        gamesList.add(createGame("Final Fantasy XIV", "An MMORPG set in the Final Fantasy universe, with a focus on story-driven content, dungeons, and raids.", "Square Enix", "Square Enix"));
        gamesList.add(createGame("Elder Scrolls Online", "An MMORPG set in the Elder Scrolls universe, where players can explore Tamriel and participate in large-scale PvP battles and dungeons.", "ZeniMax Online Studios", "Bethesda Softworks"));
        gamesList.add(createGame("Black Desert Online", "An action-oriented MMORPG with highly detailed character customization, fluid combat, and an expansive open world.", "Pearl Abyss", "Pearl Abyss"));
        gamesList.add(createGame("Star Trek Online", "An MMORPG set in the Star Trek universe, where players take command of a starship and explore the galaxy while engaging in space battles.", "Cryptic Studios", "Perfect World Entertainment"));
        gamesList.add(createGame("The Lord of the Rings Online", "An MMORPG set in Middle-Earth, where players take on the role of an adventurer exploring iconic locations from the books.", "Standing Stone Games", "Turbine Entertainment"));
        gamesList.add(createGame("Runescape", "An iconic fantasy MMORPG that allows players to explore an expansive world, completing quests, slaying monsters, and leveling up skills.", "Jagex", "Jagex"));
        gamesList.add(createGame("The Elder Scrolls V: Skyrim", "An open-world RPG where you play as the Dragonborn, exploring the northern province of Tamriel to defeat dragons and uncover ancient secrets.", "Bethesda Game Studios", "Bethesda Softworks"));
        gamesList.add(createGame("The Witcher 3: Wild Hunt", "An open-world RPG where you play as Geralt of Rivia, a monster hunter, navigating a dark and intricate fantasy world.", "CD Projekt Red", "CD Projekt"));
        gamesList.add(createGame("Red Dead Redemption 2", "An open-world action-adventure game set in the late 1800s, following the story of Arthur Morgan in the Van der Linde gang.", "Rockstar Games", "Rockstar Games"));
        gamesList.add(createGame("Grand Theft Auto V", "An open-world action game where you control three protagonists, engaging in heists, criminal activities, and various adventures in the city of Los Santos.", "Rockstar Games", "Rockstar Games"));
        gamesList.add(createGame("Horizon Zero Dawn", "An action RPG set in a post-apocalyptic world where mechanical creatures roam the earth, and players control Aloy, a young hunter.", "Guerrilla Games", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Uncharted 4: A Thief's End", "An action-adventure game following treasure hunter Nathan Drake on his final adventure, uncovering secrets and battling foes.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Spider-Man (PS4)", "An open-world action game where players control Peter Parker as Spider-Man, battling villains and navigating the streets of New York.", "Insomniac Games", "Sony Interactive Entertainment"));
        gamesList.add(createGame("God of War (2018)", "A story-driven action game following Kratos and his son Atreus on a journey through the Norse mythology world to scatter the ashes of Kratos' wife.", "Santa Monica Studio", "Sony Interactive Entertainment"));
        gamesList.add(createGame("The Last of Us Part II", "A post-apocalyptic action-adventure game where you control Ellie, seeking revenge and survival in a dangerous, infected world.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Dark Souls III", "A challenging action RPG set in a dark fantasy world, focusing on tough combat, exploration, and uncovering the lore of the land.", "FromSoftware", "Bandai Namco"));
        gamesList.add(createGame("Bloodborne", "An action RPG set in a dark, gothic city full of terrifying creatures and intense combat, where players unravel the mysteries of Yharnam.", "FromSoftware", "Sony Computer Entertainment"));
        gamesList.add(createGame("Sekiro: Shadows Die Twice", "An action-adventure game set in feudal Japan where players control a shinobi on a quest for revenge, mastering sword combat and stealth.", "FromSoftware", "Activision"));
        gamesList.add(createGame("Nier: Automata", "An action RPG set in a post-apocalyptic world where androids fight alien machines, with a deep narrative and multiple endings.", "PlatinumGames", "Square Enix"));
        gamesList.add(createGame("Assassin's Creed Valhalla", "An action RPG where you play as Eivor, a Viking warrior, exploring England and Norway while building a settlement and raiding enemies.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("Watch Dogs 2", "An open-world action game where players control Marcus Holloway, a hacker fighting against corruption and surveillance in San Francisco.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("Far Cry 5", "An open-world first-person shooter set in Montana, where players fight against a doomsday cult, in a battle for survival and freedom.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("Minecraft", "A sandbox game where players can build, craft, and explore in a blocky, procedurally generated world.", "Mojang", "Mojang"));
        gamesList.add(createGame("Fortnite", "A battle royale game with intense shooting action and building mechanics, where players fight to be the last one standing.", "Epic Games", "Epic Games"));
        gamesList.add(createGame("Overwatch", "A team-based first-person shooter where players choose from a variety of heroes, each with unique abilities, to fight in colorful, strategic battles.", "Blizzard Entertainment", "Blizzard Entertainment"));
        gamesList.add(createGame("Valorant", "A tactical first-person shooter where players control unique agents, each with distinct abilities, aiming to win rounds through strategy and skill.", "Riot Games", "Riot Games"));
        gamesList.add(createGame("Apex Legends", "A free-to-play battle royale game where players choose from a roster of heroes, each with unique abilities, to fight in squads.", "Respawn Entertainment", "Electronic Arts"));
        gamesList.add(createGame("League of Legends", "A multiplayer online battle arena game where players control champions and compete in strategic, team-based matches.", "Riot Games", "Riot Games"));
        gamesList.add(createGame("Rocket League", "A unique sports game where players control cars to play soccer in an arena, combining action and sports mechanics.", "Psyonix", "Psyonix"));
        gamesList.add(createGame("Super Smash Bros. Ultimate", "A crossover fighting game where players control characters from various gaming franchises and battle in various modes and stages.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("Street Fighter V", "A competitive fighting game where players choose from iconic characters and compete in one-on-one combat with precise controls.", "Capcom", "Capcom"));
        gamesList.add(createGame("Mortal Kombat 11", "A brutal fighting game with a focus on over-the-top fatalities and fast-paced combat, featuring a large roster of fighters.", "NetherRealm Studios", "Warner Bros. Interactive Entertainment"));
        gamesList.add(createGame("Tekken 7", "A popular 3D fighting game series where players control a variety of fighters, each with their own unique fighting styles and combos.", "Bandai Namco", "Bandai Namco Entertainment"));
        gamesList.add(createGame("Dragon Ball FighterZ", "A 2D fighting game based on the Dragon Ball franchise, where players control iconic characters in fast-paced, action-packed battles.", "Arc System Works", "Bandai Namco Entertainment"));
        gamesList.add(createGame("Final Fantasy XV", "An open-world RPG where you play as Noctis, the prince of a kingdom, on a quest to reclaim his throne and stop a global crisis.", "Square Enix", "Square Enix"));
        gamesList.add(createGame("Persona 5", "A JRPG following a group of high school students who lead double lives as Phantom Thieves, battling in a supernatural realm.", "Atlus", "Atlus"));
        gamesList.add(createGame("Dark Souls", "An action RPG known for its challenging combat, deep lore, and atmospheric world, where players battle tough enemies and explore mysterious lands.", "FromSoftware", "Bandai Namco Entertainment"));
        gamesList.add(createGame("Persona 5 Royal", "An enhanced version of Persona 5, with additional content, characters, and story elements, following a group of students battling in the Metaverse.", "Atlus", "Atlus"));
        gamesList.add(createGame("Nier Replicant ver.1.22474487139...", "A remake of the cult classic RPG, where players embark on a quest to cure a sister's mysterious illness in a post-apocalyptic world.", "Toylogic", "Square Enix"));
        gamesList.add(createGame("Resident Evil 2 Remake", "A survival horror game, reimagining the classic 1998 title, where players control Leon and Claire as they fight through a zombie-infested Raccoon City.", "Capcom", "Capcom"));
        gamesList.add(createGame("Resident Evil Village", "A survival horror game where players control Ethan Winters, fighting monsters and uncovering the secrets of a cursed village.", "Capcom", "Capcom"));
        gamesList.add(createGame("The Sims 4", "A life simulation game where players create and control virtual people, designing their homes, careers, and relationships.", "Maxis", "Electronic Arts"));
        gamesList.add(createGame("Civilization VI", "A turn-based strategy game where players build and lead a civilization, making decisions on politics, warfare, and diplomacy.", "Firaxis Games", "2K Games"));
        gamesList.add(createGame("XCOM 2", "A tactical strategy game where players lead a resistance group against an alien occupation, managing soldiers and combat strategies.", "Firaxis Games", "2K Games"));
        gamesList.add(createGame("Divinity: Original Sin 2", "A role-playing game with tactical combat and a rich story, where players can engage in turn-based battles and explore a vast world.", "Larian Studios", "Larian Studios"));
        gamesList.add(createGame("Mount & Blade II: Bannerlord", "A strategy RPG where players control a character in a medieval world, engaging in large-scale battles, trading, and politics.", "TaleWorlds Entertainment", "TaleWorlds Entertainment"));
        gamesList.add(createGame("Warframe", "A free-to-play online game where players control members of the Tenno, ancient warriors with advanced technology, battling in space and on various planets.", "Digital Extremes", "Digital Extremes"));
        gamesList.add(createGame("Borderlands 3", "A first-person shooter with RPG elements, set in a post-apocalyptic world where players choose from different characters with unique skills and abilities.", "Gearbox Software", "2K Games"));
        gamesList.add(createGame("Fallout 4", "An open-world RPG set in a post-apocalyptic world, where players explore the wasteland, battle enemies, and rebuild civilization.", "Bethesda Game Studios", "Bethesda Softworks"));
        gamesList.add(createGame("The Last of Us", "A post-apocalyptic action-adventure game where players control Joel and Ellie, survivors of a fungal infection, navigating a dangerous world.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Minecraft Dungeons", "An action RPG set in the Minecraft universe, where players explore dungeons, battle enemies, and collect loot.", "Mojang", "Mojang"));
        gamesList.add(createGame("Fortnite: Battle Royale", "A fast-paced, free-to-play battle royale game where 100 players fight to be the last one standing, featuring building mechanics.", "Epic Games", "Epic Games"));
        gamesList.add(createGame("Tetris Effect", "A mesmerizing, visually stunning version of the classic puzzle game Tetris, with immersive music and hypnotic visuals.", "Monstars", "Enhance Games"));
        gamesList.add(createGame("Celeste", "A challenging platformer where players control Madeline, climbing a mountain while avoiding obstacles, with a heartfelt story.", "Maddy Makes Games", "Maddy Makes Games"));
        gamesList.add(createGame("The Legend of Zelda: Breath of the Wild", "An open-world action-adventure game where players control Link, exploring a vast world to defeat Calamity Ganon and save Princess Zelda.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("The Legend of Zelda: Ocarina of Time", "A classic action-adventure game where players control Link on his quest to stop Ganondorf from taking control of Hyrule.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("The Legend of Zelda: Majora's Mask", "An action-adventure game where Link tries to stop the moon from crashing into the world in a mysterious time-loop scenario.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("The Legend of Zelda: Wind Waker", "An action-adventure game where Link sails across a vast ocean to find his sister, battling enemies and discovering ancient secrets.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("The Legend of Zelda: Twilight Princess", "A darker action-adventure where Link transforms into a wolf, fighting through the world of Hyrule to save Princess Zelda.", "Nintendo", "Nintendo"));
        gamesList.add(createGame("God of War: Ragnarok", "A story-driven action game following Kratos and his son Atreus as they navigate Norse mythology and face the coming of Ragnarok.", "Santa Monica Studio", "Sony Interactive Entertainment"));
        gamesList.add(createGame("God of War (2018)", "An action-adventure game where Kratos and his son, Atreus, embark on a journey through Norse mythology to spread the ashes of his wife.", "Santa Monica Studio", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Ghost of Tsushima", "An open-world action game where you play as Jin Sakai, a samurai trying to defend his homeland from the Mongol invasion.", "Sucker Punch Productions", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Hades", "A rogue-like dungeon crawler where you play as Zagreus, the son of Hades, battling your way through the Underworld to escape.", "Supergiant Games", "Supergiant Games"));
        gamesList.add(createGame("Cuphead", "A run-and-gun platformer with a 1930s cartoon aesthetic, where players control Cuphead and Mugman in their quest to repay a debt to the devil.", "Studio MDHR", "Studio MDHR"));
        gamesList.add(createGame("Sekiro: Shadows Die Twice", "An action-adventure game set in feudal Japan, where players control a shinobi named Sekiro, mastering combat and stealth to rescue his master.", "FromSoftware", "Activision"));
        gamesList.add(createGame("Cyberpunk 2077", "A dystopian open-world RPG set in Night City, where players control V, a mercenary trying to survive in a high-tech, dangerous future.", "CD Projekt Red", "CD Projekt"));
        gamesList.add(createGame("The Witcher 3: Wild Hunt", "An open-world RPG where players control Geralt of Rivia, a monster hunter, on a quest to find his adopted daughter, facing political intrigue and supernatural forces.", "CD Projekt Red", "CD Projekt"));
        gamesList.add(createGame("The Witcher 2: Assassins of Kings", "An action RPG where players control Geralt of Rivia, facing political intrigue, monsters, and moral choices in a medieval fantasy world.", "CD Projekt Red", "CD Projekt"));
        gamesList.add(createGame("The Witcher", "The original RPG where players control Geralt of Rivia, beginning his journey as a monster hunter in a world filled with magic, war, and mythical creatures.", "CD Projekt Red", "CD Projekt"));
        gamesList.add(createGame("Metal Gear Solid V: The Phantom Pain", "An open-world action game where players control Venom Snake, navigating a world filled with military conflict and unraveling a global conspiracy.", "Kojima Productions", "Konami"));
        gamesList.add(createGame("Metal Gear Solid 2: Sons of Liberty", "An action game where players control Raiden and later Snake, fighting against a mysterious terrorist group and uncovering dark conspiracies.", "Kojima Productions", "Konami"));
        gamesList.add(createGame("Metal Gear Solid 3: Snake Eater", "A stealth action game set in the Cold War, where players control Naked Snake in a mission to stop a nuclear threat and betrayals in the Soviet Union.", "Kojima Productions", "Konami"));
        gamesList.add(createGame("Red Dead Redemption", "An open-world action game where you control John Marston, a former outlaw on a quest to capture his old gang members.", "Rockstar Games", "Rockstar Games"));
        gamesList.add(createGame("Red Dead Redemption 2", "A prequel to Red Dead Redemption, players control Arthur Morgan, an outlaw in the Van der Linde gang, navigating a changing world.", "Rockstar Games", "Rockstar Games"));
        gamesList.add(createGame("Dragon Age: Inquisition", "A fantasy RPG where players control the Inquisitor, gathering allies to battle a growing evil and prevent the collapse of the world.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("Dragon Age: Origins", "A dark fantasy RPG where players control a Grey Warden, leading a group of heroes to stop an ancient evil known as the Blight.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("Dragon Age II", "A fantasy RPG that follows Hawke, a refugee who rises to power in Kirkwall, facing political intrigue, warfare, and moral dilemmas.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("Mass Effect 3", "A sci-fi RPG where players control Commander Shepard, leading a team to fight against a galactic threat and making choices that affect the galaxy's fate.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("Mass Effect 2", "A sci-fi RPG where players control Commander Shepard, leading a team to fight against a galactic threat and making choices that affect the galaxy's fate.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("Mass Effect", "A sci-fi RPG where players control Commander Shepard, exploring space, meeting alien species, and battling a galactic threat.", "BioWare", "Electronic Arts"));
        gamesList.add(createGame("StarCraft II", "A real-time strategy game where players control one of three factions in a war across the galaxy, focusing on tactical battles and resource management.", "Blizzard Entertainment", "Blizzard Entertainment"));
        gamesList.add(createGame("Halo: Combat Evolved", "A first-person shooter where players control Master Chief, fighting against alien forces to protect humanity and uncover a mystery.", "Bungie", "Microsoft Game Studios"));
        gamesList.add(createGame("Halo 2", "The sequel to Halo: Combat Evolved, where Master Chief continues his fight against the Covenant and faces new challenges.", "Bungie", "Microsoft Game Studios"));
        gamesList.add(createGame("Halo 3", "The final chapter in the original Halo trilogy, where Master Chief leads the fight against the Covenant in a battle to save humanity.", "Bungie", "Microsoft Game Studios"));
        gamesList.add(createGame("Halo 4", "A new chapter for Master Chief, battling against a new threat from ancient Forerunner technology and dealing with the aftermath of the Covenant war.", "343 Industries", "Microsoft Game Studios"));
        gamesList.add(createGame("Tomb Raider (2013)", "An action-adventure game where players control Lara Croft, uncovering the secrets of a mysterious island while fighting for survival.", "Crystal Dynamics", "Square Enix"));
        gamesList.add(createGame("Tomb Raider: Legend", "An action-adventure game where Lara Croft sets out on a globe-trotting adventure to uncover the mystery of her mother’s disappearance.", "Crystal Dynamics", "Eidos Interactive"));
        gamesList.add(createGame("Tomb Raider: Anniversary", "A reimagining of the original Tomb Raider, following Lara Croft’s first adventure as she searches for the Lost Island of Yamatai.", "Crystal Dynamics", "Eidos Interactive"));
        gamesList.add(createGame("Bioshock Infinite", "A first-person shooter set in the floating city of Columbia, where players control Booker DeWitt as he tries to rescue a mysterious woman.", "Irrational Games", "2K Games"));
        gamesList.add(createGame("Bioshock 2", "A first-person shooter set in the underwater city of Rapture, where players control Subject Delta, a Big Daddy, to uncover the truth behind Rapture’s fall.", "2K Marin", "2K Games"));
        gamesList.add(createGame("Bioshock", "A first-person shooter where players control Jack, exploring the underwater city of Rapture to uncover its secrets and battle mutated inhabitants.", "Irrational Games", "2K Games"));
        gamesList.add(createGame("Uncharted: Drake’s Fortune", "An action-adventure game where players control Nathan Drake, a treasure hunter searching for El Dorado, facing dangers and ancient mysteries.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Uncharted 2: Among Thieves", "The second entry in the Uncharted series, where Nathan Drake embarks on a global adventure to find a powerful artifact while battling mercenaries.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Uncharted 3: Drake’s Deception", "The third entry in the series, where Nathan Drake embarks on another treasure hunt, facing dangerous enemies and uncovering ancient secrets.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Uncharted 4: A Thief’s End", "The fourth entry in the series, where Nathan Drake comes out of retirement for one last treasure hunt, facing new dangers and moral dilemmas.", "Naughty Dog", "Sony Interactive Entertainment"));
        gamesList.add(createGame("Far Cry 5", "A first-person shooter where players control a sheriff’s deputy trying to stop a doomsday cult from taking over Montana.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("Far Cry 4", "A first-person shooter set in a fictional Himalayan region, where players fight against a ruthless dictator using guerrilla tactics and powerful weapons.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("Far Cry 3", "A first-person shooter where players control Jason Brody, stranded on a tropical island, and trying to rescue his friends from pirates and mercenaries.", "Ubisoft", "Ubisoft"));
        gamesList.add(createGame("The Elder Scrolls V: Skyrim", "An open-world RPG where players control the Dragonborn, embarking on a quest to stop a dragon apocalypse while exploring the vast world of Skyrim.", "Bethesda Game Studios", "Bethesda Softworks"));
        gamesList.add(createGame("The Elder Scrolls IV: Oblivion", "An open-world RPG where players control the Hero of Kvatch, stopping a daedric invasion and exploring the fantasy world of Tamriel.", "Bethesda Game Studios", "Bethesda Softworks"));
        gamesList.add(createGame("The Elder Scrolls III: Morrowind", "An open-world RPG set in the alien landscape of Vvardenfell, where players must stop an ancient evil while engaging in political intrigue.", "Bethesda Game Studios", "Bethesda Softworks"));
        gamesList.add(createGame("Halo 5: Guardians", "A first-person shooter where players control both Master Chief and Spartan Locke, fighting against a new threat to humanity.", "343 Industries", "Microsoft Game Studios"));
        gamesList.add(createGame("Dark Souls", "An action RPG known for its challenging combat, where players control the Undead in a quest to uncover the truth of the world and fight powerful foes.", "FromSoftware", "Bandai Namco Entertainment"));
        gamesList.add(createGame("Dark Souls II", "The sequel to Dark Souls, where players control the Unkindled, navigating a brutal world and defeating powerful enemies.", "FromSoftware", "Bandai Namco Entertainment"));
        gamesList.add(createGame("Dark Souls III", "The final entry in the series, where players once again control the Unkindled in a journey to end the curse and defeat powerful foes.", "FromSoftware", "Bandai Namco Entertainment"));
    }

    private GameReviewObject createGame(String gameName, String description, String developer, String publisher) {
        Random random = new Random();
        int score = random.nextInt(11); // Random score between 0 and 10
        String review = generateReview(score, description);
        return new GameReviewObject(gameName, description, developer, publisher, score, review);
    }

    private String generateReview(int score, String description) {
        String review;
        if (score >= 8) {
            review = "This game is an incredible experience. " + description + " The gameplay mechanics are fantastic and the story is gripping.";
        } else if (score >= 5) {
            review = "The game has its highs and lows. " + description + " While some aspects are enjoyable, others feel a bit lacking.";
        } else {
            review = "Unfortunately, this game doesn't live up to expectations. " + description + " The gameplay feels clunky and the story doesn't captivate.";
        }
        return review;
    }

    public List<GameReviewObject> getGamesList() {
        return gamesList;
    }

    public GameReviewObject getRandomReviewObject(){
        Random random = new Random();
        int gameIndex = random.nextInt(gamesList.size() + 1);
        return gamesList.get(gameIndex);
    }

    public List<GameReviewObject> getReviewObjectsWithScore(int gameScore) {
        return gamesList.stream()
                .filter(reviewObject -> reviewObject.getGameScore() == gameScore)
                .collect(Collectors.toList());
    }

    public List<GameReviewObject> getReviewObjectsFromScore(int gameScore) {
        return gamesList.stream()
                .filter(reviewObject -> reviewObject.getGameScore() >= gameScore)
                .collect(Collectors.toList());
    }
}

