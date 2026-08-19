# Q49: Partial Derivative \(f_x(3, 4)\)

**Тема:** Математический анализ

## Problem Statement

**Russian:** Для функции \(f(x; y) = \ln ( \sqrt{x^2 + y^2}) \) найди значение частной производной \(f_x(3; 4)\).

Если в ответе получилась десятичная дробь, округли ее до второго знака после точки.
Образец ответа: `50` (целое число) или `50.12` (округленная десятичная дробь).

**English:** For the function \(f(x, y) = \ln(\sqrt{x^2 + y^2})\), find the value of the partial derivative \(f_x(3, 4)\).

If the answer is a decimal, round it to two decimal places.
Sample answer: `50` (integer) or `50.12` (rounded decimal).

---

## Answer

**0.12**

---

## Theory: Partial Derivatives of a Composition

A **partial derivative** \(f_x\) is the ordinary derivative with respect to \(x\), treating \(y\) as a constant.

Useful log identities (for \(u > 0\)):

$$\ln\sqrt{u} = \ln\bigl(u^{1/2}\bigr) = \frac12 \ln u$$

and the chain rule:

$$\frac{\partial}{\partial x}\ln g(x,y) = \frac{1}{g(x,y)} \cdot g_x(x,y)$$

Here \(f\) is (up to a constant) the log of the distance from the origin: \(f(x,y) = \ln r\) with \(r = \sqrt{x^2+y^2}\).

---

## How to Solve (Step by Step)

### Step 1: Simplify the function

$$f(x,y) = \ln\sqrt{x^2 + y^2} = \frac12 \ln(x^2 + y^2)$$

(valid at \((3,4)\) because \(x^2+y^2 = 25 > 0\)).

### Step 2: Differentiate with respect to \(x\) (\(y\) fixed)

$$\frac{\partial}{\partial x}\ln(x^2 + y^2) = \frac{1}{x^2 + y^2} \cdot 2x = \frac{2x}{x^2 + y^2}$$

Therefore

$$f_x(x,y) = \frac12 \cdot \frac{2x}{x^2 + y^2} = \frac{x}{x^2 + y^2}$$

### Step 3: Plug in the point \((3, 4)\)

$$f_x(3, 4) = \frac{3}{3^2 + 4^2} = \frac{3}{9 + 16} = \frac{3}{25} = 0.12$$

### Step 4: Format the answer

\(0.12\) already has two decimal places.

---

## Check via the chain rule without simplifying first

Let \(u = \sqrt{x^2 + y^2}\). Then \(f = \ln u\) and

$$u_x = \frac{1}{2\sqrt{x^2+y^2}} \cdot 2x = \frac{x}{\sqrt{x^2+y^2}}$$

$$f_x = \frac{1}{u} \cdot u_x = \frac{1}{\sqrt{x^2+y^2}} \cdot \frac{x}{\sqrt{x^2+y^2}} = \frac{x}{x^2+y^2}$$

Same formula: \(f_x(3,4) = 3/25 = 0.12\).

---

## Common Mistakes

1. **Forgetting the inner derivative** — writing \(1/\sqrt{x^2+y^2}\) instead of \(x/(x^2+y^2)\).
2. **Evaluating \(\ln\sqrt{3^2+4^2} = \ln 5\)** — that is \(f(3,4)\), not \(f_x(3,4)\).
3. **Using \(f_y\)** — \(f_y = y/(x^2+y^2)\), so \(f_y(3,4) = 4/25 = 0.16\), which is the wrong partial.
4. **Leaving the answer as \(3/25\)** — the auto-grader wants a decimal: `0.12`.

---

## Summary

| Step | Action |
|------|--------|
| 1 | \(f = \frac12 \ln(x^2+y^2)\) |
| 2 | \(f_x = x/(x^2+y^2)\) |
| 3 | \(f_x(3,4) = 3/25 = 0.12\) |

**Final answer: 0.12**
