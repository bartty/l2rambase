/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.util.Date;
import java.util.Map;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

class PacketHistory
{
    protected Map<Class<?>, Long> _info;
    protected long _timeStamp;

    protected static final XMLFormat<PacketHistory> PACKET_HISTORY_XML = new XMLFormat<PacketHistory>(PacketHistory.class)
    {
		@Override
		public void read(InputElement xml, PacketHistory packetHistory) throws XMLStreamException
		{
			packetHistory._timeStamp = xml.getAttribute("time-stamp", 0);
			packetHistory._info = xml.<Map<Class<?>, Long>> get("info");
		}

		@Override
		public void write(PacketHistory packetHistory, OutputElement xml) throws XMLStreamException
		{
			xml.setAttribute("time-stamp", new Date(packetHistory._timeStamp).toString());

			for (Class<?> cls : packetHistory._info.keySet())
				xml.setAttribute(cls.getSimpleName(), packetHistory._info.get(cls));
		}

//		public void format(PacketHistory packetHistory, XmlElement xml)
//        {
//            xml.setAttribute("time-stamp", new Date(packetHistory.timeStamp).toString());
//
//            for (Class cls : packetHistory.info.keySet())
//            {
//                xml.setAttribute(cls.getSimpleName(), packetHistory.info.get(cls));
//            }
//        }
//
//        public PacketHistory parse(XmlElement xml)
//        {
//            PacketHistory packetHistory = new PacketHistory();
//            packetHistory.timeStamp     = xml.getAttribute("time-stamp", (long) 0);
//            packetHistory.info          = xml.<Map<Class, Long>> get("info");
//            return packetHistory;
//        }
//
//        public String defaultName()
//        {
//            return "packet-history";
//        }
    };
}