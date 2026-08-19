# Q45: Linear Regression Slope (Least Squares)

**Тема:** Математический анализ

## Problem Statement

**Russian:** По точкам $(1, 2)$, $(2, 3)$, $(3, 5)$ строят линейную регрессию $y = a + bx$ методом наименьших квадратов. Найдите коэффициент $b$.

Формат ответа. Если ответ десятичный, округлите до одного знака.

**English:** Using the points $(1, 2)$, $(2, 3)$, $(3, 5)$, a linear regression $y = a + bx$ is fitted by the method of least squares. Find the coefficient $b$.

Answer format: if the answer is a decimal, round it to one decimal place.

---

## Answer

**1.5**

The least-squares slope is **1.5**.

---

## Theory: Ordinary Least Squares

We fit a line $y = a + bx$ that minimizes the sum of squared residuals:

$$S(a, b) = \sum_{i=1}^{n} \bigl( y_i - a - b x_i \bigr)^2$$

Taking partial derivatives and setting them to zero gives the normal equations. The slope has the closed form

$$b = \frac{\sum_{i=1}^{n} (x_i - \bar{x})(y_i - \bar{y})}{\sum_{i=1}^{n} (x_i - \bar{x})^2}$$

or, equivalently, without centering:

$$b = \frac{n \sum x_i y_i - (\sum x_i)(\sum y_i)}{n \sum x_i^2 - (\sum x_i)^2}$$

Here $b$ is the **slope**: how much $y$ changes, on average, when $x$ increases by 1. The intercept is then $a = \bar{y} - b\,\bar{x}$ (not required in this problem).

---

## How to Solve (Step by Step)

### Step 1: List the data and the sample size

| $i$ | $x_i$ | $y_i$ |
|-----|-------|-------|
| 1   | 1     | 2     |
| 2   | 2     | 3     |
| 3   | 3     | 5     |

$$n = 3$$

### Step 2: Compute the means

$$\bar{x} = \frac{1+2+3}{3} = 2, \qquad \bar{y} = \frac{2+3+5}{3} = \frac{10}{3}$$

### Step 3: Compute deviations from the mean

| $x_i$ | $y_i$ | $x_i - \bar{x}$ | $y_i - \bar{y}$ | $(x_i-\bar{x})(y_i-\bar{y})$ | $(x_i-\bar{x})^2$ |
|-------|-------|-----------------|-----------------|--------------------------------|-------------------|
| 1     | 2     | $-1$            | $2 - 10/3 = -4/3$ | $4/3$                          | $1$               |
| 2     | 3     | $0$             | $3 - 10/3 = -1/3$ | $0$                            | $0$               |
| 3     | 5     | $1$             | $5 - 10/3 = 5/3$  | $5/3$                          | $1$               |

### Step 4: Plug into the slope formula

$$\sum (x_i - \bar{x})(y_i - \bar{y}) = \frac{4}{3} + 0 + \frac{5}{3} = 3$$

$$\sum (x_i - \bar{x})^2 = 1 + 0 + 1 = 2$$

$$b = \frac{3}{2} = 1.5$$

### Step 5: Round (already one decimal place)

$$1.5$$

---

## Check with the uncentered formula

$$\sum x_i = 6, \quad \sum y_i = 10, \quad \sum x_i y_i = 1\cdot 2 + 2\cdot 3 + 3\cdot 5 = 23, \quad \sum x_i^2 = 1+4+9 = 14$$

$$b = \frac{3\cdot 23 - 6\cdot 10}{3\cdot 14 - 6^2} = \frac{69 - 60}{42 - 36} = \frac{9}{6} = 1.5$$

Same result.

(The intercept, if needed, would be $a = \bar{y} - b\bar{x} = 10/3 - 1.5\cdot 2 = 1/3$, so the fitted line is $y = 1/3 + 1.5\, x$.)

---

## Common Mistakes

1. **Using two-point slope** $(5-2)/(3-1) = 1.5$ happens to match here, but that ignores the middle point and is not least squares in general.
2. **Swapping $x$ and $y$** — regression of $x$ on $y$ has a different slope.
3. **Forgetting to center**, or using $\sum x y / \sum x^2$ without subtracting means (that would be a line through the origin).
4. **Rounding $10/3$ too early** and then getting a messy decimal instead of the exact $3/2$.

---

## Summary

| Step | Action |
|------|--------|
| 1 | $\bar{x} = 2$, $\bar{y} = 10/3$ |
| 2 | Covariance numerator $= 3$, variance of $x$ $= 2$ |
| 3 | $b = 3/2 = 1.5$ |

**Final answer: 1.5**
