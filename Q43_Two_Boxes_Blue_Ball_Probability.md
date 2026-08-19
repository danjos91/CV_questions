# Q43: Two Boxes — Maximize Probability of a Blue Ball

**Тема:** Теория вероятностей

## Problem Statement

**Russian:** У Маши 102 синих и 100 красных шариков. Нужно разложить их по двум коробкам. Затем случайным образом выбирается коробка и из неё достаётся один шарик. Как нужно разложить шарики, чтобы вероятность вытащить синий была максимальной? Чему равна эта вероятность?

Ответ запишите, округлив до сотых. Образец ответа: 0.51.

**English:** Masha has 102 blue balls and 100 red balls. They must be distributed into two boxes. Then a box is chosen at random, and one ball is drawn from it. How should the balls be distributed so that the probability of drawing a blue ball is maximized? What is that probability?

Write the answer rounded to the nearest hundredth. Sample answer: `0.51`.

**Assumptions:**
- Both boxes are non-empty (otherwise a ball cannot be drawn).
- Every ball is placed in one of the two boxes.
- The box is chosen uniformly at random (probability $1/2$ each).
- Given the box, the ball is chosen uniformly at random from the balls in that box.

---

## Answer

**0.75**

The maximum probability is **0.75**.

Optimal arrangement:
- **Box 1:** 1 blue ball (and nothing else).
- **Box 2:** the remaining 101 blue balls and all 100 red balls.

---

## Theory: Law of Total Probability

A box is chosen first, then a ball from that box. If $B$ is the event “the drawn ball is blue”:

$$P(B) = \frac12 \cdot P(B \mid \text{box 1}) + \frac12 \cdot P(B \mid \text{box 2}) = \frac12 \left( \frac{b_1}{n_1} + \frac{b_2}{n_2} \right)$$

where $b_i$ is the number of blue balls in box $i$ and $n_i$ is the total number of balls in box $i$.

Constraints:

$$b_1 + b_2 = 102, \qquad n_1 + n_2 = 202, \qquad n_1, n_2 \geq 1, \qquad 0 \leq b_i \leq n_i$$

The sample answer `0.51` is the *naive* value (mix the colors evenly). The real maximum is much larger, because the two boxes are not weighted by how many balls they contain: an almost-sure blue box counts as much as a large mixed box.

---

## How to Solve (Step by Step)

### Step 1: See why the naive split is not optimal

Split as evenly as possible, e.g. 51 blue + 50 red in each box:

$$P(B) = \frac{51}{101} \approx 0.505 \;\rightarrow\; 0.51$$

Put all 102 blue balls in one box and all 100 red balls in the other:

$$P(B) = \frac12 \cdot 1 + \frac12 \cdot 0 = 0.50$$

Even worse. Mixing “fairly” cannot beat about $0.51$, because both boxes then have a blue fraction close to $102/202 \approx 0.505$.

### Step 2: Use an asymmetric arrangement

The box is chosen with probability $1/2$ *regardless of how many balls it holds*. So we can “spend” one box on a guaranteed blue draw, and still keep the other box slightly above $1/2$ blue (there are 2 extra blue balls).

Place a **single blue ball** in box 1. Put everything else in box 2:

| Box | Blue | Red | Total | $P(\text{blue} \mid \text{box})$ |
|-----|------|-----|-------|----------------------------------|
| 1   | 1    | 0   | 1     | $1/1 = 1$                        |
| 2   | 101  | 100 | 201   | $101/201$                        |

### Step 3: Compute the probability

$$P(B) = \frac12 \cdot 1 + \frac12 \cdot \frac{101}{201} = \frac12 + \frac{101}{402} = \frac{201 + 101}{402} = \frac{302}{402} = \frac{151}{201}$$

$$ \frac{151}{201} \approx 0.751243 $$

### Step 4: Round to two decimal places

$$0.751243 \;\rightarrow\; 0.75$$

---

## Why This Arrangement Is Optimal

Let box 1 contain $n$ balls, of which $b$ are blue. Then

$$P(B) = \frac12 \left( \frac{b}{n} + \frac{102 - b}{202 - n} \right) = \frac12 \left[ b\left(\frac{1}{n} - \frac{1}{202-n}\right) + \frac{102}{202-n} \right]$$

with $1 \leq n \leq 201$.

**If $n < 101$** (box 1 is the smaller box), the coefficient of $b$ is positive, so we should put as many blue balls as possible into box 1: $b = n$ (box 1 contains only blue balls). Then

$$P(B) = \frac12 \left( 1 + \frac{102-n}{202-n} \right) = 1 - \frac{50}{202-n}$$

This grows as $n$ shrinks, so the best choice is $n = 1$:

$$P(B) = 1 - \frac{50}{201} = \frac{151}{201} \approx 0.75$$

**If $n = 101$** (equal sizes), the coefficient of $b$ is zero and $P(B) = 102/202 \approx 0.505$ no matter how we color the boxes.

**If $n > 101$**, swap the labels of the two boxes: this is the same as the first case.

Therefore the global maximum is $151/201 \approx 0.75$, achieved by putting one blue ball alone in one box and all remaining balls in the other.

Putting $k > 1$ blue balls alone in one box is worse (or equal only after rounding):

$$P_k = \frac12 \left( 1 + \frac{102-k}{202-k} \right)$$

which decreases as $k$ increases. For $k = 2$ one gets exactly $3/4 = 0.75$; for $k = 1$ the exact value is slightly larger ($151/201 \approx 0.7512$), but both round to $0.75$. For $k \geq 3$ the probability drops below $0.75$.

---

## Common Mistakes

1. **Even split** — both boxes $\approx 51/101$, answer $0.51$. That is the sample format, not the maximum.
2. **All blue vs all red** — gives $0.50$.
3. **Weighting boxes by size** — the box is chosen first, uniformly, so a 1-ball box is as likely as a 201-ball box.
4. **Empty box** — not allowed; you cannot draw a ball from it.
5. **Leaving balls unused** — the problem asks to distribute all of them.

---

## Summary

| Step | Action |
|------|--------|
| 1 | $P(B) = \frac12(p_1 + p_2)$; boxes are equally likely |
| 2 | Put 1 blue ball in box 1; 101 blue + 100 red in box 2 |
| 3 | $P(B) = \frac12 \cdot 1 + \frac12 \cdot \frac{101}{201} = \frac{151}{201} \approx 0.7512$ |
| 4 | Round to two decimals |

**Final answer: 0.75**
