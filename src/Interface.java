/*
esta classe deve ser a da aplicação. É nessa classe que devem
estar as interações com o usuário. Deve prever um menu com as operações
mencionadas. A cada manipulação de uma partida no sistema, a tabela de
pontuação deve ser atualizada.
 */


public class Interface
{
    public static void main(String[] args)
    {
        ListaDePartidas lista = new ListaDePartidas();
        lista.inserePartida(new Partida("Ceramica", "Inter", 1, 0, 1, 1, 16, 30));
        lista.inserePartida(new Partida("Ceramica", "Inter", 1, 0, 1, 1, 16, 30));


        lista.exibeLista();
    }
}
