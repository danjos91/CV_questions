# Q41: Probability of More Heads Than Tails in 4 Tosses

## Problem Statement

**Russian:** Честную монетку подкинули 4 раза. С какой вероятностью количество орлов в этих подбрасываниях будет больше, чем количество решек?

**English:** A fair coin is tossed 4 times. What is the probability that the number of heads in these tosses is greater than the number of tails?

**Answer choices:**

1. $9/16$
2. $1/2$
3. $5/16$
4. $1/4$

---

## Answer

**Option 3: $5/16$**

Out of $16$ equally likely outcomes, exactly $5$ have strictly more heads than tails (4 heads, or 3 heads and 1 tail).

---

## Theory: Binomial Counts for a Fair Coin

### What the problem is asking

Let $H$ be the number of heads and $T$ the number of tails in $n = 4$ independent tosses of a **fair** coin. Then $H + T = 4$, so

$$
H > T \quad\Longleftrightarrow\quad H > 4 - H \quad\Longleftrightarrow\quad H > 2
$$

The required probability is $P(H \ge 3) = P(H = 3) + P(H = 4)$.

### Sample space

Each toss is heads or tails, so there are $2^4 = 16$ possible sequences. For a fair coin they are **equally likely**, each with probability $1/16$. Counting favorable sequences and dividing by $16$ is enough.

### Binomial distribution

$H \sim \operatorname{Bin}(n, p)$ with $n = 4$ and $p = 1/2$:

$$
P(H = k) = \binom{4}{k} \left(\frac{1}{2}\right)^4 = \frac{\binom{4}{k}}{16}
$$

| $k$ (heads) | Tails $4-k$ | $\binom{4}{k}$ | $H > T$? |
|-------------|----------------|----------------|----------|
| $0$ | $4$ | $1$ | no |
| $1$ | $3$ | $4$ | no |
| $2$ | $2$ | $6$ | **no** (tie) |
| $3$ | $1$ | $4$ | **yes** |
| $4$ | $0$ | $1$ | **yes** |

### Why the answer is not $1/2$

By symmetry $P(H > T) = P(H < T)$. These two events do **not** cover the whole sample space: they miss the tie $H = T$.

$$
P(H > T) + P(H < T) + P(H = T) = 1
$$

$$
P(H > T) = \frac{1 - P(H = T)}{2}
$$

For **even** $n$, a tie is possible, so $P(H > T) < 1/2$. That is why option $1/2$ is the main trap.

---

## How to Solve (Step by Step)

### Step 1: Translate the inequality

$$
H > T \iff H > 2 \iff H \in \{3, 4\}
$$

### Step 2: Count sequences with 3 heads

Choose which 3 of the 4 tosses are heads:

$$
\binom{4}{3} = 4
$$

Sequences: HHHT, HHTH, HTHH, THHH.

### Step 3: Count sequences with 4 heads

$$
\binom{4}{4} = 1
$$

Sequence: HHHH.

### Step 4: Divide by the total

$$
P = \frac{4 + 1}{16} = \frac{5}{16}
$$

### Alternative: use symmetry and subtract the tie

$$
P(H = 2) = \frac{\binom{4}{2}}{16} = \frac{6}{16}
$$

$$
P(H > T) = \frac{1 - 6/16}{2} = \frac{5}{16}
$$

Same result, and it immediately shows that $1/2$ is too large.

---

## Why the Other Options Are Wrong

| Option | Value | Typical mistake | Why it fails |
|--------|-------|-----------------|--------------|
| **1** | $9/16$ | $P(H \in \{1,3,4\}) = (4+4+1)/16$, or $1 - P(H=0) - P(H=2)$ | Still includes $H = 1$, where tails **outnumber** heads. Ties and all-tails were dropped, but 3-tails-1-head was not |
| **2** | $1/2$ | “Heads and tails are symmetric, so more heads is half” | Ignores the $6$ tie outcomes. Symmetry gives $P(H>T)=P(H<T)$, **not** $P(H>T)=1/2$ |
| **4** | $1/4$ | Only $H = 3$ ($4/16$), or solving $n = 2$ instead of $n = 4$ | Misses the all-heads sequence HHHH. Conditioning on “no tie” would give $5/10 = 1/2$, not $1/4$ |

**Common trap — Option 2:** Fairness does not make “strictly more heads” happen half the time when $n$ is even. The $6$ sequences HHTT, HTHT, HTTH, THHT, THTH, TTHH are draws.

