package io.notcute.util.signalslot;

/**
 * A signal with 9 generic arguments.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <D> The type of the forth argument.
 * @param <E> The type of the fifth argument.
 * @param <F> The type of the sixth argument.
 * @param <G> The type of the seventh argument.
 * @param <H> The type of the eighth argument.
 * @param <I> The type of the ninth argument.
 * @param <R> The type of the returned value.
 */
public class Signal9<A, B, C, D, E, F, G, H, I, R> extends FunctionalSignal<Slot9<A, B, C, D, E, F, G, H, I, R>, R> {

	/**
	 * @see Signal#invoke(Object...)
	 */
	public R emit(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
		return super.emit(a, b, c, d, e, f, g, h, i);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected R actuate(Slot<?> slot, Object... args) {
		if (slot instanceof DynamicSlot) return ((DynamicSlot<R>) slot).accept(args);
		else if (slot instanceof Slot9) return ((Slot9<A, B, C, D, E, F, G, H, I, R>) slot)
				.accept((A) args[0], (B) args[1], (C) args[2], (D) args[3], (E) args[4], (F) args[5], (G) args[6], (H) args[7], (I) args[8]);
		else throw new IllegalArgumentException("Invalid slot type: " + slot.getClass());
	}

}
