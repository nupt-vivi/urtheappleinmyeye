package nupt.vivi;


/*
 * �������ڴ���ó��򣬺����㷨
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Procedure {

	private Player _lead = null;
	private ArrayList<Player> _boys = null;
	private ArrayList<Player> _girls = null;

	private StringBuilder sb;
	
	private StringBuilder sb1;

	private Boolean _isGameOver;

	public Procedure() {
	}
   //��ʼ��
	public void init(Player lead) {
		_lead = lead;
		_isGameOver = false;
		sb = new StringBuilder();
		sb1 = new StringBuilder();
		sb.append("--------�ı�����Ů���������Լ�ƥ��ֵ---------��" + "\n");
		readDatas();
	}
	//��ʼ���ж����ݽ��д���
	public void next() {
		while(!_isGameOver) {
			boyPick();
			findMostPopularGirlAndPick();
		}
	}
	
	public synchronized String getResult() {
		return sb.toString();
	}
	
	public synchronized String getResult1() {
		return sb1.toString();
	}
	

	private void readDatas() {
		_boys = PreProcedure.readFile(".\\attach\\male.txt", Player.MALE);
		_girls = PreProcedure
				.readFile(".\\attach\\female.txt", Player.FEMALE);
		if (_lead.getGender() == Player.MALE) {
			_boys.add(0, _lead);
		} else {
			_girls.add(0, _lead);
		}
		//����Ҫ��Ϊ���ǵļ�����Ƴ�male����female�е�ĳ���ˣ�ֱ����100��101���������
		/*
		while (_boys.size() > 100) {
			_boys.remove(100);
		}
		while (_girls.size() > 100) {
			_girls.remove(100);
		}
		System.out.println(_boys.size());
		System.out.println(_girls.size());
		*/
	}

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
			if (pickedGirl!=null)
				pickedGirl.addInvitation(boy);
		}
		
	}
      
	private void findMostPopularGirlAndPick() {
		Player girl;
		int boyTotal;
		int mostPopular = -1;
		Player mostPopularGirl = null;
		int size=_girls.size();
		for(int i=0;i<size;i++) {
			boyTotal = -1;
			girl = _girls.get(i);
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
		if (mostPopularGirl != null&&mostPopularGirl.getMostFavorPlayer()!=null) {
			if (mostPopularGirl.getId() == -1
					|| mostPopularGirl.getMostFavorPlayer().getId() == -1) {
				sb.append("-------------------------------------\n");
			}
			sb.append("Ů��:"
					+ String.valueOf(mostPopularGirl.toString())
					+ " �з�:"
					+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
							.toString()) + " ƥ���:"
					+ String.valueOf(mostPopularGirl.getMostFavor()) + "\n");
			if (mostPopularGirl.getId() == -1
					|| mostPopularGirl.getMostFavorPlayer().getId() == -1) {
				sb.append("-------------------------------------\n");
			}
			
			
			//��ӵ����Ĳ��Խ��
			if (mostPopularGirl.getId() == -1
					|| mostPopularGirl.getMostFavorPlayer().getId() == -1){
				
				sb1.append("Ů��:"
					+ String.valueOf(mostPopularGirl.toString())
					+ " �з�:"
					+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
							.toString()) + " ƥ���:"
					+ String.valueOf(mostPopularGirl.getMostFavor()) + "\n");
				
			}
			
			
			
			_boys.remove(mostPopularGirl.getMostFavorPlayer());
			_girls.remove(mostPopularGirl);
			resetAllBoysAndGirls();
		} else {
			_isGameOver = true;
		}
	}

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
