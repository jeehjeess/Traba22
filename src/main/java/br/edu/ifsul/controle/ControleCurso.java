/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.trabalho1etapa.Aluno;
import br.edu.ifsul.trabalho1etapa.Curso;
import br.edu.ifsul.trabalho1etapa.Disciplina;
import br.edu.ifsul.trabalho1etapa.Instituicao;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author jessi
 */
@Named(value = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {

    @EJB
    private CursoDAO<Curso> dao;
    private Curso objeto;
    @EJB
    private InstituicaoDAO<Instituicao> daoInstituicao;
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;
    private Disciplina disciplina;
    private Boolean novaDisciplina;

    public ControleCurso() {

    }

    public void imprimeCursos() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("_blank", parametros, dao.getListaTodos());
    }

    public void imprimeCurso(Object id) {
        try {
            objeto = dao.localizar(id);
            List<Curso> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("_blank", parametros, lista);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
        }
    }

    public void novaDisciplina() {
        novaDisciplina = true;
        disciplina = new Disciplina();
    }

    public void alterarDisciplina(int index) {
        disciplina = objeto.getDisciplinas().get(index);
        novaDisciplina = false;
    }

    public void salvarDisciplina() {
        if (novaDisciplina) {
            objeto.adicionarDisciplina(disciplina);
        }
        Util.mensagemInformacao("Disciplina adicionada ou atualizada com sucesso");
    }

    public void removerDisciplina(int index) {
        objeto.removerDisciplina(index);
        Util.mensagemInformacao("Disciplina removida com sucesso!");
    }

    public String listar() {
        return "/privado/curso/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Curso();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public InstituicaoDAO<Instituicao> getDAOInstituicao() {
        return getDaoInstituicao();
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public Curso getCurso() {
        return objeto;
    }

    public void setCurso(Curso curso) {
        this.objeto = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Boolean getNovaDisciplina() {
        return novaDisciplina;
    }

    public void setNovaDisciplina(Boolean novaDisciplina) {
        this.novaDisciplina = novaDisciplina;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }

}
