package externals;

import org.bukkit.Location;

import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.conditions.AbstractCustomCondition;
import com.gmail.berndivader.mythicmobsext.externals.ConditionAnnotation;
import com.khorn.terraincontrol.TerrainControl;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

@ConditionAnnotation(name="otcbiome",author="BerndiVader")
public class OpenTerrainBiomeCondition 
extends
AbstractCustomCondition
implements
ILocationCondition {
	String[]biomes;
	boolean like;
	public OpenTerrainBiomeCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		biomes=mlc.getString("biomes","").toLowerCase().split(",");
		like=mlc.getBoolean("like",false);
	}

	@Override
	public boolean check(AbstractLocation var1) {
		String s1=null;
		s1=getBiome(BukkitAdapter.adapt(var1));
		boolean bl1=false;
		if (s1!=null) {
			for(int i1=0;i1<biomes.length;i1++) {
				bl1=like?biomes[i1].contains(s1):biomes[i1].equals(s1);
			}
		}
		return bl1;
	}
	
	String getBiome(Location l) {
		String s1=null;
		if (Main.pluginmanager.isPluginEnabled("TerrainControl")) {
			s1=TerrainControl.getBiomeName(l.getWorld().getName(),l.getBlockX(),l.getBlockZ()).toLowerCase();
		}
		return s1;
	}

}