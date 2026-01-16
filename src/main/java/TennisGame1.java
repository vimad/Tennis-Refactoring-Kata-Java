
public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            m_score1 += 1;
        else
            m_score2 += 1;
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
        return getSingleScore(m_score1) + "-" + getSingleScore(m_score2);
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
        return m_score1 == m_score2;
    }

    private boolean isOnePlayerHasAdvantageOrWon() {
        return m_score1 >= 4 || m_score2 >= 4;
    }

    private String getScoreWhenOnePlayerHasAdvantageOrWon() {
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) return "Advantage player1";
        else if (minusResult == -1) return "Advantage player2";
        else if (minusResult >= 2) return "Win for player1";
        else return "Win for player2";
    }

    private String updateScoreWhenBothEqual() {
        return switch (m_score1) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }
}
