package nupt.vivi;


/*
 * ��������Ԥ������Ҫ�Ƕ�ȡ�ļ������������Ĳ����ļ��Լ�������ɵ�������ļ���
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PreProcedure {
	
	//��ȡmale��female�е������Լ�������ɵ�maleRandom��femaleRandom�е�����
	
	public static ArrayList<Player> readFile(String path, int gender) {
		BufferedReader file = null;
		ArrayList<Player> p = new ArrayList<Player>();
		try {
			file = new BufferedReader(new FileReader(new File(
					path)));
			
			
			Player player = null;
			String line;
			while ((line = file.readLine()) != null) {
				player = new Player(line,gender);
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
}
