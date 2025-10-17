package net.kyori.adventure.text.minimessage.tag;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.VirtualComponent;
import java.util.Collections;
import java.util.List;

public interface TextModifying extends Modifying {
  String modifyText(String text);

  @Override
  default Component apply(Component current, int depth) {
    if (current instanceof TextComponent text && !(current instanceof VirtualComponent))
      return Component.text(this.modifyText(text.content()), current.style());
    return current.children(List.of());
  }
}
