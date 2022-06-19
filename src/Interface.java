/**
 * esta classe deve ser a da aplicação. É nessa classe que devem
 * estar as interações com o usuário. Deve prever um menu com as operações
 * mencionadas. A cada manipulação de uma partida no sistema, a tabela de
 * pontuação deve ser atualizada.
 */
public class Interface
{
	public static void main(String[] args)
	{
		ScoreTable scoreTable = new ScoreTable();
		Team ceramica = new Team(1, "Cerâmica");
		Team internacional = new Team(2, "Internacional");
		MatchHistory history = new MatchHistory();

		history.insertMatch(new Match(ceramica, internacional, 1, 0, 1, 1, 16, 30));
		history.insertMatch(new Match(ceramica, internacional, 2, 1, 1, 1, 16, 30));
		history.insertMatch(new Match(ceramica, internacional, 5, 1, 1, 1, 16, 30));

		scoreTable.addTeam(ceramica);
		scoreTable.addTeam(internacional);

		history.updateScores();

		Console.log(history.toString());
		Console.log(scoreTable.toString());
	}
}
