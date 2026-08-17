# Q34: Distance from a Point to a Plane

## Problem Statement

**Russian:** Найти расстояние от точки $(1, 2, 3)$ до плоскости, заданной уравнением $x - 2y + 2z = 6$.

**English:** Find the distance from the point $(1, 2, 3)$ to the plane given by the equation $x - 2y + 2z = 6$.

**Instructions:** Write the answer as a number. If the result is a decimal, round to one decimal place.

**Given:**
- Point: $P = (1, 2, 3)$
- Plane: $x - 2y + 2z = 6$

---

## Answer

**1**

The distance from point $(1, 2, 3)$ to the plane is exactly **1** unit.

---

## Theory: Distance from a Point to a Plane in 3D

### What the problem is asking

In 3D analytic geometry, a **plane** is a flat infinite surface. The **distance from a point to a plane** is the length of the shortest perpendicular segment from that point to the plane.

This is always measured along a line that is **orthogonal (perpendicular)** to the plane.

### Standard form of a plane

A plane is usually written as:

$$ax + by + cz + d = 0$$

where $(a, b, c)$ is a **normal vector** to the plane (perpendicular to the surface).

Our equation $x - 2y + 2z = 6$ must first be rewritten in standard form:

$$x - 2y + 2z - 6 = 0$$

So:
- $a = 1$
- $b = -2$
- $c = 2$
- $d = -6$

The normal vector is $\vec{n} = (1, -2, 2)$.

### Distance formula

For a point $P(x_0, y_0, z_0)$ and a plane $ax + by + cz + d = 0$, the distance is:

$$D = \frac{|ax_0 + by_0 + cz_0 + d|}{\sqrt{a^2 + b^2 + c^2}}$$

**Why this formula works (intuition):**

1. The expression $ax_0 + by_0 + cz_0 + d$ tells you how far the point is from the plane along the normal direction (signed distance).
2. The absolute value $|...|$ makes it a non-negative length.
3. Dividing by $\sqrt{a^2 + b^2 + c^2}$ (the length of the normal vector) normalizes the result so you get the true geometric distance.

### Related concepts

| Concept | Meaning |
|--------|---------|
| **Normal vector** | A vector perpendicular to the plane; for $ax+by+cz+d=0$, it is $(a,b,c)$ |
| **Signed distance** | Positive on one side of the plane, negative on the other |
| **Perpendicular distance** | The shortest distance; always $\geq 0$ |
| **Point-plane relation** | If numerator is 0, the point lies on the plane |

### Common mistakes

1. **Forgetting to move the constant to the left side** — the plane must be in the form $ax + by + cz + d = 0$ before applying the formula. Here, $d = -6$, not $+6$.
2. **Dropping the absolute value** — distance is always non-negative.
3. **Forgetting to divide by the normal's length** — the numerator alone is not the distance unless the normal is already a unit vector.
4. **Sign errors with negative coefficients** — $b = -2$, so $by_0 = (-2)(2) = -4$, not $+4$.

---

## How to Solve (Step by Step)

### Step 1: Rewrite the plane in standard form

$$x - 2y + 2z = 6 \quad \Rightarrow \quad x - 2y + 2z - 6 = 0$$

Identify coefficients: $a = 1$, $b = -2$, $c = 2$, $d = -6$.

### Step 2: Substitute the point coordinates

Point $P = (1, 2, 3)$, so $x_0 = 1$, $y_0 = 2$, $z_0 = 3$.

Compute the numerator:

$$|ax_0 + by_0 + cz_0 + d| = |1 \cdot 1 + (-2) \cdot 2 + 2 \cdot 3 + (-6)|$$

$$= |1 - 4 + 6 - 6| = |-3| = 3$$

### Step 3: Compute the length of the normal vector

$$\sqrt{a^2 + b^2 + c^2} = \sqrt{1^2 + (-2)^2 + 2^2} = \sqrt{1 + 4 + 4} = \sqrt{9} = 3$$

### Step 4: Divide to get the distance

$$D = \frac{3}{3} = 1$$

### Step 5: Format the answer

The result is an integer, so the answer is **1** (no rounding needed).

---

## Verification (Optional)

You can also verify using vector projection:

1. Pick any point on the plane, e.g. $Q = (6, 0, 0)$ (set $y = 0, z = 0$ in $x - 2y + 2z = 6$).
2. Form vector $\vec{PQ} = Q - P = (5, -2, -3)$.
3. The normal vector is $\vec{n} = (1, -2, 2)$.
4. Project $\vec{PQ}$ onto $\vec{n}$:

$$\text{distance} = \frac{|\vec{PQ} \cdot \vec{n}|}{|\vec{n}|} = \frac{|5(1) + (-2)(-2) + (-3)(2)|}{3} = \frac{|5 + 4 - 6|}{3} = \frac{3}{3} = 1$$

Both methods give the same answer.

---

## Summary

| Step | Action |
|------|--------|
| 1 | Write plane as $ax + by + cz + d = 0$ |
| 2 | Plug point into $|ax_0 + by_0 + cz_0 + d|$ |
| 3 | Divide by $\sqrt{a^2 + b^2 + c^2}$ |
| 4 | Round to 1 decimal if needed |

**Final answer: 1**
