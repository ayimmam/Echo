# Synthetic lessons

`synth_timeline.py` emits seeded **label timelines** (10 ms ticks, `coarse-v0-draft` labels, optional
`MISSING` interruptions). There is no audio, no feature, no transcript and no real person, so these are
safe to commit and to use in the Huawei Cloud demo tier.

They are plausible fixtures, not measurements: nothing here says what a real classroom sounds like.
`tools/demo/generate_mock_data.py` feeds them to the `indicators-core` reference to build the demo
lesson records. `audio_mix.py` (ML-owned synthetic *audio* mixtures) is not yet written and would need
the provenance review described in the README before use.
