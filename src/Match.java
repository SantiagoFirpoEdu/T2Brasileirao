import java.text.MessageFormat;
import java.util.Objects;

public class Match
{
	private int day;
	private int month;
	private int hour;
	private int minute;
	private Team homeTeam;
	private Team awayTeam;
	private int homeTeamGoals;
	private int awayTeamGoals;

	/**
	 * valida a entrada dos dados no construtor
	 */
	public Match(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, int day, int month, int hour, int minute)
	{
		if (day >= 1 && day <= 31)
		{
			this.day = day;
		}
		if (month >= 1 && month <= 12)
		{
			this.month = month;
		}
		if (hour >= 0 && hour <= 24)
		{
			this.hour = hour;
		}
		if (minute >= 0 && minute <= 59)
		{
			this.minute = minute;
		}
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamGoals = homeTeamGoals;
		this.awayTeamGoals = awayTeamGoals;
	}

	public String toString()
	{
		return MessageFormat.format("Partida: {0} {1} x {2} {3} {4}/{5} {6}:{7}",
				homeTeam.getName(),
				homeTeamGoals,
				awayTeamGoals,
				awayTeam.getName(),
				day,
				month,
				hour,
				minute);
	}

	/** Verifica se o time perdeu esta partida
	 * @param team o time que quer saber se perdeu
	 * @return true se o time perdeu, false se não perdeu
	 */
	private boolean lost(Team team) {
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
	public int getPoints(Team team) {
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
	public int getGoalsFor(Team team) {
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
	private boolean isHomeTeam(Team team) {
		return Objects.equals(team, homeTeam);
	}

	public int getGoalsAgainst(Team team)
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
	public boolean won(Team team) {
		if (isHomeTeam(team))
		{
			return homeTeamGoals > awayTeamGoals;
		}
		return homeTeamGoals < awayTeamGoals;
	}

	/** Verifica se esta partida resultou em um empate
	 * @return true se a partida resultou em um empate, false se não resultou em um empate
	 */
	public boolean tied() {
		return homeTeamGoals == awayTeamGoals;
	}

	/**
	 * Aplica os resultados desta partida para os dois times que jogaram nela
	 * */
	public void applyResultsToTeams() {
		homeTeam.addMatch(this);
		awayTeam.addMatch(this);
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	public Team getHomeTeam()
	{
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam)
	{
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam()
	{
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam)
	{
		this.awayTeam = awayTeam;
	}

	public int getHomeTeamGoals() {
		return homeTeamGoals;
	}

	public void setHomeTeamGoals(int homeTeamGoals)
	{
		this.homeTeamGoals = homeTeamGoals;
	}

	public int getAwayTeamGoals()
	{
		return awayTeamGoals;
	}

	public void setAwayTeamGoals(int awayTeamGoals)
	{
		this.awayTeamGoals = awayTeamGoals;
	}
}
