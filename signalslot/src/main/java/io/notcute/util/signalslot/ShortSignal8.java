package io.notcute.util.signalslot;

/**
 * A short signal with 8 generic arguments.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <D> The type of the forth argument.
 * @param <E> The type of the fifth argument.
 * @param <F> The type of the sixth argument.
 * @param <G> The type of the seventh argument.
 * @param <H> The type of the eighth argument.
 */
public class ShortSignal8<A, B, C, D, E, F, G, H> extends FunctionalShortSignal<ShortSlot8<A, B, C, D, E, F, G, H>> {

	/**
	 * @see Signal#invoke(Object...)
	 */
	public short emit(A a, B b, C c, D d, E e, F f, G g, H h) {
		return super.emit(a, b, c, d, e, f, g, h);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected short actuateShort(Slot<?> slot, Object... args) {
		if (slot instanceof DynamicShortSlot) return ((DynamicShortSlot) slot).accept(args);
		else if (slot instanceof ShortSlot8)
 return 			((ShortSlot8<A, B, C, D, E, F, G, H>) slot).accept((A)args[0], (B)args[1], (C)args[2], (D)args[3], (E)args[4], (F)args[5], (G)args[6], (H)args[7]);
		else throw new IllegalArgumentException("Invalid slot type: " + slot.getClass());
	}

}
