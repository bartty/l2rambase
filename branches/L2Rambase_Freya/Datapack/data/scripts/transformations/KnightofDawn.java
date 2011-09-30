package transformations;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.TransformationManager;
import l2.rambase.gameserver.model.L2Transformation;

public class KnightofDawn extends L2Transformation
{
	private static final int[] SKILLS = {5491,619,878,879,880};
	public KnightofDawn()
	{
		// id, colRadius, colHeight
		super(20, 12, 24);
	}

	public void onTransform()
	{
		if (getPlayer().getTransformationId() != 20 || getPlayer().isCursedWeaponEquipped())
			return;

		transformedSkills();
	}

	public void transformedSkills()
	{
		// Decrease Bow/Crossbow Attack Speed
		getPlayer().addSkill(SkillTable.getInstance().getInfo(5491, 1), false);
		// Transform Dispel
		getPlayer().addSkill(SkillTable.getInstance().getInfo(619, 1), false);
		getPlayer().addSkill(SkillTable.getInstance().getInfo(878, 1), false);
		getPlayer().addSkill(SkillTable.getInstance().getInfo(879, 1), false);
		getPlayer().addSkill(SkillTable.getInstance().getInfo(880, 1), false);
		getPlayer().setTransformAllowedSkills(SKILLS);
	}

	public void onUntransform()
	{
		removeSkills();
	}

	public void removeSkills()
	{
		// Decrease Bow/Crossbow Attack Speed
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(5491, 1), false);
		// Transform Dispel
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(619, 1), false);
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(878, 1), false);
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(879, 1), false);
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(880, 1), false);
		getPlayer().setTransformAllowedSkills(EMPTY_ARRAY);
	}

	public static void main(String[] args)
	{
		TransformationManager.getInstance().registerTransformation(new KnightofDawn());
	}
}
