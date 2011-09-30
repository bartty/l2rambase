/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.nio.ByteBuffer;

import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.clientpackets.L2GameClientPacket;


public interface CustomPacketHandlerInterface
{

    /**
     * interface for a custom packethandler to ckeck received packets
     * PacketHandler will take care of the packet if this function returns null.
     * @param data the packet
     * @param client the ClientThread
     * @return a ClientBasePacket if the packet has been processed, null otherwise
     */
    public L2GameClientPacket handlePacket(ByteBuffer data, L2GameClient client);
}
