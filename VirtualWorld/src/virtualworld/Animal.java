/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

/**
 *
 * @author Lohachov Danylo
 */
public abstract class Animal extends Organizm {
        
    public Animal(Pozycja pos, World world) {
        super(pos, world);
        this.zasieg=1;
    }
    
    
    
    @Override
    public void akcja(){
        this.mozeRuszac = true;
        this.setWiek(this.getWiek()+1);        
        ruch(world.losoweOtocz(this.getPozycja(), this.getZasieg()));        
        
    }
    
    public void ruchLewo(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()-this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY());
        this.ruch(nowaPozycja);
    }
    
    public void ruchLewoGora(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()-this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY()-this.getZasieg());
        ruch(nowaPozycja);
    }
    
    public void ruchLewoDol(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()-this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY()+this.getZasieg());
        ruch(nowaPozycja);
    }
    
    public void ruchPrawo(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()+this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY());
        ruch(nowaPozycja);
    }
    
    public void ruchPrawoDol(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()+this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY()+this.getZasieg());
        ruch(nowaPozycja);
    }
    
    public void ruchPrawoGora(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX()+this.getZasieg());
        nowaPozycja.setY(this.getPozycja().getY()-this.getZasieg());
        ruch(nowaPozycja);
    }        
    
    public void ruchDol(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX());
        nowaPozycja.setY(this.getPozycja().getY()+this.getZasieg());
        ruch(nowaPozycja);
    }        
    
    public void ruchGora(){
        Pozycja nowaPozycja = new Pozycja(0,0);
        nowaPozycja.setX(this.getPozycja().getX());
        nowaPozycja.setY(this.getPozycja().getY()-this.getZasieg());
        ruch(nowaPozycja);
    }    
        
     
    
    
    public void ruch(Pozycja nowa){        
        if(nowa.czyRowne(this.getPozycja())) return;
        if(!world.czyWPlanszy(nowa)) return;
        if(world.czyWolne(nowa)){
            this.pozycja=nowa;
            return;
        }
        Organizm atakowany = world.znajdzPozycje(nowa);
        if(atakowany.czyZywy()) kolizja(this, atakowany);
        if(this.czyZywy() && this.mozeRuszac) this.pozycja=nowa;
        
    }    
    
    
  
        
}
