package com.brasileirao.application;

import com.brasileirao.model.Team;
import com.brasileirao.utility.Console;
import com.brasileirao.model.Match;
import com.brasileirao.model.ScoreTable;

import java.util.Scanner;

/**
 * Esta classe deve ser o ponto de entrada da aplicação. É nessa classe que devem
 * estar as interações com o usuário. Deve prever um menu com as operações
 * mencionadas. A cada manipulação de uma partida no sistema, a tabela de
 * pontuação deve ser atualizada.
 */
public class Interface
{
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
				printMatchHistoryOptions();
				Scanner matchOptionScanner = new Scanner(System.in);
				String matchOptionInput = matchOptionScanner.next();
				switch (matchOptionInput)
				{
					case "1":
					{
						String homeTeamName = Console.getStringUserInput("Insira o nome do time da casa");
						String awayTeamName = Console.getStringUserInput("Insira o nome do time de fora");
						int homeTeamGoals = Console.getUserIntInput("Insira a quantidade de gols do time da casa",
																	"Entrada inválida.");

						int awayTeamGols = Console.getUserIntInput("Insira a quantidade de gols do time de fora",
																"Entrada inválida.");

						int gameMonth = Console.getUserIntInput("Insira o mês do jogo",  "Entrada inválida",
								"Valor inválido. Insira um valor de 1 a 12",
								1,13);


						int gameDay = Console.getUserIntInput("Insira o mês do jogo",  "Entrada inválida",
							"Valor inválido. Insira um valor de 1 a 31",
							1,32);

						int gameHour = Console.getUserIntInput("Insira o dia do jogo",  "Entrada inválida",
															"Valor inválido. Insira um valor de 0 a 23",
																		0,24);

						int gameMinute = Console.getUserIntInput("Insira o minuto do jogo",  "Entrada inválida",
								"Valor inválido. Insira um valor de 0 a 59",
								0,60);

					}
					case "2":
					{

					}
					case "3":
					{

					}
					case "4":
					{

					}
					case "5":
					{

					}
					case "6":
					{

					}
					default:
					{
						Console.log("Entrada inválida.");
						break;
					}

				}
			}
			case "2":
			{

			}

			default:
			{
				Console.log("Escolha inválida");
				break;
			}
		}

	}

	private static void printMatchHistoryOptions() {
		Console.log("====== MENU DE PARTIDAS ======");
		Console.log("1. Insira uma partida \n" +
					"2. Exclua uma partida \n" +
					"3. Altere uma partida \n" +
					"4. Consulte uma partida \n " +
					"5. Ordena as partidas por data \n" +
					"6. Exibe o histórico de partidas \n" +
					"");
	}
}
