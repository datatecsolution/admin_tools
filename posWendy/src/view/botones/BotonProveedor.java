package view.botones;

import javax.swing.ImageIcon;

public class BotonProveedor extends BotonesApp {
	
	public BotonProveedor(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/proveedores.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Pago proveedor (F11)");
		//setSize(136, 77);
	}

}
