# Q39: Area of a Figure on a Grid

**Тема:** Базовая математическая грамотность

## Problem Statement

**Russian:** Посчитайте площадь фигуры на картинке. Длина одной стороны квадрата — 1 см.

**English:** Calculate the area of the shape in the picture. The side of one grid square is 1 cm.

**Instructions:** If the answer is a decimal, round it to two decimal places. Example: `50` (integer) or `50.12` (rounded decimal).

![Shaded polygon on a square grid](Q39_grid_figure.png)

**Given:**
- Each grid square has side 1 cm, so 1 square unit = 1 cm²
- The visible grid is **6 squares wide and 4 squares tall**
- Place the origin at the bottom-left lattice point; then the vertices (clockwise from the top-left) are:

$$(2,4),\ (4,4),\ (6,3),\ (4,0),\ (5,3),\ (4,1),\ (4,3),\ (3,0),\ (2,3),\ (2,1),\ (1,3),\ (2,0),\ (0,3)$$

The figure is symmetric about the vertical line $x = 3$.

---

## Answer

**12**

The area of the shaded figure is **12** cm².

---

## Theory: Area of a Polygon on a Grid

### What the problem is asking

Find the area of a shaded polygon drawn on square grid paper. Because each cell is $1 \times 1$, the area in square units is the answer in cm².

### Useful methods

| Method | When to use |
|--------|-------------|
| **Decomposition** | Split into a trapezoid and triangles whose bases/heights sit on grid lines |
| **Shoelace formula** | You have vertices in boundary order |
| **Pick's theorem** | $A = I + B/2 - 1$ with interior and boundary lattice points |

### Shoelace formula

For a simple polygon with vertices $(x_1,y_1),\ldots,(x_n,y_n)$ in order, closing back to the first vertex:

$$A = \frac{1}{2} \left| \sum_{i=1}^{n} (x_i y_{i+1} - x_{i+1} y_i) \right|, \quad (x_{n+1},y_{n+1}) = (x_1,y_1)$$

### Common mistakes

1. **Miscounting the grid** — the paper is 6 wide × 4 tall. A 10-wide or 8-wide reading inflates the area (that is how the wrong answers 20 and 18 appear).
2. **Treating the bottom as three full-height triangles** — the three tips do not occupy the whole strip under $y = 3$; there are extra inner triangles that stop at $y = 1$, and unshaded notches between the teeth.
3. **Counting only full squares** — many cells are cut by diagonals.

---

## How to Solve (Step by Step)

Split the figure along the line $y = 3$ (the widest horizontal cross-section).

### Step 1: Top trapezoid ($y = 3$ to $y = 4$)

Vertices: $(0,3),\ (2,4),\ (4,4),\ (6,3)$.

Parallel sides (both horizontal):
- top: length $4 - 2 = 2$
- bottom: length $6 - 0 = 6$
- height: $1$

$$A_{\text{top}} = \frac{2 + 6}{2} \cdot 1 = 4$$

### Step 2: Pieces below $y = 3$ (left to right)

Five polygons sit under the line $y = 3$. Left and right pairs match by symmetry about $x = 3$.

**Left outer triangle** $(0,3),\ (2,0),\ (1,3)$:

$$A = \frac{1}{2} \bigl| 0(0-3) + 2(3-3) + 1(3-0) \bigr| = 1.5$$

**Left inner triangle** $(1,3),\ (2,1),\ (2,3)$  
(vertical side of length 2 at $x = 2$):

$$A = \frac{1}{2} \cdot 1 \cdot 2 = 1$$

**Central triangle** $(2,3),\ (3,0),\ (4,3)$  
(base $2$, height $3$):

$$A = \frac{2 \cdot 3}{2} = 3$$

**Right inner triangle** $(4,3),\ (4,1),\ (5,3)$ — mirror of the left inner triangle: area $1$.

**Right outer triangle** $(5,3),\ (4,0),\ (6,3)$ — mirror of the left outer triangle: area $1.5$.

$$A_{\text{bottom}} = 1.5 + 1 + 3 + 1 + 1.5 = 8$$

### Step 3: Add the parts

$$A = 4 + 8 = 12$$

The result is an integer, so no rounding is needed.

---

## Verification: Shoelace Formula

Vertices in clockwise order, then back to the start:

$$
\begin{align*}
&(2,4),\ (4,4),\ (6,3),\ (4,0),\ (5,3),\ (4,1),\ (4,3),\\
&(3,0),\ (2,3),\ (2,1),\ (1,3),\ (2,0),\ (0,3),\ (2,4)
\end{align*}
$$

$$
\sum x_i y_{i+1} = 72, \qquad \sum y_i x_{i+1} = 96
$$

$$
A = \frac{1}{2} |72 - 96| = 12
$$

### Pick's theorem

Lattice points: $I = 5$ interior, $B = 16$ on the boundary.

$$A = I + \frac{B}{2} - 1 = 5 + 8 - 1 = 12$$

---

## Why 18 and 20 are wrong

Both wrong answers use the same polygon *shape* but the wrong grid size.

| Source | Grid assumed | Top trapezoid | Bottom | Total |
|--------|----------------|---------------|--------|-------|
| **This solution** | 6 × 4 (actual) | $(2+6)/2 = 4$ | $8$ | **12** |
| Q41 | 8-wide waist | $(4+8)/2 = 6$ | three height-3 triangles $4.5+3+4.5=12$ | **18** |
| earlier Q39 | 10-wide waist | $(4+10)/2 = 7$ | $3+2+3+2+3=13$ | **20** |

Q41 also fills the notches between the teeth (treating each outer spike as a $3 \times 3$ triangle of height 3). Those notches are unshaded, so that bottom count is too large even after the width error.

---

## Summary

| Part | Area |
|------|------|
| Top trapezoid | 4 |
| Two outer triangles | $1.5 + 1.5 = 3$ |
| Two inner triangles | $1 + 1 = 2$ |
| Central triangle | 3 |
| **Total** | **12** |

**Final answer: 12**
