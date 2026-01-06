# Question 07: Git Log Function History

## Question

**Russian:** "Вы работаете в команде над проектом с длинной историей коммитов. Вам нужно понять, как изменялась конкретная функциональность со временем. Какая команда Git позволит вам увидеть историю изменений для конкретной функции или метода в коде?"

**English:** "You are working in a team on a project with a long commit history. You need to understand how a specific functionality has changed over time. Which Git command will allow you to see the history of changes for a specific function or method in the code?"

## Options

1. `git diff файл.txt`
2. `git grep имя_функции`
3. `git log -L :<имя_функции>: файл.txt`
4. `git log --stat файл.txt`
5. `git log -p файл.txt`

## Answer

**Option 3: `git log -L :<имя_функции>: файл.txt`**

## Explanation

### What each option does:

1. **`git diff файл.txt`**
   - Shows differences between working directory and staging area (or between commits)
   - Shows changes for the **entire file**, not a specific function
   - **NOT** for tracking function history

2. **`git grep имя_функции`**
   - Searches for text pattern across all files in current commit
   - Shows where function is defined/referenced **now**
   - **NOT** for viewing historical changes

3. **`git log -L :<имя_функции>: файл.txt`** ✅ **CORRECT**
   - `-L` flag enables line log (function history tracking)
   - Syntax: `-L :<function_name>:<file>` or `-L <start>,<end>:<file>`
   - Shows commit history **specifically for that function/method**
   - Shows how the function changed over time with diffs

4. **`git log --stat файл.txt`**
   - Shows commit history for the file
   - Shows statistics (lines added/removed) per commit
   - Shows changes for the **entire file**, not a specific function

5. **`git log -p файл.txt`**
   - Shows commit history for the file with patches (diffs)
   - Shows full diffs for the **entire file** per commit
   - **NOT** focused on a specific function

### Why Option 3 is correct:

The `-L` (line log) option is specifically designed to track the history of a **specific function or code range**:

- `-L :<function_name>:<file>` - Tracks function by name (uses regex to find function)
- `-L <start_line>,<end_line>:<file>` - Tracks specific line range
- Shows only commits that modified that function
- Shows diffs only for the function, not the entire file

### Example Usage:

```bash
# Track function by name
git log -L :calculateTotal:src/Main.java

# Track function by line range
git log -L 10,25:src/Main.java

# With more details
git log -L :calculateTotal:src/Main.java --pretty=format:"%h %s"
```

### Example Output:

```
commit abc123
Author: John Doe
Date: 2024-01-15

    Refactor calculateTotal method

-L:10:25:src/Main.java
@@ -10,6 +10,8 @@ public int calculateTotal() {
     int total = 0;
+    // Add tax calculation
+    total += calculateTax();
     return total;
   }
```

### Additional Useful Options:

- `git log -L :function:file --oneline` - Compact view
- `git log -L :function:file --all` - Include all branches
- `git log -L :function:file --graph` - Show branch structure

## Key Takeaway

To track the history of a **specific function or method**:
- Use `git log -L :<function_name>:<file>`
- This shows only commits that modified that function
- Shows focused diffs for the function, not the entire file
- Much more efficient than viewing entire file history
