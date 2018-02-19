package externals;

import org.bukkit.Location;

import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.conditions.AbstractCustomCondition;
import com.gmail.berndivader.mythicmobsext.externals.ConditionAnnotation;
import com.pg85.otg.LocalBiome;
import com.pg85.otg.LocalWorld;
import com.pg85.otg.OTG;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.types.RangedDouble;

@ConditionAnnotation(name="otgtemparature",author="BerndiVader / DragonsAscent")
public class OTGTemparatureCondition 
extends
AbstractCustomCondition
implements
ILocationCondition {
	RangedDouble rd1;
	boolean debug;
	public OTGTemparatureCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		rd1=new RangedDouble(mlc.getString("range",">-99"));
		debug=mlc.getBoolean("debug",false);
	}

	@Override
	public boolean check(AbstractLocation var1) {
		return rd1.equals((double)getTemparature(BukkitAdapter.adapt(var1)));
	}

	float getTemparature(Location l) {
		float f1=0f;
		if (Main.pluginmanager.isPluginEnabled("OpenTerrainGenerator")) {
			LocalBiome lb=null;
			LocalWorld lw;
			if ((lw=OTG.getWorld(l.getWorld().getName()))!=null&&(lb=lw.getBiomeById(lw.getBiomeGenerator().getBiome(l.getBlockX(),l.getBlockZ())))!=null) {
				f1=lb.getTemperatureAt(l.getBlockX(),l.getBlockY(),l.getBlockZ());
				if (debug) {
					System.out.println("temp at "+l.getBlockX()+":"+l.getBlockY()+":"+l.getBlockZ()+"= "+f1);
				}
			}
		}
		return f1;
	}

}