package nupt.vivi;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("数字配对系统");
		frame.setSize(1100, 700);
		frame.setAlwaysOnTop(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// final Label title=new
		// Label("期望样貌,期望品格,期望财富合起来必须是100，性别必须为0或者1，所有字段必须为正整数!");
		// title.setLocation(0, 0);
		// title.setSize(100, 30);
		// title.setEnabled(true);
		// frame.add(title);
		//
		// final TextField gender=new TextField("1");
		// gender.setLocation(100, 0);
		// gender.setSize(150, 30);
		// gender.setEditable(true);
		// frame.add(gender);
		//
		// final TextField wealthOwn=new TextField("35");
		// wealthOwn.setLocation(250, 0);
		// wealthOwn.setSize(150, 30);
		// wealthOwn.setEditable(true);
		// frame.add(wealthOwn);
		//
		// final TextField appearenceOwn=new TextField("32");
		// appearenceOwn.setLocation(400, 0);
		// appearenceOwn.setSize(150, 30);
		// appearenceOwn.setEditable(true);
		// frame.add(appearenceOwn);
		//
		// final TextField characterOwn=new TextField("38");
		// characterOwn.setLocation(550, 0);
		// characterOwn.setSize(150, 30);
		// characterOwn.setEditable(true);
		// frame.add(characterOwn);
		//
		// final TextField wealthPref=new TextField("28");
		// wealthPref.setLocation(250, 60);
		// wealthPref.setSize(150, 30);
		// wealthPref.setEditable(true);
		// frame.add(wealthPref);
		//
		// final TextField appearencePref=new TextField("38");
		// appearencePref.setLocation(400, 60);
		// appearencePref.setSize(150, 30);
		// appearencePref.setEditable(true);
		// frame.add(appearencePref);
		//
		// final TextField characterPref=new TextField("34");
		// characterPref.setLocation(550, 60);
		// characterPref.setSize(150, 30);
		// characterPref.setEditable(true);
		// frame.add(characterPref);

		final JLabel jl = new JLabel("CopyRight@vivi-nupt");
		jl.setBounds(850, 30, 120, 20);
		jl.setVisible(true);
		frame.add(jl);

		final TextArea ta = new TextArea();
		ta.setLocation(30, 150);
		ta.setSize(500, 400);
		ta.setText("");
		frame.add(ta);

		final JLabel jl1 = new JLabel("测试配对的结果:");
		jl1.setBounds(30, 565, 120, 20);
		jl1.setVisible(true);
		frame.add(jl1);
		
		final JLabel jl2 = new JLabel("真实配对的结果:");
		jl2.setBounds(600, 565, 120, 20);
		jl2.setVisible(true);
		frame.add(jl2);
		
		final JTextArea ta4 = new JTextArea();
		ta4.setBounds(30, 595, 500, 30);
		ta4.setVisible(true);
		frame.add(ta4);
		
		final JTextArea ta3 = new JTextArea();
		ta3.setBounds(600, 595, 500, 30);
		ta3.setVisible(true);
		frame.add(ta3);
		

		// 用于显示用户随机生成的配对情况
		 final TextArea ta1=new TextArea();
		 ta1.setLocation(550,150);
		 ta1.setSize(500, 400);
		 //ta1.setText("--------随机生成的男女配对情况以及匹配值---------:");
		 frame.add(ta1);

		final JLabel jlInput = new JLabel("用户请输入:");
		jlInput.setBounds(570, 80, 120, 20);
		jlInput.setVisible(true);
		frame.add(jlInput);

		final JTextField ta2 = new JTextField("1,70,80,90,20,40,40");
		ta2.setBounds(570, 100, 220, 30);
		frame.add(ta2);

		 JButton jb =new JButton();
		 jb.setText("开始配对");
		 jb.setBounds(880,100,100,30);
		 frame.add(jb);

		Vector<String> src = null;
		try {
			src = PreProcedure.readFileToVector(".\\attach\\players.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel jlceshi = new JLabel("测试专用数据:");
		jlceshi.setBounds(50, 5, 100, 20);
		frame.add(jlceshi);

		
		final JComboBox jcb = new JComboBox(src);
		jcb.setLocation(50, 30);
		jcb.setSize(400, 20);
		frame.add(jcb);
		
		final Procedure p = new Procedure();
		final ProcedureCustomer p1 = new ProcedureCustomer();

		
		  jb.addActionListener(new ActionListener(){
		  
		  @Override public void actionPerformed(ActionEvent e) {
		  
		  String input = (String)(ta2.getText()); String[] items =
		  input.split(",");
		  
		  
		  Player lead1 = new Player(null);
		  lead1.setGender(Integer.parseInt(items[0]));
		  lead1.setWealthOwn(Integer.parseInt(items[1]));
		  lead1.setAppearenceOwn(Integer.parseInt(items[2]));
		  lead1.setCharacterOwn(Integer.parseInt(items[3])); lead1.setId(-1);
		  lead1.setWealthPerf(Integer.parseInt(items[4]));
		  lead1.setAppearencePref(Integer.parseInt(items[5]));
		  lead1.setCharacterPref(Integer.parseInt(items[6]));
		  try {
			p1.init(lead1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		  p1.next();
		  ta1.setText(p1.getResult());
		  ta3.setText(p1.getResult1());
		 }
		  
   });

		Action action = new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String select = (String) (jcb.getSelectedItem());

					String[] items = select.split(",");

					Player lead = new Player(null);
					lead.setGender(Integer.parseInt(items[0]));
					lead.setWealthOwn(Integer.parseInt(items[1]));
					lead.setAppearenceOwn(Integer.parseInt(items[2]));
					lead.setCharacterOwn(Integer.parseInt(items[3]));
					lead.setId(-1);
					lead.setWealthPerf(Integer.parseInt(items[4]));
					lead.setAppearencePref(Integer.parseInt(items[5]));
					lead.setCharacterPref(Integer.parseInt(items[6]));

					p.init(lead);
					p.next();
					//String pro=null;
					//pro=lead.getMostFavorPlayer().getCharacterPref()+",";
					ta4.setText(p.getResult1());
					ta.setText(p.getResult());

				} catch (NumberFormatException exc) {
					return;
				}
			}

			@Override
			public void setEnabled(boolean b) {
			}

			@Override
			public void removePropertyChangeListener(
					PropertyChangeListener listener) {
			}

			@Override
			public void putValue(String key, Object value) {
			}

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public Object getValue(String key) {
				return null;
			}

			@Override
			public void addPropertyChangeListener(
					PropertyChangeListener listener) {
			}
		};

		jcb.setAction(action);
		action.actionPerformed(null);

	}

}
