public class Carta
{
    public Carta(String palo, String color, String valor)
    {
        this.palo = palo;
        this.color = color;
        this.valor = valor;
    }
    private String palo;
    private String color;
    private String valor;

    public String getPalo()
    {
        return this.palo;
    }

    public String getColor()
    {
        return this.color;
    }

    public String getValor()
    {
        return this.valor;
    }
}

