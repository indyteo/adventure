package net.kyori.adventure.text.minimessage.tag.standard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import net.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class LightenTag extends ColorModifyingTag {
  private static final String LIGHTEN = "lighten";

  static final TagResolver RESOLVER = SerializableResolver.claimingComponent(LIGHTEN, LightenTag::create, LightenTag::claimComponent);

  private final float factor;

  static Tag create(final ArgumentQueue args, final Context ctx) {
    float factor;
    if (args.hasNext())
      factor = (float) args.pop().asDouble().orElseThrow(() -> ctx.newException("Expected darkening factor to be a decimal number", args));
    else
      factor = 0.3f;
    return new LightenTag(factor);
  }

  public LightenTag(float factor) {
    this.factor = Math.min(Math.max(0, factor), 1);
  }

  @Override
  public @NotNull TextColor modifyColor(@NotNull TextColor color) {
    return TextColor.color(
      color.red() + Math.round((0xff - color.red()) * this.factor),
      color.green() + Math.round((0xff - color.green()) * this.factor),
      color.blue() + Math.round((0xff - color.blue()) * this.factor)
    );
  }

  static @Nullable Emitable claimComponent(final Component comp) {
    return null; // Wtf am I supposed to do here?
  }
}
