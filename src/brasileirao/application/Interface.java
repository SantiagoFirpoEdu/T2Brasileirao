package brasileirao.application;


import brasileirao.model.*;
import brasileirao.utility.Console;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * Esta classe deve ser o ponto de entrada da aplicação. É nessa classe que devem
 * estar as interações com o usuário. Deve prever uma ementa com as operações
 * mencionadas. A cada manipulação de uma partida no sistema, a tabela de
 * pontuação deve ser atualizada.
 */
public class Interface
{
	public static final String INVALID_INPUT_DEFAULT_MESSAGE = "Entrada inválida.";
	public static final MatchHistory matchHistory = new MatchHistory();
	public static final ScoreTable scoreTable = new ScoreTable();

	private Interface() {}

	public static void main(String[] args)
	{
		boolean shouldQuit = false;
		while (!shouldQuit)
		{
			String[] inputTokens = Console.getStringUserInput(
                    """
					Paleta de comandos:
					
					adicionar partida - adiciona nova partida ao histórico e atualiza resultados da tabela
					alterar nome do time |[nome antigo]| |[nome novo]| - tenta alterar o nome do time antigo para o nome novo
					consultar partidas - exibe o histórico completo de partidas ordenadas por data
					consultar times - exibe a tabela de times ordenada por pontuação
					consultar time |[nome do time]| - tenta exibir informações sobre o time inserido
					consultar partida
					sair - sai do programa
					""")
					.split("\\|");
			if (inputTokens.length == 0)
			{
				Console.log("Escolha inválida. Saindo do programa..");
				continue;
			}
			switch (inputTokens[0])
			{
				case "adicionar partida", "adicionar partida " ->
				{
					Match newMatch = getMatchInput();
					matchHistory.insertMatch(newMatch);
					Console.log(MessageFormat.format("Partida criada com sucesso: {0}", newMatch));
				}
				case "alterar nome do time", "alterar nome do time " -> changeTeamName(inputTokens);
				case "consultar partidas", "consultar partidas " -> Console.log(matchHistory.toString());
				case "consultar times", "consultar times " -> Console.log(scoreTable.toString());
				case "consultar time", "consultar time " -> checkTeam(inputTokens);
				case "consultar partida", "consultar partida " -> checkMatch();
				case "sair" ->
				{
					Console.log("Saindo do programa..");
					shouldQuit = true;
				}
				default -> Console.log("Comando inválido. Por favor, tente novamente.");
			}
		}

	}

/**
* Comando para alterar o nome de um time.
 * @param inputTokens array de tokens de input separados utilzando split
*/
	private static void changeTeamName(String[] inputTokens)
	{
		if (inputTokens.length < 4)
		{
			Console.log("Erro de sintaxe. Por favor use a sintaxe seguinte para este comando: alterar nome do time \"[nome antigo]\" \"[nome novo]\"");
		}
		else if (scoreTable.changeName(inputTokens[1], inputTokens[3]))
		{
			Console.log(MessageFormat.format("Time ''{0}'' alterado para ''{1}'' com sucesso.", inputTokens[1], inputTokens[3]));
		}
		else
		{
			Console.log(MessageFormat.format("ERRO. Time ''{0}'' não foi encontrado", inputTokens[1]));
		}
	}

/**
* Comando para consultar uma partida
*/
	private static void checkMatch()
	{
		MatchDate dateSearch = getMatchDateInput();
		Match match = matchHistory.getMatchByDate(dateSearch);
		if (match == null)
		{
			Console.log("Partida não encontrada.");
		}
		else
		{
			Console.log(match.toString());
		}
	}

/**
* Comando para consultar um time
 * @param inputTokens array de tokens de input separados utilizando split
*/
	private static void checkTeam(String[] inputTokens)
	{
		if (inputTokens.length < 2)
		{
			Console.log("Erro de sintaxe. Por favor use a sintaxe seguinte para este comando: consultar time \"[nome do time]\"");
		}
		else
		{
			String teamName = inputTokens[1];
			Team team = scoreTable.getTeamByName(teamName);
			if (team == null)
			{
				Console.log("Time não encontrado.");
			}
			else
			{
				Console.log(team.toString());
			}
		}
	}

/**
* Utilitário para obter input para gerar uma partida
 * @return
*/
	private static Match getMatchInput()
	{
		Team homeTeam = getUserInputTeam("Por favor, insira o nome do time da casa: ");
		Team awayTeam = getUserInputTeam("Por favor, insira o nome do time visitante: ");
		int homeTeamGoals = Console.getUserIntInput("Por favor, insira a quantidade de gols do time da casa: ",
				INVALID_INPUT_DEFAULT_MESSAGE, "Entrada inválida, por favor insira um número entre 0 e 100.", 0, 101);
		int awayTeamGoals = Console.getUserIntInput("Por favor, insira a quantidade de gols do time visitante: ",
				INVALID_INPUT_DEFAULT_MESSAGE, "Entrada inválida, por favor insira um número entre 0 e 100.", 0, 101);
		MatchDate matchDate = getMatchDateInput();
		return new Match(homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchDate);
	}

/**
* Utilitário para obter input para gerar uma data de partida
 * @return a data de partida
*/
	private static MatchDate getMatchDateInput()
	{
		int matchDay = Console.getUserIntInput("Por favor, insira o dia da partida (entre 01 e 31)", INVALID_INPUT_DEFAULT_MESSAGE, "Número inválido, por favor insira um número entre 01 e 31.", 1, 32);
		int matchMonth = Console.getUserIntInput("Por favor, insira o mês da partida (entre 01 e 12)", INVALID_INPUT_DEFAULT_MESSAGE, "Número inválido, por favor insira um número entre 1 e 12.", 1, 13);
		int matchHour = Console.getUserIntInput("Por favor, insira a hora da partida (entre 0 e 23)", INVALID_INPUT_DEFAULT_MESSAGE, "Número inválido, por favor insira um número entre 0 e 23.", 0, 24);
		int matchMinute = Console.getUserIntInput("Por favor, insira o minuto da partida (entre 0 e 59)", INVALID_INPUT_DEFAULT_MESSAGE, "Número inválido, por favor insira um número entre 0 e 59.", 0, 60);
		return new MatchDate(matchDay, matchMonth, matchHour, matchMinute);
	}

/**
* Utilitário para obter input para buscar um time ou gerá-lo caso não exista
 * @param message mensagem a ser exibida para o usuário
 * @return time gerado
*/
	private static Team getUserInputTeam(String message)
	{
		String homeTeamName = Console.getStringUserInput(message);
		Team homeTeam;
		Team teamByName = scoreTable.getTeamByName(homeTeamName);
		if (teamByName == null)
		{
			homeTeam = new Team(UUID.randomUUID(), homeTeamName);
			scoreTable.addTeam(homeTeam);
		}
		else
		{
			homeTeam = teamByName;
		}
		return homeTeam;
	}
}
