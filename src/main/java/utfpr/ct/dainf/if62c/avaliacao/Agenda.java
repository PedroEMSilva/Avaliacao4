package utfpr.ct.dainf.if62c.avaliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * IF62C Fundamentos de Programação 2 Avaliação parcial.
 *
 * @author
 */
public class Agenda {

    private final String descricao;
    private final List<Compromisso> compromissos = new ArrayList<>();
    private final Timer timer;

    public Agenda(String descricao) {
        this.descricao = descricao;
        timer = new Timer(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void novoCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        Aviso aviso = new AvisoFinal(compromisso);
        compromisso.registraAviso(aviso);
        // com a classe Aviso devidamente implementada, o erro de compilação
        // deverá desaparecer
        timer.schedule(aviso, compromisso.getData());
    }

    public void novoAviso(Compromisso compromisso, int antecedencia) {
        Aviso av = new Aviso(compromisso);
        compromisso.registraAviso(av);
        Date date = new Date();
        timer.schedule(av, compromisso.getData().getTime() - (date.getTime() + (antecedencia * 1000)));

    }

    public void novoAviso(Compromisso compromisso, int antecedencia, int intervalo) {
        Aviso av = new Aviso(compromisso);
        compromisso.registraAviso(av);
        Date date = new Date();
        timer.schedule(av, compromisso.getData().getTime() - (date.getTime() + (antecedencia * 1000)), intervalo * 1000);

    }

    public void cancela(Compromisso compromisso) {
        for (Aviso a2 : compromisso.getAvisos()) {
            a2.cancel();
        }
        getCompromissos().remove(compromisso);
    }

    public void cancela(Aviso aviso) {
        aviso.compromisso.getAvisos().remove(aviso);
        if (aviso != null) {
            aviso.cancel();
        }
        //COMO TIRAR DA LISTA DE COMPROMISSO SE EU NEM SEI O COMPROMISSO????Acho q arrumei
       
    }

    public void destroi() {
        for(Compromisso e:getCompromissos()){
            if(e!=null){
                for(Aviso a:e.getAvisos()){
                    if(a!=null){
                        a.cancel();
                    }
                }
                
            }
            
        }
        timer.cancel();

    }
}
