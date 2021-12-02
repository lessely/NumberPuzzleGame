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
	private int RC = 3; // 行列数
	private int N = RC * RC;// 块的个数
	// 数组 num用来记录每个按钮上的数字-1
	int[] num = new int[N];
	JButton[] btn = new JButton[N];
	JButton btnstart = new JButton("开始游戏");
	JButton upset = new JButton("重新打乱");
	JButton setting = new JButton("难度设置");
	JButton about = new JButton("关于");
	JButton exit = new JButton("退出");

	public BlockMoveGames_EASY() {
		setTitle("排块游戏(难度:简单)");
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JToolBar toolBar = new JToolBar();
		toolBar.add(upset);
		toolBar.add(setting);
		toolBar.add(about);
		toolBar.add(exit);
		// 程序开始，对数组赋值，并显示按钮文字
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new GridLayout(RC, RC));
		p2.add(btnstart);// 启动按钮

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
		btn[N - 1].setVisible(false);// 数字为N-1的按钮不显示

		upset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnstart_Click();// 核心
				p1.setVisible(true);
				startTime = System.currentTimeMillis();// 开始计时
			}
		});

		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting set=new setting();
			}	
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("关于");
				JPanel jPanel = new JPanel(null);
				JLabel about1 = new JLabel("排块游戏源码");
				JLabel about2 = new JLabel("北京大学唐大仕");
				JLabel about3 = new JLabel("二次编译");
				JLabel about4 = new JLabel("18物联网工程3班刘奕濠");

				about1.setFont(new Font("微软雅黑", Font.BOLD, 15));
				about2.setFont(new Font("微软雅黑", Font.BOLD, 15));
				about3.setFont(new Font("微软雅黑", Font.BOLD, 15));
				about4.setFont(new Font("微软雅黑", Font.BOLD, 15));

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
				btnstart_Click();// 核心
				p1.setVisible(true);
				startTime = System.currentTimeMillis();// 开始计时
			}
		});
		for (int i = 0; i < N; i++) {
			btn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < N; j++)
						if ((JButton) e.getSource() == btn[j])
							btn_Click(j);// 核心
				}
			});
		}
	}

	public void btnstart_Click() {
		// 打乱顺序开始游戏
		int i, j, k, t;
		k = 0;
		// 随机找两个下标，交换其对应的数组元素
		for (i = 1; i < 1000; i++) {// 难度设置
			j = (int) (Math.random() * N);
			k = findBlank();
			if (isNeighbor(j, k) == true) {
				t = num[j];
				num[j] = num[k];
				num[k] = t;
			}
		}
		// 显示它们
		for (i = 0; i < N; i++) {
			btn[i].setText("" + (num[i] + 1));
			btn[i].setVisible(true);
		}
		// 其中有一个按钮需要隐藏
		i = findBlank();
		btn[i].setVisible(false);
		btnstart.setVisible(false);
	}

	// 找到哪一个方位
	int findBlank() {
		int i;
		for (i = 0; i < N; i++) {
			if (num[i] == N - 1)
				break;
		}
		return i;
	}

	// 第Index个按钮事件的处理
	void btn_Click(int index) {
		int blank = findBlank();// 找到隐藏的空白按钮
		if (isNeighbor(blank, index)) {// 如果相邻
			btn[index].setVisible(false);// 一个隐藏，一个显示
			btn[blank].setVisible(true);// 并交换其上的数字
			int t = num[blank];
			num[blank] = num[index];
			num[index] = t;
			btn[blank].setText("" + (num[blank] + 1));
			btn[index].setText("" + (num[index] + 1));
			btn[blank].requestFocus();// 焦点移到原按钮上，以让用户看清
		}
		checkResult();// 调用过程，检测是否完成
	}

	// 判断是否相邻
	boolean isNeighbor(int a, int b) {
		boolean r;
		r = false;
		if (a == b - RC || a == b + RC)
			r = true;// 上下相邻
		if ((a == b - 1 || a == b + 1) && a / RC == b / RC)
			r = true;// 左右相邻，注意要在同一排
		return r;
	}

	// 检查结果是否完全到位
	void checkResult() {
		int i;
		for (i = 0; i < N; i++) {
			if (num[i] != i)
				return;
		}

		endTime = System.currentTimeMillis();
		long totalSeconds = (endTime - startTime) / 1000;
		long currentSeconds = totalSeconds % 60;// 获取当前秒数
		long currentMinutes = totalSeconds / 60 % 60; // 获取当前分钟数
		long currentHours = totalSeconds / 3600 % 24; // 获取当前小时数
		JOptionPane.showMessageDialog(this,"你赢了!请点击[开始]再来一次。" + "\n共用:" + currentHours + "时" + currentMinutes + "分" + currentSeconds + "秒");
		gametime = currentHours + "时" + currentMinutes + "分" + currentSeconds + "秒";
		// System.out.println(gametime);
		btnstart.setVisible(true);

	}


}
