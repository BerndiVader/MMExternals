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
import io.lumine.xikage.mythicmobs.skills.SkillString;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

@ConditionAnnotation(name="otgbiome",author="BerndiVader / DragonsAscent")
public class OpenTerrainGeneratorCondition 
extends
AbstractCustomCondition
implements
ILocationCondition {
	String[]biomes;
	boolean debug,like;
	public OpenTerrainGeneratorCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		String s1=SkillString.parseMessageSpecialChars(mlc.getString("biomes","").toLowerCase());
		if (s1.startsWith("\"")&&s1.endsWith("\"")) {
			s1=s1.substring(1,s1.length()-1);
		}
		biomes=s1.split(",");
		like=mlc.getBoolean("like",false);
		debug=mlc.getBoolean("debug",false);
	}

	@Override
	public boolean check(AbstractLocation var1) {
		String s1=null;
		s1=getBiome(BukkitAdapter.adapt(var1));
		boolean bl1=false;
		if (debug) System.out.println("OTGBiome: "+s1);
		if (s1!=null) {
			for(int i1=0;i1<biomes.length;i1++) {
				bl1=like?biomes[i1].contains(s1):biomes[i1].equals(s1);
				if (bl1) break;
			}
		}
		return bl1;
	}

	String getBiome(Location l) {
		String s1=null;
		if (Main.pluginmanager.isPluginEnabled("OpenTerrainGenerator")) {
			LocalBiome lb=null;
			LocalWorld lw;
			if ((lw=OTG.getWorld(l.getWorld().getName()))!=null&&(lb=lw.getBiomeById(lw.getBiomeGenerator().getBiome(l.getBlockX(),l.getBlockZ())))!=null) {
				s1=lb.getName().toLowerCase();
			}		}
		return s1;
	}

}