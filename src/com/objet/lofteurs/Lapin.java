/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

/**
 *
 * @author paulo
 */
public class Lapin extends Neuneu{
    
    public Lapin(int energie, int typeNeuneu, Loft monLoft, int position[]){
        super(energie, typeNeuneu, monLoft, position);
        this.nomImage = "lapin.png";
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

    @Override
    public void reproduire() {
        //vérifier si il y a autre neuneu pour se reproduire
        if( (this.enReproduction)&&(this.monLoft.c[this.position[0]][this.position[1]].neuneu2.enReproduction) ){

            //soustraire energie
            this.energie = this.energie - this.monLoft.getEnergieReproduction();
            this.monLoft.c[this.position[0]][this.position[1]].neuneu2.energie = this.monLoft.c[this.position[0]][this.position[1]].neuneu2.energie - this.monLoft.getEnergieReproduction();
            
            //fixer l'attribute enReproduction commme faux pour les deux neuneus
            this.setEnReproduction(false);
            this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(false);
            
            //TODO créer le fillot
            Lapin l;
            l = new Lapin(100, 1, this.monLoft, this.position);
            l.deplacerParHazard();
            l.monLoft.c[l.position[0]][l.position[1]].setNeuneu(l);
            this.monLoft.listaneu.add(l);
            
            //déplacer le neuneu2 après la reproduction
            this.monLoft.c[this.position[0]][this.position[1]].neuneu2.deplacerParHazard();
            
            //System.out.println("reproduction!");
            
            
                    
                
            
        }
    }
    

    @Override
    public void deplacer() {
   
        //vérifier si il y a quelque neuneu du même type pour se reproduire
        if( this.enReproduction ){
            //reproduire, ils ne se deplacent pas!
        } //TODO ajouter condition , si il n'y a pas d'energie siffisante dans les deux neuneus, le if est ignorée
        else if( (this.position[0] != 0)&&(this.position[1] !=0)&&( this.monLoft.coupleReproduction(this.position[0] - 1, this.position[1] - 1, this.typeNeuneu))){
                this.position[0]--;
                this.position[1]--;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);
                //TODO atualizar case com nova posicao do neuneu this -sera neuneu2
        }
        else if( (this.position[1] != 0)&&(this.monLoft.coupleReproduction(this.position[0], this.position[1] - 1, this.typeNeuneu)) ){
                this.position[1]--;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);
        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1)&&(this.position[1] != 0) )&&( this.monLoft.coupleReproduction(this.position[0] + 1, this.position[1] - 1, this.typeNeuneu) )){
                this.position[0]++;
                this.position[1]--;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);

        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1) )&&( this.monLoft.coupleReproduction(this.position[0] + 1, this.position[1], this.typeNeuneu) )){
                this.position[0]++;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);

        }
        else if(( (this.position[0] != this.monLoft.getTailleLoft() - 1)&&(this.position[1] != this.monLoft.getTailleLoft() - 1) )&&( this.monLoft.coupleReproduction(this.position[0] + 1, this.position[1] + 1, this.typeNeuneu) )){
                this.position[0]++;
                this.position[1]++;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);
        }
        else if(( this.position[1] != this.monLoft.getTailleLoft() - 1)&&( this.monLoft.coupleReproduction(this.position[0], this.position[1] + 1, this.typeNeuneu) )){
                this.position[1]++;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);

        }
        else if(( (this.position[0] != 0)&&(this.position[1] != this.monLoft.getTailleLoft() - 1) )&&( this.monLoft.coupleReproduction(this.position[0] - 1, this.position[1] + 1, this.typeNeuneu) )){
                this.position[0]--;
                this.position[1]++;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);
        }
        else if(( (this.position[0] != 0) )&&( this.monLoft.coupleReproduction(this.position[0] - 1, this.position[1], this.typeNeuneu) )){
                this.position[0]--;
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2 = this;
                this.setEnReproduction(true);
                this.monLoft.c[this.position[0]][this.position[1]].neuneu2.setEnReproduction(true);
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
