
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private String team;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return assists + goals;
    }

    @Override
    public String toString() {
        String[] player = new String[]{
            name,
            team,
            String.valueOf(goals),
            String.valueOf(assists),
            String.valueOf(this.getPoints())
        };
        return String.join(", ", player);
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(player.getPoints(), this.getPoints());
    }

}
