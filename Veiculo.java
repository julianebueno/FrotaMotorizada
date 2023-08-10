package FrotaMotorizada;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {
	//(marca, modelo, ano, quilometragem, placa)
	private static final long serialVersionUID = 1L;
	private   String marca;
	private   String modelo;
	private   int ano;
	private   int quilometragem;
	private	  String placa;
	protected String categoria;
	
	
	public Veiculo(String marca, String modelo, int ano, int quilometragem, String placa) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.placa = placa;
	}
	public String toString(){
		String retorno = "";
		retorno += "Marca: " + this.marca + "\n";
		retorno += "Modelo: " + this.modelo + "\n";
		retorno += "Ano: " + this.ano + "\n";
		retorno += "Quilometragem: " + this.quilometragem + "\n";
		retorno += "Placa: " + this.placa + "\n";
		retorno += "Categoria: " + this.categoria + "\n";
		retorno += "Propósito: "  + proposito() + "\n";
		return retorno;
	}

	public abstract String proposito();

}