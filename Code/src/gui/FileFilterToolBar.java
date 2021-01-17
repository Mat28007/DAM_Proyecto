package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterToolBar extends FileFilter {

	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) {
			return true;
		}
		String nombre= file.getName();
		String extension = UtilFichero.getFileExtension(nombre);
		if(extension==null) {
			return false;
		}if(extension.equals("cli")){
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// extension de los ficheros .cli ( cliente)
		return "files(*.cli)";
	}

}



