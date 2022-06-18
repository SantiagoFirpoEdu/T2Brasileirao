public class Pontuacao
{

    private int codClube;
    private String nomeClube;
    private int golsPro;
    private int golsContra;
    private int totalJogos;
    private int totalVitorias;
    private int totalEmpates;
    private int totalDerrotas;
    private int totalPontos;
    private int saldoGols;
    private double aproveitamento;

    public Pontuacao(int codClube, String nomeClube)
    {
        this.codClube = codClube;
        this.nomeClube = nomeClube;
        this.golsPro = 0;
        this.golsContra = 0;
        this.totalJogos = 0;
        this.totalVitorias = 0;
        this.totalEmpates = 0;
        this.totalDerrotas = 0;
        this.totalPontos = 0;
        this.saldoGols = 0;
        this.aproveitamento = 0;
    }

    //Atualiza toda a tabela (exceto código e nome do time)
    public void atualizarStatusGeral(int golsPro, int golsContra)
    {
        if (golsPro >= 0 && golsContra >= 0) {
            atualizarTotalDeJogos();
            atualizarSaldo(golsPro, golsContra);
            PontuarVitoriaDerrota(golsPro, golsContra);
            atualizarPontuacao();
        }
    }

    //Atualiza o total de jogos
    private void atualizarTotalDeJogos()
    {
        totalJogos++;
    }

    //Atualiza viórias e derrotas
    public void PontuarVitoriaDerrota(int golsPro, int golsContra)
    {
        if (golsPro > golsContra)
        {
            totalVitorias += 1;
        }
        else if (golsContra > golsPro)
        {
            totalDerrotas += 1;
        }
        else
        {
            totalEmpates += 1;
        }
    }

    //Atualiza número de pontos e aproveitamento
    public void atualizarPontuacao()
    {
        totalPontos = 3 * totalVitorias + totalEmpates;
        aproveitamento = ((totalPontos / (3.0 * totalJogos))) * 100.0;
    }

    /**
     * Atualiza número de gols pró e contra e saldo de gols
     * @param golsPro gols a favor do time de casa
     * @param golsContra gols contra o time de casa
     */
    public void atualizarSaldo(int golsPro, int golsContra)
    {
        this.golsPro += golsPro;
        this.golsContra += golsContra;
        saldoGols += (golsPro - golsContra);
    }

    //Escreve todas as informações na tela
    @Override
    public String toString()
    {
        return " Código do Clube:" + codClube +
                ", Nome do Clube:'" + nomeClube + '\'' +
                ", Gols pró:" + golsPro +
                ", Gols contra:" + golsContra +
                ", Total de jogos:" + totalJogos +
                ", Total de vitórias:" + totalVitorias +
                ", Total de empates:" + totalEmpates +
                ", Total de derrotas:" + totalDerrotas +
                ", Total de pontos:" + totalPontos +
                ", Saldo de gols: " + saldoGols +
                ", Aproveitamento: " + aproveitamento + '%';
    }

    //Todos os getters e o setter do nome do Clube
    public int getCodClube()
    {
        return codClube;
    }

    public String getNomeClube()
    {
        return nomeClube;
    }

    public int getGolsPro()
    {
        return golsPro;
    }

    public int getGolsContra()
    {
        return golsContra;
    }

    public int getTotalJogos()
    {
        return totalJogos;
    }

    public int getTotalVitorias()
    {
        return totalVitorias;
    }

    public int getTotalEmpates()
    {
        return totalEmpates;
    }

    public int getTotalDerrotas()
    {
        return totalDerrotas;
    }

    public int getTotalPontos()
    {
        return totalPontos;
    }

    public int getSaldoGols()
    {
        return saldoGols;
    }

    public double getAproveitamento()
    {
        return aproveitamento;
    }

    public void setNomeClube(String nomeClube)
    {
        this.nomeClube = nomeClube;
    }

}
