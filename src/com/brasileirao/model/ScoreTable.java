package com.brasileirao.model;

import com.brasileirao.utility.Console;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreTable
{
    private final ArrayList<Team> table;

    public ScoreTable()
    {
        table = new ArrayList<>();
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
        for (Team team : table)
        {
            stringBuilder.append(team.toString());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public final void orderByPoints()
    {
        Comparator<Team> pointsComparison = (team1, team2) -> team2.getPoints() - team1.getPoints();
        table.sort(pointsComparison);
    }


}