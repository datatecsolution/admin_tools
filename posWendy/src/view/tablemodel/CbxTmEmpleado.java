package view.tablemodel;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import modelo.Caja;
import modelo.Empleado;
import modelo.Impuesto;


public class CbxTmEmpleado extends DefaultComboBoxModel{
	
	private Vector<Empleado> empleados=new Vector<Empleado>();
	
	public void agregar(Empleado c){
		empleados.addElement(c);
	}
	
	public Empleado getEmpleado(int position){
		return empleados.get(position);
	}

	@Override
	public int getSize() {
		  return empleados.size();
		 }
	
	@Override
	public Object getElementAt(int index) {
		  return empleados.get(index);
		 }
	
	public void setLista(Vector<Empleado> im){
		empleados=im;
	}
	public void addEmpleado(Empleado m){
		empleados.addElement(m);
		//this.f

	}
	
	public CbxTmEmpleado(){
		
	}
	
	public int buscarImpuesto(Empleado m){
		int index=-1;
		
		for(int c=0;c<empleados.size();c++){
			
			if(empleados.get(c).getCodigo()==m.getCodigo()){
				
				index=c;
			}
		}
		
		return index;
	}

}
