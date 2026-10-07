package baseclient.gg.dhyeybg.baseclient.module.render;

import baseclient.gg.dhyeybg.baseclient.module.Category;
import baseclient.gg.dhyeybg.baseclient.module.Module;

public class HUD extends Module {

	public HUD() {
		super("HUD", "Displays the module list", Category.RENDER);
		this.visible = false;
	}
}