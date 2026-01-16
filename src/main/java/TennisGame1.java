
public class TennisGame1 implements TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        if (isBothScoreSame()) {
            return updateScoreWhenBothEqual();
        } else if (isOnePlayerHasAdvantageOrWon()) {
            return getScoreWhenOnePlayerHasAdvantageOrWon();
        } else {
            return getScoreInTheMiddle();
        }
    }

    private String getScoreInTheMiddle() {
        return getSingleScore(player1Score) + "-" + getSingleScore(player2Score);
    }

    private String getSingleScore(int singlePlayerScoreValue) {
        return switch (singlePlayerScoreValue) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalStateException("Unexpected value: " + singlePlayerScoreValue);
        };
    }

    private boolean isBothScoreSame() {
        return player1Score == player2Score;
    }

    private boolean isOnePlayerHasAdvantageOrWon() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private String getScoreWhenOnePlayerHasAdvantageOrWon() {
        int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 1) return "Advantage " + player1Name;
        else if (scoreDifference == -1) return "Advantage " + player2Name;
        else if (scoreDifference >= 2) return "Win for " + player1Name;
        else return "Win for " + player2Name;
    }

    private String updateScoreWhenBothEqual() {
        return switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }
}
