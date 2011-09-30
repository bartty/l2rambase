/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import l2.rambase.gameserver.GeoData;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.skills.effects.EffectFusion;
import l2.rambase.gameserver.util.Util;


public final class FusionSkill
{
    protected static final Logger _log = Logger.getLogger(FusionSkill.class.getName());

    protected int _skillCastRange;
    protected int _fusionId;
    protected int _fusionLevel;
    protected L2Character _caster;
    protected L2Character _target;
    protected Future<?> _geoCheckTask;

    public L2Character getCaster()
    {
        return _caster;
    }
    
    public L2Character getTarget()
    {
        return _target;
    }
    
    public FusionSkill(L2Character caster, L2Character target, L2Skill skill)
    {
        _skillCastRange = skill.getCastRange();
        _caster = caster;
        _target = target;
        _fusionId = skill.getTriggeredId();
        _fusionLevel = skill.getTriggeredLevel();
        
        L2Effect effect = _target.getFirstEffect(_fusionId);
        if (effect != null)
            ((EffectFusion)effect).increaseEffect();
        else
        {
            L2Skill force = SkillTable.getInstance().getInfo(_fusionId, _fusionLevel);
            if (force != null)
                force.getEffects(_caster, _target, null);
            else
                _log.warning("Triggered skill ["+_fusionId+";"+_fusionLevel+"] not found!");
        }
        _geoCheckTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new GeoCheckTask(), 1000, 1000);
    }

    public void onCastAbort()
    {
        _caster.setFusionSkill(null);
        L2Effect effect = _target.getFirstEffect(_fusionId);
        if (effect != null)
            ((EffectFusion)effect).decreaseForce();
        
        _geoCheckTask.cancel(true);
    }

    public class GeoCheckTask implements Runnable
    {
        public void run()
        {
            try
            {
                if (!Util.checkIfInRange(_skillCastRange, _caster, _target, true))
                    _caster.abortCast();
                
                if (!GeoData.getInstance().canSeeTarget(_caster, _target))
                    _caster.abortCast();
            }
            catch (Exception e)
            {
                // ignore
            }
        }
    }
}