package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12)); // score 16
            players.add(new Player("Lemieux", "PIT", 45, 54)); // score 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // score 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // score 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // score 124

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsPlayer() {
        assertEquals(stats.search("Yzerman").getName(), "Yzerman");
    }

    @Test
    public void searchReturnsNullWhenPlayerIsNotFound() {
        assertNull(stats.search("Selanne"));
    }

    @Test
    public void teamReturnsListOfTeamMembers() {
        List<Player> edmonton = stats.team("EDM");
        for (Player player : edmonton) {
            assertEquals(player.getTeam(), "EDM");
        }
    }

    @Test
    public void topScorersReturnsTopScorers() {
        List<Player> topScorers = stats.topScorers(3);
        assertEquals(topScorers.get(0).getName(), "Gretzky");
        assertEquals(topScorers.get(1).getName(), "Lemieux");
        assertEquals(topScorers.get(2).getName(), "Yzerman");
    }
}
