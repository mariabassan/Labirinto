package labirinto;

import java.io.*;

public class Matriz
{
  String labirinto; //conteudo do arquivo txt
  String arquivo = null; //caminho do arquivo txt
  int Linhas = 0;
  int Colunas = 0;
  BufferedReader reader = null; //leitor
  
  //Leitura do arquivo
  public Matriz(String arquivo) throws Exception
   {
	   try
	   {
		     if(this.reader == null)
		     {
		    	 this.reader = new BufferedReader(new FileReader(arquivo));
		     }
	   }
     
     catch(Exception Error)              
     {
		 throw new Exception ("\nCaminho inválido. Tente novamente, por favor.");
     }
     	this.arquivo = arquivo;
    	 verificaExtensao(arquivo);
    	 isArquivoVazio();
   }

   //Verifica se é um txt
   private void verificaExtensao(String arquivo) throws Exception
   {
	   if(!arquivo.endsWith(".txt")) //só aceita arquivo txt
	   {
		   throw new Exception ("Formato de arquivo inválido. Por favor, utilize somente arquivos .txt");
	   }
   }
  
   //Verifica o tamanho do arquivo
   private void tamanhoArquivo() throws Exception
   {  
      try
      {
    	  for(labirinto = null; (labirinto = this.reader.readLine()) != null; this.Linhas++ )
    	  {
    		  if(this.Colunas < labirinto.length())
    			  this.Colunas = labirinto.length();
    	  }
      }
      catch(Exception error)
      {
        this.reader.close();
        throw new Exception ("\nArquivo inválido, ocorreu um erro na leitura. Por favor, tente novamente.");
      }
      this.reader.close();
   }

   //Get da quantidade de linhas
   public int getLinhas() throws Exception
   {
      if(this.Linhas == 0)
      {
          throw new Exception ("\nArquivo inválido. Não há conteúdo!"); //se não tem linha, não tem labirinto
      }
      
      else
      {
    	  return this.Linhas;
      }
   }
   
   //Get da quantidade de colunas
   public int getColunas() throws Exception
   {
      if(Colunas == 0)
      {
        throw new Exception ("\nArquivo inválido. Não há conteúdo!"); //se não tem coluna, não tem labirinto
      }
      
      else
      {
    	  return this.Colunas;
      }
   }
  
   //Verifica se o arquivo ta totalmente vazio
   private void isArquivoVazio() throws Exception
   {
     if(reader.readLine() == null)
     {
       this.reader.close();
       throw new Exception("\nArquivo inválido. Não há conteúdo!");
     }
     
     else
     {
    	 this.reader.close();
    	 this.reader = new BufferedReader(new FileReader(arquivo));
    	 tamanhoArquivo();
     }
   }
   
   //Carregamento do arquivo e criação da matriz conforme quantidade de linhas e colunas
   //Validação se as linhas possuem tamanhos iguais
   public Object[][] carregaArquivo() throws Exception {
      
      int linha;
      int coluna;
      Object Matriz[][] = null;
      int qtdAtual = 0;
      int qtdInicio  = 0;
	   
	   try {
	   	   this.reader = new BufferedReader(new FileReader(arquivo));
     	      Matriz = new Object[this.Linhas][this.Colunas];

            for(linha = 0; linha < this.Linhas; linha++) {
               labirinto = this.reader.readLine();
               if(linha > 1) {
                     qtdAtual = 0;
                  }
               for(coluna = 0; coluna < labirinto.length(); coluna++) {
                  if(linha == 1) {
                     qtdInicio++;
                  }
                  if(linha > 1) {
                     qtdAtual++;
                  }
                  Matriz[linha][coluna] = labirinto.charAt(coluna);
               }
               if(linha > 1 && qtdAtual != qtdInicio) {
                  throw new Exception("\nO arquivo possui linhas de tamanhos diferentes, por favor, corrija e tente novamente.");
               }
            }
	      }
	   catch(Exception Error) {
        throw new Exception(Error);
	   }
    	return Matriz;
   }
}