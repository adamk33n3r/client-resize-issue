package com.example;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.inject.Binder;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.info.InfoPlugin;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.MultiplexingPluginPanel;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private Provider<MainPanel> mainPanelProvider;

	private NavigationButton testNavBtn;

	@Override
	public void configure(Binder binder) {
		binder.bind(MultiplexingPluginPanel.class).toProvider(() -> this.mainPanelProvider.get().getMuxer());
	}

	@Override
	protected void startUp() throws Exception
	{
		final BufferedImage icon = ImageUtil.loadImageResource(InfoPlugin.class, "info_icon.png");
		this.testNavBtn = NavigationButton.builder()
			.tooltip("Client Resize Issue")
			.icon(icon)
			.priority(1)
			.panel(this.mainPanelProvider.get().getMuxer())
			.build();
		this.clientToolbar.addNavigation(this.testNavBtn);
	}

	@Override
	protected void shutDown() throws Exception
	{
		this.clientToolbar.removeNavigation(this.testNavBtn);
	}
}
