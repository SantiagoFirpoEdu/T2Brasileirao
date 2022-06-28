package brasileirao.model;

import brasileirao.model.collections.CustomList;

import java.util.Calendar;
import java.util.Comparator;

public class MatchHistory
{
	private final CustomList<Match> matches;

	/**
	 * Construtor (cria a lista)
	 */
	public MatchHistory()
	{
		matches = new CustomList<>();
	}

	/**
	 * Insere uma partida na lista
	 */
	public final void insertMatch(Match match)
	{
		matches.add(match);
		match.applyResultsToTeams();
	}

	/**
	 * Consulta uma partida da lista
	 */
	public final Match getMatch(int index) throws IndexOutOfBoundsException
	{
		return matches.get(index);
	}

	/**
	 * Exibe a lista de partidas
	 */
	@Override
	public final String toString()
	{
		orderMatchesByDate();
		StringBuilder teamsDisplay = new StringBuilder();
		int size = matches.size();
		for (int i = 0; i < size; i++)
		{
			Match match = matches.get(i);
			if (match == null) continue;
			teamsDisplay.append(i);
			teamsDisplay.append('-');
			teamsDisplay.append(match);
			teamsDisplay.append('\n');
		}
		return teamsDisplay.toString();
	}

	/**
	 * Ordena a lista de partidas por data (Merge Sort usando a própria implementação do Java com um comparador
	 * personalizado)
	 */
	public final void orderMatchesByDate()
	{
		Comparator<Match> matchDateComparator = Comparator.comparing(match -> match.date);
		matches.bubbleSort(matchDateComparator);
	}

  /** Retorna uma referência para um objeto Match que tenha ocorrido na data especificada.
   * @param dateSearch A data que será usada para buscar o jogo.
   * @return Um objeto Match que tenha ocorrido na data especificada. null se não existir um jogo na data especificada.
   */
  public final Match getMatchByDate(MatchDate dateSearch) {
		int index = matches.linearSearch((Match match) ->
				match.date.get(Calendar.DAY_OF_MONTH) == dateSearch.day()
			&& match.date.get(Calendar.MONTH) == dateSearch.month() - 1
			&& match.date.get(Calendar.HOUR_OF_DAY) == dateSearch.hour()
			&& match.date.get(Calendar.MINUTE) == dateSearch.minute());
		if (index == CustomList.INVALID_INDEX)
		{
			return null;
		}
		return matches.get(index);
	}
}
