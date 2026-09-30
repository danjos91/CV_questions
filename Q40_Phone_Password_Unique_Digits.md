# Q40: Percentage Reduction of 5-Digit Phone Passwords

## Problem Statement

**Russian:** Пароль для телефона состоит из пяти цифр. На сколько процентов сокращается число возможных комбинаций пароля, если известно, что все цифры пароля разные?

**Примечание:** Пароль телефона может начинаться с цифры «0».

**English:** A phone password consists of five digits. By what percentage is the number of possible password combinations reduced if it is known that all digits of the password are different?

**Note:** The phone password may start with the digit `0`.

**Instructions:** Write the answer as a whole number or a finite decimal. Sample format: `54.835` (in Russian forms, a comma may be used: `54,835`).

---

## Answer

**69.76**

The number of combinations falls from $100\,000$ to $30\,240$, which is a **69.76%** reduction.

In Russian decimal notation: **69,76**.

---

## Theory: Counting Passwords With and Without Repetition

### What the problem is asking

We compare two sets of 5-digit codes (digits $0$–$9$, leading zeros allowed):

| Set | Rule | Count |
|-----|------|-------|
| **Unrestricted** | Digits may repeat | $10^5$ |
| **Restricted** | All five digits distinct | $P(10, 5) = 10 \times 9 \times 8 \times 7 \times 6$ |

The question asks for the **relative decrease** of the first count down to the second, as a percentage of the original:

$$
\text{percentage reduction} = \frac{N_{\text{all}} - N_{\text{unique}}}{N_{\text{all}}} \times 100\%
$$

This is **not** the remaining share $N_{\text{unique}} / N_{\text{all}}$, and **not** a reduction relative to $N_{\text{unique}}$.

### Key counting formulas

A password is an **ordered** 5-tuple of digits. Order matters (`12345` $\neq$ `54321`), so we use permutations, not combinations.

| Situation | Formula | Meaning |
|-----------|---------|---------|
| **With repetition** | $n^k$ | Each of $k$ positions independently has $n$ choices |
| **Without repetition** (permutations) | $P(n, k) = \dfrac{n!}{(n-k)!} = n(n-1)\cdots(n-k+1)$ | First position: $n$ choices, second: $n-1$, \ldots |
| **Unordered subsets** | $C(n, k) = \dfrac{n!}{k!(n-k)!}$ | **Wrong** for passwords — order is ignored |

Here $n = 10$ (digits $0$–$9$) and $k = 5$. Leading zeros are allowed, so we do **not** treat the first digit specially.

### Percentage change

If a quantity drops from $A$ to $B$:

$$
\text{reduction by } p\% \quad\Longleftrightarrow\quad p = \frac{A - B}{A} \times 100
$$

Equivalently:

$$
p = \left(1 - \frac{B}{A}\right) \times 100
$$

The remaining share is $B/A \times 100\%$; examiners often ask for one and hope you report the other.

---

## How to Solve (Step by Step)

### Step 1: Count all 5-digit passwords (repeats allowed)

Each of the five positions can be any of $10$ digits:

$$
N_{\text{all}} = 10^5 = 100\,000
$$

Examples: `00000`, `11111`, `01210` are all valid.

### Step 2: Count passwords with all distinct digits

Choose digits sequentially without reuse:

$$
N_{\text{unique}} = 10 \times 9 \times 8 \times 7 \times 6 = 30\,240
$$

Same as $P(10, 5) = 10! / (10-5)! = 10! / 5! = 30\,240$.

### Step 3: Compute the absolute decrease

$$
N_{\text{all}} - N_{\text{unique}} = 100\,000 - 30\,240 = 69\,760
$$

These $69\,760$ codes are exactly those that use **at least one repeated** digit.

### Step 4: Convert to a percentage of the original

$$
p = \frac{69\,760}{100\,000} \times 100 = 69.76
$$

**Final answer: 69.76** (or **69,76**).

---

## Why Nearby Answers Are Wrong

| Wrong value | How it appears | Why it fails |
|-------------|----------------|--------------|
| **30.24** | $N_{\text{unique}} / N_{\text{all}} \times 100$ | Remaining share, not the **reduction** |
| **230.69…** | $(N_{\text{all}} - N_{\text{unique}}) / N_{\text{unique}} \times 100$ | Reduction relative to the smaller set |
| **69.76 with extra rounding** | e.g. $70$ or $69.8$ | The fraction is already a finite decimal; do not round |
| **99.748** | Using $C(10,5)=252$ instead of $P(10,5)$ | Combinations ignore order; passwords are ordered |
| **72.784** | Unrestricted $10^5$, but unique codes counted as $9 \times 9 \times 8 \times 7 \times 6$ | Mixes “leading $0$ allowed” with “leading $0$ forbidden”. Using **both** counts without leading $0$ still gives $69.76$ (see Variant B) |
| **69.76 as $6976$** | Forgetting that the question already asks for a percentage | The field wants $69.76$, not $0.6976$ or $69760$ |

**Common trap — remaining vs reduced:** $30.24 + 69.76 = 100$. If you compute the unique-password share and stop, you have answered a different question.

**Common trap — combinations:** $C(10,5)$ counts unordered 5-subsets of digits. Each such subset corresponds to $5! = 120$ different passwords, so $C(10,5) \times 5! = P(10,5)$. Dropping the $5!$ undercounts badly.

