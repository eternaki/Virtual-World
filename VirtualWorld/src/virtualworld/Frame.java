
package virtualworld;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class Frame extends JFrame implements ActionListener, KeyListener {
        private final World w;
        private JPanel buttonPanel;
        private JComponent maps;
        JButton nextTour = new JButton("Next Tour");
        JButton save = new JButton("Zapisz");
        JButton load = new JButton("Wczytaj");
        JButton quit = new JButton("Wyjdz");
        JButton hex = new JButton("Hex/cart");
        
        private JPopupMenu popup = new JPopupMenu();        
        JMenuItem dodaj1 = new JMenuItem("Antylopa");
        JMenuItem dodaj2 = new JMenuItem("BarszczSosnowskiego");
        JMenuItem dodaj3 = new JMenuItem("CyberOwca");
        JMenuItem dodaj4 = new JMenuItem("Guarana");
        JMenuItem dodaj5 = new JMenuItem("WilczeJagody");
        JMenuItem dodaj6 = new JMenuItem("Lis");
        JMenuItem dodaj7 = new JMenuItem("Mlecz");
        JMenuItem dodaj8 = new JMenuItem("Owca");
        JMenuItem dodaj9 = new JMenuItem("Trawa");
        JMenuItem dodaj10 = new JMenuItem("Wilk");
        JMenuItem dodaj11 = new JMenuItem("Zolw");
        JMenuItem dodaj12 = new JMenuItem("Czlowiek");
         
        public Frame(World w){
            this.w =w;
            setTitle("Wirtualny Swiat");
            setResizable(true);
            setFocusable(true);
            
            GridLayout experimentLayout = new GridLayout(1, 1, 0, 0);
            setLayout(experimentLayout);
            
            buttonPanel= new JPanel();
            buttonPanel.add(nextTour);
            buttonPanel.add(save);
            buttonPanel.add(load);
            buttonPanel.add(quit);
            buttonPanel.add(hex);
            
            
            
            
            addKeyListener(this);
            maps = new DrawComponent(w);
            maps.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event){
                    if(!w.getHexMode()){
                    Pozycja pos = new Pozycja((event.getX()-30)/30, (event.getY()-30)/30);
                    if(pos.getX()<w.getRozmiar().getX() && pos.getY()<w.getRozmiar().getY() && w.znajdzPozycje(pos) == null) {                        
                        dodajOrganizm ramka = new dodajOrganizm(w, pos, maps);
                        ramka.setVisible(true);
                        
                    }
                    }
                    else{
                        Pozycja pos = new Pozycja((event.getX()+150)/40, (int) ((event.getY()-40)/(20*Math.sqrt(3))));
                        if(pos.getX()<w.getRozmiar().getX() && pos.getY()<w.getRozmiar().getY() && w.znajdzPozycje(pos) == null) {                        
                            dodajOrganizm ramka = new dodajOrganizm(w, pos, maps);
                            ramka.setVisible(true);
                        }
                    }
                }
            });
            
            add(maps);            
            add(buttonPanel);
            nextTour.setFocusable(false);
            save.setFocusable(false);
            load.setFocusable(false);
            quit.setFocusable(false);
            hex.setFocusable(false);
            
            nextTour.addActionListener(this);
            save.addActionListener(this);
            load.addActionListener(this);
            quit.addActionListener(this);
            hex.addActionListener(this);
            
            dodaj1.addActionListener(this);
            dodaj2.addActionListener(this);
            dodaj3.addActionListener(this);
            dodaj4.addActionListener(this);
            dodaj5.addActionListener(this);
            dodaj6.addActionListener(this);
            dodaj7.addActionListener(this);
            dodaj8.addActionListener(this);
            dodaj9.addActionListener(this);
            dodaj10.addActionListener(this);
            dodaj11.addActionListener(this);
            dodaj12.addActionListener(this);
            
            
            popup.add(dodaj1);
            popup.add(dodaj2);
            popup.add(dodaj3);
            popup.add(dodaj4);
            popup.add(dodaj5);
            popup.add(dodaj6);
            popup.add(dodaj7);
            popup.add(dodaj8);
            popup.add(dodaj9);
            popup.add(dodaj10);
            popup.add(dodaj11);
            popup.add(dodaj12);                      
            
            maps.setComponentPopupMenu(popup);
            popup.setVisible(false);
            //this.setComponentPopupMenu(popup);
            
            pack();               
        }        

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == nextTour){
           
           w.wykonajTure();           
           this.repaint();
           //w.komunikat="";
        }
        else if(src == quit)
           System.exit(0);
        else if(src == save) try 
        {
            w.zapisz();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(src == load) try {
            w.wczytaj();
            this.repaint();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(src == hex) {
            w.setHexMode(!(w.getHexMode()));
            this.repaint();
        }
        
        //else if (src == dodaj1) w.dodajOrganizm(event.get, nazwa);
        
        else return;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int src = ke.getKeyCode();
        //Organizm czlowiek = this.w.getCzlowiek();
        //if(czlowiek !=null)
        switch(src){            
            case KeyEvent.VK_LEFT:
                w.actionKey = 1; 
                break;
            case KeyEvent.VK_RIGHT:
                w.actionKey = 2;
                break;
            case KeyEvent.VK_UP:
                w.actionKey = 3;
                break;
            case KeyEvent.VK_DOWN:
                w.actionKey = 4;
                break;
            case KeyEvent.VK_SPACE:
                w.actionKey = 5;
                break;
            default: break;
        }        
    }
    
    

    @Override
    public void keyReleased(KeyEvent ke) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    

    
}