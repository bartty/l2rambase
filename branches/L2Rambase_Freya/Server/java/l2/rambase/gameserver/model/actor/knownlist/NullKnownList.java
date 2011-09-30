/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;


public class NullKnownList extends ObjectKnownList
{
	public NullKnownList(L2Object activeObject)
	{
		super(activeObject);
	}

	@Override
	public boolean addKnownObject(L2Object object)
	{
		return false;
	}

	@Override
	public L2Object getActiveObject()
	{
		return super.getActiveObject();
	}

	@Override
	public int getDistanceToForgetObject(L2Object object)
	{
		return 0;
	}

	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 0;
	}

	@Override
	public void removeAllKnownObjects()
	{ }

	@Override
	protected boolean removeKnownObject(L2Object object, boolean forget)
	{
		return false;
	}
}