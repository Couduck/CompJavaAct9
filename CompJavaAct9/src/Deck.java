import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Deck
{
    private HashMap<Integer, Carta> diccionarioCartas;  //HashMap que almacena la información de todas las cartas de la baraja, incluye una llave de tipo Integer con su contenido, el cual es un objeto de tipo Carta, ESTA COLECCIÓN NO SE MODIFICA
    private ArrayList<Integer> llavesCartas;    //ArrayList que almacena las llaves de las cartas que aun quedan en el Deck, el orden en el que están acomodados en este arreglo determina el orden en el que se encuentran las cartas del Deck

    public Deck()     //Constructor
    {
        //Se inicializan las colecciones
        diccionarioCartas = new HashMap<>();
        llavesCartas = new ArrayList<Integer>();

        //Iterador que servirá para asignar una llave a cada carta
        Integer key = 0;

        String[] palos = {"Treboles", "Corazones", "Picas", "Diamantes"};                           //Tipos de palos disponibles
        String[] colores = {"Negro", "Rojo", "Negro", "Rojo"};                                      //Colores posibles de la baraja
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};      //Los valores disponibles para cada tarjeta de un palo

        //Se llena el HashMap con todas las cartas disponibles en el Deck inicialmente
        for(byte i = 0; i < 4; i++)
        {
            for(byte j = 0; j < 13; j++)
            {
                Carta nuevaCarta = new Carta(palos[i], colores[i], valores[j]);
                diccionarioCartas.put(key, nuevaCarta);
                llavesCartas.add(key);
                key++;
            }
        }

        //Se realiza el primer shuffle del Deck, siempre ocurre cada vez que se incializa un Deck
        Shuffle(true);
    }

    public void Shuffle(boolean primerShuffle)   // Se modifica el orden en el que se encuentran las cartas aún restantes en el deck
    {
        //Dependiendo del tamaño de la Deck, se toman acciones diversas
        switch(llavesCartas.size())
        {
            case 0: //No se puede ordenar el Deck ya que no posee nada
                JOptionPane.showMessageDialog(null,"El deck esta vacio, no hay nada que revolver", "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
                break;

            case 1: //No tiene sentido ordenar un Deck de una carta
                JOptionPane.showMessageDialog(null,"El deck solamente contiene una carta, no hay necesidad de revolver", "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:    //Hay 2 o mas cartas en el deck
                Random r = new Random();    //Se crea el Random

                ArrayList<Integer> indicesReordenados = new ArrayList<Integer>();   //Lista que almacena los nuevos indices reordenados

                for(int i = llavesCartas.size()-1; i > 0; i--)
                {
                    /*
                     * Por cada iteración:
                     * 1° Se obtiene un numero entero aleatorio que puede ir desde el 0 hasta n-1, siendo n el tamaño actual del deck
                     * 2° Se añade al nuevo ArrayList la llave con el indice obtenido del paso 1
                     * 3° Se elimina la llave recolectada del ArrayList original
                     *
                     * El proceso se repite hasta que en el ArrayList original solo quede un valor
                     */

                    int indiceRecuperar = r.nextInt(i+1);
                    indicesReordenados.add(llavesCartas.get(indiceRecuperar));
                    llavesCartas.remove(indiceRecuperar);
                }

                //Se añade al ArrayList nuevo el último valor restante en el ArrayList original
                indicesReordenados.add(llavesCartas.get(0));
                llavesCartas.remove(0);

                //la lista original se actualiza
                llavesCartas = indicesReordenados;

                if(!primerShuffle)  //Si es el primer Shuffle, el mensaje de: "Deck mezclado" no se imprime
                {
                    JOptionPane.showMessageDialog(null,"Deck mezclado", "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
                }

                break;
        }
    }

    public void Head()  //Se toma la primera carta de la lista
    {
        if(llavesCartas.size() > 0)  //Para efectuar esta acción, el deck debe tener por lo menos una carta dentro de él
        {
            Carta primeraCarta = diccionarioCartas.get(llavesCartas.get(0));    //Se obtiene la primera carta del ArrayList
            llavesCartas.remove(0);      //La llave queda removida del ArrayList

            //Se imprime el mensaje con las características de la carta obtenida
            JOptionPane.showMessageDialog(null,"Primera Carta del Deck recogida:\n\n" + "Palo: " + primeraCarta.getPalo() + "\n" + "Color: " + primeraCarta.getColor() + "\n" + "Valor: " + primeraCarta.getValor() + "\n\nCartas restantes en Deck: " + llavesCartas.size(), "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
        }

        else    //No hay cartas, por lo que la acción no se realiza
        {
            JOptionPane.showMessageDialog(null,"El Deck ya no posee mas cartas", "BARAJA VACIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Pick()  //Se toma cualquier carta de la lista
    {
        switch(llavesCartas.size())     //Dependiendo del tamaño de la Deck, se hacen las siguientes opciones
        {
            case 0:     //El deck esta vacío: no hay nada que agarrar
                JOptionPane.showMessageDialog(null,"El Deck ya no posee mas cartas", "BARAJA VACIA", JOptionPane.INFORMATION_MESSAGE);
                break;

            case 1:     //Se manda a que se agarre la carta de hasta arriba, ya que es la única restante en el Deck
                Head();
                break;

            default:    //Hay 2 o más cartas
                Random r = new Random();    //Se inicializa un random
                int indiceAleatorioSeleccionado = r.nextInt(llavesCartas.size());       //Se obtiene el indice aleatorio del cual recuperar la llave de la carta
                Carta cartaAleatoria = diccionarioCartas.get(llavesCartas.get(indiceAleatorioSeleccionado));      //Se obtiene la carta referenciando a la llave que se encuentra en el indice obtenido arriba
                llavesCartas.remove(indiceAleatorioSeleccionado);             //Se quita la llave de la carta

                //Se despliega el mensaje con la información de la carta removida
                JOptionPane.showMessageDialog(null,"Carta aleatoria del Deck recogida:\n\n" + "Palo: " + cartaAleatoria.getPalo() + "\n" + "Color: " + cartaAleatoria.getColor() + "\n" + "Valor: " + cartaAleatoria.getValor() + "\n\nCartas restantes en Deck: " + llavesCartas.size(), "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    public void Hand()      //Se toman 5 cartas aleatorias de la lista
    {
        if(llavesCartas.size() >= 5)     //Si el deck aun tiene mas de 5 cartas dentro de si disponibles se puede realizar la operación
        {
            Random r = new Random();        //Random creaado
            Integer[] indicesCartasManos = new Integer [5];     //Arreglo que guarda los índices de las llaves de las cartas a retirar
            Carta[] manoCartas = new Carta[5];      //Arreglo que contiene las cartas a retirar

            //Se seleccionan las cartas,
            for(byte i = 0; i < 5; i++)
            {
                /*
                 * Por cada iteración:
                 * 1° Se obtiene un numero entero aleatorio que puede ir desde el 0 hasta n-1, siendo n el tamaño actual del deck
                 * 2° Al arreglo de indicesCartasManos[] se le anexa la llave encontrada en el índice obtenido en el paso anterior
                 * 3° Al arreglo manoCartas[] se le anexa la carta referenciada por la llave encontrada en el paso anterior
                 * 4° Se quita del ArrayList la llave que se encontró
                 *
                 * El proceso se repite 5 veces
                 */

                int aleatorio = r.nextInt(llavesCartas.size());
                indicesCartasManos[i] = llavesCartas.get(aleatorio);
                manoCartas[i] = diccionarioCartas.get(indicesCartasManos[i]);
                llavesCartas.remove(indicesCartasManos[i]);
            }

            //Se crea una String en la que se guarda toda la información de las 5 cartas en una sola cadena
            String todasCartas = "";

            for(byte i = 0; i < 5; i++)
            {
                if(i == 4)
                {
                    todasCartas += "Carta " + (i+1) + ": " + cartaATexto(manoCartas[i]) + "\n\n";
                }

                else
                {
                    todasCartas += "Carta " + (i+1) + ": " + cartaATexto(manoCartas[i]) + "\n";
                }

            }

            //Se imprime la información de las 5 cartas obtenidas
            JOptionPane.showMessageDialog(null,"Mano obtenida de baraja: \n\n" + todasCartas + "Cartas restantes en Deck: " + llavesCartas.size(), "BARAJA INFO", JOptionPane.INFORMATION_MESSAGE);
        }

        else    //Si el deck tiene menos de 5 cartas no se realiza nada
        {
            JOptionPane.showMessageDialog(null,"El Deck no posee las suficientes cartas para ejecutar esta accion", "BARAJA INSUFICIENTE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String cartaATexto(Carta carta)  //Transforma un objeto Carta a una String que se puede imrpimir
    {
        String cartaAtexto;

        cartaAtexto = "Palo: " + carta.getPalo() + ", Color: " + carta.getColor() + ", Valor: " + carta.getValor();

        return  cartaAtexto;
    }
}
