package Games;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class BlockMoveGames_EASY extends JFrame {
	private String timeString;
	private long startTime = 0;
	private long endTime = 0;
	private String gametime = null;
	private int x = 0;
	private int RC = 3; // ������
	private int N = RC * RC;// ��ĸ���
	// ���� num������¼ÿ����ť�ϵ�����-1
	int[] num = new int[N];
	JButton[] btn = new JButton[N];
	JButton btnstart = new JButton("��ʼ��Ϸ");
	JButton upset = new JButton("���´���");
	JButton setting = new JButton("�Ѷ�����");
	JButton about = new JButton("����");
	JButton exit = new JButton("�˳�");

	public BlockMoveGames_EASY() {
		setTitle("�ſ���Ϸ(�Ѷ�:��)");
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JToolBar toolBar = new JToolBar();
		toolBar.add(upset);
		toolBar.add(setting);
		toolBar.add(about);
		toolBar.add(exit);
		// ����ʼ�������鸳ֵ������ʾ��ť����
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new GridLayout(RC, RC));
		p2.add(btnstart);// ������ť

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(p1, BorderLayout.CENTER);
		getContentPane().add(p2, BorderLayout.SOUTH);
		this.setLocation(780, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		p1.setVisible(false);
		Font font = new Font("Times New Rome", 0, 24);
		for (int i = 0; i < N; i++) {
			num[i] = i;
			btn[i] = new JButton("" + (num[i] + 1));
			btn[i].setFont(font);
			p1.add(btn[i]);
			btn[i].setVisible(true);
		}
		btn[N - 1].setVisible(false);// ����ΪN-1�İ�ť����ʾ

		upset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnstart_Click();// ����
				p1.setVisible(true);
				startTime = System.currentTimeMillis();// ��ʼ��ʱ
			}
		});

		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting set=new setting();
			}	
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("����");
				JPanel jPanel = new JPanel(null);
				JLabel about1 = new JLabel("�ſ���ϷԴ��");
				JLabel about2 = new JLabel("������ѧ�ƴ���");
				JLabel about3 = new JLabel("���α���");
				JLabel about4 = new JLabel("18����������3�������");

				about1.setFont(new Font("΢���ź�", Font.BOLD, 15));
				about2.setFont(new Font("΢���ź�", Font.BOLD, 15));
				about3.setFont(new Font("΢���ź�", Font.BOLD, 15));
				about4.setFont(new Font("΢���ź�", Font.BOLD, 15));

				about1.setBounds(65, 30, 150, 20);
				about2.setBounds(57, 60, 150, 20);
				about3.setBounds(77, 100, 150, 20);
				about4.setBounds(27, 130, 250, 20);

				jPanel.add(about1);
				jPanel.add(about2);
				jPanel.add(about3);
				jPanel.add(about4);

				jf.add(jPanel);
				jf.setSize(250, 250);
				jf.setLocation(780, 400);
				// jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setVisible(true);
				jf.setResizable(false);
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnstart_Click();// ����
				p1.setVisible(true);
				startTime = System.currentTimeMillis();// ��ʼ��ʱ
			}
		});
		for (int i = 0; i < N; i++) {
			btn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < N; j++)
						if ((JButton) e.getSource() == btn[j])
							btn_Click(j);// ����
				}
			});
		}
	}

	public void btnstart_Click() {
		// ����˳��ʼ��Ϸ
		int i, j, k, t;
		k = 0;
		// ����������±꣬�������Ӧ������Ԫ��
		for (i = 1; i < 1000; i++) {// �Ѷ�����
			j = (int) (Math.random() * N);
			k = findBlank();
			if (isNeighbor(j, k) == true) {
				t = num[j];
				num[j] = num[k];
				num[k] = t;
			}
		}
		// ��ʾ����
		for (i = 0; i < N; i++) {
			btn[i].setText("" + (num[i] + 1));
			btn[i].setVisible(true);
		}
		// ������һ����ť��Ҫ����
		i = findBlank();
		btn[i].setVisible(false);
		btnstart.setVisible(false);
	}

	// �ҵ���һ����λ
	int findBlank() {
		int i;
		for (i = 0; i < N; i++) {
			if (num[i] == N - 1)
				break;
		}
		return i;
	}

	// ��Index����ť�¼��Ĵ���
	void btn_Click(int index) {
		int blank = findBlank();// �ҵ����صĿհװ�ť
		if (isNeighbor(blank, index)) {// �������
			btn[index].setVisible(false);// һ�����أ�һ����ʾ
			btn[blank].setVisible(true);// ���������ϵ�����
			int t = num[blank];
			num[blank] = num[index];
			num[index] = t;
			btn[blank].setText("" + (num[blank] + 1));
			btn[index].setText("" + (num[index] + 1));
			btn[blank].requestFocus();// �����Ƶ�ԭ��ť�ϣ������û�����
		}
		checkResult();// ���ù��̣�����Ƿ����
	}

	// �ж��Ƿ�����
	boolean isNeighbor(int a, int b) {
		boolean r;
		r = false;
		if (a == b - RC || a == b + RC)
			r = true;// ��������
		if ((a == b - 1 || a == b + 1) && a / RC == b / RC)
			r = true;// �������ڣ�ע��Ҫ��ͬһ��
		return r;
	}

	// ������Ƿ���ȫ��λ
	void checkResult() {
		int i;
		for (i = 0; i < N; i++) {
			if (num[i] != i)
				return;
		}

		endTime = System.currentTimeMillis();
		long totalSeconds = (endTime - startTime) / 1000;
		long currentSeconds = totalSeconds % 60;// ��ȡ��ǰ����
		long currentMinutes = totalSeconds / 60 % 60; // ��ȡ��ǰ������
		long currentHours = totalSeconds / 3600 % 24; // ��ȡ��ǰСʱ��
		JOptionPane.showMessageDialog(this,"��Ӯ��!����[��ʼ]����һ�Ρ�" + "\n����:" + currentHours + "ʱ" + currentMinutes + "��" + currentSeconds + "��");
		gametime = currentHours + "ʱ" + currentMinutes + "��" + currentSeconds + "��";
		// System.out.println(gametime);
		btnstart.setVisible(true);

	}


}
