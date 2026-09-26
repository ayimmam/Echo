# In-country hosting options — Article 22 compliance

Checked 2026-09-26, prompted by a review of Ethio Telecom's shared-hosting pricing (`myportal.ethiotelecom.et`) against the uploaded copy of Proclamation No. 1321/2016 E.C. (= 1321/2024 G.C.), Article 22 ("Data Sovereignty"): *"Every data controller or data processor shall ensure the storage, on a server or data center located in Ethiopia, of personal data collected or obtained locally."* This matches the Negarit Gazeta text already used in [`CLAIMS_AUDIT.md`](CLAIMS_AUDIT.md) §3 (Art. 22). A copy of the Proclamation is kept at [`../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf`](../legal/Proclamation-1321-2016EC-Personal-Data-Protection.pdf).

This answers a narrower question than the rest of the pack: **where can the `infra/openeuler` sovereign-tier server (§8.1, §12) actually be hosted?**

## The screenshot in question doesn't fit the job

The Ethio Telecom pricing shown (Linux Bronze/Silver/Gold/Platinum, Plesk, ETB 650–5,200/year) is **shared web hosting** — a PHP/MySQL site host with a control panel, not a VPS or bare-metal server with root access. Dimts' sovereign tier needs to run a custom FastAPI app and openGauss/PostgreSQL, which shared Plesk hosting cannot do. Even if Ethio Telecom's data centers are in Ethiopia (they are — see below), this specific product is the wrong tier.

## Update 2026-09-26: Zergaw's own shared-hosting tier has the same problem

The user found Zergaw's shared-hosting page (`zergaw.com/shared-hosting-platform/`), which states **"Hosting Location: Ethiopia"** per plan — even more explicit Article 22 evidence than anything found earlier. But most of its tiers have the same fit problem as the Ethio Telecom screenshot:

| Zergaw plan | Server | Root access | Price | Fits `infra/openeuler`? |
|---|---|---|---|---|
| Basic | "None (shared)" | No | ETB 5,481/**year** | No — shared hosting, "Python support" is a WSGI/Passenger app slot, not a machine you control |
| Standard | "None (shared)" | No | ETB 7,035/year | No |
| Premium | "None (shared)" | No | ETB 10,139/year | No |
| **Dedicated** | 2 CPU, 4 GB RAM, dedicated IP | **Yes** | ETB 5,646.55/**month** (≈ ETB 67,760/year) | **Yes** — this is the one that can install openGauss and run FastAPI, WorkManager ingest, and cron jobs as your own daemons |

The "Dedicated" row is a materially different product from the shared tiers above it, despite being listed on the same pricing page. It overlaps in spec (2 vCPU/4 GB) with a similarly-priced entry on Zergaw's separate Cloud Server (VPS) page (`CS02004`, ETB 5,205/month, quoted in the table below) — confirm with Zergaw sales whether "Dedicated" (shared-hosting page) and the `CS02004` cloud server (VPS page) are the same underlying product, and whether either lets you provision an openEuler image rather than their default OS template.

**Rule of thumb going forward:** on any Ethiopian host's pricing page, "shared hosting" / "Server: none" / a Plesk-or-cPanel-style feature list (subdomains, email accounts, "N databases") means no root and no ability to install openGauss or run your own background services, whatever the location says. Only a line item that names CPU/RAM and says **root/dedicated IP** (VPS, cloud server, dedicated server, bare metal) is a candidate for `infra/openeuler`.

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
- **Actual database compatibility.** Zergaw's DBaaS lists PostgreSQL/MySQL/MariaDB, not openGauss by name. openGauss is PostgreSQL-lineage (per `CLAIMS_AUDIT.md` §4, 7j), so a managed *PostgreSQL* instance is a reasonable substitute if openGauss itself isn't offered — this feeds back into ADR-005 (backend/database choice).

## Recommendation

For a **budget self-service VPS or managed Postgres with an explicit local-law framing**, contact **Zergaw** first, specifically about their **Dedicated** server or `CS02004` cloud server (both root, 2 vCPU/4 GB) — not the Basic/Standard/Premium shared tiers, which don't give you a machine to install anything on regardless of their stated location. For the strongest **compliance and pitch narrative** ("hosted on Ethiopia's own national data center"), get a quote from **INSA's G-Cloud**. Check **Ethio Telecom teleCloud** too, given the Huawei Cloud Stack overlap with the rest of the competition build. Treat Raxio and UT Solutions as colocation/build options only, not as what you want for W0–W1's spike S6.
