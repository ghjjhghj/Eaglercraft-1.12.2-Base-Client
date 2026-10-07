package baseclient.gg.dhyeybg.baseclient.module.combat;

import baseclient.gg.dhyeybg.baseclient.module.Category;
import baseclient.gg.dhyeybg.baseclient.module.Module;

public class Velocity extends Module {

	public Velocity() {
		super("Velocity", "Cancels knockback", Category.COMBAT);
	}

	@Override
	public boolean onVelocity(int entityId) {
		return true;
	}
}