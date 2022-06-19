import java.util.Arrays;
import java.util.Comparator;

public class MatchHistory
{
    private final Match[] matches;
    private int size;

    //Construtor(cria a lista)
    public MatchHistory()
    {
        matches = new Match[20];
        size = 0;
    }

    //Insere uma partida na lista
    public void insertMatch(Match match)
    {
        if(size < matches.length)
        {
            matches[size] = match;
            size++;
        }
    }

    //Exclui uma partida da lista
    public void removeMatch(int posicao)
    {
        if(posicao >= 0 && posicao < size)
        {
            if (size - 1 >= posicao) {
                System.arraycopy(matches, posicao + 1, matches, posicao, size - 1 - posicao);
            }
            size--;
        }
    }

    //Altera uma partida da lista
    public void changeMatch(int index, Match match)
    {
        if(index >= 0 && index < size)
        {
            matches[index] = match;
        }
    }

    //Consulta uma partida da lista
    public Match getMatch(int index)
    {
        if(index >= 0 && index < size)
        {
            return matches[index];
        }
        return null;
    }

    //Exibe a lista de partidas
    public void displayMatches()
    {
        for (int i = 0; i < size; i++)
        {
            Console.log(i + "- " + matches[i].toString());
        }
    }

    //Ordena a lista de partidas por data (Bubble sort usando a data como chave)
    public void orderMatchesByDate()
    {
        Comparator<Match> matchDateComparator =
                (match1, match2) -> 30 * (match1.getMonth() - match2.getMonth()) + (match1.getDay() - match2.getDay());
        Arrays.sort(matches, matchDateComparator);
    }
}
