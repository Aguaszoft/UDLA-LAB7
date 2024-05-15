import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class ListaGUI {
    private JPanel panelGeneral;
    private JButton btnAgregar;
    private JPanel panelDatos;
    private JTextField nombreField;
    private JTextField edadField;
    private JComboBox seguroComboBox;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblSeguro;
    private JTextArea textArea;
    private JPanel panelPastel;
    private JButton btnPastel;

    private Persona inicio;
    private Persona fin;
    private Persona[] arregloPersonas;
    private int size;

    public ListaGUI() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });
        btnPastel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graficoPastel(panelPastel.getGraphics(),50,125);
                graficoBarras(panelPastel.getGraphics(),arregloPersonas,50,600);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListaGUI");
        frame.setContentPane(new ListaGUI().panelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void agregarPersona() {

        String nombre = nombreField.getText();
        int edad = Integer.parseInt(edadField.getText());
        String seguroSocial = seguroComboBox.getSelectedItem().toString();

        Persona nuevaPersona = new Persona(nombre, edad, seguroSocial);

        if (inicio == null) {
            inicio = nuevaPersona;
            fin = nuevaPersona;
        } else {
            fin.setSiguiente(nuevaPersona);
            fin = nuevaPersona;
        }

        // Aumentamos el tamaño del arreglo y lo actualizamos
        aumentarArreglo();

        mostrarLista();
        nombreField.setText("");
        edadField.setText("");
    }

    private void mostrarLista() {
        textArea.setText("");
        Persona temp = inicio;
        while (temp != null) {
            textArea.append(temp.toString() + "\n");
            temp = temp.getSiguiente();
        }
    }

    private void aumentarArreglo() {
        if (arregloPersonas == null) {
            arregloPersonas = new Persona[1];
        } else {
            Persona[] nuevoArreglo = new Persona[size + 1];
            System.arraycopy(arregloPersonas, 0, nuevoArreglo, 0, size);
            arregloPersonas = nuevoArreglo;
        }
        arregloPersonas[size++] = fin;
    }

    public void graficoPastel(Graphics g, int x, int y)
    {
        int ptot=0; int s=0, ns=0;
        for(int i=0;i<arregloPersonas.length;i++)
        {
            if(arregloPersonas[i].getSeguroSocial().equals("Seguro"))
                s++;
            if(arregloPersonas[i].getSeguroSocial().equals("No Seguro"))
                ns++;
        }
        ptot=arregloPersonas.length;
        if(ptot!=0){
            g.drawRect(x+200,y+20,100,100);
            g.drawString("Leyenda:",x+210,y+40);
            g.drawString("S "+(double)(s*100/ptot)+" %",x+230,y+60);
            g.drawString("NS "+(double)(ns*100/ptot)+" %",x+230,y+80);

            g.drawRect(x+210,y+50,10,10);
            g.drawRect(x+210,y+70,10,10);

            g.fillOval(x-3,y-3,156,156);

            g.setColor(new Color(128,0,128));
            g.fillArc(x,y,150,150,0,(int)(s*360/ptot));
            g.fillRect(x+210,y+50,10,10);
            g.setColor(new Color(14,45,34));
            g.fillArc(x,y,150,150,(int)(s*360/ptot),(int)(ns*360/ptot));
            g.fillRect(x+210,y+70,10,10);
        }
    }
    public void graficoBarras(Graphics t, Persona A[],int x, int y)
    {
        int contS=0,contC=0,a=20,ancho=40;
        for(int i=0;i<A.length;i++)
        {
            if(A[i].getSeguroSocial().equals("Seguro"))
                contS++;
            if(A[i].getSeguroSocial().equals("No Seguro"))
                contC++;
        }
        //Ejes
        t.drawLine(x,y,x+300,y);
        t.drawLine(x,y,x,y-250);

        t.setColor(Color.red);
        t.drawString("Tipo",x+270,y+30);
        t.drawString("# Asegurados",x-50,y-250);

        //Leyenda
        t.setColor(Color.black);
        t.drawRect(x+210,y-190,160,100);
        t.setColor(Color.red);
        t.drawString("Leyenda",x+260,y-170);
        t.setColor(Color.blue);
        t.fillRect(x+230,y-150,ancho,ancho/2);
        t.setColor(Color.cyan);
        t.fillRect(x+230,y-120,ancho,ancho/2);
        t.setColor(Color.black);
        t.drawRect(x+230,y-150,ancho,ancho/2);
        t.drawRect(x+230,y-120,ancho,ancho/2);

        t.drawString("S",x+280,y-135);
        t.drawString("NS",x+280,y-105);


        //Seguro
        t.setColor(Color.blue);
        t.fillRect(x+50,y-(contS*a),ancho,contS*a);
        t.setColor(Color.black);
        t.drawRect(x+50,y-(contS*a),ancho,contS*a);
        t.drawLine(x,y-(contS*a),x+50+ancho,y-(contS*a));

        t.drawString("Seguro",x+50,y+30);
        t.drawString(""+contS,x-20,y-(contS*a));

        //No Seguro
        t.setColor(Color.cyan);
        t.fillRect(x+140,y-(contC*a),ancho,contC*a);
        t.setColor(Color.black);
        t.drawRect(x+140,y-(contC*a),ancho,contC*a);
        t.drawLine(x+50+ancho,y-(contC*a),x+140+ancho,y-(contC*a));

        t.drawString("No Seguro",x+140,y+30);
        t.drawString(""+contC,x-20,y-(contC*a));
    }

}
