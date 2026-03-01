# Q30: SQL Query Optimization

## Problem Statement

How can we improve this SQL query? Explain your reasoning.

```sql
SELECT * FROM employees WHERE sex = 'm' AND salary = 300000 AND age = 20;
```

## Answer

This is a classic database performance tuning question. To improve this query, you need to look at both **how the query is written** and **how the underlying database schema/indexes are structured**.

Here are the main ways to improve this query, from easiest to most advanced:

### 1. Avoid `SELECT *`
Using `SELECT *` is considered a bad practice in production for several reasons:
*   **Network & Memory Overhead:** It retrieves all columns, even those you don't need, which wastes network bandwidth, database memory, and application memory.
*   **Prevents Covering Indexes:** If you fetch every column, the database is forced to read the actual table rows (a "lookup"). If you only fetch specific columns, you can create a "covering index" (explained below) that satisfies the entire query from the index alone, making it lightning fast.

**Improvement:** Explicitly list only the columns you need.
```sql
SELECT employee_id, first_name, last_name 
FROM employees 
WHERE sex = 'm' AND salary = 300000 AND age = 20;
```

### 2. Add a Composite Index
Without an index, the database engine has to perform a **Full Table Scan**—meaning it checks every single row in the `employees` table to see if it matches the conditions. For a large table, this is extremely slow.

You should add a **Composite Index** (an index on multiple columns) for the columns in the `WHERE` clause.

**Improvement:**
```sql
CREATE INDEX idx_emp_salary_age_sex ON employees (salary, age, sex);
```

#### Order of Columns in the Index (Important Reasoning Step)
When creating a composite index, the order of columns matters. The rule of thumb is to place the columns with the highest **cardinality** (most unique values/most selective) first.
1.  **`salary` (Highest Cardinality):** There are many possible salary values. Filtering by `salary = 300000` will instantly eliminate a massive portion of the table.
2.  **`age` (Medium Cardinality):** There are realistically about 50-80 possible ages in a workforce.
3.  **`sex` (Lowest Cardinality):** Usually only 2 or 3 distinct values. Filtering by `sex` first would still leave about 50% of the table to scan.

*(Note: Because all three conditions use the equality operator `=`, most modern database optimizers can effectively use the index regardless of column order for this specific query. However, ordering by cardinality makes the index more reusable for other queries that might only filter on `salary` or `salary AND age`.)*

### 3. Use a Covering Index
If we applied improvement #1 (selecting only specific columns like `employee_id`), we can make our index a **Covering Index**. This means the index contains all the data needed for the query, so the database never even has to read the actual table on disk.

In PostgreSQL or SQL Server, you can use the `INCLUDE` clause to add payload columns to the index without changing the B-Tree sorting:
```sql
CREATE INDEX idx_emp_covering 
ON employees (salary, age, sex) 
INCLUDE (employee_id, first_name, last_name);
```

### 4. Optimize Data Types
Ensure the columns are using the most efficient data types. Smaller data types mean smaller tables and smaller indexes, which means more data fits in the fast memory cache (RAM).
*   **`sex`:** Should be an `ENUM`, `CHAR(1)`, or a `TINYINT` (if mapped to integers). It should NOT be a `VARCHAR(50)`.
*   **`age`:** Should be a `TINYINT` (1 byte, holds up to 255), not a standard `INT` (4 bytes).
*   **`salary`:** Should be a `DECIMAL`/`NUMERIC` or a standard `INT`.

### 5. Table Partitioning (For Massive Tables)
If the `employees` table has tens or hundreds of millions of rows, you might consider table partitioning. For example, if the table is partitioned by `age` or `salary` ranges, the database can use "Partition Pruning" to completely ignore partitions that don't contain data for `age = 20`. 

*(Only mention this in an interview if asked about massive-scale datasets).*

---

### Summary of Best Answer
1. Replace `SELECT *` with specific columns (`SELECT id, name`).
2. Add a composite index on `(salary, age, sex)`, ordering by the most selective column (`salary`) first.
3. If possible, make it a Covering Index so the query can be resolved entirely from RAM without hitting the table storage.
