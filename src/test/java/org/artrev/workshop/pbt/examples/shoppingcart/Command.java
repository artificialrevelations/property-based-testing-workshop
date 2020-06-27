package org.artrev.workshop.pbt.examples.shoppingcart;

import net.jqwik.api.Tuple;
import net.jqwik.api.stateful.Action;

public interface Command<M, S> extends Action<Tuple.Tuple2<M, S>> {
    default boolean checkPreconditions(final M model) {
        return true;
    }

    void execute(final M model, final S sut);

    void checkPostconditions(final M model, final S sut);

    /*
      Leveraging the jqwik Actions to implement model based stateful testing.
      JQwik implements action that can take some state S, if we want to have
      model based approach we need to pass a pair of Model and SUT as the state.
    */
    @Override
    default boolean precondition(final Tuple.Tuple2<M, S> state) {
        return checkPreconditions(state.get1());
    }

    @Override
    default Tuple.Tuple2<M, S> run(final Tuple.Tuple2<M, S> state) {
        execute(state.get1(), state.get2());
        checkPostconditions(state.get1(), state.get2());
        return state;
    }
}
