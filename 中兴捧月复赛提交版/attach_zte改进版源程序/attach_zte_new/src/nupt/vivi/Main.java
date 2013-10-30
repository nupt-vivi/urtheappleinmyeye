package nupt.vivi;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * @author vivi-NUPT
 *此类主要是为了生成客户端界面，以及为界面上的事件添加监听器。
 */
public class Main {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("数字配对系统");
		frame.setSize(800, 600);
		frame.setAlwaysOnTop(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		final JLabel jl = new JLabel("CopyRight@vivi-NUPT");
		jl.setBounds(580, 20, 150, 20);
		jl.setVisible(true);
		jl.setVisible(true);
		frame.add(jl);

		
		// 用于显示用户随机生成的配对情况
		 final TextArea ta1=new TextArea();
		 ta1.setLocation(5,100);
		 ta1.setSize(750, 400);
		 ta1.setVisible(true);
		 frame.add(ta1);
		
		JLabel userInputBoySize = new JLabel("男生的数量:");
		userInputBoySize.setBounds(5, 55, 100, 20);
		userInputBoySize.setVisible(true);
		frame.add(userInputBoySize);

		final JTextField taBoySize = new JTextField();
		taBoySize.setText("256");
		taBoySize.setBounds(110, 50, 100, 30);
		taBoySize.setVisible(true);
		frame.add(taBoySize);
		
		JLabel userInputGirlSize = new JLabel("女生的数量:");
		userInputGirlSize.setBounds(240, 55, 100, 20);
		userInputGirlSize.setVisible(true);
		frame.add(userInputGirlSize);
		
		final JTextField taGirlSize = new JTextField();
		taGirlSize.setText("255");
		taGirlSize.setBounds(340, 50, 100, 30);
		taGirlSize.setVisible(true);
		frame.add(taGirlSize);
		
		 JButton jb =new JButton();
		 jb.setText("开始配对");
		 jb.setBounds(655,50,100,30);
		 jb.setVisible(true);
		 frame.add(jb);

       //用户随机输入数据产生文件的配对情况显示。
		 jb.addActionListener(new ActionListener(){
			  
			  @Override 
			    public void actionPerformed(ActionEvent e) {
			
				 
				   ProcedureCustomer p =new ProcedureCustomer(
				   
			 Integer.parseInt(taBoySize.getText()),  Integer.parseInt(taGirlSize.getText()));
	          p.init();
			  p.next();
			  ta1.setText(p.getResult());
			 }
	   });
	  
		
	} 
}
