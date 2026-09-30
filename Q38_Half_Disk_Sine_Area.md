# Q38: Minimum Parameter for Half-Disk Area

## Problem Statement

**Russian:** Найдите минимальное значение параметра $a$, при котором площадь фигуры, определённой на плоскости множеством точек:

$$
\begin{cases}
(x - 3)^2 + y^2 \leq 9 \\
y \leq \sin\dfrac{a \pi x}{327}
\end{cases}
$$

равна $4.5\pi$, если $a \in \mathbb{N}$; $a > 0$.

**English:** Find the minimum value of parameter $a$ (a positive natural number) for which the area of the region on the plane defined by the system above equals $4.5\pi$.

**Instructions:** Write the answer as a number. If the result is a decimal, round to one decimal place.

**Given:**
- Disk: $(x - 3)^2 + y^2 \leq 9$
- Upper boundary: $y = \sin\dfrac{a \pi x}{327}$
- Required area: $4.5\pi$
- Constraint: $a \in \mathbb{N}$, $a > 0$

---

## Answer

**109**

The smallest positive integer $a$ for which the region has area $4.5\pi$ is **109**.

---

## Theory: Area of a Planar Region Bounded by a Curve

### What the problem is asking

We need the area of the part of a **disk** (filled circle) that lies **on or below** a sine curve. The parameter $a$ controls the frequency of the sine wave. We must find the smallest positive integer $a$ that makes this area exactly $4.5\pi$.

### Key geometric objects

| Object | Equation / property |
|--------|---------------------|
| **Disk** | $(x - 3)^2 + y^2 \leq 9$ — center $(3, 0)$, radius $R = 3$ |
| **Total disk area** | $\pi R^2 = 9\pi$ |
| **Target area** | $4.5\pi = \dfrac{9\pi}{2}$ — exactly **half** the disk |
| **Boundary curve** | $y = \sin\dfrac{a \pi x}{327}$ |

### Core insight

Since $4.5\pi = \dfrac{1}{2} \cdot 9\pi$, the sine curve must **split the disk into two equal areas**. The natural way this happens is when the curve is **symmetric about the center of the disk** $(3, 0)$.

---

## How to Solve (Step by Step)

### Step 1: Analyze the disk

The inequality $(x - 3)^2 + y^2 \leq 9$ describes a disk with:
- Center: $C = (3, 0)$
- Radius: $R = 3$
- Area: $9\pi$

The target area $4.5\pi$ is exactly half of $9\pi$.

### Step 2: Shift coordinates to the center

Let $u = x - 3$. Then the disk becomes:

$$u^2 + y^2 \leq 9$$

The sine boundary becomes:

$$y \leq \sin\!\left(\frac{a \pi (u + 3)}{327}\right) = \sin\!\left(\frac{a \pi u}{327} + \frac{3a \pi}{327}\right)$$

Define:

$$f(u) = \sin\!\left(\frac{a \pi u}{327} + \frac{3a \pi}{327}\right)$$

The region in $(u, y)$ coordinates is: $u^2 + y^2 \leq 9$ and $y \leq f(u)$.

### Step 3: Require odd symmetry about the origin

For the curve to divide the disk into two equal halves, we need $f$ to be an **odd function**:

$$f(-u) = -f(u)$$

Check:

$$f(-u) = \sin\!\left(-\frac{a \pi u}{327} + \frac{3a \pi}{327}\right)$$

Using $\sin(\alpha + k\pi) = (-1)^k \sin(\alpha)$, if $\dfrac{3a \pi}{327} = k\pi$ for some integer $k$, then:

$$f(u) = (-1)^k \sin\!\left(\frac{a \pi u}{327}\right), \qquad f(-u) = (-1)^k \sin\!\left(-\frac{a \pi u}{327}\right) = -f(u)$$

So $f$ is odd when:

$$\frac{3a}{327} = k \quad \Rightarrow \quad \frac{a}{109} = k \quad \Rightarrow \quad a = 109k, \quad k \in \mathbb{Z}$$

### Step 4: Find the minimum positive $a$

Since $a > 0$ and $a \in \mathbb{N}$, the smallest value is $k = 1$:

$$a = 109$$

### Step 5: Verify that $a = 109$ gives half the area

When $f$ is odd and the disk $u^2 + y^2 \leq 9$ is symmetric about the origin, the map $(u, y) \mapsto (-u, -y)$ is an area-preserving bijection between:

- Region $A$: points with $y \leq f(u)$
- Region $B$: points with $y \geq f(u)$

Therefore $\text{Area}(A) = \text{Area}(B) = \dfrac{9\pi}{2} = 4.5\pi$.

**Check that the curve passes through the center:** at $x = 3$ (i.e. $u = 0$):

$$\sin\!\left(\frac{109 \pi \cdot 3}{327}\right) = \sin(\pi) = 0 \quad \checkmark$$

### Step 6: Why no smaller $a$ works

For $a < 109$ with $a \in \mathbb{N}$, the value $\dfrac{3a}{327}$ is not an integer, so $\sin\!\left(\dfrac{3a \pi}{327}\right) \neq 0$. The sine curve does not pass through the center $(3, 0)$ and lacks the odd symmetry needed to guarantee an exact half-area split. No smaller positive integer $a$ can produce the required area of exactly $4.5\pi$.

---

## Verification

| Quantity | Value |
|----------|-------|
| Disk center | $(3, 0)$ |
| Disk radius | $3$ |
| Disk area | $9\pi$ |
| Target area | $4.5\pi = \dfrac{9\pi}{2}$ |
| Symmetry condition | $\dfrac{3a}{327} \in \mathbb{Z}$ → $a = 109k$ |
| Minimum $a > 0$ | $109$ |
| Sine at center ($x = 3$) | $\sin(\pi) = 0$ |

---

## Summary

| Step | Action |
|------|--------|
| 1 | Compute total disk area: $9\pi$ |
| 2 | Note target $4.5\pi = \dfrac{9\pi}{2}$ — half the disk |
| 3 | Shift origin to disk center: $u = x - 3$ |
| 4 | Require sine curve to be odd in $u$: $a = 109k$ |
| 5 | Take minimum positive integer: $a = 109$ |

**Final answer: 109**

---

## Visual Explanation

Open the interactive graph in a browser:

**[Q38_Half_Disk_Sine_Area.html](Q38_Half_Disk_Sine_Area.html)**

The page shows:
- The disk $(x-3)^2 + y^2 \leq 9$ (white circle, center at $(3,0)$)
- The sine curve $y = \sin\!\left(\dfrac{a\pi x}{327}\right)$ (orange)
- The target region $y \leq \sin(\ldots)$ inside the disk (blue shading)
- A slider for $a$, with quick buttons for $a = 109$ (answer), $108$, $54$, and $218$
- Live estimates of area, fraction of disk, and whether the symmetry condition $3a/327 \in \mathbb{Z}$ holds

Try $a = 109$ vs $a = 108$: only when the curve passes through the center with odd symmetry does the shaded area equal exactly $4.5\pi$.
