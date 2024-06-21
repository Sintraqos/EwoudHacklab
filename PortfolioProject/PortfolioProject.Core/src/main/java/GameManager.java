public class GameManager {

    // Get instance
    static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    Enums.gameState currentGameState;

    GameManager(){
        SetGameState(Enums.gameState.GAME_STATE_INITIALIZE);

        // TODO: create all needed objects
    }

    public void SetGameState(Enums.gameState currentGameState){
        this.currentGameState = currentGameState;

    }
}
