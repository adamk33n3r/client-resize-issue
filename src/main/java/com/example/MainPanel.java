package com.example;

import lombok.Getter;
import net.runelite.client.ui.MultiplexingPluginPanel;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;

@Getter
@Singleton
public class MainPanel extends PluginPanel {
    private final MultiplexingPluginPanel muxer = new MultiplexingPluginPanel(this);
    @Inject
    public MainPanel(Provider<TestPanel> testPanelProvider) {
        super(false);
        JButton button = new JButton("Test Panel");
        button.addActionListener(e -> {
            this.muxer.pushState(testPanelProvider.get());
        });
        this.add(button);
    }
}