**Common trap — phone numbers vs PINs:** Real telephone numbers often cannot start with $0$. The problem explicitly allows it, so every position has the same digit set $\{0,1,\ldots,9\}$.

---

## Other Variants of This Question

Examiners reuse the same counting idea with small changes. The method stays: unrestricted count, restricted count, then $(A-B)/A \times 100$.

### Variant A: Remaining percentage instead of reduction

**Question:** What percentage of 5-digit passwords have all distinct digits?

$$
\frac{30\,240}{100\,000} \times 100 = 30.24
$$

This is the complement of the original answer: $100 - 69.76 = 30.24$.

---

### Variant B: Leading zero forbidden

**Question:** Same as the original, but the first digit cannot be $0$ (like a 5-digit integer).

| Set | Count |
|-----|-------|
| All | $9 \times 10^4 = 90\,000$ |
| Unique digits | $9 \times 9 \times 8 \times 7 \times 6 = 27\,216$ |

First digit: $1$–$9$ ($9$ choices). Remaining four digits: chosen from the $9$ unused digits among $0$–$9$ (the unused set includes $0$ if $0$ was not used yet).

$$
p = \frac{90\,000 - 27\,216}{90\,000} \times 100 = 69.76
$$

The percentage matches the original because **both** counts are multiplied by $9/10$ (exactly $1/10$ of codes start with $0$ in each set). The ratio $B/A$ is unchanged. This does **not** hold for other constraints (e.g. “digit $0$ forbidden in every position”).

---

### Variant C: Different length $k$

**Question:** $k$-digit PIN, digits $0$–$9$, leading zeros allowed, all digits distinct. Reduction percentage?

$$
N_{\text{all}} = 10^k, \qquad N_{\text{unique}} = P(10, k) \quad (k \le 10)
$$

$$
p = \left(1 - \frac{P(10,k)}{10^k}\right) \times 100
$$

| $k$ | $P(10,k)$ | $p$ |
|-----|-----------|-----|
| $1$ | $10$ | $0$ |
| $2$ | $90$ | $10$ |
| $3$ | $720$ | $28$ |
| $4$ | $5040$ | $49.6$ |
| $5$ | $30240$ | $69.76$ |
| $6$ | $151200$ | $84.88$ |

For $k > 10$, $N_{\text{unique}} = 0$ and the reduction is $100\%$.

---

### Variant D: No digit $0$ at all

**Question:** Five digits from $1$–$9$ only, then require uniqueness.

$$
N_{\text{all}} = 9^5 = 59\,049, \qquad N_{\text{unique}} = P(9,5) = 9 \times 8 \times 7 \times 6 \times 5 = 15\,120
$$

$$
p = \frac{59\,049 - 15\,120}{59\,049} \times 100 \approx 74.394
$$

This is **not** a short finite decimal like $69.76$; the original problem is nicer because $10^5$ divides $69\,760 \times 100$ cleanly.

---

### Variant E: Exactly the codes that *do* repeat a digit

**Question:** How many 5-digit passwords (leading zeros allowed) are **not** all-distinct?

$$
10^5 - P(10,5) = 69\,760
$$

That is the numerator of the original percentage. Asking “how many” vs “by what percent” is a wording swap examiners use.

---

### Variant F: Combinations instead of permutations (wrong model)

If someone treats the password as an unordered set of five distinct digits:

$$
C(10,5) = 252
$$

That would be the number of **sets of digits**, not the number of **codes**. Each set $\{d_1,\ldots,d_5\}$ yields $5!$ ordered passwords. Always multiply by $k!$ when going from combinations to ordered codes of length $k$ with distinct symbols.

---

### Variant G: At least two identical digits vs “not all distinct”

“All digits different” is the complement of “at least one digit repeats.” These are the same restriction for the original problem. A stricter condition such as “exactly one pair and three unique other digits” needs inclusion of partitions of $5$ (e.g. pattern $2+1+1+1$) and is a different question.

---

## Quick Reference: Pattern Recognition

| What you see | What to count | Formula |
|--------------|---------------|---------|
| $k$-digit code, repeats OK, leading $0$ OK | all codes | $10^k$ |
| same, all digits distinct | injections $\{1..k\} \to \{0..9\}$ | $P(10,k)$ |
| “на сколько процентов сокращается” | relative drop | $(A-B)/A \times 100$ |
| “какой процент составляют” unique codes | remaining share | $B/A \times 100$ |
| first digit cannot be $0$ | trim first position | $9 \times 10^{k-1}$ vs $9 \times P(9,k-1)$ |
| unordered selection of $k$ digits | combinations | $C(10,k)$ — **not** a PIN count |

**Mnemonic:** passwords are **sequences** $\Rightarrow$ powers and $P(n,k)$. Percentage **reduction** is always relative to the **old** total $A$.

---

## Summary

| Step | Action |
|------|--------|
| 1 | Unrestricted: $N_{\text{all}} = 10^5 = 100\,000$ |
| 2 | Distinct digits: $N_{\text{unique}} = 10 \times 9 \times 8 \times 7 \times 6 = 30\,240$ |
| 3 | Decrease: $100\,000 - 30\,240 = 69\,760$ |
| 4 | Percentage of original: $69\,760 / 100\,000 \times 100 = 69.76$ |

**Final answer: 69.76** (Russian form **69,76**)
