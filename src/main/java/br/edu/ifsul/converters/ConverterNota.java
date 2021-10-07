/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.trabalho1etapa.Especialidade;
import br.edu.ifsul.trabalho1etapa.Nota;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jessi
 */
@Named(value = "converterNota")
@RequestScoped
public class ConverterNota implements Serializable, Converter{
    
    @PersistenceContext(unitName = "Trabalho1EtapaWebPU")
    private EntityManager em;
    
    @Override
   public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Nota.class, Integer.parseInt(string));
    }

    // converte o objeto que vem do banco em uma string para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
         Nota obj = (Nota) t;
        return obj.getId().toString();
    }
}
