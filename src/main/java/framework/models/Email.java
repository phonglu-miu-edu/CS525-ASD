package framework.models;

import javax.swing.*;

public class Email implements INotification<String> {
	String emailAddress;

	public Email(String email) {
		this.emailAddress = email;
	}

	public void sendNotification(String message) {
		JFrame frame = new JFrame("JoptionPane Test");
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);

		JOptionPane.showMessageDialog(frame,
				"Notification sent to " + emailAddress + ".\n\nMessage body: '" + message + "'", "Notification",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
