package io.notcute.util.signalslot;

/**
 * A slot with 5 generic arguments.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <D> The type of the forth argument.
 * @param <E> The type of the fifth argument.
 * @param <R> The type of the returned value.
 */
@FunctionalInterface
public interface Slot5<A, B, C, D, E, R> extends FunctionalSlot<R> {

    R accept(A a, B b, C c, D d, E e);

}
