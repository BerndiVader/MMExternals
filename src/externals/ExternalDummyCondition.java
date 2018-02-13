package externals;

import org.bukkit.entity.EntityType;

import com.gmail.berndivader.mythicmobsext.conditions.AbstractCustomCondition;
import com.gmail.berndivader.mythicmobsext.externals.ConditionAnnotation;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

@ConditionAnnotation(name="dummycondition",author="BerndiVader")
public class ExternalDummyCondition 
extends
AbstractCustomCondition
implements
IEntityCondition {

	public ExternalDummyCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		System.err.print("Dummy condition init!");
	}

	@Override
	public boolean check(AbstractEntity entity) {
		System.err.println("Dummy condition used! return true if the entity is not a zombie");
		return entity.getBukkitEntity().getType()!=EntityType.ZOMBIE;
	}

}