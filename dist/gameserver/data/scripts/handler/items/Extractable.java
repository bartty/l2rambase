package handler.items;

import org.apache.commons.lang3.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

import lineage2.commons.util.Rnd;
import lineage2.gameserver.cache.Msg;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.items.ItemInstance;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.network.serverpackets.components.SystemMsg;
import lineage2.gameserver.scripts.Functions;

public class Extractable extends SimpleItemHandler
{
		private static final int[] ITEM_IDS = new int[] { 14841, 14847, 
			10408, 10473, 9599, 20069, 20070, 20071, 20072, 20073, 20074, 20210, 20211, 20215, 20216,
			20217, 20218, 20219, 20220, 20227, 20228, 20229, 20233, 20234, 20235, 20239, 20240, 20241, 20242, 20243,
			20244, 20251, 20254, 20278, 20279, 20041, 20042, 20043, 20044, 20035, 20036, 20037, 20038, 20039, 20040,
			20060, 20061, 22000, 22001, 22002, 22003, 22004, 22005, 20326, 20327, 20329, 20330, 20059, 20494, 20493,
			20395, 21000, 13281, 13282, 13283, 13284, 13285, 13286, 13287, 13288, 13289, 13290, 14267, 14268, 14269,
			13280, 13713, 13714, 13715, 13716, 13717, 13718, 13719, 13720, 13721, 14549, 14550, 14551,
			14552, 14553, 14554, 14555, 14556, 14557, 13695, 13696, 13697, 13698, 13699, 13700, 13701, 13702, 13703,
			14531, 14532, 14533, 14534, 14535, 14536, 14537, 14538, 14539, 13704, 13705, 13706, 13707, 13708, 13709,
			13710, 13711, 13712, 14540, 14541, 14542, 14543, 14544, 14545, 14546, 14547, 14548, 14884, 14885, 14886,
			14887, 14888, 14889, 14890, 14891, 14892, 14893, 14894, 14895, 14896, 14897, 14898, 14899, 14900, 14901,
			14616, 20575, 20804, 20807, 20805, 20808, 20806, 20809, 20842, 20843, 20844, 20845, 20846, 20847, 20848,
			20849, 20850, 20851, 20852, 20853, 20854, 20855, 20856, 20857, 20858, 20859, 20860, 20861, 20862, 20863,
			20864, 20811, 20812, 20813, 20814, 20815, 20816, 20817, 20810, 20865, 20748, 20749, 20750, 20751, 20752,
			13777, 13778, 13779, 13780, 13781, 13782, 13783, 13784, 13785, 13786, 14849,
			14834, 14833, 13988, 13989, 13003, 13004, 13005, 13006, 13007, 13990, 13991, 13992, 14850, 14713, 14714,
			14715, 14716, 14717, 14718, 17138, 15482, 15483, 13270, 13271, 13272, 14231, 14232,
			17169, 21169, 21092, 21091, 10254, 10206, 10205, 5909, 5910, 5912, 5913, 5907, 5906,
			170, 163, 141, 140, 137, 138, 139, 136, 53, 54, 55, 56, 13079,
			13080, 13082, 13083, 13084, 13085, 13086, 13087, 13088, 13089, 13090, 13091, 13092, 13093, 13094, 13095,
			13096, 13097, 13098, 22187, 21601, 21602, 21603, 21604, 21605, 21606, 21607, 21608, 21609, 21610, 21611,
			21612, 21613, 21614, 21615, 21616, 21617, 21618, 21619, 15343, 15345, 15370, 15371, 15372, 15373, 15374,
			15375, 15376, 15377, 15378, 20101, 20102, 20179, 20180, 20181, 20182, 20183, 20184, 20185, 20186, 20187,
			20188, 20189, 15194, 15195, 15196, 15197, 15198, 15199, 10255, 10256, 10257, 10258, 10259, 13103, 13104,
			13105, 13106, 13107, 13108, 13109, 13110, 13111, 13112, 13113, 13114, 13115, 13116, 13117, 13118, 13119,
			13120, 13121, 13122, 13124, 13125, 13126, 13225, 13226, 13227, 13228, 13229, 13230, 13231, 13232, 13233,
			13256, 13257, 13275, 13276, 13279, 13291, 13292, 21620, 21621, 21622, 21623, 21624, 21625, 21626, 21627,
			21628, 21629, 21630, 21631, 21632, 21633, 21634, 21635, 21636, 21637, 21638, 21639, 21640, 21641, 21642,
			21643, 21644, 21645, 21646, 21647, 21648, 21649, 21650, 21651, 21652, 21653, 21654, 21655, 21656, 21657,
			21658, 21659, 21660, 21661, 21662, 21663, 21664, 21665, 21666, 21667, 21668, 21669, 21670, 21671, 21672,
			21673, 21674, 21675, 21676, 21677, 21678, 21679, 21680, 21681, 21682, 21683, 21684, 21685, 21686, 21001,
			21002, 21003, 21004, 21005, 21006, 21007, 21008, 21024, 21025, 21026, 21027, 21028, 21029, 21063, 21064,
			21065, 21066, 21067, 21068, 21069, 21070, 21071, 21072, 21073, 21074, 21075, 21076, 21077, 21078, 21079,
			21080, 21081, 21082, 21083, 21085, 21087, 21089, 21095, 20314, 20315, 20316, 20317, 20318, 20319, 20328,
			20331, 20397, 20398, 16383, 16384, 16385, 16386, 16387, 16388, 16389, 16390, 16391, 16392, 16393, 16394,
			16395, 16396, 16397, 16398, 16852, 16968, 16969, 16970, 16971, 16972, 16973, 16974, 16975, 16976, 16977,
			16978, 16979, 16980, 16981, 16982, 16983, 16984, 16985, 16986, 16987, 16988, 16989, 16990, 16991, 16992,
			16993, 16994, 16995, 16996, 16997, 16998, 16999, 13341, 13342, 13343, 13344, 13345, 13346, 13347, 13348,
			13349, 13350, 13351, 13352, 13353, 13354, 13355, 13356, 13357, 13358, 13359, 13360, 13361, 13362, 13363,
			13364, 13365, 13366, 13367, 13368, 13369, 13370, 13371, 13372, 13373, 13374, 13375, 13376, 13377, 13378,
			13379, 13380, 13381, 13384, 13385, 13422, 13423, 13424, 13425, 13426, 13427, 13428, 13799, 14530, 20700,
			20701, 20702, 20703, 20704, 20705, 20734, 20735, 20736, 20737, 20738, 20740, 20753, 20754, 20773,
			20796, 20797, 20798, 20799, 15200, 15201, 15202, 15203, 15204, 15205, 15206, 15207, 15209, 15212, 15213,
			15214, 15215, 15216, 15217, 15218, 15219, 15222, 15223, 15224, 15225, 15226, 15227, 15228, 15229, 15230,
			15231, 15232, 15233, 15234, 15235, 15236, 15237, 15238, 15239, 15240, 15241, 15242, 15243, 15244, 15245,
			15246, 15247, 15248, 15249, 15250, 15251, 15252, 15253, 15254, 15255, 15256, 15257, 15258, 15259, 15260,
			15261, 15262, 15263, 15264, 15265, 15266, 15267, 15268, 15269, 15270, 15271, 15272, 15273, 15274, 15275,
			15276, 15277, 15278, 20277, 20280, 20281, 20282, 20283, 20284, 20285, 20286, 20287, 20288, 20289,
			20290, 20291, 20292, 20293, 20294, 20295, 20296, 14639, 14640, 14641, 14642, 14643, 14644, 14645, 14646,
			14647, 14648, 14649, 14650, 14651, 14652, 14653, 14654, 14655, 14656, 14657, 14658, 14659, 14660, 14661,
			14662, 14663, 14676, 14677, 20402, 20403, 20404, 20450, 20451, 20452, 20453, 20454, 20455, 20456, 20457,
			20458, 20459, 20460, 20461, 20462, 20463, 20464, 20465, 20466, 20467, 20468, 20469, 20470, 20471, 20472,
			20473, 20474, 20475, 20476, 20477, 20478, 20479, 20480, 20481, 20482, 20483, 20484, 20485, 20486, 20487,
			20488, 20489, 20490, 20491, 20492, 14635, 14636, 14637, 14638, 20607, 20608, 20609, 20610, 20611, 20612,
			20619, 20620, 20623, 20624, 20625, 20627, 20631, 20632, 20680, 20681, 20682, 20683, 20684, 20685,
			20686, 20687, 20688, 20689, 20690, 20691, 20692, 20693, 20694, 20695, 20696, 20697, 20698, 20699, 20800,
			20801, 20802, 20803, 20869, 20898, 20505, 20506, 20507, 20508, 20509, 20510, 20511, 20512, 20513, 20514,
			20540, 20541, 20542, 20543, 20544, 20548, 20549, 20550, 20551, 20552, 20553, 20554, 20555, 20556, 20557,
			20558, 20559, 20560, 20561, 20562, 20563, 20564, 20565, 20566, 20574, 20576, 20577, 20578, 20579, 20580,
			20581, 20587, 20588, 20589, 20590, 20598, 20599, 20045, 20046, 20047, 20048, 20049, 20050, 20051, 20052,
			20053, 20054, 20055, 20056, 20057, 20058, 20062, 20079, 20080, 20092, 20096, 20097, 17164, 17165, 17166,
			17167, 17168, 20901, 20902, 20946, 20947, 20948, 20949, 20950, 20951, 20952, 20953, 20957, 20959, 20960,
			20961, 20962, 20963, 20964, 20965, 20966, 20967, 20969, 20994, 20995, 20996, 20997, 20998, 20999, 14228,
			14229, 14230, 14233, 14234, 14235, 14236, 14237, 14238, 14239, 14240, 14241, 14242, 14243, 14244, 14245,
			14246, 14247, 14248, 14249, 14250, 14251, 14252, 14253, 14254, 14255, 14256, 14257, 14258, 14259, 14260,
			14261, 14262, 14263, 14264, 14265, 14266, 14270, 14271, 14272, 14273, 14274, 14275, 14276, 14277, 14278,
			14279, 14280, 14281, 14282, 14283, 14284, 14285, 14286, 14287, 14288, 14289, 14290, 14291, 14719, 14729,
			14740, 14766, 14767, 14768, 17000, 17001, 17002, 17019, 17020, 17021, 17022, 17023, 17024, 17025, 17026,
			17027, 17028, 17029, 17031, 17032, 17040, 17041, 17042, 17043, 17044, 17045, 17046, 17047, 17048, 17066,
			17082, 17083, 17084, 17085,
			17086,
			21225, // by CosMosiS start line
			21226, 21229, 21230, 21233, 21234, 21237, 21238, 15404, 15405, 15406, 15407, 15408, 15409, 15410, 15411,
			15412, 15413, 15414, 15415, 15416, 15417, 15425, 15434, 15435, 15437, 15454, 15455, 15456, 15457, 15458,
			15459, 15460, 15462, 15465, 15466, 15468, 21421, 21422, 21241,// by CosMosiS end line
			20635,// by SmokiMo start line
			20636, 20637, 20638,// by SmokiMo end line
			30276, // Soulshot Pack (R-Grade)
			30277, // Soulshot Large Pack (R-Grade)
			30278, // Spiritshot Pack (R-Grade)
			30279, // Spiritshot Large Pack (R-Grade)
			30280, // Blessed Spiritshot Pack (R-Grade)
			30281, // Blessed Spiritshot Large Pack (R-Grade)
			30282, // Orichalcum Quiver
			30283, // Orichalcum Bolt Container
			30284, // Requiem Weapon Box
			30285, // Immortal Heavy Armor Box
			30286, // Immortal Light Armor Box
			30287, // Immortal Robe Armor Box
			30288, // Apocalypse Weapon Box
			30289, // Twilight Heavy Armor Box
			30290, // Twilight Light Armor Box
			30291, // Twilight Robe Armor Box
			30292, // Immortal Accessory Box
			30293, // Twilight Accessory Box
			22578, // Set Weapons C 30-days
			22579, // Set Weapons B 30-days
			22580, // Set Weapons A 30-days
			22581, // Set Weapons S 90-days
			22202, // Set Weapons S80 (Dyna) 90-days
			22583, // Set armor C 30-days
			22584, // Set armor B 30-days
			22585, // Set armor A 30-days
			21781, // Set armor S heavy 90-days
			21786, // Set armor S light 90-days
			21792, // Set armor S robe 90-days
			22586, // Set armors S 90-days
			21816, // Set jewels S 90-days
			21799, // Set armor S80 heavy (Dynasty) 90-days
			21805, // Set armor S80 light (Dynasty) 90-days
			21812, // Set armor S80 robe (Dynasty) 90-days
			21820, // Set jewels S80 (Dynasty) 90-days
			22571, // Agathion (7-Day) at random: Baby Panda, Griffin, Phoenix or One-Eyed Bat Drove
			21325, // Agathion Griffin (7-Day) 
			33465,
			34927,
			34928,
			34929,
			34930,
			34931,
			34932,
			34855,
			30374,  // Dimensional packs start here
			30388,
			30389,
			30390,
			30391,
			34955 //ancient dye lvl 5 box
	};

	// TODO 
	@Override
	public int[] getItemIds()
	{
		return ITEM_IDS;
	}

	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		int itemId = item.getItemId();

		if (!canBeExtracted(player, item))
			return false;

		if (!useItem(player, item, 1))
			return false;

