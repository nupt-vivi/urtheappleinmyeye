package nupt.vivi;


/*
 * 此类定义了玩家的各种属性，以及相互配对的方法
 */

import java.util.ArrayList;

public class Player {

	private static String SPLIT = ",";

	private int _gender;

	private int _id;

	private int _wealthOwn;
	private int _appearenceOwn;
	private int _characterOwn;

	private int _wealthPerf;
	private int _appearencePref;
	private int _characterPref;

	private ArrayList<Player> _invitation;
	private int _mostFavorValue = -1;
	private Player _mostFavorPlayer = null;

	public Player(String src) {
		if (src == null)
			return;
		String[] parse = src.split(SPLIT);
		_id = Integer.parseInt(parse[0]);
		_wealthOwn = Integer.parseInt(parse[1]);
		_appearenceOwn = Integer.parseInt(parse[2]);
		_characterOwn = Integer.parseInt(parse[3]);
		_wealthPerf = Integer.parseInt(parse[4]);
		_appearencePref = Integer.parseInt(parse[5]);
		_characterPref = Integer.parseInt(parse[6]);
	}

	@Override
	public String toString() {
		return _wealthOwn + "," + _appearenceOwn + "," + _characterOwn + ","
				+ _wealthPerf + "," + _appearencePref + "," + _characterPref
				+ ":ID-->" + _id+",";
				//+getRequestTotal();
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

	public int getOwnPropTotal() {
		return _wealthOwn + _appearenceOwn + _characterOwn;
	}

	
	  //玩家配对的方法
	public int getFavorWithOther(Player p) {
		int favor = 0;
		favor += _wealthPerf * p.getWealthOwn();
		favor += _appearencePref * p.getAppearenceOwn();
		favor += _characterPref * p.getCharacterOwn();
		return favor;
	}

	// 收到邀请最多的女生选择男生
	public void addInvitation(Player player) {
		if (_invitation == null) {
			clearStat();
			_invitation=new ArrayList<Player>();
		}
		_invitation.add(player);
		int favor = getFavorWithOther(player);
		if (favor > _mostFavorValue) {
			_mostFavorValue = favor;
			_mostFavorPlayer = player;
		} else if (favor == _mostFavorValue) {
			if (player.getOwnPropTotal() > _mostFavorPlayer.getOwnPropTotal()) {
				_mostFavorPlayer = player;
				_mostFavorValue = favor;
			} else if (player.getOwnPropTotal() == _mostFavorPlayer
					.getOwnPropTotal()) {
				if (player.getId() < _mostFavorPlayer.getId()) {
					_mostFavorPlayer = player;
					_mostFavorValue = favor;
				}
			}
		}
	}

	public int getRequestTotal() {
		if (_invitation != null) {
			return _invitation.size();
		}
		return 0;
	}

	public Player getMostFavorPlayer() {
		return _mostFavorPlayer;
	}

	public int getMostFavor() {
		return _mostFavorValue;
	}

	public void clearStat() {
		_invitation = null;
		_mostFavorValue = -1;
		_mostFavorPlayer = null;
	}

	public static final int FEMALE = 0;
	public static final int MALE = 1;
}
