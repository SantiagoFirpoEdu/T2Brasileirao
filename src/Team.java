import java.text.MessageFormat;
import java.util.Objects;

public class Team
{
	private int id;
	private String name;
	private int points;
	private int matchesPlayed;
	private int wins;
	private int draws;
	private int losses;
	private int goalsFor;
	private int goalsAgainst;
	private int goalBalance;
	private double winRatePointWise;

	public Team(int id, String name) throws IllegalArgumentException
	{
		if (id < 0)
		{
			throw new IllegalArgumentException("Id can't be lower than 0");
		}
		this.setId(id);
		this.name = name.isBlank() ? ("Time " + id) : name;
		points = 0;
		matchesPlayed = 0;
		wins = 0;
		draws = 0;
		losses = 0;
		goalsFor = 0;
		goalsAgainst = 0;
		goalBalance = 0;
		winRatePointWise = 0.0;
	}

	/**
	 * Aplica os resultados de uma partida para o seu estado atual, calculando os pontos, gols marcados e gols sofridos
	 * @param match o resultado da partida
	 * @throws IllegalArgumentException se match for null
	 */
	public void addMatch(Match match) throws IllegalArgumentException
	{
		if (match == null)
		{
			throw new IllegalArgumentException("match parameter can't be null");
		}
		matchesPlayed++;
		goalsFor += match.getGoalsFor(this);
		goalsAgainst += match.getGoalsAgainst(this);
		points += match.getPoints(this);
		winRatePointWise = points / (matchesPlayed * 3.0) * 100.00;
		if (match.won(this))
		{
			wins++;
		}
		else if (match.tied())
		{
			draws++;
		}
		else
		{
			losses++;
		}
		goalBalance = goalsFor - goalsAgainst;
	}


	@Override
	public String toString()
	{
		return MessageFormat.format(
				"""
			   Código do Clube: {0},
			   Nome do Clube: ''{1}'',
			   Gols pró: {2},
			   Gols contra: {3},
			   Total de jogos: {4},
			   Total de vitórias: {5},
			   Total de empates: {6},
			   Total de derrotas: {7},
			   Total de pontos: {8},
			   Saldo de gols: {9},
			   Aproveitamento: {10}%
			   """,
				id,
				name,
				goalsFor,
				goalsAgainst,
				matchesPlayed,
				wins,
				draws,
				losses,
				points,
				goalBalance,
				winRatePointWise);
	}

	public String getName()
	{
		return name;
	}

	private void setName(String name)
	{
		if (name.isBlank()) return;
		this.name = name;
	}

	public int getPoints()
	{
		return points;
	}

	public int getMatchesPlayed()
	{
		return matchesPlayed;
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

	public int getGoalsFor()
	{
		return goalsFor;
	}

	public int getGoalsAgainst()
	{
		return goalsAgainst;
	}

	public int getGoalBalance()
	{
		return goalBalance;
	}

	public double getWinRatePointWise()
	{
		return winRatePointWise;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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
				&& matchesPlayed == team.matchesPlayed
				&& wins == team.wins
				&& draws == team.draws
				&& losses == team.losses
				&& points == team.points && goalBalance == team.goalBalance && Double.compare(team.winRatePointWise, winRatePointWise) == 0 && name.equals(team.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, goalsFor, goalsAgainst, matchesPlayed, wins, draws, losses, points, goalBalance, winRatePointWise);
	}
}
