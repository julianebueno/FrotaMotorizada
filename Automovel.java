package FrotaMotorizada;

public class Automovel extends Veiculo{
	private static final long serialVersionUID = 1L;
	
	public String proposito() {
		return "Transporte compacto";
	}
	public Automovel(String marca, String modelo, int ano, int quilometragem, String placa) {
		super(marca, modelo, ano, quilometragem, placa);
		this.categoria = "Automovel";
	}
}
