package view.botones;

import javax.swing.ImageIcon;

public class BotonSalida extends BotonesApp {
	
	public BotonSalida(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/salida_dinero.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Salida efectivo (F12)");
		//setSize(136, 77);
	}

}
