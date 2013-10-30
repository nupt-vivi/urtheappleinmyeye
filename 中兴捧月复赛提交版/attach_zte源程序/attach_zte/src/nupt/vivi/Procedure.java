package nupt.vivi;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Vivi
 *  �������ڴ���ó��򣬺����㷨
 */
/**
 * @author Administrator
 *
 */
public class Procedure {

	private ArrayList<Player> _boys = null;
	private ArrayList<Player> _girls = null;

	private StringBuilder sb;

	private Boolean _isGameOver;

	public Procedure() {}
	
	/**
	 * ��ʼ����ʾ�����״̬��
	 */
	public void init() {
		_isGameOver = false;
		sb = new StringBuilder();
		sb.append("----------�ı�����Ů���������Լ�ƥ��ֵ-----------��" + "\n");
		readDatas();
	}
	
	
	/**
	 * ��ʼ���ж����ݽ��д���
	 */
	public void next() {
		while(!_isGameOver) {
			boyPick();
			findMostPopularGirlAndPick();
		}
	}
	
	/**
	 * @return ����ַ���
	 */
	public synchronized String getResult(){
		return sb.toString();
	}
	
	//��ȡ�ַ���
	private void readDatas() {
		_boys = PreProcedure.readFile(".\\attach\\male.txt", Player.MALE);
		_girls = PreProcedure
				.readFile(".\\attach\\female.txt", Player.FEMALE);
	}

	/*
	 * ����ѡ��Ů����һ�����ַ�����Թ���
	 * �ͽ�Ů����ΪpickedGirl��ͬʱ��¼��favorֵ
	 */
	private void boyPick() {
		Player boy, girl;
		int mostFavor;
		int favor;
		Player pickedGirl;
		int boySize=_boys.size();
		int girlSize=_girls.size();
		for (int i=0;i<boySize;i++) {
			mostFavor = -1;
			favor = -1;
			pickedGirl = null;
			boy=_boys.get(i);
			for (int j=0;j<girlSize;j++) {
				girl = _girls.get(j);
				favor = boy.getFavorWithOther(girl);
				if (favor > mostFavor) {
					pickedGirl = girl;
					mostFavor = favor;
				} else if (favor == mostFavor) {
					if (girl.getOwnPropTotal() > pickedGirl.getOwnPropTotal()) {
						pickedGirl = girl;
						mostFavor = favor;
					} else if (girl.getOwnPropTotal() == pickedGirl
							.getOwnPropTotal()) {
						if (girl.getId()<pickedGirl.getId()) {
							pickedGirl = girl;
							mostFavor = favor;
						}
					}
				}
			}
			if (pickedGirl!=null){
				pickedGirl.addInvitation(boy);
	            
			}
		}
	}

	private void findMostPopularGirlAndPick() {
		@SuppressWarnings("unchecked")
		/*
		 * ���ǽ�Ů�����ļ�����һ�ݣ�����Ů�����е�id����ѡ��������
		 * ���һ�����Ů��Aû��ѡ���κ���������ô�ʹ�copyGirls���潫
		 * ���Ů��Aɾ����Ȼ��Ϳ���ѡ���������Ů��֮���յ��������
		 * ��Ů��B����ѡ�������Ů��B������ѡ���Դ�ѭ����
		 * ��Ȼ������ֻ�Ǵ�copyGirlsɾ����A����ʵA���ڶ�ȡ���ļ�
		 * _girls�У�����A���Լ��������Ժ��ѡ��
		 */
		ArrayList<Player> copyGirls = (ArrayList<Player>)_girls.clone();

		Player mostPopularGirl = null;
		while((mostPopularGirl=findMostPopularGirl(copyGirls))!=null) {
				if (mostPopularGirl != null&&mostPopularGirl.getMostFavorPlayer()!=null) {
					sb.append("Ů��:"
							+ String.valueOf(mostPopularGirl.toString())
							+ " �з�:"
							+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
									.toString()) + " ƥ���:"
							+ String.valueOf(mostPopularGirl.getMostFavor()) + "\n");	
					
					_boys.remove(mostPopularGirl.getMostFavorPlayer());
					_girls.remove(mostPopularGirl);
					resetAllBoysAndGirls();
			return;
			} else {
			copyGirls.remove(mostPopularGirl);
			}
			
				/*
				if(mostPopularGirl.getMostFavorPlayer()==null){
					copyGirls.remove(mostPopularGirl);
				}
				*/
		}
		_isGameOver = true;	
}
	
	
	//�ҵ��յ���������Ů����
	private Player findMostPopularGirl(ArrayList<Player> copyGirls) {
		Player girl;
		int boyTotal;
		int mostPopular = -1;
		int size=copyGirls.size();
		Player mostPopularGirl = null;
		for(int i=0;i<size;i++) {
			boyTotal = -1;
			girl = copyGirls.get(i);
			boyTotal = girl.getRequestTotal();
			if (boyTotal > mostPopular) {
				mostPopularGirl = girl;
				mostPopular = boyTotal;
			} else if (boyTotal == mostPopular) {
				if (girl.getOwnPropTotal() > mostPopularGirl.getOwnPropTotal()) {
					mostPopularGirl = girl;
					mostPopular = boyTotal;
				} else if (girl.getOwnPropTotal() == mostPopularGirl
						.getOwnPropTotal()) {
					if (girl.getId() < mostPopularGirl.getId()) {
						mostPopularGirl = girl;
						mostPopular = boyTotal;
					}
				}
			}
		}
		return mostPopularGirl;
	}
		
	/*	
	 * ����������Ů���ĳ�ʼ״̬��
	 */
	private void resetAllBoysAndGirls() {
		Iterator<Player> girlIt = _girls.iterator();
		Player girl;
		while (girlIt.hasNext()) {
			girl = girlIt.next();
			girl.clearStat();
		}

		Iterator<Player> boyIt = _boys.iterator();
		Player boy;
		while (boyIt.hasNext()) {
			boy = boyIt.next();
			boy.clearStat();
		}
	}
}