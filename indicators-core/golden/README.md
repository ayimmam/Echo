# Golden vectors

Hand-calculated `acceptance-v1` requests and expected results shared by the Python reference and the
future Kotlin port (1e-6 tolerance for floats; counts and nulls exact).

* `coarse-v0-draft.json` -- the reduced ADULT / CHILD_UNSPECIFIED schema (spec 3). **Draft:** unfrozen until G1.

The original five-class oracles are `tests/acceptance/vectors.json`. Add cases by calculating the
expected values independently first; never paste the implementation's output as the oracle.
