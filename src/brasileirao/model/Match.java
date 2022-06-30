package brasileirao.model;

import brasileirao.application.Interface;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class Match
{
	public final Calendar date;
	private final UUID homeTeamID;
	private final UUID awayTeamID;
	private int homeTeamGoals;
	private int awayTeamGoals;

	/**
	 * valida a entrada dos dados no construtor
	 */
	public Match(UUID homeTeamID,
	             UUID awayTeamID,
	             int homeTeamGoals,
	             int awayTeamGoals, MatchDate matchDate)
	{
		date = Calendar.getInstance();
		if (matchDate.day >= 1 && matchDate.day <= 31)
		{
			date.set(Calendar.DAY_OF_MONTH, matchDate.day);
		}
		if (matchDate.month >= 1 && matchDate.month <= 12)
		{
			date.set(Calendar.MONTH, matchDate.month - 1);
		}
		if (matchDate.hour >= 0 && matchDate.hour <= 23)
		{
			date.set(Calendar.HOUR_OF_DAY, matchDate.hour);
		}
		if (matchDate.minute >= 0 && matchDate.minute <= 59)
		{
			date.set(Calendar.MINUTE, matchDate.minute);
		}
		this.homeTeamID = homeTeamID;
		this.awayTeamID = awayTeamID;
		this.homeTeamGoals = homeTeamGoals;
		this.awayTeamGoals = awayTeamGoals;
	}

	@Override
	public final String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM");
		return MessageFormat.format("Partida: {0} {1} x {2} {3} {4}",
				Interface.scoreTable.getTeamNameById(homeTeamID),
				homeTeamGoals,
				awayTeamGoals,
				Interface.scoreTable.getTeamNameById(awayTeamID), formatter.format(date.getTime()));
	}

	/** Calcula os pontos que esta partida gerou para o time dado
	 * @param teamId o time que quer saber os pontos
	 * @return os pontos que o time ganhou
	 */
	public final int getPoints(UUID teamId)
	{
		if (won(teamId))
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
	 * @param teamId o time que quer saber o número de gols a favor de si
	 * @return o número de gols a favor do time dado
	 */
	public final int getGoalsFor(UUID teamId)
	{
		if (isHomeTeam(teamId))
		{
			return homeTeamGoals;
		}
		else
		{
			return awayTeamGoals;
		}
	}

	/** Verifica se o time está jogando em casa
	 * @param teamId o time que quer saber se está jogando em casa
	 * @return true se o time está jogando em casa, false se não está jogando em casa
	 */
	private boolean isHomeTeam(UUID teamId)
	{
		return Objects.equals(teamId, homeTeamID);
	}

	public final int getGoalsAgainst(UUID teamId)
	{
		if (isHomeTeam(teamId))
		{
			return awayTeamGoals;
		}
		else
		{
			return homeTeamGoals;
		}
	}

	/** Verifica se o time dado ganhou esta partida
	 * @param teamId o time que quer saber se ganhou
	 * @return true se o time ganhou, false se não ganhou
	 */
	public final boolean won(UUID teamId)
	{
		if (isHomeTeam(teamId))
		{
			return homeTeamGoals > awayTeamGoals;
		}
		else if (isAwayTeam(teamId))
		{
			return homeTeamGoals < awayTeamGoals;
		}
		else
		{
			return false;
		}
	}

	private boolean isAwayTeam(UUID teamId)
	{
		return Objects.equals(teamId, awayTeamID);
	}

	/** Verifica se a partida resultou em um empate
	 * @return true se a partida resultou em um empate, false se não resultou em um empate
	 */
	public final boolean tied()
	{
		return homeTeamGoals == awayTeamGoals;
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

	public final UUID getHomeTeamID()
	{
		return homeTeamID;
	}

	public final UUID getAwayTeamID()
	{
		return awayTeamID;
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
