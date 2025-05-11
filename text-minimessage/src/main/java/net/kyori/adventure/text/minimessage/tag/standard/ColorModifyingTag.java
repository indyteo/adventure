package net.kyori.adventure.text.minimessage.tag.standard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.tag.Modifying;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public abstract class ColorModifyingTag implements Modifying {
  public abstract @NotNull TextColor modifyColor(@NotNull TextColor color);

  @Override
  public @NotNull Component apply(@NotNull Component current, int depth) {
    return Component.empty();
  }

  @Override
  public @NotNull Component apply(@NotNull Component current, int depth, @NotNull Style parentStyle) {
    TextColor color = current.color();
    if (color == null) {
      if (depth == 0)
        color = parentStyle.color();
      if (color == null)
        return current.children(Collections.emptyList());
    }
    return current.color(this.modifyColor(color)).children(Collections.emptyList());
  }
}
