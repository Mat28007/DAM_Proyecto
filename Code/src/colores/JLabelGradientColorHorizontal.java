package colores;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class JLabelGradientColorHorizontal extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private Color color1 = Color.WHITE;
	private Color color2 = ColoresBarras.VERY_LIGHT_Blue;
	
	public JLabelGradientColorHorizontal() {
        setText("");
        setHorizontalAlignment(CENTER);
        setForeground(Color.WHITE);
        setOpaque(true);
    }
	 @Override
	protected void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g.create();
		 int w = getWidth();
	     int h = getHeight();
	     GradientPaint gp = new GradientPaint(50, 290, color1, 0, h, color2);
	     g2d.setPaint(gp);
	     g2d.fillRect(0, 0, w, h);
	     g2d.dispose();
	     getUI().paint(g, this);
	    }
	    }