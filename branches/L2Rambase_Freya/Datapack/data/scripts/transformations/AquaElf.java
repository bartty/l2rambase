package transformations;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.TransformationManager;
import l2.rambase.gameserver.model.L2Transformation;

public class AquaElf extends L2Transformation
{
	private static final int[] SKILLS = new int[]{619, 965, 966};
	
	public AquaElf()
	{
		// id, colRadius, colHeight
		super(125, 12, 27.50);
	}

	public void onTransform()
	{
		if (getPlayer().getTransformationId() != 125 || getPlayer().isCursedWeaponEquipped())
			return;

		transformedSkills();
	}

	public void transformedSkills()
	{
		// Transform Dispel
		getPlayer().addSkill(SkillTable.getInstance().getInfo(619, 1), false);
		getPlayer().addSkill(SkillTable.getInstance().getInfo(965, 1), false);
		getPlayer().addSkill(SkillTable.getInstance().getInfo(966, 1), false);
		getPlayer().setTransformAllowedSkills(SKILLS);
	}

	public void onUntransform()
	{
		removeSkills();
	}

	public void removeSkills()
	{
		// Transform Dispel
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(619, 1), false);
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(965, 1), false);
		getPlayer().removeSkill(SkillTable.getInstance().getInfo(966, 1), false);
		getPlayer().setTransformAllowedSkills(EMPTY_ARRAY);
	}

	public static void main(String[] args)
	{
		TransformationManager.getInstance().registerTransformation(new AquaElf());
	}
}
