# Question 04: Git Log Graph Command

## Question

**Russian:** "Вы хотите получить графическое представление истории коммитов в консоли, чтобы лучше понять веток и слияний в проекте. Какая команда Git позволит это сделать?"

**English:** "You want to get a graphical representation of the commit history in the console to better understand branches and merges in the project. Which Git command will allow you to do this?"

## Options

1. `git log --oneline --graph`
2. `git log --graph --all`
3. `git log --graph --decorate`
4. `git log --graph --oneline --all`
5. `git log --graph --pretty=format:'%h - %s'`

## Answer

**Option 4: `git log --graph --oneline --all`**

## Explanation

### What each option does:

1. **`git log --oneline --graph`**
   - Shows graph visualization
   - Shows one-line format
   - **BUT**: Only shows current branch history, not all branches

2. **`git log --graph --all`**
   - Shows graph visualization
   - Shows all branches (`--all`)
   - **BUT**: Uses default format (full commit messages), can be verbose

3. **`git log --graph --decorate`**
   - Shows graph visualization
   - Shows branch/tag decorations
   - **BUT**: Only shows current branch, not all branches

4. **`git log --graph --oneline --all`** ✅ **CORRECT**
   - Shows graph visualization (`--graph`)
   - Shows compact one-line format (`--oneline`)
   - Shows all branches (`--all`)
   - Perfect combination for understanding branch structure and merges

5. **`git log --graph --pretty=format:'%h - %s'`**
   - Shows graph visualization
   - Custom format (hash and subject)
   - **BUT**: Only shows current branch, not all branches

### Why Option 4 is correct:

- `--graph`: Draws ASCII graph showing branch structure and merges
- `--oneline`: Makes output compact and readable
- `--all`: Shows all branches, not just current branch - **critical** for understanding branch structure

### Example Output:

```
*   a1b2c3d Merge branch 'feature'
|\
| * d4e5f6g Add new feature
* | g7h8i9j Fix bug
|/
* j0k1l2m3 Initial commit
```

### Additional Useful Combinations:

- `git log --graph --oneline --all --decorate` - Adds branch/tag names
- `git log --graph --oneline --all --simplify-by-decoration` - Shows only important commits

## Key Takeaway

To see a graphical representation of **all branches and merges** in a compact format, you need:
- `--graph` for visualization
- `--oneline` for readability
- `--all` to include all branches (not just current)
