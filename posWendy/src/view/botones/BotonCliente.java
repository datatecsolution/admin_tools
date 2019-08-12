package view.botones;

import javax.swing.ImageIcon;

public class BotonCliente extends BotonesApp {
	
	public BotonCliente(){
		setIcon(new ImageIcon(BotonAgregar.class.getResource("/view/recursos/cliente.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Pago cliente (F10)");
		//setSize(136, 77);
		
	}

}
