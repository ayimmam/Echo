# Huawei Innovation Competition deployment plan

Planning update: 2026-10-11. No cloud deployment exists. The [detailed implementation plan](../../docs/competition/INNOVATION_IMPLEMENTATION_PLAN.md) maps the supplied 2026–2027 Northern Africa criteria to implementation and evidence; the current division rules and interpretation still need organizer confirmation.

The proposed competition application runs real versioned inference on allowlisted, licensed demonstration audio and provides an interactive visual page on Huawei ECS. Synthetic multi-lesson reports remain explicitly labeled; they cannot substitute for actual model results. A measured MindSpore model/runtime contribution is the preferred Huawei AI path, subject to feasibility testing.

This extends the former synthetic-records-only deployment proposal to include approved, redistribution-compatible public/demo audio for real inference. **No classroom recordings, private notes, pilot credentials or teacher-linked records enter Huawei Cloud.** The phone continues to perform classroom analysis locally; a future Ethiopian research intake is separate.

Use one bounded ECS deployment initially. ModelArts/OBS are conditional where they make a demonstrated training/artifact contribution and fit verified credits/region availability. RDS/Redis are unnecessary until measurements justify them. Placeholder directories are not evidence of provisioning.

Create a separate competition service; preserve the loopback-only synthetic server guard. Reuse versioned analysis contracts where appropriate, without making the cloud stack dictate Zergaw's future runtime. Deployment work must include HTTPS, bounded requests/concurrency, allowlisted sample IDs, model/build identity, safe errors, health checks, cost limits, rollback, reproducible setup and teardown.

Cloud setup begins only after the team has confirmed rules, dataset/model rights, region/quota and a funded spending ceiling. Neither this plan nor the app's local storage implementation authorizes publication or real-data transfer. See plan gates G0–G3 and G6 for deployment and submission evidence.
