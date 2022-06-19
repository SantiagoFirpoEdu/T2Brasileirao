import java.util.Arrays;
import java.util.Comparator;

public class ScoreTable
{
    private final Team[] table;
    private int size;

    public ScoreTable()
    {
        table = new Team[20];
        size = 0;
    }


    public void addTeam(Team team)
    {
        if (size < table.length)
        {
            table[size] = team;
            size++;
        }
    }


    public void removeTeam(int index)
    {
        if (index >= 0 && index < size)
        {
            if (size - 1 - index >= 0)
            {
                System.arraycopy(table, index + 1, table, index, size - 1 - index);
            }
            size--;
        }
    }


    public void changeTeam(int index, Team team)
    {
        if(index >= 0 && index < size)
        {
            table[index] = team;
        }
    }


    public Team getTeam(int index)
    {
        if(index >= 0 && index < size)
        {
            return table[index];
        }
        return null;
    }

    public void displayTable()
    {
        for (int i = 1; i <= size; i++)
        {
            Console.log(table[i].toString());
        }
    }

    public void orderByPoints()
    {
        Comparator<Team> pointsComparison = (team1, team2) -> team2.getPoints() - team1.getPoints();
        Arrays.sort(table, pointsComparison);
    }


}