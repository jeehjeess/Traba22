/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.trabalho1etapa.Curso;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author jessi
 */
@Stateful
public class CursoDAO<TIPO> extends DAOGenerico<Curso> implements  Serializable{
    
    public CursoDAO(){
        super();
        classePersistente = Curso.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("sigla", "Sigla", "like"));
        listaOrdem.add(new Ordem("descricao", "Descrição", "like"));
        listaOrdem.add(new Ordem("ativo", "Ativo", "like"));
        listaOrdem.add(new Ordem("inicioAtividade", "Inicio Atividades", "like"));
        listaOrdem.add(new Ordem("sigla", "Sigla", "like"));
        listaOrdem.add(new Ordem("instituicao.nome", "Instituição", "like"));
        
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    @Override
    public Curso localizar(Object id) throws Exception{
        Curso objeto = em.find(Curso.class, id);
        objeto.getDisciplinas().size();
        return objeto;
    }
    public List<Curso> getListaCompleta (){
        String jpql = "select distinct c from Curso c left join fetch c.disciplinas order by c.id";
        return em.createQuery(jpql).getResultList();
    }
    
}
