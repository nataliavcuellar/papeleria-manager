package co.edu.unbosque.view;

public class ViewFacade {
	private VentanaPrincipal vp;
	private VentanaAuxiliar va;

	public ViewFacade() {
		vp = new VentanaPrincipal();
		va = new VentanaAuxiliar(vp);
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

	public VentanaAuxiliar getVa() {
		return va;
	}

	public void setVa(VentanaAuxiliar va) {
		this.va = va;
	}

}
