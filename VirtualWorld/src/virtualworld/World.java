/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lohachov Danylo
 */
public class World{
    
    Pozycja rozmiar;
    
    protected Organizm[] organizmy;
    protected int ileOrganizmow=0;
    public void setIleOrganizmow(int x){ this.ileOrganizmow=x;}
    public int getIleOrganizmow(){ return ileOrganizmow;}    
    protected int ktoraTura =0;
    public void setTura(int x){ this.ktoraTura = x;}
    protected boolean koniecGry = false;
    public int actionKey = 0;
    public String komunikat="Spacja - aktywacja supermocy";    
    public World(int x, int y){
        rozmiar = new Pozycja(x,y);        
        organizmy = new Organizm[x*y];
        
    }
    private boolean hexMode = false;
    public boolean getHexMode(){return this.hexMode;}
    public void setHexMode(boolean x){this.hexMode=x;}
        
    public Pozycja getRozmiar(){
        return this.rozmiar;
    }
    
    public boolean czyWolne(Pozycja pos){        
        for(int i=0; i<ileOrganizmow; i++){
            if(organizmy[i].getPozycja().czyRowne(pos)) return false;
        }
        return true;
    }
    
    public Organizm znajdzPozycje(Pozycja pos){
        if(!this.czyWPlanszy(pos)) return null;
        for(int i=0; i<this.getIleOrganizmow(); i++){
            if(organizmy[i].getPozycja().czyRowne(pos)) return organizmy[i];
        }
        return null;
    }
    
    public boolean czyWPlanszy(Pozycja pos){
        if(pos.getX()>=0 && pos.getY()>=0 && pos.getX() < rozmiar.getX() && pos.getY() < rozmiar.getY()) return true;
        return false;
    }
    
    public Pozycja losoweWolneOtocz(Pozycja srodek, int zasieg){
        int liczbaProb = 15;
        
        Random losowa = new Random();
        Pozycja nowaPozycja = new Pozycja(srodek.getX()+(losowa.nextInt(3)-1)*zasieg, srodek.getY()+(losowa.nextInt(3)-1)*zasieg);
        
        while(!czyWPlanszy(nowaPozycja) || !czyWolne(nowaPozycja) || nowaPozycja.czyRowne(srodek)){            
            if(liczbaProb == 0) return srodek;
            nowaPozycja = new Pozycja(srodek.getX()+(losowa.nextInt(3)-1)*zasieg, srodek.getY()+(losowa.nextInt(3)-1)*zasieg);
            liczbaProb--;
        }        
        return nowaPozycja;
    }
    
    public Pozycja losoweOtocz(Pozycja srodek, int zasieg){
        Random losowa = new Random();
        Pozycja nowaPozycja = new Pozycja(srodek.getX()+(losowa.nextInt(3)-1)*zasieg, srodek.getY()+(losowa.nextInt(3)-1)*zasieg);
        while(!czyWPlanszy(nowaPozycja) || nowaPozycja.czyRowne(srodek)){
           nowaPozycja = new Pozycja(srodek.getX()+(losowa.nextInt(3)-1)*zasieg, srodek.getY()+(losowa.nextInt(3)-1)*zasieg);           
        }
        return nowaPozycja;
    }
    public String losujRosline(){
        String nazwy[] = new String[]{"BarszczS", "Guarana", "Jagody", "Mlecz", "Trawa"};
        Random losowa = new Random();        
        return nazwy[losowa.nextInt(5)];
    }
    
    public String losujZwierze(){
        String nazwy[] = new String[]{"Antylopa", "CyberOwca", "Lis", "Owca", "Wilk", "Zolw"};
        Random losowa = new Random();        
        return nazwy[losowa.nextInt(6)];
    }
    
    
    
    
    public void dodajOrganizm(Pozycja pos, String nazwa){
        if(!czyWolne(pos) || this.getIleOrganizmow() == this.rozmiar.getY()*this.rozmiar.getY()) return;
        Organizm nowy;
        switch(nazwa){
            case "Czlowiek":
                nowy= new Czlowiek(pos, this);
                break;
            case "Wilk":
                nowy= new Wilk(pos, this);
                break;            
            case "Owca":
                nowy= new Owca(pos, this);
                break;
            case "Lis":
                nowy= new Lis(pos, this);
                break;
            case "Zolw":
                nowy= new Zolw(pos, this);
                break;
            case "Antylopa":
                nowy= new Antylopa(pos, this);
                break;
            case "CyberOwca":
                nowy= new CyberOwca(pos, this);
                break;
            case "Trawa":
                nowy= new Trawa(pos, this);
                break;
            case "Mlecz":
                nowy= new Mlecz(pos, this);
                break;    
            case "Guarana":
                nowy= new Guarana(pos, this);
                break;
            case "Jagody":
                nowy= new Jagody(pos, this);
                break;
            case "BarszczS":
                nowy= new BarszczS(pos, this);
                break;
            default: return;
        }
        nowy.mozeRuszac = false;
        organizmy[ileOrganizmow]=nowy;
        ileOrganizmow++;        
    }
    
    public Czlowiek getCzlowiek(){
        for(int i=0; i<ileOrganizmow; i++){
            if(this.organizmy[i] instanceof Czlowiek) return (Czlowiek) this.organizmy[i];            
        }
        return null;
    }
    
    public void wykonajTure(){
        //tutaj bedzie rysowanie
        for(int i=0; i<ileOrganizmow; i++){
            if(!organizmy[i].czyZywy()) continue;
            if("Czlowiek".equals(organizmy[i].getNazwa())){
                //tutaj bedzie ruch dla czlowieka
            }
            organizmy[i].akcja();
        }
        this.ktoraTura++;
        this.usunZabite();
        this.sortujPoInicjatywie();
        this.sortujPoWieku();
    }

