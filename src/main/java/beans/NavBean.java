package beans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
@Named
@SessionScoped
public class NavBean implements Serializable{
    private String page = "index.html";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
