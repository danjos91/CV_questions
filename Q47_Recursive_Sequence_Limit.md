# Q47: Limit of a Recursive Sequence

**Тема:** Математический анализ

## Problem Statement

**Russian:** Найдите предел последовательности $\{a_n\}$, если $a_1 = 4$ и $a_{n+1} = \sqrt{3 + 2a_n}$.

Если в ответе получается десятичная дробь, округлите ее до двух знаков после запятой.
Образец ответа: `1` (целое число) или `0.12` (округленная десятичная дробь).

**English:** Find the limit of the sequence $\{a_n\}$ if $a_1 = 4$ and $a_{n+1} = \sqrt{3 + 2a_n}$.

If the answer is a decimal, round it to two decimal places.
Sample answer: `1` (integer) or `0.12` (rounded decimal).

---

## Answer

**3**

The sequence decreases toward **3**.

---

## Theory: Limits of Recursive Sequences

If $a_{n+1} = f(a_n)$ and $a_n \to L$, and $f$ is continuous, then the limit (when it exists) must be a **fixed point**:

$$L = f(L)$$

Existence is a separate step: typically show that the sequence is monotone and bounded, then it converges, and the limit is the attractive fixed point that matches the sign/range of the terms (here $a_n > 0$ because of the square root).

---

## How to Solve (Step by Step)

### Step 1: Assume the limit exists and find candidates

Suppose $a_n \to L$. The square root is continuous, so

$$L = \sqrt{3 + 2L}$$

Both sides are non-negative (a square root cannot be negative), so $L \geq 0$. Square both sides:

$$L^2 = 3 + 2L$$

$$L^2 - 2L - 3 = 0$$

$$(L - 3)(L + 1) = 0$$

$$L = 3 \quad \text{or} \quad L = -1$$

Discard $L = -1$ because $a_n = \sqrt{\,\cdot\,} \geq 0$ for $n \geq 2$, and $a_1 = 4 > 0$. The only possible limit is **3**.

### Step 2: Prove the sequence actually converges

It is not enough to solve $L = f(L)$; we must check that $\{a_n\}$ converges.

**Bounded below by 3.**  
$a_1 = 4 > 3$. If $a_n > 3$, then

$$a_{n+1}^2 - 9 = 3 + 2a_n - 9 = 2(a_n - 3) > 0 \implies a_{n+1} > 3$$

By induction, $a_n > 3$ for all $n$.

**Decreasing.**  
Compare $a_{n+1}$ with $a_n$:

$$a_{n+1} < a_n \iff 3 + 2a_n < a_n^2 \iff a_n^2 - 2a_n - 3 > 0 \iff (a_n - 3)(a_n + 1) > 0$$

Since $a_n > 3$, this holds. So $a_{n+1} < a_n$.

A decreasing sequence that is bounded below converges. Combined with Step 1,

$$\lim_{n \to \infty} a_n = 3$$

### Step 3: Format the answer

The limit is the integer `3` (no rounding needed).

---

## Quick numerical check

$$
\begin{align*}
a_1 &= 4 \\
a_2 &= \sqrt{3 + 8} = \sqrt{11} \approx 3.317 \\
a_3 &= \sqrt{3 + 2\sqrt{11}} \approx 3.107 \\
a_4 &\approx 3.036 \\
a_5 &\approx 3.012
\end{align*}
$$

The terms drop toward 3, as proved.

---

## Common Mistakes

1. **Keeping $L = -1$** — impossible for a square-root recurrence.
2. **Stopping after $L = 3$ without monotonicity** — a fixed point need not be the actual limit (e.g. the sequence could diverge).
3. **Computing only a few terms and rounding** — $a_2 \approx 3.32$ is not the limit.

---

## Summary

| Step | Action |
|------|--------|
| 1 | $L = \sqrt{3+2L} \implies L^2-2L-3=0 \implies L=3$ (reject $-1$) |
| 2 | $\{a_n\}$ is decreasing and $a_n > 3$, hence $a_n \to 3$ |

**Final answer: 3**