    public void AddLog(String Log)
    {
        // Logs.add(Log);
        System.out.println(Log);
    }
    
    public void zabijAOE(Pozycja pos){
        Pozycja tmp = new Pozycja(pos.getX(), pos.getY());
        Organizm org;        
            //lewo gora
            tmp.setY(pos.getY()-1);
            tmp.setX(pos.getX()-1);
            for(int j=0; j<3; j++){                
                org= this.znajdzPozycje(tmp);
                if(org != null)
                    if((org instanceof Animal) && !(org instanceof CyberOwca)) org.zabij();
                tmp.setX(tmp.getX()+1);
            }
            //lewo srodek
            tmp.setY(pos.getY());
            tmp.setX(pos.getX()-1);            
            for(int j=0; j<3; j++){                
                org= this.znajdzPozycje(tmp);
                if(org != null)
                    if((org instanceof Animal) && !(org instanceof CyberOwca)) org.zabij();
                tmp.setX(tmp.getX()+1);
            }
            
            //lewo dol
            tmp.setY(pos.getY()+1);
            tmp.setX(pos.getX()-1);            
            for(int j=0; j<3; j++){                
                org= this.znajdzPozycje(tmp);
                if(org != null)
                    if((org instanceof Animal) && !(org instanceof CyberOwca)) org.zabij();
                tmp.setX(tmp.getX()+1);
            }       
    }
    
    public void swapOrganizm(int skad, int dokad){
	Organizm tmp = organizmy[dokad];
        organizmy[dokad] =organizmy[skad];
	organizmy[skad] = tmp;
    }
    
    public void usunZabite(){
	for (int i = ileOrganizmow-1; i > 0; i--){
		for (int j = 1; j <= i; j++){
			if(!organizmy[j-1].czyZywy()) swapOrganizm(j, j - 1);
		}
	}

	for (int i = ileOrganizmow - 1; !organizmy[i].czyZywy() && i >= 0; i--){		
            ileOrganizmow--;
	}
    }
    
    public void sortujPoInicjatywie(){
	for (int i = ileOrganizmow - 1; i > 0; i--){
		for (int j = 0; j < i; j++){
			if (organizmy[j+1].getInicjatywa() > organizmy[j].getInicjatywa()) swapOrganizm(j, j + 1);
		}
	}
}

    public void sortujPoWieku(){
	for (int i = ileOrganizmow - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
		if (organizmy[j].getNazwa().equals(organizmy[j + 1].getNazwa()) && organizmy[j+1].getWiek() > organizmy[j].getWiek())
		swapOrganizm(j, j + 1);
            }
	}

    }
    
    public void zapisz() throws FileNotFoundException {
        try (PrintWriter zapis = new PrintWriter("zapis.txt")) {
            zapis.println(this.getRozmiar().getX() + " " + this.getRozmiar().getY() + " " + this.ileOrganizmow + " " + this.ktoraTura+" "+ this.hexMode);
            
            Czlowiek czlowiek = this.getCzlowiek();
            if(czlowiek != null)
                zapis.println(czlowiek.getPozycja().getX() + " " + czlowiek.getPozycja().getY()+" " + czlowiek.getSila() + " " + czlowiek.getResetMocy() + " " + czlowiek.getSuperPower() + " " + czlowiek.getMocDostepna() + " " + czlowiek.getMocAktywna()+ " "+ czlowiek.getWiek());
            else
                zapis.println(-1);
            
            Organizm tmp;
            for(int i=0; i<this.getIleOrganizmow();i++){
                tmp = this.organizmy[i];
                zapis.println(tmp.getNazwa()+" "+tmp.getPozycja().getX() + " " + tmp.getPozycja().getY() + " "+ tmp.getSila()+ " " + tmp.getWiek()+ "\n");
            }
        }
    }
    
    public void wczytaj() throws FileNotFoundException {
        BufferedReader zapisany = new BufferedReader(new FileReader("zapis.txt"));
        Scanner wejscie = new Scanner(zapisany);
        this.getRozmiar().setX(wejscie.nextInt());
        this.getRozmiar().setY(wejscie.nextInt());
        int ileOrg = wejscie.nextInt();
        this.setIleOrganizmow(0);
        this.setTura(wejscie.nextInt());
        this.hexMode = wejscie.nextBoolean();
        this.organizmy = new Organizm[this.getRozmiar().getX()*this.getRozmiar().getY()];
        
        int x = wejscie.nextInt();
        
        if(x != -1) {
            Pozycja pos = new Pozycja(x, wejscie.nextInt());            
            this.dodajOrganizm(pos, "Czlowiek");
            Czlowiek czlowiek = (Czlowiek) this.znajdzPozycje(pos);
            czlowiek.setSila(wejscie.nextInt());
            czlowiek.ustawMoc(wejscie.nextInt(), wejscie.nextInt(), wejscie.nextBoolean(), wejscie.nextBoolean());
            czlowiek.setWiek(wejscie.nextInt());            
        }
        
        String nazwa;
        Pozycja pos;
        Organizm org;
        for(int i=0; i<ileOrg ;i++){
            nazwa = wejscie.next();
            pos = new Pozycja(wejscie.nextInt(),wejscie.nextInt());
            this.dodajOrganizm(pos, nazwa);
            org = this.znajdzPozycje(pos);
            org.setSila(wejscie.nextInt());
            org.setWiek(wejscie.nextInt());            
        } 
    }
}
