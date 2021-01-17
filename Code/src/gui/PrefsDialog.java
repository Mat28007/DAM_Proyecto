package gui;

import gui.Listener.PrefsListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	// 9999 est le max du spinner et 1 le minimum 3306 valeur par defaut,
	private SpinnerNumberModel spinnerModel = new SpinnerNumberModel(3306, 0,
			9999, 1);
	private JSpinner portspinner = new JSpinner(spinnerModel);
	private JTextField userField = new JTextField(10);
	private JPasswordField passField = new JPasswordField(10);
	private PrefsListener prefListener;

	// constructor preferencias
	public PrefsDialog(JFrame parent) {
		super(parent, "Preferencias!", false);
		setSize(340, 250);
		setLocationRelativeTo(parent);
		// pour mettre des * dans password
		passField.setEchoChar('*');

		// methode organiser grid preferences
		OrganisationPreferencias();

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// recuper valeur du spinner control
				Integer port = (Integer) portspinner.getValue();
				// pour recuperer text de usuario
				String user = userField.getText();
				char[] password = passField.getPassword();
				System.out.println(user + ";  " + new String(password));

				if (prefListener != null) {
					prefListener.preferencesSet(user, new String(password),
							port);
				}

				setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	private void OrganisationPreferencias() {
		// mise en page
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space,
				space, space);
		Border titleBorder = BorderFactory
				.createTitledBorder("Database Preferences");

		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,
				titleBorder));
		// controlsPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		// buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		Insets rightPad = new Insets(0, 0, 0, 15);
		Insets noPad = new Insets(0, 0, 0, 0);
		// /premiere ligne////
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPad;
		controlsPanel.add(new JLabel("User: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPad;
		controlsPanel.add(userField, gc);

		// /ligne suivante//
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPad;
		controlsPanel.add(new JLabel("Password: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPad;
		controlsPanel.add(passField, gc);

		// /ligne suivante//
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPad;
		controlsPanel.add(new JLabel("Port: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPad;
		controlsPanel.add(portspinner, gc);

		// ///Ligne des bouttons///
		gc.gridy++;
		gc.gridx = 0;
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		// met les deux boutons de la meme taille
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);

		// / ajoute un sub panel à dialogue
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

	}

	// pour garder l´info des usuario et password de preferencias:
	public void setDefaults(String user, String password, Integer port) {
		userField.setText(user);
		passField.setText(password);
		portspinner.setValue(port);
	}

	// pour garder l´info des usuario et password de preferencias:
	public void setPrefsListener(PrefsListener prefsListener) {
		this.prefListener = prefsListener;

	}

}
