package FrotaMotorizada;

public class Onibus extends Veiculo{
	private static final long serialVersionUID = 1L;
	
	public String proposito() {
		return "Transporte de pessoas";
	}
	public Onibus(String marca, String modelo, int ano, int quilometragem, String placa) {
		super(marca, modelo, ano, quilometragem, placa);
		this.categoria = "Onibus";
	}
	

}