		switch (itemId) {
		case 17086:
			use17086(player, ctrl);
			break;
		case 17085:
			use17085(player, ctrl);
			break;
		case 17084:
			use17084(player, ctrl);
			break;
		case 17083:
			use17083(player, ctrl);
			break;
		case 17082:
			use17082(player, ctrl);
			break;
		case 17066:
			use17066(player, ctrl);
			break;
		case 17048:
			use17048(player, ctrl);
			break;
		case 17047:
			use17047(player, ctrl);
			break;
		case 17046:
			use17046(player, ctrl);
			break;
		case 17045:
			use17045(player, ctrl);
			break;
		case 17044:
			use17044(player, ctrl);
			break;
		case 17043:
			use17043(player, ctrl);
			break;
		case 17042:
			use17042(player, ctrl);
			break;
		case 17041:
			use17041(player, ctrl);
			break;
		case 17040:
			use17040(player, ctrl);
			break;
		case 17032:
			use17032(player, ctrl);
			break;
		case 17031:
			use17031(player, ctrl);
			break;
		case 17029:
			use17029(player, ctrl);
			break;
		case 17028:
			use17028(player, ctrl);
			break;
		case 17027:
			use17027(player, ctrl);
			break;
		case 17026:
			use17026(player, ctrl);
			break;
		case 17025:
			use17025(player, ctrl);
			break;
		case 17024:
			use17024(player, ctrl);
			break;
		case 17023:
			use17023(player, ctrl);
			break;
		case 17022:
			use17022(player, ctrl);
			break;
		case 17021:
			use17021(player, ctrl);
			break;
		case 17020:
			use17020(player, ctrl);
			break;
		case 17019:
			use17019(player, ctrl);
			break;
		case 17002:
			use17002(player, ctrl);
			break;
		case 17001:
			use17001(player, ctrl);
			break;
		case 17000:
			use17000(player, ctrl);
			break;
		case 14768:
			use14768(player, ctrl);
			break;
		case 14767:
			use14767(player, ctrl);
			break;
		case 14766:
			use14766(player, ctrl);
			break;
		case 14740:
			use14740(player, ctrl);
			break;
		case 14729:
			use14729(player, ctrl);
			break;
		case 14719:
			use14719(player, ctrl);
			break;
		case 14291:
			use14291(player, ctrl);
			break;
		case 14290:
			use14290(player, ctrl);
			break;
		case 14289:
			use14289(player, ctrl);
			break;
		case 14288:
			use14288(player, ctrl);
			break;
		case 14287:
			use14287(player, ctrl);
			break;
		case 14286:
			use14286(player, ctrl);
			break;
		case 14285:
			use14285(player, ctrl);
			break;
		case 14284:
			use14284(player, ctrl);
			break;
		case 14283:
			use14283(player, ctrl);
			break;
		case 14282:
			use14282(player, ctrl);
			break;
		case 14281:
			use14281(player, ctrl);
			break;
		case 14280:
			use14280(player, ctrl);
			break;
		case 14279:
			use14279(player, ctrl);
			break;
		case 14278:
			use14278(player, ctrl);
			break;
		case 14277:
			use14277(player, ctrl);
			break;
		case 14276:
			use14276(player, ctrl);
			break;
		case 14275:
			use14275(player, ctrl);
			break;
		case 14274:
			use14274(player, ctrl);
			break;
		case 14273:
			use14273(player, ctrl);
			break;
		case 14272:
			use14272(player, ctrl);
			break;
		case 14271:
			use14271(player, ctrl);
			break;
		case 14270:
			use14270(player, ctrl);
			break;
		case 14266:
			use14266(player, ctrl);
			break;
		case 14265:
			use14265(player, ctrl);
			break;
		case 14264:
			use14264(player, ctrl);
			break;
		case 14263:
			use14263(player, ctrl);
			break;
		case 14262:
			use14262(player, ctrl);
			break;
		case 14261:
			use14261(player, ctrl);
			break;
		case 14260:
			use14260(player, ctrl);
			break;
		case 14259:
			use14259(player, ctrl);
			break;
		case 14258:
			use14258(player, ctrl);
			break;
		case 14257:
			use14257(player, ctrl);
			break;
		case 14256:
			use14256(player, ctrl);
			break;
		case 14255:
			use14255(player, ctrl);
			break;
		case 14254:
			use14254(player, ctrl);
			break;
		case 14253:
			use14253(player, ctrl);
			break;
		case 14252:
			use14252(player, ctrl);
			break;
		case 14251:
			use14251(player, ctrl);
			break;
		case 14250:
			use14250(player, ctrl);
			break;
		case 14249:
			use14249(player, ctrl);
			break;
		case 14248:
			use14248(player, ctrl);
			break;
		case 14247:
			use14247(player, ctrl);
			break;
		case 14246:
			use14246(player, ctrl);
			break;
		case 14245:
			use14245(player, ctrl);
			break;
		case 14244:
			use14244(player, ctrl);
			break;
		case 14243:
			use14243(player, ctrl);
			break;
		case 14242:
			use14242(player, ctrl);
			break;
		case 14241:
			use14241(player, ctrl);
			break;
		case 14240:
			use14240(player, ctrl);
			break;
		case 14239:
			use14239(player, ctrl);
			break;
		case 14238:
			use14238(player, ctrl);
			break;
		case 14237:
			use14237(player, ctrl);
			break;
		case 14236:
			use14236(player, ctrl);
			break;
		case 14235:
			use14235(player, ctrl);
			break;
		case 14234:
			use14234(player, ctrl);
			break;
		case 14233:
			use14233(player, ctrl);
			break;
		case 14230:
			use14230(player, ctrl);
			break;
		case 14229:
			use14229(player, ctrl);
			break;
		case 14228:
			use14228(player, ctrl);
			break;
		case 20999:
			use20999(player, ctrl);
			break;
		case 20998:
			use20998(player, ctrl);
			break;
		case 20997:
			use20997(player, ctrl);
			break;
		case 20996:
			use20996(player, ctrl);
			break;
		case 20995:
			use20995(player, ctrl);
			break;
		case 20994:
			use20994(player, ctrl);
			break;
		case 20969:
			use20969(player, ctrl);
			break;
		case 20967:
			use20967(player, ctrl);
			break;
		case 20966:
			use20966(player, ctrl);
			break;
		case 20965:
			use20965(player, ctrl);
			break;
		case 20964:
			use20964(player, ctrl);
			break;
		case 20963:
			use20963(player, ctrl);
			break;
		case 20962:
			use20962(player, ctrl);
			break;
		case 20961:
			use20961(player, ctrl);
			break;
		case 20960:
			use20960(player, ctrl);
			break;
		case 20959:
			use20959(player, ctrl);
			break;
		case 20957:
			use20957(player, ctrl);
			break;

		case 20953:
			use20953(player, ctrl);
			break;
		case 20952:
			use20952(player, ctrl);
			break;
		case 20951:
			use20951(player, ctrl);
			break;
		case 20950:
			use20950(player, ctrl);
			break;
		case 20949:
			use20949(player, ctrl);
			break;
		case 20948:
			use20948(player, ctrl);
			break;
		case 20947:
			use20947(player, ctrl);
			break;
		case 20946:
			use20946(player, ctrl);
			break;

		case 20902:
			use20902(player, ctrl);
			break;
		case 20901:
			use20901(player, ctrl);
			break;
		case 17168:
			use17168(player, ctrl);
			break;
		case 17167:
			use17167(player, ctrl);
			break;
		case 17166:
			use17166(player, ctrl);
			break;
		case 17165:
			use17165(player, ctrl);
			break;
		case 17164:
			use17164(player, ctrl);
			break;

		case 20097:
			use20097(player, ctrl);
			break;
		case 20096:
			use20096(player, ctrl);
			break;
		case 20092:
			use20092(player, ctrl);
			break;
		case 20080:
			use20080(player, ctrl);
			break;
		case 20079:
			use20079(player, ctrl);
			break;
		case 20062:
			use20062(player, ctrl);
			break;
		case 20058:
			use20058(player, ctrl);
			break;
		case 20057:
			use20057(player, ctrl);
			break;
		case 20056:
			use20056(player, ctrl);
			break;
		case 20055:
			use20055(player, ctrl);
			break;
		case 20054:
			use20054(player, ctrl);
			break;
		case 20053:
			use20053(player, ctrl);
			break;
		case 20052:
			use20052(player, ctrl);
			break;
		case 20051:
			use20051(player, ctrl);
			break;
		case 20050:
			use20050(player, ctrl);
			break;
		case 20049:
			use20049(player, ctrl);
			break;
		case 20048:
			use20048(player, ctrl);
			break;
		case 20047:
			use20047(player, ctrl);
			break;
		case 20046:
			use20046(player, ctrl);
			break;
		case 20045:
			use20045(player, ctrl);
			break;

		case 20599:
			use20599(player, ctrl);
			break;
		case 20598:
			use20598(player, ctrl);
			break;
		case 20590:
			use20590(player, ctrl);
			break;
		case 20589:
			use20589(player, ctrl);
			break;
		case 20588:
			use20588(player, ctrl);
			break;
		case 20587:
			use20587(player, ctrl);
			break;
		case 20581:
			use20581(player, ctrl);
			break;
		case 20580:
			use20580(player, ctrl);
			break;
		case 20579:
			use20579(player, ctrl);
			break;
		case 20578:
			use20578(player, ctrl);
			break;
		case 20577:
			use20577(player, ctrl);
			break;
		case 20576:
			use20576(player, ctrl);
			break;
		case 20574:
			use20574(player, ctrl);
			break;
		case 20566:
			use20566(player, ctrl);
			break;
		case 20565:
			use20565(player, ctrl);
			break;
		case 20564:
			use20564(player, ctrl);
			break;
		case 20563:
			use20563(player, ctrl);
			break;
		case 20562:
			use20562(player, ctrl);
			break;
		case 20561:
			use20561(player, ctrl);
			break;
		case 20560:
			use20560(player, ctrl);
			break;
		case 20559:
			use20559(player, ctrl);
			break;
		case 20558:
			use20558(player, ctrl);
			break;
		case 20557:
			use20557(player, ctrl);
			break;
		case 20556:
			use20556(player, ctrl);
			break;
		case 20555:
			use20555(player, ctrl);
			break;
		case 20554:
			use20554(player, ctrl);
			break;
		case 20553:
			use20553(player, ctrl);
			break;
		case 20552:
			use20552(player, ctrl);
			break;
		case 20551:
			use20551(player, ctrl);
			break;
		case 20550:
			use20550(player, ctrl);
			break;
		case 20549:
			use20549(player, ctrl);
			break;
		case 20548:
			use20548(player, ctrl);
			break;
		case 20544:
			use20544(player, ctrl);
			break;
		case 20543:
			use20543(player, ctrl);
			break;
		case 20542:
			use20542(player, ctrl);
			break;
		case 20541:
			use20541(player, ctrl);
			break;
		case 20540:
			use20540(player, ctrl);
			break;
		case 20514:
			use20514(player, ctrl);
			break;
		case 20513:
			use20513(player, ctrl);
			break;
		case 20512:
			use20512(player, ctrl);
			break;
		case 20511:
			use20511(player, ctrl);
			break;
		case 20510:
			use20510(player, ctrl);
			break;
		case 20509:
			use20509(player, ctrl);
			break;
		case 20508:
			use20508(player, ctrl);
			break;
		case 20507:
			use20507(player, ctrl);
			break;
		case 20506:
			use20506(player, ctrl);
			break;
		case 20505:
			use20505(player, ctrl);
			break;
		case 20898:
			use20898(player, ctrl);
			break;
		case 20869:
			use20869(player, ctrl);
			break;
		case 20803:
			use20803(player, ctrl);
			break;
		case 20802:
			use20802(player, ctrl);
			break;
		case 20801:
			use20801(player, ctrl);
			break;
		case 20800:
			use20800(player, ctrl);
			break;
		case 20699:
			use20699(player, ctrl);
			break;
		case 20698:
			use20698(player, ctrl);
			break;
		case 20697:
			use20697(player, ctrl);
			break;
		case 20696:
			use20696(player, ctrl);
			break;
		case 20695:
			use20695(player, ctrl);
			break;
		case 20694:
			use20694(player, ctrl);
			break;
		case 20693:
			use20693(player, ctrl);
			break;
		case 20692:
			use20692(player, ctrl);
			break;
		case 20691:
			use20691(player, ctrl);
			break;
		case 20690:
			use20690(player, ctrl);
			break;
		case 20689:
			use20689(player, ctrl);
			break;
		case 20688:
			use20688(player, ctrl);
			break;
		case 20687:
			use20687(player, ctrl);
			break;
		case 20686:
			use20686(player, ctrl);
			break;
		case 20685:
			use20685(player, ctrl);
			break;
		case 20684:
			use20684(player, ctrl);
			break;
		case 20683:
			use20683(player, ctrl);
			break;
		case 20682:
			use20682(player, ctrl);
			break;
		case 20681:
			use20681(player, ctrl);
			break;
		case 20680:
			use20680(player, ctrl);
			break;
		case 20632:
			use20632(player, ctrl);
			break;
		case 20631:
			use20631(player, ctrl);
			break;
		case 20627:
			use20627(player, ctrl);
			break;
		case 20625:
			use20625(player, ctrl);
			break;
		case 20624:
			use20624(player, ctrl);
			break;
		case 20623:
			use20623(player, ctrl);
			break;

		case 20620:
			use20620(player, ctrl);
			break;
		case 20619:
			use20619(player, ctrl);
			break;
		case 20612:
			use20612(player, ctrl);
			break;
		case 20611:
			use20611(player, ctrl);
			break;
		case 20610:
			use20610(player, ctrl);
			break;
		case 20609:
			use20609(player, ctrl);
			break;
		case 20608:
			use20608(player, ctrl);
			break;
		case 20607:
			use20607(player, ctrl);
			break;
		case 14638:
			use14638(player, ctrl);
			break;
		case 14637:
			use14637(player, ctrl);
			break;
		case 14636:
			use14636(player, ctrl);
			break;
		case 14635:
			use14635(player, ctrl);
			break;
		case 20492:
			use20492(player, ctrl);
			break;
		case 20491:
			use20491(player, ctrl);
			break;
		case 20490:
			use20490(player, ctrl);
			break;
		case 20489:
			use20489(player, ctrl);
			break;
		case 20488:
			use20488(player, ctrl);
			break;
		case 20487:
			use20487(player, ctrl);
			break;
		case 20486:
			use20486(player, ctrl);
			break;
		case 20485:
			use20485(player, ctrl);
			break;
		case 20484:
			use20484(player, ctrl);
			break;
		case 20483:
			use20483(player, ctrl);
			break;
		case 20482:
			use20482(player, ctrl);
			break;
		case 20481:
			use20481(player, ctrl);
			break;
		case 20480:
			use20480(player, ctrl);
			break;
		case 20479:
			use20479(player, ctrl);
			break;
		case 20478:
			use20478(player, ctrl);
			break;
		case 20477:
			use20477(player, ctrl);
			break;
		case 20476:
			use20476(player, ctrl);
			break;
		case 20475:
			use20475(player, ctrl);
			break;
		case 20474:
			use20474(player, ctrl);
			break;
		case 20473:
			use20473(player, ctrl);
			break;
		case 20472:
			use20472(player, ctrl);
			break;
		case 20471:
			use20471(player, ctrl);
			break;
		case 20470:
			use20470(player, ctrl);
			break;
		case 20469:
			use20469(player, ctrl);
			break;
		case 20468:
			use20468(player, ctrl);
			break;
		case 20467:
			use20467(player, ctrl);
			break;
		case 20466:
			use20466(player, ctrl);
			break;
		case 20465:
			use20465(player, ctrl);
			break;
		case 20464:
			use20464(player, ctrl);
			break;
		case 20463:
			use20463(player, ctrl);
			break;
		case 20462:
			use20462(player, ctrl);
			break;
		case 20461:
			use20461(player, ctrl);
			break;
		case 20460:
			use20460(player, ctrl);
			break;
		case 20459:
			use20459(player, ctrl);
			break;
		case 20458:
			use20458(player, ctrl);
			break;
		case 20457:
			use20457(player, ctrl);
			break;
		case 20456:
			use20456(player, ctrl);
			break;
		case 20455:
			use20455(player, ctrl);
			break;
		case 20454:
			use20454(player, ctrl);
			break;
		case 20453:
			use20453(player, ctrl);
			break;
		case 20452:
			use20452(player, ctrl);
			break;
		case 20451:
			use20451(player, ctrl);
			break;
		case 20450:
			use20450(player, ctrl);
			break;
		case 20402:
			use20402(player, ctrl);
			break;
		case 20403:
			use20403(player, ctrl);
			break;
		case 20404:
			use20404(player, ctrl);
			break;
		case 21325:
			use21325(player, ctrl);
			break;
		case 14677:
			use14677(player, ctrl);
			break;
		case 14676:
			use14676(player, ctrl);
			break;
		case 14663:
			use14663(player, ctrl);
			break;
		case 14662:
			use14662(player, ctrl);
			break;
		case 14661:
			use14661(player, ctrl);
			break;
		case 14660:
			use14660(player, ctrl);
			break;
		case 14659:
			use14659(player, ctrl);
			break;
		case 14658:
			use14658(player, ctrl);
			break;
		case 14657:
			use14657(player, ctrl);
			break;
		case 14656:
			use14656(player, ctrl);
			break;
		case 14655:
			use14655(player, ctrl);
			break;
		case 14654:
			use14654(player, ctrl);
			break;
		case 14653:
			use14653(player, ctrl);
			break;
		case 14652:
			use14652(player, ctrl);
			break;
		case 14651:
			use14651(player, ctrl);
			break;
		case 14650:
			use14650(player, ctrl);
			break;
		case 14649:
			use14649(player, ctrl);
			break;
		case 14648:
			use14648(player, ctrl);
			break;
		case 14647:
			use14647(player, ctrl);
			break;
		case 14646:
			use14646(player, ctrl);
			break;
		case 14645:
			use14645(player, ctrl);
			break;
		case 14644:
			use14644(player, ctrl);
			break;
		case 14643:
			use14643(player, ctrl);
			break;
		case 14642:
			use14642(player, ctrl);
			break;
		case 14641:
			use14641(player, ctrl);
			break;
		case 14640:
			use14640(player, ctrl);
			break;
		case 14639:
			use14639(player, ctrl);
			break;
		case 20296:
			use20296(player, ctrl);
			break;
		case 20295:
			use20295(player, ctrl);
			break;
		case 20294:
			use20294(player, ctrl);
			break;
		case 20293:
			use20293(player, ctrl);
			break;
		case 20292:
			use20292(player, ctrl);
			break;
		case 20291:
			use20291(player, ctrl);
			break;
		case 20290:
			use20290(player, ctrl);
			break;
		case 20289:
			use20289(player, ctrl);
			break;
		case 20288:
			use20288(player, ctrl);
			break;
		case 20287:
			use20287(player, ctrl);
			break;
		case 20286:
			use20286(player, ctrl);
			break;
		case 20285:
			use20285(player, ctrl);
			break;
		case 20284:
			use20284(player, ctrl);
			break;
		case 20283:
			use20283(player, ctrl);
			break;
		case 20282:
			use20282(player, ctrl);
			break;
		case 20281:
			use20281(player, ctrl);
			break;
		case 20280:
			use20280(player, ctrl);
			break;
		case 20277:
			use20277(player, ctrl);
			break;
		case 15278:
			use15278(player, ctrl);
			break;
		case 15277:
			use15277(player, ctrl);
			break;
		case 15276:
			use15276(player, ctrl);
			break;
		case 15275:
			use15275(player, ctrl);
			break;
		case 15274:
			use15274(player, ctrl);
			break;
		case 15273:
			use15273(player, ctrl);
			break;
		case 15272:
			use15272(player, ctrl);
			break;
		case 15271:
			use15271(player, ctrl);
			break;
		case 15270:
			use15270(player, ctrl);
			break;
		case 15269:
			use15269(player, ctrl);
			break;
		case 15268:
			use15268(player, ctrl);
			break;
		case 15267:
			use15267(player, ctrl);
			break;
		case 15266:
			use15266(player, ctrl);
			break;
		case 15265:
			use15265(player, ctrl);
			break;
		case 15264:
			use15264(player, ctrl);
			break;
		case 15263:
			use15263(player, ctrl);
			break;
		case 15262:
			use15262(player, ctrl);
			break;
		case 15261:
			use15261(player, ctrl);
			break;
		case 15260:
			use15260(player, ctrl);
			break;
		case 15259:
			use15259(player, ctrl);
			break;
		case 15258:
			use15258(player, ctrl);
			break;
		case 15257:
			use15257(player, ctrl);
			break;
		case 15256:
			use15256(player, ctrl);
			break;
		case 15255:
			use15255(player, ctrl);
			break;
		case 15254:
			use15254(player, ctrl);
			break;
		case 15253:
			use15253(player, ctrl);
			break;
		case 15252:
			use15252(player, ctrl);
			break;
		case 15251:
			use15251(player, ctrl);
			break;
		case 15250:
			use15250(player, ctrl);
			break;
		case 15249:
			use15249(player, ctrl);
			break;
		case 15248:
			use15248(player, ctrl);
			break;
		case 15247:
			use15247(player, ctrl);
			break;
		case 15246:
			use15246(player, ctrl);
			break;
		case 15245:
			use15245(player, ctrl);
			break;
		case 15244:
			use15244(player, ctrl);
			break;
		case 15243:
			use15243(player, ctrl);
			break;
		case 15242:
			use15242(player, ctrl);
			break;
		case 15241:
			use15241(player, ctrl);
			break;
		case 15240:
			use15240(player, ctrl);
			break;
		case 15239:
			use15239(player, ctrl);
			break;
		case 15238:
			use15238(player, ctrl);
			break;
		case 15237:
			use15237(player, ctrl);
			break;
		case 15236:
			use15236(player, ctrl);
			break;
		case 15235:
			use15235(player, ctrl);
			break;
		case 15234:
			use15234(player, ctrl);
			break;
		case 15233:
			use15233(player, ctrl);
			break;
		case 15232:
			use15232(player, ctrl);
			break;
		case 15231:
			use15231(player, ctrl);
			break;
		case 15230:
			use15230(player, ctrl);
			break;
		case 15229:
			use15229(player, ctrl);
			break;
		case 15228:
			use15228(player, ctrl);
			break;
		case 15227:
			use15227(player, ctrl);
			break;
		case 15226:
			use15226(player, ctrl);
			break;
		case 15225:
			use15225(player, ctrl);
			break;
		case 15224:
			use15224(player, ctrl);
			break;
		case 15223:
			use15223(player, ctrl);
			break;
		case 15222:
			use15222(player, ctrl);
			break;

		case 15219:
			use15219(player, ctrl);
			break;
		case 15218:
			use15218(player, ctrl);
			break;
		case 15217:
			use15217(player, ctrl);
			break;
		case 15216:
			use15216(player, ctrl);
			break;
		case 15215:
			use15215(player, ctrl);
			break;
		case 15214:
			use15214(player, ctrl);
			break;
		case 15213:
			use15213(player, ctrl);
			break;
		case 15212:
			use15212(player, ctrl);
			break;
		case 15209:
			use15209(player, ctrl);
			break;
		case 15207:
			use15207(player, ctrl);
			break;
		case 15206:
			use15206(player, ctrl);
			break;
		case 15205:
			use15205(player, ctrl);
			break;
		case 15204:
			use15204(player, ctrl);
			break;
		case 15203:
			use15203(player, ctrl);
			break;
		case 15202:
			use15202(player, ctrl);
			break;
		case 15201:
			use15201(player, ctrl);
			break;
		case 15200:
			use15200(player, ctrl);
			break;

		case 20799:
			use20799(player, ctrl);
			break;
		case 20798:
			use20798(player, ctrl);
			break;
		case 20797:
			use20797(player, ctrl);
			break;
		case 20796:
			use20796(player, ctrl);
			break;
		case 20773:
			use20773(player, ctrl);
			break;
		case 20754:
			use20754(player, ctrl);
			break;
		case 20753:
			use20753(player, ctrl);
			break;
		case 20740:
			use20740(player, ctrl);
			break;
		case 20738:
			use20738(player, ctrl);
			break;
		case 20737:
			use20737(player, ctrl);
			break;
		case 20736:
			use20736(player, ctrl);
			break;
		case 20735:
			use20735(player, ctrl);
			break;
		case 20734:
			use20734(player, ctrl);
			break;
		case 20705:
			use20705(player, ctrl);
			break;
		case 20704:
			use20704(player, ctrl);
			break;
		case 20703:
			use20703(player, ctrl);
			break;
		case 20702:
			use20702(player, ctrl);
			break;
		case 20701:
			use20701(player, ctrl);
			break;
		case 20700:
			use20700(player, ctrl);
			break;
		case 14530:
			use14530(player, ctrl);
			break;
		case 13799:
			use13799(player, ctrl);
			break;
		case 13428:
			use13428(player, ctrl);
			break;
		case 13427:
			use13427(player, ctrl);
			break;
		case 13426:
			use13426(player, ctrl);
			break;
		case 13425:
			use13425(player, ctrl);
			break;
		case 13424:
			use13424(player, ctrl);
			break;
		case 13423:
			use13423(player, ctrl);
			break;
		case 13422:
			use13422(player, ctrl);
			break;
		case 13385:
			use13385(player, ctrl);
			break;
		case 13384:
			use13384(player, ctrl);
			break;
		case 13381:
			use13381(player, ctrl);
			break;
		case 13380:
			use13380(player, ctrl);
			break;
		case 13379:
			use13379(player, ctrl);
			break;
		case 13378:
			use13378(player, ctrl);
			break;
		case 13377:
			use13377(player, ctrl);
			break;
		case 13376:
			use13376(player, ctrl);
			break;
		case 13375:
			use13375(player, ctrl);
			break;
		case 13374:
			use13374(player, ctrl);
			break;
		case 13373:
			use13373(player, ctrl);
			break;
		case 13372:
			use13372(player, ctrl);
			break;
		case 13371:
			use13371(player, ctrl);
			break;
		case 13370:
			use13370(player, ctrl);
			break;
		case 13369:
			use13369(player, ctrl);
			break;
		case 13368:
			use13368(player, ctrl);
			break;
		case 13367:
			use13367(player, ctrl);
			break;
		case 13366:
			use13366(player, ctrl);
			break;
		case 13365:
			use13365(player, ctrl);
			break;
		case 13364:
			use13364(player, ctrl);
			break;
		case 13363:
			use13363(player, ctrl);
			break;
		case 13362:
			use13362(player, ctrl);
			break;
		case 13361:
			use13361(player, ctrl);
			break;
		case 13360:
			use13360(player, ctrl);
			break;
		case 13359:
			use13359(player, ctrl);
			break;
		case 13358:
			use13358(player, ctrl);
			break;
		case 13357:
			use13357(player, ctrl);
			break;
		case 13356:
			use13356(player, ctrl);
			break;
		case 13355:
			use13355(player, ctrl);
			break;
		case 13354:
			use13354(player, ctrl);
			break;
		case 13353:
			use13353(player, ctrl);
			break;
		case 13352:
			use13352(player, ctrl);
			break;
		case 13351:
			use13351(player, ctrl);
			break;
		case 13350:
			use13350(player, ctrl);
			break;
		case 13349:
			use13349(player, ctrl);
			break;
		case 13348:
			use13348(player, ctrl);
			break;
		case 13347:
			use13347(player, ctrl);
			break;
		case 13346:
			use13346(player, ctrl);
			break;
		case 13345:
			use13345(player, ctrl);
			break;
		case 13344:
			use13344(player, ctrl);
			break;
		case 13343:
			use13343(player, ctrl);
			break;
		case 13342:
			use13342(player, ctrl);
			break;
		case 13341:
			use13341(player, ctrl);
			break;
		case 16999:
			use16999(player, ctrl);
			break;
		case 16998:
			use16998(player, ctrl);
			break;
		case 16997:
			use16997(player, ctrl);
			break;
		case 16996:
			use16996(player, ctrl);
			break;
		case 16995:
			use16995(player, ctrl);
			break;
		case 16994:
			use16994(player, ctrl);
			break;
		case 16993:
			use16993(player, ctrl);
			break;
		case 16992:
			use16992(player, ctrl);
			break;
		case 16991:
			use16991(player, ctrl);
			break;
		case 16990:
			use16990(player, ctrl);
			break;
		case 16989:
			use16989(player, ctrl);
			break;
		case 16988:
			use16988(player, ctrl);
			break;
		case 16987:
			use16987(player, ctrl);
			break;
		case 16986:
			use16986(player, ctrl);
			break;
		case 16985:
			use16985(player, ctrl);
			break;
		case 16984:
			use16984(player, ctrl);
			break;
		case 16983:
			use16983(player, ctrl);
			break;
		case 16982:
			use16982(player, ctrl);
			break;
		case 16981:
			use16981(player, ctrl);
			break;
		case 16980:
			use16980(player, ctrl);
			break;
		case 16979:
			use16979(player, ctrl);
			break;
		case 16978:
			use16978(player, ctrl);
			break;
		case 16977:
			use16977(player, ctrl);
			break;
		case 16976:
			use16976(player, ctrl);
			break;
		case 16975:
			use16975(player, ctrl);
			break;
		case 16974:
			use16974(player, ctrl);
			break;
		case 16973:
			use16973(player, ctrl);
			break;
		case 16972:
			use16972(player, ctrl);
			break;
		case 16971:
			use16971(player, ctrl);
			break;
		case 16970:
			use16970(player, ctrl);
			break;
		case 16969:
			use16969(player, ctrl);
			break;
		case 16968:
			use16968(player, ctrl);
			break;
		case 16852:
			use16852(player, ctrl);
			break;
		case 16398:
			use16398(player, ctrl);
			break;
		case 16397:
			use16397(player, ctrl);
			break;
		case 16396:
			use16396(player, ctrl);
			break;
		case 16395:
			use16395(player, ctrl);
			break;
		case 16394:
			use16394(player, ctrl);
			break;
		case 16393:
			use16393(player, ctrl);
			break;
		case 16392:
			use16392(player, ctrl);
			break;
		case 16391:
			use16391(player, ctrl);
			break;
		case 16390:
			use16390(player, ctrl);
			break;
		case 16389:
			use16389(player, ctrl);
			break;
		case 16388:
			use16388(player, ctrl);
			break;
		case 16387:
			use16387(player, ctrl);
			break;
		case 16386:
			use16386(player, ctrl);
			break;
		case 16385:
			use16385(player, ctrl);
			break;
		case 16384:
			use16384(player, ctrl);
			break;
		case 16383:
			use16383(player, ctrl);
			break;
		case 20398:
			use20398(player, ctrl);
			break;
		case 20397:
			use20397(player, ctrl);
			break;
		case 20331:
			use20331(player, ctrl);
			break;
		case 20328:
			use20328(player, ctrl);
			break;
		case 20319:
			use20319(player, ctrl);
			break;
		case 20318:
			use20318(player, ctrl);
			break;
		case 20317:
			use20317(player, ctrl);
			break;
		case 20316:
			use20316(player, ctrl);
			break;
		case 20315:
			use20315(player, ctrl);
			break;
		case 20314:
			use20314(player, ctrl);
			break;
		case 21095:
			use21095(player, ctrl);
			break;
		case 21087:
			use21087(player, ctrl);
			break;
		case 21089:
			use21089(player, ctrl);
			break;
		case 21085:
			use21085(player, ctrl);
			break;
		case 21083:
			use21083(player, ctrl);
			break;
		case 21082:
			use21082(player, ctrl);
			break;
		case 21081:
			use21081(player, ctrl);
			break;
		case 21080:
			use21080(player, ctrl);
			break;
		case 21079:
			use21079(player, ctrl);
			break;
		case 21078:
			use21078(player, ctrl);
			break;
		case 21077:
			use21077(player, ctrl);
			break;
		case 21076:
			use21076(player, ctrl);
			break;
		case 21075:
			use21075(player, ctrl);
			break;
		case 21074:
			use21074(player, ctrl);
			break;
		case 21073:
			use21073(player, ctrl);
			break;
		case 21072:
			use21072(player, ctrl);
			break;
		case 21071:
			use21071(player, ctrl);
			break;
		case 21070:
			use21070(player, ctrl);
			break;
		case 21069:
			use21069(player, ctrl);
			break;
		case 21068:
			use21068(player, ctrl);
			break;
		case 21067:
			use21067(player, ctrl);
			break;
		case 21066:
			use21066(player, ctrl);
			break;
		case 21065:
			use21065(player, ctrl);
			break;
		case 2106:
			use21064(player, ctrl);
			break;
		case 21063:
			use21063(player, ctrl);
			break;
		case 21029:
			use21029(player, ctrl);
			break;
		case 21028:
			use21028(player, ctrl);
			break;
		case 21027:
			use21027(player, ctrl);
			break;
		case 21026:
			use21026(player, ctrl);
			break;
		case 21025:
			use21025(player, ctrl);
			break;
		case 21024:
			use21024(player, ctrl);
			break;
		case 21008:
			use21008(player, ctrl);
			break;
		case 21007:
			use21007(player, ctrl);
			break;
		case 21006:
			use21006(player, ctrl);
			break;
		case 21005:
			use21005(player, ctrl);
			break;
		case 21004:
			use21004(player, ctrl);
			break;
		case 21003:
			use21003(player, ctrl);
			break;
		case 21002:
			use21002(player, ctrl);
			break;
		case 21001:
			use21001(player, ctrl);
			break;
		case 21686:
			use21686(player, ctrl);
			break;
		case 21685:
			use21685(player, ctrl);
			break;
		case 21684:
			use21684(player, ctrl);
			break;
		case 21683:
			use21683(player, ctrl);
			break;
		case 21682:
			use21682(player, ctrl);
			break;
		case 21681:
			use21681(player, ctrl);
			break;
		case 21680:
			use21680(player, ctrl);
			break;
		case 21679:
			use21679(player, ctrl);
			break;
		case 21678:
			use21678(player, ctrl);
			break;
		case 21677:
			use21677(player, ctrl);
			break;
		case 21676:
			use21676(player, ctrl);
			break;
		case 21675:
			use21675(player, ctrl);
			break;
		case 21674:
			use21674(player, ctrl);
			break;
		case 21673:
			use21673(player, ctrl);
			break;
		case 21672:
			use21672(player, ctrl);
			break;
		case 21671:
			use21671(player, ctrl);
			break;
		case 21670:
			use21670(player, ctrl);
			break;
		case 21669:
			use21669(player, ctrl);
			break;
		case 21668:
			use21668(player, ctrl);
			break;
		case 21667:
			use21667(player, ctrl);
			break;
		case 21666:
			use21666(player, ctrl);
			break;
		case 21665:
			use21665(player, ctrl);
			break;
		case 21664:
			use21664(player, ctrl);
			break;
		case 21663:
			use21663(player, ctrl);
			break;
		case 21662:
			use21662(player, ctrl);
			break;
		case 21661:
			use21661(player, ctrl);
			break;
		case 21660:
			use21660(player, ctrl);
			break;
		case 21659:
			use21659(player, ctrl);
			break;
		case 21658:
			use21658(player, ctrl);
			break;
		case 21657:
			use21657(player, ctrl);
			break;
		case 21656:
			use21656(player, ctrl);
			break;
		case 21655:
			use21655(player, ctrl);
			break;
		case 21654:
			use21654(player, ctrl);
			break;
		case 21653:
			use21653(player, ctrl);
			break;
		case 21652:
			use21652(player, ctrl);
			break;
		case 21651:
			use21651(player, ctrl);
			break;
		case 21650:
			use21650(player, ctrl);
			break;
		case 21649:
			use21649(player, ctrl);
			break;
		case 21648:
			use21648(player, ctrl);
			break;
		case 21647:
			use21647(player, ctrl);
			break;
		case 21646:
			use21646(player, ctrl);
			break;
		case 21645:
			use21645(player, ctrl);
			break;
		case 21644:
			use21644(player, ctrl);
			break;
		case 21643:
			use21643(player, ctrl);
			break;
		case 21642:
			use21642(player, ctrl);
			break;
		case 21641:
			use21641(player, ctrl);
			break;
		case 21640:
			use21640(player, ctrl);
			break;
		case 21639:
			use21639(player, ctrl);
			break;
		case 21638:
			use21638(player, ctrl);
			break;
		case 21637:
			use21637(player, ctrl);
			break;
		case 21636:
			use21636(player, ctrl);
			break;
		case 21635:
			use21635(player, ctrl);
			break;
		case 21634:
			use21634(player, ctrl);
			break;
		case 21633:
			use21633(player, ctrl);
			break;
		case 21632:
			use21632(player, ctrl);
			break;
		case 21631:
			use21631(player, ctrl);
			break;
		case 21630:
			use21630(player, ctrl);
			break;
		case 21629:
			use21629(player, ctrl);
			break;
		case 21628:
			use21628(player, ctrl);
			break;
		case 21627:
			use21627(player, ctrl);
			break;
		case 21626:
			use21626(player, ctrl);
			break;
		case 21625:
			use21625(player, ctrl);
			break;
		case 21624:
			use21624(player, ctrl);
			break;
		case 21623:
			use21623(player, ctrl);
			break;
		case 21622:
			use21622(player, ctrl);
			break;
		case 21621:
			use21621(player, ctrl);
			break;
		case 21620:
			use21620(player, ctrl);
			break;
		case 13292:
			use13292(player, ctrl);
			break;
		case 13291:
			use13291(player, ctrl);
			break;
		case 13279:
			use13279(player, ctrl);
			break;
		case 13276:
			use13276(player, ctrl);
			break;
		case 13275:
			use13275(player, ctrl);
			break;
		case 13257:
			use13257(player, ctrl);
			break;
		case 13256:
			use13256(player, ctrl);
			break;
		case 13233:
			use13233(player, ctrl);
			break;
		case 13232:
			use13232(player, ctrl);
			break;
		case 13231:
			use13231(player, ctrl);
			break;
		case 13230:
			use13230(player, ctrl);
			break;
		case 13229:
			use13229(player, ctrl);
			break;
		case 13228:
			use13228(player, ctrl);
			break;
		case 13227:
			use13227(player, ctrl);
			break;
		case 13226:
			use13226(player, ctrl);
			break;
		case 13225:
			use13225(player, ctrl);
			break;
		case 13126:
			use13126(player, ctrl);
			break;
		case 13125:
			use13125(player, ctrl);
			break;
		case 13124:
			use13124(player, ctrl);
			break;
		case 13122:
			use13122(player, ctrl);
			break;
		case 13121:
			use13121(player, ctrl);
			break;
		case 13120:
			use13120(player, ctrl);
			break;
		case 13119:
			use13119(player, ctrl);
			break;
		case 13118:
			use13118(player, ctrl);
			break;
		case 13117:
			use13117(player, ctrl);
			break;
		case 13116:
			use13116(player, ctrl);
			break;
		case 13115:
			use13115(player, ctrl);
			break;
		case 13114:
			use13114(player, ctrl);
			break;
		case 13113:
			use13113(player, ctrl);
			break;
		case 13112:
			use13112(player, ctrl);
			break;
		case 13111:
			use13111(player, ctrl);
			break;
		case 13110:
			use13110(player, ctrl);
			break;
		case 13109:
			use13109(player, ctrl);
			break;
		case 13108:
			use13108(player, ctrl);
			break;
		case 13107:
			use13107(player, ctrl);
			break;
		case 13106:
			use13106(player, ctrl);
			break;
		case 13105:
			use13105(player, ctrl);
			break;
		case 13104:
			use13104(player, ctrl);
			break;
		case 13103:
			use13103(player, ctrl);
			break;
		case 10259:
			use10259(player, ctrl);
			break;
		case 10258:
			use10258(player, ctrl);
			break;
		case 10257:
			use10257(player, ctrl);
			break;
		case 10256:
			use10256(player, ctrl);
			break;
		case 10255:
			use10255(player, ctrl);
			break;
		case 15199:
			use15199(player, ctrl);
			break;
		case 15198:
			use15198(player, ctrl);
			break;
		case 15197:
			use15197(player, ctrl);
			break;
		case 15196:
			use15196(player, ctrl);
			break;
		case 15195:
			use15195(player, ctrl);
			break;
		case 15194:
			use15194(player, ctrl);
			break;
		case 20189:
			use20189(player, ctrl);
			break;
		case 20188:
			use20188(player, ctrl);
			break;
		case 20187:
			use20187(player, ctrl);
			break;
		case 20186:
			use20186(player, ctrl);
			break;
		case 20185:
			use20185(player, ctrl);
			break;
		case 20184:
			use20184(player, ctrl);
			break;
		case 20183:
			use20183(player, ctrl);
			break;
		case 20182:
			use20182(player, ctrl);
			break;
		case 20181:
			use20181(player, ctrl);
			break;
		case 20180:
			use20180(player, ctrl);
			break;
		case 20179:
			use20179(player, ctrl);
			break;
		case 20102:
			use20102(player, ctrl);
			break;
		case 20101:
			use20101(player, ctrl);
			break;
		case 15378:
			use15378(player, ctrl);
			break;
		case 15377:
			use15377(player, ctrl);
			break;
		case 15376:
			use15376(player, ctrl);
			break;
		case 15375:
			use15375(player, ctrl);
			break;
		case 15374:
			use15374(player, ctrl);
			break;
		case 15373:
			use15373(player, ctrl);
			break;
		case 15372:
			use15372(player, ctrl);
			break;
		case 15371:
			use15371(player, ctrl);
			break;
		case 15370:
			use15370(player, ctrl);
			break;
		case 15345:
			use15345(player, ctrl);
			break;
		case 15343:
			use15343(player, ctrl);
			break;
		case 21619:
			use21619(player, ctrl);
			break;
		case 21618:
			use21618(player, ctrl);
			break;
		case 21617:
			use21617(player, ctrl);
			break;
		case 21616:
			use21616(player, ctrl);
			break;
		case 21615:
			use21615(player, ctrl);
			break;
		case 21614:
			use21614(player, ctrl);
			break;
		case 21613:
			use21613(player, ctrl);
			break;
		case 21612:
			use21612(player, ctrl);
			break;
		case 21611:
			use21611(player, ctrl);
			break;
		case 21610:
			use21610(player, ctrl);
			break;
		case 21609:
			use21609(player, ctrl);
			break;
		case 21608:
			use21608(player, ctrl);
			break;
		case 21607:
			use21607(player, ctrl);
			break;
		case 21606:
			use21606(player, ctrl);
			break;
		case 21605:
			use21605(player, ctrl);
			break;
		case 21604:
			use21604(player, ctrl);
			break;
		case 21603:
			use21603(player, ctrl);
			break;
		case 21602:
			use21602(player, ctrl);
			break;
		case 21601:
			use21601(player, ctrl);
			break;
		case 22187:
			use22187(player, ctrl);
			break;
		case 14841:
			use14841(player, ctrl);
			break;
		case 14847:
			use14847(player, ctrl);
			break;
		case 10408:
			use10408(player, ctrl);
			break;
		case 10473:
			use10473(player, ctrl);
			break;
		case 9599:
			use9599(player, ctrl);
			break;
		case 20069:
			use20069(player, ctrl);
			break;
		case 20070:
			use20070(player, ctrl);
			break;
		case 20071:
			use20071(player, ctrl);
			break;
		case 20072:
			use20072(player, ctrl);
			break;
		case 20073:
			use20073(player, ctrl);
			break;
		case 20074:
			use20074(player, ctrl);
			break;
		case 20210:
			use20210(player, ctrl);
			break;
		case 20211:
			use20211(player, ctrl);
			break;
		case 20215:
			use20215(player, ctrl);
			break;
		case 20216:
			use20216(player, ctrl);
			break;
		case 20217:
			use20217(player, ctrl);
			break;
		case 20218:
			use20218(player, ctrl);
			break;
		case 20219:
			use20219(player, ctrl);
			break;
		case 20220:
			use20220(player, ctrl);
			break;
		case 20227:
			use20227(player, ctrl);
			break;
		case 20228:
			use20228(player, ctrl);
			break;
		case 20229:
			use20229(player, ctrl);
			break;
		case 20233:
			use20233(player, ctrl);
			break;
		case 20234:
			use20234(player, ctrl);
			break;
		case 20235:
			use20235(player, ctrl);
			break;
		case 20239:
			use20239(player, ctrl);
			break;
		case 20240:
			use20240(player, ctrl);
			break;
		case 20241:
			use20241(player, ctrl);
			break;
		case 20242:
			use20242(player, ctrl);
			break;
		case 20243:
			use20243(player, ctrl);
			break;
		case 20244:
			use20244(player, ctrl);
			break;
		case 20251:
			use20251(player, ctrl);
			break;
		case 20254:
			use20254(player, ctrl);
			break;
		case 20278:
			use20278(player, ctrl);
			break;
		case 20279:
			use20279(player, ctrl);
			break;
		case 20041:
			use20041(player, ctrl);
			break;
		case 20042:
			use20042(player, ctrl);
			break;
		case 20043:
			use20043(player, ctrl);
			break;
		case 20044:
			use20044(player, ctrl);
			break;
		case 20035:
			use20035(player, ctrl);
			break;
		case 20036:
			use20036(player, ctrl);
			break;
		case 20037:
			use20037(player, ctrl);
			break;
		case 20038:
			use20038(player, ctrl);
			break;
		case 20039:
			use20039(player, ctrl);
			break;
		case 20040:
			use20040(player, ctrl);
			break;
		case 20060:
			use20060(player, ctrl);
			break;
		case 20061:
			use20061(player, ctrl);
			break;
		case 22000:
			use22000(player, ctrl);
			break;
		case 22001:
			use22001(player, ctrl);
			break;
		case 22002:
			use22002(player, ctrl);
			break;
		case 22003:
			use22003(player, ctrl);
			break;
		case 22004:
			use22004(player, ctrl);
			break;
		case 22005:
			use22005(player, ctrl);
			break;
		case 20326:
			use20326(player, ctrl);
			break;
		case 20327:
			use20327(player, ctrl);
			break;
		case 20329:
			use20329(player, ctrl);
			break;
		case 20330:
			use20330(player, ctrl);
			break;
		case 20059:
			use20059(player, ctrl);
			break;
		case 20494:
			use20494(player, ctrl);
			break;
		case 20493:
			use20493(player, ctrl);
			break;
		case 20395:
			use20395(player, ctrl);
			break;
		case 21000:
			use21000(player, ctrl);
			break;
		case 13281:
			use13281(player, ctrl);
			break;
		case 13282:
			use13282(player, ctrl);
			break;
		case 13283:
			use13283(player, ctrl);
			break;
		case 13284:
			use13284(player, ctrl);
			break;
		case 13285:
			use13285(player, ctrl);
			break;
		case 13286:
			use13286(player, ctrl);
			break;
		case 13287:
			use13287(player, ctrl);
			break;
		case 13288:
			use13288(player, ctrl);
			break;
		case 13289:
			use13289(player, ctrl);
			break;
		case 13290:
			use13290(player, ctrl);
			break;
		case 14267:
			use14267(player, ctrl);
			break;
		case 14268:
			use14268(player, ctrl);
			break;
		case 14269:
			use14269(player, ctrl);
			break;
		case 13280:
			use13280(player, ctrl);
			break;
		case 13713:
			use13713(player, ctrl);
			break;
		case 13714:
			use13714(player, ctrl);
			break;
		case 13715:
			use13715(player, ctrl);
			break;
		case 13716:
			use13716(player, ctrl);
			break;
		case 13717:
			use13717(player, ctrl);
			break;
		case 13718:
			use13718(player, ctrl);
			break;
		case 13719:
			use13719(player, ctrl);
			break;
		case 13720:
			use13720(player, ctrl);
			break;
		case 13721:
			use13721(player, ctrl);
			break;
		case 14549:
			use14549(player, ctrl);
			break;
		case 14550:
			use14550(player, ctrl);
			break;
		case 14551:
			use14551(player, ctrl);
			break;
		case 14552:
			use14552(player, ctrl);
			break;
		case 14553:
			use14553(player, ctrl);
			break;
		case 14554:
			use14554(player, ctrl);
			break;
		case 14555:
			use14555(player, ctrl);
			break;
		case 14556:
			use14556(player, ctrl);
			break;
		case 14557:
			use14557(player, ctrl);
			break;
		case 13695:
			use13695(player, ctrl);
			break;
		case 13696:
			use13696(player, ctrl);
			break;
		case 13697:
			use13697(player, ctrl);
			break;
		case 13698:
			use13698(player, ctrl);
			break;
		case 13699:
			use13699(player, ctrl);
			break;
		case 13700:
			use13700(player, ctrl);
			break;
		case 13701:
			use13701(player, ctrl);
			break;
		case 13702:
			use13702(player, ctrl);
			break;
		case 13703:
			use13703(player, ctrl);
			break;
		case 14531:
			use14531(player, ctrl);
			break;
		case 14532:
			use14532(player, ctrl);
			break;
		case 14533:
			use14533(player, ctrl);
			break;
		case 14534:
			use14534(player, ctrl);
			break;
		case 14535:
			use14535(player, ctrl);
			break;
		case 14536:
			use14536(player, ctrl);
			break;
		case 14537:
			use14537(player, ctrl);
			break;
		case 14538:
			use14538(player, ctrl);
			break;
		case 14539:
			use14539(player, ctrl);
			break;
		case 13704:
			use13704(player, ctrl);
			break;
		case 13705:
			use13705(player, ctrl);
			break;
		case 13706:
			use13706(player, ctrl);
			break;
		case 13707:
			use13707(player, ctrl);
			break;
		case 13708:
			use13708(player, ctrl);
			break;
		case 13709:
			use13709(player, ctrl);
			break;
		case 13710:
			use13710(player, ctrl);
			break;
		case 13711:
			use13711(player, ctrl);
			break;
		case 13712:
			use13712(player, ctrl);
			break;
		case 14540:
			use14540(player, ctrl);
			break;
		case 14541:
			use14541(player, ctrl);
			break;
		case 14542:
			use14542(player, ctrl);
			break;
		case 14543:
			use14543(player, ctrl);
			break;
		case 14544:
			use14544(player, ctrl);
			break;
		case 14545:
			use14545(player, ctrl);
			break;
		case 14546:
			use14546(player, ctrl);
			break;
		case 14547:
			use14547(player, ctrl);
			break;
		case 14548:
			use14548(player, ctrl);
			break;
		case 14884:
			use14884(player, ctrl);
			break;
		case 14885:
			use14885(player, ctrl);
			break;
		case 14886:
			use14886(player, ctrl);
			break;
		case 14887:
			use14887(player, ctrl);
			break;
		case 14888:
			use14888(player, ctrl);
			break;
		case 14889:
			use14889(player, ctrl);
			break;
		case 14890:
			use14890(player, ctrl);
			break;
		case 14891:
			use14891(player, ctrl);
			break;
		case 14892:
			use14892(player, ctrl);
			break;
		case 14893:
			use14893(player, ctrl);
			break;
		case 14894:
			use14894(player, ctrl);
			break;
		case 14895:
			use14895(player, ctrl);
			break;
		case 14896:
			use14896(player, ctrl);
			break;
		case 14897:
			use14897(player, ctrl);
			break;
		case 14898:
			use14898(player, ctrl);
			break;
		case 14899:
			use14899(player, ctrl);
			break;
		case 14900:
			use14900(player, ctrl);
			break;
		case 14901:
			use14901(player, ctrl);
			break;
		case 14616:
			use14616(player, ctrl);
			break;
		case 20575:
			use20575(player, ctrl);
			break;
		case 20804:
			use20804(player, ctrl);
			break;
		case 20807:
			use20807(player, ctrl);
			break;
		case 20805:
			use20805(player, ctrl);
			break;
		case 20808:
			use20808(player, ctrl);
			break;
		case 20806:
			use20806(player, ctrl);
			break;
		case 20809:
			use20809(player, ctrl);
			break;
		case 20842:
			use20842(player, ctrl);
			break;
		case 20843:
			use20843(player, ctrl);
			break;
		case 20844:
			use20844(player, ctrl);
			break;
		case 20845:
			use20845(player, ctrl);
			break;
		case 20846:
			use20846(player, ctrl);
			break;
		case 20847:
			use20847(player, ctrl);
			break;
		case 20848:
			use20848(player, ctrl);
			break;
		case 20849:
			use20849(player, ctrl);
			break;
		case 20850:
			use20850(player, ctrl);
			break;
		case 20851:
			use20851(player, ctrl);
			break;
		case 20852:
			use20852(player, ctrl);
			break;
		case 20853:
			use20853(player, ctrl);
			break;
		case 20854:
			use20854(player, ctrl);
			break;
		case 20855:
			use20855(player, ctrl);
			break;
		case 20856:
			use20856(player, ctrl);
			break;
		case 20857:
			use20857(player, ctrl);
			break;
		case 20858:
			use20858(player, ctrl);
			break;
		case 20859:
			use20859(player, ctrl);
			break;
		case 20860:
			use20860(player, ctrl);
			break;
		case 20861:
			use20861(player, ctrl);
			break;
		case 20862:
			use20862(player, ctrl);
			break;
		case 20863:
			use20863(player, ctrl);
			break;
		case 20864:
			use20864(player, ctrl);
			break;
		case 20811:
			use20811(player, ctrl);
			break;
		case 20812:
			use20812(player, ctrl);
			break;
		case 20813:
			use20813(player, ctrl);
			break;
		case 20814:
			use20814(player, ctrl);
			break;
		case 20815:
			use20815(player, ctrl);
			break;
		case 20816:
			use20816(player, ctrl);
			break;
		case 20817:
			use20817(player, ctrl);
			break;
		case 20810:
			use20810(player, ctrl);
			break;
		case 20865:
			use20865(player, ctrl);
			break;
		case 20748:
			use20748(player, ctrl);
			break;
		case 20749:
			use20749(player, ctrl);
			break;
		case 20750:
			use20750(player, ctrl);
			break;
		case 20751:
			use20751(player, ctrl);
			break;
		case 20752:
			use20752(player, ctrl);
			break;
		case 13777:
			use13777(player, ctrl);
			break;
		case 13778:
			use13778(player, ctrl);
			break;
		case 13779:
			use13779(player, ctrl);
			break;
		case 13780:
			use13780(player, ctrl);
			break;
		case 13781:
			use13781(player, ctrl);
			break;
		case 13782:
			use13782(player, ctrl);
			break;
		case 13783:
			use13783(player, ctrl);
			break;
		case 13784:
			use13784(player, ctrl);
			break;
		case 13785:
			use13785(player, ctrl);
			break;
		case 13786:
			use13786(player, ctrl);
			break;
		case 14849:
			use14849(player, ctrl);
			break;
		case 14834:
			use14834(player, ctrl);
			break;
		case 14833:
			use14833(player, ctrl);
			break;
		case 13988:
			use13988(player, ctrl);
			break;
		case 13989:
			use13989(player, ctrl);
			break;
		case 13003:
			use13003(player, ctrl);
			break;
		case 13004:
			use13004(player, ctrl);
			break;
		case 13005:
			use13005(player, ctrl);
			break;
		case 13006:
			use13006(player, ctrl);
			break;
		case 13007:
			use13007(player, ctrl);
			break;
		case 13990:
			use13990(player, ctrl);
			break;
		case 13991:
			use13991(player, ctrl);
			break;
		case 13992:
			use13992(player, ctrl);
			break;
		case 14850:
			use14850(player, ctrl);
			break;
		case 14713:
			use14713(player, ctrl);
			break;
		case 14714:
			use14714(player, ctrl);
			break;
		case 14715:
			use14715(player, ctrl);
			break;
		case 14716:
			use14716(player, ctrl);
			break;
		case 14717:
			use14717(player, ctrl);
			break;
		case 14718:
			use14718(player, ctrl);
			break;
		case 17138:
			use17138(player, ctrl);
			break;
		case 15482:
			use15482(player, ctrl);
			break;
		case 15483:
			use15483(player, ctrl);
			break;
		case 13270:
			use13270(player, ctrl);
			break;
		case 13271:
			use13271(player, ctrl);
			break;
		case 13272:
			use13272(player, ctrl);
			break;
		case 14231:
			use14231(player, ctrl);
			break;
		case 14232:
			use14232(player, ctrl);
			break;
		case 21747:
			use21747(player, ctrl);
			break;
		case 21748:
			use21748(player, ctrl);
			break;
		case 21749:
			use21749(player, ctrl);
			break;
		case 17169:
			use17169(player, ctrl);
			break;
		case 21169:
			use21169(player, ctrl);
			break;
		case 20635:
			use20635(player, ctrl);
			break;
		case 21092:
			use21092(player, ctrl);
			break;
		case 21091:
			use21091(player, ctrl);
			break;
		case 53:
			use53(player, ctrl);
			break;
		case 54:
			use54(player, ctrl);
			break;
		case 55:
			use55(player, ctrl);
			break;
		case 56:
			use56(player, ctrl);
			break;
		case 136:
			use136(player, ctrl);
			break;
		case 137:
			use137(player, ctrl);
			break;
		case 138:
			use138(player, ctrl);
			break;
		case 139:
			use139(player, ctrl);
			break;
		case 140:
			use140(player, ctrl);
			break;
		case 141:
			use141(player, ctrl);
			break;
		case 163:
			use163(player, ctrl);
			break;
		case 170:
			use170(player, ctrl);
			break;
		case 5906:
			use5906(player, ctrl);
			break;
		case 5907:
			use5907(player, ctrl);
			break;
		case 5909:
			use5909(player, ctrl);
			break;
		case 5910:
			use5910(player, ctrl);
			break;
		case 5912:
			use5912(player, ctrl);
			break;
		case 5913:
			use5913(player, ctrl);
			break;
		case 10205:
			use10205(player, ctrl);
			break;
		case 10206:
			use10206(player, ctrl);
			break;
		case 10254:
			use10254(player, ctrl);
			break;
		case 13079:
			use13079(player, ctrl);
			break;
		case 13080:
			use13080(player, ctrl);
			break;
		case 13082:
			use13082(player, ctrl);
			break;
		case 13083:
			use13083(player, ctrl);
			break;
		case 13084:
			use13084(player, ctrl);
			break;
		case 13085:
			use13085(player, ctrl);
			break;
		case 13086:
			use13086(player, ctrl);
			break;
		case 13087:
			use13087(player, ctrl);
			break;
		case 13088:
			use13088(player, ctrl);
			break;
		case 13089:
			use13089(player, ctrl);
			break;
		case 13090:
			use13090(player, ctrl);
			break;
		case 13091:
			use13091(player, ctrl);
			break;
		case 13092:
			use13092(player, ctrl);
			break;
		case 13093:
			use13093(player, ctrl);
			break;
		case 13094:
			use13094(player, ctrl);
			break;
		case 13095:
			use13095(player, ctrl);
			break;
		case 13096:
			use13096(player, ctrl);
			break;
		case 13097:
			use13097(player, ctrl);
			break;
		case 13098:
			use13098(player, ctrl);
			break;
		case 21225: // by CosMOsiS start line
			use21225(player, ctrl);
			break;
		case 21226:
			use21226(player, ctrl);
			break;
		case 21229:
			use21229(player, ctrl);
			break;
		case 21230:
			use21230(player, ctrl);
			break;
		case 21233:
			use21233(player, ctrl);
			break;
		case 21234:
			use21234(player, ctrl);
			break;
		case 21237:
			use21237(player, ctrl);
			break;
		case 21238:
			use21238(player, ctrl);
			break;
		case 21240:
			use21240(player, ctrl);
			break;
		case 15404:
			use15404(player, ctrl);
			break;
		case 15405:
			use15405(player, ctrl);
			break;
		case 15406:
			use15406(player, ctrl);
			break;
		case 15407:
			use15407(player, ctrl);
			break;
		case 15408:
			use15408(player, ctrl);
			break;
		case 15409:
			use15409(player, ctrl);
			break;
		case 15410:
			use15410(player, ctrl);
			break;
		case 15411:
			use15411(player, ctrl);
			break;
		case 15412:
			use15412(player, ctrl);
			break;
		case 15413:
			use15413(player, ctrl);
			break;
		case 15414:
			use15414(player, ctrl);
			break;
		case 15415:
			use15415(player, ctrl);
			break;
		case 15416:
			use15416(player, ctrl);
			break;
		case 15417:
			use15417(player, ctrl);
			break;
		case 15425:
			use15425(player, ctrl);
			break;
		case 15434:
			use15434(player, ctrl);
			break;
		case 15435:
			use15435(player, ctrl);
			break;
		case 15437:
			use15437(player, ctrl);
			break;
		case 15454:
			use15454(player, ctrl);
			break;
		case 15455:
			use15455(player, ctrl);
			break;
		case 15456:
			use15456(player, ctrl);
			break;
		case 15457:
			use15457(player, ctrl);
			break;
		case 15458:
			use15458(player, ctrl);
			break;
		case 15459:
			use15459(player, ctrl);
			break;
		case 15460:
			use15460(player, ctrl);
			break;
		case 15462:
			use15462(player, ctrl);
			break;
		case 15465:
			use15465(player, ctrl);
			break;
		case 15466:
			use15466(player, ctrl);
			break;
		case 15468:
			use15468(player, ctrl);
			break;
		case 21421:
			use21421(player, ctrl);
			break;
		case 21422:
			use21422(player, ctrl);
			break;
		case 21241:
			use21241(player, ctrl);
			break; // by CosMOsiS end line
		case 20636:
			use20636(player, ctrl);
			break;
		case 20637:
			use20637(player, ctrl);
			break;
		case 20638:
			use20638(player, ctrl);
			break; // by SmokiMo end line
		case 30276: // Soulshot Pack (R-Grade)
			use30276(player, ctrl);
			break;
		case 30277: // Soulshot Large Pack (R-Grade)
			use30277(player, ctrl);
			break;
		case 30278: // Spiritshot Pack (R-Grade)
			use30278(player, ctrl);
			break;
		case 30279: // Spiritshot Large Pack (R-Grade)
			use30279(player, ctrl);
			break;
		case 30280: // Blessed Spiritshot Pack (R-Grade)
			use30280(player, ctrl);
			break;
		case 30281: // Blessed Spiritshot Large Pack (R-Grade)
			use30281(player, ctrl);
			break;
		case 30282: // Orichalcum Quiver
			use30282(player, ctrl);
			break;
		case 30283: // Orichalcum Bolt Container
			use30283(player, ctrl);
			break;
		case 30284: // Requiem Weapon Box
			use30284(player, ctrl);
			break;
		case 30285: // Immortal Heavy Armor Box
			use30285(player, ctrl);
			break;
		case 30286: // Immortal Light Armor Box
			use30286(player, ctrl);
			break;
		case 30287: // Immortal Robe Armor Box
			use30287(player, ctrl);
			break;
		case 30288: // Apocalypse Weapon Box
			use30288(player, ctrl);
			break;
		case 30289: // Twilight Heavy Armor Box
			use30289(player, ctrl);
			break;
		case 30290: // Twilight Light Armor Box
			use30290(player, ctrl);
			break;
		case 30291: // Twilight Robe Armor Box
			use30291(player, ctrl);
			break;
		case 30292: // Immortal Accessory Box
			use30292(player, ctrl);
			break;
		case 30293: // Twilight Accessory Box
			use30293(player, ctrl);
			break;
		case 22578: // Set weapons C 30-days
			use22578(player, ctrl);
			break;
		case 22579: // Set weapons B 30-days
			use22579(player, ctrl);
			break;
		case 22580: // Set weapons A 30-days
			use22580(player, ctrl);
			break;
		case 22581: // Set weapons S 90-days
			use22581(player, ctrl);
			break;
		case 22202: // Set weapons S80 (Dynasty) 90-days
			use22202(player, ctrl);
			break;
		case 22583: // Set armor C 30-days
			use22583(player, ctrl);
			break;
		case 22584: // Set armor B 30-days
			use22584(player, ctrl);
			break;
		case 22585: // Set armor A 30-days
			use22585(player, ctrl);
			break;
		case 22586: // Set armor S 90-days
			use22586(player, ctrl);
			break;
		case 21781: // Set armor S heavy 90-days
			use21781(player, ctrl);
			break;
		case 21786: // Set armor S light 90-days
			use21786(player, ctrl);
			break;
		case 21792: // Set armor S robe 90-days
			use21792(player, ctrl);
			break;
		case 21799: // Set armor S80 heavy (Dynasty) 90-days
			use21799(player, ctrl);
			break;
		case 21805: // Set armor S80 light (Dynasty) 90-days
			use21805(player, ctrl);
			break;
		case 21812: // Set armor S80 robe (Dynasty) 90-days
			use21812(player, ctrl);
			break;
		case 21816: // Set jewels S 90-days
			use21816(player, ctrl);
			break;
		case 21820: // Set jewels S80 (Dynasty) 90-days
			use21820(player, ctrl);
			break;
		case 22571: // Agathion Pack - 7-Day Dimensional Item Pack. Box  containing one of the following Agathion (7-Day) at random: Baby Panda,  Griffin, Phoenix or One-Eyed Bat Drove.
			use22571(player, ctrl);
			break;
		case 33465:
			use33465(player, ctrl);
			break;
		case 34927:
			use34927(player, ctrl);
			break;
		case 34928:
			use34928(player, ctrl);
			break;
		case 34929:
			use34929(player, ctrl);
			break;
		case 34930:
			use34930(player, ctrl);
			break;
		case 34931:
			use34931(player, ctrl);
			break;
		case 34932:	
			use34932(player, ctrl);
			break;
		case 34855:
			use34855(player, ctrl);
			break;
		case 30374:
			use30374(player, ctrl);
			break;
		case 30388:
			use30388(player, ctrl);
			break;
		case 30389:
			use30389(player, ctrl);
			break;
		case 30390:
			use30390(player, ctrl);
			break;
		case 30391:
			use30391(player, ctrl);
			break;
		case 34955:
			use34955(player, ctrl);
			break;	
		default:
			return false;
		}

