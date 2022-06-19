import java.util.Objects;

public class Team
{
    private final int id;
    private String name;
    private int goalsFor;
    private int goalsAgainst;
    private int matchesTotal;
    private int wins;
    private int draws;
    private int losses;
    private int points;
    private int goalBalance;
    private double winRatePointWise;

    public Team(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.matchesTotal = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;
        this.goalBalance = 0;
        this.winRatePointWise = 0;
    }

    //Atualiza toda a tabela (exceto código e nome do time)
    public void addMatch(Match match)
    {
        if (goalsFor >= 0 && goalsAgainst >= 0) {
            incrementMatchesTotal();
            updateGoalBalance(goalsFor, goalsAgainst);
            updateVictoriesOrLosses(goalsFor, goalsAgainst);
            updatePoints();
        }
    }

    //Atualiza o total de jogos
    private void incrementMatchesTotal()
    {
        matchesTotal++;
    }

    //Atualiza viórias e derrotas
    public void updateVictoriesOrLosses(int goalsFor, int goalsAgainst)
    {
        if (goalsFor > goalsAgainst)
        {
            wins += 1;
        }
        else if (goalsAgainst > goalsFor)
        {
            losses += 1;
        }
        else
        {
            draws += 1;
        }
    }

    //Atualiza número de pontos e aproveitamento
    public void updatePoints()
    {
        points = 3 * wins + draws;
        winRatePointWise = points / (3.0 * matchesTotal) * 100.0;
    }

    /**
     * Atualiza número de gols pró e contra e saldo de gols
     * @param goalsFor gols a favor do time de casa
     * @param goalsAgainst gols contra o time de casa
     */
    public void updateGoalBalance(int goalsFor, int goalsAgainst)
    {
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        goalBalance += (goalsFor - goalsAgainst);
    }

    //Escreve todas as informações na tela
    @Override
    public String toString()
    {
        return " Código do Clube:" + id +
                ", Nome do Clube:'" + name + '\'' +
                ", Gols pró:" + goalsFor +
                ", Gols contra:" + goalsAgainst +
                ", Total de jogos:" + matchesTotal +
                ", Total de vitórias:" + wins +
                ", Total de empates:" + draws +
                ", Total de derrotas:" + losses +
                ", Total de pontos:" + points +
                ", Saldo de gols: " + goalBalance +
                ", Aproveitamento: " + winRatePointWise + '%';
    }

    //Todos os getters e o setter do nome do Clube
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getGoalsFor()
    {
        return goalsFor;
    }

    public int getGoalsAgainst()
    {
        return goalsAgainst;
    }

    public int getMatchesTotal()
    {
        return matchesTotal;
    }

    public int getWins()
    {
        return wins;
    }

    public int getDraws()
    {
        return draws;
    }

    public int getLosses()
    {
        return losses;
    }

    public int getPoints()
    {
        return points;
    }

    public int getGoalBalance()
    {
        return goalBalance;
    }

    public double getWinRatePointWise()
    {
        return winRatePointWise;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Team team = (Team) o;
        return id == team.id
            && goalsFor == team.goalsFor
            && goalsAgainst == team.goalsAgainst
            && matchesTotal == team.matchesTotal
            && wins == team.wins
            && draws == team.draws
            && losses == team.losses
            && points == team.points && goalBalance == team.goalBalance && Double.compare(team.winRatePointWise, winRatePointWise) == 0 && name.equals(team.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, goalsFor, goalsAgainst, matchesTotal, wins, draws, losses, points, goalBalance, winRatePointWise);
    }
}
