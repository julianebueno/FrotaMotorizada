package FrotaMotorizada;

import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Frota {
		private ArrayList<Veiculo> veiculo;

		public Frota(){
			this.veiculo = new ArrayList<Veiculo>();
		}

		public String[] leDados (String [] dadosIn){
			String [] dadosOut = new String [dadosIn.length];

			for (int i = 0; i < dadosIn.length; i++)
				dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

			return dadosOut;
		}

		public Caminhao leCaminhao (){

			String [] dados = new String [5];
			String [] nomeDados = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa"};
			dados = leDados (nomeDados);

			int ano = this.retornaInteiro(dados[2]);
			int quilometragem = this.retornaInteiro(dados[3]);
			
			Caminhao caminhao = new Caminhao (dados[0],dados[1],ano,quilometragem, dados[4]);
			return caminhao;
		}

		public Onibus leOnibus (){
			
			String [] dados = new String [5];
			String [] nomeDados = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa"};
			dados = leDados (nomeDados);

			int ano = this.retornaInteiro(dados[2]);
			int quilometragem = this.retornaInteiro(dados[3]);
			
			Onibus onibus = new Onibus (dados[0],dados[1],ano,quilometragem, dados[4]);
			return onibus;
		}
		
		public Automovel leAutomovel (){
			
			String [] dados = new String [5];
			String [] nomeDados = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa"};
			dados = leDados (nomeDados);

			int ano = this.retornaInteiro(dados[2]);
			int quilometragem = this.retornaInteiro(dados[3]);
			
			Automovel automovel = new Automovel (dados[0],dados[1],ano,quilometragem, dados[4]);
			return automovel;
		}

		private boolean intValido(String s) {
			try {
				Integer.parseInt(s); 
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		public int retornaInteiro(String entrada) { 

			while (!this.intValido(entrada)) {
				entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
			}
			return Integer.parseInt(entrada);
		}

		public void salvaVeiculo (ArrayList<Veiculo>veiculo){
			ObjectOutputStream outputStream = null;
			try {
				outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\Frota.dados"));
				for (int i=0; i < veiculo.size(); i++)
					outputStream.writeObject(veiculo.get(i));
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null,"Impossivel criar este arquivo!");
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (outputStream != null) {
						outputStream.flush();
						outputStream.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		@SuppressWarnings("finally")
		public ArrayList<Veiculo> recuperaVeiculo (){
			ArrayList<Veiculo> veiculoTemp = new ArrayList<Veiculo>();

			ObjectInputStream inputStream = null;

			try {	
				inputStream = new ObjectInputStream
						(new FileInputStream("c:\\temp\\Frota.dados"));
				Object obj = null;
				while ((obj = inputStream.readObject()) != null) {
					if (obj instanceof Veiculo) {
						veiculoTemp.add((Veiculo) obj);
					}   
				}          
			} catch (EOFException ex) { 
				System.out.println("Fim de arquivo.");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null,"Arquivo com os veiculos nao existe!");
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally { 
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
				return veiculoTemp;
			}
		}

		public void menuFrota (){

			String menu = "";
			String entrada;
			int    opc1, opc2;

			do {
				menu = "Controle de Veiculos\n" +
						"Opcoes:\n" + 
						"1. Cadastrar Veiculos\n" +
						"2. Exibir Veiculos\n" +
						"3. Limpar Veiculos\n" +
						"4. Gravar Veiculos\n" +
						"5. Recuperar Veiculos\n" +
						"9. Sair";
				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc1 = this.retornaInteiro(entrada);

				switch (opc1) {
				case 1:
					menu = "Escolher categoria de veículo\n" +
							"Opcoes:\n" + 
							"1. Caminhao\n" +
							"2. Onibus\n" +
							"3. Automovel\n";

					entrada = JOptionPane.showInputDialog (menu + "\n\n");
					opc2 = this.retornaInteiro(entrada);

					switch (opc2){
					case 1: veiculo.add((Veiculo)leCaminhao());
					break;
					case 2: veiculo.add((Veiculo)leOnibus());
					break;
					case 3: veiculo.add((Veiculo)leAutomovel());
					break;
					default: 
						JOptionPane.showMessageDialog(null,"Nao foi escolhido uma categoria!");
					}

					break;
				case 2:
					if (veiculo.size() == 0) {
						JOptionPane.showMessageDialog(null,"Primeiro cadastre veiculos nas categorias!");
						break;
					}
					String dados = "";
					for (int i=0; i < veiculo.size(); i++)	{
						dados += veiculo.get(i).toString() + "---------------\n";
					}
					JOptionPane.showMessageDialog(null,dados);
					break;
				case 3: 
					if (veiculo.size() == 0) {
						JOptionPane.showMessageDialog(null,"Primeiro cadastre veiculos nas categorias!");
						break;
					}
					veiculo.clear();
					JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
					break;
				case 4: 
					if (veiculo.size() == 0) {
						JOptionPane.showMessageDialog(null,"Primeiro cadastre veiculos nas categorias!");
						break;
					}
					salvaVeiculo(veiculo);
					JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
					break;
				case 5: 
					veiculo = recuperaVeiculo();
					if (veiculo.size() == 0) {
						JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
						break;
					}
					JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
					break;
				case 9:
					JOptionPane.showMessageDialog(null,"Fim do aplicativo Frota de Veiculos.");
					break;
				default:
					JOptionPane.showMessageDialog(null,"Escolha uma opção válida.");
				}
			} while (opc1 != 9);
		}


		public static void main (String [] args){

			Frota del = new Frota ();
			del.menuFrota();

		}
}
