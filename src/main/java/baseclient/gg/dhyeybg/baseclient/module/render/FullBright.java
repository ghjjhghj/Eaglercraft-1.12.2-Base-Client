package baseclient.gg.dhyeybg.baseclient.module.render;

import baseclient.gg.dhyeybg.baseclient.module.Category;
import baseclient.gg.dhyeybg.baseclient.module.Module;

public class FullBright extends Module {

	private float previousGamma;

	public FullBright() {
		super("FullBright", "Increases client brightness", Category.RENDER);
	}

	@Override
	public void onEnable() {
		this.previousGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 1000.0F;
	}

	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = this.previousGamma;
	}
}