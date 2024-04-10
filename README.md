# Client Resize Issue

When using the `MultiplexingPluginPanel` and the `onActivate` method to build the panel,
there are situations where the entire RuneLite client is resized very tall.

If `onActivate` calls it like `SwingUtilities.invokeLater(() -> this.build()`, then the
resize issue happens immediately when the panel is pushed on.
![example](example.gif)

If `onActivate` calls it like `this.build()`, then the resize issue happens when restoring the
panel from collapse and when switching panel tabs (like to settings and back)
![example2](example2.gif)
