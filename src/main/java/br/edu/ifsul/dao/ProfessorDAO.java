
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.trabalho1etapa.Professor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author jessica
 */
@Stateful
public class ProfessorDAO<TIPO> extends DAOGenerico<Professor> implements Serializable{
    
    public ProfessorDAO(){
        super();
        classePersistente = Professor.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("Especialidade.nome", "Especialidade", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
