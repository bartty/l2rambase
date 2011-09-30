/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import gnu.trove.TIntIntHashMap;
import gnu.trove.TIntObjectHashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.L2DatabaseFactory;


public class NpcBufferTable
{
	protected static Logger _log = Logger.getLogger(NpcBufferTable.class.getName());
	
	private TIntObjectHashMap<NpcBufferSkills> _buffers = new TIntObjectHashMap<NpcBufferSkills>();
	
	private class NpcBufferSkills
	{
		private int _npcId = 0;
		private TIntIntHashMap _skillId = new TIntIntHashMap();
		private TIntIntHashMap _skillLevels = new TIntIntHashMap();
		private TIntIntHashMap _skillFeeIds = new TIntIntHashMap();
		private TIntIntHashMap _skillFeeAmounts = new TIntIntHashMap();
		
		public NpcBufferSkills(int npcId)
		{
			_npcId = npcId;
		}
		
		public void addSkill(int skillId, int skillLevel, int skillFeeId, int skillFeeAmount,
		        int buffGroup)
		{
			_skillId.put(buffGroup, skillId);
			_skillLevels.put(buffGroup, skillLevel);
			_skillFeeIds.put(buffGroup, skillFeeId);
			_skillFeeAmounts.put(buffGroup, skillFeeAmount);
		}
		
		public int[] getSkillGroupInfo(int buffGroup)
		{
			Integer skillId = _skillId.get(buffGroup);
			Integer skillLevel = _skillLevels.get(buffGroup);
			Integer skillFeeId = _skillFeeIds.get(buffGroup);
			Integer skillFeeAmount = _skillFeeAmounts.get(buffGroup);
			
			if (skillId == null || skillLevel == null || skillFeeId == null
			        || skillFeeAmount == null)
				return null;
			
			return new int[] { skillId, skillLevel, skillFeeId, skillFeeAmount };
		}
		
		@SuppressWarnings("unused")
		public int getNpcId()
		{
			return _npcId;
		}
	}

	private NpcBufferTable()
	{
		Connection con = null;
		int skillCount = 0;
		
		try
		{
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				
				PreparedStatement statement = con.prepareStatement("SELECT `npc_id`,`skill_id`,`skill_level`,`skill_fee_id`,`skill_fee_amount`,`buff_group` FROM `npc_buffer` ORDER BY `npc_id` ASC");
				ResultSet rset = statement.executeQuery();
				
				int lastNpcId = 0;
				NpcBufferSkills skills = null;
				
				while (rset.next())
				{
					int npcId = rset.getInt("npc_id");
					int skillId = rset.getInt("skill_id");
					int skillLevel = rset.getInt("skill_level");
					int skillFeeId = rset.getInt("skill_fee_id");
					int skillFeeAmount = rset.getInt("skill_fee_amount");
					int buffGroup = rset.getInt("buff_group");
					
					if (npcId != lastNpcId)
					{
						if (lastNpcId != 0)
							_buffers.put(lastNpcId, skills);
						
						skills = new NpcBufferSkills(npcId);
						skills.addSkill(skillId, skillLevel, skillFeeId, skillFeeAmount, buffGroup);
					}
					else
						skills.addSkill(skillId, skillLevel, skillFeeId, skillFeeAmount, buffGroup);
					
					lastNpcId = npcId;
					skillCount++;
				}
				
				if (lastNpcId != 0)
					_buffers.put(lastNpcId, skills);
				rset.close();
				statement.close();
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "NpcBufferTable: Error reading npc_buffer table: " + e.getMessage(), e);
			}
			
			if (Config.CUSTOM_NPCBUFFER_TABLES)
			{
				try
				{
					PreparedStatement statement = con.prepareStatement("SELECT `npc_id`,`skill_id`,`skill_level`,`skill_fee_id`,`skill_fee_amount`,`buff_group` FROM `custom_npc_buffer` ORDER BY `npc_id` ASC");
					ResultSet rset = statement.executeQuery();
					
					int lastNpcId = 0;
					NpcBufferSkills skills = null;
					
					while (rset.next())
					{
						int npcId = rset.getInt("npc_id");
						int skillId = rset.getInt("skill_id");
						int skillLevel = rset.getInt("skill_level");
						int skillFeeId = rset.getInt("skill_fee_id");
						int skillFeeAmount = rset.getInt("skill_fee_amount");
						int buffGroup = rset.getInt("buff_group");
						
						if (npcId != lastNpcId)
						{
							if (lastNpcId != 0)
								_buffers.put(lastNpcId, skills);
							
							skills = new NpcBufferSkills(npcId);
							skills.addSkill(skillId, skillLevel, skillFeeId, skillFeeAmount, buffGroup);
						}
						else
							skills.addSkill(skillId, skillLevel, skillFeeId, skillFeeAmount, buffGroup);
						
						lastNpcId = npcId;
						skillCount++;
					}
					
					if (lastNpcId != 0)
						_buffers.put(lastNpcId, skills);
					rset.close();
					statement.close();
				}
				catch (Exception e)
				{
					_log.log(Level.SEVERE, "NpcBufferTable: Error reading custom_npc_buffer table: " + e.getMessage(), e);
				}
			}
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		
		_log.info("NpcBufferSkillIdsTable: Loaded " + _buffers.size() + " buffers and " + skillCount + " skills.");
	}
	
	public static NpcBufferTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public int[] getSkillInfo(int npcId, int buffGroup)
	{
		NpcBufferSkills skills = _buffers.get(npcId);
		
		if (skills == null)
			return null;
		
		return skills.getSkillGroupInfo(buffGroup);
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final NpcBufferTable _instance = new NpcBufferTable();
	}
}