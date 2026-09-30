# Q39: Inverse of a 2×2 Matrix

## Problem Statement

**Russian:** Если матрица $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ обратима, то её обратная матрица равна:

**English:** If the matrix $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ is invertible, then its inverse is equal to:

**Given matrix:**

$$
A = \begin{pmatrix} a & -b \\ b & a \end{pmatrix}
$$

**Answer choices:**

1. $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$
2. $\dfrac{1}{a^2 + b^2}\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$
3. $\dfrac{1}{a^2 + b^2}\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$
4. $\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$
5. $\dfrac{1}{a^2 - b^2}\begin{pmatrix} -b & a \\ a & b \end{pmatrix}$

---

## Answer

**Option 3**

$$
A^{-1} = \frac{1}{a^2 + b^2}
\begin{pmatrix}
a & b \\
-b & a
\end{pmatrix}
$$

The matrix is invertible when $a^2 + b^2 \neq 0$, i.e. when at least one of $a$, $b$ is nonzero.

---

## Theory: Inverse of a 2×2 Matrix

### What the problem is asking

A square matrix $A$ is **invertible** (non-singular) when there exists a matrix $A^{-1}$ such that:

$$
A \cdot A^{-1} = A^{-1} \cdot A = I
$$

where $I$ is the identity matrix. The question asks for a closed-form expression for $A^{-1}$ in terms of parameters $a$ and $b$.

### General formula for a 2×2 inverse

For any invertible matrix

$$
M = \begin{pmatrix} p & q \\ r & s \end{pmatrix}
$$

the inverse is:

$$
M^{-1} = \frac{1}{\det(M)}
\begin{pmatrix} s & -q \\ -r & p \end{pmatrix}
$$

where:

$$
\det(M) = ps - qr
$$

The matrix $\begin{pmatrix} s & -q \\ -r & p \end{pmatrix}$ is called the **adjugate** of $M$. It is **not** the same as $M$ itself — the off-diagonal entries change sign and the main diagonal entries swap positions (for $2 \times 2$).

### Key concepts

| Concept | Definition / role |
|--------|-------------------|
| **Determinant** | $\det(M) = ps - qr$; must be $\neq 0$ for invertibility |
| **Adjugate** | Built from cofactors; for $2 \times 2$: swap diagonal, negate off-diagonal |
| **Inverse formula** | $M^{-1} = \dfrac{1}{\det(M)} \operatorname{adj}(M)$ |
| **Singular matrix** | $\det(M) = 0$ → no inverse exists |

---

## How to Solve (Step by Step)

### Step 1: Compute the determinant

For $A = \begin{pmatrix} a & -b \\ b & a \end{pmatrix}$:

$$
\det(A) = a \cdot a - (-b) \cdot b = a^2 + b^2
$$

**Invertibility condition:** $a^2 + b^2 \neq 0$. This fails only when $a = b = 0$.

### Step 2: Build the adjugate matrix

Apply the rule “swap diagonal, negate off-diagonal” to $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$:

$$
\operatorname{adj}(A) =
\begin{pmatrix} a & -(-b) \\ -(b) & a \end{pmatrix}
=
\begin{pmatrix} a & b \\ -b & a \end{pmatrix}
$$

### Step 3: Apply the inverse formula

$$
A^{-1} = \frac{1}{a^2 + b^2}
\begin{pmatrix} a & b \\ -b & a \end{pmatrix}
$$

This matches **Option 3**.

### Step 4: Verify by multiplication (optional)

$$
\begin{pmatrix} a & -b \\ b & a \end{pmatrix}
\begin{pmatrix} a & b \\ -b & a \end{pmatrix}
=
\begin{pmatrix} a^2+b^2 & 0 \\ 0 & a^2+b^2 \end{pmatrix}
= (a^2+b^2) I
$$

Dividing both sides by $a^2+b^2$ confirms $A \cdot A^{-1} = I$.

---

## Why the Other Options Are Wrong

| Option | Expression | Why it fails |
|--------|------------|--------------|
| **1** | $A$ itself | Would require $A^2 = I$ for all $a,b$ — false in general |
| **2** | $\dfrac{1}{a^2+b^2} A$ | Correct scalar, but **wrong adjugate** — off-diagonal signs are not flipped |
| **4** | $\operatorname{adj}(A)$ without $\dfrac{1}{\det}$ | Only valid when $\det(A) = 1$, i.e. $a^2+b^2=1$ |
| **5** | Uses $a^2 - b^2$ | Wrong determinant; confuses $a^2+b^2$ with $a^2-b^2$ |

**Common trap in Option 2:** Students correctly compute $\det = a^2+b^2$ but forget that the inverse uses the **adjugate**, not the original matrix. Scaling $A$ by $\dfrac{1}{a^2+b^2}$ is $A^{-1}$ only when $b = 0$ (then Options 2 and 3 coincide). If instead $a^2+b^2 = 1$, then $A$ is orthogonal and $A^{-1} = A^T$, which is **Option 4**, not Option 2.

**Common trap in Option 5:** The denominator $a^2 - b^2$ appears in determinants of matrices like $\begin{pmatrix} a & b \\ b & a \end{pmatrix}$, not $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$.

---

## Connection to Complex Numbers

The matrix $A = \begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ represents multiplication by the complex number $z = a + bi$ when we identify $\mathbb{R}^2$ with $\mathbb{C}$ via $(x, y) \leftrightarrow x + iy$.

