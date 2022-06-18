public class TabelaDePontuacao
{
    private Pontuacao[] tabela;
    private int tamanho;

    public TabelaDePontuacao()
    {
        tabela = new Pontuacao[20];
        tamanho = 0;
    }


    public void insereClube(Pontuacao pontuacao)
    {
        if (tamanho < tabela.length)
        {
            tabela[tamanho] = pontuacao;
            tamanho++;
        }
    }


    public void excluiClube(int posicao)
    {
        if (posicao >= 0 && posicao < tamanho)
        {
            for (int i = posicao; i < tamanho - 1; i++)
            {
                tabela[i] = tabela[i + 1];
            }
            tamanho--;
        }
    }


    public void alteraClube(int posicao, Pontuacao pontuacao)
    {
        if(posicao >= 0 && posicao < tamanho)
        {
            tabela[posicao] = pontuacao;
        }
    }


    public Pontuacao consultaClube(int posicao)
    {
        if(posicao >= 0 && posicao < tamanho)
        {
            return tabela[posicao];
        }
        return null;
    }

    public void exibeTabela()
    {
        for (int i = 1; i <= tamanho; i++)
        {
            System.out.println(tabela[i].toString());
        }
    }

    public void ordenaPorPontuacao()
    {
        for (int i = 0; i < tamanho; i++)
        {
            for (int j = 0; j < tamanho - 1; j++)
            {
                if(tabela[j].getTotalPontos() > tabela[j + 1].getTotalPontos())
                {
                    Pontuacao aux = tabela[j];
                    tabela[j] = tabela[j + 1];
                    tabela[j + 1] = aux;
                }
            }
        }
    }


}