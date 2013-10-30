package nupt.vivi;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Vivi
 *  此类用于处理该程序，核心算法
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
	 * 初始化显示界面的状态。
	 */
	public void init() {
		_isGameOver = false;
		sb = new StringBuilder();
		sb.append("----------文本中男女的配对情况以及匹配值-----------：" + "\n");
		readDatas();
	}
	
	
	/**
	 * 开始进行对数据进行处理
	 */
	public void next() {
		while(!_isGameOver) {
			boyPick();
			findMostPopularGirlAndPick();
		}
	}
	
	/**
	 * @return 结果字符串
	 */
	public synchronized String getResult(){
		return sb.toString();
	}
	
	//读取字符串
	private void readDatas() {
		_boys = PreProcedure.readFile(".\\attach\\male.txt", Player.MALE);
		_girls = PreProcedure
				.readFile(".\\attach\\female.txt", Player.FEMALE);
	}

	/*
	 * 男生选择女生，一旦发现符合配对规则，
	 * 就将女生设为pickedGirl，同时记录下favor值
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
		 * 我们将女生的文件复制一份，等下女生按男的id降序选择男生。
		 * 如果一轮这个女生A没有选中任何男生。那么就从copyGirls里面将
		 * 这个女生A删除，然后就可以选出除了这个女生之外收到邀请最多
		 * 的女生B，让选出的这个女生B来继续选择，以此循环。
		 * 当然，我们只是从copyGirls删除了A，其实A还在读取的文件
		 * _girls中，所以A可以继续参与以后的选择。
		 */
		ArrayList<Player> copyGirls = (ArrayList<Player>)_girls.clone();

		Player mostPopularGirl = null;
		while((mostPopularGirl=findMostPopularGirl(copyGirls))!=null) {
				if (mostPopularGirl != null&&mostPopularGirl.getMostFavorPlayer()!=null) {
					sb.append("女方:"
							+ String.valueOf(mostPopularGirl.toString())
							+ " 男方:"
							+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
									.toString()) + " 匹配度:"
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
	
	
	//找到收到邀请最多的女生。
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
	 * 重置所有男女生的初始状态。
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