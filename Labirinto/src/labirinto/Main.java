package labirinto;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main{
  
  public static void main(String[] args) throws Exception{

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    JFileChooser get = new JFileChooser();
    get.setFileFilter(new FileNameExtensionFilter("Arquivo txt", "txt"));
    int res = get.showOpenDialog(null);
    String file = null;
    if (res == JFileChooser.APPROVE_OPTION)
				file = get.getSelectedFile().getPath();
    
    Matriz arquivo = null;
    Percorrer labirinto = null;
    
    arquivo = new Matriz(file);

    try{

      labirinto = new Percorrer(arquivo.carregaArquivo(),arquivo.getLinhas(),arquivo.getColunas());

      String resCoordenadas = labirinto.resolucaoDeLabirinto();
      
    	System.out.println("\n" + resCoordenadas);
    }
    catch(Exception Error){
      System.out.println("\n Encontramos um erro no labirinto: " +Error);
    }
	}
}