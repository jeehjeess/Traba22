package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.dao.NotaDAO;
import br.edu.ifsul.trabalho1etapa.Aluno;
import br.edu.ifsul.trabalho1etapa.Curso;
import br.edu.ifsul.trabalho1etapa.Disciplina;
import br.edu.ifsul.trabalho1etapa.Instituicao;
import br.edu.ifsul.trabalho1etapa.Nota;
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
 * @author jessica
 */
@Named(value = "controleDisciplina")
@ViewScoped
public class ControleDisciplina implements Serializable {

    @EJB
    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;

    private Aluno aluno;
    private Boolean novoAluno;

    @EJB
    private AlunoDAO<Aluno> daoAluno;
    //private Aluno aluno;
    @EJB
    private CursoDAO<Curso> daoCurso;
    @EJB
    private InstituicaoDAO<Instituicao> daoInstituicao;

    public ControleDisciplina() {
    }
    
    public void imprimeDisciplinas(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioDisciplinas", parametros, dao.getListaCompleta());
    }
    
    public void imprimeDisciplina(Object id){
        try {
            objeto = dao.localizar(id);
            List<Disciplina> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioDisciplinas", parametros, lista);
        } catch (Exception e) {
             Util.mensagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
        }
    }
    
    public void novoAluno(){
        setNovoAluno((Boolean) true);
        setAluno(new Aluno());
    }
    
   /* public void alterarAluno(int index){
        setAluno(objeto.getAlunos().get(index));
        setNovoAluno((Boolean) false);
    }*/
   /* public void salvarAluno(){
        if(getNovoAluno()){
            objeto.adicionarAluno(getAluno());
        }
        Util.mensagemInformacao("Aluno adicionada ou atualizada com sucesso!!");
    }*/

   /* public void removerAluno(int index){
        objeto.removerAluno(index);
        Util.mensagemInformacao("Aluno removido com sucesso!");
    }*/
    public String listar() {
        return "/privado/disciplina/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Disciplina();
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

    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }

    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
        this.objeto = objeto;
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public CursoDAO<Curso> getDaoCurso() {
        return daoCurso;
    }

    public void setDaoCurso(CursoDAO<Curso> daoCurso) {
        this.daoCurso = daoCurso;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Boolean getNovoAluno() {
        return novoAluno;
    }

    public void setNovoAluno(Boolean novoAluno) {
        this.novoAluno = novoAluno;
    }


}
