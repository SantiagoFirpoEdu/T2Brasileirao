package com.brasileirao.model;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Match
{
	private final Calendar date;
	private Team homeTeam;
	private Team awayTeam;
	private int homeTeamGoals;
	private int awayTeamGoals;

	/**
	 * valida a entrada dos dados no construtor
	 */
	public Match(Team homeTeam,
	             Team awayTeam,
	             int homeTeamGoals,
	             int awayTeamGoals, MatchDate matchDate)
	{
		date = Calendar.getInstance();
		if (matchDate.day() >= 1 && matchDate.day() <= 31)
		{
			date.set(Calendar.DAY_OF_MONTH, matchDate.day());
		}
		if (matchDate.month() >= 1 && matchDate.month() <= 12)
		{
			date.set(Calendar.MONTH, matchDate.month() - 1);
		}
		if (matchDate.hour() >= 0 && matchDate.hour() <= 23)
		{
			date.set(Calendar.HOUR_OF_DAY, matchDate.hour());
		}
		if (matchDate.minute() >= 0 && matchDate.minute() <= 59)
		{
			date.set(Calendar.MINUTE, matchDate.minute());
		}
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamGoals = homeTeamGoals;
		this.awayTeamGoals = awayTeamGoals;
	}

	public Match()
	{
		date = Calendar.getInstance();
	}

	@Override
	public final String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm dd/MM");
		return MessageFormat.format("Partida: {0} {1} x {2} {3} {4}",
				homeTeam.getName(),
				homeTeamGoals,
				awayTeamGoals,
				awayTeam.getName(), formatter.format(date.getTime()));
	}

	/** Verifica se o time perdeu esta partida
	 * @param team o time que quer saber se perdeu
	 * @return true se o time perdeu, false se não perdeu
	 */
	private boolean lost(Team team)
	{
		if (isHomeTeam(team))
		{
			return homeTeamGoals < awayTeamGoals;
		}
		return homeTeamGoals > awayTeamGoals;
	}

	/** Calcula os pontos que esta partida gerou para o time dado
	 * @param team o time que quer saber os pontos
	 * @return os pontos que o time ganhou
	 */
	public final int getPoints(Team team)
	{
		if (won(team))
		{
			return 3;
		}
		else if (tied())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	/** Retorna o número de gols a favor do time dado
	 * @param team o time que quer saber o número de gols a favor de si
	 * @return o número de gols a favor do time dado
	 */
	public final int getGoalsFor(Team team)
	{
		if (isHomeTeam(team))
		{
			return homeTeamGoals;
		}
		else
		{
			return awayTeamGoals;
		}
	}

	/** Verifica se o time está jogando em casa
	 * @param team o time que quer saber se está jogando em casa
	 * @return true se o time está jogando em casa, false se não está jogando em casa
	 */
	private boolean isHomeTeam(Team team)
	{
		return Objects.equals(team, homeTeam);
	}

	public final int getGoalsAgainst(Team team)
	{
		if (isHomeTeam(team))
		{
			return awayTeamGoals;
		}
		else
		{
			return homeTeamGoals;
		}
	}

	/** Verifica se o time dado ganhou esta partida
	 * @param team o time que quer saber se ganhou
	 * @return true se o time ganhou, false se não ganhou
	 */
	public final boolean won(Team team)
	{
		if (isHomeTeam(team))
		{
			return homeTeamGoals > awayTeamGoals;
		}
		return homeTeamGoals < awayTeamGoals;
	}

	/** Verifica se a partida resultou em um empate
	 * @return true se a partida resultou em um empate, false se não resultou em um empate
	 */
	public final boolean tied()
	{
		return homeTeamGoals == awayTeamGoals;
	}

	/**
	 * Aplica os resultados desta partida para os dois times que jogaram nela
	 * */
	public final void applyResultsToTeams()
	{
		homeTeam.addMatch(this);
		awayTeam.addMatch(this);
	}

	public final int getDay()
	{
		return date.get(Calendar.DAY_OF_MONTH);
	}

	public final void setDay(int day)
	{
		date.set(Calendar.DAY_OF_MONTH, day);
	}

	public final int getMonth()
	{
		return date.get(Calendar.DAY_OF_MONTH) + 1;
	}

	public final void setMonth(int month)
	{
		date.set(Calendar.MONTH, month - 1);
	}

	public final int getHour()
	{
		return date.get(Calendar.HOUR_OF_DAY);
	}

	public final void setHour(int hour)
	{
		date.set(Calendar.HOUR_OF_DAY, hour);
	}

	public final int getMinute()
	{
		return date.get(Calendar.MINUTE);
	}

	public final void setMinute(int minute)
	{
		date.set(Calendar.MINUTE, minute);
	}

	public final Team getHomeTeam()
	{
		return homeTeam;
	}

	public final void setHomeTeam(Team homeTeam)
	{
		this.homeTeam = homeTeam;
	}

	public final Team getAwayTeam()
	{
		return awayTeam;
	}

	public final void setAwayTeam(Team awayTeam)
	{
		this.awayTeam = awayTeam;
	}

	public final int getHomeTeamGoals()
	{
		return homeTeamGoals;
	}

	public final void setHomeTeamGoals(int homeTeamGoals)
	{
		this.homeTeamGoals = homeTeamGoals;
	}

	public final int getAwayTeamGoals()
	{
		return awayTeamGoals;
	}

	public final void setAwayTeamGoals(int awayTeamGoals)
	{
		this.awayTeamGoals = awayTeamGoals;
	}
}
