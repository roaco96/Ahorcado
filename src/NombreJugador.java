
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Ciclo formativo: Desarrollo de Aplicaciones Multiplataforma
 * Módulo profesional: Programación
 * Reto número 2
 * Alumno: Manuel Muñoz Rodríguez
 * Curso académico: 2016-2017
 * Poniente Formación
*/
public class NombreJugador extends javax.swing.JFrame {

    private static int fallos=0;
    private ArrayList<JLabel> mostrarPalabra;
    private String palabra;
    private NombreJugador ventana;
    private Random aleatorio;
    private ArrayList<String> letras;
    private ArrayList<String> imagenes;
    private static int puntos=0;
    private static int n_letras=0;
    
    
    public void reiniciarAhorcado()
    {
        lMensaje.setText("");
        ImageIcon icono= new ImageIcon(this.getClass().getResource(imagenes.get(0)));
        lImagen.setIcon(icono);
        
        lNumeroPuntos.setText("0");
        
        int n_aleatorio=aleatorio.nextInt(72-0)+0;
        palabra=palabraAleatoria(n_aleatorio);
        
        pBorde.removeAll();
        
        mostrarPalabra.clear();
        
        for (int i = 0; i < palabra.length(); i++) 
        {
            JLabel guion=new JLabel();
            mostrarPalabra.add(guion);
            guion.setText("_");
            pBorde.add(guion);
            
        }
        
        fallos=0;
        
        
        
        ventana = this;
        
        pBotones.removeAll();
        for (int i = 0; i < 27; i++) 
        {
            int posicion=i;
            String letra=letras.get(i);
            final JButton boton=new JButton();
            boton.setText(letras.get(i));
            pBotones.add(boton);
            
            boton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(letras.get(posicion)=="Ñ")
                    {
                        if(comprobarN(posicion)==false)
                        {
                            fallos++;
                            if(fallos==6)
                            {
                                JOptionPane.showMessageDialog(null, 
                                "Has perdido, la palabra era: "+palabra);
                                reiniciarAhorcado();
                            }
                            else
                            {
                                ImageIcon icono= new ImageIcon(this.getClass().getResource(imagenes.get(fallos)));
                                lImagen.setIcon(icono);
                                lMensaje.setText("No hay "+letras.get(posicion)+" en la palabra.");
                            }
                            
                        }
                        else
                        {
                            int suma=puntos;
                                
                            suma=suma+Integer.valueOf(lNumeroPuntos.getText());
                            lNumeroPuntos.setText(String.valueOf(suma));
                            puntos=0;
                            lMensaje.setText("Hay "+n_letras+" "+letras.get(posicion)+" en la palabra.");
                            n_letras=0;
                        }
                        
                    }
                    else if(comprobarLetras(letra, posicion)==false)
                    {
                        fallos++;
                        if(fallos==6)
                        {
                            JOptionPane.showMessageDialog(null, 
                            "Has perdido, la palabra era: "+palabra);
                            reiniciarAhorcado();

                        }
                        else
                        {
                            ImageIcon icono= new ImageIcon(this.getClass().getResource(imagenes.get(fallos)));
                            lImagen.setIcon(icono);
                            lMensaje.setText("No hay "+letras.get(posicion)+" en la palabra.");
                        }
                    }
                    else
                    {
                        int suma=puntos;

                        suma=suma+Integer.valueOf(lNumeroPuntos.getText());
                        lNumeroPuntos.setText(String.valueOf(suma));
                        puntos=0;
                        lMensaje.setText("Hay "+n_letras+" "+letras.get(posicion)+" en la palabra.");
                        n_letras=0;
                    }
                    boton.setEnabled(false);
                    
                }
            });
        }
        
    }
    
    
    public boolean comprobarN(int posicion)
    {
        boolean cierto=true;
        for (int i = 0; i < palabra.length(); i++) 
        {

            if(String.valueOf(palabra.charAt(i)).equals("Ñ")==true)
            {
                mostrarPalabra.get(i).setText("Ñ");
                puntos=puntos+5;
                n_letras++;
            }
            else
            {
                cierto=false;
            }
        }
        if(puntos!=0)
        {
            cierto=true;
        }
        return cierto;
        
    }
    
    
    
    
    public boolean comprobarLetras(String letra, int posicion)
    {
        boolean cierto=true;
        
        
        for (int i = 0; i < palabra.length(); i++) 
        {

            if(String.valueOf(palabra.charAt(i)).equals(letra)==true)
            {
                mostrarPalabra.get(i).setText(letra);
                puntos=puntos+5;
                n_letras++;
            }
            else
            {
                cierto=false;
            }
            if(puntos!=0)
            {
                cierto=true;
            }
        }
        
        
        if(comprobarTodasLasLetras()==true)
        {
            int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¡Acertaste todas las letras de la palabra! Has ganado, ¿otra partida?",
            "Ganaste",
            JOptionPane.YES_NO_OPTION);
            
            if(respuesta==0)
            {
                reiniciarAhorcado();
            }
            
        }
        return cierto;
    }
    
    
    public boolean comprobarTodasLasLetras()
    {
        boolean c=true;
        int contador=0;
        for (int i = 0; i < palabra.length(); i++) 
        {
            if(mostrarPalabra.get(i).getText().equals("_")==true)
            {
                contador++;
            }
            
        }
        if(contador!=0)
        {
            c=false;
        }
        return c;
    }
    
    
    public boolean comprobarPalabra(String p)
    {
        boolean cierto;
        if(p.equals(palabra)==true)
        {
            cierto=true;
        }
        else
        {
            cierto=false;
        }
        return cierto;
    }
    
    
    
    public String palabraAleatoria(int n)
    {
        String p="";
        
        BufferedReader bufer=null;
        InputStreamReader isr =null;
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("ahorcado.txt");
            isr = new InputStreamReader(fis, "ISO-8859-1");
            BufferedReader bf = new BufferedReader(isr);
            
            for (int i = 0; i < n; i++) 
            {
                p=bf.readLine();
                
            }
            
        }
        catch(IOException error)
        {
            JOptionPane.showMessageDialog(this,
            "Error en el fichero--> "+error.toString(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
                if(bufer!=null)
                {
                    bufer.close();
                }
                if(isr!=null)
                {
                    isr.close();
                }
                if(fis!=null)
                {
                    fis.close();
                }
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(this,
                "Error en el fichero--> "+e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
        }
        return p;
    }
    
    
    
    /**
     * Creates new form NombreJugador
     */
    public NombreJugador() {
        
        String respuesta = (String)JOptionPane.showInputDialog(
        this,
        "",
        "Introduce tu nombre",
        JOptionPane.QUESTION_MESSAGE,
        null,
        null,
        null);

        
        
        initComponents();
        
        imagenes= new ArrayList<>();
        imagenes.add("imagenes/im1.jpg");
        imagenes.add("imagenes/im2.jpg");
        imagenes.add("imagenes/im3.jpg");
        imagenes.add("imagenes/im4.jpg");
        imagenes.add("imagenes/im5.jpg");
        imagenes.add("imagenes/im6.jpg");
        
        
        if (respuesta.isEmpty()==true)
        {
            lNombreJugador.setText("Sin nombre");
        }
        else
        {
            lNombreJugador.setText(respuesta);
        }
       
        
        aleatorio=new Random();
        int n_aleatorio=aleatorio.nextInt(72-0)+0;
        
        palabra=palabraAleatoria(n_aleatorio);
        
        mostrarPalabra=new ArrayList<>();
        for (int i = 0; i < palabra.length(); i++) 
        {
            JLabel guion=new JLabel();
            mostrarPalabra.add(guion);
            guion.setText("_");
            pBorde.add(guion);
            
        }
        
        
        letras=new ArrayList<>();
        
        letras.add("A");
        letras.add("B");
        letras.add("C");
        letras.add("D");
        letras.add("E");
        letras.add("F");
        letras.add("G");
        letras.add("H");
        letras.add("I");
        letras.add("J");
        letras.add("K");
        letras.add("L");
        letras.add("M");
        letras.add("N");
        letras.add("Ñ");
        letras.add("O");
        letras.add("P");
        letras.add("Q");
        letras.add("R");
        letras.add("S");
        letras.add("T");
        letras.add("U");
        letras.add("V");
        letras.add("W");
        letras.add("X");
        letras.add("Y");
        letras.add("Z");
        
        ventana = this;
        
        for (int i = 0; i < 27; i++) 
        {
            int posicion=i;
            String letra=letras.get(i);
            final JButton boton=new JButton();
            boton.setText(letras.get(i));
            pBotones.add(boton);
            
            boton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(letras.get(posicion)=="Ñ")
                    {
                        if(comprobarN(posicion)==false)
                        {
                            fallos++;
                            if(fallos==6)
                            {
                                JOptionPane.showMessageDialog(null, 
                                "Has perdido, la palabra era: "+palabra);
                                reiniciarAhorcado();
                            }
                            else
                            {
                                ImageIcon icono= new ImageIcon(this.getClass().getResource(imagenes.get(fallos)));
                                lImagen.setIcon(icono);
                                lMensaje.setText("No hay "+letras.get(posicion)+" en la palabra.");
                            }
                            
                        }
                        else
                        {
                            int suma=puntos;
                                
                            suma=suma+Integer.valueOf(lNumeroPuntos.getText());
                            lNumeroPuntos.setText(String.valueOf(suma));
                            puntos=0;
                            lMensaje.setText("Hay "+n_letras+" "+letras.get(posicion)+" en la palabra.");
                            n_letras=0;
                        }
                        
                    }
                    else if(comprobarLetras(letra, posicion)==false)
                    {
                        fallos++;
                        if(fallos==6)
                        {
                            JOptionPane.showMessageDialog(null, 
                            "Has perdido, la palabra era: "+palabra);
                            reiniciarAhorcado();

                        }
                        else
                        {
                            ImageIcon icono= new ImageIcon(this.getClass().getResource(imagenes.get(fallos)));
                            lImagen.setIcon(icono);
                            lMensaje.setText("No hay "+letras.get(posicion)+" en la palabra.");
                        }
                    }
                    else
                    {
                        int suma=puntos;

                        suma=suma+Integer.valueOf(lNumeroPuntos.getText());
                        lNumeroPuntos.setText(String.valueOf(suma));
                        puntos=0;
                        lMensaje.setText("Hay "+n_letras+" "+letras.get(posicion)+" en la palabra.");
                        n_letras=0;
                    }
                    boton.setEnabled(false);
                    
                }
            });
        }
        
        
        
        
    }

    /**
     * This method isr called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method isr always
 regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pSur = new javax.swing.JPanel();
        pTiempo = new javax.swing.JPanel();
        lTiempo = new javax.swing.JLabel();
        lContador = new javax.swing.JLabel();
        pNombre = new javax.swing.JPanel();
        lNombreJugador = new javax.swing.JLabel();
        pPuntuacion = new javax.swing.JPanel();
        lPuntuacion = new javax.swing.JLabel();
        lNumeroPuntos = new javax.swing.JLabel();
        pCentro = new javax.swing.JPanel();
        pImagen = new javax.swing.JPanel();
        lImagen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lMensaje = new javax.swing.JLabel();
        pDerecho = new javax.swing.JPanel();
        pEncabezado = new javax.swing.JPanel();
        lNombreJuego = new javax.swing.JLabel();
        pCentroDerecha = new javax.swing.JPanel();
        pBorde = new javax.swing.JPanel();
        pOrdenacion = new javax.swing.JPanel();
        pResolver = new javax.swing.JPanel();
        pPalabra = new javax.swing.JPanel();
        lResolver = new javax.swing.JLabel();
        pTexto = new javax.swing.JPanel();
        tfResolver = new javax.swing.JTextField();
        pBotonResolver = new javax.swing.JPanel();
        bResolver = new javax.swing.JButton();
        pBotones = new javax.swing.JPanel();
        mbDelJuego = new javax.swing.JMenuBar();
        mJuego = new javax.swing.JMenu();
        miNueva = new javax.swing.JMenuItem();
        miGuardar = new javax.swing.JMenuItem();
        miCargar = new javax.swing.JMenuItem();
        mSobreJuego = new javax.swing.JMenu();
        miInformacion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego del ahorcado");

        pSur.setLayout(new java.awt.GridLayout(1, 3));

        pTiempo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lTiempo.setText("Tiempo jugado: ");
        pTiempo.add(lTiempo);

        lContador.setText("00:00:00");
        pTiempo.add(lContador);

        pSur.add(pTiempo);

        pNombre.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        pNombre.add(lNombreJugador);

        pSur.add(pNombre);

        pPuntuacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lPuntuacion.setText("Puntuación: ");
        pPuntuacion.add(lPuntuacion);

        lNumeroPuntos.setText("0");
        pPuntuacion.add(lNumeroPuntos);

        pSur.add(pPuntuacion);

        getContentPane().add(pSur, java.awt.BorderLayout.SOUTH);

        pCentro.setLayout(new java.awt.GridLayout(1, 2));

        pImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pImagen.setLayout(new java.awt.BorderLayout());

        lImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/im1.jpg"))); // NOI18N
        pImagen.add(lImagen, java.awt.BorderLayout.CENTER);

        lMensaje.setText("----------");
        jPanel1.add(lMensaje);

        pImagen.add(jPanel1, java.awt.BorderLayout.SOUTH);

        pCentro.add(pImagen);

        pDerecho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pDerecho.setLayout(new java.awt.BorderLayout());

        lNombreJuego.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lNombreJuego.setText("Juego del ahorcado");
        pEncabezado.add(lNombreJuego);

        pDerecho.add(pEncabezado, java.awt.BorderLayout.NORTH);

        pCentroDerecha.setLayout(new java.awt.BorderLayout());

        pBorde.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Palabra secreta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pCentroDerecha.add(pBorde, java.awt.BorderLayout.NORTH);

        pOrdenacion.setLayout(new java.awt.BorderLayout());

        pResolver.setLayout(new java.awt.GridLayout(2, 0));

        pPalabra.setLayout(new java.awt.BorderLayout());

        lResolver.setText("Resolver");
        pPalabra.add(lResolver, java.awt.BorderLayout.WEST);

        pTexto.setLayout(new java.awt.GridLayout(1, 0));

        tfResolver.setPreferredSize(new java.awt.Dimension(130, 20));
        pTexto.add(tfResolver);

        pPalabra.add(pTexto, java.awt.BorderLayout.CENTER);

        pResolver.add(pPalabra);

        pBotonResolver.setLayout(new java.awt.GridLayout(1, 0));

        bResolver.setText("Resolver palabra");
        bResolver.setActionCommand("Resolver");
        bResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResolverActionPerformed(evt);
            }
        });
        pBotonResolver.add(bResolver);

        pResolver.add(pBotonResolver);

        pOrdenacion.add(pResolver, java.awt.BorderLayout.NORTH);

        pBotones.setLayout(new java.awt.GridLayout(6, 5));
        pOrdenacion.add(pBotones, java.awt.BorderLayout.CENTER);

        pCentroDerecha.add(pOrdenacion, java.awt.BorderLayout.CENTER);

        pDerecho.add(pCentroDerecha, java.awt.BorderLayout.CENTER);

        pCentro.add(pDerecho);

        getContentPane().add(pCentro, java.awt.BorderLayout.CENTER);

        mJuego.setText("Juego");

        miNueva.setText("Nueva partida");
        miNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNuevaActionPerformed(evt);
            }
        });
        mJuego.add(miNueva);

        miGuardar.setText("Guardar partida");
        miGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGuardarActionPerformed(evt);
            }
        });
        mJuego.add(miGuardar);

        miCargar.setText("Cargar partida");
        miCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCargarActionPerformed(evt);
            }
        });
        mJuego.add(miCargar);

        mbDelJuego.add(mJuego);

        mSobreJuego.setText("Sobre el juego");

        miInformacion.setText("Información");
        mSobreJuego.add(miInformacion);

        mbDelJuego.add(mSobreJuego);

        setJMenuBar(mbDelJuego);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResolverActionPerformed
        
        if(comprobarPalabra(tfResolver.getText())==true)
        {
            int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¡Correcto! Has ganado, ¿otra partida?",
            "Ganaste",
            JOptionPane.YES_NO_OPTION);
            
            if(respuesta==0)
            {
                reiniciarAhorcado();
                tfResolver.setText("");
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,
            "¡Fallaste resolviendo la palabra!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bResolverActionPerformed

    private void miNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNuevaActionPerformed
        reiniciarAhorcado();
    }//GEN-LAST:event_miNuevaActionPerformed

    private void miCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCargarActionPerformed
        JFileChooser elegir = new JFileChooser();
        
        elegir.setSelectedFile(new File("fichero.txt"));
        
        FileFilter filter = new FileNameExtensionFilter("Ficheros de texto", "txt");
        elegir.addChoosableFileFilter(filter);
        
        int resultado = elegir.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION)
        {
            
            try 
            {
                
                
                
            } 
            catch (Exception ex) 
            {
                
            } 
            finally
            {
                try 
                {
                    
                } 
                catch (Exception ex)
                {
                    
                } 
            }
            
        }
    }//GEN-LAST:event_miCargarActionPerformed

    private void miGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGuardarActionPerformed
        
    }//GEN-LAST:event_miGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) isr not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NombreJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NombreJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NombreJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NombreJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NombreJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bResolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lContador;
    private javax.swing.JLabel lImagen;
    private javax.swing.JLabel lMensaje;
    private javax.swing.JLabel lNombreJuego;
    private javax.swing.JLabel lNombreJugador;
    private javax.swing.JLabel lNumeroPuntos;
    private javax.swing.JLabel lPuntuacion;
    private javax.swing.JLabel lResolver;
    private javax.swing.JLabel lTiempo;
    private javax.swing.JMenu mJuego;
    private javax.swing.JMenu mSobreJuego;
    private javax.swing.JMenuBar mbDelJuego;
    private javax.swing.JMenuItem miCargar;
    private javax.swing.JMenuItem miGuardar;
    private javax.swing.JMenuItem miInformacion;
    private javax.swing.JMenuItem miNueva;
    private javax.swing.JPanel pBorde;
    private javax.swing.JPanel pBotonResolver;
    private javax.swing.JPanel pBotones;
    private javax.swing.JPanel pCentro;
    private javax.swing.JPanel pCentroDerecha;
    private javax.swing.JPanel pDerecho;
    private javax.swing.JPanel pEncabezado;
    private javax.swing.JPanel pImagen;
    private javax.swing.JPanel pNombre;
    private javax.swing.JPanel pOrdenacion;
    private javax.swing.JPanel pPalabra;
    private javax.swing.JPanel pPuntuacion;
    private javax.swing.JPanel pResolver;
    private javax.swing.JPanel pSur;
    private javax.swing.JPanel pTexto;
    private javax.swing.JPanel pTiempo;
    private javax.swing.JTextField tfResolver;
    // End of variables declaration//GEN-END:variables
}
