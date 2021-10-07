
package br.edu.ifsul.dao;


import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.trabalho1etapa.Especialidade;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author jessica
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends DAOGenerico<Especialidade> implements  Serializable{
    
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
        
               
    }
}
