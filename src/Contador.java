



import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Contador implements Runnable
{
    private int hora;
    private int minutos;
    private int segundos;
    private JLabel todo;

    public Contador() 
    {
        hora=0;
        minutos=0;
        segundos=0;
        todo=new JLabel();
    }

    public Contador(JLabel todo) {
        this.todo = todo;
    }
    
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public JLabel getTodo() {
        return todo;
    }

    public void setTodo(JLabel todo) {
        this.todo = todo;
    }

    

    
    @Override
    public void run() 
    {
        try
        {
            while(true)
            {
                if(segundos<59)
                {
                    segundos++;
                }
                else if(segundos==59)
                {
                    if(minutos<59)
                    {
                        segundos=0;
                        minutos++;
                    }
                    else if(minutos==59 && segundos==59)
                    {
                        minutos=0;
                        segundos=0;
                        if(hora==23)
                        {
                            hora=0;
                        }
                        else 
                        {
                            hora++;
                        }
                        
                        
                    }
                    
                }
                String patron="%02d:%02d:%02d";
                String resultado=String.format(patron, hora, minutos, segundos);
                
                todo.setText(resultado);
                sleep(1000);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error--> "+ex.toString());
        }
    }
    
}
