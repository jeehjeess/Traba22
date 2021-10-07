
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.trabalho1etapa.Instituicao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author jessica
 */
@Stateful
public class InstituicaoDAO<TIPO> extends DAOGenerico<Instituicao> implements Serializable{
    
    public InstituicaoDAO(){
    
        super();
        classePersistente = Instituicao.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("anoFundacao", "Ano Fundação", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    
}
