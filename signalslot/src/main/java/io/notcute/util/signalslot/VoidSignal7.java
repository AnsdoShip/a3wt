package io.notcute.util.signalslot;

/**
 * A void signal with 7 generic arguments.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <D> The type of the forth argument.
 * @param <E> The type of the fifth argument.
 * @param <F> The type of the sixth argument.
 * @param <G> The type of the seventh argument.
 */
public class VoidSignal7<A, B, C, D, E, F, G> extends FunctionalVoidSignal<VoidSlot7<A, B, C, D, E, F, G>> {

	/**
	 * @see Signal#invoke(Object...)
	 */
	public void emit(final A a, final B b, final C c, final D d, final E e, final F f, final G g) {
		super.emit(a, b, c, d, e, f, g);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void actuateVoid(Slot<?> slot, final Object... args) {
		if (slot instanceof DynamicVoidSlot) ((DynamicVoidSlot) slot).accept(args);
		else if (slot instanceof VoidSlot7)
			((VoidSlot7<A, B, C, D, E, F, G>) slot).accept((A)args[0], (B)args[1], (C)args[2], (D)args[3], (E)args[4], (F)args[5], (G)args[6]);
		else throw new IllegalArgumentException("Invalid slot type: " + slot.getClass());
	}

}
