# Q48: Gold Stealing (Dasha and Zulik)

**Тема:** Смекалка

## Problem Statement

**Russian:**
В первой серии мультфильма днем Даша-путешественница и Жулик нашли по одинаковому мешку золота.
1. Ночью, в первую серию Жулик крадёт у Даши 1/10 её мешка.
2. Днём, во второй серии Даша находит Жулика и отбирает у него одну 1/11 его нынешнего запаса.
3. Ночью, в третьей серии Жулик снова тайком забирает у Даши 1/12 её тогдашнего золота.
4. Днём, в четвёртой серии Даша, не желая уступать, отнимает у Жулика 1/13 его текущего мешка.
5. ...

Так у заурядных режиссеров появилось много однотипных серий. В итоге, Жулик в последней серии сезона смог украсть 1/100 золота девочки и был таков, Даша не смогла его найти.

Вопрос: на сколько килограмм золота было больше у более удачливого персонажа мультфильма, если изначально у каждого в мешках было по 100 кг золота? Учитывайте что золото бесконечно делимо.

Если в ответе получилась десятичная дробь, округлите ее до двух знаков после точки.
Образец ответа: 50 (целое число) или 50.12 (округлённая десятичная дробь)

**English:**
In the first episode of the cartoon during the day, Dasha the Traveler (Dora) and Zulik (Swiper) each found an identical bag of gold.
1. At night, in the first episode, Zulik steals 1/10 of Dasha's bag.
2. During the day, in the second episode, Dasha finds Zulik and takes back 1/11 of his current stash.
3. At night, in the third episode, Zulik secretly takes 1/12 of Dasha's gold at that time.
4. During the day, in the fourth episode, Dasha, not wanting to give in, takes 1/13 of Zulik's current bag.
5. ...

Thus, ordinary directors produced many episodes of the same type. In the end, in the last episode of the season, Zulik managed to steal 1/100 of the girl's gold and was gone; Dasha could not find him.

Question: by how many kilograms of gold did the more fortunate cartoon character have more, if initially each had 100 kg of gold in their bags? Note that gold is infinitely divisible.

If the answer is a decimal fraction, round it to two decimal places.
Sample answer: `50` (integer) or `50.12` (rounded decimal fraction)

---

## Answer

**2**

---

## How to Solve (Step by Step)

### Step 1: Analyze the first few steps
Initially, both Dasha and Zulik have 100 kg of gold.
* **Initial:** Dasha = 100, Zulik = 100
* **Step 1:** Zulik steals $1/10$ of Dasha's gold.
  * Amount stolen = $100 \times \frac{1}{10} = 10$
  * Dasha = $100 - 10 = 90$
  * Zulik = $100 + 10 = 110$
* **Step 2:** Dasha takes $1/11$ of Zulik's current stash.
  * Amount taken = $110 \times \frac{1}{11} = 10$
  * Dasha = $90 + 10 = 100$
  * Zulik = $110 - 10 = 100$

Notice that after Step 2 (Dasha's turn), both characters are back exactly where they started: with 100 kg each.

### Step 2: Formulate the general pattern
Let's see if this "reset" happens after every pair of turns.
Assume before an odd turn (Zulik's turn), both have exactly 100 kg.
* **Odd turn (Zulik's turn):** Zulik steals fraction $\frac{1}{N}$ from Dasha.
  * Amount stolen = $\frac{100}{N}$
  * Dasha's gold = $100 - \frac{100}{N} = 100 \cdot \frac{N-1}{N}$
  * Zulik's gold = $100 + \frac{100}{N} = 100 \cdot \frac{N+1}{N}$
* **Even turn (Dasha's turn):** Dasha takes fraction $\frac{1}{N+1}$ from Zulik.
  * Amount taken = $\left(100 \cdot \frac{N+1}{N}\right) \times \frac{1}{N+1} = \frac{100}{N}$
  * Dasha's gold = $\left(100 \cdot \frac{N-1}{N}\right) + \frac{100}{N} = 100 \cdot \frac{N}{N} = 100$
  * Zulik's gold = $\left(100 \cdot \frac{N+1}{N}\right) - \frac{100}{N} = 100 \cdot \frac{N}{N} = 100$

The pattern is perfectly stable! After every single even turn (when Dasha takes gold back), their amounts reset to 100 kg each.

### Step 3: Evaluate the final step
We are told that in the final episode, Zulik steals **$1/100$** of Dasha's gold and runs away.
Since Zulik is stealing, this is an **odd turn** in our sequence.
This means the turn just before it was an even turn, and therefore both of them had exactly 100 kg of gold right before this last theft.

* **Final turn:** Zulik steals $1/100$ of Dasha's 100 kg.
  * Amount stolen = $100 \times \frac{1}{100} = 1$ kg.
  * Dasha's final gold = $100 - 1 = 99$ kg.
  * Zulik's final gold = $100 + 1 = 101$ kg.

### Step 4: Calculate the final difference
The question asks by how many kilograms the more fortunate character (Zulik) had more gold than the other (Dasha).
$$ \text{Difference} = 101 - 99 = 2 \text{ kg} $$

The answer is **2**.
