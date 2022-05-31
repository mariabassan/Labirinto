package labirinto;

import java.io.*;

public class Matriz2
{
  String labirinto; //conteudo do arquivo txt
  String caminho = null; //caminho do arquivo txt
  int Linhas = 0;
  int Colunas = 0;
  BufferedReader leitor = null;
   
  public Matriz2(String caminho) throws Exception
   {
	   try
	   {
		     if(this.leitor == null)
		     {
		    	 this.leitor = new BufferedReader(new FileReader(caminho));
		     }
	   }
     
     catch(Exception Error)              
     {
		 throw new Exception ("\nCaminho inválido. Tente novamente, por favor.");
     }
     	this.caminho = caminho;
    	 verificaExtensao(caminho);
    	 isArquivoVazio();  
   }

   private void verificaExtensao(String caminho) throws Exception
   {
	   if(!caminho.endsWith(".txt")) //só aceita arquivo txt
	   {
		   throw new Exception ("Formato de arquivo inválido. Por favor, utilize somente arquivos .txt");
	   }
   }
  
   private void tamanhoArquivo() throws Exception
   {  
      try
      {
    	  for(labirinto = null; (labirinto = this.leitor.readLine()) != null; this.Linhas++ )
    	  {
    		  if(this.Colunas < labirinto.length())
    			  this.Colunas = labirinto.length();
    	  }
      }
      catch(Exception error)
      {
        this.leitor.close();
        throw new Exception ("\nArquivo inválido, ocorreu um erro na leitura. Por favor, tente novamente.");
      }
      this.leitor.close();
   }

   public int getLinhas() throws Exception
   {
      if(this.Linhas == 0)
      {
          throw new Exception ("\nArquivo inválido. Não há conteúdo!");
      }
      
      else
      {
    	  return this.Linhas;
      }
   }
   
   public int getColunas() throws Exception
   {
      if(Colunas == 0)
      {
        throw new Exception ("\nArquivo inválido. Não há conteúdo!");
      }
      
      else
      {
    	  return this.Colunas;
      }
   }
  
   private void isArquivoVazio() throws Exception
   {
     if(leitor.readLine() == null)
     {
       this.leitor.close();
       throw new Exception("\nArquivo inválido. Não há conteúdo!");
     }
     
     else
     {
    	 this.leitor.close();
    	 this.leitor = new BufferedReader(new FileReader(caminho));
    	 tamanhoArquivo();
     }
   }
   
   public String getCaminhoArquivo() throws Exception
   {
      if(this.caminho != null) 
      {
    	  return this.caminho;
      }
      else
      {
    	  throw new Exception ("\nPor favor, forneça um caminho para iniciar.");
      }
   }

   public Object[][] carregaArquivo() throws Exception
   {
	  int linha;
	  int coluna;
    Object Matriz[][] = null;
    int qtdColunasLinhaAtual = 0;
    int qtdColunhasLinhaUm  = 0;
	   
	   try{
	   	   this.leitor = new BufferedReader(new FileReader(caminho));
     	      Matriz = new Object[this.Linhas][this.Colunas];

            for(linha = 0; linha < this.Linhas; linha++)
            {
               labirinto = this.leitor.readLine();
               if(linha > 1){
                     qtdColunasLinhaAtual = 0;
                  }
               for(coluna = 0; coluna < labirinto.length(); coluna++)
               {
                  if(linha == 1){
                     qtdColunhasLinhaUm++;
                  }
                  if(linha > 1){
                     qtdColunasLinhaAtual++;
                  }
                  Matriz[linha][coluna] = labirinto.charAt(coluna);
               }
               if(linha > 1 && qtdColunasLinhaAtual != qtdColunhasLinhaUm){
                  throw new Exception("\nO arquivo possui linhas de tamanhos diferentes, por favor, corrija e tente novamente.");
               }
            }
	      }
	   catch(Exception Error)
	   {
        throw new Exception(Error);
    	   //this.leitor.close();
	   }
    	return Matriz;
   }
}