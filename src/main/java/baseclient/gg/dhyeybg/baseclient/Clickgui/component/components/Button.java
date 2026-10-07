package baseclient.gg.dhyeybg.baseclient.Clickgui.component.components;

import java.util.ArrayList;

import baseclient.gg.dhyeybg.baseclient.BaseClient;
import baseclient.gg.dhyeybg.baseclient.Clickgui.ClickGui;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.Component;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.Frame;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.components.Sub.Checkbox;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.components.Sub.Keybind;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.components.Sub.ModuleButton;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.components.Sub.Slider;
import baseclient.gg.dhyeybg.baseclient.Clickgui.component.components.Sub.VisibleButton;
import baseclient.gg.dhyeybg.baseclient.module.Module;
import baseclient.gg.dhyeybg.baseclient.settings.Setting;
import net.lax1dude.eaglercraft.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Button extends Component {

	private static final int PURPLE = 0xFF9700FF;
	private static final int PURPLE_DARK = 0xFF6900B2;
	private static final int TOGGLED_BG = 0xFF0E0E0E;
	private static final int IDLE_BG = 0xFF111111;

	public Module mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	private ArrayList<Component> subcomponents;
	public boolean open;

	public Button(Module mod, Frame parent, int offset) {
		this.mod = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<Component>();
		this.open = false;
		int opY = offset + 12;
		ArrayList<Setting> settings = BaseClient.instance.settingsManager.getSettingsByMod(mod);
		if (settings != null) {
			for (Setting s : settings) {
				if (s.isCombo()) {
					this.subcomponents.add(new ModuleButton(s, this, mod, opY));
					opY += 12;
				}
				if (s.isSlider()) {
					this.subcomponents.add(new Slider(s, this, opY));
					opY += 12;
				}
				if (s.isCheck()) {
					this.subcomponents.add(new Checkbox(s, this, opY));
					opY += 12;
				}
			}
		}
		this.subcomponents.add(new Keybind(this, opY));
		opY += 12;
		this.subcomponents.add(new VisibleButton(this, mod, opY));
	}

	@Override
	public void setOff(int newOff) {
		offset = newOff;
		int opY = offset + 12;
		for (Component comp : this.subcomponents) {
			comp.setOff(opY);
			opY += 12;
		}
	}

	@Override
	public void renderComponent() {
		int bg;
		if (this.isHovered) {
			bg = this.mod.isToggled() ? PURPLE_DARK : PURPLE;
		} else {
			bg = this.mod.isToggled() ? TOGGLED_BG : IDLE_BG;
		}
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, bg);
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.mod.getName(), (parent.getX() + 2) * 2, (parent.getY() + offset + 2) * 2 + 4, this.mod.isToggled() ? 0xBD62FF : -1);
		if (this.subcomponents.size() > 2)
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.open ? "-" : "+", (parent.getX() + parent.getWidth() - 10) * 2, (parent.getY() + offset + 2) * 2 + 4, -1);
		GlStateManager.popMatrix();
		if (this.open && !this.subcomponents.isEmpty()) {
			for (Component comp : this.subcomponents) {
				comp.renderComponent();
			}
			Gui.drawRect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), ClickGui.color);
		}
	}

	@Override
	public int getHeight() {
		if (this.open) {
			return 12 * (this.subcomponents.size() + 1);
		}
		return 12;
	}

	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.isHovered = isMouseOnButton(mouseX, mouseY);
		for (Component comp : this.subcomponents) {
			comp.updateComponent(mouseX, mouseY);
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (isMouseOnButton(mouseX, mouseY) && button == 0) {
			this.mod.toggle();
		}
		if (isMouseOnButton(mouseX, mouseY) && button == 1) {
			this.open = !this.open;
			this.parent.refresh();
		}
		for (Component comp : this.subcomponents) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for (Component comp : this.subcomponents) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void keyTyped(char typedChar, int key) {
		for (Component comp : this.subcomponents) {
			comp.keyTyped(typedChar, key);
		}
	}

	public boolean isMouseOnButton(int x, int y) {
		return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
	}
}
