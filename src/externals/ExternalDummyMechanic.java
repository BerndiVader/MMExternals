package externals;

import com.gmail.berndivader.mythicmobsext.externals.SkillAnnotation;

import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

@SkillAnnotation(name="dummymechanic",author="BerndiVader")
public class ExternalDummyMechanic 
extends 
SkillMechanic
implements
INoTargetSkill {
	public ExternalDummyMechanic(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		this.ASYNC_SAFE=false;
		System.err.println("External dummy mechanic init!");
	}

	@Override
	public boolean cast(SkillMetadata data) {
		System.err.println("External dummy mechanic used! broadcast hello world into the casters world");
		
		data.getCaster().getEntity().getBukkitEntity().getWorld().getPlayers().stream().forEach(p->{
			p.sendMessage("Hello world!");
		});
		
		return true;
	}
}
