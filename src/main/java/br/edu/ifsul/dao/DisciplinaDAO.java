/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.trabalho1etapa.Disciplina;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author jessi
 */
@Stateful
public class DisciplinaDAO<TIPO> extends DAOGenerico<Disciplina> implements Serializable {

    public DisciplinaDAO() {
        super();
        classePersistente = Disciplina.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

    public Disciplina localizar(Object id) throws Exception {

        Disciplina objeto = em.find(Disciplina.class, id);
//        objeto.getNotas();
        objeto.getNotas().size();
        return objeto;
    }
    public List<Disciplina> getListaCompleta(){
        String jpql = "select distinct d from Disciplina d left join fetch d.notas order by t.id";
        return em.createQuery(jpql).getResultList();
    }
}
