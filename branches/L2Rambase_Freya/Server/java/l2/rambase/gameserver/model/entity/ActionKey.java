/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.entity;


public class ActionKey
{
	int _cat;
	int _cmd;
	int _key;
	int _tgKey1;
	int _tgKey2;
	int _show;
	
	/**
	 * L2ActionKey Initialization
	 * @param cat: Category ID
	 * @param cmd: Command ID
	 * @param key: User Defined Primary Key
	 * @param tgKey1: 1st Toogled Key (eg. Alt, Ctrl or Shift)
	 * @param tgKey2: 2nd Toogled Key (eg. Alt, Ctrl or Shift)
	 * @param show: Show Action in UI
	 */
	public ActionKey(int cat, int cmd, int key, int tgKey1, int tgKey2, int show)
	{
		_cat = cat;
		_cmd = cmd;
		_key = key;
		_tgKey1 = tgKey1;
		_tgKey2 = tgKey2;
		_show = show;
	}
	
	public int getCategory()
	{
		return _cat;
	}
	
	public int getCommandId()
	{
		return _cmd;
	}
	
	public int getKeyId()
	{
		return _key;
	}
	
	public int getToogleKey1()
	{
		return _tgKey1;
	}
	
	public int getToogleKey2()
	{
		return _tgKey2;
	}
	
	public int getShowStatus()
	{
		return _show;
	}
	
	public String getSqlSaveString(int playerId, int order)
	{
		return "("+playerId+", "+_cat+", "+order+", "+_cmd+","
		+_key+", "+_tgKey1+", "+_tgKey2+", "+_show+")";
	}
}