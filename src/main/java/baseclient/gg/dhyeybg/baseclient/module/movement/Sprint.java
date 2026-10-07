package baseclient.gg.dhyeybg.baseclient.module.movement;

import baseclient.gg.dhyeybg.baseclient.module.Category;
import baseclient.gg.dhyeybg.baseclient.module.Module;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", "Sprints while moving forward", Category.MOVEMENT);
	}

	@Override
	public void onUpdate() {
		if (mc.player != null) {
			mc.player.setSprinting(mc.player.movementInput.forwardKeyDown && !mc.player.isSneaking());
		}
	}

	@Override
	public void onDisable() {
		if (mc.player != null) {
			mc.player.setSprinting(false);
		}
	}
}