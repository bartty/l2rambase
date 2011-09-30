/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.VehiclePathPoint;


public class ExAirShipTeleportList extends L2GameServerPacket
{
	private static final String _S__FE_9A_EXAIRSHIPTELEPORTLIST = "[S] FE:9A ExAirShipTeleportList";

	private int _dockId;
	private VehiclePathPoint[][] _teleports;
	private int[] _fuelConsumption;

	public ExAirShipTeleportList(int dockId, VehiclePathPoint[][] teleports, int[] fuelConsumption)
	{
		_dockId = dockId;
		_teleports = teleports;
		_fuelConsumption = fuelConsumption;
	}

	@Override
	protected void writeImpl()
	{
		writeC(254);
		writeH(154);

		writeD(_dockId);
		if (_teleports != null)
		{
			writeD(_teleports.length);

			VehiclePathPoint[] path;
			VehiclePathPoint dst;
			for (int i = 0; i < _teleports.length; i++)
			{
				writeD(i - 1);
				writeD(_fuelConsumption[i]);
				path = _teleports[i];
				dst = path[path.length - 1];
				writeD(dst.x);
				writeD(dst.y);
				writeD(dst.z);
			}
		}
		else
			writeD(0);
	}

	@Override
	public String getType()
	{
		return _S__FE_9A_EXAIRSHIPTELEPORTLIST;
	}
}