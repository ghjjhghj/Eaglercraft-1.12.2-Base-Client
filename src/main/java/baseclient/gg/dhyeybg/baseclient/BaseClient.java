package baseclient.gg.dhyeybg.baseclient;

import baseclient.gg.dhyeybg.baseclient.Clickgui.ClickGui;
import baseclient.gg.dhyeybg.baseclient.module.Module;
import baseclient.gg.dhyeybg.baseclient.module.ModuleManager;
import baseclient.gg.dhyeybg.baseclient.settings.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class BaseClient {

	public static BaseClient instance;

	public static final String NAME = "BaseClient";
	public static final String VERSION = "v1.0.0";

	public static final int CLICKGUI_KEY = 54;

	public ModuleManager moduleManager;
	public SettingsManager settingsManager;

	public void init() {
		settingsManager = new SettingsManager();
		moduleManager = new ModuleManager();
		System.out.println("[" + NAME + "] " + VERSION + " initialised");
	}

	public void onUpdate() {
		if (moduleManager == null) return;
		for (Module m : moduleManager.getModuleList()) {
			if (m.isToggled()) m.onUpdate();
		}
	}

	public void onKey(int key) {
		if (moduleManager == null) return;
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen != null) return;

		if (key == CLICKGUI_KEY) {
			mc.displayGuiScreen(new ClickGui());
			return;
		}
		for (Module m : moduleManager.getModuleList()) {
			if (m.getKey() != 0 && m.getKey() == key) m.toggle();
		}
	}

	public void onRender2D() {
		if (moduleManager == null) return;
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc);

		mc.fontRendererObj.drawStringWithShadow(NAME + " " + VERSION, 2, 2, 0xFFBD62FF);

		int y = 2;
		for (Module m : moduleManager.getModuleList()) {
			if (m.isToggled()) {
				m.onRender2D();
				if (m.visible) {
					int w = mc.fontRendererObj.getStringWidth(m.getName());
					mc.fontRendererObj.drawStringWithShadow(m.getName(), sr.getScaledWidth() - w - 2, y, 0xFFBD62FF);
					y += 10;
				}
			}
		}
	}

	public boolean onVelocity(int entityId) {
		if (moduleManager == null) return false;
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.player == null || mc.player.getEntityId() != entityId) return false;
		boolean cancel = false;
		for (Module m : moduleManager.getModuleList()) {
			if (m.isToggled() && m.onVelocity(entityId)) cancel = true;
		}
		return cancel;
	}
}