| Complex operation | Matrix form |
|-------------------|-------------|
| $z = a + bi$ | $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ |
| $|z|^2 = a^2 + b^2$ | $\det(A)$ |
| $\bar{z} = a - bi$ | $\begin{pmatrix} a & b \\ -b & a \end{pmatrix} = \operatorname{adj}(A)$ |
| $\dfrac{1}{z} = \dfrac{\bar{z}}{|z|^2}$ | $A^{-1} = \dfrac{1}{a^2+b^2}\operatorname{adj}(A)$ |

This is why the inverse has the “conjugate-like” pattern: swap/flip signs on the off-diagonal, divide by $|z|^2$.

---

## Other Variants of This Question

Examiners often reuse the same idea with small changes. Here are the main variants and how the answer changes.

### Variant A: General 2×2 matrix

**Question:** Find the inverse of $\begin{pmatrix} a & b \\ c & d \end{pmatrix}$.

**Answer:**

$$
\frac{1}{ad - bc}
\begin{pmatrix} d & -b \\ -c & a \end{pmatrix}
$$

This is the formula you should memorize. Every special case below is a substitution into this template.

---

### Variant B: Sign flip on the upper-right entry

**Question:** Inverse of $\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$.

| Quantity | Value |
|----------|-------|
| Determinant | $a^2 + b^2$ |
| Adjugate | $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ |
| Inverse | $\dfrac{1}{a^2+b^2}\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ |

Notice: this is **Option 2** in the original question — the inverse of Variant B looks like Option 2, while the inverse of the original matrix looks like Option 3. Swapping the sign of one off-diagonal entry swaps the adjugate pattern.

---

### Variant C: Both off-diagonals equal ($b$ on both)

**Question:** Inverse of $\begin{pmatrix} a & b \\ b & a \end{pmatrix}$.

| Quantity | Value |
|----------|-------|
| Determinant | $a^2 - b^2$ |
| Adjugate | $\begin{pmatrix} a & -b \\ -b & a \end{pmatrix}$ |
| Inverse | $\dfrac{1}{a^2-b^2}\begin{pmatrix} a & -b \\ -b & a \end{pmatrix}$ |

This is where the **$a^2 - b^2$** denominator in Option 5 comes from — but Option 5 also has the wrong adjugate entries, so it is still incorrect for our original matrix.

---

### Variant D: Diagonal matrix ($b = 0$)

**Question:** Inverse of $\begin{pmatrix} a & 0 \\ 0 & d \end{pmatrix}$.

$$
\begin{pmatrix} a & 0 \\ 0 & d \end{pmatrix}^{-1}
=
\begin{pmatrix} \dfrac{1}{a} & 0 \\ 0 & \dfrac{1}{d} \end{pmatrix},
\quad a \neq 0,\; d \neq 0
$$

For the original matrix with $b = 0$: $\begin{pmatrix} a & 0 \\ 0 & a \end{pmatrix}^{-1} = \begin{pmatrix} \dfrac{1}{a} & 0 \\ 0 & \dfrac{1}{a} \end{pmatrix}$.

---

### Variant E: Rotation matrix (unit determinant)

**Question:** When is $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ a rotation matrix?

When $a^2 + b^2 = 1$, the matrix is a rotation (hence orthogonal):

$$
A^{-1} = A^T = \begin{pmatrix} a & b \\ -b & a \end{pmatrix}
$$

This matches **Option 4** — but only in the special case $|z| = 1$. For general $a, b$, you must divide by $a^2 + b^2$.

---

### Variant F: Numeric substitution

**Question:** Find the inverse of $\begin{pmatrix} 3 & -4 \\ 4 & 3 \end{pmatrix}$.

$$
\det = 9 + 16 = 25, \qquad
A^{-1} = \frac{1}{25}\begin{pmatrix} 3 & 4 \\ -4 & 3 \end{pmatrix}
$$

Always: compute determinant → build adjugate → divide.

---

### Variant G: “Is the matrix invertible?”

**Question:** For which $a, b$ is $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ singular?

$$
\det(A) = a^2 + b^2 = 0 \iff a = b = 0
$$

Over the reals, the matrix is invertible for **all** $(a, b) \neq (0, 0)$. A general $2 \times 2$ matrix can be singular for many parameter values; this special form is singular only at the origin.

---

## Quick Reference: Pattern Recognition

| Matrix pattern | Determinant | Inverse structure |
|----------------|-------------|-------------------|
| $\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ | $a^2 + b^2$ | $\dfrac{1}{a^2+b^2}\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$ |
| $\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$ | $a^2 + b^2$ | $\dfrac{1}{a^2+b^2}\begin{pmatrix} a & -b \\ b & a \end{pmatrix}$ |
| $\begin{pmatrix} a & b \\ b & a \end{pmatrix}$ | $a^2 - b^2$ | $\dfrac{1}{a^2-b^2}\begin{pmatrix} a & -b \\ -b & a \end{pmatrix}$ |
| $\begin{pmatrix} a & b \\ c & d \end{pmatrix}$ | $ad - bc$ | $\dfrac{1}{ad-bc}\begin{pmatrix} d & -b \\ -c & a \end{pmatrix}$ |

**Mnemonic for $2 \times 2$:** “Determinant on the bottom, adjugate on top — swap main diagonal, negate off-diagonal.”

---

## Summary

| Step | Action |
|------|--------|
| 1 | Compute $\det(A) = a^2 + b^2$ |
| 2 | Check invertibility: $a^2 + b^2 \neq 0$ |
| 3 | Form adjugate: $\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$ |
| 4 | Divide: $A^{-1} = \dfrac{1}{a^2+b^2}\operatorname{adj}(A)$ |
| 5 | Match to **Option 3** |

**Final answer: Option 3** — $\dfrac{1}{a^2 + b^2}\begin{pmatrix} a & b \\ -b & a \end{pmatrix}$
