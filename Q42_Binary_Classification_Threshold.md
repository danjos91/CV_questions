# Q42: Binary Classification Threshold

**Тема:** Базовая математическая грамотность

## Problem Statement

**Russian:** Бинарный классификатор выдал score для семи объектов. При пороге $t$ модель предсказывает класс 1, если score $\geq t$, и класс 0 иначе. Рассмотрите только три порога: $t = 0.3, t = 0.5, t = 0.75$. Выберите порог, при котором число ошибок минимально. Если минимум достигается при нескольких порогах, выберите наименьший из них.

| Объект | Score | Истинный класс |
|--------|-------|----------------|
| 1      | 0.95  | 1              |
| 2      | 0.80  | 0              |
| 3      | 0.70  | 1              |
| 4      | 0.55  | 0              |
| 5      | 0.40  | 1              |
| 6      | 0.25  | 0              |
| 7      | 0.10  | 0              |

Формат ответа. Ответ запишите числом: 0.3, 0.5 или 0.75.

**English:** A binary classifier output a score for seven objects. At threshold $t$, the model predicts class 1 if $score \geq t$, and class 0 otherwise. Consider only three thresholds: $t = 0.3, t = 0.5, t = 0.75$. Choose the threshold that minimizes the number of errors. If the minimum is achieved at multiple thresholds, choose the smallest one.

| Object | Score | True Class |
|--------|-------|------------|
| 1      | 0.95  | 1          |
| 2      | 0.80  | 0          |
| 3      | 0.70  | 1          |
| 4      | 0.55  | 0          |
| 5      | 0.40  | 1          |
| 6      | 0.25  | 0          |
| 7      | 0.10  | 0          |

---

## Answer

**0.3**

---

## Explanation Step by Step

### How it works
For binary classification, the model predicts the positive class (1) if the model's output score is greater than or equal to a chosen threshold $t$. If the score is less than $t$, it predicts the negative class (0). An error occurs when the predicted class does not match the true class (False Positives and False Negatives).

We need to evaluate the predictions and count the errors for each of the given thresholds: $t = 0.3, 0.5,$ and $0.75$.

### Step 1: Evaluate threshold $t = 0.3$
Condition: Predict 1 if $Score \geq 0.3$, else predict 0.

| Object | Score | Pred ($t=0.3$) | True Class | Correct/Error |
|--------|-------|----------------|------------|---------------|
| 1      | 0.95  | **1**          | 1          | Correct       |
| 2      | 0.80  | **1**          | 0          | **Error**     |
| 3      | 0.70  | **1**          | 1          | Correct       |
| 4      | 0.55  | **1**          | 0          | **Error**     |
| 5      | 0.40  | **1**          | 1          | Correct       |
| 6      | 0.25  | **0**          | 0          | Correct       |
| 7      | 0.10  | **0**          | 0          | Correct       |

**Total errors for $t = 0.3$: 2 errors.**

---

### Step 2: Evaluate threshold $t = 0.5$
Condition: Predict 1 if $Score \geq 0.5$, else predict 0.

| Object | Score | Pred ($t=0.5$) | True Class | Correct/Error |
|--------|-------|----------------|------------|---------------|
| 1      | 0.95  | **1**          | 1          | Correct       |
| 2      | 0.80  | **1**          | 0          | **Error**     |
| 3      | 0.70  | **1**          | 1          | Correct       |
| 4      | 0.55  | **1**          | 0          | **Error**     |
| 5      | 0.40  | **0**          | 1          | **Error**     |
| 6      | 0.25  | **0**          | 0          | Correct       |
| 7      | 0.10  | **0**          | 0          | Correct       |

**Total errors for $t = 0.5$: 3 errors.**

---

### Step 3: Evaluate threshold $t = 0.75$
Condition: Predict 1 if $Score \geq 0.75$, else predict 0.

| Object | Score | Pred ($t=0.75$) | True Class | Correct/Error |
|--------|-------|-----------------|------------|---------------|
| 1      | 0.95  | **1**           | 1          | Correct       |
| 2      | 0.80  | **1**           | 0          | **Error**     |
| 3      | 0.70  | **0**           | 1          | **Error**     |
| 4      | 0.55  | **0**           | 0          | Correct       |
| 5      | 0.40  | **0**           | 1          | **Error**     |
| 6      | 0.25  | **0**           | 0          | Correct       |
| 7      | 0.10  | **0**           | 0          | Correct       |

**Total errors for $t = 0.75$: 3 errors.**

---

### Conclusion
- At $t = 0.3$, there are 2 errors.
- At $t = 0.5$, there are 3 errors.
- At $t = 0.75$, there are 3 errors.

The minimum number of errors is 2, which is achieved at the threshold $t = 0.3$. 

*(Note: The problem specifies that if the minimum is achieved at multiple thresholds, we should pick the smallest one, but since 2 is strictly less than 3, there's no tie to break).*

**Final Answer:** `0.3`