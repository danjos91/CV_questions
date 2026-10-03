# Yandex Cup 2026 — Algorithm track — preparation kit

Qualification: **1 November 2026**, online, 240 minutes, Yandex Contest.
Trial round: **12–18 October** (optional, same platform, past problems).
Final: 27–29 November, Moscow.

Files:

- [`Template.java`](Template.java) — contest template: fast IO, big-stack thread, sort that survives anti-quicksort tests, hash mixer, math helpers.
- this file — plan, checklists and the weekly log.

## Calibration (from the 2025 Algorithm qualification)

| Fact | Value |
| --- | --- |
| Problems | 11 (A–K), some with test groups / partial score |
| Ranking | solved count, then penalty time |
| Rank 1 | 11 solved |
| Ranks 10–26 (finalist zone) | 9 solved |
| Rank 40 | 8 solved |
| Ranks 200–250 | 4 solved |
| Problem A | ~700 accepted / ~3100 attempts |

Top 20 in the open developers stream is roughly Codeforces 2400+.

**Goal for 1 November 2026: 5–6 solved, clean, no penalty from careless WAs.**
If the 2026 rules add per-grade finalist quotas, the same plan applies and the final becomes reachable.

## Setup checklist (week 1)

- [ ] Register at [yandex.ru/cup](https://yandex.ru/cup), direction «Алгоритм».
- [ ] Read the 2026 [rules](https://yandex.ru/cup/rules) when published: finalist quotas per stream/grade, number of attempts, scoring.
- [ ] Check on Yandex Contest: Java version offered, time-limit multiplier for Java (if any).
- [ ] Compile and run `Template.java` locally with a 10^6-number input; confirm it reads in < 1 s.
- [ ] Codeforces account; set up a local `in.txt` / run script so submitting from the IDE is one keystroke.
- [ ] Create `log.md` next to this file (or use the tables below) and log every failed problem.

## Java pitfalls checklist (re-read before every contest)

- [ ] Input via `FastReader`, never `Scanner`. Output via one `PrintWriter`, flushed once.
- [ ] `int[]` / `long[]` sorted only through `sort()` from the template (shuffle first). Never bare `Arrays.sort(primitiveArray)` on n > 10^4.
- [ ] Recursive DFS/DP only inside the big-stack thread (already in `main`). Depth 10^6 is fine there.
- [ ] `HashMap` with adversarial integer keys → key through `mix()`, or sort + binary search instead.
- [ ] Sums, products, prefix sums: think `long` first. `10^5 * 10^5` overflows `int`.
- [ ] Modular arithmetic: reduce after every multiplication, fix negatives with `(x % MOD + MOD) % MOD`.
- [ ] Reading `n` then `n` lines: use `next()` not `nextLine()` after a number.
- [ ] String building in loops: `StringBuilder`, not `+=`.
- [ ] `Integer` comparison with `==` is a bug above 127. Use `equals` / unbox.
- [ ] Do not allocate objects per element in hot loops (use `int[][]` instead of `List<Pair>`).

## Topic checklist

Mark a topic done only after solving ≥ 5 problems on it without looking at editorials.

### Basics
- [ ] Complexity estimation from constraints (n ≤ 10^5 → n log n, n ≤ 5000 → n^2, n ≤ 20 → 2^n)
- [ ] Sorting + custom comparators
- [ ] Two pointers
- [ ] Prefix sums (1D, 2D), difference arrays
- [ ] Binary search on index and on the answer
- [ ] Sliding window
- [ ] Hash maps / sets, frequency counting
- [ ] Stack, queue, deque, monotonic stack/queue
- [ ] Heaps / `PriorityQueue`, `TreeMap` for ordered operations
- [ ] Greedy with proof by exchange argument
- [ ] Implementation / simulation problems (careful reading, edge cases)

### Graphs
- [ ] BFS, DFS, connected components
- [ ] Bipartite check, cycle detection
- [ ] Topological sort (Kahn), DAG DP
- [ ] DSU (union by size + path compression)
- [ ] Dijkstra, 0-1 BFS
- [ ] MST (Kruskal)
- [ ] Trees: subtree sizes, depths, diameter, simple rerooting
- [ ] LCA by binary lifting (only if time permits)

### Dynamic programming
- [ ] 1D DP (stairs, coins, LIS O(n log n))
- [ ] 2D DP (grids, LCS, edit distance)
- [ ] Knapsack 0/1 and unbounded
- [ ] DP over prefixes with a "last position" transition
- [ ] Bitmask DP (n ≤ 20)
- [ ] DP on trees
- [ ] Recovering the answer, not only its value

### Math and strings
- [ ] gcd / lcm, sieve, factorization by trial division
- [ ] Fast power, modular inverse, factorials and C(n, k) mod p
- [ ] Basic combinatorics and counting with inclusion–exclusion (small)
- [ ] Polynomial string hashing (double mod or 64-bit with random base)
- [ ] Prefix function / Z-function
- [ ] Fenwick tree (point update, prefix sum)
- [ ] Segment tree (point update, range query)

### Skiena (3rd ed.) reading map — read after being stuck, not before
- [ ] Ch. 2 Algorithm analysis
- [ ] Ch. 4 Sorting
- [ ] Ch. 7 Graph traversal
- [ ] Ch. 8 Weighted graphs
- [ ] Ch. 9 Combinatorial search (backtracking)
- [ ] Ch. 10 Dynamic programming

## Weekly plan

Rhythm: weekdays 1.5–2 h (3–4 problems on the week's topic, slightly above comfort, always up-solve), Saturday a 4-hour virtual contest, Sunday 1–2 h of editorials for what you didn't solve. ~12–15 h/week.

### Week 1 — Sep 12–18 — baseline and tooling
- [ ] Setup checklist above done
- [ ] Virtual: Yandex Cup 2025 qualification, problems A–D (4 h, real conditions)
- [ ] Topics: complexity, sorting, two pointers, prefix sums, binary search
- [ ] Codeforces: 10 problems, rating 1000–1300

### Week 2 — Sep 19–25 — linear structures and greedy
- [ ] Topics: hash maps, stack/queue/deque, monotonic stack, sliding window, heaps, greedy
- [ ] Codeforces: 10 problems, rating 1200–1500
- [ ] Saturday virtual: Yandex Cup 2024 qualification
- [ ] Sunday: editorials, log updated

### Week 3 — Sep 26–Oct 2 — graphs
- [ ] Topics: BFS/DFS, components, bipartite, topological sort, DSU, Dijkstra, 0-1 BFS, MST
- [ ] Codeforces: 10 problems, rating 1300–1600, graph tag
- [ ] Skiena ch. 7–8
- [ ] Saturday virtual: any Codeforces Div. 2 (virtual participation)

### Week 4 — Oct 3–9 — dynamic programming
- [ ] Topics: 1D/2D DP, knapsack, LIS/LCS, bitmask DP, DP on trees
- [ ] Codeforces: 10 problems, rating 1300–1700, dp tag
- [ ] Skiena ch. 10
- [ ] Saturday virtual: Yandex Cup 2023 qualification

### Week 5 — Oct 10–16 — math, strings, trial round
- [ ] Topics: gcd/sieve/modpow/inverse, C(n,k), hashing, prefix/Z-function, Fenwick, segment tree
- [ ] **Trial round on Yandex Contest (12–18 Oct)** in Java with `Template.java`
- [ ] Note: Java version, TL behaviour, judge quirks → update pitfalls checklist

### Week 6 — Oct 17–23 — mixed sets and speed
- [ ] Two full 4-hour virtuals (Yandex Cup 2022 qualification + one more past set)
- [ ] Targets: A accepted ≤ 10 min, B ≤ 25 min, all statements read in first 10 min
- [ ] Re-solve every problem from the failure log that was "idea" type

### Week 7 — Oct 24–31 — taper
- [ ] One virtual (Saturday Oct 24 or 25)
- [ ] 1 h/day easy-medium problems for confidence
- [ ] No new theory after Oct 28
- [ ] Re-read Java pitfalls and contest-day checklist
- [ ] Oct 31: rest, template compiled and open, normal sleep

## Contest-day checklist (1 November)

Before start:
- [ ] Template compiled, `in.txt` ready, IDE and Yandex Contest tab open, logged in
- [ ] Water, no other tabs, phone away, 4 h blocked in the calendar

Minutes 0–10:
- [ ] Skim all 11 statements, note constraints and whether the problem has test groups
- [ ] Order by accepted count in standings, not by letter (in 2025 G was easier than F)

During:
- [ ] Easy problems first — penalty is time of solution, every early AC counts
- [ ] Before submitting: sample passed, `long` checked, n = 1 and max-n cases considered
- [ ] Test groups → brute force for the small group is real points, submit it
- [ ] 40 minutes without progress → switch problems, come back later
- [ ] WA on a "sure" solution → write brute force + random stress (see below), do not resubmit blindly

Stress-test loop (bash, brute `Brute.java` vs `Sol.java`, generator `Gen.java` printing a random test):

```bash
for i in $(seq 1 500); do
  java Gen $i > t.txt
  java Brute < t.txt > b.txt
  java Sol   < t.txt > s.txt
  cmp -s b.txt s.txt || { echo "diff on test $i"; cat t.txt; break; }
done
```

## Weekly log

| Week | Hours | Problems solved | Virtual score | Weakest topic | Note |
| --- | --- | --- | --- | --- | --- |
| 1 |  |  |  |  |  |
| 2 |  |  |  |  |  |
| 3 |  |  |  |  |  |
| 4 |  |  |  |  |  |
| 5 |  |  |  |  |  |
| 6 |  |  |  |  |  |
| 7 |  |  |  |  |  |

## Failure log

Reason codes: **I** = no idea, **B** = bug in a correct idea, **S** = too slow to code, **IO** = input/output/TLE from Java, **R** = misread statement.

| Date | Problem (link) | Topic | Reason | Fix / what to remember |
| --- | --- | --- | --- | --- |
|  |  |  |  |  |

## Resources

- Past Yandex Cup problems: [yandex.ru/cup/algorithm](https://yandex.ru/cup/algorithm) → «задачи прошлых лет», and the trial round on contest.yandex.ru
- 2025 qualification standings (calibration): [contest.yandex.com/contest/84268/standings](https://contest.yandex.com/contest/84268/standings/)
- Яндекс «Тренировки по алгоритмам» — free, same judge, A–E level
- [cp-algorithms.com](https://cp-algorithms.com) — theory + code for every topic above
- Codeforces problemset, filter by rating and tag
- Skiena, «Алгоритмы. Руководство по разработке», 3-е изд. — reference, see reading map above
- Neagoie (Udemy) — refresher only, ≤ 5 h total
