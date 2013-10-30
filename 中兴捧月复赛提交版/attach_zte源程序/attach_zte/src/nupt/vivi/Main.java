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
 * @author Vivi
 * System entrance
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		JFrame appFrame = new JFrame("�������ϵͳ");
		appFrame.setSize(800, 600);
		appFrame.setAlwaysOnTop(false);
		appFrame.setVisible(true);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setLayout(null);

		final JLabel copyRightLabel = new JLabel("CopyRight@vivi-NUPT");
		copyRightLabel.setBounds(620, 15, 150, 20);
		copyRightLabel.setVisible(true);
		copyRightLabel.setVisible(true);
		appFrame.add(copyRightLabel);

		final TextArea ta = new TextArea();
		ta.setLocation(5, 80);
		ta.setSize(750, 200);
		ta.setText("");
		ta.setVisible(true);
		appFrame.add(ta);

		// ������ʾ�û�������ɵ�������
		final TextArea outputArea = new TextArea();
		outputArea.setLocation(5, 325);
		outputArea.setSize(750, 200);
		outputArea.setVisible(true);
		appFrame.add(outputArea);

		JLabel userInputBoySize = new JLabel("����������������:");
		userInputBoySize.setBounds(5, 295, 150, 20);
		userInputBoySize.setVisible(true);
		appFrame.add(userInputBoySize);

		final JTextField taBoySize = new JTextField();
		taBoySize.setText("256");
		taBoySize.setBounds(160, 290, 100, 30);
		taBoySize.setVisible(true);
		appFrame.add(taBoySize);

		JLabel userInputGirlSize = new JLabel("������Ů��������:");
		userInputGirlSize.setBounds(280, 295, 150, 20);
		userInputGirlSize.setVisible(true);
		appFrame.add(userInputGirlSize);

		final JTextField taGirlSize = new JTextField();
		taGirlSize.setText("255");
		taGirlSize.setBounds(430, 290, 100, 30);
		taGirlSize.setVisible(true);
		appFrame.add(taGirlSize);

		JButton srcMatchButton = new JButton();
		srcMatchButton.setText("��ʼ���");
		srcMatchButton.setBounds(655, 45, 100, 30);
		srcMatchButton.setVisible(true);
		appFrame.add(srcMatchButton);

		JButton customMatchButton = new JButton();
		customMatchButton.setText("��ʼ���");
		customMatchButton.setBounds(655, 290, 100, 30);
		customMatchButton.setVisible(true);
		appFrame.add(customMatchButton);

		JLabel jlceshi = new JLabel("��      ��      ר      ��      ��      ��:");
		jlceshi.setBounds(5, 45, 400, 20);
		jlceshi.setVisible(true);
		appFrame.add(jlceshi);

		// ��ȡ���ı��е����ݵ���������ʾ��
		srcMatchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure p = new Procedure();
				p.init();
				p.next();
				ta.setText(p.getResult());
			}
		});

		// �û�����������ݲ����ļ�����������ʾ��
		customMatchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProcedureCustomer p1 = new ProcedureCustomer(Integer
						.parseInt(taBoySize.getText()), Integer
						.parseInt(taGirlSize.getText()));
				p1.init();
				p1.next();
				outputArea.setText(p1.getResult());
			}
		});

	}
}
