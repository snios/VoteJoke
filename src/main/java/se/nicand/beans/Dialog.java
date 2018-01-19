/**
 * Andre Olofsson och Nicklas Öst
 * Course: Java EE
 * Date: 2018-01-19
 */
package se.nicand.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;


@Named
@SessionScoped
public class Dialog implements Serializable{

    public void rateJoke(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("submitjoke",options,null);
    }
}
