package Games;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class login extends JFrame implements ActionListener {
	private JPanel panel1;
	private JLabel lblnum, lblpwd;
	private JTextField txtnum;
	private JPasswordField txtpwd;
	private JButton dlButton, zcButton;
	public String numString = null;
	public String pwdString = null;
	database mydatabase = new database();

	// ÓÎÏ·Ö÷½çÃæ
	public login() {
		super("ÅÅ¿éÓÎÏ·µÇÂ¼´°¿Ú");
		panel1 = new JPanel(null);
		lblnum = new JLabel("Ñ§ ºÅ:");
		lblpwd = new JLabel("ÃÜ  Âë:");
		lblnum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		lblpwd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		txtnum = new JTextField(20);
		txtpwd = new JPasswordField(20);
		txtpwd.setEchoChar('*');
		dlButton = new JButton("µÇ Â¼");
		zcButton = new JButton("×¢ ²á");
		dlButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		zcButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		lblnum.setBounds(80, 40, 60, 25);
		txtnum.setBounds(145, 40, 150, 25);
		lblpwd.setBounds(80, 85, 60, 25);
		txtpwd.setBounds(145, 85, 150, 25);
		zcButton.setBounds(110, 150, 65, 30);
		dlButton.setBounds(210, 150, 65, 30);
		panel1.add(lblnum);
		panel1.add(txtnum);
		panel1.add(lblpwd);
		panel1.add(txtpwd);
		panel1.add(dlButton);
		panel1.add(zcButton);
		this.add(panel1);
		this.setSize(390, 250);
		this.setLocation(780, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		dlButton.addActionListener(this);
		zcButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		mydatabase.Connection();
		String btnlabel = e.getActionCommand();
		String gametime=null;
		// ÓÃ»§Ñ¡ÔñµÇÂ¼
		if (btnlabel.equals("µÇ Â¼")) {
			numString = txtnum.getText();
			pwdString = new String(txtpwd.getPassword());
			if (mydatabase.query(numString, pwdString)) {
				System.out.print("µÇÂ½³É¹¦");
				this.setVisible(false);
				BlockMoveGames_MIDDLE blockMoveGame = new BlockMoveGames_MIDDLE();
//				gametime=blockMoveGame.gametime();
//				if(gametime!=null)
//					System.out.println(gametime);
			}

			// µÇÂ¼Ê§°Ü
			else {
				JOptionPane.showMessageDialog(null, "ÕËºÅ»òÃÜÂëÊäÈë´íÎó!\n" + "ÄúÊäÈëµÄÕËºÅÎª£º" + numString + "\nÄúÊäÈëµÄÃÜÂëÎª£º" + pwdString,
						"ÌáÊ¾", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(numString);
				System.out.println(pwdString);
			}
		}
		// ÓÃ»§Ñ¡Ôñ×¢²á
		if (btnlabel.equals("×¢ ²á"))

		{
			System.out.println("×¢²á´°¿Ú");
			new zhuce();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		login log = new login();
	}

}
