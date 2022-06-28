package brasileirao.model;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

/**
* Modela a pontuação de um time
*/
public class Team
{
	private UUID id;
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

	public Team(UUID id, String name) throws IllegalArgumentException
	{
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
	public final void addMatch(Match match) throws IllegalArgumentException
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
	public final String toString()
	{
		return MessageFormat.format(
				"Código do Clube: {0},\n Nome do Clube: ''{1}'',\nGols pró: {2},
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

	public final String getName()
	{
		return name;
	}



	public final void setName(String name)
	{
		if (name.isBlank()) return;
		this.name = name;
	}

	public final int getPoints()
	{
		return points;
	}

	public final int getMatchesPlayed()
	{
		return matchesPlayed;
	}

	public final int getWins()
	{
		return wins;
	}

	public final int getDraws()
	{
		return draws;
	}

	public final int getLosses()
	{
		return losses;
	}

	public final int getGoalsFor()
	{
		return goalsFor;
	}

	public final int getGoalsAgainst()
	{
		return goalsAgainst;
	}

	public final int getGoalBalance()
	{
		return goalBalance;
	}

	public final double getWinRatePointWise()
	{
		return winRatePointWise;
	}

	public final UUID getId()
	{
		return id;
	}

	public final void setId(UUID id)
	{
		this.id = new UUID(id.getMostSignificantBits(), id.getLeastSignificantBits());
	}
	@Override
	public final boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null || getClass() != other.getClass())
		{
			return false;
		}
		Team team = (Team) other;
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
	public final int hashCode()
	{
		return Objects.hash(id, name, goalsFor, goalsAgainst, matchesPlayed, wins, draws, losses, points, goalBalance, winRatePointWise);
	}
}
