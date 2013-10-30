package nupt.vivi;


/*
 * ��������Ԥ������Ҫ�Ƕ�ȡ�ļ������������Ĳ����ļ��Լ�������ɵ�������ļ���
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class PreProcedure {
	private static String EMPTY = "";
	
	//��ȡmale��female�е������Լ�������ɵ�male1��female1�е�����
	
	public static ArrayList<Player> readFile(String path, int gender) {
		BufferedReader file = null;
		ArrayList<Player> p = new ArrayList<Player>();
		try {
			file = new BufferedReader(new FileReader(new File(
					path)));

			
			Player player = null;
			String line;
			while ((line = file.readLine()) != null) {
				player = new Player(line);
				player.setGender(gender);
				p.add(player);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file!=null){
				try {
					file.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

	//��ȡplayers�ı��е��ļ�
	
	public static Vector<String> readFileToVector(String path)
			throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(new File(path)));
		String line = EMPTY;

		Vector<String> p = new Vector<String>();
		while ((line = file.readLine()) != null) {
			p.add(line);
		}
		file.close();
		return p;
	}
}
