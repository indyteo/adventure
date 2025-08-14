package net.kyori.adventure.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.Nullable;

final class ComponentLinearizer {
  private ComponentLinearizer() {
  }

  static Component linearize(final Component component) {
    // Check if component is already linearized
    if (isEmptyText(component) && component.style().isEmpty() && component.children().stream().allMatch(c -> c.children().isEmpty()))
      return component;
    // Otherwise, linearize it!
    final List<Component> linearized = new ArrayList<>();
    linearizeRecursively(linearized, component, component.style());
    return Component.empty().children(linearized);
  }

  private static void linearizeRecursively(final List<Component> linearized, final Component component, final Style style) {
    // Skip "container" components (empty text)
    // Note that the style will still be kept
    if (!isEmptyText(component))
      linearized.add(component.children(List.of()).style(style));
    // Process children recursively, with merged style
    for (final Component child : component.children())
      linearizeRecursively(linearized, child, style.merge(child.style()));
  }

  private static boolean isEmptyText(final Component component) {
    return (component instanceof TextComponent text) && !(component instanceof VirtualComponent) && text.content().isEmpty();
  }
}
