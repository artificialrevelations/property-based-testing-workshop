package org.artrev.workshop.pbt.examples.shoppingcart;

import net.jqwik.api.Tuple;
import net.jqwik.api.stateful.Action;

public interface Command<M, S> extends Action<Tuple.Tuple2<M, S>> {
    default boolean precondition(final M model, final S sut) {
        // no checks by default
        return true;
    }

    void execute(final M model, final S sut);

    default void postcondition(final M model, final S sut) {
        // no checks by default
    }

    @Override
    default Tuple.Tuple2<M, S> run(final Tuple.Tuple2<M, S> state) {
        final M model = state.get1();
        final S sut = state.get2();

        execute(model, sut);
        postcondition(model, sut);

        return Tuple.of(model, sut);
    }

    @Override
    default boolean precondition(final Tuple.Tuple2<M, S> state) {
        final M model = state.get1();
        final S sut = state.get2();

        return precondition(model, sut);
    }
}
