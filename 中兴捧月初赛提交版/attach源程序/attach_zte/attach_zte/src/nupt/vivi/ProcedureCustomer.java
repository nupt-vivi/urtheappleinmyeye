package nupt.vivi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProcedureCustomer {

	private Player _lead1 = null;
	private ArrayList<Player> _boys1 = null;
	private ArrayList<Player> _girls1 = null;

	private StringBuilder sb1;
	
	private StringBuilder sb2;

	private Boolean _isGameOver1;

	public ProcedureCustomer() {

	}
	
	
	//随机产生100个男生和100个女生，并且保证产生的数据符合要求。
	public void randomMaleAndFemale() throws IOException{
		int[][] male1 = new int[100][7];
		int tmpmale;
		for(int i=0;i<100;i++)
			{
				male1[i][0]=i;
				male1[i][1]=(int) (Math.random()*100);
				male1[i][2]=(int) (Math.random()*100);
				male1[i][3]=(int) (Math.random()*100);
				
				
				while(true){
				male1[i][4]=(int) (Math.random()*100);
				male1[i][5]=(int) (Math.random()*100);
				tmpmale=100-male1[i][4]-male1[i][5];
				if(tmpmale>0){
					male1[i][6]=tmpmale;
				    break;
				}
				}
			}
		int[][] female1 = new int[100][7];
		int tmpfemale;
		for(int i=0;i<100;i++)
			{
				female1[i][0]=i;
				female1[i][1]=(int) (Math.random()*100);
				female1[i][2]=(int) (Math.random()*100);
				female1[i][3]=(int) (Math.random()*100);
				
				
				while(true){
				female1[i][4]=(int) (Math.random()*100);
				female1[i][5]=(int) (Math.random()*100);
				tmpfemale=100-female1[i][4]-female1[i][5];
				if(tmpfemale>0){
					male1[i][6]=tmpfemale;
				    break;
				}
				}
			}
		BufferedWriter maleTxt1=new BufferedWriter(new FileWriter(new File(".\\attach\\male1.txt")));
		BufferedWriter femaleTxt1=new BufferedWriter(new FileWriter(new File(".\\attach\\female1.txt")));	     
	     for (int i = 0; i<100; i++)
	     {
	    	 try{
	    	 maleTxt1.write(Integer.toString(male1[i][0])+","+Integer.toString(male1[i][1])
	    			 +","+Integer.toString(male1[i][2])+","+Integer.toString(male1[i][3])+
	    			 ","+Integer.toString(male1[i][4])+","+
	    			 Integer.toString(male1[i][5])+","+Integer.toString(male1[i][6])+"\n");
	    	 }catch(NumberFormatException e){
	    		 
	    	 }
        
	     }
	     for (int i = 0; i<100; i++)
	     {
	    	 try{
	    	 femaleTxt1.write(Integer.toString(female1[i][0])+","+Integer.toString(female1[i][1])
	    			 +","+Integer.toString(female1[i][2])+","+Integer.toString(female1[i][3])+
	    			 ","+Integer.toString(female1[i][4])+","+
	    			 Integer.toString(female1[i][5])+","+Integer.toString(female1[i][6])+"\n");
	    	 }catch(NumberFormatException e){
	    		 
	    	 }
        
	     }
	     maleTxt1.close();
	     femaleTxt1.close();
		
		
	}
	
	public void init(Player lead) throws IOException {
		_lead1 = lead;
		_isGameOver1 = false;
		randomMaleAndFemale();
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
		// sb.append(String.valueOf(Math.random()) + "--\n");
		sb1.append("--------随机生成的男女的配对情况以及匹配值---------：" + "\n");
		readDatas();
	}
	
	public void next() {
		while(!_isGameOver1) {
			boyPick();
			findMostPopularGirlAndPick();
		}
	}
	
	public synchronized String getResult() {
		return sb1.toString();
	}
	
	public synchronized String getResult1() {
		return sb2.toString();
	}

	private void readDatas() {
		_boys1 = PreProcedure.readFile(".\\attach\\male1.txt", Player.MALE);
		_girls1 = PreProcedure
				.readFile(".\\attach\\female1.txt", Player.FEMALE);
		if (_lead1.getGender() == Player.MALE) {
			_boys1.add(0, _lead1);
		} else {
			_girls1.add(0, _lead1);
		}
//		while (_boys.size() > 100) {
//			_boys.remove(100);
//		}
//		while (_girls.size() > 100) {
//			_girls.remove(100);
//		}
//		System.out.println(_boys.size());
//		System.out.println(_girls.size());
	}

	private void boyPick() {
		Player boy, girl;
		int mostFavor;
		int favor;
		Player pickedGirl;
		int boySize=_boys1.size();
		int girlSize=_girls1.size();
		for (int i=0;i<boySize;i++) {
			mostFavor = -1;
			favor = -1;
			pickedGirl = null;
			boy=_boys1.get(i);
			for (int j=0;j<girlSize;j++) {
				girl = _girls1.get(j);
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
		int size=_girls1.size();
		for(int i=0;i<size;i++) {
			boyTotal = -1;
			girl = _girls1.get(i);
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
				sb1.append("-------------------------------------\n");
			}
			sb1.append("女方:"
					+ String.valueOf(mostPopularGirl.toString())
					+ " 男方:"
					+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
							.toString()) + " 匹配度:"
					+ String.valueOf(mostPopularGirl.getMostFavor()) + "\n");
			if (mostPopularGirl.getId() == -1
					|| mostPopularGirl.getMostFavorPlayer().getId() == -1) {
				sb1.append("-------------------------------------\n");
			}
			
			//用于显示单个输入的配对结果
			if (mostPopularGirl.getId() == -1
					|| mostPopularGirl.getMostFavorPlayer().getId() == -1){
				sb2.append("女方:"
						+ String.valueOf(mostPopularGirl.toString())
						+ " 男方:"
						+ String.valueOf(mostPopularGirl.getMostFavorPlayer()
								.toString()) + " 匹配度:"
						+ String.valueOf(mostPopularGirl.getMostFavor()) + "\n");
				
				
			}
			
			_boys1.remove(mostPopularGirl.getMostFavorPlayer());
			_girls1.remove(mostPopularGirl);
			resetAllBoysAndGirls();
		} else {
			_isGameOver1 = true;
		}
	}

	private void resetAllBoysAndGirls() {
		Iterator<Player> girlIt = _girls1.iterator();
		Player girl;
		while (girlIt.hasNext()) {
			girl = girlIt.next();
			girl.clearStat();
		}

		Iterator<Player> boyIt = _boys1.iterator();
		Player boy;
		while (boyIt.hasNext()) {
			boy = boyIt.next();
			boy.clearStat();
		}
	}
}
