package com.accurate.discussions.utils;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.context.AdfFacesContext;

/**
 * Utils class.
 *
 * @author Accurate Software
 *
 */
public final class Utils {
    
    private Utils() {}

    /**
     * Resolve Expression
     * 
     * @param <T>
     * @param expression
     * @param classType
     * @return T type passed as parameter in classType
     */
    public static <T> T resolveExpression(String expression, Class<T> classType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return classType.cast(valueExp.getValue(elContext));
    }

    /**
     * Set param to PageFlow
     * 
     * @param key
     * @param value
     */
    public static void addParamPageFlow(String key, Object value) {
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put(key, value);
    }

    /**
     * Get Param from PageFlow
     * 
     * @param key
     * @return value
     */
    public static <T> T getParamPageFlow(String key, Class<T> classType) {
        return classType.cast(AdfFacesContext.getCurrentInstance().getPageFlowScope().get(key));
    }
}
