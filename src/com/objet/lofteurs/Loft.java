/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author paulo
 */
public class Loft implements ObjetDessinable{
    
    
    /**
     * Nombre de cases du Loft sera w*h
     * Pour la création des contenus au dedans des cases, j'ai ajouté des 
     * nourritures (et ses quantites en chaque case) par hazard, et en ce
     * que les quantités seront au maximum de 10 par case
     * 
     * 
     * @param w dimension horizontale
     * @param h dimension verticale
     * 
     */
    public static int energieReproduction = 60;
    public static int energieNourriture = 20;
    
    
    private int tailleLoft;
    private ZoneGraphique zone;
    protected Case[][] c;
    protected ArrayList<Nourriture> listanou;
    protected ArrayList<Neuneu> listaneu;
    
    
    
    
    public Loft(int tailleLoft, ZoneGraphique zone ) {
        this.tailleLoft = tailleLoft;
        this.zone = zone;
    }

    @Override
    public void dessinerObjet(Graphics g) {
        int size = 600; //size in pixels
                
        BufferedImage bufImg = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);

        BufferedImage solo;

        try {
                solo        = ImageIO.read(new File("brown.jpeg"));

                Graphics horsEcran = bufImg.getGraphics();

                for(int i = 0; i < this.tailleLoft; i++){
                        for(int j = 0; j < this.tailleLoft; j++){
                                horsEcran.drawImage(solo, i * 20, j * 20, null);
                        }
                }
                
                //pour dessiner des nourritures
                for(Nourriture n : this.listanou){
                        BufferedImage bufImgNourriture = ImageIO.read(new File(n.getNomImage()));
                       int pos[] = n.getPosition();
                        horsEcran.drawImage(bufImgNourriture, pos[0] * 20, pos[1] * 20, null);        
                }
                
                //pour dessiner des neuneus
                for(Neuneu n : this.listaneu){
                        BufferedImage imgNeneu = ImageIO.read(new File(n.getNomImage()));
                        int pos[] = n.getPosition();
                        horsEcran.drawImage(imgNeneu, pos[0] * 20, pos[1] * 20, null);
                }

                g.drawImage(bufImg, 0, 0, null);

        } catch (IOException e) {
                System.out.println("Exception:(image!!) " + e.getMessage());
                e.printStackTrace();
        }
        
        
        
    }
    
    public void remplissageAleatoire(int proportionAleatoire){
        this.listanou = new ArrayList<Nourriture>();
        this.c = new Case[this.tailleLoft][this.tailleLoft];
        
        for(int i=0; i<tailleLoft; i++){
            for(int j=0; j<tailleLoft; j++){
                int position[];
                position = new int[2];
                position[0] = i;
                position[1] = j;
                Random randomGenerator = new Random();
                
                if (randomGenerator.nextInt(proportionAleatoire) >= 10){
                    Nourriture n;
                    //n = new Nourriture(randomGenerator.nextBoolean(), randomGenerator.nextInt(5), position);
                    n = new Nourriture(randomGenerator.nextBoolean(), randomGenerator.nextInt(2), 2, this, position);
                    
                    this.listanou.add(n);
                
                    this.c[i][j] = new Case(n);
                }
                else{                
                    this.c[i][j] = new Case(null);
                }
                    
                
            }
            
            
            
        }
    }
    
    public void ajouterNeuneusAleatoire(int proportionAleatoire){
        this.listaneu = new ArrayList<Neuneu>();
        
        for(int i=0; i<tailleLoft; i++){
            for(int j=0; j<tailleLoft; j++){
                int position[];
                position = new int[2];
                position[0] = i;
                position[1] = j;
                Random randomGenerator = new Random();
                
                if (randomGenerator.nextInt(proportionAleatoire) >= 100){
                    switch(randomGenerator.nextInt(4)){
                        case 0:
                            Erratique e;
                            e = new Erratique(100, 1, this, position);
                            this.listaneu.add(e);
                            this.c[i][j].setNeuneu(e);
                            
                            break;
                        case 1:
                            Lapin l;
                            l = new Lapin(100, 2, this, position);
                            this.listaneu.add(l);
                            this.c[i][j].setNeuneu(l);
                            
                            break;
                        case 2:
                            Vorace v;
                            v = new Vorace(100, 2, this, position);
                            this.listaneu.add(v);
                            this.c[i][j].setNeuneu(v);
                            
                            break;
                        case 3:
                            Cannibale c;
                            c = new Cannibale(100, 3, this, position);
                            this.listaneu.add(c);
                            this.c[i][j].setNeuneu(c);
                            
                            break;
                        default:
                            System.out.println("deu pau na criacao de neuneus aleatorios!!");
                    }
                    
                    
                    
                    
                }
                else{                
                    this.c[i][j].setNeuneu(null);
                }
                
                
            }
        }
    }
    
    public int getTailleLoft(){
        return this.tailleLoft;
    }
    
    //methode que retourne true si la case est vide
    public boolean caseVide(int positionX, int positionY){
        if (this.c[positionX][positionY].getNeuneu() == null ){
            return true; //peut se deplacer
        }
        return false;
    }
    
    //methode similaire à ci-dessus, mais avec controlle des erreurs en cas d'une entrée invalide
    public boolean caseCannibale(int positionX, int positionY){
        if((positionX < 0)||(positionY < 0)||(positionX == this.getTailleLoft())||(positionY == this.getTailleLoft())){
            return false;
        }
        else if( this.c[positionX][positionY].getNeuneu() != null  ){
            return true;
        }
        else
            return false;
        
    }
    
    
    public boolean caseNourriture(int positionX, int positionY){
        if (this.c[positionX][positionY].getNourriture() == null ){
            return false; //ne peut pas se deplacer
        }
        return true;
    }
    
    /**
     * retourne vrai si il y a dans la case (x,y) un neuneu1 du type typeneuneu,
     * si il y a energie suffisante pour la reproduction et aussi si neuneu2 est null
     * C'est important de vérifier si la case est dejà
     * 
     * 
     * */
    public boolean coupleReproduction(int positionX, int positionY, int typeNeuneu){
        if( (this.c[positionX][positionY].getNeuneu() != null)&&(this.c[positionX][positionY].getNeuneu2() == null) ){
            if((this.c[positionX][positionY].getTypeNeuneu() == typeNeuneu)&&(this.c[positionX][positionY].getEnergie1()>= energieReproduction) ){
               return true; 
            }
            
        }        
        return false;
    }
    
    public boolean isTypeNeuneu(int positionX, int positionY, int typeNeuneu){
        if( this.c[positionX][positionY].getTypeNeuneu() == typeNeuneu ){
            return true;
        }
        return false;
    }
    
    
    public void remplacerNeuneuCase(int positionInitialX, int positionInitialY, int positionFinalX, int positionFinalY, Neuneu n){
        this.c[positionFinalX][positionFinalY].setNeuneu(n);
        this.c[positionInitialX][positionInitialY].setNeuneu(null);
        //System.out.println("case mis a jour!");
        
    }
    
    public void remplacerNeuneuCase2(int positionInitialX, int positionInitialY, int positionFinalX, int positionFinalY, Neuneu n){
        this.c[positionFinalX][positionFinalY].setNeuneu2(n);
        this.c[positionInitialX][positionInitialY].setNeuneu(null);
        //System.out.println("case mis a jour pour reproduction!");
        
    }
    
    public int getEnergieReproduction(){
        return energieReproduction;
    }
    
    public int getEnergieNourriture(){
        return energieNourriture;
    }
    
    public void go(Graphics g) throws InterruptedException{
        
        while(true){
            
            
            //ce structure est necessaire pour deplacer chaque neuneu, et 
            //de temps en temps tuer des neuneus sans energie sufisante pour vivre
            int flag = listaneu.size();
            int counter = 0;
            while(flag > 0){
                Neuneu pointer;
                 
                pointer = listaneu.get(counter);
                
                pointer.deplacer();
                pointer.manger();
                pointer.reproduire();
                if(pointer.getEnergie()<=0){
                    pointer.tuerNeuneu();
                    flag--;
                }
                if(pointer.cannibale()){
                    flag--;
                    
                }
                
                
                flag--;
                counter++;
                
            }
            //fin de la structure de deplacement
            
            
            //attendre un temp pour bien voir qu'est-ce que se passe dans le loft
            //Thread.sleep(50);
            
            this.dessinerObjet(g);
        
        }
        
        
        
        
        
        
    
    }


    
    
}
