package br.com.schoolportal.view.mbeans;

import java.io.Serializable;

public class CustomMBean implements Serializable {
    
    private String text = "Wow Im fu***ng here!!";


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
