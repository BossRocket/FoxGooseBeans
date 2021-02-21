// This is a brief backtracking solution to the fox, goose, and bag of beans problem.
// Read about it here: https://en.wikipedia.org/wiki/Wolf,_goat_and_cabbage_problem
// In particular, this solution uses recursive depth-first search.

import java.util.*;

public class FoxGooseBeans
{
	// boolean array to represent the positions of the farmer, fox, goose, and beans (in that order)
	static boolean positions[] = new boolean[4];

	static HashSet<Integer> states = new HashSet<>();

	static ArrayList<String> moves = new ArrayList<>();

	public static boolean hashThese(boolean positions[])
	{
		int temp = 0;

		// For each value in 'positions', this creates a unique value based on their combined value.
		for (int i = 0; i < positions.length; i++)
			if (positions[i])
				temp += (i + 1) * (i + 1);

		return states.add(temp);
	}

	public static void printMoves()
	{
		for (int i = moves.size() - 1; i >= 0; i--)
			System.out.println(moves.get(i));
	}

	// Moves the farmer and farm-thing (if applicable) to the opposite side
	public static void setPositions(int i)
	{
		if (positions[i] == true)
		{
			positions[i] = false;
			positions[0] = false;
		}
		else
		{
			positions[i] = true;
			positions[0] = true;
		}
	}

	// Recursive DFS method
	public static boolean findRoute(boolean positions[])
	{
		// Base case
		if (positions[0] && positions[1] && positions[2] && positions[3])
			return true;

		// Checks if we've already been to this state
		if (hashThese(positions) == false)
			return false;

		for (int i = 0; i < positions.length; i++)
		{
			// If the farmer is not on the side of i, nothing can happen.
			if (positions[0] != positions[i])
				continue;

			// Try moving positions.
			setPositions(i);

			// Revert if these positions are bad.
			if (positions[1] == positions[2] && positions[1] != positions[0])
			{
				setPositions(i);
				continue;
			}

			if (positions[2] == positions[3] && positions[2] != positions[0])
			{
				setPositions(i);
				continue;
			}

			if (findRoute(positions))
			{
				if (i != 0)
				{
					positions[0] = positions[i];

					if (i == 1)
						moves.add("Move Farmer and Fox");

					if (i == 2)
						moves.add("Move Farmer and Goose");

					if (i == 3)
						moves.add("Move Farmer and Beans");
				}

				else
				{
					moves.add("Move Farmer");
				}

				return true;
			}

			else
			{
				setPositions(i);
			}
		}

		// If no solution was found, return a sad false value :(
		return false;
	}

	public static void main(String [] args)
	{
		if (findRoute(positions))
			printMoves();
		else
			System.out.println("No solution was found :(");
	}
}
