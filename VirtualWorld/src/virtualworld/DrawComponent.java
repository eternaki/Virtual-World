/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
/**
 *
 * @author Lohachov Danylo
 */
public class DrawComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHT = 900;
    private World w;
    public DrawComponent(World w){
        this.w = w;
    }
    @Override
    public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            
            int width = 30;
            int height = 30;
            
            
            //okreslenie nazwy stylu i rozmiaru czcionki uzywanej do nazw organizmow
            Font font = new Font("Arial", Font.BOLD, 15);
            g2.setFont(font);
            
            
            if(!(w.getHexMode())){
                g2.drawString(w.komunikat, width, height*(w.rozmiar.getY()+2));
                g2.setColor(Color.BLACK);
                for(int i=0; i<w.rozmiar.getY(); i++){
                    for(int j=0;j<w.rozmiar.getX() ;j++){
                        int leftX=j*width;
                        int topY=i*height;                    

                        Rectangle2D rect = new Rectangle2D.Double(leftX+30, topY+30, width, height);
                        g2.draw(rect);  
                    }
                }



                for(int i=0; i<w.getIleOrganizmow();i++){
                    int leftX=width*w.organizmy[i].getPozycja().getX();
                    int topY=height*w.organizmy[i].getPozycja().getY();

                    Rectangle2D rect = new Rectangle2D.Double(leftX+30, topY+30, width, height);
                    g2.setPaint(w.organizmy[i].getColor());
                    g2.fill(rect);
                    g2.setColor(Color.BLACK);                
                    g2.drawString(w.organizmy[i].getNazwa(), leftX+30, topY-2+30);

                }
            }
            else{
                int radius = 20;
                int switcher =1;
                Pozycja pos;
                Organizm org;
                font = new Font("Arial", Font.BOLD, 12);
                g2.setFont(font);
                for(int k=0; k<w.getRozmiar().getY(); k++){
                    for(int j=0;j<w.getRozmiar().getX();j++){            
                        Polygon p = new Polygon();
                        if(j%2==0){
                            for (int i = 0; i < 6; i++) {
                                p.addPoint((int) (50+ j*radius*1.5+ radius * Math.cos(i * 2 * Math.PI / 6)), (int) (50 + k*radius*Math.sqrt(3) + radius*Math.sqrt(3)*switcher*0.5 +radius * Math.sin(i * 2 * Math.PI / 6)));
                            }
                        }
                        else{
                            for (int i = 0; i < 6; i++) {
                                p.addPoint((int) (50+ j*radius*1.5+ radius * Math.cos(i * 2 * Math.PI / 6)), (int) (50 + k*radius*Math.sqrt(3) + radius * Math.sin(i * 2 * Math.PI / 6)));
                            }
                        }
                        //g2.fill(p);
                        pos = new Pozycja(j, k);
                        org = w.znajdzPozycje(pos);
                        
                       
                        if(org != null) {
                            g2.setPaint(org.getColor());
                            g2.fill(p);
                            
                        }
                        else {
                            g2.setPaint(Color.getHSBColor(238, 238, 238));
                            g2.fill(p);
                        }
                        g2.setPaint(Color.BLACK);
                        g2.drawPolygon(p);
                        g2.drawString(w.komunikat, width, (int) (140+height*(w.rozmiar.getY()+2)));
                        if(org != null) {
                            if(j%2==0) g2.drawString(org.getNazwa(), (int) (20+j*radius*1.5+ radius), (int) (50 + k*radius*Math.sqrt(3) - radius*Math.sqrt(3)*switcher*0.25));
                            else g2.drawString(org.getNazwa(), (int) (20+j*radius*1.5+ radius), (int) (k*radius*Math.sqrt(3)+20));
                        }
                        switcher*=-1;
                    }
                    switcher=1;
                }
            }
    }
    @Override
    public Dimension getPreferredSize(){ return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);}
}
