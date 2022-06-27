package brasileirao.model;

import brasileirao.model.collections.CustomList;
import brasileirao.utility.Console;

import java.util.Comparator;
import java.util.function.Predicate;

public class ScoreTable
{
    private final CustomList<Team> table;

    public ScoreTable()
    {
        table = new CustomList<>(20);
    }


    public final void addTeam(Team team)
    {
        table.add(team);
    }


    public final void removeTeam(int index)
    {
        try
        {
            table.remove(index);
        }
        catch (IndexOutOfBoundsException e)
        {
            Console.log("O índice inserido é inválido.");
        }
    }

    private void changeName(String oldName, String newName)
    {
        Predicate<Team> namePredicate = (Team team) -> team.getName().equals(oldName);
        int index = table.linearSearch(namePredicate);
        if (index == CustomList.INVALID_INDEX) return;
        table.get(index).setName(newName);
    }


    public final void changeTeam(int index, Team team)
    {
        if (index >= 0 && index < table.size())
        {
            table.set(index, team);
        }
    }


    public final Team getTeam(int index)
    {
        if (index >= 0 && index < table.size())
        {
            return table.get(index);
        }
        return null;
    }

    @Override
    public String toString()
    {
        orderByPoints();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < table.size(); i++)
        {
            Team team = table.get(i);
            if (team == null) continue;
            stringBuilder.append(team);
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public final void orderByPoints()
    {
        Comparator<Team> pointsComparison = (team1, team2) -> team2.getPoints() - team1.getPoints();
        table.bubbleSort(pointsComparison);
    }


}