package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entity.Viaje;
import utils.JpaUtils;

public class ViajeFacade extends AbstractFacade<Viaje> implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public ViajeFacade() {
		super(Viaje.class);
		getEntityManager();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		if (em == null) {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
		}
		return em;
	}
	public List<?>findByTelefono(String telefono){
		getEntityManager();
//		String sql="select v.origen,v.destino,v.precio,v.fecha from viaje v "+ 
//				   "inner join conductor c on v.id_conductor=c.id_conductor where c.telefono='"+telefono+"';";
		
		String sql ="select v.* from viaje v inner join conductor c on v.id_conductor=c.id_conductor where c.telefono='"+telefono+"';";
		System.out.println(sql.toString());
		
		List<?>listaViaje=null;
		try {
			listaViaje=em.createNativeQuery(sql,Viaje.class).getResultList();
			for(Object o:listaViaje) {
				o.toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(listaViaje==null) {
			listaViaje= new ArrayList<Viaje>();
		}
		return listaViaje;
	}

}
