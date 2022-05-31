package labirinto;

import java.lang.reflect.*;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.sound.sampled.Line;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

	public static void main(String[] args) throws Exception {
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFileChooser get = new JFileChooser();
			get.setFileFilter(new FileNameExtensionFilter("Arquivo txt", "txt"));
			int res = get.showOpenDialog(null);
			String file = null;
			if (res == JFileChooser.APPROVE_OPTION)
				file = get.getSelectedFile().getPath();
		
			Matriz2 labirinto = new Matriz2();
			labirinto.Valida(file);
			
		}catch(Exception erro) 
		{
			System.out.println(erro.getMessage());
		}
	}
}