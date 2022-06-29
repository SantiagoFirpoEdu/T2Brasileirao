package brasileirao.utility;

import java.util.Scanner;

/** Classe utilitária de IO e Console*/
public class Console
{
	private Console() {}

	/**
	 * @param message Recebe uma String e escreve uma mensagem no console.
	 */
	public static void log(String message)
	{
		System.out.println(message);
	}

	public static int getUserIntInput(String message, String errorMessage)
	{
		Scanner scanner = new Scanner(System.in);
		log(message);
		String rawInput = scanner.next();
		int input = 0;
		try
		{
			input = Integer.parseInt(rawInput);
		}
		catch (NumberFormatException exception)
		{
			log(errorMessage);
		}
		return input;
	}


	public static int getUserIntInput(String message, String errorMessage, String outOfBounds, int minInclusive, int maxExclusive)
	{
		int input = getUserIntInput(message, errorMessage);
		while (input < minInclusive || input >= maxExclusive)
		{
			log(outOfBounds);
			input = getUserIntInput(message, errorMessage);
		}
		return input;
	}


	public static String getStringUserInput(String message)
	{
		Scanner scanner = new Scanner(System.in);
		log(message);
		return scanner.nextLine();
	}
}
