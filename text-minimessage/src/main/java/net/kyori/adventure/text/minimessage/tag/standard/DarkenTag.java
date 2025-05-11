package net.kyori.adventure.text.minimessage.tag.standard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.VirtualComponent;
import net.kyori.adventure.text.VirtualComponentRenderer;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import net.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class DarkenTag extends ColorModifyingTag {
  private static final String DARKEN = "darken";

  static final TagResolver RESOLVER = SerializableResolver.claimingComponent(DARKEN, DarkenTag::create, DarkenTag::claimComponent);

  private final float factor;

  static Tag create(final ArgumentQueue args, final Context ctx) {
    float factor;
    if (args.hasNext())
      factor = (float) args.pop().asDouble().orElseThrow(() -> ctx.newException("Expected darkening factor to be a decimal number", args));
    else
      factor = 0.3f;
    return new DarkenTag(factor);
  }

  public DarkenTag(float factor) {
    this.factor = 1 - Math.min(Math.max(0, factor), 1);
  }

  @Override
  public @NotNull TextColor modifyColor(@NotNull TextColor color) {
    return TextColor.color(
      color.red() * this.factor / 0xff,
      color.green() * this.factor / 0xff,
      color.blue() * this.factor / 0xff
    );
  }

  static @Nullable Emitable claimComponent(final Component comp) {
    return null; // Wtf am I supposed to do here?
  }
}
