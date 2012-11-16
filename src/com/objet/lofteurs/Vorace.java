/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

/**
 *
 * @author paulo
 */
public class Vorace extends Neuneu{
    
    public Vorace(int energie, int typeNeuneu, Loft monLoft, int position[]){
        super(energie, typeNeuneu, monLoft, position);
        this.nomImage = "vorace.png";
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
            
            //System.out.println("vorace qui mange!");
            //System.out.println("/////////////////");
            

        }
    }

    @Override
    public void reproduire() {
        //cette classe ne se reproduit pas
    }

    @Override
    public void deplacer() {
        
        if( (this.position[0] != 0)&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]--;
                this.position[1]--;
        }
        else if( (this.position[1] != 0)&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[1]--;
        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1)&&(this.position[1] != 0) )&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]++;
                this.position[1]--;
        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1) )&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]++;
        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1)&&(this.position[1] != this.monLoft.getTailleLoft() - 1) )&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]++;
                this.position[1]++;
        }
        else if(( this.position[1] != this.monLoft.getTailleLoft() - 1)&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[1]++;

        }
        else if(( (this.position[0] != 0)&&(this.position[1] != this.monLoft.getTailleLoft() - 1) )&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]--;
                this.position[1]++;
        }
        else if(( (this.position[0] != 0) )&&(this.position[1] !=0)&&(this.monLoft.caseVide(this.position[0], this.position[1])&&(this.monLoft.caseNourriture(this.position[0], this.position[1])) )){
                this.position[0]--;
        }
        else{
            this.deplacerParHazard();
        }
        
    }
    
    
    @Override
    public boolean cannibale() {
        //il n'est pas cannibale, donc il jamais mangera autre neuneu!
        return false;
    }
    
}
