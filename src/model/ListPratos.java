package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat_000
 */
public class ListPratos {
    
    public List<Prato> pratos = new ArrayList<>();

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
    
}
