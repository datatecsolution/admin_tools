package view.botones;

import javax.swing.ImageIcon;

public class BotonExistencia  extends BotonesApp {

	public BotonExistencia() {
		// TODO Auto-generated constructor stub
		setIcon(new ImageIcon(BotonExistencia.class.getResource("/view/recursos/almacen.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Existencia");
	}

}
