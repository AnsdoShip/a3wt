package io.notcute.util.signalslot;

/**
 * A signal with 3 generic arguments.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <R> The type of the returned value.
 */
public class Signal3<A, B, C, R> extends FunctionalSignal<Slot3<A, B, C, R>, R> {

	/**
	 * @see Signal#invoke(Object...)
	 */
	public R emit(A a, B b, C c) {
		return super.emit(a, b, c);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected R actuate(Slot<?> slot, Object... args) {
		if (slot instanceof DynamicSlot) return ((DynamicSlot<R>) slot).accept(args);
		else if (slot instanceof Slot3) return ((Slot3<A, B, C, R>) slot).accept((A) args[0], (B) args[1], (C) args[2]);
		else throw new IllegalArgumentException("Invalid slot type: " + slot.getClass());
	}

}
