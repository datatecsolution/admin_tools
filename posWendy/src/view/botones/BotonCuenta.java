package view.botones;

import javax.swing.ImageIcon;

public class BotonCuenta  extends BotonesApp {
	
	public BotonCuenta(){
		setIcon(new ImageIcon(BotonKardex.class.getResource("/view/recursos/contable.png"))); // NOI18N
		//this.setSize(200, 100);.
		setToolTipText("Reporte cuenta");
	}

}
