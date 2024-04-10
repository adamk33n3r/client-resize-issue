package com.example;

import net.runelite.client.ui.MultiplexingPluginPanel;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class TestPanel extends PluginPanel {
    private final Provider<MultiplexingPluginPanel> muxer;

    @Inject
    public TestPanel(Provider<MultiplexingPluginPanel> muxer) {
        super(false);
        this.muxer = muxer;
        this.setLayout(new GridLayout(0, 1));
    }

    void build() {
        this.removeAll();
        JTextArea textArea = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at imperdiet lectus. Aliquam sem enim, facilisis ac vestibulum laoreet, suscipit non nec.");
        textArea.setLineWrap(true);
        this.add(textArea);

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> this.muxer.get().popState());
        this.add(backButton);
    }

    @Override
    public void onActivate() {
        SwingUtilities.invokeLater(this::build);
        // Use this one instead to see the effect on collapse/restore and NOT open
//        this.build();
    }
}
