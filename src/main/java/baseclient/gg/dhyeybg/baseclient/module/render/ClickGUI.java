package baseclient.gg.dhyeybg.baseclient.module.render;

import baseclient.gg.dhyeybg.baseclient.Clickgui.ClickGui;
import baseclient.gg.dhyeybg.baseclient.module.Category;
import baseclient.gg.dhyeybg.baseclient.module.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", "Opens the module menu", Category.RENDER);
	}

	@Override
	public void onEnable() {
		mc.displayGuiScreen(new ClickGui());
	}

	@Override
	public void onDisable() {
		if (mc.currentScreen instanceof ClickGui) {
			mc.displayGuiScreen(null);
		}
	}
}