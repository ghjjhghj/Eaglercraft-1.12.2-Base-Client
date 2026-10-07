package baseclient.gg.dhyeybg.baseclient.module;

import java.util.ArrayList;

import baseclient.gg.dhyeybg.baseclient.module.combat.*;
import baseclient.gg.dhyeybg.baseclient.module.movement.*;
import baseclient.gg.dhyeybg.baseclient.module.render.*;

public class ModuleManager {

	public ArrayList<Module> modules;

	public ModuleManager() {
		modules = new ArrayList<Module>();
		this.modules.add(new ClickGUI());
		this.modules.add(new HUD());
		this.modules.add(new FullBright());
		this.modules.add(new Sprint());
		this.modules.add(new Velocity());
	}

	public Module getModule(String name) {
		for (Module m : this.modules) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public ArrayList<Module> getModuleList() {
		return this.modules;
	}

	public ArrayList<Module> getModulesInCategory(Category c) {
		ArrayList<Module> mods = new ArrayList<Module>();
		for (Module m : this.modules) {
			if (m.getCategory() == c) {
				mods.add(m);
			}
		}
		return mods;
	}
}
