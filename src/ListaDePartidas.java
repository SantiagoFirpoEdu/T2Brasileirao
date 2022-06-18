public class ListaDePartidas
{
    private Partida[] lista;
    private int tamanho;

    //Construtor(cria a lista)
    public ListaDePartidas()
    {
        lista = new Partida[20];
        tamanho = 0;
    }

    //Insere uma partida na lista
    public void inserePartida(Partida partida)
    {
        if(tamanho < lista.length)
        {
            lista[tamanho] = partida;
            tamanho++;
        }
    }

    //Exclui uma partida da lista
    public void excluiPartida(int posicao)
    {
        if(posicao >= 0 && posicao < tamanho)
        {
            for(int i = posicao; i < tamanho - 1; i++)
            {
                lista[i] = lista[i + 1];
            }
            tamanho--;
        }
    }

    //Altera uma partida da lista
    public void alteraPartida(int posicao, Partida partida)
    {
        if(posicao >= 0 && posicao < tamanho)
        {
            lista[posicao] = partida;
        }
    }

    //Consulta uma partida da lista
    public Partida consultaPartida(int posicao)
    {
        if(posicao >= 0 && posicao < tamanho)
        {
            return lista[posicao];
        }
        return null;
    }

    //Exibe a lista de partidas
    public void exibeLista()
    {
        for (int i = 0; i < tamanho; i++)
        {
            System.out.println(i + "- " + lista[i].toString());
        }
    }

    //Ordena a lista de partidas por data (Bubble sort usando a data como chave)
    public void ordenaLista()
    {
        for (int i = 0; i < tamanho; i++)
        {
            for (int j = 0; j < tamanho - 1; j++)
            {
                if (lista[j].getMes() == lista[j + 1].getMes())
                    if(lista[j].getDia() > lista[j + 1].getDia())
                    {
                        Partida aux = lista[j];
                        lista[j] = lista[j + 1];
                        lista[j + 1] = aux;
                    }
            }
        }
    }

    //
}
