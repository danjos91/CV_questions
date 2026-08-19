# Q38: Probability of the Next Click (Exponential Distribution)

**Тема:** Теория вероятностей

## Problem Statement

**Russian:** Время до следующего клика имеет экспоненциальное распределение. В среднем за минуту происходит 0.12 клика. С какой вероятностью следующий клик произойдет не позже чем через 8 минут?

**English:** The time until the next click has an exponential distribution. On average, 0.12 clicks occur per minute. What is the probability that the next click will occur no later than in 8 minutes?

**Instructions:** Round the answer to two decimal places.

**Given:**
- Distribution: exponential
- Rate: $\lambda = 0.12$ clicks per minute
- Time horizon: $t = 8$ minutes
- Find: $P(X \leq 8)$

---

## Answer

**0.62**

The probability that the next click happens within 8 minutes is **0.62**.

---

## Theory: Exponential Distribution

### What the problem is asking

Let $X$ be the waiting time (in minutes) until the next click. We need the probability that this wait is at most 8 minutes:

$$P(X \leq 8)$$

This is the **cumulative distribution function (CDF)** of $X$ evaluated at $t = 8$.

### Rate parameter $\lambda$

The problem says: *on average, 0.12 clicks occur per minute*.

That is the **intensity (rate)** of a Poisson process of clicks:

$$\lambda = 0.12 \quad \text{(clicks per minute)}$$

For an exponential waiting time, $\lambda$ is both:

| Interpretation | Meaning here |
|----------------|--------------|
| **Rate** | Average number of clicks per unit time: $0.12$ per minute |
| **Inverse of the mean wait** | Mean time until the next click is $1/\lambda = 1/0.12 \approx 8.33$ minutes |

So $X \sim \operatorname{Exp}(\lambda = 0.12)$.

### Density and CDF

The PDF of an exponential random variable is:

$$f(x) = \lambda e^{-\lambda x}, \quad x \geq 0$$

The CDF (probability of waiting at most $t$) is:

$$F(t) = P(X \leq t) = 1 - e^{-\lambda t}, \quad t \geq 0$$

**Why $1 - e^{-\lambda t}$ (intuition):**

1. In a Poisson process with rate $\lambda$, the number of events in $[0, t]$ is $\operatorname{Poisson}(\lambda t)$.
2. “No click in 8 minutes” means zero events: $P(N(t) = 0) = e^{-\lambda t}$.
3. “At least one click by time $t$” is the complement: $1 - e^{-\lambda t}$.
4. For a waiting time $X$, “at least one click by $t$” is exactly $X \leq t$.

### Related concepts

| Concept | Meaning |
|--------|---------|
| **Exponential distribution** | Continuous distribution of waiting time between events in a Poisson process |
| **Memorylessness** | $P(X > s + t \mid X > s) = P(X > t)$; the remaining wait does not depend on how long you already waited |
| **Poisson process** | Clicks occur independently at constant average rate $\lambda$ |
| **Survival function** | $P(X > t) = e^{-\lambda t}$ — probability the wait exceeds $t$ |

### Common mistakes

1. **Using $e^{-\lambda t}$ instead of $1 - e^{-\lambda t}$** — that is $P(X > 8)$, the probability the click is *later* than 8 minutes.
2. **Treating $0.12$ as the mean wait** — $0.12$ is the *rate*, not $E[X]$. The mean wait is $1/0.12 \approx 8.33$ minutes.
3. **Using $\lambda = 1/0.12$ in the CDF** — that would be $1 - e^{-8/0.12}$, which is almost 1 and is wrong.
4. **Rounding too early** — compute $1 - e^{-0.96}$ fully, then round to two decimals.

---

## How to Solve (Step by Step)

### Step 1: Identify the model and the parameter

$$X \sim \operatorname{Exp}(\lambda), \qquad \lambda = 0.12$$

### Step 2: Write the required probability

“Not later than 8 minutes” means $X \leq 8$:

$$P(X \leq 8) = 1 - e^{-\lambda \cdot 8} = 1 - e^{-0.12 \cdot 8}$$

### Step 3: Compute the exponent

$$0.12 \cdot 8 = 0.96$$

So

$$P(X \leq 8) = 1 - e^{-0.96}$$

### Step 4: Evaluate the exponential

$$e^{-0.96} \approx 0.382893$$

$$1 - 0.382893 = 0.617107$$

### Step 5: Round to two decimal places

$$0.617107 \rightarrow 0.62$$

---

## Verification (Optional)

**Complement:** probability the next click is *after* 8 minutes:

$$P(X > 8) = e^{-0.96} \approx 0.38$$

Then $1 - 0.38 = 0.62$, same answer after rounding.

**Sanity check:** the mean wait is $1/0.12 \approx 8.33$ minutes, which is close to 8. For an exponential, $P(X \leq E[X]) = 1 - e^{-1} \approx 0.63$. Our $t = 8$ is slightly below the mean, so a probability slightly below $0.63$ (namely $0.62$) is consistent.

---

## Summary

| Step | Action |
|------|--------|
| 1 | $\lambda = 0.12$ (rate, not mean wait) |
| 2 | $P(X \leq t) = 1 - e^{-\lambda t}$ |
| 3 | $1 - e^{-0.12 \cdot 8} = 1 - e^{-0.96} \approx 0.617$ |
| 4 | Round to two decimals |

**Final answer: 0.62**