		return true;
	}

	private void use17086(Player player, boolean ctrl)
	{
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9201, 1);
	}

	private void use17085(Player player, boolean ctrl)
	{
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9194, 1);
	}

	private void use17084(Player player, boolean ctrl)
	{
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9187, 1);
	}

	private void use17083(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10613, 1);
	}

	private void use17082(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10296, 1);
	}

	private void use17066(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17067, 60);
		Functions.addItem(player, 17068, 15);
		Functions.addItem(player, 17062, 1);
		Functions.addItem(player, 17064, 1);
	}

	private void use17048(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17049, 1);
	}

	private void use17047(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17044, 1);
		Functions.addItem(player, 17045, 1);
		Functions.addItem(player, 17046, 1);
		Functions.addItem(player, 17050, 1);
		Functions.addItem(player, 17061, 1);
	}

	private void use17046(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17057, 1);
		Functions.addItem(player, 17058, 1);
		Functions.addItem(player, 17059, 1);
		Functions.addItem(player, 17060, 1);
	}

	private void use17045(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17054, 1);
		Functions.addItem(player, 17055, 1);
		Functions.addItem(player, 17056, 1);
	}

	private void use17044(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17051, 1);
		Functions.addItem(player, 17052, 1);
		Functions.addItem(player, 17053, 1);
	}

	private void use17043(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17047, 1);
	}

	private void use17042(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17046, 1);
	}

	private void use17041(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17045, 1);
	}

	private void use17040(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17044, 1);
	}

	private void use17032(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16852, 1);
	}

	private void use17031(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17033, 1);
		Functions.addItem(player, 9552, 1);
		Functions.addItem(player, 9553, 1);
		Functions.addItem(player, 9554, 1);
		Functions.addItem(player, 9555, 1);
		Functions.addItem(player, 9556, 1);
		Functions.addItem(player, 9557, 1);
		Functions.addItem(player, 14055, 3);
	}

	private void use17029(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9409, 1);
	}

	private void use17028(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9208, 1);
	}

	private void use17027(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10616, 1);
	}

	private void use17026(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 8919, 1);
	}

	private void use17025(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14559, 1);
	}

	private void use17024(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15440, 5);
	}

	private void use17023(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13235, 1);
	}

	private void use17022(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13234, 1);
	}

	private void use17021(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14239, 1);
		Functions.addItem(player, 13082, 1);
		Functions.addItem(player, 13081, 1);
	}

	private void use17020(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14739, 1);
	}

	private void use17019(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13121, 1);
		Functions.addItem(player, 14231, 1);
		Functions.addItem(player, 14232, 1);
	}

	private void use17002(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16864, 1);
		Functions.addItem(player, 16863, 2);
		Functions.addItem(player, 16865, 2);
	}

	private void use17001(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16861, 1);
		Functions.addItem(player, 16860, 2);
		Functions.addItem(player, 16862, 2);
	}

	private void use17000(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16858, 1);
		Functions.addItem(player, 16857, 2);
		Functions.addItem(player, 16859, 2);
	}

	private void use14768(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14770, 1);
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14774, 1, 1 }

		};

		double chances[] = new double[] {
		// chance
		80.0

		};

		capsulate(player, items, chances);
	}

	private void use14767(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 14769, 1, 1 }, { 14771, 1, 1 }, { 14769, 1, 1 }, { 14775, 1, 1 }

		};

		double chances[] = new double[] {
				// chance
				50.0, 50.0, 50.0, 50.0

		};

		capsulate(player, items, chances);
	}

	private void use14766(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 14769, 5, 5 }, { 14772, 1, 1 }, { 14769, 5, 5 }, { 14773, 1, 1 }, { 14769, 5, 5 }, { 14776, 1, 1 }

		};

		double chances[] = new double[] {
				// chance
				10.0, 10.0, 40.0, 40.0, 50.0, 50.0

		};

		capsulate(player, items, chances);
	}

	private void use14740(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14722, 60);
		Functions.addItem(player, 14723, 15);
		Functions.addItem(player, 14724, 1);
		Functions.addItem(player, 14725, 1);
	}

	private void use14729(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 14733, 1, 1 }, { 14737, 1, 1 }, { 14736, 1, 1 }, { 14735, 1, 1 }, { 14734, 1, 1 }, { 14731, 1, 1 },
				{ 14730, 1, 1 }

		};

		double chances[] = new double[] {
				// chance
				35.1, 22.1, 22.1, 10.1, 6.1, 3.0, 1.5

		};

		capsulate(player, items, chances);
	}

	private void use14719(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14675, 1);
	}

	private void use14291(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14074, 1);
	}

	private void use14290(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14074, 2);
	}

	private void use14289(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14068, 3);
	}

	private void use14288(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14055, 3);
	}

	private void use14287(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12371, 1);
	}

	private void use14286(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12370, 1);
	}

	private void use14285(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12369, 1);
	}

	private void use14284(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12368, 1);
	}

	private void use14283(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12367, 1);
	}

	private void use14282(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12366, 1);
	}

	private void use14281(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12365, 1);
	}

	private void use14280(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12364, 1);
	}

	private void use14279(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12363, 1);
	}

	private void use14278(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12362, 1);
	}

	private void use14277(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14103, 1);
	}

	private void use14276(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14102, 1);
	}

	private void use14275(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14101, 1);
	}

	private void use14274(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14100, 1);
	}

	private void use14273(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14099, 1);
	}

	private void use14272(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14098, 1);
	}

	private void use14271(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14097, 1);
	}

	private void use14270(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14096, 1);
	}

	private void use14266(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14092, 1);
	}

	private void use14265(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14091, 1);
	}

	private void use14264(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14090, 1);
	}

	private void use14263(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14089, 1);
	}

	private void use14262(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14088, 1);
	}

	private void use14261(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14087, 1);
	}

	private void use14260(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14086, 1);
	}

	private void use14259(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14085, 1);
	}

	private void use14258(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14084, 1);
	}

	private void use14257(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14083, 1);
	}

	private void use14256(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14082, 1);
	}

	private void use14255(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14081, 1);
	}

	private void use14254(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14080, 1);
	}

	private void use14253(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14079, 1);
	}

	private void use14252(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14078, 1);
	}

	private void use14251(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14077, 1);
	}

	private void use14250(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14076, 1);
	}

	private void use14249(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14075, 1);
	}

	private void use14248(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14065, 1);
	}

	private void use14247(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14073, 1);
	}

	private void use14246(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14072, 1);
	}

	private void use14245(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14071, 1);
	}

	private void use14244(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14070, 1);
	}

	private void use14243(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14069, 1);
	}

	private void use14242(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14068, 1);
	}

	private void use14241(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14067, 1);
	}

	private void use14240(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14066, 1);
	}

	private void use14239(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14065, 2);
	}

	private void use14238(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14060, 1);
	}

	private void use14237(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14059, 1);
	}

	private void use14236(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14058, 1);
	}

	private void use14235(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14057, 1);
	}

	private void use14234(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14056, 1);
	}

	private void use14233(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14055, 1);
	}

	private void use14230(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13026, 1);
	}

	private void use14229(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13025, 1);
	}

	private void use14228(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13024, 1);
	}

	private void use20999(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20982, 1);
	}

	private void use20998(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20981, 1);
	}

	private void use20997(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20975, 1);
	}

	private void use20996(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20974, 1);
	}

	private void use20995(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20973, 1);
	}

	private void use20994(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20970, 1);
	}

	private void use20969(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20968, 1);
	}

	private void use20967(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20937, 1);
	}

	private void use20966(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20936, 1);
	}

	private void use20965(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20935, 1);
	}

	private void use20964(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20934, 1);
	}

	private void use20963(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20933, 1);
	}

	private void use20962(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20932, 1);
	}

	private void use20961(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20931, 1);
	}

	private void use20960(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20930, 1);
	}

	private void use20959(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20929, 1);
	}

	private void use20957(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20956, 1);
	}

	private void use20953(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20945, 1);
	}

	private void use20952(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20944, 1);
	}

	private void use20951(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20943, 1);
	}

	private void use20950(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20942, 1);
	}

	private void use20949(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20941, 1);
	}

	private void use20948(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20940, 1);
	}

	private void use20947(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20939, 1);
	}

	private void use20946(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20938, 1);
	}

	private void use20902(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20205, 1);
	}

	private void use20901(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20900, 1);
		Functions.addItem(player, 14611, 1);
	}

	private void use17168(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 9627, 1);
	}

	private void use17167(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 959, 1);
	}

	private void use17166(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14052, 10);
	}

	private void use17165(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14744, 5);
	}

	private void use17164(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 17163, 3);
	}

	private void use20097(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 20098, 1, 1 }, { 20099, 1, 1 } };

		double chances[] = new double[] {
				// chance
				20.00, 80.00 };

		capsulate(player, items, chances);
	}

	private void use20096(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20097, 1);
	}

	private void use20092(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20093, 1);
	}

	private void use20080(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20082, 1);
	}

	private void use20079(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20081, 1);
	}

	private void use20062(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20033, 1);
	}

	private void use20058(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20029, 1);
	}

	private void use20057(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20028, 1);
	}

	private void use20056(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20027, 1);
	}

	private void use20055(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20026, 1);
	}

	private void use20054(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20025, 1);
	}

	private void use20053(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20024, 1);
	}

	private void use20052(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20023, 1);
	}

	private void use20051(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20022, 1);
	}

	private void use20050(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20021, 1);
	}

	private void use20049(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20020, 1);
	}

	private void use20048(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20019, 1);
	}

	private void use20047(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20018, 1);
	}

	private void use20046(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20017, 1);
	}

	private void use20045(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20016, 1);
	}

	private void use20599(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20601, 1);
	}

	private void use20598(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20600, 1);
	}

	private void use20590(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20594, 1);
	}

	private void use20589(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20593, 1);
	}

	private void use20588(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20592, 1);
	}

	private void use20587(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20591, 1);
	}

	private void use20581(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20572, 1);
	}

	private void use20580(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20571, 1);
	}

	private void use20579(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20570, 1);
	}

	private void use20578(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20569, 1);
	}

	private void use20577(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20568, 1);
	}

	private void use20576(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20567, 1);
	}

	private void use20574(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20341, 1);
		Functions.addItem(player, 20392, 4);

	}

	private void use20566(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22066, 1);
	}

	private void use20565(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20352, 1);
	}

	private void use20564(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20351, 1);
	}

	private void use20563(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20350, 1);
	}

	private void use20562(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20349, 1);
	}

	private void use20561(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20348, 1);
	}

	private void use20560(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20347, 1);
	}

	private void use20559(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20346, 1);
	}

	private void use20558(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20345, 1);
	}

	private void use20557(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20344, 1);
	}

	private void use20556(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20343, 1);
	}

	private void use20555(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20342, 1);
	}

	private void use20554(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20341, 1);
	}

	private void use20553(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20340, 1);
	}

	private void use20552(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20339, 1);
	}

	private void use20551(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20338, 1);
	}

	private void use20550(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20337, 1);
	}

	private void use20549(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20336, 1);
	}

	private void use20548(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20335, 1);
	}

	private void use20544(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20539, 1);
	}

	private void use20543(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20538, 1);
	}

	private void use20542(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20537, 1);
	}

	private void use20541(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20536, 1);
	}

	private void use20540(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20535, 1);
	}

	private void use20514(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20504, 1);
	}

	private void use20513(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20503, 1);
	}

	private void use20512(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20502, 1);
	}

	private void use20511(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20501, 1);
	}

	private void use20510(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20500, 1);
	}

	private void use20509(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20499, 1);
	}

	private void use20508(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20498, 1);
	}

	private void use20507(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20497, 1);
	}

	private void use20506(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20496, 1);
	}

	private void use20505(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20495, 1);
	}

	private void use20898(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20897, 1);
	}

	private void use20869(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20867, 1);
		Functions.addItem(player, 20868, 3);

	}

	private void use20803(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20781, 1);
	}

	private void use20802(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20780, 1);
	}

	private void use20801(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20779, 1);
	}

	private void use20800(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20778, 1);
	}

	private void use20699(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20673, 1);
	}

	private void use20698(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20672, 1);
	}

	private void use20697(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20671, 1);
	}

	private void use20696(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20670, 1);
	}

	private void use20695(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20669, 1);
	}

	private void use20694(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20668, 1);
	}

	private void use20693(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20667, 1);
	}

	private void use20692(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20666, 1);
	}

	private void use20691(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20665, 1);
	}

	private void use20690(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20664, 1);
	}

	private void use20689(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20663, 1);
	}

	private void use20688(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20662, 1);
	}

	private void use20687(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20661, 1);
	}

	private void use20686(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20660, 1);
	}

	private void use20685(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20659, 1);
	}

	private void use20684(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20658, 1);
	}

	private void use20683(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20657, 1);
	}

	private void use20682(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20656, 1);
	}

	private void use20681(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20655, 1);
	}

	private void use20680(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20654, 1);
	}

	private void use20632(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20634, 1);
	}

	private void use20631(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20633, 1);
	}

	private void use20627(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20628, 1);
	}

	private void use20625(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20626, 1);
	}

	private void use20624(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20603, 100);
	}

	private void use20623(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20602, 100);
		Functions.addItem(player, 20603, 100);
	}

	private void use20620(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20622, 1);
	}

	private void use20619(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20621, 1);
	}

	private void use20612(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20618, 1);
	}

	private void use20611(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20617, 1);
	}

	private void use20610(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20616, 1);
	}

	private void use20609(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20615, 1);
	}

	private void use20608(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20614, 1);
	}

	private void use20607(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20613, 1);
	}

	private void use14638(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 57, 697000, 697000 }, { 57, 418200, 418200 }, { 9628, 2, 2 }, { 9630, 2, 2 }, { 9629, 2, 2 }, };

		double chances[] = new double[] {
				// chance
				60.00, 30.00, 30.00, 30.00, 30.00 };

		capsulate(player, items, chances);
	}

	private void use14637(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 57, 626000, 626000 }, { 57, 438200, 438200 }, { 9629, 4, 4 } };

		double chances[] = new double[] {
				// chance
				70.00, 30.00, 30.00 };

		capsulate(player, items, chances);
	}

	private void use14636(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 57, 605000, 605000 }, { 57, 484000, 484000 }, { 9630, 3, 3 } };

		double chances[] = new double[] {
				// chance
				80.00, 20.00, 20.00 };

		capsulate(player, items, chances);
	}

	private void use14635(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 57, 583000, 583000 }, { 57, 524700, 524700 }, { 9628, 2, 2 } };

		double chances[] = new double[] {
				// chance
				90.00, 10.00, 10.00 };

		capsulate(player, items, chances);
	}

	private void use20492(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20447, 1);
	}

	private void use20491(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20446, 1);
	}

	private void use20490(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20445, 1);
	}

	private void use20489(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20444, 1);
	}

	private void use20488(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20443, 1);
	}

	private void use20487(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20442, 1);
	}

	private void use20486(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20441, 1);
	}

	private void use20485(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20440, 1);
	}

	private void use20484(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20439, 1);
	}

	private void use20483(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20438, 1);
	}

	private void use20482(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20437, 1);
	}

	private void use20481(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20436, 1);
	}

	private void use20480(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20435, 1);
	}

	private void use20479(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20434, 1);
	}

	private void use20478(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20433, 1);
	}

	private void use20477(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20432, 1);
	}

	private void use20476(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20431, 1);
	}

	private void use20475(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20430, 1);
	}

	private void use20474(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20429, 1);
	}

	private void use20473(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20428, 1);
	}

	private void use20472(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20427, 1);
	}

	private void use20471(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20426, 1);
	}

	private void use20470(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20425, 1);
	}

	private void use20469(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20424, 1);
	}

	private void use20468(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20423, 1);
	}

	private void use20467(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20422, 1);
	}

	private void use20466(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20421, 1);
	}

	private void use20465(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20420, 1);
	}

	private void use20464(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20419, 1);
	}

	private void use20463(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20418, 1);
	}

	private void use20462(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20417, 1);
	}

	private void use20461(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20416, 1);
	}

	private void use20460(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20415, 1);
	}

	private void use20459(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20414, 1);
	}

	private void use20458(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20413, 1);
	}

	private void use20457(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20412, 1);
	}

	private void use20456(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20411, 1);
	}

	private void use20455(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20410, 1);
	}

	private void use20454(Player player, boolean ctrl) {
		Functions.addItem(player, 20409, 1);
	}

	private void use20453(Player player, boolean ctrl) {
		Functions.addItem(player, 20408, 1);
	}

	private void use20452(Player player, boolean ctrl) {
		Functions.addItem(player, 20407, 1);
	}

	private void use20451(Player player, boolean ctrl) {
		Functions.addItem(player, 20406, 1);
	}

	private void use20450(Player player, boolean ctrl) {
		Functions.addItem(player, 20405, 1);
	}

	private void use20404(Player player, boolean ctrl) {
		Functions.addItem(player, 20401, 1);
	}

	private void use20403(Player player, boolean ctrl) {
		Functions.addItem(player, 20400, 1);
	}

	private void use20402(Player player, boolean ctrl) {
		Functions.addItem(player, 20399, 1);
	}

	private void use21325(Player player, boolean ctrl) {
		Functions.addItem(player, 21331, 1);
	}
	
	private void use14677(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 14700, 3, 3 }, { 14701, 3, 3 }, { 9625, 1, 1 }, { 9626, 1, 1 }, { 14716, 1, 1 }, { 14717, 1, 1 },
				{ 14718, 1, 1 }, { 14709, 1, 1 }, { 14710, 1, 1 }, { 14711, 1, 1 }, { 14704, 1, 1 }, { 14705, 1, 1 },
				{ 14706, 1, 1 }, { 14678, 1, 1 }, { 14679, 1, 1 }, { 14680, 1, 1 }, { 14681, 1, 1 } };

		double chances[] = new double[] {
				// chance
				40.0, 26.0, 4.0, 4.0, 6.0, 6.0, 6.0, 3.9, 1.95, 0.15, 0.65, 0.325, 0.025, 0.6, 0.26, 0.13, 0.01 };

		capsulate(player, items, chances);
	}

	private void use14676(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 14700, 3, 3 }, { 14701, 3, 3 }, { 9625, 1, 1 }, { 9626, 1, 1 }, { 14716, 1, 1 }, { 14717, 1, 1 },
				{ 14718, 1, 1 }, { 14709, 1, 1 }, { 14710, 1, 1 }, { 14711, 1, 1 }, { 14704, 1, 1 }, { 14705, 1, 1 },
				{ 14706, 1, 1 }, { 14678, 1, 1 }, { 14679, 1, 1 }, { 14680, 1, 1 }, { 14681, 1, 1 } };

		double chances[] = new double[] {
				// chance
				40.0, 26.0, 4.0, 4.0, 6.0, 6.0, 6.0, 3.9, 1.95, 0.15, 0.65, 0.325, 0.025, 0.6, 0.26, 0.13, 0.01 };

		capsulate(player, items, chances);
	}

	private void use14663(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 2582, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14662(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 7897, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14661(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 7891, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14660(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 7888, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14659(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 5286, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14658(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 2503, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14657(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 299, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14656(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 286, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14655(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 266, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14654(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 228, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14653(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 206, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14652(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 205, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14651(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 204, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14650(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 135, 1);
		Functions.addItem(player, 13421, 3);
	}

	private void use14649(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 2499, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14648(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 297, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14647(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 280, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14646(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 262, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14645(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 225, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14644(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 190, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14643(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 189, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14642(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 188, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14641(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 187, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14640(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 159, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use14639(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 70, 1);
		Functions.addItem(player, 13421, 5);
	}

	private void use20296(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20313, 1);
	}

	private void use20295(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20312, 1);
	}

	private void use20294(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20311, 1);
	}

	private void use20293(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20310, 1);
	}

	private void use20292(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20309, 1);
	}

	private void use20291(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20308, 1);
	}

	private void use20290(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20307, 1);
	}

	private void use20289(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20306, 1);
	}

	private void use20288(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20305, 1);
	}

	private void use20287(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20304, 1);
	}

	private void use20286(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20303, 1);
	}

	private void use20285(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20302, 1);
	}

	private void use20284(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20301, 1);
	}

	private void use20283(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20300, 1);
	}

	private void use20282(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20299, 1);
	}

	private void use20281(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20298, 1);
	}

	private void use20280(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20297, 1);
	}

	private void use20277(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 20268, 1, 1 }, { 20269, 1, 1 }, { 20267, 1, 1 } };

		double chances[] = new double[] {
				// chance
				33.00, 33.00, 34.00 };

		capsulate(player, items, chances);
	}

	private void use15278(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14739, 1);
		int items[][] = new int[][] {
				// itemId, min, max
				{ 9156, 1, 1 }, { 9157, 1, 1 }, { 14705, 1, 1 }, { 14706, 1, 1 }, };

		double chances[] = new double[] {
				// chance
				5.0, 5.0, 0.025, 0.025

		};

		capsulate(player, items, chances);
	}

	private void use15277(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14976, 1);
		Functions.addItem(player, 14975, 2);
		Functions.addItem(player, 14977, 2);
	}

	private void use15276(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14973, 1);
		Functions.addItem(player, 14972, 2);
		Functions.addItem(player, 14974, 2);
	}

	private void use15275(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14970, 1);
		Functions.addItem(player, 14969, 2);
		Functions.addItem(player, 14971, 2);
	}

	private void use15274(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14966, 2);
		Functions.addItem(player, 14967, 1);
		Functions.addItem(player, 14968, 2);
	}

	private void use15273(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15090, 1);
		Functions.addItem(player, 15089, 2);
		Functions.addItem(player, 15091, 2);
	}

	private void use15272(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15087, 1);
		Functions.addItem(player, 15086, 2);
		Functions.addItem(player, 15088, 2);
	}

	private void use15271(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15084, 1);
		Functions.addItem(player, 15083, 2);
		Functions.addItem(player, 15085, 2);
	}

	private void use15270(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15080, 2);
		Functions.addItem(player, 15081, 1);
		Functions.addItem(player, 15082, 2);
	}

	private void use15269(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15076, 1);
		Functions.addItem(player, 15077, 1);
		Functions.addItem(player, 15078, 1);
		Functions.addItem(player, 15079, 1);
	}

	private void use15268(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15072, 1);
		Functions.addItem(player, 15073, 1);
		Functions.addItem(player, 15074, 1);
		Functions.addItem(player, 15075, 1);
	}

	private void use15267(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15068, 1);
		Functions.addItem(player, 15069, 1);
		Functions.addItem(player, 15070, 1);
		Functions.addItem(player, 15071, 1);
	}

	private void use15266(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15064, 1);
		Functions.addItem(player, 15065, 1);
		Functions.addItem(player, 15066, 1);
		Functions.addItem(player, 15067, 1);
	}

	private void use15265(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15060, 1);
		Functions.addItem(player, 15061, 1);
		Functions.addItem(player, 15062, 1);
		Functions.addItem(player, 15063, 1);
	}

	private void use15264(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15056, 1);
		Functions.addItem(player, 15057, 1);
		Functions.addItem(player, 15058, 1);
		Functions.addItem(player, 15059, 1);
	}

	private void use15263(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15052, 1);
		Functions.addItem(player, 15053, 1);
		Functions.addItem(player, 15054, 1);
		Functions.addItem(player, 15055, 1);
	}

	private void use15262(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15048, 1);
		Functions.addItem(player, 15049, 1);
		Functions.addItem(player, 15050, 1);
		Functions.addItem(player, 15051, 1);
	}

	private void use15261(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15044, 1);
		Functions.addItem(player, 15045, 1);
		Functions.addItem(player, 15046, 1);
		Functions.addItem(player, 15047, 1);
	}

	private void use15260(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15040, 1);
		Functions.addItem(player, 15041, 1);
		Functions.addItem(player, 15042, 1);
		Functions.addItem(player, 15043, 1);
	}

	private void use15259(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15036, 1);
		Functions.addItem(player, 15037, 1);
		Functions.addItem(player, 15038, 1);
		Functions.addItem(player, 15039, 1);
	}

	private void use15258(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15032, 1);
		Functions.addItem(player, 15033, 1);
		Functions.addItem(player, 15034, 1);
		Functions.addItem(player, 15035, 1);
	}

	private void use15257(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14992, 1);
		Functions.addItem(player, 14993, 1);
		Functions.addItem(player, 14994, 1);
		Functions.addItem(player, 14995, 1);
		Functions.addItem(player, 15023, 1);
	}

	private void use15256(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14988, 1);
		Functions.addItem(player, 14989, 1);
		Functions.addItem(player, 14990, 1);
		Functions.addItem(player, 14991, 1);
		Functions.addItem(player, 15012, 1);
	}

	private void use15255(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14983, 1);
		Functions.addItem(player, 14984, 1);
		Functions.addItem(player, 14985, 1);
		Functions.addItem(player, 14986, 1);
		Functions.addItem(player, 14987, 1);
	}

	private void use15254(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14978, 1);
		Functions.addItem(player, 14979, 1);
		Functions.addItem(player, 14980, 1);
		Functions.addItem(player, 14981, 1);
		Functions.addItem(player, 14982, 1);
	}

	private void use15253(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15017, 1);
		Functions.addItem(player, 15018, 1);
		Functions.addItem(player, 15019, 1);
		Functions.addItem(player, 15020, 1);
		Functions.addItem(player, 15023, 1);
	}

	private void use15252(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15008, 1);
		Functions.addItem(player, 15009, 1);
		Functions.addItem(player, 15010, 1);
		Functions.addItem(player, 15011, 1);
		Functions.addItem(player, 15012, 1);
	}

	private void use15251(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15000, 1);
		Functions.addItem(player, 15001, 1);
		Functions.addItem(player, 15003, 1);
		Functions.addItem(player, 15005, 1);
	}

	private void use15250(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 14996, 1);
		Functions.addItem(player, 14997, 1);
		Functions.addItem(player, 14998, 1);
		Functions.addItem(player, 14999, 1);
		Functions.addItem(player, 14979, 1);
	}

	private void use15249(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15021, 1);
		Functions.addItem(player, 15022, 1);
		Functions.addItem(player, 15023, 1);
		Functions.addItem(player, 15024, 1);
		Functions.addItem(player, 15025, 1);
		Functions.addItem(player, 15026, 1);
	}

	private void use15248(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15012, 1);
		Functions.addItem(player, 15013, 1);
		Functions.addItem(player, 15014, 1);
		Functions.addItem(player, 15015, 1);
		Functions.addItem(player, 15016, 1);
	}

	private void use15247(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15001, 1);
		Functions.addItem(player, 15002, 1);
		Functions.addItem(player, 15004, 1);
		Functions.addItem(player, 15006, 1);
		Functions.addItem(player, 15007, 1);
	}

	private void use15246(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15027, 1);
		Functions.addItem(player, 15028, 1);
		Functions.addItem(player, 15029, 1);
		Functions.addItem(player, 15030, 1);
		Functions.addItem(player, 15031, 1);
	}

	private void use15245(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15190, 1);
		Functions.addItem(player, 15191, 1);
		Functions.addItem(player, 15192, 1);
		Functions.addItem(player, 15193, 1);
	}

	private void use15244(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15186, 1);
		Functions.addItem(player, 15187, 1);
		Functions.addItem(player, 15188, 1);
		Functions.addItem(player, 15189, 1);
	}

	private void use15243(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15182, 1);
		Functions.addItem(player, 15183, 1);
		Functions.addItem(player, 15184, 1);
		Functions.addItem(player, 15185, 1);
	}

	private void use15242(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15178, 1);
		Functions.addItem(player, 15179, 1);
		Functions.addItem(player, 15180, 1);
		Functions.addItem(player, 15181, 1);
	}

	private void use15241(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15174, 1);
		Functions.addItem(player, 15175, 1);
		Functions.addItem(player, 15176, 1);
		Functions.addItem(player, 15177, 1);
	}

	private void use15240(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15170, 1);
		Functions.addItem(player, 15171, 1);
		Functions.addItem(player, 15172, 1);
		Functions.addItem(player, 15173, 1);
	}

	private void use15239(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15166, 1);
		Functions.addItem(player, 15167, 1);
		Functions.addItem(player, 15168, 1);
		Functions.addItem(player, 15169, 1);
	}

	private void use15238(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15162, 1);
		Functions.addItem(player, 15163, 1);
		Functions.addItem(player, 15164, 1);
		Functions.addItem(player, 15165, 1);
	}

	private void use15237(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15158, 1);
		Functions.addItem(player, 15159, 1);
		Functions.addItem(player, 15160, 1);
		Functions.addItem(player, 15161, 1);
	}

	private void use15236(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15154, 1);
		Functions.addItem(player, 15155, 1);
		Functions.addItem(player, 15156, 1);
		Functions.addItem(player, 15157, 1);
	}

	private void use15235(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15150, 1);
		Functions.addItem(player, 15151, 1);
		Functions.addItem(player, 15152, 1);
		Functions.addItem(player, 15153, 1);
	}

	private void use15234(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15146, 1);
		Functions.addItem(player, 15147, 1);
		Functions.addItem(player, 15148, 1);
		Functions.addItem(player, 15149, 1);
	}

	private void use15233(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15106, 1);
		Functions.addItem(player, 15107, 1);
		Functions.addItem(player, 15108, 1);
		Functions.addItem(player, 15109, 1);
		Functions.addItem(player, 15137, 1);
	}

	private void use15232(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15102, 1);
		Functions.addItem(player, 15103, 1);
		Functions.addItem(player, 15104, 1);
		Functions.addItem(player, 15105, 1);
		Functions.addItem(player, 15126, 1);
	}

	private void use15231(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15097, 1);
		Functions.addItem(player, 15098, 1);
		Functions.addItem(player, 15099, 1);
		Functions.addItem(player, 15100, 1);
		Functions.addItem(player, 15101, 1);
	}

	private void use15230(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15092, 1);
		Functions.addItem(player, 15093, 1);
		Functions.addItem(player, 15094, 1);
		Functions.addItem(player, 15095, 1);
		Functions.addItem(player, 15096, 1);
	}

	private void use15229(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15131, 1);
		Functions.addItem(player, 15132, 1);
		Functions.addItem(player, 15133, 1);
		Functions.addItem(player, 15134, 1);
		Functions.addItem(player, 15137, 1);
	}

	private void use15228(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15122, 1);
		Functions.addItem(player, 15123, 1);
		Functions.addItem(player, 15124, 1);
		Functions.addItem(player, 15125, 1);
		Functions.addItem(player, 15126, 1);
	}

	private void use15227(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15114, 1);
		Functions.addItem(player, 15115, 1);
		Functions.addItem(player, 15117, 1);
		Functions.addItem(player, 15119, 1);
	}

	private void use15226(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15110, 1);
		Functions.addItem(player, 15111, 1);
		Functions.addItem(player, 15112, 1);
		Functions.addItem(player, 15113, 1);
		Functions.addItem(player, 15093, 1);
	}

	private void use15225(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15135, 1);
		Functions.addItem(player, 15136, 1);
		Functions.addItem(player, 15137, 1);
		Functions.addItem(player, 15138, 1);
		Functions.addItem(player, 15139, 1);
		Functions.addItem(player, 15140, 1);
	}

	private void use15224(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15126, 1);
		Functions.addItem(player, 15127, 1);
		Functions.addItem(player, 15128, 1);
		Functions.addItem(player, 15129, 1);
		Functions.addItem(player, 15130, 1);
	}

	private void use15223(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15115, 1);
		Functions.addItem(player, 15116, 1);
		Functions.addItem(player, 15118, 1);
		Functions.addItem(player, 15120, 1);
		Functions.addItem(player, 15121, 1);
	}

	private void use15222(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15141, 1);
		Functions.addItem(player, 15142, 1);
		Functions.addItem(player, 15143, 1);
		Functions.addItem(player, 15144, 1);
		Functions.addItem(player, 15145, 1);
	}

	private void use15219(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15208, 1);
		Functions.addItem(player, 15220, 1);
		Functions.addItem(player, 14065, 1);
		Functions.addItem(player, 15211, 1);
		Functions.addItem(player, 15279, 10);
	}

	private void use15218(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15219, 1);
	}

	private void use15217(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15208, 1);
		Functions.addItem(player, 15221, 1);
		Functions.addItem(player, 13273, 1);
		Functions.addItem(player, 15211, 1);
	}

	private void use15216(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15217, 1);
	}

	private void use15215(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15208, 1);
		Functions.addItem(player, 15220, 1);
		Functions.addItem(player, 14065, 1);
		Functions.addItem(player, 15279, 10);
	}

	private void use15214(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15215, 1);
	}

	private void use15213(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15208, 1);
		Functions.addItem(player, 15221, 1);
		Functions.addItem(player, 13273, 1);
	}

	private void use15212(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15213, 1);
	}

	private void use15209(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15210, 1);
	}

	private void use15207(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15246, 1);
		Functions.addItem(player, 15247, 1);
		Functions.addItem(player, 15248, 1);
		Functions.addItem(player, 15249, 1);
		Functions.addItem(player, 15254, 1);
		Functions.addItem(player, 15255, 1);
		Functions.addItem(player, 15256, 1);
		Functions.addItem(player, 15257, 1);
		Functions.addItem(player, 15258, 1);
		Functions.addItem(player, 15261, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15206(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15246, 1);
		Functions.addItem(player, 15247, 1);
		Functions.addItem(player, 15248, 1);
		Functions.addItem(player, 15249, 1);
		Functions.addItem(player, 15250, 1);
		Functions.addItem(player, 15251, 1);
		Functions.addItem(player, 15252, 1);
		Functions.addItem(player, 15253, 1);
		Functions.addItem(player, 15263, 1);
		Functions.addItem(player, 15264, 1);
		Functions.addItem(player, 15266, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15205(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15250, 1);
		Functions.addItem(player, 15251, 1);
		Functions.addItem(player, 15252, 1);
		Functions.addItem(player, 15253, 1);
		Functions.addItem(player, 15267, 1);
		Functions.addItem(player, 15268, 1);
		Functions.addItem(player, 15269, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15204(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15250, 1);
		Functions.addItem(player, 15251, 1);
		Functions.addItem(player, 15252, 1);
		Functions.addItem(player, 15253, 1);
		Functions.addItem(player, 15259, 1);
		Functions.addItem(player, 15260, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15203(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15246, 1);
		Functions.addItem(player, 15247, 1);
		Functions.addItem(player, 15248, 1);
		Functions.addItem(player, 15249, 1);
		Functions.addItem(player, 15261, 1);
		Functions.addItem(player, 15265, 1);
		Functions.addItem(player, 15266, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15202(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15254, 1);
		Functions.addItem(player, 15255, 1);
		Functions.addItem(player, 15256, 1);
		Functions.addItem(player, 15257, 1);
		Functions.addItem(player, 15258, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15201(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15246, 1);
		Functions.addItem(player, 15247, 1);
		Functions.addItem(player, 15248, 1);
		Functions.addItem(player, 15249, 1);
		Functions.addItem(player, 15261, 1);
		Functions.addItem(player, 15262, 1);
		Functions.addItem(player, 15265, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 15277, 1);
	}

	private void use15200(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15222, 1);
		Functions.addItem(player, 15223, 1);
		Functions.addItem(player, 15224, 1);
		Functions.addItem(player, 15225, 1);
		Functions.addItem(player, 15230, 1);
		Functions.addItem(player, 15231, 1);
		Functions.addItem(player, 15232, 1);
		Functions.addItem(player, 15233, 1);
		Functions.addItem(player, 15234, 1);
		Functions.addItem(player, 15237, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);
	}

	private void use20799(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20777, 1);
	}

	private void use20798(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20776, 1);
	}

	private void use20797(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20775, 1);
	}

	private void use20796(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20774, 1);
	}

	private void use20773(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20762, 1);
		Functions.addItem(player, 20739, 1);
		Functions.addItem(player, 20764, 1);
		Functions.addItem(player, 20765, 1);
		Functions.addItem(player, 20766, 1);
		Functions.addItem(player, 20767, 1);
		Functions.addItem(player, 20768, 1);
	}

	private void use20754(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20756, 1);
		Functions.addItem(player, 20757, 1);
		Functions.addItem(player, 5561, 1);
	}

	private void use20753(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20756, 1);
		Functions.addItem(player, 20758, 1);
		Functions.addItem(player, 5561, 1);
	}

	private void use20740(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20393, 1);
		Functions.addItem(player, 20394, 1);
	}

	private void use20738(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20733, 1);
	}

	private void use20737(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20732, 1);
	}

	private void use20736(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20727, 1);
	}

	private void use20735(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20726, 1);
	}

	private void use20734(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20724, 1);
	}

	private void use20705(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20679, 1);
	}

	private void use20704(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20678, 1);
	}

	private void use20703(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20677, 1);
	}

	private void use20702(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20676, 1);
	}

	private void use20701(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20675, 1);
	}

	private void use20700(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20674, 1);
	}

	private void use14530(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13300, 3);
		Functions.addItem(player, 13383, 3);
		Functions.addItem(player, 13308, 1);
		Functions.addItem(player, 13316, 1);
	}

	private void use13799(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 9628, 1, 2 }, { 9629, 1, 2 }, { 9630, 1, 2 } };

		double chances[] = new double[] {
				// chance
				60.0, 30.0, 35.0

		};

		capsulate(player, items, chances);
	}

	private void use13428(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 10260, 1, 1 }, { 10261, 1, 1 }, { 10262, 1, 1 }, { 10263, 1, 1 }, { 10264, 1, 1 }, { 10265, 1, 1 },
				{ 10266, 1, 1 }, { 10267, 1, 1 }, { 10268, 1, 1 }, { 10269, 1, 1 }, { 10270, 1, 1 } };

		double chances[] = new double[] {
				// chance
				9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 10.0

		};

		capsulate(player, items, chances);
	}

	private void use13427(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 10260, 2, 2 }, { 10261, 2, 2 }, { 10262, 2, 2 }, { 10263, 2, 2 }, { 10264, 2, 2 }, { 10265, 2, 2 },
				{ 10266, 2, 2 }, { 10267, 2, 2 }, { 10268, 2, 2 }, { 10269, 2, 2 }, { 10270, 2, 2 } };

		double chances[] = new double[] {
				// chance
				9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 10.0

		};

		capsulate(player, items, chances);
	}

	private void use13426(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 9546, 1, 1 }, { 9547, 1, 1 }, { 9548, 1, 1 }, { 9549, 1, 1 }, { 9550, 1, 1 }, { 9551, 1, 1 } };

		double chances[] = new double[] {
				// chance
				16.6, 16.6, 16.6, 16.6, 16.6, 17.0

		};

		capsulate(player, items, chances);
	}

	private void use13425(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 7122, 1, 1 }, { 7123, 1, 1 }, { 7124, 1, 1 }, { 7125, 1, 1 }, { 7126, 1, 1 }, { 7127, 1, 1 },
				{ 7128, 1, 1 }, { 7129, 1, 1 }, { 7130, 1, 1 }, { 7131, 1, 1 }, { 7132, 1, 1 }, { 7133, 1, 1 },
				{ 7134, 1, 1 }, { 7135, 1, 1 } };

		double chances[] = new double[] {
				// chance
				7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14

		};

		capsulate(player, items, chances);
	}

	private void use13424(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 10260, 3, 3 }, { 10261, 3, 3 }, { 10262, 3, 3 }, { 10263, 3, 3 }, { 10264, 3, 3 }, { 10265, 3, 3 },
				{ 10266, 3, 3 }, { 10267, 3, 3 }, { 10268, 3, 3 }, { 10269, 3, 3 }, { 10270, 3, 3 } };

		double chances[] = new double[] {
				// chance
				9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 9.0, 10.0 };

		capsulate(player, items, chances);
	}

	private void use13423(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 9546, 2, 2 }, { 9547, 2, 2 }, { 9548, 2, 2 }, { 9549, 2, 2 }, { 9550, 2, 2 }, { 9551, 2, 2 } };

		double chances[] = new double[] {
				// chance
				2.16, 2.16, 2.16, 2.16, 2.16, 2.17 };

		capsulate(player, items, chances);
	}

	private void use13422(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 7122, 2, 2 }, { 7123, 2, 2 }, { 7124, 2, 2 }, { 7125, 2, 2 }, { 7126, 2, 2 }, { 7127, 2, 2 },
				{ 7128, 2, 2 }, { 7129, 2, 2 }, { 7130, 2, 2 }, { 7131, 2, 2 }, { 7132, 2, 2 }, { 7133, 2, 2 },
				{ 7134, 2, 2 }, { 7135, 2, 2 } };

		double chances[] = new double[] {
				// chance
				7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14, 7.14 };

		capsulate(player, items, chances);
	}

	private void use13385(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13383, 1);
	}

	private void use13384(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13383, 3);
	}

	private void use13381(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13336, 1);
		Functions.addItem(player, 13337, 1);
		Functions.addItem(player, 13338, 1);
	}

	private void use13380(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13334, 1);
		Functions.addItem(player, 13335, 1);
	}

	private void use13379(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13333, 1);
	}

	private void use13378(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13332, 1);
	}

	private void use13377(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13331, 1);
	}

	private void use13376(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13330, 1);
	}

	private void use13375(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13329, 1);
	}

	private void use13374(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13328, 1);
	}

	private void use13373(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13327, 1);
	}

	private void use13372(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13326, 1);
	}

	private void use13371(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13325, 1);
	}

	private void use13370(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13339, 1);
	}

	private void use13369(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13340, 1);
	}

	private void use13368(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13300, 1);
	}

	private void use13367(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13299, 5);
	}

	private void use13366(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13298, 5);
	}

	private void use13365(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13297, 5);
	}

	private void use13364(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13299, 15);
	}

	private void use13363(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13298, 15);
	}

	private void use13362(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13297, 15);
	}

	private void use13361(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13016, 10);
	}

	private void use13360(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13307, 1);
	}

	private void use13359(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13307, 3);
	}

	private void use13358(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13308, 1);
	}

	private void use13357(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13321, 1);
		Functions.addItem(player, 13322, 1);
		Functions.addItem(player, 13323, 1);
	}

	private void use13356(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13319, 1);
		Functions.addItem(player, 13320, 1);
	}

	private void use13355(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13318, 1);
	}

	private void use13354(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13317, 1);
	}

	private void use13353(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13316, 1);
	}

	private void use13352(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13315, 1);
	}

	private void use13351(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13314, 1);
	}

	private void use13350(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13313, 1);
	}

	private void use13349(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13312, 1);
	}

	private void use13348(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13311, 1);
	}

	private void use13347(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13310, 1);
	}

	private void use13346(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13297, 5);
		Functions.addItem(player, 13298, 5);
		Functions.addItem(player, 13299, 5);

	}

	private void use13345(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13324, 1);
	}

	private void use13344(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13309, 1);
	}

	private void use13343(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13300, 3);
	}

	private void use13342(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13302, 30);
	}

	private void use13341(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13301, 1);
	}

	private void use16999(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16854, 2);
		Functions.addItem(player, 16855, 1);
		Functions.addItem(player, 16856, 2);
	}

	private void use16998(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16964, 1);
		Functions.addItem(player, 16965, 1);
		Functions.addItem(player, 16966, 1);
		Functions.addItem(player, 16967, 1);
	}

	private void use16997(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16960, 1);
		Functions.addItem(player, 16961, 1);
		Functions.addItem(player, 16962, 1);
		Functions.addItem(player, 16963, 1);
	}

	private void use16996(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16956, 1);
		Functions.addItem(player, 16957, 1);
		Functions.addItem(player, 16958, 1);
		Functions.addItem(player, 16959, 1);
	}

	private void use16995(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16952, 1);
		Functions.addItem(player, 16953, 1);
		Functions.addItem(player, 16954, 1);
		Functions.addItem(player, 16955, 1);
	}

	private void use16994(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16948, 1);
		Functions.addItem(player, 16949, 1);
		Functions.addItem(player, 16950, 1);
		Functions.addItem(player, 16951, 1);
	}

	private void use16993(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16944, 1);
		Functions.addItem(player, 16945, 1);
		Functions.addItem(player, 16946, 1);
		Functions.addItem(player, 16947, 1);
	}

	private void use16992(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16940, 1);
		Functions.addItem(player, 16941, 1);
		Functions.addItem(player, 16942, 1);
		Functions.addItem(player, 16943, 1);
	}

	private void use16991(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16936, 1);
		Functions.addItem(player, 16937, 1);
		Functions.addItem(player, 16938, 1);
		Functions.addItem(player, 16939, 1);
	}

	private void use16990(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16932, 1);
		Functions.addItem(player, 16933, 1);
		Functions.addItem(player, 16934, 1);
		Functions.addItem(player, 16935, 1);
	}

	private void use16989(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16928, 1);
		Functions.addItem(player, 16929, 1);
		Functions.addItem(player, 16930, 1);
		Functions.addItem(player, 16931, 1);
	}

	private void use16988(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16924, 1);
		Functions.addItem(player, 16925, 1);
		Functions.addItem(player, 16926, 1);
		Functions.addItem(player, 16927, 1);
	}

	private void use16987(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16920, 1);
		Functions.addItem(player, 16921, 1);
		Functions.addItem(player, 16922, 1);
		Functions.addItem(player, 16923, 1);
	}

	private void use16986(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16880, 1);
		Functions.addItem(player, 16881, 1);
		Functions.addItem(player, 16882, 1);
		Functions.addItem(player, 16883, 1);
		Functions.addItem(player, 16911, 1);
	}

	private void use16985(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16876, 1);
		Functions.addItem(player, 16877, 1);
		Functions.addItem(player, 16878, 1);
		Functions.addItem(player, 16879, 1);
		Functions.addItem(player, 16900, 1);
	}

	private void use16984(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16871, 1);
		Functions.addItem(player, 16872, 1);
		Functions.addItem(player, 16873, 1);
		Functions.addItem(player, 16874, 1);
		Functions.addItem(player, 16875, 1);
	}

	private void use16983(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16866, 1);
		Functions.addItem(player, 16867, 1);
		Functions.addItem(player, 16868, 1);
		Functions.addItem(player, 16869, 1);
		Functions.addItem(player, 16870, 1);
	}

	private void use16982(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16905, 1);
		Functions.addItem(player, 16906, 1);
		Functions.addItem(player, 16907, 1);
		Functions.addItem(player, 16908, 1);
		Functions.addItem(player, 16911, 1);
	}

	private void use16981(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16896, 1);
		Functions.addItem(player, 16897, 1);
		Functions.addItem(player, 16898, 1);
		Functions.addItem(player, 16899, 1);
		Functions.addItem(player, 16900, 1);
	}

	private void use16980(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16888, 1);
		Functions.addItem(player, 16889, 1);
		Functions.addItem(player, 16891, 1);
		Functions.addItem(player, 16893, 1);
	}

	private void use16979(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16884, 1);
		Functions.addItem(player, 16885, 1);
		Functions.addItem(player, 16886, 1);
		Functions.addItem(player, 16887, 1);
		Functions.addItem(player, 16868, 1);
	}

	private void use16978(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16909, 1);
		Functions.addItem(player, 16910, 1);
		Functions.addItem(player, 16911, 1);
		Functions.addItem(player, 16912, 1);
		Functions.addItem(player, 16913, 1);
		Functions.addItem(player, 16914, 1);
	}

	private void use16977(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16900, 1);
		Functions.addItem(player, 16901, 1);
		Functions.addItem(player, 16802, 1);
		Functions.addItem(player, 16803, 1);
		Functions.addItem(player, 16804, 1);
	}

	private void use16976(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16889, 1);
		Functions.addItem(player, 16890, 1);
		Functions.addItem(player, 16892, 1);
		Functions.addItem(player, 16894, 1);
		Functions.addItem(player, 16895, 1);
	}

	private void use16975(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16915, 1);
		Functions.addItem(player, 16916, 1);
		Functions.addItem(player, 16917, 1);
		Functions.addItem(player, 16918, 1);
		Functions.addItem(player, 16919, 1);
	}

	private void use16974(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16975, 1);
		Functions.addItem(player, 16976, 1);
		Functions.addItem(player, 16977, 1);
		Functions.addItem(player, 16978, 1);
		Functions.addItem(player, 16983, 1);
		Functions.addItem(player, 16984, 1);
		Functions.addItem(player, 16985, 1);
		Functions.addItem(player, 16986, 1);
		Functions.addItem(player, 16987, 1);
		Functions.addItem(player, 16990, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16973(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16975, 1);
		Functions.addItem(player, 16976, 1);
		Functions.addItem(player, 16977, 1);
		Functions.addItem(player, 16978, 1);
		Functions.addItem(player, 16979, 1);
		Functions.addItem(player, 16980, 1);
		Functions.addItem(player, 16981, 1);
		Functions.addItem(player, 16982, 1);
		Functions.addItem(player, 16992, 1);
		Functions.addItem(player, 16993, 1);
		Functions.addItem(player, 16995, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16972(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16979, 1);
		Functions.addItem(player, 16980, 1);
		Functions.addItem(player, 16981, 1);
		Functions.addItem(player, 16982, 1);
		Functions.addItem(player, 16996, 1);
		Functions.addItem(player, 16997, 1);
		Functions.addItem(player, 16998, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16971(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16979, 1);
		Functions.addItem(player, 16980, 1);
		Functions.addItem(player, 16981, 1);
		Functions.addItem(player, 16982, 1);
		Functions.addItem(player, 16988, 1);
		Functions.addItem(player, 16989, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16970(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16975, 1);
		Functions.addItem(player, 16976, 1);
		Functions.addItem(player, 16977, 1);
		Functions.addItem(player, 16978, 1);
		Functions.addItem(player, 16990, 1);
		Functions.addItem(player, 16994, 1);
		Functions.addItem(player, 16995, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16969(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16983, 1);
		Functions.addItem(player, 16984, 1);
		Functions.addItem(player, 16985, 1);
		Functions.addItem(player, 16986, 1);
		Functions.addItem(player, 16987, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16968(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16975, 1);
		Functions.addItem(player, 16976, 1);
		Functions.addItem(player, 16977, 1);
		Functions.addItem(player, 16978, 1);
		Functions.addItem(player, 16990, 1);
		Functions.addItem(player, 16991, 1);
		Functions.addItem(player, 16994, 1);
		Functions.addItem(player, 16999, 1);
		Functions.addItem(player, 17000, 1);
		Functions.addItem(player, 17001, 1);
		Functions.addItem(player, 17002, 1);
	}

	private void use16852(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16853, 1);
		Functions.addItem(player, 13273, 1);
		Functions.addItem(player, 15426, 1);
	}

	private void use16398(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16402, 1);
		Functions.addItem(player, 16406, 3);
	}

	private void use16397(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16401, 1);
		Functions.addItem(player, 16406, 3);
	}

	private void use16396(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16400, 1);
		Functions.addItem(player, 16404, 3);
	}

	private void use16395(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16399, 1);
		Functions.addItem(player, 16404, 3);
	}

	private void use16394(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16393, 5);
		Functions.addItem(player, 16407, 1);
	}

	private void use16393(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16405, 1);
		Functions.addItem(player, 16406, 1);
	}

	private void use16392(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16391, 1);
		Functions.addItem(player, 16407, 1);
	}

	private void use16391(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16403, 1);
		Functions.addItem(player, 16404, 1);
	}

	private void use16390(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16398, 1);
	}

	private void use16389(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16397, 1);
	}

	private void use16388(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16394, 1);
	}

	private void use16387(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16393, 1);
	}

	private void use16386(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16396, 1);
	}

	private void use16385(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16395, 1);
	}

	private void use16384(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16392, 1);
	}

	private void use16383(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 16391, 1);
	}

	private void use20398(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13421, 10);
		Functions.addItem(player, 22000, 1);
		Functions.addItem(player, 22003, 1);
		Functions.addItem(player, 10649, 3);
	}

	private void use20397(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 6354, 1);
		Functions.addItem(player, 6355, 1);
		Functions.addItem(player, 13121, 1);
		Functions.addItem(player, 1060, 20);
		Functions.addItem(player, 5789, 1000);
		Functions.addItem(player, 5790, 1000);
		Functions.addItem(player, 9191, 1);
		Functions.addItem(player, 5593, 1);
	}

	private void use20331(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20334, 10000);
	}

	private void use20328(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20334, 5000);
	}

	private void use20319(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20325, 1);
	}

	private void use20318(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20324, 1);
	}

	private void use20317(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20323, 1);
	}

	private void use20316(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20322, 1);
	}

	private void use20315(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20321, 1);
	}

	private void use20314(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20320, 1);
	}

	private void use21095(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino fix CosMOsiS
		 */
		Functions.addItem(player, 13508, 1);
	}

	private void use21089(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21088, 1);
	}

	private void use21087(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20741, 1);
	}

	private void use21085(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20742, 1);
	}

	private void use21083(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21062, 1);
	}

	private void use21082(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21061, 1);
	}

	private void use21081(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21060, 1);
	}

	private void use21080(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21059, 1);
	}

	private void use21079(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21058, 1);
	}

	private void use21078(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21057, 1);
	}

	private void use21077(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21056, 1);
	}

	private void use21076(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21055, 1);
	}

	private void use21075(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21054, 1);
	}

	private void use21074(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21053, 1);
	}

	private void use21073(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21052, 1);
	}

	private void use21072(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21051, 1);
	}

	private void use21071(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21050, 1);
	}

	private void use21070(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21049, 1);
	}

	private void use21069(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21048, 1);
	}

	private void use21068(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21047, 1);
	}

	private void use21067(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21046, 1);
	}

	private void use21066(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21045, 1);
	}

	private void use21065(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21044, 1);
	}

	private void use21064(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21043, 1);
	}

	private void use21063(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21042, 1);
	}

	private void use21029(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21014, 1);
	}

	private void use21028(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21013, 1);
	}

	private void use21027(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21012, 1);
	}

	private void use21026(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21011, 1);
	}

	private void use21025(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21010, 1);
	}

	private void use21024(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21009, 1);
	}

	private void use21008(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20991, 1);
	}

	private void use21007(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20990, 1);
	}

	private void use21006(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20989, 1);
	}

	private void use21005(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20988, 1);
	}

	private void use21004(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20987, 1);
	}

	private void use21003(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20986, 1);
	}

	private void use21002(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20985, 1);
	}

	private void use21001(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20984, 1);
	}

	private void use21686(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22172, 1);
	}

	private void use21685(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20447, 1);
	}

	private void use21684(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22171, 1);
	}

	private void use21683(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20446, 1);
	}

	private void use21682(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22170, 1);
	}

	private void use21681(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20445, 1);
	}

	private void use21680(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22169, 1);
	}

	private void use21679(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20444, 1);
	}

	private void use21678(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22168, 1);
	}

	private void use21677(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20443, 1);
	}

	private void use21676(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22167, 1);
	}

	private void use21675(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20442, 1);
	}

	private void use21674(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22166, 1);
	}

	private void use21673(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20441, 1);
	}

	private void use21672(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22165, 1);
	}

	private void use21671(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20440, 1);
	}

	private void use21670(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22164, 1);
	}

	private void use21669(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20439, 1);
	}

	private void use21668(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22163, 1);
	}

	private void use21667(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20438, 1);
	}

	private void use21666(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22162, 1);
	}

	private void use21665(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20437, 1);
	}

	private void use21664(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22161, 1);
	}

	private void use21663(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20436, 1);
	}

	private void use21662(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22160, 1);
	}

	private void use21661(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20435, 1);
	}

	private void use21660(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22159, 1);
	}

	private void use21659(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20434, 1);
	}

	private void use21658(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22158, 1);
	}

	private void use21657(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20433, 1);
	}

	private void use21656(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22157, 1);
	}

	private void use21655(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20432, 1);
	}

	private void use21654(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22156, 1);
	}

	private void use21653(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20431, 1);
	}

	private void use21652(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20325, 1);
	}

	private void use21651(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20430, 1);
	}

	private void use21650(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20324, 1);
	}

	private void use21649(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20429, 1);
	}

	private void use21648(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20323, 1);
	}

	private void use21647(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20428, 1);
	}

	private void use21646(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20322, 1);
	}

	private void use21645(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20427, 1);
	}

	private void use21644(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 21088, 1);
	}

	private void use21643(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20939, 1);
	}

	private void use21642(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20938, 1);
	}

	private void use21641(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20502, 1);
	}

	private void use21640(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20503, 1);
	}

	private void use21639(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20396, 1);
	}

	private void use21638(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20504, 1);
	}

	private void use21637(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20449, 1);
	}

	private void use21636(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20029, 1);
	}

	private void use21635(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20030, 1);
	}

	private void use21634(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20448, 1);
	}

	private void use21633(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20394, 1);
	}

	private void use21632(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20393, 1);
	}

	private void use21631(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20392, 1);
	}

	private void use21630(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20391, 1);
	}

	private void use21629(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12371, 1);
	}

	private void use21628(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12370, 1);
	}

	private void use21627(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12369, 1);
	}

	private void use21626(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12368, 1);
	}

	private void use21625(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12367, 1);
	}

	private void use21624(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12366, 1);
	}

	private void use21623(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12365, 1);
	}

	private void use21622(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12364, 1);
	}

	private void use21621(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12363, 1);
	}

	private void use21620(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12362, 1);
	}

	private void use13292(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13250, 1);
		Functions.addItem(player, 13251, 1);
		Functions.addItem(player, 13252, 1);
	}

	private void use13291(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13248, 1);
		Functions.addItem(player, 13249, 1);
	}

	private void use13279(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10649, 1);
	}

	private void use13276(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13273, 1);
	}

	private void use13275(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13273, 3);
	}

	private void use13257(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13382, 1);
	}

	private void use13256(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13027, 1);
	}

	private void use13233(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13012, 5);
	}

	private void use13232(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13011, 5);
	}

	private void use13231(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13010, 5);
	}

	private void use13230(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13012, 15);
	}

	private void use13229(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13011, 15);
	}

	private void use13228(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13010, 15);
	}

	private void use13227(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13012, 15);
	}

	private void use13226(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13011, 15);
	}

	private void use13225(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13010, 15);
	}

	private void use13126(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13307, 1);
		Functions.addItem(player, 13302, 10);
		Functions.addItem(player, 13301, 1);
	}

	private void use13125(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13016, 10);
	}

	private void use13124(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13021, 1);
	}

	private void use13122(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino Fix by CosMOsiS
		 */
		Functions.addItem(player, 13021, 3);
	}

	private void use13121(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13022, 1);
	}

	private void use13120(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12795, 1);
		Functions.addItem(player, 12796, 1);
		Functions.addItem(player, 12797, 1);
	}

	private void use13119(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12793, 1);
		Functions.addItem(player, 12794, 1);
	}

	private void use13118(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12792, 1);
	}

	private void use13117(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12791, 1);
	}

	private void use13116(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12790, 1);
	}

	private void use13115(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12789, 1);
	}

	private void use13114(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12788, 1);
	}

	private void use13113(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12787, 1);
	}

	private void use13112(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12786, 1);
	}

	private void use13111(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12783, 1);
	}

	private void use13110(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12782, 1);
	}

	private void use13109(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13010, 5);
		Functions.addItem(player, 13011, 5);
		Functions.addItem(player, 13012, 5);
	}

	private void use13108(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 12800, 1);
	}

	private void use13107(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13023, 1);
	}

	private void use13106(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10649, 3);
	}

	private void use13105(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13010, 5);
		Functions.addItem(player, 13011, 5);
		Functions.addItem(player, 13012, 5);
	}

	private void use13104(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13016, 30);
	}

	private void use13103(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 13015, 1);
	}

	// 6th Place Treasure Sack
	private void use10259(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 10260, 3, 3 }, { 10261, 3, 3 }, { 10262, 3, 3 }, { 10263, 3, 3 }, { 10264, 3, 3 }, { 10265, 3, 3 },
				{ 10266, 3, 3 }, { 10267, 3, 3 }, { 10268, 3, 3 }, { 10269, 3, 3 }, { 10270, 3, 3 } };

		double chances[] = new double[] {
				// chance
				9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 10.00 };

		capsulate(player, items, chances);
	}

	// 5th Place Treasure Sack
	private void use10258(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 10178, 5);
		Functions.addItem(player, 10179, 5);
	}

	// 4th Place Treasure Sack
	private void use10257(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 951, 1, 1 }, { 952, 1, 1 }, { 955, 1, 1 }, { 956, 1, 1 }, { 1538, 1, 1 }, { 9909, 1, 1 },
				{ 10139, 1, 1 } };

		double chances[] = new double[] {
				// chance
				3.01, 21.30, 6.41, 52.72, 2.56, 7.00, 7.00 };

		capsulate(player, items, chances);
	}

	// 3rd Place Treasure Sack
	private void use10256(Player player, boolean ctrl) { /* by 4ipolino */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 729, 1, 1 }, { 730, 1, 1 }, { 947, 1, 1 }, { 948, 1, 1 }, { 8736, 1, 1 }, { 8738, 1, 1 },
				{ 8741, 1, 1 }, { 8742, 1, 1 }, { 3936, 1, 1 } };

		double chances[] = new double[] {
				// chance
				0.51, 3.86, 1.85, 11.57, 32.15, 23.15, 12.86, 11.57, 2.48 };

		capsulate(player, items, chances);
	}

	// 2nd Place Treasure Sack
	private void use10255(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 9625, 1, 1 }, { 9626, 1, 1 }, { 9627, 1, 1 }, { 9546, 1, 1 }, { 9547, 1, 1 }, { 9548, 1, 1 },
				{ 9549, 1, 1 }, { 9550, 1, 1 }, { 9551, 1, 1 }, { 959, 1, 1 }, { 960, 1, 1 } };

		double chances[] = new double[] {
				// chance
				2.20, 1.42, 0.67, 15.30, 15.30, 15.30, 15.30, 15.30, 15.30, 0.28, 3.73 };

		capsulate(player, items, chances);
	}

	private void use15199(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15222, 1);
		Functions.addItem(player, 15223, 1);
		Functions.addItem(player, 15224, 1);
		Functions.addItem(player, 15225, 1);
		Functions.addItem(player, 15226, 1);
		Functions.addItem(player, 15227, 1);
		Functions.addItem(player, 15228, 1);
		Functions.addItem(player, 15229, 1);
		Functions.addItem(player, 15239, 1);
		Functions.addItem(player, 15240, 1);
		Functions.addItem(player, 15242, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);

	}

	private void use15198(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15226, 1);
		Functions.addItem(player, 15227, 1);
		Functions.addItem(player, 15228, 1);
		Functions.addItem(player, 15229, 1);
		Functions.addItem(player, 15243, 1);
		Functions.addItem(player, 15244, 1);
		Functions.addItem(player, 15245, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);

	}

	private void use15197(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15226, 1);
		Functions.addItem(player, 15227, 1);
		Functions.addItem(player, 15228, 1);
		Functions.addItem(player, 15229, 1);
		Functions.addItem(player, 15235, 1);
		Functions.addItem(player, 15236, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);
	}

	private void use15196(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15222, 1);
		Functions.addItem(player, 15223, 1);
		Functions.addItem(player, 15224, 1);
		Functions.addItem(player, 15225, 1);
		Functions.addItem(player, 15237, 1);
		Functions.addItem(player, 15241, 1);
		Functions.addItem(player, 15242, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);

	}

	private void use15195(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15230, 1);
		Functions.addItem(player, 15231, 1);
		Functions.addItem(player, 15232, 1);
		Functions.addItem(player, 15233, 1);
		Functions.addItem(player, 15234, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);
	}

	private void use15194(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15222, 1);
		Functions.addItem(player, 15223, 1);
		Functions.addItem(player, 15224, 1);
		Functions.addItem(player, 15225, 1);
		Functions.addItem(player, 15237, 1);
		Functions.addItem(player, 15238, 1);
		Functions.addItem(player, 15241, 1);
		Functions.addItem(player, 15270, 1);
		Functions.addItem(player, 15271, 1);
		Functions.addItem(player, 15272, 1);
		Functions.addItem(player, 15273, 1);

	}

	private void use20189(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20190, 1);
	}

	private void use20188(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20028, 10);
	}

	private void use20187(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20026, 10);
	}

	private void use20186(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20028, 5);
	}

	private void use20185(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20026, 5);
	}

	private void use20184(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20033, 10);
	}

	private void use20183(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20033, 5);
	}

	private void use20182(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20025, 10);
	}

	private void use20181(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20025, 5);
	}

	private void use20180(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20027, 10);
	}

	private void use20179(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20027, 5);
	}

	private void use20102(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20075, 1);
		Functions.addItem(player, 20076, 1);
		Functions.addItem(player, 20078, 1);
	}

	private void use20101(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20075, 1);
		Functions.addItem(player, 20076, 1);
		Functions.addItem(player, 20077, 1);
	}

	private void use15378(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15383, 1);
	}

	private void use15377(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15380, 1);
	}

	private void use15376(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int[] list = new int[] { 15380, 15389, 15380, 15390, 15380, 15391, 15380, 15392 };
		int[] chances = new int[] { 65, 65, 25, 25, 10, 10, 1, 1 };
		int[] counts = new int[] { 5, 1, 5, 1, 5, 1, 5, 1 };
		extract_item_r(list, counts, chances, player);
	}

	private void use15375(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15377, 1);
	}

	private void use15374(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15376, 1);
	}

	private void use15373(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15379, 1);
	}

	private void use15372(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		int[] list = new int[] { 15384, 15379, 15385, 15379, 15386, 15387 };
		int[] chances = new int[] { 65, 25, 25, 10, 10, 1 };
		int[] counts = new int[] { 1, 5, 1, 5, 1, 1 };
		extract_item_r(list, counts, chances, player);

	}

	private void use15371(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15373, 1);
	}

	private void use15370(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15372, 1);
	}

	private void use15345(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15344, 1);
	}

	private void use15343(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 15342, 1);
	}

	private void use21619(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22066, 1);
	}

	private void use21618(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20352, 1);
	}

	private void use21617(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20351, 1);
	}

	private void use21616(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20350, 1);
	}

	private void use21615(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20349, 1);
	}

	private void use21614(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20348, 1);
	}

	private void use21613(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20347, 1);
	}

	private void use21612(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20346, 1);
	}

	private void use21611(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20345, 1);
	}

	private void use21610(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20344, 1);
	}

	private void use21609(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20343, 1);
	}

	private void use21608(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20342, 1);
	}

	private void use21607(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20341, 1);
	}

	private void use21606(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20340, 1);
	}

	private void use21605(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20339, 1);
	}

	private void use21604(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20338, 1);
	}

	private void use21603(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20337, 1);
	}

	private void use21602(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20336, 1);
	}

	private void use21601(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 20335, 1);
	}

	private void use22187(Player player, boolean ctrl) {
		/**
		 * @Author: 4ipolino
		 */
		Functions.addItem(player, 22188, 3);
		Functions.addItem(player, 21595, 1);
		Functions.addItem(player, 21594, 1);
	}

	// ------ AGNOLIC � 2012 ---------

	// RUNE OF EXPERIENCE POINTS 50% 7-DAY PACK
	private void use21091(Player player, boolean ctrl) {
		Functions.addItem(player, 20340, 1);
	}

	// RUNE OF EXPERIENCE POINTS 50% 7-DAY PACK
	private void use21092(Player player, boolean ctrl) {
		Functions.addItem(player, 20346, 1);
	}

	// ------ Adventurer's Boxes ------

	// Quest 376: Giants Cave Exploration, Part 1
	private void use14841(Player player, boolean ctrl) {
		int[] list = { 14836, 14837, 14838, 14839, 14840 };
		int[] counts = { 1, 1, 1, 1, 1 };

		if (ctrl) {
			long item_count = 1 + Functions.removeItem(player, 14841, Functions.getItemCount(player, 14841));
			for (int[] res : mass_extract_item(item_count, list, counts, player))
				Functions.addItem(player, res[0], res[1]);
		} else
			extract_item(list, counts, player);
	}

	// Quest 377: Giants Cave Exploration, Part 2, new
	private void use14847(Player player, boolean ctrl) {
		int[] list = { 14842, 14843, 14844, 14845, 14846 };
		int[] counts = { 1, 1, 1, 1, 1 };

		if (ctrl) {
			long item_count = 1 + Functions.removeItem(player, 14847, Functions.getItemCount(player, 14847));
			for (int[] res : mass_extract_item(item_count, list, counts, player))
				Functions.addItem(player, res[0], res[1]);
		} else
			extract_item(list, counts, player);
	}

	// Old Agathion
	private void use10408(Player player, boolean ctrl) {
		Functions.addItem(player, 6471, 20);
		Functions.addItem(player, 5094, 40);
		Functions.addItem(player, 9814, 3);
		Functions.addItem(player, 9816, 4);
		Functions.addItem(player, 9817, 4);
		Functions.addItem(player, 9815, 2);
		Functions.addItem(player, 57, 6000000);
	}

	// Magic Armor Set
	private void use10473(Player player, boolean ctrl) {
		Functions.addItem(player, 10470, 2); // Shadow Item - Red Crescent
		Functions.addItem(player, 10471, 2); // Shadow Item - Ring of Devotion
		Functions.addItem(player, 10472, 1); // Shadow Item - Necklace of Devotion
	}

	// Ancient Tome of the Demon
	private void use9599(Player player, boolean ctrl) {
		int[] list = new int[] { 9600, 9601, 9602 };
		int[] count_min = new int[] { 1, 1, 1 };
		int[] count_max = new int[] { 2, 2, 1 };
		int[] chances = new int[] { 4, 10, 1 };

		if (ctrl) {
			long item_count = 1 + Functions.removeItem(player, 9599, Functions.getItemCount(player, 9599));
			for (int[] res : mass_extract_item_r(item_count, list, count_min, count_max, chances, player))
				Functions.addItem(player, res[0], res[1]);
		} else
			extract_item_r(list, count_min, count_max, chances, player);
	}

	// Baby Panda Agathion Pack
	private void use20069(Player player, boolean ctrl) {
		Functions.addItem(player, 20063, 1);
	}

	// Bamboo Panda Agathion Pack
	private void use20070(Player player, boolean ctrl) {
		Functions.addItem(player, 20064, 1);
	}

	// Sexy Panda Agathion Pack
	private void use20071(Player player, boolean ctrl) {
		Functions.addItem(player, 20065, 1);
	}

	// Agathion of Baby Panda 15 Day Pack
	private void use20072(Player player, boolean ctrl) {
		Functions.addItem(player, 20066, 1);
	}

	// Bamboo Panda Agathion 15 Day Pack
	private void use20073(Player player, boolean ctrl) {
		Functions.addItem(player, 20067, 1);
	}

	// Agathion of Sexy Panda 15 Day Pack
	private void use20074(Player player, boolean ctrl) {
		Functions.addItem(player, 20068, 1);
	}

	// Charming Valentine Gift Set
	private void use20210(Player player, boolean ctrl) {
		Functions.addItem(player, 20212, 1);
	}

	// Naughty Valentine Gift Set
	private void use20211(Player player, boolean ctrl) {
		Functions.addItem(player, 20213, 1);
	}

	// White Maneki Neko Agathion Pack
	private void use20215(Player player, boolean ctrl) {
		Functions.addItem(player, 20221, 1);
	}

	// Black Maneki Neko Agathion Pack
	private void use20216(Player player, boolean ctrl) {
		Functions.addItem(player, 20222, 1);
	}

	// Brown Maneki Neko Agathion Pack
	private void use20217(Player player, boolean ctrl) {
		Functions.addItem(player, 20223, 1);
	}

	// White Maneki Neko Agathion 7-Day Pack
	private void use20218(Player player, boolean ctrl) {
		Functions.addItem(player, 20224, 1);
	}

	// Black Maneki Neko Agathion 7-Day Pack
	private void use20219(Player player, boolean ctrl) {
		Functions.addItem(player, 20225, 1);
	}

	// Brown Maneki Neko Agathion 7-Day Pack
	private void use20220(Player player, boolean ctrl) {
		Functions.addItem(player, 20226, 1);
	}

	// One-Eyed Bat Drove Agathion Pack
	private void use20227(Player player, boolean ctrl) {
		Functions.addItem(player, 20230, 1);
	}

	// One-Eyed Bat Drove Agathion 7-Day Pack
	private void use20228(Player player, boolean ctrl) {
		Functions.addItem(player, 20231, 1);
	}

	// One-Eyed Bat Drove Agathion 7-Day Pack
	private void use20229(Player player, boolean ctrl) {
		Functions.addItem(player, 20232, 1);
	}

	// Pegasus Agathion Pack
	private void use20233(Player player, boolean ctrl) {
		Functions.addItem(player, 20236, 1);
	}

	// Pegasus Agathion 7-Day Pack
	private void use20234(Player player, boolean ctrl) {
		Functions.addItem(player, 20237, 1);
	}

	// Pegasus Agathion 7-Day Pack
	private void use20235(Player player, boolean ctrl) {
		Functions.addItem(player, 20238, 1);
	}

	// Yellow-Robed Tojigong Pack
	private void use20239(Player player, boolean ctrl) {
		Functions.addItem(player, 20245, 1);
	}

	// Blue-Robed Tojigong Pack
	private void use20240(Player player, boolean ctrl) {
		Functions.addItem(player, 20246, 1);
	}

	// Green-Robed Tojigong Pack
	private void use20241(Player player, boolean ctrl) {
		Functions.addItem(player, 20247, 1);
	}

	// Yellow-Robed Tojigong 7-Day Pack
	private void use20242(Player player, boolean ctrl) {
		Functions.addItem(player, 20248, 1);
	}

	// Blue-Robed Tojigong 7-Day Pack
	private void use20243(Player player, boolean ctrl) {
		Functions.addItem(player, 20249, 1);
	}

	// Green-Robed Tojigong 7-Day Pack
	private void use20244(Player player, boolean ctrl) {
		Functions.addItem(player, 20250, 1);
	}

	// Bugbear Agathion Pack
	private void use20251(Player player, boolean ctrl) {
		Functions.addItem(player, 20252, 1);
	}

	// Agathion of Love Pack (Event)
	private void use20254(Player player, boolean ctrl) {
		Functions.addItem(player, 20253, 1);
	}

	// Gold Afro Hair Pack
	private void use20278(Player player, boolean ctrl) {
		Functions.addItem(player, 20275, 1);
	}

	// Pink Afro Hair Pack
	private void use20279(Player player, boolean ctrl) {
		Functions.addItem(player, 20276, 1);
	}

	// Plaipitak Agathion Pack
	private void use20041(Player player, boolean ctrl) {
		Functions.addItem(player, 20012, 1);
	}

	// Plaipitak Agathion 30-Day Pack
	private void use20042(Player player, boolean ctrl) {
		Functions.addItem(player, 20013, 1);
	}

	// Plaipitak Agathion 30-Day Pack
	private void use20043(Player player, boolean ctrl) {
		Functions.addItem(player, 20014, 1);
	}

	// Plaipitak Agathion 30-Day Pack
	private void use20044(Player player, boolean ctrl) {
		Functions.addItem(player, 20015, 1);
	}

	// Majo Agathion Pack
	private void use20035(Player player, boolean ctrl) {
		Functions.addItem(player, 20006, 1);
	}

	// Gold Crown Majo Agathion Pack
	private void use20036(Player player, boolean ctrl) {
		Functions.addItem(player, 20007, 1);
	}

	// Black Crown Majo Agathion Pack
	private void use20037(Player player, boolean ctrl) {
		Functions.addItem(player, 20008, 1);
	}

	// Majo Agathion 30-Day Pack
	private void use20038(Player player, boolean ctrl) {
		Functions.addItem(player, 20009, 1);
	}

	// Gold Crown Majo Agathion 30-Day Pack
	private void use20039(Player player, boolean ctrl) {
		Functions.addItem(player, 20010, 1);
	}

	// Black Crown Majo Agathion 30-Day Pack
	private void use20040(Player player, boolean ctrl) {
		Functions.addItem(player, 20011, 1);
	}

	// Kat the Cat Hat Pack
	private void use20060(Player player, boolean ctrl) {
		Functions.addItem(player, 20031, 1);
	}

	// Skull Hat Pack
	private void use20061(Player player, boolean ctrl) {
		Functions.addItem(player, 20032, 1);
	}

	// ****** Start Item Mall ******
	// Small fortuna box
	private void use22000(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22006, 3 }, { 22007, 2 }, { 22008, 1 }, { 22014, 1 }, { 22022, 3 },
				{ 22023, 3 }, { 22024, 1 }, { 8743, 1 }, { 8744, 1 }, { 8745, 1 }, { 8753, 1 }, { 8754, 1 },
				{ 8755, 1 }, { 22025, 5 } };
		double[] chances = new double[] { 20.55555, 14.01515, 6.16666, 0.86999, 3.19444, 6.38888, 5.75, 10, 8.33333,
				6.94444, 2, 1.6666, 1.38888, 12.77777 };
		extractRandomOneItem(player, list, chances);
	}

	// Middle fortuna box
	private void use22001(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22007, 3 }, { 22008, 2 }, { 22009, 1 }, { 22014, 1 }, { 22015, 1 },
				{ 22022, 5 }, { 22023, 5 }, { 22024, 2 }, { 8746, 1 }, { 8747, 1 }, { 8748, 1 }, { 8756, 1 },
				{ 8757, 1 }, { 8758, 1 }, { 22025, 10 } };
		double[] chances = new double[] { 27.27272, 9, 5, 0.93959, 0.32467, 3.75, 7.5, 5.625, 9.11458, 7.875, 6.5625,
				1.82291, 1.575, 1.3125, 12.5 };
		extractRandomOneItem(player, list, chances);
	}

	// Large fortuna box
	private void use22002(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22008, 2 }, { 22009, 1 }, { 22014, 1 }, { 22015, 1 }, { 22018, 1 },
				{ 22019, 1 }, { 22022, 10 }, { 22023, 10 }, { 22024, 5 }, { 8749, 1 }, { 8750, 1 }, { 8751, 1 },
				{ 8759, 1 }, { 8760, 1 }, { 8761, 1 }, { 22025, 20 } };
		double[] chances = new double[] { 27, 15, 0.78299, 0.27056, 0.00775, 0.0027, 3.75, 7.5, 4.5, 9.75, 8.125,
				6.77083, 1.95, 1.625, 1.35416, 12.5 };
		extractRandomOneItem(player, list, chances);
	}

	// Small fortuna cube
	private void use22003(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22010, 3 }, { 22011, 2 }, { 22012, 1 }, { 22016, 1 }, { 22022, 3 },
				{ 22023, 3 }, { 22024, 1 }, { 8743, 1 }, { 8744, 1 }, { 8745, 1 }, { 8753, 1 }, { 8754, 1 },
				{ 8755, 1 }, { 22025, 5 } };
		double[] chances = new double[] { 20.22222, 13.78787, 6.06666, 0.69599, 3.47222, 6.94444, 6.25, 9.5, 7.91666,
				6.59722, 1.9, 1.58333, 1.31944, 13.88888 };
		extractRandomOneItem(player, list, chances);
	}

	// Middle fortuna cube
	private void use22004(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22011, 3 }, { 22012, 2 }, { 22013, 1 }, { 22016, 1 }, { 22017, 1 },
				{ 22022, 5 }, { 22023, 5 }, { 22024, 2 }, { 8746, 1 }, { 8747, 1 }, { 8748, 1 }, { 8756, 1 },
				{ 8757, 1 }, { 8758, 1 }, { 22025, 10 } };
		double[] chances = new double[] { 26.51515, 8.75, 4.86111, 0.91349, 0.31565, 3.75, 7.5, 5.625, 9.54861, 8.25,
				6.875, 1.90972, 1.65, 1.375, 12.5 };
		extractRandomOneItem(player, list, chances);
	}

	// Large fortuna cube
	private void use22005(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 22012, 2 }, { 22013, 1 }, { 22016, 1 }, { 22017, 1 }, { 22020, 1 },
				{ 22021, 1 }, { 22022, 10 }, { 22023, 10 }, { 22024, 5 }, { 8749, 1 }, { 8750, 1 }, { 8751, 1 },
				{ 8759, 1 }, { 8760, 1 }, { 8761, 1 }, { 22025, 20 } };
		double[] chances = new double[] { 26.25, 14.58333, 0.69599, 0.24049, 0.00638, 0.0022, 3.95833, 7.91666, 4.75,
				9.58333, 7.98611, 6.65509, 1.91666, 1.59722, 1.33101, 13.19444 };
		extractRandomOneItem(player, list, chances);
	}

	// Beast Soulshot Pack
	private void use20326(Player player, boolean ctrl) {
		Functions.addItem(player, 20332, 5000);
	}

	// Beast Spiritshot Pack
	private void use20327(Player player, boolean ctrl) {
		Functions.addItem(player, 20333, 5000);
	}

	// Beast Soulshot Large Pack
	private void use20329(Player player, boolean ctrl) {
		Functions.addItem(player, 20332, 10000);
	}

	// Beast Spiritshot Large Pack
	private void use20330(Player player, boolean ctrl) {
		Functions.addItem(player, 20333, 10000);
	}

	// Light Purple Maned Horse Bracelet 30-Day Pack
	private void use20059(Player player, boolean ctrl) {
		Functions.addItem(player, 20030, 1);
	}

	// Steam Beatle Mounting Bracelet 7 Day Pack
	private void use20494(Player player, boolean ctrl) {
		Functions.addItem(player, 20449, 1);
	}

	// Light Purple Maned Horse Mounting Bracelet 7 Day Pack
	private void use20493(Player player, boolean ctrl) {
		Functions.addItem(player, 20448, 1);
	}

	// Steam Beatle Mounting Bracelet Pack
	private void use20395(Player player, boolean ctrl) {
		Functions.addItem(player, 20396, 1);
	}

	// Ball Trapping Gnosian Agathion Pack Fix CosMOsiS
	private void use21000(Player player, boolean ctrl) {
		Functions.addItem(player, 20986, 1);
	}

	// Pumpkin Transformation Stick 7 Day Pack
	private void use13281(Player player, boolean ctrl) {
		Functions.addItem(player, 13253, 1);
	}

	// Kat the Cat Hat 7-Day Pack
	private void use13282(Player player, boolean ctrl) {
		Functions.addItem(player, 13239, 1);
	}

	// Feline Queen Hat 7-Day Pack
	private void use13283(Player player, boolean ctrl) {
		Functions.addItem(player, 13240, 1);
	}

	// Monster Eye Hat 7-Day Pack
	private void use13284(Player player, boolean ctrl) {
		Functions.addItem(player, 13241, 1);
	}

	// Brown Bear Hat 7-Day Pack
	private void use13285(Player player, boolean ctrl) {
		Functions.addItem(player, 13242, 1);
	}

	// Fungus Hat 7-Day Pack
	private void use13286(Player player, boolean ctrl) {
		Functions.addItem(player, 13243, 1);
	}

	// Skull Hat 7-Day Pack
	private void use13287(Player player, boolean ctrl) {
		Functions.addItem(player, 13244, 1);
	}

	// Ornithomimus Hat 7-Day Pack
	private void use13288(Player player, boolean ctrl) {
		Functions.addItem(player, 13245, 1);
	}

	// Feline King Hat 7-Day Pack
	private void use13289(Player player, boolean ctrl) {
		Functions.addItem(player, 13246, 1);
	}

	// Kai the Cat Hat 7-Day Pack
	private void use13290(Player player, boolean ctrl) {
		Functions.addItem(player, 13247, 1);
	}

	// Sudden Agathion 7 Day Pack
	private void use14267(Player player, boolean ctrl) {
		Functions.addItem(player, 14093, 1);
	}

	// Shiny Agathion 7 Day Pack
	private void use14268(Player player, boolean ctrl) {
		Functions.addItem(player, 14094, 1);
	}

	// Sobbing Agathion 7 Day Pack
	private void use14269(Player player, boolean ctrl) {
		Functions.addItem(player, 14095, 1);
	}

	// Agathion of Love 7-Day Pack
	private void use13280(Player player, boolean ctrl) {
		Functions.addItem(player, 20201, 1);
	}

	// ****** End Item Mall ******

	// ****** Belts ******
	// Gludio Supply Box - Belt: Grade B, C
	private void use13713(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Belt: Grade B, C
	private void use13714(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Belt: Grade B, C
	private void use13715(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Belt: Grade B, C
	private void use13716(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Belt: Grade B, C
	private void use13717(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Belt: Grade B, C
	private void use13718(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Belt: Grade B, C
	private void use13719(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Belt: Grade B, C
	private void use13720(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Belt: Grade B, C
	private void use13721(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13894, 1); // Cloth Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13895, 1); // Leather Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Gludio Supply Box - Belt: Grade S, A
	private void use14549(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Belt: Grade S, A
	private void use14550(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Belt: Grade S, A
	private void use14551(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Belt: Grade S, A
	private void use14552(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Belt: Grade S, A
	private void use14553(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Belt: Grade S, A
	private void use14554(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Belt: Grade S, A
	private void use14555(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Belt: Grade S, A
	private void use14556(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Belt: Grade S, A
	private void use14557(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13896, 1); // Iron Belt
		else if (Rnd.chance(50))
			Functions.addItem(player, 13897, 1); // Mithril Belt
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// ****** Magic Pins ******
	// Gludio Supply Box - Magic Pin: Grade B, C
	private void use13695(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Pin: Grade B, C
	private void use13696(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Pin: Grade B, C
	private void use13697(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Pin: Grade B, C
	private void use13698(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Pin: Grade B, C
	private void use13699(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))

			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Pin: Grade B, C
	private void use13700(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Pin: Grade B, C
	private void use13701(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Pin: Grade B, C
	private void use13702(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Pin: Grade B, C
	private void use13703(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13898, 1); // Sealed Magic Pin (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13899, 1); // Sealed Magic Pin (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Gludio Supply Box - Magic Pin: Grade S, A
	private void use14531(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Pin: Grade S, A
	private void use14532(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Pin: Grade S, A
	private void use14533(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Pin: Grade S, A
	private void use14534(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Pin: Grade S, A
	private void use14535(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Pin: Grade S, A
	private void use14536(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Pin: Grade S, A
	private void use14537(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Pin: Grade S, A
	private void use14538(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Pin: Grade S, A
	private void use14539(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13900, 1); // Sealed Magic Pin (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13901, 1); // Sealed Magic Pin (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// ****** Magic Pouchs ******
	// Gludio Supply Box - Magic Pouch: Grade B, C
	private void use13704(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Pouch: Grade B, C
	private void use13705(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Pouch: Grade B, C
	private void use13706(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Pouch: Grade B, C
	private void use13707(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Pouch: Grade B, C
	private void use13708(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Pouch: Grade B, C
	private void use13709(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Pouch: Grade B, C
	private void use13710(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Pouch: Grade B, C
	private void use13711(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Pouch: Grade B, C
	private void use13712(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13918, 1); // Sealed Magic Pouch (C-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13919, 1); // Sealed Magic Pouch (B-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Gludio Supply Box - Magic Pouch: Grade S, A
	private void use14540(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Pouch: Grade S, A
	private void use14541(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Pouch: Grade S, A
	private void use14542(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Pouch: Grade S, A
	private void use14543(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Pouch: Grade S, A
	private void use14544(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Pouch: Grade S, A
	private void use14545(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Pouch: Grade S, A
	private void use14546(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Pouch: Grade S, A
	private void use14547(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Pouch: Grade S, A
	private void use14548(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 13920, 1); // Sealed Magic Pouch (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 13921, 1); // Sealed Magic Pouch (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// ****** Magic Rune Clip ******
	// Gludio Supply Box - Magic Rune Clip: Grade S, A
	private void use14884(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Rune Clip: Grade S, A
	private void use14885(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Rune Clip: Grade S, A
	private void use14886(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Rune Clip: Grade S, A
	private void use14887(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Rune Clip: Grade S, A
	private void use14888(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Rune Clip: Grade S, A
	private void use14889(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Rune Clip: Grade S, A
	private void use14890(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Rune Clip: Grade S, A
	private void use14891(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Rune Clip: Grade S, A
	private void use14892(Player player, boolean ctrl) {
		if (Rnd.chance(50))
			Functions.addItem(player, 14902, 1); // Sealed Magic Rune Clip (A-Grade)
		else if (Rnd.chance(50))
			Functions.addItem(player, 14903, 1); // Sealed Magic Rune Clip (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// ****** Magic Ornament ******
	// Gludio Supply Box - Magic Ornament: Grade S, A
	private void use14893(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Dion Supply Box - Magic Ornament: Grade S, A
	private void use14894(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Giran Supply Box - Magic Ornament: Grade S, A
	private void use14895(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Oren Supply Box - Magic Ornament: Grade S, A
	private void use14896(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Aden Supply Box - Magic Ornament: Grade S, A
	private void use14897(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Innadril Supply Box - Magic Ornament: Grade S, A
	private void use14898(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Goddard Supply Box - Magic Ornament: Grade S, A
	private void use14899(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Rune Supply Box - Magic Ornament: Grade S, A
	private void use14900(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Schuttgart Supply Box - Magic Ornament: Grade S, A
	private void use14901(Player player, boolean ctrl) {
		if (Rnd.chance(20))
			Functions.addItem(player, 14904, 1); // Sealed Magic Ornament (A-Grade)
		else if (Rnd.chance(20))
			Functions.addItem(player, 14905, 1); // Sealed Magic Ornament (S-Grade)
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	// Gift from Santa Claus
	private void use14616(Player player, boolean ctrl) {
		// Santa Claus' Weapon Exchange Ticket - 12 Hour Expiration Period
		Functions.addItem(player, 20107, 1);

		// Christmas Red Sock
		Functions.addItem(player, 14612, 1);

		// Special Christmas Tree
		if (Rnd.chance(30))
			Functions.addItem(player, 5561, 1);

		// Christmas Tree
		if (Rnd.chance(50))
			Functions.addItem(player, 5560, 1);

		// Agathion Seal Bracelet - Rudolph
		if (Functions.getItemCount(player, 10606) == 0 && Rnd.chance(5))
			Functions.addItem(player, 10606, 1);

		// Agathion Seal Bracelet: Rudolph - 30
		if (Functions.getItemCount(player, 20094) == 0 && Rnd.chance(3))
			Functions.addItem(player, 20094, 1);

		// Chest of Experience (Event)
		if (Rnd.chance(30))
			Functions.addItem(player, 20575, 1);
	}

	// Chest of Experience (Event)
	private void use20575(Player player, boolean ctrl) {
		Functions.addItem(player, 20335, 1); // Rune of Experience: 30% - 5 hour limited time
		Functions.addItem(player, 20341, 1); // Rune of SP 30% - 5 Hour Expiration Period
	}

	// Nepal Snow Agathion Pack
	private void use20804(Player player, boolean ctrl) {
		Functions.addItem(player, 20782, 1);
	}

	// Nepal Snow Agathion 7-Day Pack - Snow's Haste
	private void use20807(Player player, boolean ctrl) {
		Functions.addItem(player, 20785, 1);
	}

	// Round Ball Snow Agathion Pack
	private void use20805(Player player, boolean ctrl) {
		Functions.addItem(player, 20783, 1);
	}

	// Round Ball Snow Agathion 7-Day Pack - Snow's Acumen
	private void use20808(Player player, boolean ctrl) {
		Functions.addItem(player, 20786, 1);
	}

	// Ladder Snow Agathion Pack
	private void use20806(Player player, boolean ctrl) {
		Functions.addItem(player, 20784, 1);
	}

	// Ladder Snow Agathion 7-Day Pack - Snow's Wind Walk
	private void use20809(Player player, boolean ctrl) {
		Functions.addItem(player, 20787, 1);
	}

	// Iken Agathion Pack
	private void use20842(Player player, boolean ctrl) {
		Functions.addItem(player, 20818, 1);
	}

	// Iken Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20843(Player player, boolean ctrl) {
		Functions.addItem(player, 20819, 1);
	}

	// Lana Agathion Pack
	private void use20844(Player player, boolean ctrl) {
		Functions.addItem(player, 20820, 1);
	}

	// Lana Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20845(Player player, boolean ctrl) {
		Functions.addItem(player, 20821, 1);
	}

	// Gnocian Agathion Pack
	private void use20846(Player player, boolean ctrl) {
		Functions.addItem(player, 20822, 1);
	}

	// Gnocian Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20847(Player player, boolean ctrl) {
		Functions.addItem(player, 20823, 1);
	}

	// Orodriel Agathion Pack
	private void use20848(Player player, boolean ctrl) {
		Functions.addItem(player, 20824, 1);
	}

	// Orodriel Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20849(Player player, boolean ctrl) {
		Functions.addItem(player, 20825, 1);
	}

	// Lakinos Agathion Pack
	private void use20850(Player player, boolean ctrl) {
		Functions.addItem(player, 20826, 1);
	}

	// Lakinos Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20851(Player player, boolean ctrl) {
		Functions.addItem(player, 20827, 1);
	}

	// Mortia Agathion Pack
	private void use20852(Player player, boolean ctrl) {
		Functions.addItem(player, 20828, 1);
	}

	// Mortia Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20853(Player player, boolean ctrl) {
		Functions.addItem(player, 20829, 1);
	}

	// Hayance Agathion Pack
	private void use20854(Player player, boolean ctrl) {
		Functions.addItem(player, 20830, 1);
	}

	// Hayance Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20855(Player player, boolean ctrl) {
		Functions.addItem(player, 20831, 1);
	}

	// Meruril Agathion Pack
	private void use20856(Player player, boolean ctrl) {
		Functions.addItem(player, 20832, 1);
	}

	// Meruril Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20857(Player player, boolean ctrl) {
		Functions.addItem(player, 20833, 1);
	}

	// Taman ze Lapatui Agathion Pack
	private void use20858(Player player, boolean ctrl) {
		Functions.addItem(player, 20834, 1);
	}

	// Taman ze Lapatui Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20859(Player player, boolean ctrl) {
		Functions.addItem(player, 20835, 1);
	}

	// Kaurin Agathion Pack
	private void use20860(Player player, boolean ctrl) {
		Functions.addItem(player, 20836, 1);
	}

	// Kaurin Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20861(Player player, boolean ctrl) {
		Functions.addItem(player, 20837, 1);
	}

	// Ahertbein Agathion Pack
	private void use20862(Player player, boolean ctrl) {
		Functions.addItem(player, 20838, 1);
	}

	// Ahertbein Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20863(Player player, boolean ctrl) {
		Functions.addItem(player, 20839, 1);
	}

	// Naonin Agathion Pack
	private void use20864(Player player, boolean ctrl) {
		Functions.addItem(player, 20840, 1);
	}

	// Rocket Gun Hat Pack Continuous Fireworks
	private void use20811(Player player, boolean ctrl) {
		Functions.addItem(player, 20789, 1);
	}

	// Yellow Paper Hat 7-Day Pack Bless the Body
	private void use20812(Player player, boolean ctrl) {
		Functions.addItem(player, 20790, 1);
	}

	// Pink Paper Mask Set 7-Day Pack Bless the Soul
	private void use20813(Player player, boolean ctrl) {
		Functions.addItem(player, 20791, 1);
	}

	// Flavorful Cheese Hat Pack
	private void use20814(Player player, boolean ctrl) {
		Functions.addItem(player, 20792, 1);
	}

	// Sweet Cheese Hat Pack
	private void use20815(Player player, boolean ctrl) {
		Functions.addItem(player, 20793, 1);
	}

	// Flavorful Cheese Hat 7-Day Pack Scent of Flavorful Cheese
	private void use20816(Player player, boolean ctrl) {
		Functions.addItem(player, 20794, 1);
	}

	// Sweet Cheese Hat 7-Day Pack Scent of Sweet Cheese
	private void use20817(Player player, boolean ctrl) {
		Functions.addItem(player, 20795, 1);
	}

	// Flame Box Pack
	private void use20810(Player player, boolean ctrl) {
		Functions.addItem(player, 20725, 1);
	}

	// Naonin Agathion 7-Day Pack Prominent Outsider Adventurer's Ability
	private void use20865(Player player, boolean ctrl) {
		Functions.addItem(player, 20841, 1);
	}

	// Shiny Mask of Giant Hercules 7 day Pack
	private void use20748(Player player, boolean ctrl) {
		Functions.addItem(player, 20743, 1);
	}

	// Shiny Mask of Silent Scream 7 day Pack
	private void use20749(Player player, boolean ctrl) {
		Functions.addItem(player, 20744, 1);
	}

	// Shiny Spirit of Wrath Mask 7 day Pack
	private void use20750(Player player, boolean ctrl) {
		Functions.addItem(player, 20745, 1);
	}

	// Shiny Undecaying Corpse Mask 7 Day Pack
	private void use20751(Player player, boolean ctrl) {
		Functions.addItem(player, 20746, 1);
	}

	// Shiny Planet X235 Alien Mask 7 day Pack
	private void use20752(Player player, boolean ctrl) {
		Functions.addItem(player, 20747, 1);
	}

	private static final int[] SOI_books = { 14209, // Forgotten Scroll - Hide
			14212, // Forgotten Scroll - Enlightenment - Wizard
			14213, // Forgotten Scroll - Enlightenment - Healer
			10554, // Forgotten Scroll - Anti-Magic Armor
			14208, // Forgotten Scroll - Final Secret
			10577 // Forgotten Scroll - Excessive Loyalty
	};

	// Jewel Ornamented Duel Supplies
	private void use13777(Player player, boolean ctrl) {
		int rnd = Rnd.get(100);
		if (rnd <= 65) {
			Functions.addItem(player, 9630, 3); // 3 Orichalcum
			Functions.addItem(player, 9629, 3); // 3 Adamantine
			Functions.addItem(player, 9628, 4); // 4 Leonard
			Functions.addItem(player, 8639, 6); // 6 Elixir of CP (S-Grade)
			Functions.addItem(player, 8627, 6); // 6 Elixir of Life (S-Grade)
			Functions.addItem(player, 8633, 6); // 6 Elixir of Mental Strength (S-Grade)
		} else if (rnd <= 95)
			Functions.addItem(player, SOI_books[Rnd.get(SOI_books.length)], 1);
		else
			Functions.addItem(player, 14027, 1); // Collection Agathion Summon Bracelet
	}

	// Mother-of-Pearl Ornamented Duel Supplies
	private void use13778(Player player, boolean ctrl) {
		int rnd = Rnd.get(100);
		if (rnd <= 65) {
			Functions.addItem(player, 9630, 2); // 3 Orichalcum
			Functions.addItem(player, 9629, 2); // 3 Adamantine
			Functions.addItem(player, 9628, 3); // 4 Leonard
			Functions.addItem(player, 8639, 5); // 5 Elixir of CP (S-Grade)
			Functions.addItem(player, 8627, 5); // 5 Elixir of Life (S-Grade)
			Functions.addItem(player, 8633, 5); // 5 Elixir of Mental Strength (S-Grade)
		} else if (rnd <= 95)
			Functions.addItem(player, SOI_books[Rnd.get(SOI_books.length)], 1);
		else
			Functions.addItem(player, 14027, 1); // Collection Agathion Summon Bracelet
	}

	// Gold-Ornamented Duel Supplies
	private void use13779(Player player, boolean ctrl) {
		int rnd = Rnd.get(100);
		if (rnd <= 65) {
			Functions.addItem(player, 9630, 1); // 1 Orichalcum
			Functions.addItem(player, 9629, 1); // 1 Adamantine
			Functions.addItem(player, 9628, 2); // 2 Leonard
			Functions.addItem(player, 8639, 4); // 4 Elixir of CP (S-Grade)
			Functions.addItem(player, 8627, 4); // 4 Elixir of Life (S-Grade)
			Functions.addItem(player, 8633, 4); // 4 Elixir of Mental Strength (S-Grade)
		} else if (rnd <= 95)
			Functions.addItem(player, SOI_books[Rnd.get(SOI_books.length)], 1);
		else
			Functions.addItem(player, 14027, 1); // Collection Agathion Summon Bracelet
	}

	// Silver-Ornamented Duel Supplies
	private void use13780(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 4); // 4 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 4); // 4 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 4); // 4 Elixir of Mental Strength (S-Grade)
	}

	// Bronze-Ornamented Duel Supplies
	private void use13781(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 4); // 4 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 4); // 4 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 4); // 4 Elixir of Mental Strength (S-Grade)
	}

	// Non-Ornamented Duel Supplies
	private void use13782(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 3); // 3 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 3); // 3 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 3); // 3 Elixir of Mental Strength (S-Grade)
	}

	// Weak-Looking Duel Supplies
	private void use13783(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 3); // 3 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 3); // 3 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 3); // 3 Elixir of Mental Strength (S-Grade)
	}

	// Sad-Looking Duel Supplies
	private void use13784(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 3); // 3 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 3); // 3 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 3); // 3 Elixir of Mental Strength (S-Grade)
	}

	// Poor-Looking Duel Supplies
	private void use13785(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 2); // 2 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 2); // 2 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 2); // 2 Elixir of Mental Strength (S-Grade)
	}

	// Worthless Duel Supplies
	private void use13786(Player player, boolean ctrl) {
		Functions.addItem(player, 8639, 1); // 1 Elixir of CP (S-Grade)
		Functions.addItem(player, 8627, 1); // 1 Elixir of Life (S-Grade)
		Functions.addItem(player, 8633, 1); // 1 Elixir of Mental Strength (S-Grade)
	}

	// Kahman's Supply Box
	private void use14849(Player player, boolean ctrl) {
		int[] list = new int[] { 9625, 9626 }; // codex_of_giant_forgetting, codex_of_giant_training
		int[] chances = new int[] { 100, 80 };
		int[] counts = new int[] { 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	// Big Stakato Cocoon
	private void use14834(Player player, boolean ctrl) {
		int[][] items = new int[][] { { 9575, 1 }, // rare_80_s
				{ 10485, 1 }, // rare_82_s
				{ 10577, 1 }, // sb_excessive_loyalty
				{ 14209, 1 }, // sb_hide1
				{ 14208, 1 }, // sb_final_secret1
				{ 14212, 1 }, // sb_enlightenment_wizard1
				{ 960, 1 }, // scrl_of_ench_am_s
				{ 9625, 1 }, // codex_of_giant_forgetting
				{ 9626, 1 }, // codex_of_giant_training
				{ 959, 1 }, // scrl_of_ench_wp_s
				{ 10373, 1 }, // rp_icarus_sowsword_i
				{ 10374, 1 }, // rp_icarus_disperser_i
				{ 10375, 1 }, // rp_icarus_spirits_i
				{ 10376, 1 }, // rp_icarus_heavy_arms_i
				{ 10377, 1 }, // rp_icarus_trident_i
				{ 10378, 1 }, // rp_icarus_chopper_i
				{ 10379, 1 }, // rp_icarus_knuckle_i
				{ 10380, 1 }, // rp_icarus_wand_i
				{ 10381, 1 } }; // rp_icarus_accipiter_i
		double[] chances = new double[] { 2.77, 2.31, 3.2, 3.2, 3.2, 3.2, 6.4, 3.2, 2.13, 0.64, 1.54, 1.54, 1.54, 1.54,
				1.54, 1.54, 1.54, 1.54, 1.54 };
		extractRandomOneItem(player, items, chances);
	}

	// Small Stakato Cocoon
	private void use14833(Player player, boolean ctrl) {
		int[][] items = new int[][] { { 9575, 1 }, // rare_80_s
				{ 10485, 1 }, // rare_82_s
				{ 10577, 1 }, // sb_excessive_loyalty
				{ 14209, 1 }, // sb_hide1
				{ 14208, 1 }, // sb_final_secret1
				{ 14212, 1 }, // sb_enlightenment_wizard1
				{ 960, 1 }, // scrl_of_ench_am_s
				{ 9625, 1 }, // codex_of_giant_forgetting
				{ 9626, 1 }, // codex_of_giant_training
				{ 959, 1 }, // scrl_of_ench_wp_s
				{ 10373, 1 }, // rp_icarus_sowsword_i
				{ 10374, 1 }, // rp_icarus_disperser_i
				{ 10375, 1 }, // rp_icarus_spirits_i
				{ 10376, 1 }, // rp_icarus_heavy_arms_i
				{ 10377, 1 }, // rp_icarus_trident_i
				{ 10378, 1 }, // rp_icarus_chopper_i
				{ 10379, 1 }, // rp_icarus_knuckle_i
				{ 10380, 1 }, // rp_icarus_wand_i
				{ 10381, 1 } }; // rp_icarus_accipiter_i
		double[] chances = new double[] { 2.36, 1.96, 2.72, 2.72, 2.72, 2.72, 5.44, 2.72, 1.81, 0.54, 1.31, 1.31, 1.31,
				1.31, 1.31, 1.31, 1.31, 1.31, 1.31 };
		extractRandomOneItem(player, items, chances);
	}

	private void use13988(Player player, boolean ctrl) {
		int[] list = new int[] { 9442, 9443, 9444, 9445, 9446, 9447, 9448, 9450, 10252, 10253, 10215, 10216, 10217,
				10218, 10219, 10220, 10221, 10222, 10223 };
		int[] chances = new int[] { 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 40, 40, 40, 40, 40, 40, 40, 40, 40 };
		int[] counts = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	private void use13989(Player player, boolean ctrl) {
		int[] list = new int[] { 9514, 9515, 9516, 9517, 9518, 9519, 9520, 9521, 9522, 9523, 9524, 9525, 9526, 9527,
				9528 };
		int[] chances = new int[] { 50, 63, 70, 75, 75, 50, 63, 70, 75, 75, 50, 63, 70, 75, 75 };
		int[] counts = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	// Pathfinder's Reward - D-Grade
	private void use13003(Player player, boolean ctrl) {
		if (Rnd.chance(3.2))
			Functions.addItem(player, 947, 1); // Scroll: Enchant Weapon B
	}

	// Pathfinder's Reward - C-Grade
	private void use13004(Player player, boolean ctrl) {
		if (Rnd.chance(1.6111))
			Functions.addItem(player, 729, 1); // Scroll: Enchant Weapon A
	}

	// Pathfinder's Reward - B-Grade
	private void use13005(Player player, boolean ctrl) {
		if (Rnd.chance(1.14))
			Functions.addItem(player, 959, 1); // Scroll: Enchant Weapon S
	}

	// Pathfinder's Reward - A-Grade
	private void use13006(Player player, boolean ctrl) {
		int[][] items = new int[][] { { 9546, 1 }, { 9548, 1 }, { 9550, 1 }, { 959, 1 }, { 9442, 1 }, { 9443, 1 },
				{ 9444, 1 }, { 9445, 1 }, { 9446, 1 }, { 9447, 1 }, { 9448, 1 }, { 9449, 1 }, { 9450, 1 },
				{ 10252, 1 }, { 10253, 1 }, { 15645, 1 }, { 15646, 1 }, { 15647, 1 } };
		double[] chances = new double[] { 19.8, 19.8, 19.8, 1.98, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
				0.1, 1, 1, 1 };
		extractRandomOneItem(player, items, chances);
	}

	// Pathfinder's Reward - S-Grade
	private void use13007(Player player, boolean ctrl) {
		int[][] items = new int[][] { { 9546, 1 }, { 9548, 1 }, { 9550, 1 }, { 959, 1 }, { 10215, 1 }, { 10216, 1 },
				{ 10217, 1 }, { 10218, 1 }, { 10219, 1 }, { 10220, 1 }, { 10221, 1 }, { 10222, 1 }, { 10223, 1 } };
		double[] chances = new double[] { 26.4, 26.4, 26.4, 3.84, 0.13, 0.13, 0.13, 0.13, 0.13, 0.13, 0.13, 0.13, 0.13 };
		extractRandomOneItem(player, items, chances);
	}

	// Pathfinder's Reward - AU Karm
	private void use13270(Player player, boolean ctrl) {
		if (Rnd.chance(30))
			Functions.addItem(player, 13236, 1);
	}

	// Pathfinder's Reward - AR Karm
	private void use13271(Player player, boolean ctrl) {
		if (Rnd.chance(30))
			Functions.addItem(player, 13237, 1);
	}

	// Pathfinder's Reward - AE Karm
	private void use13272(Player player, boolean ctrl) {
		if (Rnd.chance(30))
			Functions.addItem(player, 13238, 1);
	}

	private void use13990(Player player, boolean ctrl) {
		int[] list = new int[] { 6364, 6365, 6366, 6367, 6368, 6369, 6370, 6371, 6372, 6534, 6579, 7575 };
		int[] chances = new int[] { 83, 83, 83, 83, 83, 83, 83, 83, 83, 83, 83, 83 };
		int[] counts = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	private void use13991(Player player, boolean ctrl) {
		int[] list = new int[] { 6674, 6675, 6676, 6677, 6679, 6680, 6681, 6682, 6683, 6684, 6685, 6686, 6687 };
		int[] chances = new int[] { 70, 80, 95, 95, 90, 55, 95, 95, 90, 55, 95, 95, 90 };
		int[] counts = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	private void use13992(Player player, boolean ctrl) {
		int[] list = new int[] { 6724, 6725, 6726 };
		int[] chances = new int[] { 25, 32, 42 };
		int[] counts = new int[] { 1, 1, 1 };
		extract_item_r(list, counts, chances, player);
	}

	// Droph's Support Items
	private void use14850(Player player, boolean ctrl) {
		int rndAA = Rnd.get(80000, 100000);
		Functions.addItem(player, 5575, rndAA); // Ancient Adena
	}

	// Greater Elixir Gift Box (No-Grade)
	private void use14713(Player player, boolean ctrl) {
		Functions.addItem(player, 14682, 50); // Greater Elixir of Life (No-Grade)
		Functions.addItem(player, 14688, 50); // Greater Elixir of Mental Strength (No-Grade)
		Functions.addItem(player, 14694, 50); // Greater Elixir of CP (No Grade)
	}

	// Greater Elixir Gift Box (D-Grade)
	private void use14714(Player player, boolean ctrl) {
		Functions.addItem(player, 14683, 50); // Greater Elixir of Life (D-Grade)
		Functions.addItem(player, 14689, 50); // Greater Elixir of Mental Strength (D-Grade)
		Functions.addItem(player, 14695, 50); // Greater Elixir of CP (D Grade)
	}

	// Greater Elixir Gift Box (C-Grade)
	private void use14715(Player player, boolean ctrl) {
		Functions.addItem(player, 14684, 50); // Greater Elixir of Life (C-Grade)
		Functions.addItem(player, 14690, 50); // Greater Elixir of Mental Strength (C-Grade)
		Functions.addItem(player, 14696, 50); // Greater Elixir of CP (C Grade)
	}

	// Greater Elixir Gift Box (B-Grade)
	private void use14716(Player player, boolean ctrl) {
		Functions.addItem(player, 14685, 50); // Greater Elixir of Life (B-Grade)
		Functions.addItem(player, 14691, 50); // Greater Elixir of Mental Strength (B-Grade)
		Functions.addItem(player, 14697, 50); // Greater Elixir of CP (B Grade)
	}

	// Greater Elixir Gift Box (A-Grade)
	private void use14717(Player player, boolean ctrl) {
		Functions.addItem(player, 14686, 50); // Greater Elixir of Life (A-Grade)
		Functions.addItem(player, 14692, 50); // Greater Elixir of Mental Strength (A-Grade)
		Functions.addItem(player, 14698, 50); // Greater Elixir of CP (A Grade)
	}

	// Greater Elixir Gift Box (S-Grade)
	private void use14718(Player player, boolean ctrl) {
		Functions.addItem(player, 14687, 50); // Greater Elixir of Life (S-Grade)
		Functions.addItem(player, 14693, 50); // Greater Elixir of Mental Strength (S-Grade)
		Functions.addItem(player, 14699, 50); // Greater Elixir of CP (S Grade)
	}

	// Freya's Gift
	private void use17138(Player player, boolean ctrl) {
		int[][] items = new int[][] { { 16026, 1 }, { 9627, 1 }, { 17139, 1 }, { 17140, 1 }, { 14052, 1 }, { 6622, 1 },
				{ 2134, 2 }, { 14701, 1 } };
		double[] chances = new double[] { 0.0001, 0.1417, 1.4172, 1.4172, 2.8345, 18.424, 21.2585, 54.5068 };
		extractRandomOneItem(player, items, chances);
	}

	// Beginner Adventurer's Treasure Sack
	private void use21747(Player player, boolean ctrl) {
		int group = Rnd.get(7);
		int[] items = new int[0];
		if (group < 4) // Low D-Grade rewards
			items = new int[] { 312, 167, 220, 258, 178, 221, 123, 156, 291, 166, 274 };
		else if (group >= 4) // Low C-Grade rewards
			items = new int[] { 160, 298, 72, 193, 192, 281, 7887, 226, 2524, 191, 71, 263 };

		Functions.addItem(player, items[Rnd.get(items.length)], 1);
	}

	// Experienced Adventurer's Treasure Sack
	private void use21748(Player player, boolean ctrl) {
		int group = Rnd.get(10);
		int[] items = new int[0];
		if (group < 4) // Low B-Grade rewards
			items = new int[] { 78, 2571, 300, 284, 142, 267, 229, 148, 243, 92, 7892, 91 };
		else if (group >= 7 && group < 9) // Low A-Grade rewards
			items = new int[] { 98, 5233, 80, 235, 269, 288, 7884, 2504, 150, 7899, 212 };
		else if (group == 9) // Low S-Grade rewards
			items = new int[] { 6365, 6371, 6364, 6366, 6580, 7575, 6579, 6372, 6370, 6369, 6367 };

		Functions.addItem(player, items[Rnd.get(items.length)], 1);
	}

	// Great Adventurer's Treasure Sack
	private void use21749(Player player, boolean ctrl) {
		int group = Rnd.get(9);
		int[] items = new int[0];
		if (group < 5) // Top S-Grade rewards
			items = new int[] { 9447, 9384, 9449, 9380, 9448, 9443, 9450, 10253, 9445, 9442, 9446, 10004, 10252, 9376,
					9444 };
		else if (group >= 5 && group < 8) // S80-Grade rewards
			items = new int[] { 10226, 10217, 10224, 10215, 10225, 10223, 10220, 10415, 10216, 10221, 10219, 10218,
					10222 };
		else if (group == 8) // Low S84-Grade rewards
			items = new int[] { 13467, 13462, 13464, 13461, 13465, 13468, 13463, 13470, 13460, 52, 13466, 13459, 13457,
					13469, 13458 };

		Functions.addItem(player, items[Rnd.get(items.length)], 1);
	}

	// Golden Spice Crate
	private void use15482(Player player, boolean ctrl) {
		if (Rnd.chance(10)) {
			Functions.addItem(player, 15474, 40);
			if (Rnd.chance(50))
				Functions.addItem(player, 15476, 5);
			else
				Functions.addItem(player, 15478, 5);
		} else
			Functions.addItem(player, 15474, 50);
	}

	// Crystal Spice Crate
	private void use15483(Player player, boolean ctrl) {
		if (Rnd.chance(10)) {
			Functions.addItem(player, 15475, 40);
			if (Rnd.chance(50))
				Functions.addItem(player, 15477, 5);
			else
				Functions.addItem(player, 15479, 5);
		} else
			Functions.addItem(player, 15475, 50);
	}

	// Gold Maned Lion Mounting Bracelet 7 Day Pack
	private void use14231(Player player, boolean ctrl) {
		Functions.addItem(player, 14053, 1);
	}

	// Steam Beatle Mounting Bracelet 7 Day Pack
	private void use14232(Player player, boolean ctrl)
	{
		Functions.addItem(player, 14054, 1);
	}

	// Olympiad Treasure Chest
	private void use17169(Player player, boolean ctrl)
	{
		int[][] items = new int[][] { { 13750, 1 }, { 13751, 1 }, { 13754, 1 }, { 13753, 1 }, { 13752, 1 }, { 6622, 1 }, { 8621, 1 } };
		double[] chances = new double[] { 34.7, 12.3, 2.65, 1.2, 1.98, 46.5, 5.4 };
		if (Rnd.chance(60))
			extractRandomOneItem(player, items, chances);
		int[] counts = { 100, 150, 200, 250, 300, 350 };
		Functions.addItem(player, 13722, counts[Rnd.get(counts.length)]);
	}

	// Birthday Present Pack
	private void use21169(Player player, boolean ctrl)
	{
		Functions.addItem(player, 21170, 3);
		Functions.addItem(player, 21595, 1);
		Functions.addItem(player, 13488, 1);
	}

	// (Not In Use)
	private void use53(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13024, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// (Not In Use)
	private void use54(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13025, 1, 3 } };

		double chances[] = new double[] {
		// chance
		72.0 };

		capsulate(player, items, chances);
	}

	// Shiny Agathion 7 Day Pack (Event)
	private void use55(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14102, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Sobbing Agathion 7 Day Pack (Event)
	private void use56(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14103, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// (Not In Use)
	private void use136(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14075, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// (Not In Use)
	private void use137(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14076, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// (Not In Use)
	private void use138(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14077, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Sudden Agathion 7 Day Pack
	private void use139(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14093, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Shiny Agathion 7 Day Pack
	private void use140(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14094, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Sobbing Agathion 7 Day Pack
	private void use141(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14095, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// (Not In Use)
	private void use163(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13026, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Sudden Agathion 7 Day Pack (Event)
	private void use170(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 14101, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (10 Honey Rice Cake Set)
	private void use5906(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 10, 10 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (9 Honey Rice Cake Set)
	private void use5907(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 9, 9 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (8 Honey Rice Cake Set)
	private void use5909(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 8, 8 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (7 Honey Rice Cake Set)
	private void use5910(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 7, 7 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (6 Honey Rice Cake Set)
	private void use5912(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 6, 6 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Five-Year Anniversary Gift Box (3 Honey Wheat Cake Set)
	private void use5913(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13420, 3, 3 }, { 13419, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// White Seed of Evil Lump
	private void use10205(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 9597, 2, 2 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Black Seed of Evil Lump
	private void use10206(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 9598, 2, 2 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// 1st Place Treasure Sack
	private void use10254(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 6373, 1, 1 }, { 6374, 1, 1 }, { 6375, 1, 1 }, { 6376, 1, 1 }, { 6377, 1, 1 }, { 6378, 1, 1 },
				{ 6379, 1, 1 }, { 6380, 1, 1 }, { 6381, 1, 1 }, { 6382, 1, 1 }, { 6383, 1, 1 }, { 6384, 1, 1 },
				{ 6385, 1, 1 }, { 6386, 1, 1 }, { 9570, 1, 1 }, { 9571, 1, 1 }, { 9572, 1, 1 }, { 5908, 1, 1 },
				{ 5911, 1, 1 }, { 5914, 1, 1 } };

		double chances[] = new double[] {
				// chance
				1.19, 1.84, 4.47, 4.47, 4.25, 3.01, 0.19, 4.47, 4.47, 3.01, 0.19, 4.47, 4.47, 3.01, 3.16, 3.16, 3.16,
				15.67, 15.67, 15.67 };

		capsulate(player, items, chances);
	}

	// My Teleport Spellbook 1-Sheet Pack
	private void use13079(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13015, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// My Teleport Scroll 30-Sheet Pack
	private void use13080(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13016, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Feather of Blessing 3-Sheet Pack
	private void use13082(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 10649, 3, 3 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Agathion of Love 30-Day Pack
	private void use13083(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13023, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Pumpkin Transformation Stick 30-Day Pack
	private void use13084(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12800, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Kamaloka Entrance Pass 3-Sheet Pack
	private void use13085(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 13010, 5, 5 }, { 13011, 5, 5 }, { 13012, 5, 5 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Kat the Cat Hat 30-Day Pack
	private void use13086(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12782, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Feline Queen Hat 30-Day Pack
	private void use13087(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12783, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Monster Eye Hat 30-Day Pack
	private void use13088(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12786, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Brown Bear Hat 30-Day Pack
	private void use13089(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12787, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Fungus Hat 30-Day Pack
	private void use13090(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12788, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Skull Hat 30-Day Pack
	private void use13091(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12789, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Ornithomimus Hat 30-Day Pack
	private void use13092(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12790, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Feline King Hat 30-Day Pack
	private void use13093(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12791, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Kai the Cat Hat 30-Day Pack
	private void use13094(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 12792, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// OX Stick 30-Day Pack
	private void use13095(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 12793, 1, 1 }, { 12794, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Rock-Paper-Scissors Stick 30-Day Pack
	private void use13096(Player player, boolean ctrl) {
		int items[][] = new int[][] {
				// itemId, min, max
				{ 12795, 1, 1 }, { 12796, 1, 1 }, { 12797, 1, 1 } };

		double chances[] = new double[] {
				// chance
				100.0, 100.0, 100.0 };

		capsulate(player, items, chances);
	}

	// Purple Maned Horse Bracelet Pack
	private void use13097(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13022, 1, 1 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// Color Name 3-Sheet Pack
	private void use13098(Player player, boolean ctrl) {
		int items[][] = new int[][] {
		// itemId, min, max
		{ 13021, 3, 3 } };

		double chances[] = new double[] {
		// chance
		100.0 };

		capsulate(player, items, chances);
	}

	// |========================================| Start Line by CosMOsiS |==========================================|
	private void use21225(Player player, boolean ctrl) {
		Functions.addItem(player, 10178, 3);
	}

	private void use21226(Player player, boolean ctrl) {
		Functions.addItem(player, 10178, 3);
	}

	private void use21229(Player player, boolean ctrl) {
		Functions.addItem(player, 10179, 3);
	}

	private void use21230(Player player, boolean ctrl) {
		Functions.addItem(player, 10179, 3);
	}

	private void use21233(Player player, boolean ctrl) {
		Functions.addItem(player, 14739, 3);
	}

	private void use21234(Player player, boolean ctrl) {
		Functions.addItem(player, 14739, 3);
	}

	private void use21237(Player player, boolean ctrl) {
		Functions.addItem(player, 21239, 3);
	}

	private void use21238(Player player, boolean ctrl) {
		Functions.addItem(player, 21240, 3);
	}

	private void use21240(Player player, boolean ctrl) {
		Functions.addItem(player, 21243, 3);
	}

	private void use21241(Player player, boolean ctrl) {
		Functions.addItem(player, 21244, 3);
	}

	// 6th Anniversary Party Gift Box (2003) by (CosMOsiS)
	private void use15404(Player player, boolean ctrl) {
		Functions.addItem(player, 15418, 1);
	}

	// 6th Anniversary Party Gift Box (2004) by (CosMOsiS)
	private void use15405(Player player, boolean ctrl) {
		Functions.addItem(player, 15419, 1);
	}

	// 6th Anniversary Party Gift Box (2005) by (CosMOsiS)
	private void use15406(Player player, boolean ctrl) {
		Functions.addItem(player, 15420, 1);
	}

	// 6th Anniversary Party Gift Box (2006) by (CosMOsiS)
	private void use15407(Player player, boolean ctrl) {
		Functions.addItem(player, 15421, 1);
	}

	// 6th Anniversary Party Gift Box (2007) by (CosMOsiS)
	private void use15408(Player player, boolean ctrl) {
		Functions.addItem(player, 15422, 1);
	}

	// 6th Anniversary Party Gift Box (2008) by (CosMOsiS)
	private void use15409(Player player, boolean ctrl) {
		Functions.addItem(player, 15423, 1);
	}

	// 6th Anniversary Party Gift Box (2009) by (CosMOsiS)
	private void use15410(Player player, boolean ctrl) {
		Functions.addItem(player, 15424, 1);
	}

	// 6th Anniversary Party Gift Set (2003) by (CosMOsiS)
	private void use15411(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15418, 1);
	}

	// 6th Anniversary Party Gift Set (2004) by (CosMOsiS)
	private void use15412(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15419, 1);
	}

	// 6th Anniversary Party Gift Set (2005) by (CosMOsiS)
	private void use15413(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15420, 1);
	}

	// 6th Anniversary Party Gift Set (2006) by (CosMOsiS)
	private void use15414(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15421, 1);
	}

	// 6th Anniversary Party Gift Set (2007) by (CosMOsiS)
	private void use15415(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15422, 1);
	}

	// 6th Anniversary Party Gift Set (2008) by (CosMOsiS)
	private void use15416(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15423, 1);
	}

	// 6th Anniversary Party Gift Set (2009) by (CosMOsiS)
	private void use15417(Player player, boolean ctrl) {
		Functions.addItem(player, 15425, 1);
		Functions.addItem(player, 15424, 1);
	}

	// Hero's Treasure Box by (CosMOsiS)
	private void use15425(Player player, boolean ctrl) {
		Functions.addItem(player, 15211, 1);
		Functions.addItem(player, 13273, 1);
		Functions.addItem(player, 15426, 1);
	}

	// Halloween Toy Chest by (CosMOsiS)
	private void use15434(Player player, boolean ctrl) { /* by CosMOsiS */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 15443, 1, 1 }, { 15444, 1, 1 }, { 15445, 1, 1 }, { 15446, 1, 1 }, { 15447, 1, 1 } };

		double chances[] = new double[] {
				// chance
				20.00, 20.00, 20.00, 20.00, 20.00 };

		capsulate(player, items, chances);
	}

	// Shiny Halloween Toy Chest by (CosMOsiS)
	private void use15435(Player player, boolean ctrl) { /* by CosMOsiS */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 15450, 1, 1 }, { 15451, 1, 1 }, { 15452, 1, 1 }, { 15453, 1, 1 }, { 15449, 1, 1 } };

		double chances[] = new double[] {
				// chance
				20.00, 20.00, 20.00, 20.00, 20.00 };

		capsulate(player, items, chances);
	}

	// Transformation Scroll Box by (CosMOsiS)
	private void use15437(Player player, boolean ctrl) { /* by CosMOsiS */
		int items[][] = new int[][] {
				// itemId, min, max
				{ 10131, 1, 1 }, { 10132, 1, 1 }, { 10133, 1, 1 } };

		double chances[] = new double[] {
				// chance
				33.00, 33.00, 34.00 };

		capsulate(player, items, chances);
	}

	// Shiny Planet X235 Alien's Mask Pack by (CosMOsiS)
	private void use15454(Player player, boolean ctrl) {
		Functions.addItem(player, 15449, 1);
	}

	// Shiny Super Strong Giant's Mask Pack by (CosMOsiS)
	private void use15455(Player player, boolean ctrl) {
		Functions.addItem(player, 15450, 1);
	}

	// Shiny Silent Scream's Mask Pack by (CosMOsiS)
	private void use15456(Player player, boolean ctrl) {
		Functions.addItem(player, 15451, 1);
	}

	// Shiny Wrathful Spirit's Mask Pack by (CosMOsiS)
	private void use15457(Player player, boolean ctrl) {
		Functions.addItem(player, 15452, 1);
	}

	// Shiny Unrotten Corpse's Mask Pack by (CosMOsiS)
	private void use15458(Player player, boolean ctrl) {
		Functions.addItem(player, 15453, 1);
	}

	// Halloween Nectar Pumpkin Pack by (CosMOsiS)
	private void use15459(Player player, boolean ctrl) {
		Functions.addItem(player, 15461, 1);
	}

	// Halloween King Pumpkin Pack by (CosMOsiS)
	private void use15460(Player player, boolean ctrl) {
		Functions.addItem(player, 15462, 1);
	}

	// Halloween King Pumpkin by (CosMOsiS)
	private void use15462(Player player, boolean ctrl) {
		Functions.addItem(player, 15461, 6);
		Functions.addItem(player, 15464, 1);
	}

	// Halloween Nectar Pumpkin Pack - event by (CosMOsiS)
	private void use15465(Player player, boolean ctrl) {
		Functions.addItem(player, 15461, 1);
	}

	// Halloween King Pumpkin Pack - event by (CosMOsiS)
	private void use15466(Player player, boolean ctrl) {
		Functions.addItem(player, 15462, 1);
	}

	// Color Title - event by (CosMOsiS)
	private void use21421(Player player, boolean ctrl) {
		Functions.addItem(player, 13021, 1);
	}

	// Color title - event by (CosMOsiS)
	private void use21422(Player player, boolean ctrl) {
		Functions.addItem(player, 13307, 1);
	}

	// Halloween King Pumpkin - event by (CosMOsiS)
	private void use15468(Player player, boolean ctrl) {
		Functions.addItem(player, 15461, 6);
		Functions.addItem(player, 15464, 1);
	} // |==========================================| END LINE by CosMOsiS |==========================================|

	// No Grade Beginner's Adventurer Support Pack by (SmokiMo)
	private void use20635(Player player, boolean ctrl) {
		Functions.addItem(player, 8973, 1);
		Functions.addItem(player, 10178, 3);
		Functions.addItem(player, 9035, 1);
		Functions.addItem(player, 9034, 1);
		Functions.addItem(player, 9033, 1);
		Functions.addItem(player, 9032, 1);
		Functions.addItem(player, 9031, 1);
		Functions.addItem(player, 9030, 1);
		Functions.addItem(player, 8977, 1);
		Functions.addItem(player, 10179, 3);
	}

		// D-GRADE FIGHTER SUPPORT PACK
	private void use20636(Player player, boolean ctrl) {
		Functions.addItem(player, 20639, 1);
		Functions.addItem(player, 20640, 1);
		Functions.addItem(player, 20641, 1);
		Functions.addItem(player, 20642, 1);
		Functions.addItem(player, 20643, 1);
		Functions.addItem(player, 20644, 1);
		Functions.addItem(player, 20645, 1);
		Functions.addItem(player, 20646, 1);
		Functions.addItem(player, 20647, 1);
		Functions.addItem(player, 20648, 1);
	}
	
	// D-Grade Mage Support Pack
	private void use20637(Player player, boolean ctrl) {
		Functions.addItem(player, 20645, 1);
		Functions.addItem(player, 20649, 1);
		Functions.addItem(player, 20650, 1);
		Functions.addItem(player, 20651, 1);
		Functions.addItem(player, 20652, 1);
		Functions.addItem(player, 20653, 1);
	}

	//  Beginner's Adventurer Reinforcement Pack 
	private void use20638(Player player, boolean ctrl) {
		Functions.addItem(player, 20415, 1);
		Functions.addItem(player, 21091, 1);
		Functions.addItem(player, 21092, 1);
	}

	private void use30276(Player player, boolean ctrl) { // Soulshot Pack (R-Grade)
		Functions.addItem(player, 177754, 5000);
	}

	private void use30277(Player player, boolean ctrl) { // Soulshot Large Pack (R-Grade)
		Functions.addItem(player, 17754, 10000);
	}

	private void use30278(Player player, boolean ctrl) { // Spiritshot Pack (R-Grade)
		Functions.addItem(player, 19441, 5000);
	}

	private void use30279(Player player, boolean ctrl) { // Spiritshot Large Pack (R-Grade)
		Functions.addItem(player, 19441, 10000);
	}

	private void use30280(Player player, boolean ctrl) { // Blessed Spiritshot Pack (R-Grade)
		Functions.addItem(player, 19442, 5000);
	}

	private void use30281(Player player, boolean ctrl) { // Blessed Spiritshot Large Pack (R-Grade)
		Functions.addItem(player, 19442, 10000);
	}

	private void use30282(Player player, boolean ctrl) { // Orichalcum Quiver
		Functions.addItem(player, 19443, 10000);
	}

	private void use30283(Player player, boolean ctrl) { // Orichalcum Bolt Container
		Functions.addItem(player, 18550, 1);
	}

	private void use30284(Player player, boolean ctrl) { // Requiem Weapon Box
		Functions.addItem(player, 18103, 1);
		Functions.addItem(player, 18104, 1);
		Functions.addItem(player, 18105, 1);
		Functions.addItem(player, 18106, 1);
		Functions.addItem(player, 18107, 1);
		Functions.addItem(player, 18109, 1);
		Functions.addItem(player, 18109, 1);
		Functions.addItem(player, 18110, 1);
		Functions.addItem(player, 18111, 1);
		Functions.addItem(player, 18112, 1);
		Functions.addItem(player, 18113, 1);
		Functions.addItem(player, 18114, 1);
		Functions.addItem(player, 18115, 1);
		Functions.addItem(player, 18116, 1);

	}

	private void use30285(Player player, boolean ctrl) { // Immortal Heavy Armor Box
		Functions.addItem(player, 18117, 1);
		Functions.addItem(player, 18118, 1);
		Functions.addItem(player, 18119, 1);
		Functions.addItem(player, 18120, 1);
		Functions.addItem(player, 18121, 1);
		Functions.addItem(player, 18122, 1);
	}

	private void use30286(Player player, boolean ctrl) { // Immortal Light Armor Box
		Functions.addItem(player, 18123, 1);
		Functions.addItem(player, 18124, 1);
		Functions.addItem(player, 18125, 1);
		Functions.addItem(player, 18126, 1);
		Functions.addItem(player, 18127, 1);

	}

	private void use30287(Player player, boolean ctrl) { // Immortal Robe Armor Box
		Functions.addItem(player, 18128, 1);
		Functions.addItem(player, 18129, 1);
		Functions.addItem(player, 18130, 1);
		Functions.addItem(player, 18131, 1);
		Functions.addItem(player, 18132, 1);
		Functions.addItem(player, 18133, 1);
	}

	private void use30288(Player player, boolean ctrl) { // Apocalypse Weapon Box
		Functions.addItem(player, 18137, 1);
		Functions.addItem(player, 18138, 1);
		Functions.addItem(player, 18139, 1);
		Functions.addItem(player, 18140, 1);
		Functions.addItem(player, 18141, 1);
		Functions.addItem(player, 18142, 1);
		Functions.addItem(player, 18143, 1);
		Functions.addItem(player, 18144, 1);
		Functions.addItem(player, 18145, 1);
		Functions.addItem(player, 18146, 1);
		Functions.addItem(player, 18147, 1);
		Functions.addItem(player, 18148, 1);
		Functions.addItem(player, 18149, 1);
		Functions.addItem(player, 18150, 1);
	}

	private void use30289(Player player, boolean ctrl) { // Twilight Heavy Armor Box
		Functions.addItem(player, 18151, 1);
		Functions.addItem(player, 18152, 1);
		Functions.addItem(player, 18153, 1);
		Functions.addItem(player, 18154, 1);
		Functions.addItem(player, 18155, 1);
		Functions.addItem(player, 18156, 1);
	}

	private void use30290(Player player, boolean ctrl) { // Twilight Light Armor Box
		Functions.addItem(player, 18157, 1);
		Functions.addItem(player, 18158, 1);
		Functions.addItem(player, 18159, 1);
		Functions.addItem(player, 18160, 1);
		Functions.addItem(player, 18161, 1);
	}

	private void use30291(Player player, boolean ctrl) { // Twilight Robe Armor Box
		Functions.addItem(player, 18162, 1);
		Functions.addItem(player, 18163, 1);
		Functions.addItem(player, 18164, 1);
		Functions.addItem(player, 18165, 1);
		Functions.addItem(player, 18166, 1);
		Functions.addItem(player, 18167, 1);
	}

	private void use30292(Player player, boolean ctrl) { // Immortal Accessory Box
		Functions.addItem(player, 18134, 1);
		Functions.addItem(player, 18135, 1);
		Functions.addItem(player, 18135, 1);
		Functions.addItem(player, 18136, 1);
		Functions.addItem(player, 18136, 1);
	}

	private void use30293(Player player, boolean ctrl) { // Twilight Accessory Box
		Functions.addItem(player, 18168, 1);
		Functions.addItem(player, 18169, 1);
		Functions.addItem(player, 18169, 1);
		Functions.addItem(player, 18170, 1);
		Functions.addItem(player, 18170, 1);
	}
	
	private void use22578(Player player, boolean ctrl) { // Set weapons C 30-days
		Functions.addItem(player, 15034, 1);
		Functions.addItem(player, 15038, 1);
		Functions.addItem(player, 15042, 1);
		Functions.addItem(player, 15046, 1);
		Functions.addItem(player, 15050, 1);
		Functions.addItem(player, 15054, 1);
		Functions.addItem(player, 15058, 1);
		Functions.addItem(player, 15062, 1);
		Functions.addItem(player, 15066, 1);
	}
	
	private void use22579(Player player, boolean ctrl) { // Set weapons B 30-days
		Functions.addItem(player, 15033, 1);
		Functions.addItem(player, 15037, 1);
		Functions.addItem(player, 15041, 1);
		Functions.addItem(player, 15045, 1);
		Functions.addItem(player, 15049, 1);
		Functions.addItem(player, 15053, 1);
		Functions.addItem(player, 15057, 1);
		Functions.addItem(player, 15061, 1);
		Functions.addItem(player, 15065, 1);
	}
	
	private void use22580(Player player, boolean ctrl) { // Set weapons A 30-days
		Functions.addItem(player, 15032, 1);
		Functions.addItem(player, 15036, 1);
		Functions.addItem(player, 15040, 1);
		Functions.addItem(player, 15044, 1);
		Functions.addItem(player, 15048, 1);
		Functions.addItem(player, 15052, 1);
		Functions.addItem(player, 15056, 1);
		Functions.addItem(player, 15060, 1);
		Functions.addItem(player, 15064, 1);
	}
	
	private void use22581(Player player, boolean ctrl) { // Set weapons S 90-days
		Functions.addItem(player, 21821, 1);
		Functions.addItem(player, 21822, 1);
		Functions.addItem(player, 21823, 1);
		Functions.addItem(player, 21824, 1);
		Functions.addItem(player, 21825, 1);
		Functions.addItem(player, 21826, 1);
		Functions.addItem(player, 21827, 1);
		Functions.addItem(player, 21828, 1);
		Functions.addItem(player, 21829, 1);
	}
	
	private void use22202(Player player, boolean ctrl) { // Set weapons S80 (Dynasty) 90-days
		Functions.addItem(player, 21833, 1);
		Functions.addItem(player, 21834, 1);
		Functions.addItem(player, 21835, 1);
		Functions.addItem(player, 21836, 1);
		Functions.addItem(player, 21837, 1);
		Functions.addItem(player, 21838, 1);
		Functions.addItem(player, 21839, 1);
		Functions.addItem(player, 21840, 1);
		Functions.addItem(player, 21841, 1);
	}
	
	private void use22583(Player player, boolean ctrl) { // Set armors C 30-days
		Functions.addItem(player, 15248, 1);
		Functions.addItem(player, 15252, 1);
		Functions.addItem(player, 15256, 1);
	}
	
	private void use22584(Player player, boolean ctrl) { // Set armors B 30-days
		Functions.addItem(player, 15247, 1);
		Functions.addItem(player, 15251, 1);
		Functions.addItem(player, 15255, 1);
	}
	
	private void use22585(Player player, boolean ctrl) { // Set armors A 30-days
		Functions.addItem(player, 15246, 1);
		Functions.addItem(player, 15250, 1);
		Functions.addItem(player, 15254, 1);
	}
	
	private void use21781(Player player, boolean ctrl) { // Set armor S heavy 90-days
		Functions.addItem(player, 21775, 1);
		Functions.addItem(player, 21776, 1);
		Functions.addItem(player, 21777, 1);
		Functions.addItem(player, 21778, 1);
		Functions.addItem(player, 21779, 1);
		Functions.addItem(player, 21780, 1);
	}
	
	private void use21786(Player player, boolean ctrl) { // Set armor S light 90-days
		Functions.addItem(player, 21782, 1);
		Functions.addItem(player, 21783, 1);
		Functions.addItem(player, 21784, 1);
		Functions.addItem(player, 21785, 1);
	}
	
	private void use21792(Player player, boolean ctrl) { // Set armor S robe 90-days
		Functions.addItem(player, 21787, 1);
		Functions.addItem(player, 21788, 1);
		Functions.addItem(player, 21789, 1);
		Functions.addItem(player, 21790, 1);
		Functions.addItem(player, 21791, 1);
	}
	
	private void use22586(Player player, boolean ctrl) { // Set armors S 90-days
		Functions.addItem(player, 21781, 1);
		Functions.addItem(player, 21786, 1);
		Functions.addItem(player, 21792, 1);
	}
	
	private void use21799(Player player, boolean ctrl) { // Set armor S80 (Dynasty) heavy 90-days
		Functions.addItem(player, 21793, 1);
		Functions.addItem(player, 21794, 1);
		Functions.addItem(player, 21795, 1);
		Functions.addItem(player, 21796, 1);
		Functions.addItem(player, 21797, 1);
		Functions.addItem(player, 21798, 1);
	}
	
	private void use21805(Player player, boolean ctrl) { // Set armor S80 (Dynasty) light 90-days
		Functions.addItem(player, 21800, 1);
		Functions.addItem(player, 21801, 1);
		Functions.addItem(player, 21802, 1);
		Functions.addItem(player, 21803, 1);
		Functions.addItem(player, 21804, 1);
	}
	
	private void use21812(Player player, boolean ctrl) { // Set armor S80 (Dynasty) robe 90-days
		Functions.addItem(player, 21806, 1);
		Functions.addItem(player, 21807, 1);
		Functions.addItem(player, 21808, 1);
		Functions.addItem(player, 21809, 1);
		Functions.addItem(player, 21810, 1);
		Functions.addItem(player, 21811, 1);
	}
	
	private void use21816(Player player, boolean ctrl) { // Set jewels S 90-days
		Functions.addItem(player, 21813, 2);
		Functions.addItem(player, 21814, 2);
		Functions.addItem(player, 21815, 1);
	}
	
	private void use21820(Player player, boolean ctrl) { // Set jewels S80 (Dynasty) 90-days
		Functions.addItem(player, 21817, 2);
		Functions.addItem(player, 21818, 1);
		Functions.addItem(player, 21819, 2);
	}
	
	private void use30391(Player player, boolean ctrl) {  // Dimensional pack lvl 40
		Functions.addItem(player, 14739, 3);
		Functions.addItem(player, 22583, 1);
		Functions.addItem(player, 15276, 1);
		Functions.addItem(player, 22105, 1);
		Functions.addItem(player, 22095, 1);
		Functions.addItem(player, 33800, 1);
	}
	
	private void use30390(Player player, boolean ctrl) {  // Dimensional pack lvl 50
		Functions.addItem(player, 14739, 3);
		Functions.addItem(player, 22584, 1);
		Functions.addItem(player, 15275, 1);
		Functions.addItem(player, 22106, 1);
		Functions.addItem(player, 22096, 1);
	}
	
	private void use30389(Player player, boolean ctrl) {  // Dimensional pack lvl 60
		Functions.addItem(player, 22585, 1);
		Functions.addItem(player, 15274, 1);
		Functions.addItem(player, 22107, 1);
		Functions.addItem(player, 22097, 1);
	}
	
	private void use30388(Player player, boolean ctrl) {  // Dimensional pack lvl 76
		Functions.addItem(player, 21816, 1);
		Functions.addItem(player, 22586, 1);
		Functions.addItem(player, 22571, 1);
	}
	
	private void use30374(Player player, boolean ctrl) {  // Dimensional pack lvl 80
		Functions.addItem(player, 21799, 1);
		Functions.addItem(player, 21805, 1);
		Functions.addItem(player, 21812, 1);
		Functions.addItem(player, 21820, 1);
		Functions.addItem(player, 22202, 1);
		Functions.addItem(player, 20059, 1);
	}
	
	// Agathion Pack - 7-Day Dimensional Item Pack. Box  containing one of the following Agathion (7-Day) at random: Baby Panda,  Griffin, Phoenix or One-Eyed Bat Drove.
	private void use22571(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 20456, 1 }, { 21325, 1 }, { 20949, 1 }, { 20229, 1 } };
		double[] chances = new double[] { 25, 25, 25, 25 };
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34927(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 19448, 1 }, { 19447, 1 }, { 18552, 1 }, { 18551, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 18553, 1 }, { 19509, 1 }, { 19508, 1 }, { 19510, 1 } };
		double[] chances = new double[] { 0.9666, 0.3666, 5.16666, 5.16666, 7.16666, 7.16666, 7.16666, 7.16666, 7.16666,
				7.16666, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 5.16666, 13.3443, 13.3443, 13.3443};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34928(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 19448, 1 }, { 19447, 1 }, { 18552, 1 }, { 18551, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 17527, 1 }, { 17526, 1 }, { 18553, 1 }, { 19509, 1 }, { 19508, 1 }, { 19510, 1 } };
		double[] chances = new double[] { 0.9666, 0.3666, 5.16666, 5.16666, 8.16666, 8.16666, 8.16666, 8.16666, 8.16666,
				8.16666, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 6.16666, 1.16666, 5.16666, 13.3443, 13.3443, 13.3443};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34929(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 19448, 1 }, { 19447, 1 }, { 18555, 1 }, { 18554, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 17527, 1 }, { 17526, 1 }, { 18556, 1 }, { 19512, 1 }, { 19511, 1 }, { 19513, 1 } };
		double[] chances = new double[] { 0.9666, 0.3666, 3.16666, 3.16666, 8.16666, 8.16666, 8.16666, 8.16666, 8.16666,
				8.16666, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 6.16666, 1.16666, 2.06666, 3.0443, 3.3443, 3.3443};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34930(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 18552, 1 }, { 18551, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 34651, 1 }, { 34650, 1 }, { 34652, 1 }, { 34649, 1 },
				{ 34653, 1 }, { 34654, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 17527, 1 }, { 17526, 1 }, { 18553, 1 } };
		double[] chances = new double[] { 5.6666, 5.6666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 21.33333,
				21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443,
				5.33333, 1.33333, 5.33333};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34931(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 18552, 1 }, { 18551, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 34651, 1 }, { 34650, 1 }, { 34652, 1 }, { 34649, 1 },
				{ 34653, 1 }, { 34654, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 17527, 1 }, { 17526, 1 }, { 18553, 1 } };
		double[] chances = new double[] { 5.6666, 5.6666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 21.33333,
				21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443,
				5.33333, 1.33333, 5.33333};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34932(Player player, boolean ctrl) {
		int[][] list = new int[][] { { 18555, 1 }, { 18554, 1 }, { 9555, 1 }, { 9553, 1 }, { 9554, 1 },
				{ 9552, 1 }, { 9557, 1 }, { 9556, 1 }, { 34651, 1 }, { 34650, 1 }, { 34652, 1 }, { 34649, 1 },
				{ 34653, 1 }, { 34654, 1 }, { 9549, 1 }, { 9547, 1 }, { 9548, 1 }, { 9546, 1 }, { 9551, 1 },
				{ 9550, 1 }, { 17527, 1 }, { 17526, 1 }, { 18556, 1 } };
		double[] chances = new double[] { 0.6666, 0.5666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 3.16666, 21.33333,
				21.33333, 21.33333, 21.33333, 21.33333, 21.33333, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443, 13.3443,
				5.33333, 1.33333, 0.63333};
		extractRandomOneItem(player, list, chances);
	}
	
	private void use34855(Player player, boolean ctrl) {
		if (Rnd.chance(15))
			Functions.addItem(player, 34742, 1);
		else
			player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	private static void capsulate(Player player, int[][] items, double[] chances)
	{
		int c = 0;
		boolean empty = true;
		for (int[] i : items)
		{
			if (chances[c] <= 0 || i[1] <= 0 || i[2] <= 0 || i[2] < i[1])
				continue;

			if (Rnd.chance(chances[c])) {
				Functions.addItem(player, i[0], Rnd.get(i[1], i[2]));
				empty = false;
			}
		}
		if(empty)
		{
			player.sendPacket(SystemMsg.THERE_WAS_NOTHING_FOUND_INSIDE);
		}
	}

	private static void extract_item(int[] list, int[] counts, Player player)
	{
		int index = Rnd.get(list.length);
		int id = list[index];
		int count = counts[index];
		Functions.addItem(player, id, count);
	}

	private static List<int[]> mass_extract_item(long source_count, int[] list, int[] counts, Player player) {
		List<int[]> result = new ArrayList<int[]>((int) Math.min(list.length, source_count));

		for (int n = 1; n <= source_count; n++) {
			int index = Rnd.get(list.length);
			int item = list[index];
			int count = counts[index];

			int[] old = null;
			for (int[] res : result)
				if (res[0] == item)
					old = res;

			if (old == null)
				result.add(new int[] { item, count });
			else
				old[1] += count;
		}

		return result;
	}

	private static void extract_item_r(int[] list, int[] count_min, int[] count_max, int[] chances, Player player) {
		int[] counts = count_min;
		for (int i = 0; i < count_min.length; i++)
			counts[i] = Rnd.get(count_min[i], count_max[i]);
		extract_item_r(list, counts, chances, player);
	}

	private static void extract_item_r(int[] list, int[] counts, int[] chances, Player player) {
		int sum = 0;

		for (int i = 0; i < list.length; i++)
			sum += chances[i];

		int[] table = new int[sum];
		int k = 0;

		for (int i = 0; i < list.length; i++)
			for (int j = 0; j < chances[i]; j++) {
				table[k] = i;
				k++;
			}

		int i = table[Rnd.get(table.length)];
		int item = list[i];
		int count = counts[i];

		Functions.addItem(player, item, count);
	}

	private static List<int[]> mass_extract_item_r(long source_count, int[] list, int[] count_min, int[] count_max,
			int[] chances, Player player) {
		int[] counts = count_min;
		for (int i = 0; i < count_min.length; i++)
			counts[i] = Rnd.get(count_min[i], count_max[i]);
		return mass_extract_item_r(source_count, list, counts, chances, player);
	}

	private static List<int[]> mass_extract_item_r(long source_count, int[] list, int[] counts, int[] chances,
			Player player) {
		List<int[]> result = new ArrayList<int[]>((int) Math.min(list.length, source_count));

		int sum = 0;
		for (int i = 0; i < list.length; i++)
			sum += chances[i];

		int[] table = new int[sum];
		int k = 0;

		for (int i = 0; i < list.length; i++)
			for (int j = 0; j < chances[i]; j++) {
				table[k] = i;
				k++;
			}

		for (int n = 1; n <= source_count; n++) {
			int i = table[Rnd.get(table.length)];
			int item = list[i];
			int count = counts[i];

			int[] old = null;
			for (int[] res : result)
				if (res[0] == item)
					old = res;

			if (old == null)
				result.add(new int[] { item, count });
			else
				old[1] += count;
		}

		return result;
	}

	private static boolean canBeExtracted(Player player, ItemInstance item) {
		if (player.getWeightPenalty() >= 3 || player.getInventory().getSize() > player.getInventoryLimit() - 10) {
			player.sendPacket(Msg.YOUR_INVENTORY_IS_FULL, new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(item.getItemId()));
			return false;
		}
		return true;
	}

	public static boolean extractRandomOneItem(Player player, int[][] items, double[] chances) {
		if (items.length != chances.length)
			return false;

		double extractChance = 0;
		for (double c : chances)
			extractChance += c;

		if (Rnd.chance(extractChance)) {
			int[] successfulItems = new int[0];
			while (successfulItems.length == 0)
				for (int i = 0; i < items.length; i++)
					if (Rnd.chance(chances[i]))
						successfulItems = ArrayUtils.add(successfulItems, i);
			int[] item = items[successfulItems[Rnd.get(successfulItems.length)]];
			if (item.length < 2)
				return false;

			Functions.addItem(player, item[0], item[1]);
		}
		return true;
	}
	
	private void use33465(Player player, boolean ctrl)
	{
		if (Rnd.chance(30))
		{
			if (Rnd.chance(40))
				Functions.addItem(player, 30297, 1L);
			else if (Rnd.chance(20))
				Functions.addItem(player, 30299, 1L);
			else if (Rnd.chance(10))
				Functions.addItem(player, 30298, 1L);
			else if (Rnd.chance(30))
				Functions.addItem(player, 30300, 1L);
		}
		else player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}

	
	private void use34955(Player player, boolean ctrl)
	{
		if (Rnd.chance(90))
		{
			if (Rnd.chance(17))
				Functions.addItem(player, 23060, 1L);
			else if (Rnd.chance(17))
				Functions.addItem(player, 23061, 1L);
			else if (Rnd.chance(17))
				Functions.addItem(player, 23062, 1L);
			else if (Rnd.chance(17))
				Functions.addItem(player, 23063, 1L);
			else if (Rnd.chance(16))
				Functions.addItem(player, 23064, 1L);
			else if (Rnd.chance(16))
				Functions.addItem(player, 23065, 1L);
			
			
		}
		else player.sendPacket(Msg.THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT);
	}
	
}