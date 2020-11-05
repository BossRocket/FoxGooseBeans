// Currently a work-in-progress. While this compiles properly, it doesn't output an actual
// solution to the problem.

// This is a brief backtracking solution to the fox, goose, and bag of beans problem.
// Read about it here: https://en.wikipedia.org/wiki/Wolf,_goat_and_cabbage_problem
// In particular, this solution uses recursive depth-first search.

import java.util.*;

public class FoxGooseBeans
{
	// boolean array to represent the positions of the farmer, fox, goose, and beans
	// ^in that order
	// false = north, true = south
	static boolean positions[] = new boolean[4];

	// HashSet for keeping track of previous states
	static HashSet<Integer> states = new HashSet<>();

	// Small arraylist to hold the order in which we moved things around.
	static ArrayList<String> moves = new ArrayList<>();

	// Method to add the contents of the array the HashSet
	public static boolean hashThese(boolean positions[])
	{
		int temp = 0;

		for (int i = 0; i < positions.length; i++)
		{
			if (positions[i])
				temp += (i + 1) * (i + 1);
		}

		return states.add(temp);
	}

	public static void printMoves()
	{
		for (int i = moves.size() - 1; i >= 0; i--)
		{
			System.out.println(moves.get(i));
		}
	}

	// Recursive DFS
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
			if (positions[i] == true)
				positions[i] = false;
			else
				positions[i] = true;

			if (positions[1] == positions[2] && positions[1] != positions[0])
			{
				if (positions[i] == true)
					positions[i] = false;
				else
					positions[i] = true;

				continue;
			}

			if (positions[2] == positions[3] && positions[2] != positions[0])
			{
				if (positions[i] == true)
					positions[i] = false;
				else
					positions[i] = true;

				continue;
			}

			// Move the farmer with the farm things
			if (i != 0)
				positions[0] = positions[i];

			if (findRoute(positions))
			{
				if (i != 0)
				{
					if (i == 1)
						moves.add("Move Fox");

					if (i == 2)
						moves.add("Move Goose");

					if (i == 3)
						moves.add("Move Beans");

					moves.add("Move Farmer and");
				}

				else
				{
					if (i == 0)
					{
						moves.add("Move Farmer");
					}
				}

				return true;
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
