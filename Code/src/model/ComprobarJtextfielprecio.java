package model;


import gui.GestionStock.JPanelRegistrarArticulo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

public class ComprobarJtextfielprecio implements KeyListener {
	private	JFormattedTextField jt;
	private JPanelRegistrarArticulo jPanelRegistrarArticulo;
	
	
	// Con esto se maneja el objeto de forma global en esta clase.
	public ComprobarJtextfielprecio(JPanelRegistrarArticulo jPanelRegistrarArticulo,JFormattedTextField jt) {
		this.jPanelRegistrarArticulo = jPanelRegistrarArticulo; 
		this.jt=jt;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		jPanelRegistrarArticulo.comprobarPrecios(e,jt);}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}


