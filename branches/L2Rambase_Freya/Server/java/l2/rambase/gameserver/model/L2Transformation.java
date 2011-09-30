/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public abstract class L2Transformation implements Cloneable, Runnable
{
	private final int _id;
	private final int _graphicalId;
	private double _collisionRadius;
	private double _collisionHeight;
	private final boolean _isStance;

	public static final int TRANSFORM_ZARICHE = 301;
	public static final int TRANSFORM_AKAMANAH = 302;
	
	protected static final int[] EMPTY_ARRAY = {};

	private L2PcInstance _player;

	/**
	 * 
	 * @param id Internal id that server will use to associate this transformation 
	 * @param graphicalId Client visible transformation id
	 * @param collisionRadius Collision Radius of the player while transformed
	 * @param collisionHeight  Collision Height of the player while transformed
	 */
	public L2Transformation(int id, int graphicalId, double collisionRadius, double collisionHeight)
	{
		_id = id;
		_graphicalId = graphicalId;
		_collisionRadius = collisionRadius;
		_collisionHeight = collisionHeight;
		_isStance = false;
	}

	/**
	 * 
	 * @param id Internal id(will be used also as client graphical id) that server will use to associate this transformation 
	 * @param collisionRadius Collision Radius of the player while transformed
	 * @param collisionHeight  Collision Height of the player while transformed
	 */
	public L2Transformation(int id, double collisionRadius, double collisionHeight)
	{
		this(id, id, collisionRadius, collisionHeight);
	}

	/**
	 * 
	 * @param id Internal id(will be used also as client graphical id) that server will use to associate this transformation
	 * Used for stances
	 */
	public L2Transformation(int id)
	{
		_id = id;
		_graphicalId = id;
		_isStance = true;
	}

	/**
	 * @return Returns the id.
	 */
	public int getId()
	{
		return _id;
	}

	/**
	 * @return Returns the graphicalId.
	 */
	public int getGraphicalId()
	{
		return _graphicalId;
	}

	/**
	 * Return true if this is a stance (vanguard/inquisitor)
	 * @return
	 */
	public boolean isStance()
	{
		return _isStance;
	}

	/**
	 * @return Returns the collisionRadius.
	 */
	public double getCollisionRadius()
	{
		if (isStance())
			return _player.getCollisionRadius();
		return _collisionRadius;
	}

	/**
	 * @return Returns the collisionHeight.
	 */
	public double getCollisionHeight()
	{
		if (isStance())
			return _player.getCollisionHeight();
		return _collisionHeight;
	}

	// Scriptable Events
	public abstract void onTransform();

	public abstract void onUntransform();

	/**
	 * @param player The player to set.
	 */
	private void setPlayer(L2PcInstance player)
	{
		_player = player;
	}

	/**
	 * @return Returns the player.
	 */
	public L2PcInstance getPlayer()
	{
		return _player;
	}

	public void start()
	{
		this.resume();
	}

	public void resume()
	{
		this.getPlayer().transform(this);
	}

	public void run()
	{
		this.stop();
	}

	public void stop()
	{
		this.getPlayer().untransform();
	}

	public L2Transformation createTransformationForPlayer(L2PcInstance player)
	{
		try
		{
			L2Transformation transformation = (L2Transformation) this.clone();
			transformation.setPlayer(player);
			return transformation;
		}
		catch (CloneNotSupportedException e)
		{
			// should never happen
			return null;
		}
	}

	// Override if necessary
	public void onLevelUp()
	{

	}

	/**
	 * Returns true if transformation can do melee attack
	 */
	public boolean canDoMeleeAttack()
	{
		return true;
	}

	/**
	 * Returns true if transformation can start follow target when trying to cast an skill out of range
	 */
	public boolean canStartFollowToCast()
	{
		return true;
	}
}
