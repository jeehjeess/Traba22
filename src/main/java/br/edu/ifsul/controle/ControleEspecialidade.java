
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.trabalho1etapa.Especialidade;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.jfree.util.HashNMap;

/**
 *
 * @author jessica
 */
@Named(value = "controleEspecialidade")
@ViewScoped
public class ControleEspecialidade implements Serializable{
    @EJB
    private EspecialidadeDAO<Especialidade> dao; 
    private Especialidade objeto; 
    
    public ControleEspecialidade(){
    }
    public void imprimeEspecialidades(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("Blank_A4", parametros, dao.getListaTodos());
    }
 
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    public void novo(){
        objeto = new Especialidade();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.localizar(id);
        
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
   public void excluir(Object id){
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public EspecialidadeDAO<Especialidade> getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO<Especialidade> dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }

}