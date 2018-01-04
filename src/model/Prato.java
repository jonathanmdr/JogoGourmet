package model;

/**
 *
 * @author jonat_000
 */
public class Prato {
    
    private String descricao;
    private String caracteristica;        
    
    public Prato(String descricao, String caracteristica) {
        this.descricao = descricao;
        this.caracteristica = caracteristica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }
    
}
