# Question 40: Largest Eigenvalue of a Matrix

**Тема:** Линейная алгебра

## Problem Statement

Given a matrix $B$:
$$
B = \begin{pmatrix} 
0 & 2 & 2 & 2 \\ 
2 & 0 & 2 & 2 \\ 
2 & 2 & 0 & 2 \\ 
2 & 2 & 2 & 0 
\end{pmatrix}
$$

and a basis of its eigenvectors:
$$
v_1 = \begin{pmatrix} 1 \\ 0 \\ 0 \\ -1 \end{pmatrix}, \quad 
v_2 = \begin{pmatrix} -1 \\ 0 \\ 1 \\ 0 \end{pmatrix}, \quad 
v_3 = \begin{pmatrix} 1 \\ -1 \\ 0 \\ 0 \end{pmatrix}, \quad 
v_4 = \begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix}
$$

Find the largest eigenvalue of the matrix $B$.

## Solution

To find the eigenvalue $\lambda$ associated with an eigenvector $v$, we use the definition of an eigenvector:
$$Bv = \lambda v$$

This means that when we multiply the matrix $B$ by its eigenvector $v$, the result is just the original vector $v$ multiplied by a scalar number $\lambda$. This number $\lambda$ is the eigenvalue.

Let's test the given eigenvectors to find their corresponding eigenvalues.

**Testing $v_4$:**
$$
B \cdot v_4 = \begin{pmatrix} 
0 & 2 & 2 & 2 \\ 
2 & 0 & 2 & 2 \\ 
2 & 2 & 0 & 2 \\ 
2 & 2 & 2 & 0 
\end{pmatrix} 
\begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix} 
= \begin{pmatrix} 
0\cdot1 + 2\cdot1 + 2\cdot1 + 2\cdot1 \\ 
2\cdot1 + 0\cdot1 + 2\cdot1 + 2\cdot1 \\ 
2\cdot1 + 2\cdot1 + 0\cdot1 + 2\cdot1 \\ 
2\cdot1 + 2\cdot1 + 2\cdot1 + 0\cdot1 
\end{pmatrix}
= \begin{pmatrix} 6 \\ 6 \\ 6 \\ 6 \end{pmatrix} 
= 6 \cdot \begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix} 
= 6 \cdot v_4
$$
So, the eigenvalue for $v_4$ is **$\lambda_4 = 6$**.

**Testing $v_1$ (for completeness):**
$$
B \cdot v_1 = \begin{pmatrix} 
0 & 2 & 2 & 2 \\ 
2 & 0 & 2 & 2 \\ 
2 & 2 & 0 & 2 \\ 
2 & 2 & 2 & 0 
\end{pmatrix} 
\begin{pmatrix} 1 \\ 0 \\ 0 \\ -1 \end{pmatrix} 
= \begin{pmatrix} 
0\cdot1 + 2\cdot0 + 2\cdot0 + 2\cdot(-1) \\ 
2\cdot1 + 0\cdot0 + 2\cdot0 + 2\cdot(-1) \\ 
2\cdot1 + 2\cdot0 + 0\cdot0 + 2\cdot(-1) \\ 
2\cdot1 + 2\cdot0 + 2\cdot0 + 0\cdot(-1) 
\end{pmatrix}
= \begin{pmatrix} -2 \\ 0 \\ 0 \\ 2 \end{pmatrix} 
= -2 \cdot \begin{pmatrix} 1 \\ 0 \\ 0 \\ -1 \end{pmatrix} 
= -2 \cdot v_1
$$
So, the eigenvalue for $v_1$ is **$\lambda_1 = -2$**.

You can similarly verify that multiplying $B$ by $v_2$ and $v_3$ will also result in the vector being multiplied by $-2$, meaning their eigenvalues are also $-2$.

Comparing the eigenvalues we found ($-2$ and $6$), the largest one is $6$.

**Answer:** 6

---

## Explanation for a Kid

Imagine the matrix $B$ is a magical machine that transforms arrows (we call them vectors). 

Usually, when you put an arrow into this machine, it comes out pointing in a completely different direction.

But there are some very special arrows for this machine. When you put one of these special arrows in, it comes out pointing in the **exact same direction** (or exactly the opposite direction). The only thing the machine does to these special arrows is stretch them or shrink them! 

These special arrows are called **"eigenvectors"**. The amount the machine stretches or shrinks them is called the **"eigenvalue"**.

The problem gives us the machine (matrix $B$) and 4 of its special arrows ($v_1, v_2, v_3, v_4$). It asks us to find the largest "stretch factor" (largest eigenvalue).

How do we find it? We just feed the special arrows into the machine and see what happens!

Let's take the easiest-looking special arrow, which is $v_4 = \begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix}$. This arrow has length 1 in all 4 directions.

To feed it into the machine, we multiply the machine (matrix) by the arrow (vector):
$$
\begin{pmatrix} 
0 & 2 & 2 & 2 \\ 
2 & 0 & 2 & 2 \\ 
2 & 2 & 0 & 2 \\ 
2 & 2 & 2 & 0 
\end{pmatrix} 
\times
\begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix}
$$

Let's do the math for the top row: $(0 \times 1) + (2 \times 1) + (2 \times 1) + (2 \times 1) = 0 + 2 + 2 + 2 = 6$.
Because the machine is very symmetrical, the same thing happens for all the other rows!

So, the machine spits out a new arrow: $\begin{pmatrix} 6 \\ 6 \\ 6 \\ 6 \end{pmatrix}$.

Now, look at the original arrow and the new arrow:
Original arrow: $\begin{pmatrix} 1 \\ 1 \\ 1 \\ 1 \end{pmatrix}$
New arrow: $\begin{pmatrix} 6 \\ 6 \\ 6 \\ 6 \end{pmatrix}$

How many times bigger is the new arrow? It's exactly **6 times** bigger!
Since the new arrow is just the old arrow stretched by 6, the **stretch factor (eigenvalue) is 6**.

If we tested the other special arrows, we would find that the machine stretches them by $-2$ (the minus sign just means it flips them backwards and then stretches them by 2).

Since 6 is bigger than -2, the largest stretch factor is **6**.
