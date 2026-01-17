import java.util.HashMap;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else if (playerName.equals(player2Name))
            player2Score++;
        else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {

        NormalizedScore normalizedScore = getNormalizedScores(player1Score, player2Score);

        var lookup = getNormalizedScoreLookupTable();

        if (lookup.containsKey(normalizedScore)) {
            return lookup.get(normalizedScore);
        } else {
            throw new IllegalArgumentException("Invalid score.");
        }
    }

    private static HashMap<NormalizedScore, String> getNormalizedScoreLookupTable() {
        var lookup = new HashMap<NormalizedScore, String>();
        lookup.put(new NormalizedScore(0, 0), "Love-All");
        lookup.put(new NormalizedScore(0, 1), "Love-Fifteen");
        lookup.put(new NormalizedScore(0, 2), "Love-Thirty");
        lookup.put(new NormalizedScore(0, 3), "Love-Forty");
        lookup.put(new NormalizedScore(0, 4), "Win for player2");
        lookup.put(new NormalizedScore(1, 0), "Fifteen-Love");
        lookup.put(new NormalizedScore(1, 1), "Fifteen-All");
        lookup.put(new NormalizedScore(1, 2), "Fifteen-Thirty");
        lookup.put(new NormalizedScore(1, 3), "Fifteen-Forty");
        lookup.put(new NormalizedScore(1, 4), "Win for player2");
        lookup.put(new NormalizedScore(2, 0), "Thirty-Love");
        lookup.put(new NormalizedScore(2, 1), "Thirty-Fifteen");
        lookup.put(new NormalizedScore(2, 2), "Thirty-All");
        lookup.put(new NormalizedScore(2, 3), "Thirty-Forty");
        lookup.put(new NormalizedScore(2, 4), "Win for player2");
        lookup.put(new NormalizedScore(3, 0), "Forty-Love");
        lookup.put(new NormalizedScore(3, 1), "Forty-Fifteen");
        lookup.put(new NormalizedScore(3, 2), "Forty-Thirty");
        lookup.put(new NormalizedScore(3, 3), "Deuce");
        lookup.put(new NormalizedScore(3, 4), "Advantage player2");
        lookup.put(new NormalizedScore(4, 0), "Win for player1");
        lookup.put(new NormalizedScore(4, 1), "Win for player1");
        lookup.put(new NormalizedScore(4, 2), "Win for player1");
        lookup.put(new NormalizedScore(4, 3), "Advantage player1");
        lookup.put(new NormalizedScore(4, 4), "Deuce");
        return lookup;
    }

    private static NormalizedScore getNormalizedScores(int player1Score, int player2Score) {
        while (player1Score > 4 || player2Score > 4) {
            player1Score--;
            player2Score--;
        }
        return new NormalizedScore(player1Score, player2Score);
    }

    private record NormalizedScore(int player1Score, int player2Score) {
    }
}
