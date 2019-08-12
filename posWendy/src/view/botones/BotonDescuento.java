package view.botones;

import javax.swing.ImageIcon;

public class BotonDescuento extends BotonesApp {
	
	public BotonDescuento(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/descuento.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Descuento (F7)");
		//setSize(136, 77);
	}

}
