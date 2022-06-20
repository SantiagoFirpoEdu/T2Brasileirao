package com.brasileirao.utility;

import java.util.Scanner;

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


	public static int getUserIntInput(String message, String errorMessage, String outOfBounds, int minValue, int maxValue)
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
		while (input <= minValue || input > maxValue)
		{
			rawInput = scanner.next();
			log(outOfBounds);
			try
			{
				input = Integer.parseInt(rawInput);
			}
			catch (NumberFormatException exception)
			{
				log(errorMessage);
			}
		}
		return input;
	}


	public static String getStringUserInput(String message)
	{
		Scanner scanner = new Scanner(System.in);
		log(message);
		String rawInput = scanner.next();

		return rawInput;
	}
}
