/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import gnu.trove.TIntObjectHashMap;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Transformation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class TransformationManager
{
	private static final Logger _log = Logger.getLogger(TransformationManager.class.getName());
	
	public static TransformationManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private TIntObjectHashMap<L2Transformation> _transformations;
	
	private TransformationManager()
	{
		_transformations = new TIntObjectHashMap<L2Transformation>();
	}
	
	public void report()
	{
		_log.info("Loaded: " + _transformations.size() + " transformations.");
	}
	
	public boolean transformPlayer(int id, L2PcInstance player)
	{
		L2Transformation template = this.getTransformationById(id);
		if (template != null)
		{
			L2Transformation trans = template.createTransformationForPlayer(player);
			trans.start();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public L2Transformation getTransformationById(int id)
	{
		return _transformations.get(id);
	}
	
	public L2Transformation registerTransformation(L2Transformation transformation)
	{
		return _transformations.put(transformation.getId(), transformation);
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final TransformationManager _instance = new TransformationManager();
	}
}
