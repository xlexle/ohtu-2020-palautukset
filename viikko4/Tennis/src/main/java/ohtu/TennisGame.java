package ohtu;

import java.util.Map;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    private Map<Integer, String> scoreStrings = Map.of(
        0, "Love",
        1, "Fifteen",
        2, "Thirty",
        3, "Forty"
    );

    private Map<Integer, String> tiedScoreStrings = Map.of(
        0, "Love-All",
        1, "Fifteen-All",
        2, "Thirty-All",
        3, "Forty-All",
        4, "Deuce"
    );

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name) {
            player1Score += 1;
        } else if (playerName == player2Name) {
            player2Score += 1;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return tiedScoreStrings.get(player1Score);
        }

        if (player1Score >= 4 || player2Score >= 4) {
            return advantageOrWin();
        }

        return scoreStrings.get(player1Score) + "-" + scoreStrings.get(player2Score);
    }

    private String advantageOrWin() {
        int difference = Math.abs(player1Score - player2Score);
        String leader = player1Score > player2Score ? player1Name : player2Name;

        if (difference == 1) {
            return "Advantage " + leader;
        }

        return "Win for " + leader;
    }

}
