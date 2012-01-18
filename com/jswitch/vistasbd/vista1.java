package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * jaja prueba 5
 * @author Personal ----
 */
@Entity
public class vista1 extends BeanVO implements Serializable {

    /**
     *
     */
    @Column(updatable=false,insertable=false)
    @BusinessKey
    @Id
    private String nombre;
    
    /**
     *
     */
    @Column(updatable=false,insertable=false)
    @BusinessKey
    private Double suma;

    public vista1() {
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
