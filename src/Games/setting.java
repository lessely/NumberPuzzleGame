package Games;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
public class setting implements ItemListener{
		public int a=2;
		public int b=2;

		JRadioButton easyBox=new JRadioButton("简 单");
		JRadioButton middleBox=new JRadioButton("一 般",true);
		JRadioButton difficultBox=new JRadioButton("大 师");
		ButtonGroup bg=new ButtonGroup();
	public setting(){
		JFrame f=new JFrame("难度设置");
		JPanel p=new JPanel();
		JPanel p2=new JPanel();

		easyBox.addItemListener(this);
		middleBox.addItemListener(this);
		difficultBox.addItemListener(this);

		JButton qrButton=new JButton("确 认");
		JButton fhButton=new JButton("返 回");
		bg.add(easyBox);
		bg.add(middleBox);
		bg.add(difficultBox);
		p.add(easyBox);
		p.add(middleBox);
		p.add(difficultBox);
		p2.add(qrButton);
		p2.add(fhButton);
		f.setLayout(new BorderLayout());
		f.add(p,BorderLayout.CENTER);
		f.add(p2,BorderLayout.SOUTH);
		f.setSize(250, 150);
		f.setLocation(780,400);
		f.setVisible(true);
		f.setResizable(false);
		
		fhButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				//System.out.println(b);
			}
		});
		
		qrButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				b=a;
				f.dispose();
				//System.out.println(b);
				if(b==1) {
					BlockMoveGames_EASY easy=new BlockMoveGames_EASY();
			}
				if(b==2) {
					BlockMoveGames_MIDDLE middle=new BlockMoveGames_MIDDLE();
			}
				if(b==3) {
					BlockMoveGames_DIFFICULT difficult=new BlockMoveGames_DIFFICULT();
			}
			}
		});
		}
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource()==easyBox&&easyBox.isSelected()) {
				System.out.println("用户选择简单");
				a=1;
			}
			if (e.getSource()==middleBox&&middleBox.isSelected()) {
				System.out.println("用户选择一般");
				a=2;
			}
			if (e.getSource()==difficultBox&&difficultBox.isSelected()) {
				System.out.println("用户选择大师");
				a=3;
			}
		}
		
}
		

