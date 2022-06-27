package brasileirao.model;

import brasileirao.model.collections.CustomList;

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
	}

	/**
	 * Exclui uma partida da lista
	 */
	public final void removeMatch(int index) throws IndexOutOfBoundsException
	{
		matches.remove(index);
	}

	/**
	 * Altera uma partida da lista
	 */
	public final void changeMatch(int index, Match match) throws IndexOutOfBoundsException
	{
		matches.set(index, match);
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
	public String toString()
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

	/**
	 * Itera pela lista de partidas e aplica o seu resultado para ambos os times que jogaram nela
	 * */
	public final void updateScores() {
		for (int i = 0; i < matches.size(); i++)
		{
			Match match = matches.get(i);
			if (match == null) continue;
			match.applyResultsToTeams();
		}
	}
}
