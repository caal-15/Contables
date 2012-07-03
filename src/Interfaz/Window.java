/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Application.General;
import Application.PCount;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Carlos
 */
public final class Window extends JFrame{
    Color color = Color.lightGray;
    General jesus;
    JComboBox accountBox;
    AddCount addCount;
    //LinkedList<BasicDBObject> accountList;
    
    public Window () throws UnknownHostException{
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(540, 250);
        this.setTitle("SuperContables 2.8.e.21");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        Mongo m= new Mongo();
        
        jesus = new General(m, "contables");
        addCount = new AddCount(this,jesus);
        
        
        adminSwitch();
        loadList();
    }
    
    private void adminSwitch(){
        JPanel father= new JPanel();
        father.setLayout(new GroupLayout(father));
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        father.setBackground(color);
        
        JLabel info = new JLabel("Software contable \n Empresa :D");
        info.setBounds(100, 15, this.getWidth(), 20);
        info.setFont(new Font("Serif", Font.BOLD, 18));
        info.setForeground(Color.black);
        
        JLabel foot = new JLabel("Brought to you by Manuel Pineda and Carlos Gonzáles.");
        foot.setBounds(this.getWidth()/2 - 180, this.getHeight()-55 , 350 , 20);
        
        JLabel dateLab= new JLabel("Seleccione la cuenta:");
        dateLab.setBounds(10, 55, 330, 20);
        
        JLabel amountLab= new JLabel("Ingrese la Cantidad de dinero:");
        amountLab.setBounds(10, 95, 330, 20);
                
        JLabel accountLab= new JLabel ("Fecha transacción:");
        accountLab.setBounds(this.getWidth()-150, 55, 140, 20);
        
        JLabel oweLab= new JLabel ("Deber:");
        oweLab.setBounds(this.getWidth()-150, 115, 55, 20);
        
        JLabel haveLab= new JLabel ("Haber:");
        haveLab.setBounds(this.getWidth()-85, 115, 55, 20);
        
        JRadioButton haveBut=new JRadioButton();
        haveBut.setBounds(this.getWidth()-45, 115, 20, 20);
        
        JRadioButton oweBut=new JRadioButton();
        oweBut.setBounds(this.getWidth()-110, 115, 20, 20);
        
        ButtonGroup group = new ButtonGroup();
        group.add(oweBut);
        group.add(haveBut);
        
        JTextField dateField=new JTextField("dd/mm/aaaa");
        dateField.setBounds(this.getWidth()-148, 75, 135, 20);
        dateField.setBackground(Color.white);
        
        JTextField amountField=new JTextField();
        amountField.setBounds(20, 115, 330, 20);
        amountField.setBackground(Color.white);
        
        accountBox= new JComboBox();
        accountBox.setBounds(20, 75, 330, 20);
        accountBox.setBackground(Color.white);
        accountBox.setEditable(true);
        accountBox.getEditor().getEditorComponent().setBackground(Color.white);
        
        JButton addTButton=new JButton("Añadir Transacción");
        addTButton.setBounds(5, 135, 145, 40);
        
        JButton addAButton=new JButton("Añadir Cuenta");
        addAButton.setBounds(140, 135, 115, 40);
        
        JButton libMButton=new JButton("Libro Mayor");
        libMButton.setBounds(245,135, 100, 40);
        
        JButton resulButton=new JButton("Resultados");
        resulButton.setBounds(335,135, 100, 40);
        
        JButton balButton=new JButton("B. General");
        balButton.setBounds(425, 135, 100, 40);
        
        father.add(info);
        father.add(foot);
        father.add(balButton);
        father.add(resulButton);
        father.add(libMButton);
        father.add(addAButton);
        father.add(addTButton);
        father.add(haveLab);
        father.add(haveBut);
        father.add(oweBut);
        father.add(oweLab);
        father.add(amountField);        
        father.add(amountLab);
        father.add(dateLab);
        father.add(dateField);
        father.add(accountBox);
        father.add(accountLab);
        this.add(father);
        
        //Action listeners here
        addAButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addCount.setVisible(true);
            }
        }); 
        
    }
    
    public void loadList(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
        for (BasicDBObject mio : jesus.listCuentas() ) {
           modelo.addElement((BasicDBObject)new PCount(mio));
        }
        accountBox.setModel(modelo);
    }
    
}