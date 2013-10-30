package nupt.vivi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Vivi
 * 此类和Procedure类相似，用于随机产生用户
 * 自定义数目的男生和女生的数据，并且进行配对。
 * 此类可以与Procedure合并，共用一个算法，只是考虑到理解起来的合理性以及
 * 相比预赛代码修改量，故保留此类。
 */
public class ProcedureCustomer {

	private ArrayList<Player> _boys = null;
	private ArrayList<Player> _girls = null;

	private StringBuilder sb;

	private Boolean _isGameOver;
	
	private int _boySize;
	
	private int _girlSize;

	public ProcedureCustomer(int boySize,int girlSize) {
		_boySize = boySize;
		_girlSize = girlSize;
	}

	/**
	 * 随机产生_boySize个男生和_girlSize个女生，并且保证产生的数据符合要求。
	 * 为了保证male和female中的所有数都是大于0的，所以对每个属性都用了while的循环
	 * 为了方便计算，我们把男生的最低期望值都设为0.
	 * @param boySize 男生数量
	 * @param girlSize 女生数量
	 */
	public void randomMaleAndFemale(int boySize,int girlSize) {
		int[][] male = new int[_boySize][10];
		int tmpMale;
		for(int i=0;i<_boySize;i++)
			{
				male[i][0]=i;
				while(true){
					male[i][1]=(int) (Math.random()*100);
				if(male[i][1]>0){   
					male[i][1]=male[i][1];
					break;
				   }
				else 
					continue;
				}
				while(true){
					male[i][2]=(int) (Math.random()*100);
				if(male[i][2]>0){   
					male[i][2]=male[i][2];
					break;
				   }
				else 
					continue;
				}
				while(true){
					male[i][3]=(int) (Math.random()*100);
		        if(male[i][3]>0){   
		        	male[i][3]=male[i][3];
		      	    break;
		         }
		        else 
					continue;
		         }
				while(true){
					male[i][4]=(int) (Math.random()*100);
			        if(male[i][4]>0){   
			        	male[i][4]=male[i][4];
			      	    break;
			         }
			        else 
						continue;
			         }
				while(true){
				
				while(true){
				    male[i][5]=(int) (Math.random()*100);
				    if(male[i][5]>0){  
				    male[i][5]=male[i][5];
				    break;
				    }
				    else 
						continue;
				    }
				while(true){
					male[i][6]=(int) (Math.random()*100);
					if(male[i][6]>0)  {
					male[i][6]=male[i][6];
					break;
					}
					else 
						continue;
					}
				while(true){
					male[i][7]=(int) (Math.random()*100);
					if(male[i][7]>0){  
					male[i][7]=male[i][7];
					break;
					}
					else 
						continue;
					}
				
				tmpMale=100-male[i][5]-male[i][6]-male[i][7];
				if(tmpMale>0){
					male[i][8]=tmpMale;
				    break;
				}
				else 
					continue;
			}
				male[i][9] = 0;
			}
		int[][] female = new int[_girlSize][10];
		int tmpFemale;
		for(int i=0;i<_girlSize;i++)
			{
			
			female[i][0] = i;
			while(true){
				female[i][1]=(int) (Math.random()*100);
			if(female[i][1]>0){   
				female[i][1]=female[i][1];
				break;
			   }
			else 
				continue;
			}
			while(true){
				female[i][2]=(int) (Math.random()*100);
			if(female[i][2]>0){   
				female[i][2]=female[i][2];
				break;
			   }
			else 
				continue;
			}
			while(true){
				female[i][3]=(int) (Math.random()*100);
	        if(female[i][3]>0){   
	        	female[i][3]=female[i][3];
	      	    break;
	         }
	        else 
				continue;
	         }
			while(true){
				female[i][4]=(int) (Math.random()*100);
		        if(female[i][4]>0){   
		        	female[i][4]=female[i][4];
		      	    break;
		         }
		        else 
					continue;
		         }
			while(true){
			
			while(true){
				female[i][5]=(int) (Math.random()*100);
			    if(female[i][5]>0){  
			    	female[i][5]=female[i][5];
			    break;
			    }
			    else 
					continue;
			    }
			while(true){
				female[i][6]=(int) (Math.random()*100);
				if(female[i][6]>0)  {
					female[i][6]=female[i][6];
				break;
				}
				else 
					continue;
				}
			while(true){
				female[i][7]=(int) (Math.random()*100);
				if(female[i][7]>0){  
					female[i][7]=female[i][7];
				break;
				}
				else 
					continue;
				}
			
			tmpFemale=100-female[i][5]-female[i][6]-female[i][7];
			if(tmpFemale>0){
				female[i][8]=tmpFemale;
			    break;
			}
			else 
				continue;
		}
			while(true){
				female[i][9]=(int) (Math.random()*10000);
		        if(female[i][9]>0){   
		        	female[i][9]=female[i][9];
		      	    break;
		         }
		        else 
		        	continue;
		         }
	}
		BufferedWriter maleTxt1;
		BufferedWriter femaleTxt1;
		try {
			maleTxt1 = new BufferedWriter(new FileWriter(new File(".\\attach\\maleRandom.txt")));
			femaleTxt1=new BufferedWriter(new FileWriter(new File(".\\attach\\femaleRandom.txt")));	     
	     for (int i = 0; i<_boySize; i++)
	     {
	    	 try{
	    	 maleTxt1.write(Integer.toString(male[i][0])+","+Integer.toString(male[i][1])
	    			 +","+Integer.toString(male[i][2])+","+Integer.toString(male[i][3])+
	    			 ","+Integer.toString(male[i][4])+","+
	    			 Integer.toString(male[i][5])+","+Integer.toString(male[i][6])
	    			 +","+Integer.toString(male[i][7])+","+
	    			 Integer.toString(male[i][8])+","+Integer.toString(male[i][9])+"\n");
	    	 }catch(NumberFormatException e){	 
	    	 }catch(IOException e){
	    	 }
        
	     }
	     for (int i = 0; i<_girlSize; i++)
	     {
	    	 try{
	    	 femaleTxt1.write(Integer.toString(female[i][0])+","+Integer.toString(female[i][1])
	    			 +","+Integer.toString(female[i][2])+","+Integer.toString(female[i][3])+
	    			 ","+Integer.toString(female[i][4])+","+
	    			 Integer.toString(female[i][5])+","+Integer.toString(female[i][6])+","+
	    			 Integer.toString(female[i][7])+","+Integer.toString(female[i][8])+
	    			 ","+Integer.toString(female[i][9])+"\n");
	    	 }catch(NumberFormatException e){
	    	 }catch(IOException e){
	    	 }
	     }
	     maleTxt1.close();
	     femaleTxt1.close();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
	}
	
	/**
	 * 
	 */
	public void init(){
		_isGameOver = false;
		randomMaleAndFemale(_boySize,_girlSize);
		sb = new StringBuilder();
		sb.append("--------随机生成的男女的配对情况以及匹配值---------：" + "\n");
		readDatas();
	}
	
	/**
	 * 
	 */
	public void next() {
		while(!_isGameOver) {
			boyPick();
			findMostPopularGirlAndPick();
		}
	}
	
	public synchronized String getResult() {
		return sb.toString();
	}
	private void readDatas() {
		_boys = PreProcedure.readFile(".\\attach\\maleRandom.txt", Player.MALE);
		_girls = PreProcedure
				.readFile(".\\attach\\femaleRandom.txt", Player.FEMALE);
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
			if (pickedGirl!=null){
				pickedGirl.addInvitation(boy);
			}
		}
	}

	private void findMostPopularGirlAndPick() {
		@SuppressWarnings("unchecked")
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
		}
		_isGameOver = true;	
}
	
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