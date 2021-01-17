package gui.Proveedor;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import colores.ControlBorderImage;

public class JPanelStartProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel jp=new JPanel();
	private URL url = getClass().getResource("/Images/fondoProveedor.jpg");
	
	public JPanelStartProveedor(){
		setLayout(new BorderLayout());		
		try {
			ControlBorderImage borde = new ControlBorderImage(ImageIO.read(url) );            
		     jp.setBorder(borde);            
		} catch (IOException e) {
		      System.out.println(e.getMessage());
		}
		add(jp,BorderLayout.CENTER);
	}
}
