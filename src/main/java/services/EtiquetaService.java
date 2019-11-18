package services;


import entidades.Articulo;
import entidades.Etiqueta;
import entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaService extends BaseService<Etiqueta> {
    private static EtiquetaService etiquetaServiceIntance;

    public EtiquetaService() {
        super(Etiqueta.class);
    }

    public static EtiquetaService getInstance() {
        if(etiquetaServiceIntance==null){
            etiquetaServiceIntance = new EtiquetaService();
        }
        return etiquetaServiceIntance;
    }
}

