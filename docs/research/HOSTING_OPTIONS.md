# In-country hosting options — Article 22 compliance

> 2026-09-27 follow-up: this is a dated provider shortlist, not a selected host. The undergraduate STEM trial uses the same in-country privacy boundary as early grades. See [INFRASTRUCTURE_RECHECK.md](INFRASTRUCTURE_RECHECK.md): exact runtime/database compatibility, authenticated connections, provider backup/location terms and restore tests are mandatory selection gates; the October revision below removes the OS-specific baseline. Prices below were not revalidated in this pass.


Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).

This answers a narrower question than the rest of the pack: **where can the in-country application and separate research services (§8.1, §12) actually be hosted?**

## 2026-10-02 correction: shared hosting is a conditional candidate

The earlier rejection of all shared hosting was too broad. Python application support can be sufficient for derived records and reports without root access. The user intends Zergaw Standard and supplied screenshots advertising Ethiopia hosting, Python/SSH, weekly backup, 100 GB and ETB 7,035/year. These are screenshot claims, not verified capacity or a current quote; the cropped “Unlimited” rows do not establish application resources.

Neither the dashboard image nor “Python support” establishes ASGI, WSGI, Passenger, installed databases, scheduler rights or tenant isolation. The user confirmed these are **not yet confirmed**. Do not assume a particular gateway or infer feasibility from storage quota.

Use [ADR-015](../adr/015-capability-gated-hosting.md) and the [shared-hosting intake](../../infra/in-country/shared-hosting/README.md): managed ASGI permits FastAPI/PostgreSQL; WSGI permits Django with a supported selected database. Verify dependency installation, process/restart and job limits, transaction semantics, private files/secrets, protected DB access, local backups/logs, and measured load. Weekly backup alone fails the proposed daily recovery target. Use a VPS if any required capability or security boundary fails.

Shared hosting never receives research audio, annotation or training workloads. No separate research machine is confirmed. Draft [ADR-016](../adr/016-defence-scope-and-no-recording.md) removes Research Mode/new recording services; [research setup](../../infra/research/README.md) now covers approved existing corpora and observer summaries. Personal-data sync is optional, but any enabled server must pass the same controls; phone-only field storage needs an Article 22 decision. Root access and an openEuler image are not prerequisites for the shared application profile.

The historical provider shortlist below is retained as research, not as current pricing, compliance certification or a selected deployment. Reconfirm every commercial and location claim with the provider before selection.

## Providers that offer rentable infrastructure in Ethiopia

| Provider | Confirmed location | Offering | Fits the sovereign-tier server? | Price |
|---|---|---|---|---|
| **INSA G-Cloud** — `cloud.gov.et` | ICT Park, Addis Ababa. Ethiopia's National Data Center, run by the Information Network Security Administration. | IaaS (Elastic Cloud Servers, Bare Metal Servers, VPC, storage), PaaS (container service, **managed MySQL/PostgreSQL/document DB**), SaaS (email, workspace), backup/DR, firewalls, vulnerability scanning. | Best compliance story: a government-run sovereign cloud built explicitly around "local data sovereignty." Targets government, finance, telecom and enterprise; doesn't explicitly exclude a university research project. | Not published; request a quote. |
| **Ethio Telecom teleCloud** — `telecloud.ethiotelecom.et` | Ethio Telecom's own data centers, built on **Huawei Cloud Stack** as Ethiopia's national sovereign cloud (Ethio Telecom, 2026; Huawei Cloud case study). | IaaS/PaaS/SaaS; billing via telebirr. | Same Huawei stack as MindSpore/ModelArts — a clean story for the competition pitch. **Site returned no response from this session** (network policy); verify pricing and Postgres/DB offerings directly. | Not confirmed here. |
| **Zergaw Cloud** — `zergaw.com` | HQ and infrastructure at ICT Park, Addis Ababa. Founded 2019; states government institutions among its ~500 clients. | Cloud servers and bare-metal servers (9 tiers, 1–32 vCPU / 2–64 GB RAM), shared hosting, and a **Database-as-a-Service** for PostgreSQL/MySQL/MariaDB marketed as *"Quality and secured databases at local host under Ethiopian legal framework."* | Closest self-service match: rentable VPS with root, or a managed Postgres instance, in ETB, from a company already positioning itself around local legal compliance. | Cloud servers: **ETB 3,272/month** (1 vCPU, 2 GB, 30 GB) up to **ETB 60,573/month** (32 vCPU, 64 GB). DBaaS pricing not published on the page fetched. |
| **Raxio Ethiopia (ET1)** | ICT Park, Addis Ababa; Tier III certified (Uptime Institute); 800 racks, 3 MW IT power. | **Colocation only** — cross-connect, fibre, carrier-neutral connectivity. You supply and manage the physical server. | Only relevant if the university wants to rack its own hardware in a proper facility instead of hosting on campus. Not a managed-hosting or VPS product. | Colocation pricing not published; comparable retail colocation elsewhere in Addis runs USD 380–650/kW/month (Safaricom Ethiopia, per UT Solutions' 2026 guide). |
| **UT Solutions PLC** | Addis Ababa HQ; builds and operates data centers (Tier III/IV), including a private edge deployment inside Hawassa Industrial Park for one industrial tenant. | Data-center design, build and managed operations — not a rentable public cloud. | Not a fit as a hosting product. Worth knowing that Hawassa-area data-center-grade infrastructure has been built before, if the university ever wanted a custom on-site facility. | N/A |

## What this doesn't resolve

- **Article 33 registration.** None of these providers' marketing pages mention helping customers register as a data controller/processor with the Ethiopian Communications Authority. That's still the team's responsibility regardless of host (see `CLAIMS_AUDIT.md` §3, C-7f; `RESEARCH_PLAN.md` WS-F Q1).
- **Whether renting from a private Ethiopian cloud (Zergaw, teleCloud) satisfies Art. 22** as fully as the government's own G-Cloud. The article only requires the server/data center be *located in Ethiopia* — it doesn't require a government operator — but confirm this reading with the legal advisor alongside the other WS-F questions.
- **Ethio Telecom and HostHabesha sites were unreachable from this session** (network policy), so their VPS/teleCloud pricing and specs are unverified here. Fetch them directly.
- **Actual database compatibility.** The historical DBaaS list does not establish engines included with Standard. Confirm the exact plan, versions, driver, transaction, migration and connection-security behavior. PostgreSQL is preferred, with supported MySQL/MariaDB conditional on the Django path.

## Recommendation

Assess Zergaw Standard first using the capability manifest and HOST/SEC acceptance plan. Its suitability is **unknown**, not rejected or approved. Compare the full cost of daily backups, reliable scheduled jobs, support and capacity with a local VPS if the gates cannot be met. Use one maintained application and one tested database; do not install openGauss merely for a competition technology mapping. Retain alternative providers as fallbacks with fresh contractual and technical evidence.
