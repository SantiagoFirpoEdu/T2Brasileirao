import java.util.Objects;

public class Match
{
    private int day;
    private int month;
    private int hour;
    private int minute;
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;

    //validar a entrada dos dados no construtor
    public Match(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, int day, int month, int hour, int minute)
    {
        if (day >= 1 && day <= 31)
        {
            this.day = day;
        }
        if (month >= 1 && month <= 12)
        {
            this.month = month;
        }
        if (hour >= 0 && hour <= 24)
        {
            this.hour = hour;
        }
        if (minute >= 0 && minute <= 59)
        {
            this.minute = minute;
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public String toString()
    {
        return "Partida: " + homeTeam + " " + homeTeamGoals + " x " + awayTeamGoals + " " + awayTeam + " " + day + "/" + month + " " + hour + ":" + minute;
    }

    private boolean lost(Team team)
    {
        if (isHomeTeam(team))
        {
            return homeTeamGoals < awayTeamGoals;
        }
        return homeTeamGoals > awayTeamGoals;
    }

    public int getPoints(Team team)
    {
        if (won(team))
        {
            return 3;
        }
        else if (tied())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int getGoalsFor(Team team)
    {
        if (isHomeTeam(team))
        {
            return homeTeamGoals;
        }
        else
        {
            return awayTeamGoals;
        }
    }

    private boolean isHomeTeam(Team team)
    {
        return Objects.equals(team, homeTeam);
    }

    public int getGoalsAgainst(Team team)
    {
        if (isHomeTeam(team))
        {
            return awayTeamGoals;
        }
        else
        {
            return homeTeamGoals;
        }
    }

    public boolean won(Team team)
    {
        if (isHomeTeam(team))
        {
            return homeTeamGoals > awayTeamGoals;
        }
        return homeTeamGoals < awayTeamGoals;
    }

    public boolean tied()
    {
        return homeTeamGoals == awayTeamGoals;
    }

    public void applyResultsToTeams()
    {
        homeTeam.addMatch(this);
        awayTeam.addMatch(this);
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    public Team getHomeTeam()
    {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam()
    {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals)
    {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals()
    {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals)
    {
        this.awayTeamGoals = awayTeamGoals;
    }
}
