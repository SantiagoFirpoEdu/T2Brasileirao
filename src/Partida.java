public class Partida
{
    private int dia;
    private int mes;
    private int hora;
    private int minuto;
    private String timeCasa;
    private String timeFora;
    private int golsCasa;
    private int golsFora;
    private String resultado;

    //validar a entrada dos dados no construtor
    public Partida(String timeCasa, String timeFora, int golsCasa, int golsFora, int dia, int mes, int hora, int minuto)
    {
        if(dia >= 1 && dia <= 31 ) this.dia = dia;
        if(mes >= 1 && mes <= 12) this.mes = mes;
        if (hora >= 0 && hora <= 24) this.hora = hora;
        if (minuto >= 0 && minuto <= 59) this.minuto = minuto;
        this.timeCasa = timeCasa;
        this.timeFora = timeFora;
        this.golsCasa = golsCasa;
        this.golsFora = golsFora;
    }

    public String toString()
    {
        return "Partida: " + timeCasa + " " + golsCasa + " x " + golsFora + " " + timeFora + " " +dia + "/" + mes + " " + hora + ":" + minuto;
    }


    public int getDia()
    {
        return dia;
    }

    public void setDia(int dia)
    {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes)
    {
        this.mes = mes;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora)
    {
        this.hora = hora;
    }

    public int getMinuto()
    {
        return minuto;
    }

    public void setMinuto(int minuto)
    {
        this.minuto = minuto;
    }

    public String getTimeCasa()
    {
        return timeCasa;
    }

    public void setTimeCasa(String timeCasa)
    {
        this.timeCasa = timeCasa;
    }

    public String getTimeFora()
    {
        return timeFora;
    }

    public void setTimeFora(String timeFora)
    {
        this.timeFora = timeFora;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public void setGolsCasa(int golsCasa)
    {
        this.golsCasa = golsCasa;
    }

    public int getGolsFora()
    {
        return golsFora;
    }

    public void setGolsFora(int golsFora)
    {
        this.golsFora = golsFora;
    }

    public String getResultado()
    {
        return resultado;
    }

    public void setResultado(String resultado)
    {
        this.resultado = resultado;
    }
}
