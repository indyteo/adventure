package net.kyori.adventure.text.minimessage.tag.standard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import net.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class InvertTag extends ColorModifyingTag {
  private static final String INVERT = "invert";

  static final TagResolver RESOLVER = SerializableResolver.claimingComponent(INVERT, InvertTag::create, InvertTag::claimComponent);

  static Tag create(final ArgumentQueue args, final Context ctx) throws ParsingException {
    return new InvertTag();
  }

  @Override
  public @NotNull TextColor modifyColor(@NotNull TextColor color) {
    return TextColor.color(~color.value());
  }

  static @Nullable Emitable claimComponent(final Component comp) {
    return null; // Wtf am I supposed to do here?
  }
}
