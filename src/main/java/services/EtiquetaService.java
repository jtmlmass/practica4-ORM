package services;


import entidades.Etiqueta;

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

