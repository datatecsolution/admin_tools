package view.botones;

import javax.swing.ImageIcon;

public class BotonPrecio extends BotonesApp {
	
	public BotonPrecio(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/precio.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Precio (F8)");
		//setSize(136, 77);
		
	}

}
