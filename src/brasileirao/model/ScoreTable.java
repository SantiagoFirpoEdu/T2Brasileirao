package brasileirao.model;

import brasileirao.model.collections.CustomList;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

/** Guarda e atualiza as pontuações de todos os times do campeonato*/
public class ScoreTable {
	private final CustomList<Team> table;

	public ScoreTable()
	{
		table = new CustomList<>(20);
	}

	/**
	 * Adiciona um time para a tabela
	 * @param team o time a ser adicionado
	 */
	public final void addTeam(Team team)
	{
		table.add(team);
	}

	/**
	* Tenta alterar o nome do time com nome dado para o novo nome
	* @param oldName o nome do time a ser alterado
	* @param newName o novo nome do time
	* @return true se o nome do time foi alterado com sucesso, false se o time não foi encontrado
	*/
	public final boolean changeName(String oldName, String newName) {
		Predicate<Team> namePredicate = (Team team) -> team.getName().equals(oldName);
		int index = table.linearSearch(namePredicate);
		if (index == CustomList.INVALID_INDEX)
		{
			return false;
		}
		Objects.requireNonNull(getTeam(index)).setName(newName);
		return true;
	}

	/**
	 * Busca o time utilizando o índice da tabela
	 * @param index o índice do time na tabela
	 * @return o time buscado, null se o índice não existir
	 */
	public final Team getTeam(int index)
	{
		if (index >= 0 && index < table.size())
		{
			return table.get(index);
		}
		return null;
	}

	/**
	 * Imprime a tabela inteira
	 * @return a tabela em formato de string
	 */
	@Override
	public final String toString()
	{
		orderByPoints();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < table.size(); i++)
		{
			Team team = table.get(i);
			if (team == null) continue;
			stringBuilder.append(i + 1);
			stringBuilder.append("o lugar\n");
			stringBuilder.append(team);
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}

	/**Ordena a lista de times utilizando um comparador de pontos e bubble sort */
	public final void orderByPoints()
	{
		Comparator<Team> pointsComparison = (team1, team2) -> team2.getPoints() - team1.getPoints();
		table.bubbleSort(pointsComparison);
	}

	/**
	 * @param teamName o nome do time a ser procurado
	 * @return true se a lista contém um time com o nome dado, false se a lista não o contém
	 */
	public final boolean containsTeam(String teamName)
	{
		int index = getTeamIndexByName(teamName);
		return index != CustomList.INVALID_INDEX;
	}

	/**
	 * Realiza uma busca linear na lista de times
	 * @param homeTeamName o nome do time desejado
	 * @return O índice do time desejado. CustomList.INVALID_INDEX se o elemento não existe
	 */
	public final int getTeamIndexByName(String homeTeamName)
	{
		return table.linearSearch((Team team) -> homeTeamName.equals(team.getName()));
	}
  /** Busca um time pelo nome e retorna a sua referência.
   * @param homeTeamName o nome do time desejado
   * @return A referência do time desejado. null se o elemento não existe
   */
  public final Team getTeamByName(String homeTeamName)
  {
		int index = getTeamIndexByName(homeTeamName);
		if (index == CustomList.INVALID_INDEX)
		{
			return null;
		}
		return table.get(index);
	}

	public String getTeamNameById(UUID homeTeamID)
	{
		Predicate<Team> idPredicate = (Team team) -> team.getId() == homeTeamID;
		int index = table.linearSearch(idPredicate);
		if (index == CustomList.INVALID_INDEX)
		{
			return "Time inválido";
		}
		return table.get(index).getName();
	}
}