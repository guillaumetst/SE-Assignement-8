/*First code smell :
    The first obvious code smell would be Long Method. Indeed the length of the function is disproportionate in regards
    to what it does.
    The method is 76 lines long and just returns the string of the current score.
*/

/* Second code smell :
    The second code smell is the overuse of if statements and duplicate code. This complexifies the reading of the method and adds
    unnecessary lines.
    Many of the conditions can be regrouped in to in statement with improved conditions.
    The same applies to the values assignment to the variables P1res and P2res.
 */

/* Third code smell :
    The third code smell is Shotgun Surgery. This means that any small change reqiueres a lot of editing.
    For example we decide to change the counting of points we would need to change 31 occurrences in the smelly code
    In the reformed code such a change would only need 12 changes.
 */
import java.util.HashMap;

public class TennisGame {
    public int P1point = 0;
    public int P2point = 0;

    public String P1res = "";
    public String P2res = "";
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    /*
    public String getScore() {
        String score = "";
        if (P1point == P2point && P1point < 4) {
            if (P1point == 0)
                score = "Love";
            if (P1point == 1)
                score = "Fifteen";
            if (P1point == 2)
                score = "Thirty";
            score += "-All";
        }
        if (P1point == P2point && P1point >= 3)
            score = "Deuce";

        if (P1point > 0 && P2point == 0) {
            if (P1point == 1)
                P1res = "Fifteen";
            if (P1point == 2)
                P1res = "Thirty";
            if (P1point == 3)
                P1res = "Forty";

            P2res = "Love";
            score = P1res + "-" + P2res;
        }
        if (P2point > 0 && P1point == 0) {
            if (P2point == 1)
                P2res = "Fifteen";
            if (P2point == 2)
                P2res = "Thirty";
            if (P2point == 3)
                P2res = "Forty";

            P1res = "Love";
            score = P1res + "-" + P2res;
        }

        if (P1point > P2point && P1point < 4) {
            if (P1point == 2)
                P1res = "Thirty";
            if (P1point == 3)
                P1res = "Forty";
            if (P2point == 1)
                P2res = "Fifteen";
            if (P2point == 2)
                P2res = "Thirty";
            score = P1res + "-" + P2res;
        }
        if (P2point > P1point && P2point < 4) {
            if (P2point == 2)
                P2res = "Thirty";
            if (P2point == 3)
                P2res = "Forty";
            if (P1point == 1)
                P1res = "Fifteen";
            if (P1point == 2)
                P1res = "Thirty";
            score = P1res + "-" + P2res;
        }

        if (P1point > P2point && P2point >= 3) {
            score = "Advantage player1";
        }

        if (P2point > P1point && P1point >= 3) {
            score = "Advantage player2";
        }

        if (P1point >= 4 && P2point >= 0 && (P1point - P2point) >= 2) {
            score = "Win for player1";
        }
        if (P2point >= 4 && P1point >= 0 && (P2point - P1point) >= 2) {
            score = "Win for player2";
        }
        return score;
    }
*/


    /* Refactoring steps :
    - First, we identified what needed to be outside of any conditional statements

    - Secondly, we created a hashmap containing the strings for each score. This allows us to give values to
      P1res and P2res without having if statements for each score. This cuts down the number of if statements inside of
      each of the later statements.

    - Thirdly, we simplified all the conditional expressions to reduce the length and complexity of the method.
      We identified that there were two main conditions that separate the method into two main parts. The first being
      when the two players don't have the same amount of points and the second being the analogous inverse.

      In the original code we realized that the win conditions could be simplified and put first. If no win was attained
      by the players then we check for advantage. Since we know that the players do not have the same points we can
      simply show the players scores.

      In the second parts, we tread the cases where the players have the same score. There are two outcomes, when the
      players have less than 40 points and when they have 40 points or more.
     */


    public String getScore() {
        String score = "";

        HashMap<Integer,String> Scores = new HashMap<Integer,String>();
        Scores.put(0,"Love");
        Scores.put(1,"Fifteen");
        Scores.put(2,"Thirty");
        Scores.put(3,"Forty");
        Scores.put(4,"Advantage");

        P1res = Scores.get(P1point);
        P2res = Scores.get(P2point);

        if (!(P1point == P2point)) {

            if (P1point > P2point && P1point - P2point >= 2 && P1point >= 4 ) score = "Win for player1";
            else if (P2point > P1point && P2point - P1point >= 2 && P2point >= 4 ) score = "Win for player2";

            else if (P1point > P2point && P2point >= 3) score = "Advantage player1";
            else if (P2point > P1point && P1point >= 3) score = "Advantage player2";

            else score = P1res + "-" + P2res;
        }
        else {
            if (P1point < 3) score = P1res + "-All";
            else score = "Deuce";
        }

        return score;
    }


    public void SetP1Score(int number) {

        for (int i = 0; i < number; i++) {
            P1Score();
        }

    }

    public void SetP2Score(int number) {

        for (int i = 0; i < number; i++) {
            P2Score();
        }

    }

    public void P1Score() {
        P1point++;
    }

    public void P2Score() {
        P2point++;
    }

    //equals() instead of ==
    public void wonPoint(String player) {
        if (player.equals("player1"))
            P1Score();
        else
            P2Score();
    }
}