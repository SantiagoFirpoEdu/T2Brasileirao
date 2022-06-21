package com.brasileirao.application;

import com.brasileirao.model.MatchDate;
import com.brasileirao.model.Team;
import com.brasileirao.utility.Console;
import com.brasileirao.model.Match;
import com.brasileirao.model.ScoreTable;

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

	private Interface() {}

	public static void main(String[] args)
	{
		ScoreTable scoreTable = new ScoreTable();
		Scanner input = new Scanner(System.in);
		Console.log("Insira blablabla");
		String choice = input.next();

		switch(choice)
		{
			case "1":
			{
				matchHistory();
			}
			case "2":
			{
				break;
			}

			default:
			{
				Console.log("Escolha inválida");
				break;
			}
		}

	}

	private static void matchHistory()
	{
		printMatchHistoryOptions();
		Scanner matchOptionScanner = new Scanner(System.in);
		String matchOptionInput = matchOptionScanner.next();
		switch (matchOptionInput)
		{
			case "1" -> addMatch();
			case "2" ->
			{
			}
			case "3" ->
			{
			}
			case "4" ->
			{
			}
			case "5" ->
			{
			}
			case "6" ->
			{
			}
			default -> Console.log(INVALID_INPUT_DEFAULT_MESSAGE);
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
		Console.log("Partida adicionada com sucesso! " + match);
	}

	private static void printMatchHistoryOptions() {
		Console.log("""
					====== MENU DE PARTIDAS ======
					1. Insira uma partida
					2. Exclua uma partida
					3. Altere uma partida
					4. Consulte uma partida
					5. Ordena as partidas por data
					6. Exibe o histórico de partidas
					""");
	}
}
