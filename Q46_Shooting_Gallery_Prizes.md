# Q46: Shooting Gallery Prizes

**Тема:** Теория вероятностей

## Problem Statement

**Russian:**
Игрок стреляет в тире, после каждого выстрела происходит одно из трёх событий:
* Выигрыш суперприза и 20 патронов — с вероятностью 0,01;
* Выигрыш обычного приза и 5 патронов — с вероятностью 0,05;
* Ничего — с вероятностью 0,94.

Каждый выстрел стоит 1 патрон. Стрелок начинает с 11 патронов.

Вопрос: сколько в среднем он получит суперпризов и обычных призов за всю игру?

Перемножьте два значения и внесите в качестве ответа. Если в ответе получилась десятичная дробь, округлите её до двух знаков после точки.
Образец ответа: `50` (целое число) или `50.12` (округленная десятичная дробь)

**English:**
A player shoots in a shooting gallery. After each shot, one of three events occurs:
* Wins a super prize and 20 cartridges — with probability 0.01;
* Wins a regular prize and 5 cartridges — with probability 0.05;
* Nothing — with probability 0.94.

Each shot costs 1 cartridge. The shooter starts with 11 cartridges.

Question: how many super prizes and regular prizes will he get on average during the entire game?

Multiply the two values and enter as the answer. If the answer is a decimal fraction, round it to two decimal places.
Sample answer: `50` (integer) or `50.12` (rounded decimal fraction)

---

## Answer

**0.2** (or **0.20**)

---

## How to Solve (Step by Step)

### Step 1: Expected cartridge gain per shot
Let's calculate the expected number of cartridges the player receives back from a single shot:
$$ E[\text{gain}] = 20 \cdot 0.01 + 5 \cdot 0.05 + 0 \cdot 0.94 = 0.20 + 0.25 = 0.45 $$

Since every shot costs exactly 1 cartridge to make, the **net loss** per shot on average is:
$$ \text{Net loss} = 1 - 0.45 = 0.55 \text{ cartridges.} $$

### Step 2: Expected total number of shots
The player starts with 11 cartridges. They will continue shooting until they run out. 
Because the cost of each shot is exactly 1 cartridge, they can only hit 0 exactly (there is no "overshooting" into negative cartridges). 

Thus, we can use Wald's equation, which states that the expected total number of shots $E[N]$ satisfies:
$$ \text{Initial cartridges} = E[N] \times \text{Net loss per shot} $$
$$ 11 = E[N] \times 0.55 $$
$$ E[N] = \frac{11}{0.55} = \frac{1100}{55} = 20 $$
So, on average, the player will make exactly **20 shots** during the entire game.

*(Alternative derivation: Let $E_1$ be the expected number of total shots starting with 1 cartridge. By the law of total expectation: $E_1 = 1 + 0.01 \cdot (20 E_1) + 0.05 \cdot (5 E_1) = 1 + 0.45 E_1$. Solving gives $0.55 E_1 = 1 \implies E_1 = 20/11$. Since each cartridge's "lifetime" is independent, starting with 11 cartridges gives $E_{11} = 11 \cdot E_1 = 20$.)*

### Step 3: Expected number of prizes
Now we find the expected number of each prize won over those 20 shots:
* **Super prizes:** $E[\text{super}] = 20 \cdot 0.01 = 0.2$
* **Regular prizes:** $E[\text{regular}] = 20 \cdot 0.05 = 1.0$

### Step 4: Multiply the values
The problem asks to calculate both expected values and then multiply them:
$$ 0.2 \times 1.0 = 0.2 $$

Following the instruction to round to up to two decimal places (if applicable), the answer is `0.2` (or `0.20`).
