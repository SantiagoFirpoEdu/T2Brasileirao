package brasileirao.application;


import brasileirao.model.*;
import brasileirao.utility.Console;

import java.util.Scanner;
import java.util.UUID;

/**
 * Esta classe deve ser o ponto de entrada da aplicação. É nessa classe que devem
 * estar as interações com o usuário. Deve prever um menu com as operações
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
		Scanner input = new Scanner(System.in);
		Console.log("Insira uma opção entre 1 e 2");
		boolean shouldQuit = false;
		while (!shouldQuit)
		{
			String choice = input.next();
			switch (choice)
			{
				case "1" -> matchHistory();
				case "2" -> Console.log(scoreTable.toString());
				default ->
				{
					Console.log("Escolha inválida");
					shouldQuit = true;
				}
			}
		}

	}

	private static void matchHistory()
	{
		//Inserir partida, alterar partida,
		printMatchHistoryOptions();
		Scanner matchOptionScanner = new Scanner(System.in);
		String matchOptionInput = matchOptionScanner.next();
		switch (matchOptionInput)
		{
			case "1" -> addMatch();
			case "2" -> removeMatch();
			case "4" -> viewMatch();
			case "6" -> Console.log(matchHistory.toString());
			default -> Console.log(INVALID_INPUT_DEFAULT_MESSAGE);
		}
	}

	private static void viewMatch()
	{
		int input = Console.getUserIntInput("Insira o índice da partida que deseja remover", INVALID_INPUT_DEFAULT_MESSAGE);
		try
		{
			matchHistory.getMatch(input);
		}
		catch (IndexOutOfBoundsException exception) {
			Console.log(INVALID_INPUT_DEFAULT_MESSAGE);
		}
	}


	private static void removeMatch()
	{
		int input = Console.getUserIntInput("Insira o índice da partida que deseja remover", INVALID_INPUT_DEFAULT_MESSAGE);
		try
		{
			matchHistory.removeMatch(input);
		}
		catch (IndexOutOfBoundsException exception)
		{
			Console.log(INVALID_INPUT_DEFAULT_MESSAGE);
		}
	}

	private static void addMatch()
	{
		String homeTeamName = Console.getStringUserInput("Insira o nome do time da casa");
		String awayTeamName = Console.getStringUserInput("Insira o nome do time de fora");
		int homeTeamGoals = Console.getUserIntInput("Insira a quantidade de gols do time da casa", INVALID_INPUT_DEFAULT_MESSAGE);

		int awayTeamGols = Console.getUserIntInput("Insira a quantidade de gols do time de fora", INVALID_INPUT_DEFAULT_MESSAGE);

		int gameMonth = Console.getUserIntInput("Insira o mês do jogo", INVALID_INPUT_DEFAULT_MESSAGE,
				"Valor inválido. Insira um valor de 1 a 12",
				1,13);


		int gameDay = Console.getUserIntInput("Insira o dia do jogo",  INVALID_INPUT_DEFAULT_MESSAGE,
			"Valor inválido. Insira um valor de 1 a 31",
			1,32);

		int gameHour = Console.getUserIntInput("Insira a hora do jogo",  INVALID_INPUT_DEFAULT_MESSAGE,
											"Valor inválido. Insira um valor de 0 a 23",
														0,24);

		int gameMinute = Console.getUserIntInput("Insira o minuto do jogo",  INVALID_INPUT_DEFAULT_MESSAGE,
				"Valor inválido. Insira um valor de 0 a 59",
				0,60);
		Team team1 = new Team(UUID.randomUUID(), homeTeamName);
		Team team2 = new Team(UUID.randomUUID(), awayTeamName);
		Match match = new Match(team1, team2, homeTeamGoals, awayTeamGols, new MatchDate(gameDay, gameMonth, gameHour, gameMinute));
		matchHistory.insertMatch(match);
		Console.log("Partida adicionada com sucesso! " + match);
	}

	private static void printMatchHistoryOptions() {
		Console.log("""
					====== MENU DE PARTIDAS ======
					1. Insira uma partida
					2. Altere o nome de um time
					3. Consulte uma partida
					4. Ordena as partidas por data
					5. Exibe o histórico de partidas
					""");
	}
}
