//teste.txt x,y e labirinto
//matriz LABIRINTO
//pilha cordenada chamada CAMINHO do tamanho x * y
//pilha de pilha cordenada chamado POSSIBILIDADES do tamanho x * y que armazena as pilhas CAMINHO
//objeto ATUAL
//fila cordenada chamado FILA DE ADJACENTES com tamanho 3
//pilha cordenada chamado INVERSO com tamanho x * y
//metodos obrigatórios equals, hash code e tostring

import java.io.*;

public class Main{
  private static BufferedReader leitor = new BufferedReader (new InputStreamReader(System.in));
  
  public static void main(String[] args){
    
    System.out.println("\n\n Digite o nome do arquivo com .txt: ");
    Arquivo arquivo = null;
    Labirinto labirinto = null;
    
    try{
      String caminho = leitor.readLine();
      arquivo = new Arquivo(caminho);
    }
    catch(Exception Error){
      System.out.println("\n ----- ERRO: Arquivo invalido - " +Error);
    }
    try{
      labirinto = new Labirinto(arquivo.carregarArquivo(),arquivo.getLinhas(),arquivo.getColunas());
      String solucao = labirinto.solucaoLabirinto();
    	System.out.println("\n" + solucao);
    }
    catch(Exception Error){
      System.out.println("\n ----- ERRO: labirinto - " +Error);
    }
	}
}