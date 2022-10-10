import javax.swing.*;

public class Main
{
    public static void main(String[] args)  //Metodo Main
    {
        MenuActividad8();
    }

    public static void MenuActividad8()     //Permite poder implementar el JOptionPane al método main
    {
        Deck baraja = new Deck();   //Se crea un objeto de tipo Deck que se usará para el programa

        String eleccionCompleta;        //Captura el valor de la string elegida completa
        char eleccionSwit ='0';         //Guarda el primer caracter de la opcion elegida para poder utilizarse en un switch
        int salirProceso;               //La opcion que guarda el int que dicta si salir del programa o no
        boolean accionValida = false;   //Boolean que permite que las opciones puedan repetirse indefinidamente hasta que el usuario desee salir del programa

        do
        {
            accionValida = true;

            try
            {
                //Despliega el panel de opciones posibles dentro del programa
                eleccionCompleta = (String) JOptionPane.showInputDialog(null,"Seleccione una opcion:\n\t1) Reordenar baraja\n\t2) Agarrar carta de hasta arriba\n\t3) Agarrar carta aleatoria\n\t4) Pedir mano\n\n\t0) Salir del programa", "MENU ACTIVIDAD 9: Baraja Poker v2.0", JOptionPane.QUESTION_MESSAGE);
                eleccionSwit = eleccionCompleta.charAt(0);
            }

            catch(NullPointerException a) //El usuario seleccionó la opcion de cerrar el mensaje o de cancelar
            {
                //Se pregunta si el usuario desea salir del programa usando unicamente la opcion de si o no
                salirProceso = JOptionPane.showConfirmDialog(null,"Quieres salir del programa?", "ACTIVIDAD 9: Baraja Poker v2.0", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                //Si presiona Si
                if(salirProceso == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null,"Programa terminado", "ACTIVIDAD 9: Baraja Poker v2.0", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                //Si presiona No
                else
                {
                    accionValida = false;
                    continue;
                }
            }

            //Dependiendo de la opción elegida, se ejecuta la acción especifica
            switch(eleccionSwit)
            {
                case '1':
                    baraja.Shuffle(false);
                    accionValida = false;
                    break;

                case '2':
                    baraja.Head();
                    accionValida = false;
                    break;

                case '3':
                    baraja.Pick();
                    accionValida = false;
                    break;

                case '4':
                    baraja.Hand();
                    accionValida = false;
                    break;

                case '0':
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Comando no reconocido, vuelva a intentarlo", "ACTIVIDAD 9 Baraja Poker v2.0", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        while(!accionValida);
    }
}