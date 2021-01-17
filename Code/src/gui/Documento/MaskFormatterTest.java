package gui.Documento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class MaskFormatterTest  {
	
	private static final DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
	private static final	JFormattedTextField tf = new JFormattedTextField(df);
	
public MaskFormatterTest(){
	
	 tf.setColumns(20);
	try {
        MaskFormatter dateMask = new MaskFormatter("####/##/##");
        dateMask.install(tf);
    } catch (ParseException ex) {
        Logger.getLogger(MaskFormatterTest.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}

