package nupt.vivi;


/*
 * ���ඨ������ҵĸ������ԣ��Լ��໥��Եķ���
 */

import java.util.ArrayList;

public class Player {

	private static String SPLIT = ",";


	private int _gender;

	private int _id;

	private int _wealthOwn;
	private int _appearenceOwn;
	private int _characterOwn;
	private int _healthOwn;

	private int _wealthPerf;
	private int _appearencePref;
	private int _characterPref;
	private int _healthPref;
	
	private int _minFavorValue;
	
	private int _avgFavorValue;

	private ArrayList<Player> _invitation;
	private int _mostFavorValue = -1;
	private Player _mostFavorPlayer = null;

	public Player(String src,int gender) {
		if (src == null)
			return;
		   String[] parse = src.split(SPLIT);
		   _id = Integer.parseInt(parse[0]);
		   _wealthOwn = Integer.parseInt(parse[1]);
		   _appearenceOwn = Integer.parseInt(parse[2]);
		   _characterOwn = Integer.parseInt(parse[3]);
		   _healthOwn = Integer.parseInt(parse[4]);
		   _wealthPerf = Integer.parseInt(parse[5]);
		   _appearencePref = Integer.parseInt(parse[6]);
		   _characterPref = Integer.parseInt(parse[7]);
		   _healthPref = Integer.parseInt(parse[8]);
		   _minFavorValue = Integer.parseInt(parse[9]);
		   _avgFavorValue = Integer.parseInt(parse[10]);
		   
	   }
	
	
      /*
       * @see java.lang.Object#toString()
       * �˷�����дtoString()�������û���ʾ�����Խ���ĸ�ʽ��
       */
	@Override
	public String toString() {
		return formatOuput(_wealthOwn, 3) + "," + formatOuput(_appearenceOwn,3) + "," + formatOuput(_characterOwn,3) + ","
				+ formatOuput(_healthOwn,3) + "," + formatOuput(_wealthPerf,3) + "," + formatOuput(_appearencePref,3) + ","
				+ formatOuput(_characterPref,3) + "," + formatOuput(_healthPref,3) + "," + formatOuput(_minFavorValue,4)
				+ "," + ":ID-->" + formatOuput(_id,4) + ",";
	}

	public int getGender() {
		return _gender;
	}

	public void setGender(int value) {
		if (value != 0)
			value = 1;
		_gender = value;
	}

	public int getId() {
		return _id;
	}

	public void setId(int value) {
		_id = value;
	}

	public int getWealthOwn() {
		return _wealthOwn;
	}

	public void setWealthOwn(int value) {
		_wealthOwn = value;
	}

	public int getAppearenceOwn() {
		return _appearenceOwn;
	}

	public void setAppearenceOwn(int value) {
		_appearenceOwn = value;
	}

	public int getCharacterOwn() {
		return _characterOwn;
	}

	public void setCharacterOwn(int value) {
		_characterOwn = value;
	}
	public int getHealthOwn() {
		return _healthOwn;
	}

	public void setHealthOwn(int value) {
		_healthOwn = value;
	}

	public int getWealthPerf() {
		return _wealthPerf;
	}

	public void setWealthPerf(int value) {
		_wealthPerf = value;
	}

	public int getAppearencePref() {
		return _appearencePref;
	}

	public void setAppearencePref(int value) {
		_appearencePref = value;
	}

	public int getCharacterPref() {
		return _characterPref;
	}

	public void setCharacterPref(int value) {
		_characterPref = value;
	}
	
	public int getHealthPref() {
		return _healthPref;
	}

	public void setHealthPref(int value) {
		_healthPref = value;
	}
	
	public int getMinFavorValue() {
		return _minFavorValue;
	}

	public void setMinFavorValue(int value) {
		_minFavorValue = value;
	}
	
	public int getAvgFavorValue() {
		return _avgFavorValue;
	}

	public void setAvgFavorValue(int value) {
		_avgFavorValue = value;
	}

	public int getOwnPropTotal() {
		return _wealthOwn + _appearenceOwn + _characterOwn+_healthOwn;
	}

	
	  //��Ů����ԵĻ������򣬰������Եĳ˻���С��
	public int getFavorWithOther(Player p) {
		int favor = 0;
		favor += _wealthPerf * p.getWealthOwn();
		favor += _appearencePref * p.getAppearenceOwn();
		favor += _characterPref * p.getCharacterOwn();
		favor += _healthPref * p.getHealthOwn();
		return favor;
	}

	/*
	 * һ��Ů��������ѡΪ������󣬴�Ů��������Ӵ�������Ϊ
	 * ����Ů��ѡ�����ĺ�ѡ��
	 */
	public void addInvitation(Player player){
		if (_invitation == null) {
			clearStat();
			_invitation=new ArrayList<Player>();
		}
		_invitation.add(player);
	}
	
	
	
	public ArrayList<Player>  getInvitation(){
		return _invitation;
	}
	
	public int getRequestTotal() {
		if (_invitation != null) {
			return _invitation.size();
		}
		return 0;
	}
	/*
	 * ��ѡ�е�������ѡ�����������������ֵ1.5�����ˣ�������ѡΪƥ����ˡ�
	 * �����Ԥ�����㷨һ����
	 */
	public Player getMostFavorPlayer() {
		Player player;
		for(int i=getRequestTotal()-1;i>=0;i--)
		{
	        	player = _invitation.get(i);
		        int favor = getFavorWithOther(player);
		        if(favor >= (1.5*_avgFavorValue))
		        {
			    _mostFavorPlayer = player;
				_mostFavorValue = favor;
                        break;
		       	}
		      //�����������ֵ��ƽ������ֵ��1.5����ʱ��
		        else if((favor <(1.5*_avgFavorValue))&&(favor >= _minFavorValue))
		        {
                            if (favor > _mostFavorValue)
                            {
					_mostFavorValue = favor;
					_mostFavorPlayer = player;
                            } 
                            else if (favor == _mostFavorValue) 
                            {
					           if (player.getOwnPropTotal() > _mostFavorPlayer.getOwnPropTotal()) 
					           {
						         _mostFavorPlayer = player;
						         _mostFavorValue = favor;
				            	}
					           else if (player.getOwnPropTotal() == _mostFavorPlayer.getOwnPropTotal()) 
					           {
						          if (player.getId() < _mostFavorPlayer.getId()) 
						          {
						         	_mostFavorPlayer = player;
						          	_mostFavorValue = favor;
						           }
				                }
			      	        }
			       }
		  }
		  return _mostFavorPlayer;
	}

	public int getMostFavor() {
		return _mostFavorValue;
	}

	//��ʼ��״̬��
	public void clearStat() {
		_invitation = null;
		_mostFavorValue = -1;
		_mostFavorPlayer = null;
	}
	public static String formatOuput(int input, int digit) {
		if (digit <= 0)
			return String.valueOf(input);
		int inputDigit = 1;
		int remain = input;
		while ((remain = remain / 10)!=0) {
			inputDigit++;
		}
		if (digit <= inputDigit) {
			return String.valueOf(input);
		}
		String prefix = EMPTY;
		int dis=digit-inputDigit;
		for (int i = 0; i < dis; i++) {
			prefix+=SPACE;
		}
		return String.valueOf(prefix+input);
	}
	private static final String EMPTY = "";
	private static final String SPACE = "_";
	public static final int FEMALE = 0;
	public static final int MALE = 1;
}
