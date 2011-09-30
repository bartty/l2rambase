/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.itemauction;


public enum ItemAuctionState
{
	CREATED((byte)0),
	STARTED((byte)1),
	FINISHED((byte)2);
	
	private final byte _stateId;
	
	private ItemAuctionState(final byte stateId)
	{
		_stateId = stateId;
	}
	
	public byte getStateId()
	{
		return _stateId;
	}
	
	public static final ItemAuctionState stateForStateId(final byte stateId)
	{
		for (final ItemAuctionState state : ItemAuctionState.values())
		{
			if (state.getStateId() == stateId)
				return state;
		}
		return null;
	}
}