**Common trap — Option 1:** Strict vs non-strict. $P(H \ge T) = (6+4+1)/16 = 11/16$ is a different (and unlisted) misread. $9/16$ is the nearby count that drops ties and all-tails but keeps $H = 1$.

**Common trap — Option 4:** $4/16 = 1/4$ is $P(H = 3)$ alone. The event also includes HHHH.

---

## Other Variants of This Question

Examiners reuse the same binomial idea with a changed inequality, a changed $n$, or a biased coin.

### Variant A: At least as many heads as tails ($H \ge T$)

**Question:** Probability that heads are **not fewer** than tails.

$$
P(H \ge 2) = \frac{6 + 4 + 1}{16} = \frac{11}{16}
$$

This is **not** $9/16$ and **not** $1/2$.

---

### Variant B: Unequal number of heads and tails ($H \neq T$)

$$
P(H \neq T) = 1 - \frac{6}{16} = \frac{10}{16} = \frac{5}{8}
$$

Equivalently $2 \times 5/16 = 10/16$.

---

### Variant C: Odd number of tosses (no tie possible)

**Question:** Same event $H > T$ for $n = 5$.

Then $H + T = 5$ is odd, so $H = T$ is impossible. Symmetry now **does** give $1/2$:

$$
P(H > T) = P(H \ge 3) = \frac{\binom{5}{3}+\binom{5}{4}+\binom{5}{5}}{32} = \frac{10+5+1}{32} = \frac{16}{32} = \frac{1}{2}
$$

**Rule:** $P(\text{more heads}) = 1/2$ for a fair coin iff $n$ is **odd**.

---

### Variant D: Exactly 3 heads

$$
P(H = 3) = \frac{\binom{4}{3}}{16} = \frac{4}{16} = \frac{1}{4}
$$

This is **Option 4** of the original test — a different question.

---

### Variant E: At least 3 heads (same as the original)

$H \ge 3$ is identical to $H > T$ when $n = 4$. Wording can change; the count does not.

---

### Variant F: First compute $P(H > 2)$ via binomial formula

$$
P(H > 2) = \sum_{k=3}^{4} \binom{4}{k} \left(\frac{1}{2}\right)^4 = \frac{5}{16}
$$

Use this form when $p \neq 1/2$ (then sequences are **not** equally likely and listing $16$ outcomes is the wrong method).

---

### Variant G: Biased coin

**Question:** $P(\text{heads}) = 2/3$, four tosses, $P(H > T)$.

$$
P(H > 2) = \binom{4}{3}\left(\frac{2}{3}\right)^3\left(\frac{1}{3}\right) + \binom{4}{4}\left(\frac{2}{3}\right)^4 = 4 \cdot \frac{8}{27} \cdot \frac{1}{3} + \frac{16}{81} = \frac{32}{81} + \frac{16}{81} = \frac{48}{81} = \frac{16}{27}
$$

Symmetry $P(H>T)=P(H<T)$ **fails** because $p \neq 1/2$.

---

### Variant H: Two coins, or $n = 2$

For $n = 2$: outcomes HH, HT, TH, TT. $H > T$ only for HH, so $1/4$.

That matches **Option 4** of the original test if someone solves the **wrong** $n$.

---

## Quick Reference: Pattern Recognition

| What you see | What to compute |
|--------------|-----------------|
| Fair coin, $n$ tosses, sequences equally likely | $2^n$ outcomes; $P(H=k)=\binom{n}{k}/2^n$ |
| $H > T$ | $H > n/2$ |
| $n$ even, fair coin | $P(H>T) = (1 - \binom{n}{n/2}/2^n)/2 < 1/2$ |
| $n$ odd, fair coin | $P(H>T) = 1/2$ (no tie) |
| $H \ge T$ | include the $\binom{n}{n/2}$ tie term when $n$ is even |
| Biased coin | cannot cancel $p^k(1-p)^{n-k}$; use the full binomial formula |

**Mnemonic:** For a fair coin, “more heads than tails” is half the outcomes **except the ties**. Four tosses have $6$ ties, so $(16-6)/2 = 5$ favorable, probability $5/16$.

---

## Summary

| Step | Action |
|------|--------|
| 1 | $H > T \iff H > 2$ for $n = 4$ |
| 2 | Favorable: $\binom{4}{3} + \binom{4}{4} = 4 + 1 = 5$ |
| 3 | Total: $2^4 = 16$ |
| 4 | $P = 5/16$; reject $1/2$ because of $6$ ties |

**Final answer: Option 3 — $5/16$**
