package utfpr.ct.dainf.if62c.avaliacao;

import java.util.TimerTask;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class Aviso extends TimerTask {
    
    protected final Compromisso compromisso;

    public Aviso(Compromisso compromisso) {
       this.compromisso = compromisso;
    }
    public void run(){
        long sec=(compromisso.getData().getTime()/1000-(int)(System.currentTimeMillis()/1000));
        System.out.println(compromisso.getDescricao()+" começa em "+sec+"s.");
    }
        
}
