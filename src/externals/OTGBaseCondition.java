package externals;

import org.bukkit.Location;

import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.conditions.AbstractCustomCondition;
import com.gmail.berndivader.mythicmobsext.externals.ConditionAnnotation;
import com.gmail.berndivader.mythicmobsext.utils.Utils;
import com.pg85.otg.LocalBiome;
import com.pg85.otg.LocalWorld;
import com.pg85.otg.OTG;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.types.RangedDouble;

@ConditionAnnotation(name="otgbase",author="BerndiVader / DragonsAscent")
public class OTGBaseCondition 
extends
AbstractCustomCondition
implements
ILocationCondition {
	enum base{
		temperature,
		wetness,
		isleinbiome,
		volatility,
		color,
		rarity,
		size
	}
	RangedDouble rd1;
	boolean debug;
	char c1;
	public OTGBaseCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		rd1=new RangedDouble(mlc.getString("range",">-99"));
		String s1=mlc.getString("base","temperature").toLowerCase();
		for(base b1:base.values()) {
			if (b1.toString().contains(s1)) {
				c1=b1.toString().charAt(0);
				break;
			};
		}
		debug=mlc.getBoolean("debug",false);
	}

	@Override
	public boolean check(AbstractLocation var1) {
		LocalBiome lb=getBiome(BukkitAdapter.adapt(var1));
		boolean bl1=true;
		if (lb!=null) {
			double d1=0d;
			switch(c1) {
			case 't':
				d1=(double)lb.getBiomeConfig().biomeTemperature;
				break;
			case 'w':
				d1=(double)lb.getBiomeConfig().biomeWetness;
				break;
			case 'i':
				d1=(double)lb.getBiomeConfig().isleInBiome.size();
				break;
			case 'v':
				d1=(double)lb.getBiomeConfig().biomeVolatility;
				break;
			case 'c':
				d1=(double)lb.getBiomeConfig().biomeColor;
				break;
			case 'r':
				d1=(double)lb.getBiomeConfig().biomeRarity;
				break;
			case 's':
				d1=(double)lb.getBiomeConfig().biomeSize;
				break;
			}
			d1=Utils.round(d1,6);
			if (debug) {
				System.out.println("OTGBase outcome: "+d1);
			}
			bl1=rd1.equals(d1);
		}
		return bl1;
	}

	LocalBiome getBiome(Location l) {
		if (Main.pluginmanager.isPluginEnabled("OpenTerrainGenerator")) {
			LocalBiome lb=null;
			LocalWorld lw;
			if ((lw=OTG.getWorld(l.getWorld().getName()))!=null&&(lb=lw.getBiomeById(lw.getBiomeGenerator().getBiome(l.getBlockX(),l.getBlockZ())))!=null) {
				return lb;
			}
		}
		return null;
	}

}