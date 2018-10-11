package net.berrygames.witchrush.game;

public enum GameState {

    LOBBY,
    GAME,
    END,
    ;

    private static GameState state;

    public static void setStatus(final GameState status) {
        GameState.state = status;
    }

    public static boolean isStatus(final GameState status) {
        return GameState.state == status;
    }

    public static GameState getStatus() {
        return GameState.state;
    }
}
