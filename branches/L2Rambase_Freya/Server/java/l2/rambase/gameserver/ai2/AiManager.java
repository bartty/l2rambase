/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import gnu.trove.TIntObjectHashMap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai2.AiInstance.QueueEventRunner;

import javolution.util.FastList;
import javolution.util.FastMap;
import javolution.util.FastSet;


public class AiManager
{
	protected static final Logger _log = Logger.getLogger(AiManager.class.getName());
	private List<AiInstance> _aiList;
	private TIntObjectHashMap<AiInstance> _aiMap;
	private ThreadPoolManager _tpm;
	private Map<String, String> _paramcache;
	
	public static AiManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private AiManager()
	{
		_aiList = new FastList<AiInstance>();
		_aiMap = new TIntObjectHashMap<AiInstance>();
		_tpm = ThreadPoolManager.getInstance();
		_paramcache = new FastMap<String, String>();
		load();
	}
	
	public void load()
	{
		try
		{
			@SuppressWarnings("unused")
			JarFile jar = new JarFile("./l2jserver.jar");
			URL url = Class.class.getResource("/com/l2jserver/gameserver/ai/managers");
			if (url == null)
			{
				_log.severe("Could not open the ai managers folder. No ai will be loaded!");
				return;
			}
			File directory = new File(url.getFile());
			for (String file : directory.list())
			{
				if (file.endsWith(".class"))
				{
					try
					{
						Class<?> managerClass = Class.forName("l2.rambase.gameserver.ai.managers." + file.substring(0, file.length() - 6));
						Object managerObject = managerClass.newInstance();
						if (!(managerObject instanceof ISpecificAiManager))
						{
							_log.info("A class that was not a ISpecificAiManager was found in the ai managers folder.");
							continue;
						}
						ISpecificAiManager managerInstance = (ISpecificAiManager) managerObject;
						for (EventHandler handler : managerInstance.getEventHandlers())
						{
							AiPlugingParameters pparams = handler.getPlugingParameters();
							pparams.convertToIDs();
							boolean perfectMatch = false;
							// let's check if any previously created AiInstance is allready used for the NPC concerned by this handler
							List<Intersection> intersections = new FastList<Intersection>();
							for (AiInstance ai : _aiList)
							{
								if (ai.getPluginingParamaters().equals(pparams))
								{
									ai.addHandler(handler);
									perfectMatch = true;
									break;
								}
								Intersection intersection = new Intersection(ai);
								for (int id : pparams.getIDs())
								{
									if (ai.getPluginingParamaters().contains(id))
									{
										//intersection with this AI
										intersection.ids.add(id);
									}
								}
								if (!intersection.isEmpty())
									intersections.add(intersection);
							}
							if (perfectMatch)
								continue;
							for (Intersection i : intersections)
							{
								//remove secant ids on both AiInstances
								pparams.removeIDs(i.ids);
								i.ai.getPluginingParamaters().removeIDs(i.ids);
								//create a new instance with the secant ids that will inherit all the handlers from the secant Ai
								AiPlugingParameters newAiPparams = new AiPlugingParameters(null, null, null, i.ids, null);
								AiInstance newAi = new AiInstance(i.ai, newAiPparams);
								newAi.addHandler(handler);
								_aiList.add(newAi);
							}
							if (pparams.isEmpty())
								continue;
							// create a new instance with the remaining ids
							AiInstance newAi = new AiInstance(pparams);
							newAi.addHandler(handler);
							_aiList.add(newAi);
						}
						
					}
					catch (Exception e)
					{
						_log.log(Level.WARNING, "Error while loading AiManager: " + e.getMessage(), e);
					}
				}
			}
		}
		catch (IOException e1)
		{
			_log.log(Level.WARNING, e1.getMessage(), e1);
		}
		// build a mighty map
		for (AiInstance ai : _aiList)
		{
			for (Integer i : ai.getHandledNPCIds())
			{
				_aiMap.put(i, ai);
			}
		}
	}
	
	public void executeEventHandler(QueueEventRunner runner)
	{
		_tpm.executeAi(runner);
	}
	
	/**
	 * @param instance
	 */
	public void addAiInstance(AiInstance instance)
	{
		_aiList.add(instance);
	}
	
	/**
	 * @param npcId
	 * @return
	 */
	public AiInstance getAiForNPCId(int npcId)
	{
		return _aiMap.get(npcId);
	}
	
	public String getParameter(String who, String paramsType, String param1, String param2)
	{
		String key = who + ":" + paramsType + ":" + param1 + ":" + param2;
		String cacheResult = _paramcache.get(key);
		if (cacheResult != null)
			return cacheResult;
		String result = null;
		// get from SQL
		_paramcache.put(key, result);
		return null;
	}
	
	private class Intersection
	{
		public AiInstance ai;
		public Set<Integer> ids;
		
		public Intersection(AiInstance instance)
		{
			ai = instance;
			ids = new FastSet<Integer>();
		}
		
		public boolean isEmpty()
		{
			return ids.isEmpty();
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final AiManager _instance = new AiManager();
	}
}
