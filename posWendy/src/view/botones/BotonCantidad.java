package view.botones;

import javax.swing.ImageIcon;

public class BotonCantidad extends BotonesApp {
	
	public BotonCantidad(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/cantidad.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Cantidad (F9)");
		//setSize(136, 77);
	}
	
}
