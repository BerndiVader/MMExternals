package externals;

import java.util.HashSet;

import com.gmail.berndivader.mythicmobsext.externals.TargeterAnnotation;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

@TargeterAnnotation(name="dummytargeter",author="BerndiVader")
public class ExternalDummyTargeter 
extends 
IEntitySelector {

	public ExternalDummyTargeter(MythicLineConfig mlc) {
		super(mlc);
		System.err.println("dummy targeter init!");
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
		HashSet<AbstractEntity>targets=new HashSet<>();
		
		System.err.println("dummy targeter used! return the casters target if present");
		
		if (data.getCaster().getEntity().getTarget()!=null) {
			targets.add(data.getCaster().getEntity().getTarget());
		}
		return targets;
	}
}