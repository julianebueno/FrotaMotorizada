package FrotaMotorizada;

public class Caminhao extends Veiculo{
	private static final long serialVersionUID = 1L;
	
	public String proposito() {
		return "Transporte de cargas";
	}
	public Caminhao(String marca, String modelo, int ano, int quilometragem, String placa) {
		super(marca, modelo, ano, quilometragem, placa);
		this.categoria = "Caminhao";
	}
	
}

