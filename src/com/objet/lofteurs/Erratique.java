/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

import java.util.Random;

/**
 *
 * @author paulo
 */
public class Erratique extends Neuneu{
    
    public Erratique(int energie, int typeNeuneu, Loft monLoft, int position[]){
        super(energie, typeNeuneu, monLoft, position);
        this.nomImage = "erratique.png";
    }

    @Override
    public void manger() {
        //vérifier si il y a une nourriture dans la case oú le neuneu se trouve
        if(this.monLoft.c[this.position[0]][this.position[1]].getNourriture() != null){
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            this.monLoft.c[this.position[0]][this.position[1]].soustraireNourriture();
            //vérifier si la lourriture est dejà fini, et la effacer dans ce cas
            if( this.monLoft.c[this.position[0]][this.position[1]].getQuantiteNourriture() <=0  ){
                this.effacerNourriture( this.monLoft.c[this.position[0]][this.position[1]].getNourriture() );
            }
            

        }
    }
    
    
    /**
    * Règle de reproduction utilisé:
    * 1.soustraire energie de les 2 neuneus qui se reproduisent
    * 2.rechercher une case vide au hazard et créer le fillot là bas
    *   créer le fillot sans énergie suffisant por se reproduire
    * 3.rechercher la première case vide et deplacer le neuneu2 si il n'y a pas 
    * d'énergie suffisant pour une autre réproduction
    * 
    * 
    */
    @Override
    public void reproduire() {
        //cette classe ne se reproduit pas
  
    }
    
    //deplacement au hazard des erratiques
    @Override
    public void deplacer() {
        this.deplacerParHazard();
    }

    @Override
    public boolean cannibale() {
        //il n'est pas cannibale, donc il jamais mangera autre neuneu!
        return false;
    }
    
    
}
